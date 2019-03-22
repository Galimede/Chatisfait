// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

let typechat:string="";
let typeabonnement:string="";

export default class AddAbonnement extends Page {
	constructor(){
		super('');
	}

	render():string {
        return `
		<h1 id="chatisfait">Chatisfait</h1>
		<h2 id="monchat">Mon Chat</2>
		<h3 id="textinfo"> Indiquez-nous la taille de votre matou afin que nous lui sélectionnions des produits adaptés!</h3>
		<div class="choixchat">	
			<div class="chaton">
				<h2 id="chatontext">Chaton</h2>
				<h3 id="infochaton">(De 2 mois à un an)</h3>
			</div>
			<div class="adulte">
				<h2 id="adulte">Adulte</h2>
				<h3 id="infoadulte">(De 1 an à 8 ans)</h3>
			</div>
			<div class="Senior">
				<h2 id="senior">Senior</h2>
				<h3 id="infosenior">(à partir de 8ans)</h3>
			</div>
		</div>
		<br>
		<h2 id="choisirformule">Choissiez une formule</h2>
		<h3 id="texteformuel">TEXTE</h3>
			<div class="mensuel">
					<h2 id="mensuel">Mensuel</h2>
					<h3 id="prixmensuel">19.90€</h3>
				</div>
			<br>
			<section class="Apropos">
			<h1 id="apropos">A propos de votre achat</h1>
			<h4 id="age">Age</h4>
			<select id="ageselect">
    			<option value="">Adulte</option>
    			<option value="Chaton">Chaton</option>
    			<option value="Senior">Senior</option>
			</select>
			<h4 id="Poils">Poils</h4>
			<select id="poilsselect">
    			<option value="Court">Court</option>
    			<option value="Long">Long</option>
			</select>
			<h4 id="sterelise">Stérilisé</h4>
			<select id="stereliseselect">
    			<option value="Oui">Oui</option>
    			<option value="Non">Non</option>
			</select>

`;
    }
    
	mount(container:HTMLElement):void {
		//$('form.addAbonnement').submit( this.submit );
		$('.chaton').click( (event:Event) => {
			event.preventDefault();
			typechat="chaton";
			$('.choixchat').html(`<h1>Chaton</h1>
								<select id="stereliseselect">
								<option value="Croquettes">Croquettes</option>
								<option value="Litières">Litières</option>
								</select>`);
		});
		$('.adulte').click( (event:Event) => {
			event.preventDefault();
			typechat="adulte";
			$('.choixchat').html(`<h1>Adulte</h1>
								<select id="stereliseselect">
								<option value="Croquettes">Croquettes</option>
								<option value="Litières">Litières</option>
								</select>`);
		});
		$('.Senior').click( (event:Event) => {
			event.preventDefault();
			typechat="senior";
			$('.choixchat').html(`<h1>Senior</h1>
								<select id="stereliseselect">
								<option value="Croquettes">Croquettes</option>
								<option value="Litières">Litières</option>
								</select>`);
		});
		$('.mensuel').click( (event:Event) => {
			event.preventDefault();
			typeabonnement="mensuel";
			$('.mensuel').html(`<h1>Vous avez souscrit à un abonnement mensuel</h1>`);
		});
	}
}

// TODO --> verifier l'authentification
/*let submit:HTMLElement = document.querySelector(".submit");
submit.click( (event:Event) => {
    event.preventDefault();
    PageRenderer.renderPage(HomePage);
});*/