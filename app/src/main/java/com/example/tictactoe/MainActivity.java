package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean playerIs1 = true;
    private int numOfTurns = 0;
    private Button[][] cells = new Button[3][3];


    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


    private int player1Points;
    private int player2Points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewPlayer1 = findViewById(R.id.textView_p1);
        textViewPlayer2 = findViewById(R.id.textView_p2);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                cells[i][j] = findViewById(resID);
                cells[i][j].setOnClickListener(this);
            }
        }

        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")) {
            return;
        }
        if(playerIs1) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        numOfTurns++;

        if(isWin()) {
            if(playerIs1) {
                celebrate(1);
            } else {
                celebrate(2);
            }
        } else if (numOfTurns == 9) {
            celebrate(0);
        } else {
            playerIs1 = !playerIs1;
        }
    }

    private boolean isWin() {
        String[][] field = new String[3][3];

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                field[i][j] = cells[i][j].getText().toString();
            }
        }

        for(int i = 0; i < 3; i++) {
            if(field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for(int i = 0; i < 3; i++) {
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

       if(field[0][0].equals(field[1][1])
               && field[0][0].equals(field[2][2])
               && !field[0][0].equals("")) { //diagonal
                return true;
       }


       if(field[0][2].equals(field[1][1])
               && field[0][2].equals(field[2][0])
               && !field[0][2].equals("")) { //diagonal
                return true;
       }

       return false;
    }

    private void celebrate(int i) {

        if(i == 0) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            resetBoard();
        } else if(i == 1) {
            player1Points++;
            Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
            updatePointsText();
            resetBoard();
        } else if(i == 2) {
            player2Points++;
            Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
            updatePointsText();
            resetBoard();
        }

    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cells[i][j].setText("");
            }
        }

        numOfTurns = 0;
        playerIs1 = true;
    }

    private void resetGame() {
        resetBoard();
        player2Points = 0;
        player1Points = 0;
        textViewPlayer1.setText("Player 1: 0");
        textViewPlayer2.setText("Player 2: 0");
    }
}
