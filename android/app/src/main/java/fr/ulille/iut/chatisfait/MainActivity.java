package fr.ulille.iut.chatisfait;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ReceiverClient {

    private GenericDataCenter generic;

    private String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity2);

        SearchView search = (SearchView) findViewById(R.id.searchTool);

        RequestQueue queue = Volley.newRequestQueue(this);
        generic = new GenericDataCenter(this, queue);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String text){
                searchQuery = text;
                System.out.println(text);
                generic.doGet(MainActivity.this, GenericDataCenter.Articles);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String text){
                System.out.println("submit text "+text);
                return true;
            }
        });

    }

    public void getJsonArrayResponse(JSONArray response){
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                String[] cut = generic.cutJsonArray(obj);
                int nomIdx = generic.searchPattern(cut, ".*nom.*");
                if(cut[nomIdx].matches(".*"+searchQuery+".*")){
                    TextView t = (TextView) findViewById(R.id.test);
                    t.setText(cut[nomIdx]);
                }
                //System.out.println(nomIdx);
                System.out.println(obj);
            }
        }catch (Exception e){

        }
    }

    public void postJsonObjectResponse(JSONObject response){

    }

    public void onConnexion(View view){
        Intent it = new Intent(this, Authentification.class);
        startActivity(it);
    }

    public void onAbonne(View view){
        Intent it = new Intent(this, Abonnement.class);
        startActivity(it);
    }

    public void onBox(View view){

    }

    public void onPannierClick(View view){

    }

}
