package sp.voice.nita.noteslock;

import android.content.Intent;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class CreatePasswordActivity extends Activity {

    EditText editText1,editText2;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button = (ImageButton) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(CreatePasswordActivity.this,"Lets Have Password To your Notes", Toast.LENGTH_SHORT).show();
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();

                if(text1.equals("")||text2.equals("")){
                    //there is no password
                    Toast.makeText(CreatePasswordActivity.this,"No password eneterd", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(text1.equals(text2)){
                        //save the pass
                        SharedPreferences settings = getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("password",text1);
                        editor.apply();
                        //enter the app
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else{
                        // there is no match on the password
                        Toast.makeText(CreatePasswordActivity.this,"password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
