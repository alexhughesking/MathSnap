package uf.seniorproj.alexryan;

import processing.core.PApplet;

public class GraphActivity extends PApplet {

	int value;
	
	public void setup(){
		rectMode(CENTER);
		makeGraph();
		value = 0;
	}

	public void draw() {

	}
	
	public void makeGraph(){
		for (int i =0; i<=displayWidth; i+=displayHeight/10){
			line(i,0,i,displayHeight);
			line(0,i,displayWidth,i);
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
