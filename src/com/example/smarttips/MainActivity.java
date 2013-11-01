/**
 * CST8333
 * Cao, Jun
 * 040710235
 * Oct 21, 2013
 * 
 * This class includes all functions that will be called from interface
 * It will also initialize interface at the beginning.
 */

package com.example.smarttips;

import info.androidhive.sqlite.model.Grade;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity  {
	private GradesDataSource data;
	String errorMessage;
	String n;
	int[] grades = new int[3] ;
	TextView tv;
	int precent;
	
	// Initialize interface
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		data = new GradesDataSource(this);
		data.open();
		tv = (TextView) findViewById(R.id.tip);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// insert new row to table when button clicked
	// Display rows count
	public void onClick(View view) {
		// Get values from all EditText box
		EditText name = (EditText)findViewById(R.id.name); 
		n = name.getText().toString(); 
		
		errorMessage = null;

		EditText enviroment = (EditText)findViewById(R.id.enviroment); 
		try {
			grades[0] = Integer.parseInt(enviroment.getText().toString());
		} catch(NumberFormatException nfe) {
			enviroment.setText(null);
			grades[0] = 0 ;
		  // System.out.println("Could not parse " + nfe);
		} 
		

		EditText food = (EditText)findViewById(R.id.food); 
		try {
			grades[1] = Integer.parseInt(food .getText().toString());
		} catch(NumberFormatException nfe) {
			food.setText(null);
			grades[1] = 0 ;
		  // System.out.println("Could not parse " + nfe);
		} 
		

		EditText service = (EditText)findViewById(R.id.service); 
		try {
			grades[2] = Integer.parseInt(service .getText().toString());
		} catch(NumberFormatException nfe) {
			service.setText(null);
			grades[2] = 0 ;
		  // System.out.println("Could not parse " + nfe);
		} 
		
		// Set error message when missing values
		for (int i: grades)
		{
			if (i==0){
			errorMessage = "All grades have to be a number and bettween 1 to 100";
			}
		}
		
		if(errorMessage== null){
			tipPercent();
			// Save data to database
			Grade grade = new Grade(n,grades[0],grades[1],grades[2]);
			// Return the id of new created row
			long number = data.addGrade(grade);
		}else{
			tv.setText(errorMessage);
		}
	}
	
	// Compute the suggested tip percentage
	// Check all grades, increase percentage for high grade and decrease percent for low grade
	protected void tipPercent(){
		precent = 15;
		for (int i: grades)
		{
			if (i<=10)	{
				precent -=3;
			}else if(i<=40){
				precent -=1;
			}else if (i>=80){
				precent +=3;
			}else if (i>=60){
				precent +=1;
			}
		}
		 
        tv.setText("We suggest you pay "+ precent+ " precent tips" );
	}
	
}
