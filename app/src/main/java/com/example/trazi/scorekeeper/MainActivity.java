package com.example.trazi.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int teamOneScore;
    int teamTwoScore;
    int MINIMUM_SCORE = 26;
    int MAXIMUM_SCORE = 50;
    int ONE_POINT = 1;
    int TWO_POINTS = 2;
    int THREE_POINTS = 3;
    int gameScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetGame();
    }

    public void resetGame() {
        teamOneScore = 0;
        teamTwoScore = 0;
        displayTeamOneScore(teamOneScore);
        displayTeamTwoScore(teamTwoScore);
        setRandomMaximumScore();
    }

    private void setRandomMaximumScore() {
        Random r = new Random();
        gameScore = r.nextInt(MAXIMUM_SCORE - MINIMUM_SCORE) + MINIMUM_SCORE;
    }

    public void addGamePoints(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.team_one_point_1:
                teamOneScore += ONE_POINT;
                break;
            case R.id.team_two_point_1:
                teamTwoScore += ONE_POINT;
                break;
            case R.id.team_one_point_2:
                teamOneScore += TWO_POINTS;
                break;
            case R.id.team_two_point_2:
                teamTwoScore += TWO_POINTS;
                break;
            case R.id.team_one_point_3:
                teamOneScore += THREE_POINTS;
                break;
            case R.id.team_two_point_3:
                teamTwoScore += THREE_POINTS;
                break;
        }

        boolean teamOneWinner = gameScore <= teamOneScore;
        boolean teamTwoWinner = gameScore <= teamTwoScore;

        if (!teamOneWinner && !teamTwoWinner) {
            displayTeamOneScore(teamOneScore);
            displayTeamTwoScore(teamTwoScore);
        } else {
            String winner = teamOneWinner ? "Team one" : "Team two";
            int score = teamOneWinner ? teamOneScore : teamTwoScore;
            this.showWinner(winner, score);
            resetGame();
        }
    }

    public void displayTeamOneScore(int value) {
        TextView textView = findViewById(R.id.score_team_one);
        textView.setText(String.valueOf(value));
    }

    public void displayTeamTwoScore(int value) {
        TextView textView = findViewById(R.id.score_team_two);
        textView.setText(String.valueOf(value));
    }

    public void showWinner(String team, int score) {
        String text = "Winner: " + team + ", Score: " + score;
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

}
