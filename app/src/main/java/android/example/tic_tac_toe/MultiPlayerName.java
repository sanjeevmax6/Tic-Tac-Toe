package android.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MultiPlayerName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_name);

        /*final GlobalClass globalClass = (GlobalClass) getApplicationContext();*/

        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);

        Button button3 = (Button) findViewById(R.id.button3);



        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String temp = editText.getText().toString();
                String temp2 = editText2.getText().toString();

               /* globalClass.setPlayer1Name(temp);
                globalClass.setPlayer2Name(temp2);*/

                    Intent intent = new Intent(MultiPlayerName.this, multiCanvas.class);
                    startActivity(intent);

            }
        });


    }
}
