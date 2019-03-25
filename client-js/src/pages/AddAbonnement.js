// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

let typechat:string="";
let sterelise:string="";
let typepoil:string="";
let typebox:string="";
let poids:string="";

export default class AddAbonnement extends Page {
	constructor(){
		super('');
	}

	render():string {
		return `
<div class="containerBox">
				<form class="form cf">
					<section class="profil">
						<h1>Profil de votre chat</h1>
						<div class="num1">
							<label>Age</label>
							<select id="age" class="pl-el">
								<option id="chaton">Chaton (de 2 mois à 1 an)</option>
								<option id="adulte">Adulte (entre 1 et 8 ans)</option>
								<option id="senior">Senior (8 ans et plus)</option>
							</select>
							<br><br>
							<label>Stérilisé</label>
							<select id="sterilise" class="pl-el">
								<option id="non">Non</option>
								<option id="oui">Oui</option>
							</select>
						</div>
						<div class="num2">
							<label>Poils</label>
							<select id="poil" class="pl-el">
								<option id="court">Court</option>
								<option id="milong">Mi-long</option>
								<option id="long">Long</option>
								<option id="sans">Sans poils</option>
							</select>
							<label>Poids</label>
							<select id="poids" class="pl-el">
								<option id="mince">mince</option>
								<option id="normal">normal</option>
								<option id="surpoids">surpoids</option>
							</select>
							<br><br>
							<label id="name" class="name">Nom</label>
							<input type="text" name="catname" id="catname" class="pl-el">
						</div>
					</section>

					<section class="plan cf">
						<h1>Choix de la formule</h1>
						<input type="radio" name="radio1" id="free" value="free"><label class="free-label four col" for="free">1 mois - 19,90€<br>sans engagement</label>
						<input class="submit" type="submit" value="Valider">	
					</section>
					</form>
</div>

`;
    }
    
	mount(container:HTMLElement):void {
		//$('form.addAbonnement').submit( this.submit );
		$('#chaton').click( (event:Event) => {
			event.preventDefault();
			typechat="chaton";
			/*$('.choixchat').html(`<h1>Chaton</h1>
								<select id="stereliseselect">
								<option value="Croquettes">Croquettes</option>
								<option value="Litières">Litières</option>
								</select>`);
		});*/
	});
		$('#adulte').click( (event:Event) => {
			event.preventDefault();
			typechat="adulte";
			/*$('.choixchat').html(`<h1>Adulte</h1>
								<select id="stereliseselect">
								<option value="Croquettes">Croquettes</option>
								<option value="Litières">Litières</option>
								</select>`);
		});*/
		});
		$('#senior').click( (event:Event) => {
			event.preventDefault();
			typechat="senior";
			/*$('.choixchat').html(`<h1>Senior</h1>
								<select id="stereliseselect">
								<option value="Croquettes">Croquettes</option>
								<option value="Litières">Litières</option>
								</select>`);
		});*/
	});
		/*$('.mensuel').click( (event:Event) => {
			event.preventDefault();
			typeabonnement="mensuel";
			$('.mensuel').html(`<h1>Vous avez souscrit à un abonnement mensuel</h1>`);
		});*/
		$('#non').click( (event:Event) => {
			event.preventDefault();
			sterelise="non";
		});

		$('#oui').click( (event:Event) => {
			event.preventDefault();
			sterelise="oui";
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
			typebox="free";
		});

		$('#mince').click( (event:Event) => {
			event.preventDefault();
			poids="mince";
		});

		$('#normal').click( (event:Event) => {
			event.preventDefault();
			poids="normal";
		})

		$('#surpoids').click( (event:Event) => {
			event.preventDefault();
			poids="surpoids";
		});

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
			'typechat',
			'sterelise',
			'typepoil',
            'typebox',
			'poids',
			'nom',
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
			const abonnement = {
				nom: values.nom,
				typechat: typechat,
				sterelise: sterelise,
				typepoil: typepoil,
				typebox: typebox,
				poids: poids,
			};
			fetch( 'http://localhost:8080/v1/abonnement/', {
					method:'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify(abonnement)
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

// TODO --> verifier l'authentification
/*let submit:HTMLElement = document.querySelector(".submit");
submit.click( (event:Event) => {
    event.preventDefault();
    PageRenderer.renderPage(HomePage);
});*/