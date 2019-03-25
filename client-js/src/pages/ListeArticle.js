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
            html += `<br>
            <li>
                <div class="img"><a href=""><img alt="img" src="${article.image}"></a></div>
                <div class="info">
                    <a class="nom" href="">${article.nom}</a>
                    <p>${article.description}</p>
                    <p class="categorie">${article.categorie}</p>
                    <div class="price">
                        <span class="st">Prix:</span><strong>${article.prix}€</strong>
                    </div>
                    <div class="actions">
                        <a href="">Details</a>
                        <a href="" class="ajoutPanier" id="${article.idArticle}">Ajouter Au Panier</a>
                    </div>
                </div>
            </li>̀`;
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

