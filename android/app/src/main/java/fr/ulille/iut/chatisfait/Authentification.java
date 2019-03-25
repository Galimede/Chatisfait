package fr.ulille.iut.chatisfait;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class Authentification extends AppCompatActivity implements ReceiverClient {

    protected GenericDataCenter generic;
    private RequestQueue queue;

    private static String login;
    private static String mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authent);

        queue = Volley.newRequestQueue(this);
        generic = new GenericDataCenter(this, queue);
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
                    GenericDataCenter.setLogin(login);
                    GenericDataCenter.setPasswd(mdp);
                }

            }

            String text;
            int duration = Toast.LENGTH_SHORT;

            text = found ? "Connecté !" : "Login ou mot de passe incorrect";

            Toast toast = Toast.makeText(Authentification.this, text, duration);
            toast.show();

            if(found){
                startActivity(new Intent(this, MainActivity.class));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getJsonArrayResponse(JSONArray response) {
        authentify(response);
    }

    public void postJsonObjectResponse(JSONObject response){
        //Nothing here
    }

    public void onConnect(View view){
        generic.hideKeyboard();

        EditText log = (EditText) findViewById(R.id.login);
        EditText passwd = (EditText) findViewById(R.id.mdp);

        login = log.getText().toString();
        mdp = passwd.getText().toString();

        generic.doGet(this, GenericDataCenter.Utilisateurs);

    }

    public void doInscrip(View view){
        Intent it = new Intent(this, Inscription.class);
        startActivity(it);
    }
}
