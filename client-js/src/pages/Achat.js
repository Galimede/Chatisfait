// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import { panier } from '../main.js';
import { box } from './AddBox.js';
import { authentPage } from '../main.js';
import PageRenderer from '../PageRenderer.js';
import ListeArticle from './ListeArticle.js';
import {resetPanier} from '../main.js';
import {boxPage} from '../main.js';

let articles: Array<{ description: string, idArticle: number, nom: string, prix: number }> = [];

// configuration du PageRenderer
PageRenderer.titleElement = document.querySelector('.pageTitle');
PageRenderer.contentElement = document.querySelector('.contenu');

export default class Achat extends Page {
    constructor() {
        super('Votre Panier');
        fetch('http://localhost:8080/v1/articles')
            .then((response: Response) => response.json())
            .then(MAJArticles);
    }

    render(): string {
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

    mount(container: HTMLElement): void {
        container.innerHTML = this.render();

        const compte: { login: string, password: string } = authentPage.getCompte();

        if (compte.login != null) {
            fetch('http://localhost:8080/v1/utilisateurs/' + compte.login)
                .then((response: Response) => response.json())
                .then(MAJ);
        }


        console.log(box);
        if (box == true) {  //a mettre a true
            $('.liste').html(`</ul>
            <li>Abonnement à la box</li>
            <ul>
            <li>prix: 19.99€/mois</li>
            </ul>
            `);

            $('.confirmer').click((event: Event) => {
                event.preventDefault();

                if (compte.login != null) {
                    let user: { idutilisateur: number, pseudo: string, mdp: string, sel: string, prenom: string, nom: string, adresse: string, adressemail: string, abonne: boolean };
                    fetch('http://localhost:8080/v1/utilisateurs/' + compte.login)
                        .then((response: Response) => response.json())
                        .then((data: any) => {
                            if (data) user = data;
                            user.abonne = true;
                            return fetch('http://localhost:8080/v1/utilisateurs/' + compte.login, {
                                method: 'PUT',
                                headers: { 'Content-Type': 'application/json' },
                                body: JSON.stringify(user)
                            })
                        })
                        .then(response => {

                            if (!response.ok) {
                                throw new Error(`${response.status} : ${response.statusText}`);
                            }else{
                                alert('Vous êtes abonné à la box');
                                PageRenderer.renderPage(boxPage);
                            }
                            return response.json();
                        })
                        .catch(error => alert(`Enregistrement impossible : ${error.message}`));





                } else {
                    PageRenderer.renderPage(authentPage);
                }

            });



        } else {
            let htmlcontenu: string = "<h2>Mon Panier</h2>";
            if (panier) {
                console.log("002");
                console.log(panier);
                panier.forEach(article => {
                    let flag: boolean = false;
                    for (let i = 0; i < articles.length && flag == false; i++) {
                        if (articles[i].idArticle == article) {
                            flag = true;
                            htmlcontenu += `<li>
                                    <div class="containerLP">
                        <div class="product">
                            <div class="img-containerLP">
                            <img class="imgLP" src="${articles[i].image}">
                            </div>
                            <div class="product-info">
                            <div class="product-content">
                                <h2 class="nom">${articles[i].nom}</h2>
                                <p class="categorie">${articles[i].categorie} </p>
                                <p>${articles[i].description}</p>

                                <div class="buttons">
                                <a class="button buy" href="#">Prix : <strong>${articles[i].prix}€</strong> </a>
                                </div>
                            </div>
                            </div>
                        </div>
                        </li>`;
        htmlcontenu += '</div></ul>';
                        }
                    }
                });

                $('.liste').html(htmlcontenu);

                if (compte.login != null) {


                    //COMMANDE
                    $('.confirmer').click((event: Event) => {
                        event.preventDefault();

                        const produits: ListeArticle = new ListeArticle();
                        if (panier.length == 0) {
                            alert('Veuillez ajouter des produits');
                            PageRenderer.renderPage(produits);
                        } else {
                            alert('Votre commande a été passée');
                            resetPanier();
                            PageRenderer.renderPage(boxPage);
                        }

                        panier.forEach(article => {

                            let user: { idutilisateur: number, pseudo: string, mdp: string, sel: string, prenom: string, nom: string, adresse: string, adressemail: string, abonne: boolean };
                            fetch('http://localhost:8080/v1/utilisateurs/' + compte.login)
                                .then((response: Response) => response.json())
                                .then((data: any) => {
                                    if (data) user = data;
                                    const commande = {
                                        adresse: user.adresse,
                                        adresseMail: user.adressemail,
                                        idUtilisateur: user.idUtilisateur,
                                        idArticle: article,
                                        nom: user.nom,
                                        prenom: user.prenom,
                                    }
                                    return fetch('http://localhost:8080/v1/commandes', {
                                        method: 'POST',
                                        headers: { 'Content-Type': 'application/json' },
                                        body: JSON.stringify(commande)
                                    })
                                })
                                .then(response => {

                                    if (!response.ok) {
                                        throw new Error(`${response.status} : ${response.statusText}`);
                                    }
                                    return response.json();
                                })
                                .catch(error => alert(`Enregistrement impossible : ${error.message}`));
                        });

                    });

                } else {
                    $('.confirmer').click((event: Event) => {
                        event.preventDefault();
                        PageRenderer.renderPage(authentPage);
                    });
                }
            }
        }


    }
}

function MAJ(data2: string) {
    let user: { idutilisateur: number, pseudo: string, mdp: string, sel: string, prenom: string, nom: string, adresse: string, adressemail: string, abonne: boolean };
    if (data2) user = data2;
    if (user.pseudo != null) {
        $('.vosinfos').html(`<li>pseudo : ${user.pseudo}</li>
        <li>nom : ${user.nom}</li>
        <li>prenom : ${user.prenom}</li>
        <li>adresse : ${user.adresse}</li>
        <li>mail : ${user.adresseMail}</li>`);
    } else {
        $('.vosinfos').html('<li>Veuillez vous connecter</li>');
    }
}

function MAJArticles(data2: any) {
    const data: Array<{ description: string, idArticle: string, nom: string, prix: number }> = data2;
    if (data) {
        articles = data;
    }
}
