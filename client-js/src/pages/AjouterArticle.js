// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

let articles: Array<{ categorie: string, description: string, idArticle: string, image:string, nom: string, prix: number }>;

export default class AjouterArticle extends Page {
	constructor(){
        super(`Ajouter un article à la base`);
        // $FlowFixMe
        this.submit = this.submit.bind(this);
	}
    // nom, prix, description, image, categorie
	render():string {
        return `<div class="formajoutarticle">
        <label>
        <h1> Formulaire d'ajout d'article</h1>
        <label>
        <div class="formulaireajout">
            <form class="ajout">
		<label>
			<input id="form" type="text" name="nom" placeholder="Nom de l'article" class="form-control">
        </label>
        <label>
            <input id="form"  type="text" name="prix" placeholder="Prix" class="form-control">
        </label>
        <label>
			<input id="form" type="text" name="description" placeholder="Entrez la description du produit" class="form-control">
        </label>
        <label>
            <input type="file" name="image" id="image" placeholder="Entrez le chemin vers l'image" class="form-control" /><br />
        </label>
        <label>
            <input type="text" name="categorie" placeholder="Entrez la catégorie de l'article" class="form-control" /><br />
        </label>
        <button id="boutonsubmit" type="submit" class="btn btn-default">Soumettre<img id="sendimg" src="images/send.jpg" /></button>
    </form>
    </div>`;
    }
    
    mount(container:HTMLElement):void {
        $('form.ajout').submit( this.submit );
    }

    getFieldValue(fieldName: string): ?string | Array<string> {
        // on récupère une référence vers le champ qui a comme attribut `name` la valeur fieldName (login, password)
        const field: ?HTMLElement = document.querySelector(`[name=${fieldName}]`);
        if (field instanceof HTMLInputElement) {
            // s'il s'agit d'un <input> on utilise la propriété `value`
            // et on retourne la chaine de caractère saisie
            return field.value != '' ? field.value : null;
        }
        return null;
    }
    
    submit(event:Event):void {
		event.preventDefault();
		const fieldNames:Array<string> = [
			'nom',
			'prix',
			'description',
            'image',
            'categorie',
		];
		// on récupère la valeur saisie dans chaque champ
		const values:any = {};
		const errors:Array<string> = [];

		fieldNames.forEach( (fieldName:string) => {
			const value = this.getFieldValue(fieldName);
			if ( !value ){
				errors.push( `Le champ ${fieldName} ne peut être vide !` );
			}
			values[fieldName] = value;
		});

		if (errors.length) {
			// si des erreurs sont détectées, on les affiche
			alert( errors.join('\n') );
		} else {
			// si il n'y a pas d'erreur on envoie les données
			const article = {
				nom: values.nom,
				prix: values.prix,
                description: values.description,
                image: values.image,
                categorie: values.categorie,
			};
			fetch( 'http://localhost:8080/api/v1/articles/', {
					method:'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify(article)
				})
			.then(response => {
				if (!response.ok) {
					throw new Error( `${response.status} : ${response.statusText}` );
				}
				return response.json();
			})
			.catch( error => alert(`Enregistrement impossible : ${error.message}`) );
		}
    }
}