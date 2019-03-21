// @flow

import $ from 'jquery';
import Authent from './pages/Authent.js';
import AddBox from './pages/AddBox.js';
import AddAbonnement from './pages/AddAbonnement.js';

import HomePage from './pages/HomePage.js';
import PageRenderer from './PageRenderer.js';
import Menu from './components/Menu.js';


// configuration du PageRenderer
PageRenderer.titleElement = document.querySelector('.pageTitle');
PageRenderer.contentElement = document.querySelector('.contenu');

// Déclaration des pages 
const homePage:HomePage = new HomePage([]);
const authentPage:Authent = new Authent();
const boxPage:AddBox = new AddBox();
const abonnementPage:AddAbonnement = new AddAbonnement();

// Déclaration liens menus 
const menu:Menu = new Menu();
const logoLink = $('a.navbar-brand');
const connexionButton = $('.connecter');
const abonnementButton = $('.abonner');
const boxButton = $('.box');


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

