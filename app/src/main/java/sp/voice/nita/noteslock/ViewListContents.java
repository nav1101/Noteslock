package sp.voice.nita.noteslock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.Toast;
import android.widget.ListAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class ViewListContents extends Activity{

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        ListView listView = (ListView) findViewById(R.id.ListView);
        myDB = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();

        //ArrayAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);

        //listView.setAdapter(listAdapter);

       if (data.getCount() == 0) {
            Toast.makeText(ViewListContents.this, "Database was empty :( ", Toast.LENGTH_LONG).show();

        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(1));
                // ADD THIS LINE TO REVERSE ORDER!
                //listView.notifyDataSetChanged;
                 ArrayAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);

                listView.setAdapter(listAdapter);


            }
        }

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ViewListContents.this, "Note Selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            // setting onItemLongClickListener and passing the position to the function
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View arg1,
                                           int position, long arg3) {
                String itemontouch = String.valueOf(parent.getItemAtPosition(position));
                removeItemFromList(itemontouch);
                //listAdapter.notifyDataSetChanged();
                //ArrayAdapter lg =  listAdapter.getAdapter();
                //((ArrayAdapter) listAdapter.getAdapter()).notifyDataSetChanged();

                return true;
            }
        });
    }

        // method to remove list item

    protected void removeItemFromList(String itemontouch) {
        final String deletePosition = itemontouch;

        AlertDialog.Builder alert = new AlertDialog.Builder(
                ViewListContents.this);

        alert.setTitle("Delete");
        alert.setMessage("Do you want delete this item?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Deleteraw(deletePosition);

            }
        });
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });

        alert.show();

        }

    private void Deleteraw(String position) {
        myDB.Deletedata(position);

        Toast.makeText(ViewListContents.this, "Deleted?", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());

    }
}







