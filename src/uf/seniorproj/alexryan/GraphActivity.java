package uf.seniorproj.alexryan;

import processing.core.PApplet;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GraphActivity extends PApplet {
	
	
	int value;
	
	public void setup(){
		rectMode(CENTER);
		makeGraph();
		value = 0;
	}

	public void draw() {
//	  fill(value);
//	  rect(displayWidth/2, displayHeight/2, 300, 300);
	}
	
	public void makeGraph(){
		if(displayWidth > displayHeight){
			for (int i =0; i<=displayWidth; i+=displayHeight/10){
				line(i,0,i,displayHeight);
				line(0,i,displayWidth,i);
			}
		}
		else{
			for (int i =0; i<=displayHeight; i+=displayWidth/10){
				line(i,0,i,displayWidth);
				line(0,i,displayHeight,i);
			}
		}
		
	}
	
	public void mousePressed() {
		  if (value == 0) {
		    value = 255;
		  } else {
		    value = 0;
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
