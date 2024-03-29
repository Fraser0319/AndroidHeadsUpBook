package com.example.fraserbeaton.headsupchapter6starbuzzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import android.widget.CursorAdapter;
import android.widget.Toast;


public class TopLevelActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        //create onItemClickListener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };
        //add the listener to the list view
        ListView listview = (ListView) findViewById(R.id.list_options);
        listview.setOnItemClickListener(itemClickListener);

        // populate the list_favourites list view from a cursor
        ListView listFavorites = (ListView) findViewById(R.id.list_favorites);
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            favoritesCursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);

            CursorAdapter favouriteAdaptor = new SimpleCursorAdapter(TopLevelActivity.this,
                    android.R.layout.simple_expandable_list_item_1,
                    favoritesCursor,
                    new String []{"NAME"},
                    new int []{android.R.id.text1}, 0);
            listFavorites.setAdapter(favouriteAdaptor);
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        //navigate to drink activity if drink is clicked
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id)
            {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKNO, (int)id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRestart(){
        super.onRestart();
        try{
            StarbuzzDatabaseHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor newCursor = db.query("DRINK",
                    new String []{"_id","NAME"},
                    "FAVORITE = 1",
                    null,null,null,null);
            ListView listFavorites = (ListView)findViewById(R.id.list_favorites);
            CursorAdapter adapter = (CursorAdapter)listFavorites.getAdapter();
            adapter.changeCursor(newCursor);
            favoritesCursor = newCursor;
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"Database unavailable", Toast.LENGTH_SHORT);
            toast.show();






        }
    }


    //Close the cursor and database in the onDestroy() method
    @Override
    public void onDestroy(){
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }

    

}
