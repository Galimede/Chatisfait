// @flow
import Page from './Page.js';
import $ from 'jquery';

export default class AboutUs extends Page {

    constructor() {
        super('Qui sommes nous ?');
    }

    render():string {
        return`
        <section id="banner" class="bannerInOut">
        <div class="banner-bg"></div>
        <img src="../images/prof2.jpg">
        <div class="container">
            <p> Chatisfait est une idée de Tom Hazard. Il a souhaité mettre en avant sa passion pour les chats en dédiant toute une gamme de services pour leur bien-être. Que ce soit on en terme d’accessoires, de soins, d’entretien et d’alimentation, mais aussi par la création d’une box avec tous les mois de nouvelles surprises dont ils raffolent. Tout est bon pour chatisfaire votre félin préféré.</p>
        </div>
    </section>

            `;}

}