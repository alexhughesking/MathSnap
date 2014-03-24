package uf.seniorproj.alexryan;

import processing.core.PApplet;
import processing.core.PFont;
import android.os.Bundle;

public class GraphActivity extends PApplet {
	
	int diff;
	int[] xpos = new int[11];
	int[] ypos = new int[11];
	int plotX;
	int plotY;
	PFont regFont;
	PFont boldFont;
	
	int curProb;
	
	String[] equations = {"y = x + 1", "y = x + 1",
			             "y = 2x", "y = 2x",
			             "y = 3", "y = 3",
			             "y = 3x - 2", "y = 3x - 2",
			             "y = -x + 2", "y = -x +2"}; 
	
	String[] problems = {"Tap the y-intercept", "Grap the line",
			             "Tap the y-intercept", "Grap the line",
			             "Tap the y-intercept", "Grap the line",
			             "Tap the y-intercept", "Grap the line",
			             "Tap the y-intercept", "Grap the line"};
	
	
	public void setup(){
		Bundle extras = getIntent().getExtras();
		curProb = extras.getInt("probNum");//set the problem that was selected
		rectMode(CENTER);
		textAlign(CENTER, CENTER);
		regFont = createFont("Arial", 48);//make 48 dynamic!!
		boldFont = createFont("Droid Sans Bold", 48);//make 48 dynamic!!!
		background(255,255,224);
		makeGraph();
		displayProblem();
		makeNextPrevButtons();
	}


	public void draw() {}
	
	public void makeGraph(){// assuming forced landscape mode
		diff = displayWidth - displayHeight;
			for (int i =0; i<=10; i++){
				//vertical lines
				line(i*(displayHeight/10)+(diff/2),0,i*(displayHeight/10)+(diff/2),displayHeight);
				//horizontal lines
				line(diff/2,i*(displayHeight/10),displayWidth-(diff/2),i*(displayHeight/10));
				
				xpos[i] = i*(displayHeight/10)+(diff/2);
				ypos[i] = i*(displayHeight/10);
			
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
		text("Current Equation", 205, 45);
		text("Current Problem", displayWidth - 205, 45);
		textFont(regFont);
		text(equations[curProb], 205, 115);
		text(problems[curProb], displayWidth - 205, 115);	
	}
	
	public void makeNextPrevButtons(){
		noStroke();
		fill(211,211,211);
		rect(210,displayHeight-110,350,150);
		rect(displayWidth-210,displayHeight-110,350,150);
		fill(0);
		text("Prev", 210, displayHeight-110);
		text("Next", displayWidth-210, displayHeight-110);
	}
	
	public void mousePressed() {
		  plotX = xpos[0];
		  plotY = ypos[0];
		  fill(0,0,200);
		  noStroke();
		  
		  //if user taps on the graph
		  if(mouseX >= (diff/2)-(displayHeight/25) && mouseX <= (displayWidth-(diff/2))+(displayHeight/25)){
			  for(int x=0; x<=10; x++){
				  for(int y=0; y<=10; y++){
					  if(dist(xpos[x], ypos[y], mouseX, mouseY) < dist(plotX, plotY, mouseX, mouseY)){
						  plotX = xpos[x];
						  plotY = ypos[y];
					  }
				  }
			  }
			  
			  ellipse(plotX, plotY, displayHeight/25, displayHeight/25);	
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
