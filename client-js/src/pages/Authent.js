// @flow 
import Page from './Page.js'
import $ from 'jquery';

export default class Authent extends Page {

    constructor() {
        super('Se connecter');
        // $FlowFixMe
		this.submit = this.submit.bind(this);
    }

    render(): string {
        return `form class="Authent">
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
    <button>S'inscrire</button>`;  //TODO : Rediriger sur inscription
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
            const compte: {login:string,password:string} = {
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
        container.innerHTML = this.render();
	}

    verificationCompte(compte: {login:string,password:string}) : boolean {
        const fakeAccounts: Array<{ login: string, password: string }> = [
            {login:'titi',password:'toto'},
            {login:'jean',password:'michel'},
        ];    
       return fakeAccounts.includes(compte.login) && fakeAccounts.includes(compte.password) ? true:false; 
    }

}