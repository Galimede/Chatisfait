package fr.ulille.iut.chatisfait;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class GenericDataCenter {

    private Activity activity;

    protected static final String HOST = "10.0.2.2";

    private static String login = "";
    private static String passwd = "";

    public static final String Utilisateurs = "/utilisateurs/";
    public static final String Articles = "/articles/";

    private static String[] pannier;

    private RequestQueue queue;

    public GenericDataCenter(Activity classe){
        activity = classe;
    }


    public GenericDataCenter(Activity classe, RequestQueue queue){
        activity = classe;
        this.queue = queue;
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected String getFullHostname() {
        String host = "http://";
        host += HOST;
        host += ":" + String.valueOf(activity.getResources().getInteger(R.integer.default_port));
        host += activity.getResources().getString(R.string.default_base);
        return host;
    }

    public static void setLogin(String pseudo){
        if(login.equals("")) login = pseudo;
    }

    public static void setPasswd(String password){
        if(passwd.equals("")) passwd = password;
    }

    public static String getLogin(){
        return login;
    }

    public static String getPasswd(){
        return passwd;
    }

    protected String[] cutJsonArray(JSONObject data){
        String text = data.toString().substring(2, data.toString().length()-1);
        return text.split(",");
    }

    protected int searchPattern(String[] donnees, String regex){
        for(int i=0; i<donnees.length; i++){
            if(donnees[i].matches(regex)) return i;
        }

        return -1;
    }

    protected void doGet(final ReceiverClient iencli, String domain){
        String base_uri = getFullHostname();

        String uri = base_uri + domain;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                uri,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        iencli.getJsonArrayResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        queue.add(request);
    }

    protected boolean doPut(final ReceiverClient client, String jsonObj, String domain, String id){
        JSONObject jsonRequest;
        String base_uri = getFullHostname();

        String uri = base_uri + domain + id;

        try {
            jsonRequest = new JSONObject(jsonObj);
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.PUT,
                    uri,
                    jsonRequest,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            client.postJsonObjectResponse(response);
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

            return true;

        } catch (Exception e) {
            System.out.println("APPLI Error while initializing Json request content");
            return false;
        }
    }

    public static void addArticle(){

    }

}
