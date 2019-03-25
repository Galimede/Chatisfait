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

    public List<CommandeEntity> getAllCommandes() {
        TypedQuery<CommandeEntity> query = em.createNamedQuery("FindAllCommandes", CommandeEntity.class);
        return query.getResultList();
    }

    public List<AbonnementEntity> getAllAbonnements() {
        TypedQuery<AbonnementEntity> query = em.createNamedQuery("FindAllAbonnements", AbonnementEntity.class);
        return query.getResultList();
    }

    public List<NoteEntity> getAllNotes() {
        TypedQuery<NoteEntity> query = em.createNamedQuery("FindAllNotes", NoteEntity.class);
        return query.getResultList();
    }

    public List<BonReductionEntity> getAllBonReduction() {
        TypedQuery<BonReductionEntity> query = em.createNamedQuery("FindAllBonReductions", BonReductionEntity.class);
        return query.getResultList();
    }

    public BonReductionEntity getBonReductionByCode(int code) {
        return em.find(BonReductionEntity.class, code);
    }

    public ArticleEntity getArticleById(int idArticle) {
        return em.find(ArticleEntity.class, idArticle);
    }

    public CommandeEntity getCommandeById(int idCommande) {
        return em.find(CommandeEntity.class, idCommande);
    }

    public AbonnementEntity getAbonnementById(int idAbonnements) {
        return em.find(AbonnementEntity.class, idAbonnements);
    }

    public NoteEntity getNoteById(int idNote) {
        return em.find(NoteEntity.class, idNote);
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

    public NoteEntity getNoteByIdArticle(int idArticle) {
        NoteEntity returnValue;
        TypedQuery<NoteEntity> query = em.createNamedQuery("FindNoteByIdArticle", NoteEntity.class);
        query.setParameter("nidarticle", idArticle);
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

    public int createCommande(CommandeEntity commandeEntity) throws DatabaseConstraintException {
        try {
            em.persist(commandeEntity);
            em.flush();
        } catch (PersistenceException e) {
            throw new DatabaseConstraintException();
        }
        return commandeEntity.getIdCommande();
    }

    public int createAbonnement(AbonnementEntity abonnementEntity) throws DatabaseConstraintException {
        try {
            em.persist(abonnementEntity);
            em.flush();
        } catch (PersistenceException e) {
            throw new DatabaseConstraintException();
        }
        return abonnementEntity.getIdAbonnement();
    }

    public int createNote(NoteEntity noteEntity) throws DatabaseConstraintException {
        try {
            em.persist(noteEntity);
            em.flush();
        } catch (PersistenceException e) {
            throw new DatabaseConstraintException();
        }
        return noteEntity.getIdNote();
    }


    public void deleteArticle(String nom) throws Exception {
        ArticleEntity articleEntity = em.find(ArticleEntity.class,  nom);
        //System.out.println(articleEntity.toString());
        if (articleEntity == null) throw new Exception();
        em.remove(em.merge(articleEntity));
    }

    public void deleteCommande(int idCommande) throws Exception {
        CommandeEntity commandeEntity = em.find(CommandeEntity.class,  idCommande);
        //System.out.println(articleEntity.toString());
        if (commandeEntity == null) throw new Exception();
        em.remove(em.merge(commandeEntity));
    }

    public void deleteAbonnement(int idAbonnement) throws Exception {
        AbonnementEntity abonnementEntity = em.find(AbonnementEntity.class,  idAbonnement);
        //System.out.println(articleEntity.toString());
        if (abonnementEntity == null) throw new Exception();
        em.remove(em.merge(abonnementEntity));
    }

    public void deleteNote(int idArticle) throws Exception {
        NoteEntity noteEntity = em.find(NoteEntity.class,  idArticle);
        //System.out.println(articleEntity.toString());
        if (noteEntity == null) throw new Exception();
        em.remove(em.merge(noteEntity));
    }

    public void updateArticle(ArticleEntity articleEntity) throws DatabaseConstraintException {
        try {
            em.merge(articleEntity);
            em.flush();
        } catch (PersistenceException e) {
            throw new DatabaseConstraintException();
        }
    }

    public void updateCommande(CommandeEntity commandeEntity) throws DatabaseConstraintException {
        try {
            em.merge(commandeEntity);
            em.flush();
        } catch (PersistenceException e) {
            throw new DatabaseConstraintException();
        }
    }

    public void updateAbonnement(AbonnementEntity abonnementEntity) throws DatabaseConstraintException {
        try {
            em.merge(abonnementEntity);
            em.flush();
        } catch (PersistenceException e) {
            throw new DatabaseConstraintException();
        }
    }

    public void updateNote(NoteEntity noteEntity) throws DatabaseConstraintException {
        try {
            em.merge(noteEntity);
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

	
	/**
	 * Recherche d'une pizza à partir de son id.
	 * retourne null si aucune pizza de la base ne possède cet id.
	 * @param idPizza l'id recherché
	 * @return La pizza si elle existe
	 */

	
	/**
	 * Recherche d'une pizza à partir de son nom
	 * retourne null si aucune pizza de ce nom n'existe
	 * retourne null si il existe plusieurs pizzas de ce nom.
	 * @param nom le nom de la pizza recherchée.
	 * @return La pizza recherchée si elle existe
	 */

    /**
     * Met à jour les informations sur une pizza (y compris la liste de ses ingrédients).
     * L'objet Pizza doit avoir été construit auparavant (soit par insertion, soi par lecture).
     * La modification de l'ID est impossible par cette méthode.
     * La réutilisation d'un nom déjà existant provoque une exception.
     * @param pizza La pizza dont on veut modifier les informations.
     * @throws PizzaNameExistsException Si le nouveau nom de pizza existait déjà sur une autre  ligne (contrainte d'unicité de la table)
     */

}
