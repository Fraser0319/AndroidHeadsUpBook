package com.example.fraserbeaton.headsupchapter6starbuzzapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        int drinkNo = (Integer) getIntent().getExtras().getInt(EXTRA_DRINKNO);



        // create the cursor
        try{
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                                        new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                                        "_id = ?",
                                        new String [] {Integer.toString(drinkNo)},
                                        null,null,null);

            if(cursor.moveToFirst()){
                // get the drink details from the the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoid = cursor.getInt(2);

                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoid);
                photo.setContentDescription(nameText);
            }

            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
