package fr.ulille.iut.chatisfait;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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

    protected static void setFormule(String formula){
        formule = formula;
    }

    protected static void setAbonnement(){
        abon = "Vous êtes abonné à la box";
    }

    public static void add(String categorie, String nom, String prix){
        articles.add(new Produit(categorie, nom, prix));
    }
}
