// @flow
import Component from '../components/Component.js';

export default class Page extends Component {
	#title:string;
	constructor( title:string, children:void|string|Array<string|Component> ){
		super('section', null, children);
		this.#title = title;
	}

	renderTitle():string {
		return ``;
	}
	mount(contentElement:HTMLElement):void {
		//
	}
}
