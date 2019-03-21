package fr.ulille.iut.chatisfait.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.persistence.*;

public class DataAccess {
    private final static Logger logger = LoggerFactory.getLogger(DataAccess.class);
    private EntityManager em;
    private EntityTransaction et;

    // global operations

    /**
     * Procedure d'initialisation de la connexion.
     * Crée un objet dataAccess et ouvre la transction.
     * La connexion doit être fermée par un appel à {@link #closeConnection(boolean)}.
     * @return L'objet {@link DataAccess} permettant l'accès à la base.
     */
    public synchronized static DataAccess begin() {
        return new DataAccess();
    }

    /**
     * Termine la connexion sur laqualle cette méthode est appliquée (avec ou sans validation).
     * Si commit vaut true, les opérations sont effectivement écrites dans la BDD,
     * sinon, les opérations sont ignorées.
     * @param commit ignore les opérations effectuées.
     */
    public void closeConnection(boolean commit) {
        if (commit) {
            this.commit();
        } else {
            this.rollback();
        }
        em.close();
    }

    /**
     * Valide toutes les opérations BDD de la connexion courante.
     */
    private void commit() {
        et.commit();
    }

    /**
     * Annule toutes les opérations BDD de la connexion courante.
     */
    private void rollback() {
        et.rollback();
    }

    /**
     * Crée un objet connection et initialise une transaction BDD.
     */
    private DataAccess() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PizzalandPersistenceUnit");
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    // Ingredient operations
    
    /**
     * Charge la liste de tous les ingrédientns de la base
     * @return La liste des ingredients
     */
	public List<UtilisateurEntity> getAllUtilisateurs() {
        TypedQuery<UtilisateurEntity> query = em.createNamedQuery("FindAllUtilisateurs", UtilisateurEntity.class);
        return query.getResultList();
	}

    public List<ArticleEntity> getAllArticles() {
        TypedQuery<ArticleEntity> query = em.createNamedQuery("FindAllArticles", ArticleEntity.class);
        return query.getResultList();
    }

    public ArticleEntity getArticleById(int idArticle) {
        return em.find(ArticleEntity.class, idArticle);
    }

    public ArticleEntity getArticleByNom(String nom) {
        ArticleEntity returnValue;
        TypedQuery<ArticleEntity> query = em.createNamedQuery("FindArticleByNom", ArticleEntity.class);
        query.setParameter("anom", nom);
        try {
            returnValue = query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
            returnValue = null;
        }
        return returnValue;
    }

    public int createArticle(ArticleEntity articleEntity) throws DatabaseConstraintException {
        try {
            em.persist(articleEntity);
            em.flush();
        } catch (PersistenceException e) {
            throw new DatabaseConstraintException();
        }
        return articleEntity.getIdArticle();
    }

    public void deleteArticle(String nom) throws Exception {
        ArticleEntity articleEntity = em.find(ArticleEntity.class,  nom);
        //System.out.println(articleEntity.toString());
        if (articleEntity == null) throw new Exception();
        em.remove(em.merge(articleEntity));
    }

    public void updateArticle(ArticleEntity articleEntity) throws DatabaseConstraintException {
        try {
            em.merge(articleEntity);
            em.flush();
        } catch (PersistenceException e) {
            throw new DatabaseConstraintException();
        }
    }
	
	/**
	 * Recherche d'un ingredient à partir de son id.
	 * retourne null si aucun ingredient de la base ne possède cet id.
	 * @param idUtilisateur l'id recherché
	 * @return L'ingredient si elle existe
	 */
	public UtilisateurEntity getUtilisateurById(int idUtilisateur) {
        return em.find(UtilisateurEntity.class, idUtilisateur);
	}
	
	/**
	 * Recherche d'un ingredient à partir de son nom.
	 * retourne null si aucun ingredient de la base ne possède ce nom.
	 * retourne null si il existe plusieurs ingredients de ce nom.
	 * @param pseudo le nom recherché
	 * @return L'ingredient si il existe
	 */
	public UtilisateurEntity getUtilisateurByPseudo(String pseudo) {
	    UtilisateurEntity returnValue;
        TypedQuery<UtilisateurEntity> query = em.createNamedQuery("FindUtilisateurByPseudo", UtilisateurEntity.class);
        query.setParameter("upseudo", pseudo);
        try {
            returnValue = query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
        	returnValue = null;
        }
        return returnValue;
	}

