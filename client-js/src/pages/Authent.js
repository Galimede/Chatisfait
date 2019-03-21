// @flow 
import Page from './Page.js';
import $ from 'jquery';
import Inscription from './Inscription';

import PageRenderer from '../PageRenderer.js';

// configuration du PageRenderer
PageRenderer.titleElement = document.querySelector('.pageTitle');
PageRenderer.contentElement = document.querySelector('.contenu');

const inscriptionPage:Inscription = new Inscription();

let users:Array<{id:Number, login:string, password:string,sel:string,prenom:string,nom:string,adresse:string,mail:string,aboonne:boolean}>;
export let compte: {login:string,password:string};
export default class Authent extends Page {
    
    constructor() {
        super('Se connecter');
        // $FlowFixMe
        this.submit = this.submit.bind(this);
        fetch('http://localhost:8080/api/v1/utilisateurs')
        .then( (response:Response) => response.text() )
        .then( MAJ );
    }

    render(): string {
        return `<form class="Authent">
		<label>
			Login :
			<input type="text" name="login" class="form-control">
		</label>
		<label>
			Mot De passe :
            <input type="password" name="password" class="form-control">
		</label>
        <button type="submit" class="btn btn-default">Ajouter</button>
    </form>
    <button class="btn btn-default inscription">S'inscrire</button>`;  //TODO : Rediriger sur inscription
    }

    submit(event: Event): void {
        event.preventDefault();
        const fieldNames: Array<string> = [
            'login',
            'password',
        ];
        // On recupere les valeurs des champs saisis
        const values: any = {};
        const errors: Array<string> = [];

        fieldNames.forEach((fieldName: string) => {
            const value = this.getFieldValue(fieldName);
            if (!value) {
                errors.push(`Le champ ${fieldName} ne peut être vide !`);
            }
            values[fieldName] = value;
        });

        if (errors.length) {
            // si des erreurs sont détectées, on les affiche
            alert(errors.join('\n'));
        } else {
            // si il n'y a pas d'erreur on recupère les données 
            compte= {
                login: values.login,
                password: values.password,
            };
            if (this.verificationCompte(compte)) {
                alert('Connexion réussie');
                // puis on vide le formulaire
                const form: ?HTMLElement = document.querySelector('form.Authent');
                if (form && form instanceof HTMLFormElement) {
                    form.reset();
                }
            } else {
                alert('Votre login ou mot de passe est incorrect');
                const form: ?HTMLElement = document.querySelector('form.Authent');
                if (form && form instanceof HTMLFormElement) {
                    form.reset();
                }
            }
        }
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

    mount(container:HTMLElement):void {
        $('form.Authent').submit( this.submit );
        $('.inscription').click( (event:Event) => {
            event.preventDefault();
            PageRenderer.renderPage(inscriptionPage);
        });
    }

    verificationCompte(compte: {login:string,password:string}) : boolean {  
        fetch('http://localhost:8080/api/v1/utilisateurs')
        .then( (response:Response) => response.text() )
        .then( MAJ );
        
        let flag:boolean=false;
        users.forEach(function(value){
            console.log("un tour");
            console.log(value.pseudo);
            console.log(value.mdp);
            console.log(compte.login);
            console.log(compte.password);
            console.log(value.pseudo === (compte.login) && value.mdp === (compte.password));
            if(value.pseudo === (compte.login) && value.mdp === (compte.password)){
                flag=true;
            }
        });
        return flag;
     
    }

}


function MAJ(data2:string){
    const data:Array<{id:number, login:string, password:string,sel:string,prenom:string,nom:string,adresse:string,mail:string,aboonne:boolean}> = JSON.parse(data2);
    if(data){
        users=data;
    }
}