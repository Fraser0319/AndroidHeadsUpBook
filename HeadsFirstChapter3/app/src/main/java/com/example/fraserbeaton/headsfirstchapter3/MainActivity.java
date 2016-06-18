package com.example.fraserbeaton.headsfirstchapter3;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSendMessage(View view){
        EditText messageView = (EditText)findViewById(R.id.message);
        String message = messageView.getText().toString();
//        Intent intent = new Intent(this,OtherActivity.class);
//        intent.putExtra(OtherActivity.EXTRA_MESSAGE,message);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        String chooserTitle = getString(R.string.chooser);
        Intent chooseIntent = Intent.createChooser(intent,chooserTitle);
        startActivity(chooseIntent);
    }
}
