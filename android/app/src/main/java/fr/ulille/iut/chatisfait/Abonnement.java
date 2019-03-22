package fr.ulille.iut.chatisfait;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class Abonnement extends AppCompatActivity implements ReceiverClient {

    private Spinner abo;
    private TextView selection;
    private EditText name;

    protected GenericDataCenter generic;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abonne);

        queue = Volley.newRequestQueue(this);
        generic = new GenericDataCenter(this, queue);


        selection = (TextView) findViewById(R.id.selection);

        abo = (Spinner) findViewById(R.id.spinnerAbo);
        String[] choix = getResources().getStringArray(R.array.choixAbo);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Abonnement.this, R.layout.support_simple_spinner_dropdown_item, choix);
        abo.setAdapter(dataAdapter);
        abo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener (){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                String item = (String) parent.getItemAtPosition(pos);
                selection.setText("item selected : "+item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

    }

    protected void abonne(JSONArray response){
        try {
            boolean found = false;

            for (int i = 0; i < response.length() ; i++) {
                JSONObject obj = response.getJSONObject(i);
                System.out.println(obj);
                String[] splitted = generic.cutJsonArray(obj);
                int nameIdx = generic.searchPattern(splitted, ".*pseudo.*");

                if(splitted[nameIdx].equals("\"pseudo\":\""+name.getText().toString()+"\"") && !found) {
                    //TO DO UPDATE
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getJsonArrayResponse(JSONArray response){
        abonne(response);
    }

    public void doAbonnement(View view){
        if(!GenericDataCenter.getLogin().equals("") && !GenericDataCenter.getPasswd().equals("")){
            name = (EditText) findViewById(R.id.aboNom);
            System.out.println("nom : "+name.getText().toString());

            /*String base_uri = generic.getFullHostname();

            String uri = base_uri + "/utilisateurs";

            JsonArrayRequest request = new JsonArrayRequest(
                    Request.Method.GET,
                    uri,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            abonne(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            System.out.println("requete : " + request);
            queue.add(request);*/
            generic.doGet(this);
        }else{
            Intent it = new Intent(this, Authentification.class);
            startActivity(it);
        }
    }

}
