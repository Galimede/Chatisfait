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
                            <div class="Block_title">Excogitatum est super his, ut homines quidam ignoti, vilitate ipsa parum cavendi ad colligendos rumores per Antiochiae latera cuncta destinarentur relaturi quae audirent. hi peragranter et dissimulanter honoratorum circulis adsistendo pervadendoque divites domus egentium habitu quicquid noscere poterant vel audire latenter intromissi per posticas in regiam </div>
        
                        </div>
        
                        <div class="Block">
                            <div class="Block_img">
                                <img class="img" src="images/box/2.jpg" alt=""/> 
                            </div>
                            <div class="Block_title">L'abonnement</div>
                            <div class="Block_title">Excogitatum est super his, ut homines quidam ignoti, vilitate ipsa parum cavendi ad colligendos rumores per Antiochiae latera cuncta destinarentur relaturi quae audirent. hi peragranter et dissimulanter honoratorum circulis adsistendo pervadendoque divites domus egentium habitu quicquid noscere poterant vel audire latenter intromissi per posticas in regiam </div>
        
                        </div>
        
                        <div class="Block">
                            <div class="Block_img">
                                <img class="img" src="images/box/4.jpg" alt=""/> 
                            </div>
                            <div class="Block_title">Patience !</div>
                            <div class="Block_title">Excogitatum est super his, ut homines quidam ignoti, vilitate ipsa parum cavendi ad colligendos rumores per Antiochiae latera cuncta destinarentur relaturi quae audirent. hi peragranter et dissimulanter honoratorum circulis adsistendo pervadendoque divites domus egentium habitu quicquid noscere poterant vel audire latenter intromissi per posticas in regiam </div>
        
                        </div>
                    </div>
        
                </div>
            </section>
        
            <section id="bannerfooter"> 
        
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
