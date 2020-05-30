package android.example.tic_tac_toe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class multiCanvas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIntent();

        PixelGridView pixelGridMulti = new PixelGridView(this);
        pixelGridMulti.setNumColumns(5);
        pixelGridMulti.setNumRows(5);

        setContentView(pixelGridMulti);

    }
}
