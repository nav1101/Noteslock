package sp.voice.nita.noteslock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Context;



public class MainActivity extends Activity {

    Context context;

    ImageButton btnAdd,btnView;
    DatabaseHelper myDB = new DatabaseHelper(this);
    //Button btnView;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnView = (ImageButton) findViewById(R.id.btnView);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewListContents.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(newEntry.length()!=0) {
                    AddData(newEntry);
                    editText.setText("");
                    Toast.makeText(MainActivity.this, newEntry, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"PUT SOMETHING IN THE TEXT FIELD",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void AddData(String newEntry){
        boolean insertData = myDB.addData(newEntry);

        if(insertData == true){
            Toast.makeText(MainActivity.this," successed " ,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), ViewListContents.class);
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this," somthing went wrong " ,Toast.LENGTH_LONG).show();
        }
    }

}
