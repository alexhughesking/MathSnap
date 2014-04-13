package uf.seniorproj.alexryan;

import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PFont;
import android.content.SharedPreferences;
import android.os.Bundle;

//s4 screen size is 1080 x 1920

public class GraphActivity extends PApplet {
	
	SharedPreferences sPref;
	SharedPreferences.Editor sPrefEdit;
	
	int[] xpos = new int[11];
	int[] ypos = new int[11];
	int plotX;
	int plotY;
	PFont regFont;
	PFont boldFont;
	
	int curProb;
	int diff;
	int scale;
	boolean check;
	boolean animate;
	int index;
	
	String[] equations = {"y = x + 1", "y = x + 1",
			             "y = 2x",     "y = 2x",
			             "y = 3",      "y = 3",
			             "y = 3x - 2", "y = 3x - 2",
			             "y = -x + 2", "y = -x + 2"}; 
	
	String[] problems = {"1) Tap y-intercept", "2) Graph the line",
			             "3) Tap y-intercept", "4) Graph the line",
			             "5) Tap y-intercept", "6) Graph the line",
			             "7) Tap y-intercept", "8) Graph the line",
			             "9) Tap y-intercept", "10) Graph the line"};
	
	
	ArrayList<Point> ans0 = new ArrayList<Point>();
	ArrayList<Point> ans1 = new ArrayList<Point>();
	ArrayList<Point> ans2 = new ArrayList<Point>();
	ArrayList<Point> ans3 = new ArrayList<Point>();
	ArrayList<Point> ans4 = new ArrayList<Point>();
	ArrayList<Point> ans5 = new ArrayList<Point>();
	ArrayList<Point> ans6 = new ArrayList<Point>();
	ArrayList<Point> ans7 = new ArrayList<Point>();
	ArrayList<Point> ans8 = new ArrayList<Point>();
	ArrayList<Point> ans9 = new ArrayList<Point>();
	
	ArrayList<ArrayList> answers = new ArrayList<ArrayList>();
	
	ArrayList<Point> input = new ArrayList<Point>();
	
	public void setup(){//make dynamic!!!
		sPref = getSharedPreferences("shared_preferences", MODE_PRIVATE);
		sPrefEdit = sPref.edit();
		
		Bundle extras = getIntent().getExtras();
		curProb = extras.getInt("probNum");//set the problem that was selected
		
		
		rectMode(CENTER);
		textAlign(CENTER, CENTER);
		//fonts not working :(
		regFont = createFont("Arial", (float)(displayHeight/22.5));//make 48 dynamic!!
		boldFont = createFont("Droid Sans Bold", (float)(displayHeight/22.5));//make 48 dynamic!!!
		background(255,255,224);
		diff = displayWidth - displayHeight;
		scale = (displayHeight/10);
		animate = false;
		index = 0;
		makeGraph();
		loadAnswers();
		displayProblem();
		makeButtons();
	}


	public void draw() {
		frameRate(4);
		if(animate && curProb % 2 == 1){
			if (index >= answers.get(curProb).size() - 1)
				index = 0;
			
			strokeWeight(10);
			stroke(0,0,200);
			line(((Point) answers.get(curProb).get(index)).getX(), ((Point) answers.get(curProb).get(index)).getY(),
			     ((Point) answers.get(curProb).get(index+1)).getX(), ((Point) answers.get(curProb).get(index+1)).getY());
			
			index++;
		}
	}
	
