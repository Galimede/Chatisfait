package fr.ulille.iut.chatisfait;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Article extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.article);

        ImageView image = (ImageView) findViewById(R.id.picture);

        String donnees = getIntent().getStringExtra("data");
        String[] data = donnees.split(",");
        data[0].substring(2, data[0].length()-1);

        String description = removeSpecialCharacter(data[1], '"');
        String imagePath = data[3].substring(9, data[3].length()-1);
        String prix = data[5].substring(7, data[5].length()-1) + "€";
        String nom = data[4].substring(7, data[4].length()-1);

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
}
