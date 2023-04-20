package com.mad1.tictactoepm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class settings extends AppCompatActivity {

    private EditText textViewPlayer1;
    private EditText textViewPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Intent intent = getIntent();
        Player player1 = (Player) intent.getSerializableExtra("player1");
        Player player2 = (Player) intent.getSerializableExtra("player2");
        textViewPlayer1 = findViewById(R.id.edit_player1_name);
        textViewPlayer1.setText(player1.getName());

        textViewPlayer2 = findViewById(R.id.edit_player2_name);
        textViewPlayer2.setText(player2.getName());

        Button saveNames = findViewById(R.id.button_save);
        saveNames.setOnClickListener(v -> newNames(player1, player2));
        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(v -> homePage(player1, player2));

    }

    private void newNames(Player player1, Player player2) {
        String player1Name = textViewPlayer1.getText().toString();
        String player2Name = textViewPlayer2.getText().toString();
        if (player1Name.equals("") || player2Name.equals("")) {
            Toast.makeText(this, "Please enter a name for both players", Toast.LENGTH_SHORT).show();
        } else {
            player1.setName(player1Name);
            player2.setName(player2Name);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("player1", player1);
            intent.putExtra("player2", player2);
            startActivity(intent);
            finish();
        }
    }

    private void homePage(Player player1, Player player2) {
        String player1Name = textViewPlayer1.getText().toString();
        String player2Name = textViewPlayer2.getText().toString();
        player1.setName(player1Name);
        player2.setName(player2Name);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("player1", player1);
        intent.putExtra("player2", player2);
        startActivity(intent);
        finish();    }

}
