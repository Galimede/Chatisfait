package fr.ulille.iut.chatisfait.fr.ulille.iut.chatisfait.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.ulille.iut.chatisfait.R;

public class Inscription extends AppCompatActivity implements ReceiverClient {

    protected GenericDataCenter generic;
    private RequestQueue queue;

    private String login;
    private String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscrip);


        queue = Volley.newRequestQueue(Inscription.this);
        generic = new GenericDataCenter(this, queue);
    }

    public void showJsonObjectResponse(JSONObject response) {
        System.out.println("response --> " + response);
        try {
            Log.d("APPLI", response.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getJsonArrayResponse(JSONArray response){}

    public void postJsonObjectResponse(JSONObject response){
        GenericDataCenter.setLogin(login);
        GenericDataCenter.setPasswd(passwd);
    }

    public void onInscriptionPOST(View view){
        generic.hideKeyboard();
        String base_uri = generic.getFullHostname();
        final String uri = base_uri + GenericDataCenter.Utilisateurs;

        String nom = ((EditText) findViewById(R.id.name)).getText().toString();
        String prenom = ((EditText) findViewById(R.id.prenom)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String adresse = ((EditText) findViewById(R.id.address)).getText().toString();
        login = ((EditText) findViewById(R.id.pseudo)).getText().toString();
        passwd = ((EditText) findViewById(R.id.password)).getText().toString();

        String json = "{'abonne':'false', 'admin':'false', 'adresse': '"+adresse+"', 'adresseMail':'"+email+"', 'mdp':'"+passwd+"', 'pseudo':'"+login+"', 'nom':'"+nom+"', 'prenom':'"+prenom+"'}";

        generic.doPost(this, json, GenericDataCenter.Utilisateurs);
    }
}
