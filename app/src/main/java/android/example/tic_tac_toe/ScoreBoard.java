package android.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ScoreBoard extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        GlobalClass globalClass = (GlobalClass) getApplicationContext();

        String[] Names = new String[0];
        int[] score = new int[0];

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        Names[(globalClass.getCount())] = globalClass.getPlayer1Name();
        score[(globalClass.getCount())] = globalClass.getPlayer1Score();
        globalClass.setCount(( globalClass.getCount() ) + 1);

        Names[(globalClass.getCount())] = globalClass.getPlayer2Name();
        score[(globalClass.getCount())] = globalClass.getPlayer2Score();
        globalClass.setCount(( globalClass.getCount() ) + 1);

        MyAdapter myAdapter = new MyAdapter(this,Names,score);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
