// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

export default class ListeArticle extends Page {
	constructor(){
		super(``);
	}

	render():string {
        return `<!doctype html>
        <html lang="en">
        <head>
            <meta charset="utf-8" />
            <link rel="icon" type="image/png" href="/fresh-bootstrap-table/assets/img/favicon.ico">
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        
            <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
        
            <meta name="twitter:card" content="product">
            <meta name="twitter:site" content="@creativetim">
            <meta name="twitter:title" content="Fresh Bootstrap Table by Creative Tim">
        
            <meta name="twitter:description" content="Probably the most beautiful and complex bootstrap table you've ever seen on the internet, this bootstrap table is one of the essential plugins you will need.">
            <meta name="twitter:creator" content="@creativetim">
            <meta name="twitter:image" content="http://s3.amazonaws.com/creativetim_bucket/products/31/original/opt_fbt_thumbnail.jpg">
            <meta name="twitter:data1" content="Fresh Bootstrap Table by Creative Tim">
            <meta name="twitter:label1" content="Product Type">
            <meta name="twitter:data2" content="Free">
            <meta name="twitter:label2" content="Price">
        
            <!-- Open Graph data -->
            <meta property="og:title" content="Fresh Bootstrap Table by Creative Tim" />
            <meta property="og:type" content="article" />
            <meta property="og:url" content="http://demos.creative-tim.com/fresh-bootstrap-table" />
            <meta property="og:image" content="http://s3.amazonaws.com/creativetim_bucket/products/31/original/opt_fbt_thumbnail.jpg"/>
            <meta property="og:description" content="Probably the most beautiful and complex bootstrap table you've ever seen on the internet, this bootstrap table is one of the essential plugins you will need." />
            <meta property="og:site_name" content="Creative Tim" />
        
        
            <link href="/fresh-bootstrap-table/assets/css/bootstrap.css" rel="stylesheet" />
            <link href="/fresh-bootstrap-table/assets/css/fresh-bootstrap-table.css" rel="stylesheet" />
            <link href="/fresh-bootstrap-table/assets/css/demo.css" rel="stylesheet" />
        
            <!--     Fonts and icons     -->
            <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
            <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
        
            <script type="text/javascript" src="/fresh-bootstrap-table/assets/js/jquery-1.11.2.min.js"></script>
            <script type="text/javascript" src="/fresh-bootstrap-table/assets/js/bootstrap.js"></script>
            <script type="text/javascript" src="/fresh-bootstrap-table/assets/js/bootstrap-table.js"></script>
        

            <script type="text/javascript" src="/fresh-bootstrap-table/assets/js/demo/gsdk-switch.js"></script>
            <script type="text/javascript" src="/fresh-bootstrap-table/assets/js/demo/jquery.sharrre.js"></script>
            <script type="text/javascript" src="/fresh-bootstrap-table/assets/js/demo/demo.js"></script>
        
        </head>
        <body>
        
        <div class="wrapper">
             <!--   Creative Tim Branding   -->
            <a href="http://creative-tim.com">
                 <div class="logo-container full-screen-table-demo">
                    <div class="logo">
                        <img src="/fresh-bootstrap-table/assets/img/new_logo.png">
                    </div>
                    <div class="brand">
                        Creative Tim
                    </div>
                </div>
            </a>
        
            <div class="fresh-table full-color-orange full-screen-table">
            
                <table id="fresh-table" class="table">
                    <thead>
                        <th data-field="id">ID</th>
                        <th data-field="name" data-sortable="true">Name</th>
                        <th data-field="salary" data-sortable="true">Salary</th>
                        <th data-field="country" data-sortable="true">Country</th>
                        <th data-field="city">City</th>
                        <th data-field="actions" data-formatter="operateFormatter" data-events="operateEvents">Actions</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Dakota Rice</td>
                            <td>$36,738</td>
                            <td>Niger</td>
                            <td>Oud-Turnhout</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Minerva Hooper</td>
                            <td>$23,789</td>
                            <td>Curaçao</td>
                            <td>Sinaai-Waas</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Sage Rodriguez</td>
                            <td>$56,142</td>
                            <td>Netherlands</td>
                            <td>Baileux</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Philip Chaney</td>
                            <td>$38,735</td>
                            <td>Korea, South</td>
                            <td>Overland Park</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>Doris Greene</td>
                            <td>$63,542</td>
                            <td>Malawi</td>
                            <td>Feldkirchen in Kärnten</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>Mason Porter</td>
                            <td>$78,615</td>
                            <td>Chile</td>
                            <td>Gloucester</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>Alden Chen</td>
                            <td>$63,929</td>
                            <td>Finland</td>
                            <td>Gary</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td>Colton Hodges</td>
                            <td>$93,961</td>
                            <td>Nicaragua</td>
                            <td>Delft</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td>Illana Nelson</td>
                            <td>$56,142</td>
                            <td>Heard Island</td>
                            <td>Montone</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>Nicole Lane</td>
                            <td>$93,148</td>
                            <td>Cayman Islands</td>
                            <td>Cottbus</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>11</td>
                            <td>Chaim Saunders</td>
                            <td>$5,502</td>
                            <td>Romania</td>
                            <td>New Quay</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>12</td>
                            <td>Josiah Simon</td>
                            <td>$50,265</td>
                            <td>Christmas Island</td>
                            <td>Sint-Jans-Molenbeek</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>13</td>
                            <td>Ila Poole</td>
                            <td>$67,413</td>
                            <td>Montenegro</td>
                            <td>Pontevedra</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>14</td>
                            <td>Shana Mejia</td>
                            <td>$58,566</td>
                            <td>Afghanistan</td>
                            <td>Ballarat</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>15</td>
                            <td>Lana Ryan</td>
                            <td>$64,151</td>
                            <td>Martinique</td>
                            <td>Portobuffolè</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>16</td>
                            <td>Daquan Bender</td>
                            <td>$91,906</td>
                            <td>Sao Tome and Principe</td>
                            <td>Walhain-Saint-Paul</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>17</td>
                            <td>Connor Wagner</td>
                            <td>$86,537</td>
                            <td>Germany</td>
                            <td>Solihull</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>18</td>
                            <td>Boris Horton</td>
                            <td>$35,094</td>
                            <td>Laos</td>
                            <td>Saint-Mard</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>19</td>
                            <td>Winifred Ryan</td>
                            <td>$64,436</td>
                            <td>Ireland</td>
                            <td>Ronciglione</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>20</td>
                            <td>Tanisha Hayes</td>
                            <td>$43,670</td>
                            <td>Argentina</td>
                            <td>Lint</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>21</td>
                            <td>Knox Morris</td>
                            <td>$58,474</td>
                            <td>Norway</td>
                            <td>Melle</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>22</td>
                            <td>Idola Stephenson</td>
                            <td>$25,236</td>
                            <td>Saint Barthélemy</td>
                            <td>Joncret</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>23</td>
                            <td>Abra Keller</td>
                            <td>$28,272</td>
                            <td>Switzerland</td>
                            <td>Thame</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>24</td>
                            <td>Teagan Mcgowan</td>
                            <td>$51,059</td>
                            <td>Sudan</td>
                            <td>Stalowa Wola</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>98</td>
                            <td>Stephen Pace</td>
                            <td>$60,739</td>
                            <td>Hungary</td>
                            <td>Cinco Esquinas</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>99</td>
                            <td>Desiree Humphrey</td>
                            <td>$51,795</td>
                            <td>Portugal</td>
                            <td>Forst</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>100</td>
                            <td>Vielka Norton</td>
                            <td>$44,584</td>
                            <td>Spain</td>
                            <td>Thane</td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
        </body>
            <script type="text/javascript">
                var $table = $('#fresh-table'),
                    $alertBtn = $('#alertBtn'),
                    full_screen = false,
                    window_height;
                $().ready(function(){
                    window_height = $(window).height();
                    table_height = window_height - 20;
                    $table.bootstrapTable({
                        toolbar: ".toolbar",
                        showRefresh: true,
                        search: true,
                        showToggle: true,
                        showColumns: true,
                        pagination: true,
                        striped: true,
                        sortable: true,
                        height: table_height,
                        pageSize: 25,
                        pageList: [25,50,100],
                        formatShowingRows: function(pageFrom, pageTo, totalRows){
                            //do nothing here, we don't want to show the text "showing x of y from..."
                        },
                        formatRecordsPerPage: function(pageNumber){
                            return pageNumber + " rows visible";
                        },
                        icons: {
                            refresh: 'fa fa-refresh',
                            toggle: 'fa fa-th-list',
                            columns: 'fa fa-columns',
                            detailOpen: 'fa fa-plus-circle',
                            detailClose: 'fa fa-minus-circle'
                        }
                    });
                    window.operateEvents = {
                        'click .like': function (e, value, row, index) {
                            alert('You click like icon, row: ' + JSON.stringify(row));
                            console.log(value, row, index);
                        },
                        'click .edit': function (e, value, row, index) {
                            alert('You click edit icon, row: ' + JSON.stringify(row));
                            console.log(value, row, index);
                        },
                        'click .remove': function (e, value, row, index) {
                            $table.bootstrapTable('remove', {
                                field: 'id',
                                values: [row.id]
                            });
                        }
                    };
                    $alertBtn.click(function () {
                        alert("You pressed on Alert");
                    });
                    $(window).resize(function () {
                        $table.bootstrapTable('resetView');
                    });
                });
                function operateFormatter(value, row, index) {
                    return [
                        '<a rel="tooltip" title="Like" class="table-action like" href="javascript:void(0)" title="Like">',
                            '<i class="fa fa-heart"></i>',
                        '</a>',
                        '<a rel="tooltip" title="Edit" class="table-action edit" href="javascript:void(0)" title="Edit">',
                            '<i class="fa fa-edit"></i>',
                        '</a>',
                        '<a rel="tooltip" title="Remove" class="table-action remove" href="javascript:void(0)" title="Remove">',
                            '<i class="fa fa-remove"></i>',
                        '</a>'
                    ].join('');
                }
                $('#sharrreTitle').sharrre({
                        share: {
                        twitter: true,
                        facebook: true,
                        googlePlus: true
                        },
                        template: "",
                        enableHover: false,
                        enableTracking: true,
                        render: function(api, options){
                        $("#sharrreTitle").html('Thank you for ' + options.total + ' shares!');
                        },
                        enableTracking: true,
                        url: 'http://demos.creative-tim.com/fresh-bootstrap-table'
                    });
                    $('#twitter').sharrre({
                      share: {
                        twitter: true
                      },
                      enableHover: false,
                      enableTracking: true,
                      buttons: { twitter: {via: 'CreativeTim'}},
                      click: function(api, options){
                        api.simulateClick();
                        api.openPopup('twitter');
                      },
                      template: '<i class="fa fa-twitter"></i> {total}',
                      url: 'http://demos.creative-tim.com/fresh-bootstrap-table'
                    });
                    $('#facebook').sharrre({
                      share: {
                        facebook: true
                      },
                      enableHover: false,
                      enableTracking: true,
                      click: function(api, options){
                        api.simulateClick();
                        api.openPopup('facebook');
                      },
                      template: '<i class="fa fa-facebook-square"></i> {total}',
                      url: 'http://demos.creative-tim.com/fresh-bootstrap-table'
                    });
            </script>
        
            <script>
              (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
              (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
              m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
              })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
              ga('create', 'UA-46172202-1', 'auto');
              ga('send', 'pageview');
            </script>
        
        </html>`;
        }
                        
    mount(container:HTMLElement):void {
        container.innerHTML = this.render();
    }
}

/*const liste:Element = document.querySelector(".panier");
    liste.click( (event:Event) => {
        event.preventDefault();
        PageRenderer.renderPage(HomePage);
    });*/
                    