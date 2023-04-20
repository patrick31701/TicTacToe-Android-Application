package com.mad1.tictactoepm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameStats extends AppCompatActivity {
    private TextView player1Stats;
    private TextView player2Stats;
    private TextView player1Name;
    private TextView player2Name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_stats);
        Intent intent = getIntent();
        Player player1 = (Player) intent.getSerializableExtra("player1");
        Player player2 = (Player) intent.getSerializableExtra("player2");

        player1Name = findViewById(R.id.player1_name_stats);
        player1Name.setText(player1.getName());
        player2Name = findViewById(R.id.player2_name_stats);
        player2Name.setText(player2.getName());
        player1Stats = findViewById(R.id.Player2_stats);
        player1Stats.setText("Wins: " + player1.getWins() + " Draws: " + player1.getDraws() + " Losses: " + player1.getLosses());
        player2Stats = findViewById(R.id.Player1_stats);
        player2Stats.setText("Wins: " + player2.getWins() + " Draws: " + player2.getDraws() + " Losses: " + player2.getLosses());
//        player2Name = findViewById(R.id.stats_name_2);
//        player2Name.setText(player2.getName()+":");
//        player1Name = findViewById(R.id.stats_name_1);
//        player1Name.setText(player1.getName()+":");
//        player2Stats = findViewById(R.id.Player1_stats);
//        player2Stats.setText("has won " + player1.getWins() + " times and lost " + player1.getLosses() + " times and drawn " + player1.getDraws() + " times");
//        player1Stats = findViewById(R.id.Player2_stats);
//        player1Stats.setText("has won " + player2.getWins() + " times and lost " + player2.getLosses() + " times and drawn " + player2.getDraws() + " times");
        Button back = findViewById(R.id.btn_back);
        back.setOnClickListener(v -> homePage(player1, player2));

    }

    private void homePage(Player player1, Player player2) {
        Intent intent2 = new Intent(this, MainActivity.class);
        intent2.putExtra("player1", player1);
        intent2.putExtra("player2", player2);
        startActivity(intent2);
        finish();
    }
}
