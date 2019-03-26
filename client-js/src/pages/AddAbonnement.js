// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import {authentPage} from '../main.js';
import PageRenderer from '../PageRenderer.js';

let typechat:number=0;
let sterelise:boolean=false;
let typepoil:string="";
let typebox:number=0;
let poids:number=0;

// configuration du PageRenderer
PageRenderer.titleElement = document.querySelector('.pageTitle');
PageRenderer.contentElement = document.querySelector('.contenu');



export default class AddAbonnement extends Page {
	constructor(){
		super('');
		// $FlowFixMe
		this.submit = this.submit.bind(this);
	}

	render():string {
		return `
<div class="containerBox">
				<form class="form cf">
					<section class="profil">
						<h1>Profil de votre chat</h1>
						<div class="num1">
						<label>
						Age :
						<select name="age" class="form-control">
							<option  value="1">Chaton (de 2 mois à 1 an)</option>
							<option  value="2">Adulte (entre 1 et 8 ans)</option>
							<option  value="3">Senior (8 ans et plus)</option>
						</select>
						</label>
						<label>
						Poil:
						<select name="poil" class="form-control">
						<option  value="court">Court</option>
						<option  value="milong">Mi-long</option>
						<option  value="long">Long</option>
						<option  value="sans">Sans poils</option>
						</select>
						</label>
						<label>
						sterilise:
						<select name="sterilise" class="form-control">
						<option  value="0">Non</option>
						<option  value="1">Oui</option>
						</select>
						</label>
						<label>
						poids:
						<select name="poids" class="form-control">
						<option  value="1">mince</option>
						<option  value="2">normal</option>
						<option  value="3">surpoids</option>
						</select>
						</label>
							<br><br>
							<!-- <label>
							Sterelise :
							<select name="sterelise" class="form-control">
								<option  value="false">Non</option>
								<option  value="true">Oui</option>
							</select>
							</label>
						</div> -->
						<!-- <label>
						Poil :
						<select id="poil" class="form-control">
								<option  value="court">Court</option>
								<option  value="milong">Mi-long</option>
								<option  value="long">Long</option>
								<option  value="sans">Sans poils</option>
								</select>
						</label> -->
						<!-- <label>
						Poids :
						<select id="poids" class="form-control">
							<option  value="1">mince</option>
							<option  value="2">normal</option>
							<option  value="3">surpoids</option>
							</select>
						</label> -->

					 <section class="plan cf">
						<h1>Choix de la formule</h1>
						<input type="radio" name="choix" id="free" value="1"><label class="free-label four col" for="free">1 mois - 19,90€<br></label>
						<input type="radio" name="choix" id="basic" value="2"><label class="free-label four col" for="basic">3 mois<br></label>
						<input type="radio" name="choix" id="premium" value="3"><label class="free-label four col" for="premium">6 mois<br></label>
						<input type="radio" name="choix" id="gold" value="4"><label class="free-label four col" for="gold">12 mois</label>
						<input class="submit" type="submit" value="Valider">	
					</section> 
					</form>
</div>

`;
    }
    
	mount(container:HTMLElement):void {
		$('form.cf').submit( this.submit );
	}
	
	getFieldValue(fieldName:string):?string|Array<string>{
		// on récupère une référence vers le champ qui a comme attribut `name` la valeur fieldName (nom, base, prix_petite, etc.)
		const field:?HTMLElement = document.querySelector(`[name=${fieldName}]`);
		if ( field instanceof HTMLInputElement ) {
			// s'il s'agit d'un <input> on utilise la propriété `value`
			// et on retourne la chaine de caractère saisie
			if ( field.getAttribute('type') == 'radio') {
				const radioList:NodeList = document.querySelectorAll(`[name=${fieldName}]`);
				for (var i = 0, length = radioList.length; i < length; i++){
 					if (radioList[i].checked)
 						{
							return radioList[i].value;
 					}
				}	
		 
			} else {
				return field.value != '' ? field.value : null;
			}
		} else if ( field instanceof HTMLSelectElement ) {
			// s'il s'agit d'un <select> on utilise la propriété `selectedOptions`
			const values:Array<string> = [];
			for (let i = 0; i < field.selectedOptions.length; i++) {
				values.push( field.selectedOptions[i].value );
			}
			// et on retourne un tableau avec les valeurs sélectionnées
			return values.length ? values : null;
		}
		return null;
	}
    
    submit(event:Event):void {
		const compte:{login:string,password:string}=authentPage.getCompte();
		if(compte.login != null){
			event.preventDefault();
			let iduser;

			const fieldNames:Array<string> = [
				'age',
				'choix',
				'poil',
				'poids',
				'sterilise',
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


				fetch('http://localhost:8080/v1/utilisateurs/' + compte.login)
				.then((response: Response) => response.json())
				.then((data: any) => {
					const abonnement = {
						age: values.age[0],
						sterelise: values.sterelise,
						//poil: values.poil,
						choix: values.choix,
						poids: values.poids[0],
						idUtilisateur : data.idUtilisateur,
					};
					return fetch('http://localhost:8080/v1/abonnements', {
						method: 'POST',
						headers: { 'Content-Type': 'application/json' },
						body: JSON.stringify(abonnement)
					})
				})
				.then(response => {

				if (!response.ok) {
					throw new Error(`${response.status} : ${response.statusText}`);
				}
				alert('Votre abonnement a bien été enregistré');
				return response.json();
			})
			.catch(error => alert(`Enregistrement impossible : ${error.message}`));
		}

		}else{
			event.preventDefault();
			PageRenderer.renderPage(authentPage);
		}

    }


}

// TODO --> verifier l'authentification
/*let submit:HTMLElement = document.querySelector(".submit");
submit.click( (event:Event) => {
    event.preventDefault();
    PageRenderer.renderPage(HomePage);
});*/