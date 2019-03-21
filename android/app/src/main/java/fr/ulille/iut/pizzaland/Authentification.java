package fr.ulille.iut.pizzaland;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class Authentification extends AppCompatActivity {

    private static final String HOST = "10.0.2.2";
    public static final String LOG_TAG = "APPLI";
    public static final String VOLLEY_TAG = "TEST_CHATISFAIT";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authent);
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
        String host = "";
        host += "http://";
        host += HOST;
        host += ":" + String.valueOf(getResources().getInteger(R.integer.default_port));
        host += getResources().getString(R.string.default_base);
        return host;
    }

    public void showError(VolleyError error) {
        error.printStackTrace();
    }

    public void showStringResponse(String response) {

    }

    public void onConnect(View view){
        hideKeyboard();
        String base_uri = getFullHostname();
        System.out.println(base_uri);
        Log.d(LOG_TAG, "Send started");

        String uri = base_uri + "/utilisateurs";
        System.out.println(uri);

        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(LOG_TAG, "Response received" + response);
                        showStringResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Authentification.this.showError(error);
                    }
                });
        stringRequest.setTag(VOLLEY_TAG);
        queue.add(stringRequest);
        Log.d(LOG_TAG, "Send done");*/
    }
}
