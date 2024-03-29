package fr.ulille.iut.chatisfait;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import fr.ulille.iut.chatisfait.fr.ulille.iut.chatisfait.user.Authentification;
import fr.ulille.iut.chatisfait.fr.ulille.iut.chatisfait.user.GenericDataCenter;
import fr.ulille.iut.chatisfait.fr.ulille.iut.chatisfait.user.MonPannier;

public class Article extends AppCompatActivity {

    private String category;
    private String prix;
    private String nom;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.article);

        ImageView image = (ImageView) findViewById(R.id.picture);

        String donnees = getIntent().getStringExtra("data");
        String[] data = donnees.split(",");

        id = Integer.parseInt(data[2].substring(12));

        String description = removeSpecialCharacter(data[1], '"');
        String imagePath = data[3].substring(29, data[3].length()-1);

        //imagePath = "/drawable/" + imagePath;

        File f = new File(imagePath);
        System.out.println("File is null "+ (f == null));
        int[] codes = getResources().getIntArray(R.array.a);

        int code = codes[id];

        //int resourceId = getResources().getIdentifier(imagePath, "drawable", this.getPackageName());
        //System.out.println(resourceId + "  " + R.drawable.croquette);
        image.setImageResource(code);

        category = data[0].substring(14, data[0].length()-1);
        prix = data[5].substring(7, data[5].length()-1) + "€";
        nom = data[4].substring(7, data[4].length()-1);

        TextView desc = (TextView) findViewById(R.id.description);
        desc.setText(description);

        TextView price = (TextView) findViewById(R.id.price);
        price.setText(prix);

        TextView name = (TextView) findViewById(R.id.nomArticle);
        name.setText(nom);


        System.out.println(description+imagePath);
    }

    private String removeSpecialCharacter(String toBuild, char pattern){
        StringBuffer buffer = new StringBuffer(toBuild);
        for(int i=0; i<toBuild.length(); i++){
            if(buffer.charAt(i) == pattern){
                buffer.setCharAt(i, ' ');
            }
        }

        return buffer.toString();
    }

    public void doAddToPannier(View view){
        if(GenericDataCenter.getLogin().equals("") && GenericDataCenter.getPasswd().equals("")){
            startActivity(new Intent(this, Authentification.class));
        }else {

            MonPannier.add(category, nom, prix, id);

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, "Produit ajouté au pannier", duration);
            toast.show();
        }
    }
}
