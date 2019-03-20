package application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import fr.ulille.iut.pizzaland.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity2);

        System.out.println("salut bro");

        TextView title = (TextView) findViewById(R.id.label);
        EditText search = (EditText) findViewById(R.id.searchTool);

    }
}
