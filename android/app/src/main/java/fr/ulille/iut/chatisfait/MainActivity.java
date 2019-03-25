package fr.ulille.iut.chatisfait;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ReceiverClient {

    private GenericDataCenter generic;

    private String searchQuery;

    private ArrayAdapter adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity2);

        SearchView search = (SearchView) findViewById(R.id.searchTool);

        RequestQueue queue = Volley.newRequestQueue(this);
        generic = new GenericDataCenter(this, queue);

        list = (ListView) findViewById(R.id.list);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String text){
                searchQuery = text;
                System.out.println(text);
                generic.doGet(MainActivity.this, GenericDataCenter.Articles);
                list.setVisibility(searchQuery.length() > 0 ? View.VISIBLE : View.INVISIBLE);

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
        ArrayList<String> choix = new ArrayList<>();
        final ArrayList<JSONObject> choixObj = new ArrayList<>();

        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                String[] cut = generic.cutJsonArray(obj);
                int nomIdx = generic.searchPattern(cut, ".*nom.*");
                if(cut[nomIdx].matches(".*"+searchQuery+".*")){
                    choix.add(cut[nomIdx].substring(7,cut[nomIdx].length()-1));
                    choixObj.add(obj);
                }

                System.out.println(obj);
            }
            if(choix.size() == 0){
                choix.add("Aucun résultat");
            }

            adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, choix);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(choixObj.size() > 0) {
                        Intent it = new Intent(MainActivity.this, Article.class);
                        it.putExtra("data", choixObj.get(position).toString());
                        startActivity(it);
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
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
        Intent it = new Intent(this, AbonnementBox.class);
        startActivity(it);
    }

    public void onPannierClick(View view){
        Intent it = new Intent(this, MonPannier.class);
        startActivity(it);
    }

}
