package com.example.systemofrecommendations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner listItems;

    String shoppingHistory[][] = {
            {"Samuel", "Guantes", "Moby Dick(novela)", "Audifonos", "Lentes para sol", "Café"},
            {"Juan", "Fanela deportiva", "Café", "Cafetera", "Café", "Café"},
            {"Janina", "Lentes para sol", "Zapatos deportivos", "Franela deportiva", "Zapatos deportivos", "Calcetines"},
            {"Henrique", "2001: A Space Odyssey(dvd)", "Audífonos", "Franela deportiva", "Guantes", "Sandalias"},
            {"Victor", "Franela deportiva", "Sandalias", "Lentes para sol", "Moby Dick(novela)", "Protector solar"},
            {"Tobias", "Moby Dick(novela)", "Café", "2001: A Space Odyssey(dvd)", "Audífonos", "Café"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItems = findViewById(R.id.listItems);
        ArrayAdapter<CharSequence> adapterListItems = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapterListItems.setDropDownViewResource(android.R.layout.simple_spinner_item);
        listItems.setAdapter(adapterListItems);
    }
}