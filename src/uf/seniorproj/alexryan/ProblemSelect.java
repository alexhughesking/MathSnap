package uf.seniorproj.alexryan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ProblemSelect extends Activity {
	
	int id;//the id of the activity which was clicked in main
	//2-9-2 or 4-5-4 for the layout

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_problem_select);
		Bundle extras = getIntent().getExtras();
		id = extras.getInt("activityID");//get the id they clicked on
		
		
		TextView currActivity = (TextView)findViewById(R.id.probSelectText);//the label at the top
		String colorString = "#FFF";//to set background color
		switch (id) {
		case R.id.mainFOIL: 
			currActivity.setText("F.O.I.L");
			colorString = "#FF6B6B";//this value should match the one in main.xml
			break;
		case R.id.mainWordProblems: 
			currActivity.setText("Word Problems");
			colorString = "#C7F464";
			break;
		case R.id.mainSolveForX: 
			currActivity.setText("Solve for x");
			colorString = "#C44D58";
			break;
		}
		
		currActivity.setBackgroundColor(Color.parseColor(colorString));
		
		findViewById(R.id.selectProb1).setBackgroundColor(Color.parseColor(colorString));
		findViewById(R.id.selectProb2).setBackgroundColor(Color.parseColor(colorString));
		findViewById(R.id.selectProb3).setBackgroundColor(Color.parseColor(colorString));
		findViewById(R.id.selectProb4).setBackgroundColor(Color.parseColor(colorString));
		findViewById(R.id.selectProb5).setBackgroundColor(Color.parseColor(colorString));
		findViewById(R.id.selectProb6).setBackgroundColor(Color.parseColor(colorString));
		findViewById(R.id.selectProb7).setBackgroundColor(Color.parseColor(colorString));
		findViewById(R.id.selectProb8).setBackgroundColor(Color.parseColor(colorString));
		findViewById(R.id.selectProb9).setBackgroundColor(Color.parseColor(colorString));
		findViewById(R.id.selectProb10).setBackgroundColor(Color.parseColor(colorString));
		
		
		
		
	}
	
	public void startProblem(View v) {
		int probNum = 0; //the problem num to be passed
		Intent intent;
		switch (v.getId()) {
		case R.id.selectProb1: probNum = 0; break;
		case R.id.selectProb2: probNum = 1; break;
		case R.id.selectProb3: probNum = 2; break;
		case R.id.selectProb4: probNum = 3; break;
		case R.id.selectProb5: probNum = 4; break;
		case R.id.selectProb6: probNum = 5; break;
		case R.id.selectProb7: probNum = 6; break;
		case R.id.selectProb8: probNum = 7; break;
		case R.id.selectProb9: probNum = 8; break;
		case R.id.selectProb10: probNum = 9; break;
		}
		
		if (id == R.id.mainFOIL) {
			intent = new Intent(this, FoilActivity.class);
			intent.putExtra("probNum", probNum);
			startActivity(intent);
		}
		else if (id == R.id.mainWordProblems) {
			intent = new Intent(this, WordProbActivity.class);
			intent.putExtra("probNum", probNum);	
			startActivity(intent);
		}
		else if (id == R.id.mainSolveForX) {
			intent = new Intent(this, SolveForX.class);
			intent.putExtra("probNum", probNum);	
			startActivity(intent);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.problem_select, menu);
		return true;
	}

}
