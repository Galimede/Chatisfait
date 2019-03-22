package fr.ulille.iut.pizzaland;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class Authentification extends AppCompatActivity {

    protected GenericData generic;
    private RequestQueue queue;

    private static String login;
    private static String mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authent);

        generic = new GenericData(this);
        queue = Volley.newRequestQueue(this);
    }

    protected void authentify(JSONArray response){
        System.out.println(response);
        boolean found = false;
        try {
            for (int i = 0; i < response.length() ; i++) {
                JSONObject obj = response.getJSONObject(i);
                String[] cut = generic.cutJsonArray(obj);
                int logIdx = generic.searchPattern(cut, ".*pseudo.*");
                int mdpIdx = generic.searchPattern(cut, ".*mdp.*");

                if(cut[mdpIdx].equals("\"mdp\":\""+mdp+"\"") && cut[logIdx].equals("\"pseudo\":\""+login+"\"") && !found) {
                    found = true;
                    GenericData.setLogin(login);
                    GenericData.setPasswd(mdp);
                }

            }

            String text;
            int duration = Toast.LENGTH_SHORT;

            text = found ? "Connecté !" : "Login ou mot de passe incorrect";

            Toast toast = Toast.makeText(Authentification.this, text, duration);
            toast.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showJsonArrayResponse(JSONArray response) {

    }

    public void onConnect(View view){
        generic.hideKeyboard();

        EditText log = (EditText) findViewById(R.id.login);
        EditText passwd = (EditText) findViewById(R.id.mdp);

        login = log.getText().toString();
        mdp = passwd.getText().toString();

        String base_uri = generic.getFullHostname();

        String uri = base_uri + "/utilisateurs";

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                uri,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        authentify(response);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        //System.out.println("requete : " + request);
        queue.add(request);
    }

    public void doInscrip(View view){
        Intent it = new Intent(this, Inscription.class);
        startActivity(it);
    }
}
