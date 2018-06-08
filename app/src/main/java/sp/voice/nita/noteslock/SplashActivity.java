package sp.voice.nita.noteslock;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.content.Intent;
import android.widget.TextView;


public class SplashActivity extends Activity {

    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView tx = (TextView)findViewById(R.id.textview1);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/youngones_RS-Regular.ttf");

        tx.setTypeface(custom_font);
        //load the password
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password","");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //if there is no password
                if(password.equals("")) {
                    Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    //if there is no password
                    Intent intent = new Intent(getApplicationContext(), EnterPasswordActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);
    }

}
