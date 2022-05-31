package com.example.systemofrecommendations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Declaramos las variables de los componentes que usaremos.
    Spinner spinner_listItems;
    TextView txt_listItems;
    TextView txt_recommendations;
    TextView txt_warning;

    // Declaramos e inicializamos con sus valores el array bidimensional que contendra el registro de compras previo.
    String[][] shoppingHistory = {
            {"Samuel", "Guantes", "Moby Dick(novela)", "Audifonos", "Lentes para sol", "Cafe"},
            {"Juan", "Franela deportiva", "Cafe", "Cafetera", "Cafe", "Cafe"},
            {"Janina", "Lentes para sol", "Zapatos deportivos", "Franela deportiva", "Zapatos deportivos", "Calcetines"},
            {"Henrique", "2001: A Space Odyssey(dvd)", "Audifonos", "Franela deportiva", "Guantes", "Sandalias"},
            {"Victor", "Franela deportiva", "Sandalias", "Lentes para sol", "Moby Dick(novela)", "Protector solar"},
            {"Tobias", "Moby Dick(novela)", "Cafe", "2001: A Space Odyssey(dvd)", "Audifonos", "Cafe"}
    };

    // Declaramos y establecemos el tamaño del array que contendra la lista de compra actual del usuario.
    String[] currentBuy = new String[4];
    int counter = 0; // Declaramos e inicializamos a 0 el contador que usaremos para recorrer newUser.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos el desplegable y le añadimos los items que contendra.
        spinner_listItems = findViewById(R.id.spinner_listItems);
        ArrayAdapter<CharSequence> adapterListItems = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapterListItems.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_listItems.setAdapter(adapterListItems);

        // Inicializamos las variables de los componentes de texto que usaremos.
        txt_listItems = findViewById(R.id.txt_listItems);
        txt_recommendations = findViewById(R.id.txt_recommendations);
        txt_warning = findViewById(R.id.txt_warning);
    }

    // Este metodo se encargara de ir añadiendo cada producto de nuestra compra a la lista, con un maximo de 5.
    public void buyItem(View view) {
        if (counter < 5) {
            String item = spinner_listItems.getSelectedItem().toString();
            String getCurrentBuy = txt_listItems.getText().toString();
            txt_listItems.setText(getCurrentBuy + item + "\n");

            currentBuy[counter] = item;
            counter++;

            if (counter == 4) {
                txt_recommendations.setText(returnRecommendation(shoppingHistory, currentBuy));
            }
        } else {
            txt_warning.setText("¡YA HAS COMPRADO 5 PRODUCTOS!");
        }
    }


    public String returnRecommendation(String[][] buyLog, String[] targetUser) {

        HashMap<String, Integer> similarity = new HashMap<String, Integer>();

        for (int i = 0; i < targetUser.length; i++) {
            System.out.println(targetUser[i]);
        }

        //Hacemos un hashmap con modelo <usuario, similitud> para hacer una lista de similitud
        for (int i = 0; i < buyLog.length; i++) {
            for (int j = 0; j < buyLog[i].length; j++) {
                if (j != 0) {
                    for (int m = 0; m < targetUser.length; m++) {
                        //System.out.println(targetUser[m].toString() + " - " + buyLog[i][j].toString());
                        if (targetUser[m].equals(buyLog[i][j])) {
                            System.out.println("equisde");
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

        System.out.println(similarity);


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

        System.out.println(mostSimilarityUser);

        boolean intoArray = false;

        String recommendations = "";

        //Finalmente creamos un string con los arrays del nuevo usuario y del usuario con mas similitud quitando las compras hechas por los dos
        for (int i = 0; i < buyLog.length; i++) {
            if (buyLog[i][0] == mostSimilarityUser) {
                for (int j = 0; j < buyLog.length; j++) {
                    if (j > 0) {
                        for (int k = 0; k < targetUser.length; k++) {
                            if (targetUser[k].equals(buyLog[i][j])) {
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