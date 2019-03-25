// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import {panier} from '../main.js';
import {box} from './AddBox.js';
import {authentPage} from '../main.js';

let articles :Array<{description:string,idArticle:number,nom:string,prix:number}>=[];

export default class Achat extends Page {
	constructor(){
		super('Votre Panier');
        fetch('http://localhost:8080/v1/articles')
        .then((response: Response) => response.json())
        .then( MAJArticles );
	}

	render():string {
        return `
        <ul class="liste">

        </ul>
        <h2>Vos informations</h2>
        <ul class="vosinfos">

        </ul>
        <button class="confirmer" type="button">
            Confirmer
        </button>
        `;
    }

    mount(container:HTMLElement):void {
        container.innerHTML = this.render();

        const compte:{login:string,password:string}=authentPage.getCompte();

        if(compte.login!=null){
                fetch('http://localhost:8080/v1/utilisateurs/'+compte.login)
                .then( (response:Response) => response.json() )
                .then( MAJ );
        }


        console.log(box);
        if(box==true){  //a mettre a true
            $('.liste').html(`</ul>
            <li>Abonnement à la box</li>
            <ul>
            <li>prix: 19.99€/mois</li>
            </ul>
            `);

            $('.confirmer').click( (event:Event) => {
                event.preventDefault();
    
                if(compte.login != null){
                    console.log(1);
                    alert('Vous êtes abonné à la box');
                    let user:{idutilisateur:number, pseudo:string, mdp:string,sel:string,prenom:string,nom:string,adresse:string,adressemail:string,abonne:boolean};
                    fetch('http://localhost:8080/v1/utilisateurs/'+compte.login)
                    .then( (response:Response) => response.json() )
                    .then ( (data:any) => {
                        if(data) user = data;
                        user.abonne=true;
                        console.log(2);
                        console.log(user);
                        //console.log("TEST");
                        console.log(JSON.stringify(user));
                        return fetch( 'http://localhost:8080/v1/utilisateurs/'+compte.login, {
                            method:'PUT',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(user)
                        })
                    })
                    .then(response => {
        
                        if (!response.ok) {
                            throw new Error( `${response.status} : ${response.statusText}` );
                        }
                        return response.json();
                    })
                    .catch( error => alert(`Enregistrement impossible : ${error.message}`) );
                    
    
                    
                    
                
                }else{
                    PageRenderer.renderPage(authentPage);
                }
    
            });



        }else{
            let htmlcontenu:string="";
            if(panier){
                panier.forEach( article => { 
                    let flag:boolean=false;
                    for(let i=0;i<articles.length && flag==false;i++){
                        if(articles[i].idArticle==article){
                            flag=true;
                            htmlcontenu+= `
                            <li>
                                <div class="info">
                                    <a class="nom" href="">${articles[i].nom}</a>
                                    <p>${articles[i].description}</p>
                                    <p class="categorie">${articles[i].categorie}</p>
                                    <div class="price">
                                        <span class="st">Prix:</span><strong>${articles[i].prix}€</strong>
                                    </div>
                                </div>
                            </li>̀`;
                        }
                    }
                });

                $('.liste').html(htmlcontenu);
            }
        }
        
        
    }
}

function MAJ (data2: string){
    let user:{idutilisateur:number, pseudo:string, mdp:string,sel:string,prenom:string,nom:string,adresse:string,adressemail:string,abonne:boolean};
    if(data2) user = data2;
    if(user.pseudo!=null){
        $('.vosinfos').html(`<li>pseudo : ${user.pseudo}</li>
        <li>nom : ${user.nom}</li>
        <li>prenom : ${user.prenom}</li>
        <li>adresse : ${user.adresse}</li>
        <li>mail : ${user.adressemail}</li>`);
    }else{
        $('.vosinfos').html('<li>Veuillez vous connecter</li>');
    }
}

function MAJArticles(data2:any) {
    const data: Array<{description:string,idArticle:string,nom:string,prix:number}> = data2;
    if (data) {
        articles = data;
    }
}
