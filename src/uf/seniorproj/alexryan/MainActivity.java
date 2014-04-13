package uf.seniorproj.alexryan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	SharedPreferences sPref;
	SharedPreferences.Editor sPrefEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sPref = getSharedPreferences("shared_preferences", MODE_PRIVATE);
		sPrefEdit = sPref.edit();
        checkWP();
        checkFoil();
        checkSolveX();
        checkGraph();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void startProblemSelect(View v) {
    	Intent intent = new Intent(this, ProblemSelect.class);
    	intent.putExtra("activityID", v.getId());
    	startActivity(intent);
    }
    
    public void clearProgress(View v){
    	new AlertDialog.Builder(this)
        .setTitle("Clear All Progress")
        .setMessage("Are you sure you want to clear all of your current progress?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	sPrefEdit.clear();
            	sPrefEdit.commit();
            	onRestart();
            }
         })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // do nothing
            }
         })
//        .setIcon(R.drawable.x)
         .show();
    }
    
    public void checkWP (){
    	int pCount=0;
    	for(int i=0; i<=10; i++){
    		if(sPref.getString("wp"+String.valueOf(i), "false").equals("true"))
    			pCount++;
    	}
    	ImageView progress = (ImageView) findViewById(R.id.wpProgress);
    	switch(pCount){
    		case(0):
    			progress.setImageResource(R.drawable.progress0); break;
    		case(1):
    			progress.setImageResource(R.drawable.progress1); break;
    		case(2):
    			progress.setImageResource(R.drawable.progress2); break;
    		case(3):
    			progress.setImageResource(R.drawable.progress3); break;
    		case(4):
    			progress.setImageResource(R.drawable.progress4); break;
    		case(5):
    			progress.setImageResource(R.drawable.progress5); break;
    		case(6):
    			progress.setImageResource(R.drawable.progress6); break;
    		case(7):
    			progress.setImageResource(R.drawable.progress7); break;
    		case(8):
    			progress.setImageResource(R.drawable.progress8); break;
    		case(9):
    			progress.setImageResource(R.drawable.progress9); break;
    		case(10):
    			progress.setImageResource(R.drawable.progress10); break;
    	}
    }
    
    public void checkFoil(){
    	int pCount=0;
    	for(int i=0; i<=10; i++){
    		if(sPref.getString("foil"+String.valueOf(i), "false").equals("true"))
    			pCount++;
    	}
    	ImageView progress = (ImageView) findViewById(R.id.foilProgress);
    	switch(pCount){
    		case(0):
    			progress.setImageResource(R.drawable.progress0); break;
    		case(1):
    			progress.setImageResource(R.drawable.progress1); break;
    		case(2):
    			progress.setImageResource(R.drawable.progress2); break;
    		case(3):
    			progress.setImageResource(R.drawable.progress3); break;
    		case(4):
    			progress.setImageResource(R.drawable.progress4); break;
    		case(5):
    			progress.setImageResource(R.drawable.progress5); break;
    		case(6):
    			progress.setImageResource(R.drawable.progress6); break;
    		case(7):
    			progress.setImageResource(R.drawable.progress7); break;
    		case(8):
    			progress.setImageResource(R.drawable.progress8); break;
    		case(9):
    			progress.setImageResource(R.drawable.progress9); break;
    		case(10):
    			progress.setImageResource(R.drawable.progress10); break;
    	}
    }
    
    public void checkSolveX() {
    	int pCount=0;
    	for(int i=0; i<=10; i++){
    		if(sPref.getString("solveX"+String.valueOf(i), "false").equals("true"))
    			pCount++;
    	}
    	ImageView progress = (ImageView) findViewById(R.id.s4xProgress);
    	switch(pCount){
    		case(0):
    			progress.setImageResource(R.drawable.progress0); break;
    		case(1):
    			progress.setImageResource(R.drawable.progress1); break;
    		case(2):
    			progress.setImageResource(R.drawable.progress2); break;
    		case(3):
    			progress.setImageResource(R.drawable.progress3); break;
    		case(4):
    			progress.setImageResource(R.drawable.progress4); break;
    		case(5):
    			progress.setImageResource(R.drawable.progress5); break;
    		case(6):
    			progress.setImageResource(R.drawable.progress6); break;
    		case(7):
    			progress.setImageResource(R.drawable.progress7); break;
    		case(8):
    			progress.setImageResource(R.drawable.progress8); break;
    		case(9):
    			progress.setImageResource(R.drawable.progress9); break;
    		case(10):
    			progress.setImageResource(R.drawable.progress10); break;
    	}
    }
    
    public void checkGraph(){
    	int pCount=0;
    	for(int i=0; i<=10; i++){
    		if(sPref.getString("graph"+String.valueOf(i), "false").equals("true"))
    			pCount++;
    	}
    	ImageView progress = (ImageView) findViewById(R.id.graphProgress);
    	switch(pCount){
    		case(0):
    			progress.setImageResource(R.drawable.progress0); break;
    		case(1):
    			progress.setImageResource(R.drawable.progress1); break;
    		case(2):
    			progress.setImageResource(R.drawable.progress2); break;
    		case(3):
    			progress.setImageResource(R.drawable.progress3); break;
    		case(4):
    			progress.setImageResource(R.drawable.progress4); break;
    		case(5):
    			progress.setImageResource(R.drawable.progress5); break;
    		case(6):
    			progress.setImageResource(R.drawable.progress6); break;
    		case(7):
    			progress.setImageResource(R.drawable.progress7); break;
    		case(8):
    			progress.setImageResource(R.drawable.progress8); break;
    		case(9):
    			progress.setImageResource(R.drawable.progress9); break;
    		case(10):
    			progress.setImageResource(R.drawable.progress10); break;
    	}
    }
    
    protected void onRestart() {
    	super.onRestart();
    	checkWP();
    	checkFoil();
    	checkSolveX();
    	checkGraph();
    }

}
