package uf.seniorproj.alexryan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void startFoilActivity(View v) {
    	Intent intent = new Intent(this, FoilActivity.class);
    	startActivity(intent);
    }
    

    public void startWordProbActivity(View v) {
    	Intent intent = new Intent(this, WordProbActivity.class);
    	startActivity(intent);
    }

    public void startSolveForX(View v) {
    	Intent intent = new Intent(this, SolveForX.class);
    	startActivity(intent);
    }
    

}
