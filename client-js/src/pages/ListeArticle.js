// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

let articles : Array<{description:string,idArticle:string,nom:string,prix:number}>;
export default class ListeArticle extends Page {
	constructor(){
        super('Les articles');
        fetch('http://localhost:8080/api/v1/articles')
        .then((response: Response) => response.json())
        .then( MAJ );
	}

	render():string {
        let html:string  = '';
        articles.forEach( article => { html+= `<ul>
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
            </li>
        </ul>`;
        });
        console.log(html);
        return html;
        }
                        
}

function MAJ(data2:any) {
    const data: Array<{description:string,idArticle:string,nom:string,prix:number}> = data2;
    if (data) {
        articles = data;
    }
}

            