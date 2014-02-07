package uf.seniorproj.alexryan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WordProbActivity extends Activity {
	
	int curProb = 0;

	String[] question = { "1) A passenger plane made a trip to Las Vegas and back. On the trip there it flew 432 mph and on the return trip it went 480 mph. How long did the trip there take if the return trip took nine hours?",
			              "2) If 4 apples and 2 oranges equals $1 and 2 apples and 3 orange equals $0.70, how much does each apple and each orange cost? ",
			              "3) The sum of two numbers is 16. The difference is 4. What are the two numbers?",
			              "4) The area of a rectangle is 24 cm2. The width is two less than the length. What is the length and width of the rectangle? ",
			              "5) Twenty-five years ago, Hailey was five more than one-third as old as Jason was. Today, Jason is twenty-six less than two times the age of Hailey. How old is Jason?",
			              "6) Austin has nickels, dimes and quarters. He has a total of $17.65. He has eleven fewer quarters than nickels and six times as many dimes as quarters. How many of each coin does he have?",
			              "7) What are two consecutive integers, such that seven times the larger minus three times the smaller is 95?",
			              "8) The ratio of votes for Brian to votes for Jose in an election is 13:5. There were a total of 1,530 votes. How many people voted for Jose?",
			              "9) A small town has averaged 1.7 inches of rain per month from January to April. What must be the average monthly rainfall from May to December so that the average rainfall for the entire year will be approximately 1.43 inches per month?",
			              "10) A baseball team won three more than three times the number of games lost. If the team played fifty-one games, how many games did the team lose?"};
	
	String[] a = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10"};
	String[] b = {"b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "b10"};
	String[] c = {"c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10"};
	String[] d = {"d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10"};
	String[] ans = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10"};
	
	public void loadActivity(){//allow for new problems to be loaded on call
		setContentView(R.layout.activity_word_prob);
		
		TextView problem = (TextView)findViewById(R.id.wordProblem);
		problem.setText(question[curProb]);
		
		TextView aRow = (TextView)findViewById(R.id.a);
		aRow.setText(a[curProb]);
		
		TextView bRow = (TextView)findViewById(R.id.b);
		bRow.setText(b[curProb]);
	
		TextView cRow = (TextView)findViewById(R.id.c);
		cRow.setText(c[curProb]);
		
		TextView dRow = (TextView)findViewById(R.id.d);
		dRow.setText(d[curProb]);
	}
	
	
	public void checkAns(View v){//see if the answer chosen is correct
	    switch (v.getId()) {
	    case (R.id.a):
	        if(a[curProb].equals(ans[curProb]))
	        	ansCorrect();
	        else
	        	ansWrong();
	    break;
	    case (R.id.b):
	    	if(b[curProb].equals(ans[curProb]))
	        	ansCorrect();
	        else
	        	ansWrong();
	    break;
	    case (R.id.c):
	    	if(c[curProb].equals(ans[curProb]))
	        	ansCorrect();
	        else
	        	ansWrong();
	    break;
	    case (R.id.d):
	    	if(d[curProb].equals(ans[curProb]))
	        	ansCorrect();
	        else
	        	ansWrong();
	    break;
	    }
	}
	
	public void ansCorrect(){//do this when correct
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
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		curProb = extras.getInt("probNum");//set the problem that was selected
		loadActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.word_prob, menu);
		return true;
	}

}
