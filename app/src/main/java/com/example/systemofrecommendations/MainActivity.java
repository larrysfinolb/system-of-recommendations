package com.example.systemofrecommendations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

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
    }
}