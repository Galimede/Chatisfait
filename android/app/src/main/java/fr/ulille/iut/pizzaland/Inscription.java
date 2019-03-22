package fr.ulille.iut.pizzaland;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Inscription extends AppCompatActivity {

    protected GenericData generic;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscrip);
        generic = new GenericData(this);

        queue = Volley.newRequestQueue(Inscription.this);
    }

    public void showJsonObjectResponse(JSONObject response) {
        System.out.println("response --> " + response);
        try {
            Log.d("APPLI", response.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onInscriptionPOST(View view){
        generic.hideKeyboard();
        String base_uri = generic.getFullHostname();
        final String uri = base_uri + "/utilisateurs";

        String nom = ((EditText) findViewById(R.id.name)).getText().toString();
        String prenom = ((EditText) findViewById(R.id.prenom)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String adresse = ((EditText) findViewById(R.id.address)).getText().toString();
        String login = ((EditText) findViewById(R.id.pseudo)).getText().toString();
        String passwd = ((EditText) findViewById(R.id.password)).getText().toString();

        JSONObject jsonRequest;
        try {
            jsonRequest = new JSONObject("{'abonne':'false', 'mdp':'"+passwd+"', 'pseudo':'"+login+"', 'nom':'"+nom+"', 'prenom':'"+prenom+"', 'adresseMail':'"+email+"', 'adresse':'"+adresse+"'}");
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    uri,
                    jsonRequest,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            showJsonObjectResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

            queue.add(request);
            System.out.println("request : "+request);
            GenericData.setLogin(login);
            GenericData.setPasswd(passwd);
        } catch (Exception e) {
            Log.e("APPLI", "Error while initializing Json request content");
        }
    }
}
