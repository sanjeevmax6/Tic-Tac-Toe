package android.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SinglePlayerName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_name);

        /*final GlobalClass globalClass = (GlobalClass) getApplicationContext();
*/
        final EditText editText3 = (EditText) findViewById(R.id.editText3);
        Button button = (Button) findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String temp = editText3.getText().toString();

                /*globalClass.setSinglePlayerName(temp);*/

                    Intent intent = new Intent(SinglePlayerName.this, singleCanvas.class);
                    startActivity(intent);

            }
        });


    }
}
