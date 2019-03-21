package fr.ulille.iut.pizzaland;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    private static final String HOST = "10.0.2.2";
    public static final String LOG_TAG = "APPLI";
    public static final String VOLLEY_TAG = "TEST_CHATISFAIT";
    private RequestQueue queue;

    private static String login;
    private static String mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authent);

        queue = Volley.newRequestQueue(Authentification.this);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private String getFullHostname() {
        String host = "http://";
        host += HOST;
        host += ":" + String.valueOf(getResources().getInteger(R.integer.default_port));
        host += getResources().getString(R.string.default_base);
        return host;
    }

    private String[] cutJsonArray(JSONObject data){
        String text = data.toString().substring(2, data.toString().length()-1);
        String splitted[] = text.split(",");
        return splitted;
    }

    public void showJsonArrayResponse(JSONArray response) {
        boolean found = false;
        try {
            for (int i = 0; i < response.length() ; i++) {
                JSONObject obj = response.getJSONObject(i);
                String[] cut = cutJsonArray(obj);
                if(cut[2].equals("\"mdp\":\""+mdp+"\"") && cut[3].equals("\"pseudo\":\""+login+"\"") && !found)
                    found = true;

            }

            String text;
            int duration = Toast.LENGTH_SHORT;

            text = found ? "Connecté !" : "Login ou mot de passe incorrect";

            Toast toast = Toast.makeText(Authentification.this, text, duration);
            toast.show();

            //tvDisplay.setText(response.toString(4));
        } catch (Exception e) {
            e.printStackTrace();
            //tvDisplay.setText(getResources().getString(R.string.msg_jsonFormatError));
        }
    }

    public void onConnect(View view){
        hideKeyboard();

        EditText log = (EditText) findViewById(R.id.login);
        EditText passwd = (EditText) findViewById(R.id.mdp);

        login = log.getText().toString();
        mdp = passwd.getText().toString();

        String base_uri = getFullHostname();
        Log.d(LOG_TAG, "Send started");

        String uri = base_uri + "/utilisateurs";
        Log.d(LOG_TAG, "Uri: " + uri);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                uri,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        showJsonArrayResponse(response);
                        //cbGetPizzas.setChecked(true);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        System.out.println("requete : " + request);
        request.setTag(VOLLEY_TAG);
        queue.add(request);
    }

    public void doInscrip(View view){
        Intent it = new Intent(Authentification.this, Inscription.class);
        startActivity(it);
    }
}
