package com.mad1.tictactoepm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    //    private int player1Points;
//    private int player2Points;
    private TextView textViewPlayer;
    Player player1 = new Player("X", 0, 1, 0, 0);
    Player player2 = new Player("O", 0, 2, 0, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlayer = findViewById(R.id.players_turn);
        Player player1NewName = (Player) getIntent().getSerializableExtra("player1");
        Player player2NewName = (Player) getIntent().getSerializableExtra("player2");
        if (player1NewName != null) {
            player1.setName(player1NewName.getName());
            player1.setWins(player1NewName.getWins());
            player1.setDraws(player1NewName.getDraws());
            player1.setLosses(player1NewName.getLosses());
        }
        if (player2NewName != null) {
            player2.setName(player2NewName.getName());
            player2.setWins(player2NewName.getWins());
            player2.setDraws(player2NewName.getDraws());
            player2.setLosses(player2NewName.getLosses());
        }
        textViewPlayer.setText(player1.getName() + "'s turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(v -> {
                    if (!((Button) v).getText().toString().equals("")) {
                        return;
                    }
                    if (player1Turn) {
                        ((Button) v).setText("X");
                        textViewPlayer.setText(player2.getName() + "'s turn");
                    } else {
                        ((Button) v).setText("O");
                        textViewPlayer.setText(player1.getName() + "'s turn");
                    }
                    roundCount++;
                    if (checkForWin()) {
                        if (player1Turn) {
                            player1Wins();
                        } else {
                            player2Wins();
                        }
                    } else if (roundCount == 9) {
                        draw();
                    } else {
                        player1Turn = !player1Turn;
                    }
                });
            }
        }
        Button buttonReset = findViewById(R.id.newGame);
        buttonReset.setOnClickListener(v -> resetBoard());
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void player1Wins() {
        player1.setWins(player1.getWins() + 1);
        player2.setLosses(player2.getLosses() + 1);
        textViewPlayer.setText(player1.getName() + " wins!");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setClickable(false);
            }
        }
    }

    private void player2Wins() {
        player2.setWins(player2.getWins() + 1);
        player1.setLosses(player1.getLosses() + 1);
        textViewPlayer.setText(player2.getName() + " wins!");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setClickable(false);
            }
        }
    }

    private void draw() {
        textViewPlayer.setText("Draw!");
        player1.setDraws(player1.getDraws() + 1);
        player2.setDraws(player2.getDraws() + 1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setClickable(false);
            }
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setClickable(true);
            }
        }
        roundCount = 0;
        player1Turn = true;
        textViewPlayer.setText(player1.getName() + "'s turn");
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("roundCount", roundCount);
//        outState.putInt("player1Points", player1Points);
//        outState.putInt("player2Points", player2Points);
//        outState.putBoolean("player1Turn", player1Turn);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        roundCount = savedInstanceState.getInt("roundCount");
//        player1Points = savedInstanceState.getInt("player1Points");
//        player2Points = savedInstanceState.getInt("player2Points");
//        player1Turn = savedInstanceState.getBoolean("player1Turn");
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, settings.class);
                intent.putExtra("player1", player1);
                intent.putExtra("player2", player2);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_gammeStats:
                Intent intent1 = new Intent(this, GameStats.class);
                intent1.putExtra("player1", player1);
                intent1.putExtra("player2", player2);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}