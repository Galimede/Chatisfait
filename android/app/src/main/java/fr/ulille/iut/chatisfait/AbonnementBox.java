package fr.ulille.iut.chatisfait;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class AbonnementBox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.box);
    }

    public void onAbonnage(View view){
        MonPannier.setAbonnement();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, "Vous êtes abonné à la box", duration);
        toast.show();
    }
}
