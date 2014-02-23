package uf.seniorproj.alexryan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class WordProbActivity extends Activity {
	
	int curProb = 0;
	SharedPreferences sPref;
	SharedPreferences.Editor sPrefEdit;
	RadioButton rba;
	RadioButton rbb;
	RadioButton rbc;
	RadioButton rbd;

	String[] question = { "1) Two numbers add up to 7. One of the numbers is 4. What is the other number?",
			              "2) A number minus 27 is 15. What is the number?",
			              "3) What number is multiplied 8 by to get 56?",
			              "4) A number divided by 3 is 10. What is the number?",
			              "5) Sara has 15 apples and 12 oranges. How many pieces of fruit does she have?",
			              "6) Two consecutive numbers have a sum of 27. What are the numbers?",
			              "7) Half a number plus 5 is 11.What is the number?",
			              "8) The length of a rectangle is 5 more than the width. The perimeter of the rectangle is 50 meters. What are the dimentions of the rectangle?",
			              "9) Peter has three quarters, two dimes, and a penny. Mary has a quarter, six dimes, and three nickels. Who has more money and how much money does that person have?",
			              "10) Kathy is driving on the highway at 65 miles per hour. If she drives for 5 hours, how far will she have travled?"};
	
	String[] a =   {"A) 4", "A) 12", "A) 8", "A) 30", "A) 3",  "A) 10, 11", "A) 32", "A) L= 20, W= 25", "A) Mary, 100¢",  "A) 325 miles"};
	String[] b =   {"B) 2", "B) 32", "B) 7", "B) 90", "B) 24", "B) 3, 4",   "B) 12", "B) L= 25, W= 20", "B) Peter, 86¢",  "B) 60 miles"};
	String[] c =   {"C) 3", "C) 42", "C) 5", "C) 33", "C) 30", "C) 12, 15", "C) 20", "C) L= 15, W= 10", "C) Peter, 100¢", "C) 70 miles"};
	String[] d =   {"D) 7", "D) 30", "D) 6", "D) 99", "D) 27", "D) 13, 14", "D) 8",  "D) L= 10, W= 15", "D) Mary, 86¢",   "D) 13 miles"};
	String[] ans = {"C) 3", "C) 42", "B) 7", "A) 30", "D) 27", "D) 13, 14", "B) 12", "C) L= 15, W= 10", "A) Mary, 100¢",  "A) 325 miles"};
	
	String[] hint1 = {"Remember the key word add.", 
					  "Remember the key word minus.", 
					  "Remember the key word multiplied.", 
					  "Remember the key word divided.", 
					  "Let x = Total Amount of Fruit", 
					  "Let x = The First Consecutive Number\n\nLet x + 1 = The Second Consecutive Number", 
					  "Half a number means divide by 2.", 
					  "Let x = Width of Rectangle\n\nLet x + 5 = Length of Rectangle", 
					  "Remember a quarter is 25¢, a dime is 10¢, a nickel is 5¢, and a  penny is 1¢", 
					  "Distance = Rate * Time"};
	
	String[] hint2 = {"x + 4 = 7", 
					  "x - 27 = 15", 
					  "x * 8 = 56 \n\nNOTE: The symbol * means multiply.", 
					  "x / 3 = 10 \n\nNOTE: The symbol / means divide.", 
					  "15 (apples) + 12 (oranges) = x (total fruit)", 
					  "x + (x + 1) = 27", 
					  "(x/2) + 5 = 11", 
					  "2(x) + 2(x+5) = 50\n\nNOTE: 2(width) +2(length) = perimeter", 
					  "Peter has (3 * 25¢) + (2 * 10¢) + (1 * 1¢)\n\nMary has (1 * 25¢) + (6 * 10¢) + (3 * 5¢)", 
					  "Distance = 65 mph * 5 hours"};
	
	int[] idAns = {R.id.c, R.id.c, R.id.b, R.id.a, R.id.d, R.id.d, R.id.b, R.id.c, R.id.a, R.id.a};
	
	public void loadActivity(){//allow for new problems to be loaded on call
		setContentView(R.layout.activity_word_prob);
		
		TextView problem = (TextView)findViewById(R.id.wordProblem);
		problem.setText(Html.fromHtml(question[curProb]));
		
		TextView aRow = (TextView)findViewById(R.id.a);
		aRow.setText(a[curProb]);
		
		TextView bRow = (TextView)findViewById(R.id.b);
		bRow.setText(b[curProb]);
	
		TextView cRow = (TextView)findViewById(R.id.c);
		cRow.setText(c[curProb]);
		
		TextView dRow = (TextView)findViewById(R.id.d);
		dRow.setText(d[curProb]);
		
		//if problem already answered correctly
		//block all radio buttons from being clicked and set the correct answer to already clicked
		if(sPref.getString("wp"+String.valueOf(curProb), "false").equals("true")){
			
			rba = (RadioButton) findViewById(R.id.a);
			rbb = (RadioButton) findViewById(R.id.b);
			rbc = (RadioButton) findViewById(R.id.c);
			rbd = (RadioButton) findViewById(R.id.d);
			
			rba.setClickable(false);
			rbb.setClickable(false);
			rbc.setClickable(false);
			rbd.setClickable(false);
			
			switch(idAns[curProb]){
			case(R.id.a):
				rba.setChecked(true);
				rba.setTextColor(Color.argb(255, 72, 255, 0));
			break;
			case(R.id.b):
				rbb.setChecked(true);
				rbb.setTextColor(Color.argb(255, 72, 255, 0));
			break;
			case(R.id.c):
				rbc.setChecked(true);
				rbc.setTextColor(Color.argb(255, 72, 255, 0));
			break;
			case(R.id.d):
				rbd.setChecked(true);
				rbd.setTextColor(Color.argb(255, 72, 255, 0));
			break;
			}

		}	
	}
	
	
	public void checkAns(View v){//see if the answer chosen is correct
		if(!sPref.getString("wp"+String.valueOf(curProb), "false").equals("true")){
			
			rba = (RadioButton) findViewById(R.id.a);
			rbb = (RadioButton) findViewById(R.id.b);
			rbc = (RadioButton) findViewById(R.id.c);
			rbd = (RadioButton) findViewById(R.id.d);
			
		    switch (v.getId()) {
		    case (R.id.a):
		        if(a[curProb].equals(ans[curProb])){
		        	ansCorrect();
		        	rba.setTextColor(Color.argb(255, 72, 255, 0));
		        }
		        else
		        	ansWrong();
		    break;
		    case (R.id.b):
		    	if(b[curProb].equals(ans[curProb])){
		    		ansCorrect();
		    		rbb.setTextColor(Color.argb(255, 72, 255, 0));
		    	}
		        else
		        	ansWrong();
		    break;
		    case (R.id.c):
		    	if(c[curProb].equals(ans[curProb])){
		    		ansCorrect();
		    		rbc.setTextColor(Color.argb(255, 72, 255, 0));
		    	}
		        else
		        	ansWrong();
		    break;
		    case (R.id.d):
		    	if(d[curProb].equals(ans[curProb])){
		    		ansCorrect();
		    		rbd.setTextColor(Color.argb(255, 72, 255, 0));
		    	}
		        else
		        	ansWrong();
		    break;
		    }
		}
	}
	
	public void ansCorrect(){
		//store problem as answered
		sPrefEdit.putString("wp"+String.valueOf(curProb), "true");
		sPrefEdit.commit();
		
		rba = (RadioButton) findViewById(R.id.a);
		rbb = (RadioButton) findViewById(R.id.b);
		rbc = (RadioButton) findViewById(R.id.c);
		rbd = (RadioButton) findViewById(R.id.d);
		
		rba.setClickable(false);
		rbb.setClickable(false);
		rbc.setClickable(false);
		rbd.setClickable(false);
		
		Toast.makeText(this, "Correct! Well done.", Toast.LENGTH_SHORT).show();
	}
	
	public void ansWrong(){//do this when wrong
		Toast.makeText(this, "Oops. Try again!", Toast.LENGTH_SHORT).show();
	}
	
	public void nextProb(View v){//move to next problem
		if(curProb == 9)
			Toast.makeText(this, "No more problems remain.", Toast.LENGTH_SHORT).show();
		else{
			curProb++;
			loadActivity();
		}
	}
	
	public void prevProb(View v){//move to previous problem
		if(curProb == 0)
			Toast.makeText(this, "Cannot go back any further.", Toast.LENGTH_SHORT).show();
		else{
			curProb--;
			loadActivity();
		}
	}
	
	public void hint1(final View v){
    	new AlertDialog.Builder(this)
        .setTitle("Hint 1")
        .setMessage(hint1[curProb])
        .setPositiveButton("Next Hint", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	hint2(v);
            }
         })
        .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // do nothing
            }
         })
//        .setIcon(R.drawable.x)
         .show();
	}
	
	public void hint2(final View v){
		new AlertDialog.Builder(this)
        .setTitle("Hint 2")
        .setMessage(hint2[curProb])
        .setPositiveButton("Prev Hint", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	hint1(v);
            }
         })
        .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // do nothing
            }
         })
//        .setIcon(R.drawable.x)
         .show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		curProb = extras.getInt("probNum");//set the problem that was selected
		sPref = getSharedPreferences("shared_preferences", MODE_PRIVATE);
		sPrefEdit = sPref.edit();
		loadActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.word_prob, menu);
		return true;
	}

}
