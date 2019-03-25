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
        return `<div>
        <img id="boximg" src="images/box/bannierebox.png" alt="box">
        <button class="abonnerbox"
            type="button">
            S'abonner
        </button>
        </div>
        <div>   
        <h2>Comment ça marche ?</h2>
        </div>

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
