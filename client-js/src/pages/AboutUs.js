// @flow
import Page from './Page.js';
import $ from 'jquery';

export default class AboutUs extends Page {

    constructor() {
        super('Qui sommes nous ?');
    }

    render():string {
        return `<p> BLA BLA BLA </p>`;
    }

}