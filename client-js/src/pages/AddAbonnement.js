// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import {authentPage} from '../main.js';

let typechat:number=0;
let sterelise:boolean=false;
let typepoil:string="";
let typebox:number=0;
let poids:number=0;

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
						choix: values.free,	<br><br>
						choix: values.free,	<!-- <label id="name" class="name">Nom</label>
						choix: values.free,	 <input type="text" name="nomduchat" id="nom" placeholder="Nom du chat" class="form-control" -->
					</secchoix: values.free,tion>
choix: values.free,
					 <secchoix: values.free,tion class="plan cf">
						<choix: values.free,h1>Choix de la formule</h1>
						<input type="radio" name="choix" id="free" value="1"><label class="free-label four col" for="free">1 mois - 19,90€<br></label>
						<input type="radio" name="radio1" id="3mois" value="free"><label class="free-label four col" for="free">3 mois<br></label>
						<!--<input type="radio" name="radio1" id="6mois" value="free"><label class="free-label four col" for="free">6 mois<br></label>
						<input type="radio" name="radio1" id="12mois" value="free"><label class="free-label four col" for="free">12 mois<br></label>-->
						<input class="submit" type="submit" value="Valider">	
					</section> 
					</form>
</div>

`;
    }
    
	mount(container:HTMLElement):void {
		$('form.cf').submit( this.submit );
		//$('#chaton').click( (event:Event) => {
		//	event.preventDefault();
		//	typechat=1;
			/*$('.choixchat').html(`<h1>Chaton</h1>
								<select id="stereliseselect">
								<option value="Croquettes">Croquettes</option>
								<option value="Litières">Litières</option>
								</select>`);
		});
	});
		$('#adulte').click( (event:Event) => {
			event.preventDefault();
			typechat=2;
			/*$('.choixchat').html(`<h1>Adulte</h1>
								<select id="stereliseselect">
								<option value="Croquettes">Croquettes</option>
								<option value="Litières">Litières</option>
								</select>`);
		});
		});
		$('#senior').click( (event:Event) => {
			event.preventDefault();
			typechat=3;
			/*$('.choixchat').html(`<h1>Senior</h1>
								<select id="stereliseselect">
								<option value="Croquettes">Croquettes</option>
								<option value="Litières">Litières</option>
								</select>`);
		});*/
	//});
		/*$('.mensuel').click( (event:Event) => {
			event.preventDefault();
			typeabonnement="mensuel";
			$('.mensuel').html(`<h1>Vous avez souscrit à un abonnement mensuel</h1>`);
		});
		$('#non').click( (event:Event) => {
			event.preventDefault();
			sterelise=false;
		});

		$('#oui').click( (event:Event) => {
			event.preventDefault();
			sterelise=true;
		});

		$('#court').click( (event:Event) => {
			event.preventDefault();
			typepoil="court";
		});

		$('#milong').click( (event:Event) => {
			event.preventDefault();
			typepoil="milong";
		});

		$('#long').click( (event:Event) => {
			event.preventDefault();
			typepoil="long";
		});

		$('#sans').click( (event:Event) => {
			event.preventDefault();
			typepoil="sans";
		});

		$('#free').click( (event:Event) => {
			event.preventDefault();
			typebox=1;
		});

		$('#mince').click( (event:Event) => {
			event.preventDefault();
			poids=1;
		});

		$('#normal').click( (event:Event) => {
			event.preventDefault();
			poids=2;
		})

		$('#surpoids').click( (event:Event) => {
			event.preventDefault();
			poids=3;
		});

		$('#3mois').click( (event:Event) => {
			event.preventDefault();
			typebox=2;
		});

		$('#6mois').click( (event:Event) => {
			event.preventDefault();
			typebox=3;
		});

		$('#12mois').click( (event:Event) => {
			event.preventDefault();
			typebox=4;
		});*/
	}



  /*  getFieldValue(fieldName: string): ?string | Array<string> {
        // on récupère une référence vers le champ qui a comme attribut `name` la valeur fieldName (login, password)
        const field: ?HTMLElement = document.querySelector(`[name=${fieldName}]`);
        if (field instanceof HTMLInputElement) {
            // s'il s'agit d'un <input> on utilise la propriété `value`
            // et on retourne la chaine de caractère saisie
            return field.value != '' ? field.value : null;
        }
        return null;
	}*/
	
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
		event.preventDefault();
		let iduser;
		const compte:{login:string,password:string}=authentPage.getCompte();

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
					//age: values.age,
					//sterelise: values.sterelise,
					//poil: values.poil,
					//choix: values.choix,
					//poids: values.poids,
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
				return response.json();
			})
			.catch(error => alert(`Enregistrement impossible : ${error.message}`));

		}
    }


}

// TODO --> verifier l'authentification
/*let submit:HTMLElement = document.querySelector(".submit");
submit.click( (event:Event) => {
    event.preventDefault();
    PageRenderer.renderPage(HomePage);
});*/