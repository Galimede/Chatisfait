// @flow

import $ from 'jquery';
import Authent from './pages/Authent.js';
import AddBox from './pages/AddBox.js';
import AddAbonnement from './pages/AddAbonnement.js';
import HomePage from './pages/HomePage.js';
import PageRenderer from './PageRenderer.js';
import Menu from './components/Menu.js';
import AboutUs from './pages/AboutUs.js';
import ListeArticle from './pages/ListeArticle.js';


// configuration du PageRenderer
PageRenderer.titleElement = document.querySelector('.pageTitle');
PageRenderer.contentElement = document.querySelector('.contenu');

// Déclaration des pages 
const homePage:HomePage = new HomePage([]);
export let authentPage:Authent = new Authent();
const boxPage:AddBox = new AddBox();
const abonnementPage:AddAbonnement = new AddAbonnement();
const aboutUs:AboutUs = new AboutUs();
const liste:ListeArticle = new ListeArticle();

// Déclaration liens menus 
const menu:Menu = new Menu();
const logoLink = $('a.navbar-brand');
const connexionButton = $('.connecter');
const abonnementButton = $('.abonner');
const boxButton = $('.box');
const aboutButton = $('.about');
const aboutpanier = $('.panier');

// Gestion des evenements bouttons 

logoLink.click((event:Event) => {
	event.preventDefault();
	PageRenderer.renderPage(homePage);
});

connexionButton.click( (event:Event) => {
	event.preventDefault();
	PageRenderer.renderPage(authentPage);
});

abonnementButton.click( (event:Event) => {
	event.preventDefault();
	PageRenderer.renderPage(abonnementPage);
});

boxButton.click( (event:Event) => {
	event.preventDefault();
	PageRenderer.renderPage(boxPage);
});

aboutButton.click( (event:Event) => {
	event.preventDefault();
	PageRenderer.renderPage(aboutUs);
});

aboutpanier.click( (event:Event) => {
	event.preventDefault();
	PageRenderer.renderPage(liste);
});

