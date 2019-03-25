// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import List from 'list.js';
import {panier} from '../main.js';

let articles: Array<{ categorie: string, description: string, idArticle: string, image:string, nom: string, prix: number }>;
let options;
let articlesList;

export default class ListeArticle extends Page {
    constructor() {
        super('Les articles');
        fetch('http://localhost:8080/v1/articles')
            .then((response: Response) => response.json())
            .then(MAJ);
    }

    render(): string {
        let html: string = `<div id="articles"> 
        <input class="search" type="text" placeholder="Rechercher un article" aria-label="Search"></input>
        <button class="sort" data-sort="nom">Trier par nom</button>
        <button class="sort" data-sort="categorie">Trier par categorie</button>
        <ul class="list">`;
        articles.forEach(article => {
            html += `<div class="containerLP">
  <div class="product">
    <div class="img-containerLP">
      <img class="imgLP" src="${article.image}">
    </div>
    <div class="product-info">
      <div class="product-content">
        <h2 class="nom">${article.nom}</h2>
        <p class="categorie">${article.categorie} </p>
        <p class="categorie">Brosse</p>
        <p>${article.description}</p>

        <div class="buttons">
          <a class="button buy" href="#">Prix : <strong>${article.prix}€</strong> </a>
          <a class="button add" href="#" id="${article.idArticle}">Ajouter au panier</a>
        </div>
      </div>
    </div>
  </div>`;
        });
        html += '</div></ul>';
        return html;
    }

    mount(container:HTMLElement):void {
        options = { valueNames: ['nom','categorie'] };
        articlesList = new List('articles', options);

        const ajoutPanier = $('.ajoutPanier'); 
        ajoutPanier.click( (event:Event) => {
        event.preventDefault();
        let id = parseInt($(event.currentTarget).attr('id'),10);
       panier.push(id);
       console.log(panier);
});
	}
}

function MAJ(data2: any) {
    const data: Array<{ categorie: string, description: string, idArticle: string, image:string,  nom: string, prix: number }> = data2;
    if (data) {
        articles = data;
      
    }
}