	public void loadAnswers(){
		ans0.add(new Point(5*scale+(diff/2), 4*scale));
		
		ans1.addAll(Arrays.asList(new Point(0*scale+(diff/2), 9*scale), new Point(1*scale+(diff/2), 8*scale), 
								  new Point(2*scale+(diff/2), 7*scale), new Point(3*scale+(diff/2), 6*scale), 
								  new Point(4*scale+(diff/2), 5*scale), new Point(5*scale+(diff/2), 4*scale), 
								  new Point(6*scale+(diff/2), 3*scale), new Point(7*scale+(diff/2), 2*scale), 
								  new Point(8*scale+(diff/2), 1*scale), new Point(9*scale+(diff/2), 0*scale)));
		
		ans2.add(new Point(5*scale+(diff/2), 5*scale));
		
		ans3.addAll(Arrays.asList(new Point(2*scale+(diff/2), 11*scale),
								  new Point(3*scale+(diff/2), 9*scale), new Point(4*scale+(diff/2), 7*scale), 
								  new Point(5*scale+(diff/2), 5*scale), new Point(6*scale+(diff/2), 3*scale), 
								  new Point(7*scale+(diff/2), 1*scale),
								  new Point(9*scale+(diff/2), -3*scale)));
		
		ans4.add(new Point(5*scale+(diff/2), 2*scale));
		
		ans5.addAll(Arrays.asList(new Point(0*scale+(diff/2), 2*scale), new Point(1*scale+(diff/2), 2*scale), 
								  new Point(2*scale+(diff/2), 2*scale), new Point(3*scale+(diff/2), 2*scale), 
								  new Point(4*scale+(diff/2), 2*scale), new Point(5*scale+(diff/2), 2*scale), 
								  new Point(6*scale+(diff/2), 2*scale), new Point(7*scale+(diff/2), 2*scale), 
								  new Point(8*scale+(diff/2), 2*scale), new Point(9*scale+(diff/2), 2*scale), 
								  new Point(10*scale+(diff/2), 2*scale)));   
		
		ans6.add(new Point(5*scale+(diff/2), 7*scale));
		
		ans7.addAll(Arrays.asList(new Point(4*scale+(diff/2), 10*scale), new Point(5*scale+(diff/2), 7*scale), 
								  new Point(6*scale+(diff/2), 4*scale), new Point(7*scale+(diff/2), 1*scale),
								  new Point(8*scale+(diff/2), -2*scale)));
		
		ans8.add(new Point(5*scale+(diff/2), 3*scale));
		
		ans9.addAll(Arrays.asList(new Point(2*scale+(diff/2), 0*scale), new Point(3*scale+(diff/2), 1*scale), 
								  new Point(4*scale+(diff/2), 2*scale), new Point(5*scale+(diff/2), 3*scale), 
								  new Point(6*scale+(diff/2), 4*scale), new Point(7*scale+(diff/2), 5*scale), 
								  new Point(8*scale+(diff/2), 6*scale), new Point(9*scale+(diff/2), 7*scale), 
								  new Point(10*scale+(diff/2), 8*scale)));
		
		answers.add(ans0); answers.add(ans1); answers.add(ans2); answers.add(ans3); answers.add(ans4);
		answers.add(ans5); answers.add(ans6); answers.add(ans7); answers.add(ans8); answers.add(ans9);
	}
	
	public boolean checkInput(){
		//not enough points submitted
		if((curProb % 2 == 0 && input.size() <= 0) || (curProb % 2 == 1 && input.size() <= 2))
			return false;
		//if enough points submitted, check to see if points are correct
		for(int i = 0; i < input.size(); i++){
			check = false;
			for (int j = 0; j < answers.get(curProb).size(); j++){
				if(((Point) input.get(i)).getX().equals(((Point) answers.get(curProb).get(j)).getX()) &&
				   ((Point) input.get(i)).getY().equals(((Point) answers.get(curProb).get(j)).getY()))   {
					
					check = true;
					break;
				}
			}
			if(!check)
				return false;
		}
		animate = true;
		index = 0;
		return true;
	}
	
	public void makeGraph(){// assuming forced landscape mode
		strokeWeight(1);
		stroke(0);
		fill(0);
		
		for (int i=0; i<=10; i++){
			//vertical lines
			line(i*scale+(diff/2),0,i*scale+(diff/2),displayHeight);
			//horizontal lines
			line(diff/2,i*scale,displayWidth-(diff/2),i*scale);
			
			xpos[i] = i*scale+(diff/2);
			ypos[i] = i*scale;
		
		}		
		
		strokeWeight(5);
		//y-axis
		line(displayWidth/2, 0, displayWidth/2, displayHeight);
		//x-axis
		line(diff/2, displayHeight/2, displayWidth-(diff/2), displayHeight/2);
	}
	
	public void displayProblem(){
		fill(0);
		textFont(boldFont);
		text("Current Equation", (float)(displayWidth/9.3658), (float)(displayHeight/24));
		text("Current Problem", displayWidth - (float)(displayWidth/9.3658),(float)(displayHeight/24));
		textFont(regFont);
		text(equations[curProb], (float)(displayWidth/9.3658), (float)(displayHeight/9.3913));
		text(problems[curProb], displayWidth - (float)(displayWidth/9.3658), (float)(displayHeight/9.3913));	
		
		if(curProb % 2 == 1)
			text("  Plot 3 points", displayWidth - (float)(displayWidth/9.3658), (float)(displayHeight/5.8378));
		
		if(sPref.getString("graph"+String.valueOf(curProb), "false").equals("true")){
			fill(0,0,200);
			noStroke();
			for(int i = 0; i < answers.get(curProb).size(); i++){
				ellipse(((Point) answers.get(curProb).get(i)).getX(), ((Point) answers.get(curProb).get(i)).getY(), 
						displayHeight/25, displayHeight/25);
			}
			strokeWeight(10);
			stroke(0,0,200);
			line(((Point) answers.get(curProb).get(0)).getX(), ((Point) answers.get(curProb).get(0)).getY(),
				 ((Point) answers.get(curProb).get(answers.get(curProb).size()-1)).getX(),
				 ((Point) answers.get(curProb).get(answers.get(curProb).size()-1)).getY());
			
			fill(0,200,0);
			text("Correct!", (float)(displayWidth/9.3658), (float)(displayHeight/5.8378)); 
		}
	}
	
