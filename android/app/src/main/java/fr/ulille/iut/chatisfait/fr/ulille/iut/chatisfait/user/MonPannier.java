package fr.ulille.iut.chatisfait.fr.ulille.iut.chatisfait.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.ulille.iut.chatisfait.Produit;
import fr.ulille.iut.chatisfait.R;

public class MonPannier extends AppCompatActivity {

    private static ArrayList<Produit> articles = new ArrayList<>();
    private static ListView achats;

    private static String formule = "   ";
    private static String abon = "Vous n'êtes pas abonné à la box";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.monpanier);

        achats = (ListView) findViewById(R.id.courses);

        TextView abo = (TextView) findViewById(R.id.abonnee);
        abo.setText(abon);

        TextView form = (TextView) findViewById(R.id.formulo);
        form.setText(formule);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, articles);
        achats.setAdapter(adapter);

        TextView facture = (TextView) findViewById(R.id.facture);
        facture.setText("Facture : "+getFinalFacture()+" €");

    }

    protected double getFinalFacture(){
        double facture = 0.0;
        for(int i=0; i< articles.size(); i++){
            facture += articles.get(i).getPrice();
        }

        return facture;
    }

    public static void setFormule(String formula){
        formule = formula;
    }

    public static void setAbonnement(){
        abon = "Vous êtes abonné à la box";
    }

    public static void add(String categorie, String nom, String prix, int id){
        articles.add(new Produit(categorie, nom, prix, id));
    }

    protected static void clear(){
        articles.clear();

        formule = "   ";
        abon = "Vous n'êtes pas abonné à la box";
    }

    public void onPaiement(View view){
        int duration = Toast.LENGTH_SHORT;

        String message = articles.size() > 0 ? "Paiement effectué : vous avez passé commande" : "Votre pannier est vide :(";

        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }
}
