package fr.ulille.iut.chatisfait;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class Abonnement extends AppCompatActivity implements ReceiverClient {

    private Spinner abo;
    private Spinner form;

    protected GenericDataCenter generic;
    private RequestQueue queue;

    private String choix;
    private String formula;
    private boolean isSterilized;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abonne);

        queue = Volley.newRequestQueue(this);
        generic = new GenericDataCenter(this, queue);


        abo = (Spinner) findViewById(R.id.categorie);
        String[] choix = getResources().getStringArray(R.array.choixAbo);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Abonnement.this, R.layout.support_simple_spinner_dropdown_item, choix);
        abo.setAdapter(dataAdapter);
        abo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener (){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                String item = (String) parent.getItemAtPosition(pos);
                Abonnement.this.choix = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        form = (Spinner) findViewById(R.id.spinnerAbo);
        String[] formules = getResources().getStringArray(R.array.formul);
        ArrayAdapter<String> formAdapter = new ArrayAdapter<String>(Abonnement.this, R.layout.support_simple_spinner_dropdown_item, formules);
        form.setAdapter(formAdapter);
        form.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener (){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                String item = (String) parent.getItemAtPosition(pos);
                Abonnement.this.formula = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        CheckBox check = (CheckBox) findViewById(R.id.steril);
        isSterilized = check.isChecked();

    }

    public void postJsonObjectResponse(JSONObject response){
        System.out.println(response);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, "Vous êtes abonné !", duration);
        toast.show();

        System.out.println("choix: "+choix+"  formule: "+formula+" est sterile : "+isSterilized);
        MonPannier.setFormule(formula);
    }

    public void getJsonArrayResponse(JSONArray response){}

    public void doAbonnement(View view){
        if(!GenericDataCenter.getLogin().equals("") && !GenericDataCenter.getPasswd().equals("")){
            String json = "{'abonne':'true'}";
            generic.doPut(this, json, GenericDataCenter.Utilisateurs, GenericDataCenter.getLogin());

        }else{
            Intent it = new Intent(this, Authentification.class);
            startActivity(it);
        }
    }

}
