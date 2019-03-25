// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import PageRenderer from '../PageRenderer.js';
import {authentPage} from '../main.js';
import {achatpage} from '../main.js';
import Achat from './Achat.js';

export let box:boolean=false;

export default class AddBox extends Page {
	constructor(){
		super('');
	}

	render():string {
        return `<section id="banner" class="banner-in-out">
                    <div class="banner-bg home-banner"></div>
                    <div class="container">
                        <button class="btn">Button</button>
                        <a href="" class="btn text-uppercase abonnerbox">s'abonner
                        </a>
                    </div>
                </section>
        
        
                <section class="containerbis">
                    <h2>Comment ça marche ?</h2>
                    <div id="Conteneur">
                        <div class="Block">
                            <div class="Block_img">
                                <img class="img" src="images/box/1.jpg" alt=""/> 
                            </div>
                            <div class="Block_title">Créer le profil de votre chat</div>
                            <div class="Block_title">La personnalisation du profil de votre chat nous permettra d’adapter au mieux la box à votre compagnon afin qu’il soit pleinement satisfait !</div>
        
                        </div>
        
                        <div class="Block">
                            <div class="Block_img">
                                <img class="img" src="images/box/2.jpg" alt=""/> 
                            </div>
                            <div class="Block_title">L'abonnement</div>
                            <div class="Block_title">L’abonnement est sans engagement, vous pouvez le suspendre à tout moment, vous êtes libre de tout !</div>
        
                        </div>
        
                        <div class="Block">
                            <div class="Block_img">
                                <img class="img" src="images/box/4.jpg" alt=""/> 
                            </div>
                            <div class="Block_title">Patience !</div>
                            <div class="Block_title">Vous recevrez votre box entre le 15 et le 25 du mois, de quoi gâter votre chat de ses nombreuses surprises !</div>
        
                        </div>
                    </div>
        
                </div>
            </section>
        
            <section id="bannerfooter"> 
            <h2>Que contient la box ?</h2>
            <div class="img">
                <img src="images/box/pt1.png">
                Tous les mois recevez des produits correspondant au mieux à votre chat : des jouets, des accessoires, des produits d’hygiène et de soin et au moins un produit alimentaire !
            </div>
            <div>
                <img src="images/box/pt2.png">
                Ces produits sont bios et éco-responsables, pour son bien-être et celui de la Planet !
            </div>
            <div>
                <img src="images/box/pt3.png">
                Tous les mois de nouvelles surprises, testez une large gamme de produit dont votre chat ne se lassera jamais !
            </div>
        
            </section>

        `;
    }

    mount(container:HTMLElement):void {
        container.innerHTML = this.render();

        const compte:{login:string,password:string}=authentPage.getCompte();
        
        $('.abonnerbox').click( (event:Event) => {
            event.preventDefault();
            if(compte.login != null){
                box=true;
                this.achatpage = new Achat();
                PageRenderer.renderPage(achatpage);
                box=false;
            }else{
                PageRenderer.renderPage(authentPage);
            }

        });
    }
    
}
