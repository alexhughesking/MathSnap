package uf.seniorproj.alexryan;

import processing.core.PApplet;
import android.os.Bundle;
import android.view.Menu;

public class GraphActivity extends PApplet {
	
	int diff;
	int[] xpos = new int[11];
	int[] ypos = new int[11];
	String[] problems = {"y = 3x + 1"}; 
	
	public void setup(){
		background(255,255,224);
		makeGraph();
	}

	public void draw() {}
	
	public void makeGraph(){
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
	
	public void makeButtons(){
		
	}
	
	public void mousePressed() {
		  int plotX = xpos[0];
		  int plotY = ypos[0];
		  fill(0,0,200);
		  noStroke();
		  
		  if(mouseX >= diff/2 && mouseX <= displayWidth-(diff/2)){
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_graph);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graph, menu);
		return true;
	}

}
