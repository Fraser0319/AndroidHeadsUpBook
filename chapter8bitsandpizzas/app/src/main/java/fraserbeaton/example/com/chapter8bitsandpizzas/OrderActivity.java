package fraserbeaton.example.com.chapter8bitsandpizzas;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;

public class OrderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

}
