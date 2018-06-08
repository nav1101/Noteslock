package sp.voice.nita.noteslock;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;




public class EnterPasswordActivity extends Activity {

    ImageButton button1;
    EditText editText1;
    Button button;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");
        editText1 = (EditText) findViewById(R.id.editText1);
        button1 = (ImageButton) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String text = editText1.getText().toString();
                if(text.equals(password)){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(EnterPasswordActivity.this,"wrong password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}

