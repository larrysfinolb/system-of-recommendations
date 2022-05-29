package com.example.systemofrecommendations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Spinner listItems;
    TextView txt_listItems;
    TextView recommendations;

    String[][] shoppingHistory = {
            {"Samuel", "Guantes", "Moby Dick(novela)", "Audifonos", "Lentes para sol", "Cafe"},
            {"Juan", "Fanela deportiva", "Cafe", "Cafetera", "Cafe", "Cafe"},
            {"Janina", "Lentes para sol", "Zapatos deportivos", "Franela deportiva", "Zapatos deportivos", "Calcetines"},
            {"Henrique", "2001: A Space Odyssey(dvd)", "Audifonos", "Franela deportiva", "Guantes", "Sandalias"},
            {"Victor", "Franela deportiva", "Sandalias", "Lentes para sol", "Moby Dick(novela)", "Protector solar"},
            {"Tobias", "Moby Dick(novela)", "Cafe", "2001: A Space Odyssey(dvd)", "Audifonos", "Cafe"}
    };

    // String[] newUser = {"Te verde", "Franela deportiva", "Lentes para sol", "Sandalias"};
    String[] newUser = new String[4];
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItems = findViewById(R.id.listItems);
        ArrayAdapter<CharSequence> adapterListItems = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapterListItems.setDropDownViewResource(android.R.layout.simple_spinner_item);
        listItems.setAdapter(adapterListItems);

        txt_listItems = findViewById(R.id.txt_listItems);
        recommendations = findViewById(R.id.recommendations);
    }

    public void buyItem(View view) {
        String item = listItems.getSelectedItem().toString();
        newUser[counter] = item + "\n";
        counter++;
        String newText = txt_listItems.getText().toString();
        for (int i = 0; i < counter; i++) {
            txt_listItems.setText(newText + newUser[i]);
        }
        if(counter == 3) {
            recommendations.setText(returnRecommendation(shoppingHistory, newUser));
        }
    }

    String returnRecommendation(String[][] buyLog, String[] targetUser) {

        HashMap<String, Integer> similarity = new HashMap<String, Integer>();


        //Hacemos un hashmap con modelo <usuario, similitud> para hacer una lista de similitud
        for (int i = 0; i < buyLog.length; i++) {
            for (int j = 0; j < buyLog[i].length; j++) {
                if (j != 0) {
                    for (int m = 0; m < targetUser.length; m++) {
                        if (targetUser[m] == buyLog[i][j]) {
                            if (similarity.get(buyLog[i][0]) == null) {
                                similarity.put(buyLog[i][0], 1);
                            } else {
                                similarity.put(buyLog[i][0], similarity.get(buyLog[i][0]) + 1);
                            }

                        }
                    }
                }
            }
        }

        String mostSimilarityUser = "";
        int counter = 0;

        //Buscamos el usuario que tiene más silimitud y lo guardamos en la variable mostSimilarityUser;
        for (int i = 0; i < buyLog.length; i++) {
            if (similarity.get(buyLog[i][0]) != null) {
                if (similarity.get(buyLog[i][0]) > counter) {
                    counter = similarity.get(buyLog[i][0]);
                    mostSimilarityUser = buyLog[i][0];
                }
            }
        }

        boolean intoArray = false;

        String recommendations = "";

        //Finalmente creamos un string con los arrays del nuevo usuario y del usuario con mas similitud quitando las compras hechas por los dos
        for (int i = 0; i < buyLog.length; i++) {
            if (buyLog[i][0] == mostSimilarityUser) {
                for (int j = 0; j < buyLog.length; j++) {
                    if (j > 0) {
                        for (int k = 0; k < targetUser.length; k++) {
                            if (targetUser[k] == buyLog[i][j]) {
                                intoArray = true;
                            }
                        }
                        if (intoArray == false) {

                            recommendations += " " + buyLog[i][j];

                        } else {
                            intoArray = false;
                        }
                    }
                }
            }
        }

        //System.out.println(recommendations);

        return recommendations;
    }
}