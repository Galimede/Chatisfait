// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import PageRenderer from '../PageRenderer.js';
import Authent from './Authent.js';

export default class Inscription extends Page {
	constructor(){
        super(`S'inscrire`);
        // $FlowFixMe
        this.submit = this.submit.bind(this);
	}

	render():string {
        return `<div class="formcomplet">
        <label>
        <h1> Formulaire d'inscription</h1>
        <label>
        <div class="formulaireinscription">
            <form class="Inscription">
		<label>
			<input id="block" type="text" name="pseudo" placeholder="Entrez un pseudo" class="form-control">
        </label>
        <label>
        <input id="block"  type="password" name="password" placeholder="Entrez votre mot de passe" class="form-control">
    </label>
        <label>
			<input id="block" type="text" name="prenom" placeholder="Entrez un prenom" class="form-control">
        </label>
        <label>
			<input id="block" type="text" name="nom" placeholder="Entrez un nom" class="form-control">
        </label>
        <label>
			<input id="block"  type="text" name="adresse" placeholder="Entrez votre adresse" class="form-control">
        </label>
        </label>
			<input id="block"  type="text" name="mail" placeholder="Entrez votre adresse mail" class="form-control">
        </label> <br>
        <button id="boutonsubmit" type="submit" class="btn btn-default">Soumettre<img id="sendimg" src="images/send.jpg" /></button>
    </form>
    </div>
    </div>`;
    }
    
    mount(container:HTMLElement):void {
        $('form.Inscription').submit( this.submit );
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
			'pseudo',
			'password',
			'prenom',
            'nom',
            'adresse',
            'mail',
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
			const user = {
				pseudo: values.pseudo,
				mdp: values.password,
                prenom: values.prenom,
                nom: values.nom,
                adresse: values.adresse,
                adresseMail: values.mail,
            };
			fetch( 'http://localhost:8080/v1/utilisateurs/', {
					method:'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify(user)
				})
			.then(response => {
				if (!response.ok) {
					throw new Error( `${response.status} : ${response.statusText}` );
                }
				return response.json();
			})
            .catch( error => alert(`Enregistrement impossible : ${error.message}`) );
            alert('Inscription Reussie');
            const authentPage = new Authent();
            PageRenderer.renderPage(authentPage);
		}
    }
}

/*const envoi:Element = document.querySelector(.submit);
    envoi.click( (event:Event) => {
        event.preventDefault();
        PageRenderer.renderPage(HomePage);
    });*/
