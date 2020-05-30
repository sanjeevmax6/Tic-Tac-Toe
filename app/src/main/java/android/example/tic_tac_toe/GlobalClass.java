package android.example.tic_tac_toe;

import android.app.Application;
import android.media.MediaPlayer;

public class GlobalClass extends Application {

    private int player1Score;
    private int player2Score;
    private String SinglePlayerScore;
    private String player1Name;
    private String Player2Name;
    private String SinglePlayerName;
    private int count = 0;

    public void playTouchSound(){
        MediaPlayer touchSound = MediaPlayer.create(this, R.raw.buttonsound);
        touchSound.start();
    }

    public void setSinglePlayerName(String singlePlayerName) {
        SinglePlayerName = singlePlayerName;
    }

    public String getSinglePlayerName() {
        return SinglePlayerName;
    }

    public void setSinglePlayerScore(String singlePlayerScore) {
        SinglePlayerScore = singlePlayerScore;
    }

    public String getSinglePlayerScore() {
        return SinglePlayerScore;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;

    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer2Name(String player2Name) {
        Player2Name = player2Name;
    }

    public String getPlayer2Name() {
        return Player2Name;
    }
}