    /**
     * Ajoute un ingredients à la liste des ingrédients disponibles.
     * Un ingrédient de même nom ne doit pas déjà exister.
     * L'id (généré par la BDD) est renseigné automatiquement après l'ajout.
     * @param utilisateur L'ingrédient à ajouter
     * @return L'id de l'ingrédient ajouté
     * @throws DatabaseConstraintException Si un ingrédient de même nom eiste déjà
     */
	public int createUtilisateur(UtilisateurEntity utilisateur) throws DatabaseConstraintException {
        try {
            //if(em.find(UtilisateurEntity.class,  utilisateur.getPseudo()) == null) {
                em.persist(utilisateur);
                em.flush();
            //}
        } catch (Exception e) {
            throw new DatabaseConstraintException();
        }
        return utilisateur.getIdUtilisateur();
	}

    /**
     * Supprime de la base l'ingrédient spéxifié par son identifiant.
     * TODO On ne vérifie pas qu'aucune pizza n'utilise cet ingrédient
     * @param idUtilisateur L'identifant de la pizza à supprimer.
     * @throws Exception Si aucun ingrédient n'a cet id.
     */
	public void deleteUtilisateur(int idUtilisateur) throws Exception {
     // @TODO On ne vérifie pas qu'aucune pizza n'utilise cet ingrédient
        UtilisateurEntity utilisateurEntity = em.find(UtilisateurEntity.class,  idUtilisateur);
        if (utilisateurEntity == null) throw new Exception();
        em.remove(em.merge(utilisateurEntity));
	}

    /**
     *
     * @param utilisateurEntity L'ingredient mis à jour
     * @throws DatabaseConstraintException si l unicité du nom ou de l'id n'est pas respectée
     */
	public void updateUtilisateur(UtilisateurEntity utilisateurEntity) throws DatabaseConstraintException {
	    try {
	        em.merge(utilisateurEntity);
            em.flush();
        } catch (PersistenceException e) {
            throw new DatabaseConstraintException();
        }
    }

	// Pizza operations
	
	/**
	 * Lecture de la totalités des pizzas de la base
	 * @return La liste des pizzas
	 */
	public List<PizzaEntity> getAllPizzas() {
        TypedQuery<PizzaEntity> query = em.createNamedQuery("FindAllPizzas", PizzaEntity.class);
        return query.getResultList();
	}
	
	/**
	 * Recherche d'une pizza à partir de son id.
	 * retourne null si aucune pizza de la base ne possède cet id.
	 * @param idPizza l'id recherché
	 * @return La pizza si elle existe
	 */
	public PizzaEntity getPizzaById(long idPizza) {
        return em.find(PizzaEntity.class,  idPizza);
	}
	
	/**
	 * Recherche d'une pizza à partir de son nom
	 * retourne null si aucune pizza de ce nom n'existe
	 * retourne null si il existe plusieurs pizzas de ce nom.
	 * @param nom le nom de la pizza recherchée.
	 * @return La pizza recherchée si elle existe
	 */
    public PizzaEntity getPizzaByName(String nom) {
        TypedQuery<PizzaEntity> query = em.createNamedQuery("FindPizzaByName", PizzaEntity.class);
        query.setParameter("pnom", nom);
        try {
            return query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException e) {
        	return null;
        }
	}

    /**
     * Met à jour les informations sur une pizza (y compris la liste de ses ingrédients).
     * L'objet Pizza doit avoir été construit auparavant (soit par insertion, soi par lecture).
     * La modification de l'ID est impossible par cette méthode.
     * La réutilisation d'un nom déjà existant provoque une exception.
     * @param pizza La pizza dont on veut modifier les informations.
     * @throws PizzaNameExistsException Si le nouveau nom de pizza existait déjà sur une autre  ligne (contrainte d'unicité de la table)
     */
	public void updatePizza(PizzaEntity pizza) throws PizzaNameExistsException {
	    try {
            em.merge(pizza);
            em.flush();
        } catch (javax.persistence.PersistenceException e){
            throw new PizzaNameExistsException();
        }
	}
	
	public long createPizza(PizzaEntity pizza) {
        em.persist(pizza);
        em.flush();
        return pizza.getId();
	}

	public void deletePizza(long id) throws Exception {
        PizzaEntity pizza = em.find(PizzaEntity.class,  id);
        if (pizza == null) throw new Exception();
        em.remove(em.merge(pizza));
	}
}
