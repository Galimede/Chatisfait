package fr.ulille.iut.chatisfait;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.ulille.iut.chatisfait.fr.ulille.iut.chatisfait.user.Authentification;
import fr.ulille.iut.chatisfait.fr.ulille.iut.chatisfait.user.GenericDataCenter;
import fr.ulille.iut.chatisfait.fr.ulille.iut.chatisfait.user.MonPannier;
import fr.ulille.iut.chatisfait.fr.ulille.iut.chatisfait.user.ReceiverClient;

public class AbonnementBox extends AppCompatActivity implements ReceiverClient {

    protected GenericDataCenter generic;
    private RequestQueue queue;

    private static boolean isAbonne = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);
        generic = new GenericDataCenter(this, queue);

        setContentView(R.layout.box);
    }

    public void getJsonArrayResponse(JSONArray response){}

    public void postJsonObjectResponse(JSONObject response){
        System.out.println(response);

        MonPannier.setAbonnement();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, "Vous êtes abonné à la box", duration);
        toast.show();
    }

    public void onAbonnage(View view){
        if(!GenericDataCenter.getLogin().equals("") && !GenericDataCenter.getPasswd().equals("") && !isAbonne){
            String json = "{'abonne':'true'}";
            generic.doPut(this, json, GenericDataCenter.Utilisateurs, GenericDataCenter.getLogin());
            isAbonne = true;

        }else if(isAbonne){
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, "Vous êtes déjà abonné à la box", duration);
            toast.show();
        }else{
            Intent it = new Intent(this, Authentification.class);
            startActivity(it);
        }
    }

    public static void clear(){
        isAbonne = false;
    }
}