	public void makeButtons(){
		noStroke();
		fill(211,211,211);
		
		//prev background
		rect((float)(displayWidth/9.1428),(float)(displayHeight-displayHeight/9.8181),
			 (float)(displayWidth/5.4857),(float)(displayHeight/7.2));
		//next background
		rect((float)(displayWidth-displayWidth/9.1428),(float)(displayHeight-displayHeight/9.8181),
			 (float)(displayWidth/5.4857),(float)(displayHeight/7.2));
		//submit background
		rect((float)(displayWidth-displayWidth/9.1428), (float)(displayHeight/3.0857),
			 (float)(displayWidth/5.4857),(float)(displayHeight/7.2));
		//clear button
		rect((float)(displayWidth/9.1428),(float)(displayHeight/3.0857),(float)(displayWidth/5.4857),(float)(displayHeight/7.2)); 
		
		fill(0);
		text("Prev", (float)(displayWidth/9.1428),(float)(displayHeight-displayHeight/9.8181));
		text("Next", (float)(displayWidth-displayWidth/9.1428),(float)(displayHeight-displayHeight/9.8181));
		text("Submit", (float)(displayWidth-displayWidth/9.1428), (float)(displayHeight/3.0857));
		text("Clear", (float)(displayWidth/9.1428),(float)(displayHeight/3.0857));
	}
	
	public void mousePressed() {
		  plotX = xpos[0];
		  plotY = ypos[0];
		  fill(0,0,200);
		  noStroke();
		  
		  //if user taps on the graph
		  if(mouseX >= (diff/2)-(displayHeight/25) && mouseX <= (displayWidth-(diff/2))+(displayHeight/25)){
			  if(sPref.getString("graph"+String.valueOf(curProb), "false").equals("false")){
				  for(int x=0; x<=10; x++){
					  for(int y=0; y<=10; y++){
						  if(dist(xpos[x], ypos[y], mouseX, mouseY) < dist(plotX, plotY, mouseX, mouseY)){
							  plotX = xpos[x];
							  plotY = ypos[y];
						  }
					  }
				  }
				  input.add(new Point(plotX, plotY));
				  ellipse(plotX, plotY, displayHeight/25, displayHeight/25);
			  }
		  }
		  
		  if(mouseY <= displayHeight - displayHeight/30.8571 && mouseY >= displayHeight - displayHeight/5.8378){
			  //over prev button
			  if(mouseX >= displayWidth/54.8571 && mouseX <= displayWidth/4.9870){
				  if(curProb != 0){
					  animate = false;
					  curProb--;
					  fill(255,255,224);
					  rect(displayWidth/2, displayHeight/2, displayWidth, displayHeight);
					  input.clear();
					  makeGraph();
					  makeButtons();
					  displayProblem();
				  }
			  }
			  //over next button
			  if(mouseX >= displayWidth - displayWidth/4.9870 && mouseX <= displayWidth - displayWidth/54.8571){
				  if(curProb != 9){
					  animate = false;
					  curProb++;
					  fill(255,255,224);
					  rect(displayWidth/2, displayHeight/2, displayWidth, displayHeight);
					  input.clear();
					  makeGraph();
					  makeButtons();
					  displayProblem();
				  }
			  }
		  }
		  
		  //over submit button
		  if(mouseX >= displayWidth - displayWidth/4.9870 && mouseX <= displayWidth - displayWidth/54.857 && 
			 mouseY >= displayHeight/3.9272 &&  mouseY <= displayHeight/2.5411){
			  if(sPref.getString("graph"+String.valueOf(curProb), "false").equals("false")){
				  if(checkInput()) {
					  sPrefEdit.putString("graph"+String.valueOf(curProb), "true");
					  sPrefEdit.commit();
					  fill(255,255,224);
					  rect((float)(displayWidth/9.3658),(float)(displayHeight/5.8378),
						   (float)(displayWidth/9.3658),(float)(displayHeight/18));
					  fill(0,200,0);
					  text("Correct!", (float)(displayWidth/9.3658), (float)(displayHeight/5.8378)); 
				  }
				  else{
					  fill(200,0,0);
					  text("Try Again", (float)(displayWidth/9.3658), (float)(displayHeight/5.8378)); 
				  }
			  }
		  }
		  
		  //over clear button
		  if(mouseX >= displayWidth/54.857 && mouseX <= displayWidth/4.9870 && 
			 mouseY >= displayHeight/3.9272 &&  mouseY <= displayHeight/2.5411){
			  if(sPref.getString("graph"+String.valueOf(curProb), "false").equals("false")){
				  fill(255,255,224);
				  rect(displayWidth/2, displayHeight/2, displayHeight+displayHeight/25, displayHeight);
				  makeGraph();
				  input.clear();
			  }
		  }
	}

//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_graph);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.graph, menu);
//		return true;
//	}

}
