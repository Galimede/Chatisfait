// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

export default class AddAbonnement extends Page {
	constructor(){
		super('');
	}

	render():string {
        return `
		<h1 id="chatisfait">Chatisfait</h1>
		<h2 id="monchat">Mon Chat</2>
		<h3 id="textinfo"> Indiquez-nous la taille de votre matou afin que nous lui sélectionnions des produits adaptés!</h3>
			<div class="chaton">
				<h2 id="chaton">Chaton</div>
				<h3 id="infochaton">(De 2 mois à un an)</h3>
			</div>
			<div class="adulte">
				<h2 id="adulte">Adulte</div>
				<h3 id="infoadulte">(De 1 an à 8 ans)</h3>
			</div>
			<div class="Senior">
				<h2 id="senior">Senior</div>
				<h3 id="infosenior">(à partir de 8ans)</h3>
			</div>
		<br>
		<h2 id="choisirformule">Choissiez une formule</h2>
		<h3 id="texteformuel">TEXTE</h3>
		<div class="mensuel">
				<h2 id="mensuel">Mensuel</div>
				<h3 id="prixmensuel">19.90€</h3>
				<h3 id="infomensuel">Sans engagement</h3>
			</div>
			<div class="3mois">
				<h2 id="3mois">3 mois</div>
				<h3 id="prix3mois">56.70€</h3>
				<h3 id="infomensuel">Une offre idéale pour se lancer</h3>
			</div>
			<div class="6mois">
				<h2 id="6mois">6 mois</div>
				<h3 id="prix6mois">101.40€</h3>
				<h3 id="info6mois">Seulement pour la box 16.90€</h3>
			</div>
			<div class="12mois">
				<h2 id="12mois">12 mois</div>
				<h3 id="prix12mois">178.80€</h3>
				<h3 id="info12mois">Seulement pour la box 16.90€</h3>
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
			<div class="adresseliv">
			<label>
			Nom
			<input id="nompropos"  type="text" name="nompropos" class="form-control">
			</label>
			</section>
			<section class="Adresse livraion">
			<label>
			Nom
			<input id="nom"  type="text" name="nomp" class="form-control">
			</label>
			<label>
			Prenom
			<input id="prenom"  type="text" name="prenom" class="form-control">
			</label>
			<label>
			Information
			<input id="information"  type="text" name="information" class="form-control">
			</label>
			</section>
			</div>

`;
    }
    
	mount(container:HTMLElement):void {
        $('form.addAbonnement').submit( this.submit );
	}
}

// TODO --> verifier l'authentification
/*let submit:HTMLElement = document.querySelector(".submit");
submit.click( (event:Event) => {
    event.preventDefault();
    PageRenderer.renderPage(HomePage);
});*/