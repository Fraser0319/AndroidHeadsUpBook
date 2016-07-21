package com.example.fraserbeaton.headsupchapter6starbuzzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.SimpleCursorAdapter;


public class DrinkCategoryActivity extends ListActivity {

    private  SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listDrinks = getListView();

        try {
            SQLiteOpenHelper starbuzzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzzDatabaseHelper.getReadableDatabase();

            cursor = db.query("DRINK",
                                new String [] {"_id", "NAME"},
                                null,null,null,null,null);

            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                                            android.R.layout.simple_expandable_list_item_1,
                                            cursor,
                                            new String [] {"NAME"},
                                            new int [] {android.R.id.text1},
                                            0);

            listDrinks.setAdapter(listAdapter);
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(DrinkCategoryActivity.this,DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINKNO,(int) id);
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
