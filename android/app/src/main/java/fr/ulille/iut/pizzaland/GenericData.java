package fr.ulille.iut.pizzaland;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class GenericData {

    private Activity activity;

    private static final String HOST = "10.0.2.2";

    private static String login = "";
    private static String passwd = "";

    private JSONArray answer;
    private RequestQueue queue;


    public GenericData(Activity classe){
        activity = classe;
        queue = Volley.newRequestQueue(classe);
        answer = null;
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

}
