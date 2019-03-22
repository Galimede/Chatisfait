// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import {panier} from '../main.js';
import {box} from '../main.js';

let articles :Array<{description:string,idArticle:string,nom:string,prix:number}>;
export default class Achat extends Page {
	constructor(){
		super('Votre Panier');
        fetch('http://localhost:8080/api/v1/articles')
        .then((response: Response) => response.json())
        .then( MAJ );
	}

	render():string {
        return `<h1>Votre Panier</h1>
        <ul class="liste">

        </ul>
        <button class="achatbutton"
            type="button">
            S'abonner
        </button>
        `;
    }

    mount(container:HTMLElement):void {
        container.innerHTML = this.render();
        let htmlcontenu="";
        if(box==true){
            htmlcontenu+=`
            <h2>Abonnement à la box</h2>
            <li>prix: 19.99€/mois</li>
            <h2>Vos informations</h2>
            <li>Votre nom : ${user.nom}</li>
            <li>Votre prenom : ${user.prenom}</li>
            <li>Votre adresse : ${user.adresse}</li>
            <li>Votre mail : ${user.adressemail}</li>
            `
        }
        panier=[{idarticle:1},{idarticle:2}];
        if(panier!=null){
            articles.forEach( article => { 
                if(panier.includes(article.idArticle)){
                    htmlcontenu+= `
                <li>
                    <!--<div class="img"><a href="#"><img alt="img" src="images/post1.jpg"></a></div>-->
                    <div class="info">
                        <a class="title" href="#">${article.nom}</a>
                        <p>${article.description}</p>
                        <div class="price">
                            <span class="st">Prix:</span><strong>${article.prix}€</strong>
                        </div>
                        <div class="actions">
                            <a href="#">Details</a>
                            <a href="#">Ajouter Au Panier</a>
                        </div>
                    </div>
                </li>`;
                }
            });
        }
        
        $('liste').html(    htmlcontenu   );
    }
}

function MAJ(data2:any) {
    const data: Array<{description:string,idArticle:string,nom:string,prix:number}> = data2;
    if (data) {
        articles = data;
    }
}
