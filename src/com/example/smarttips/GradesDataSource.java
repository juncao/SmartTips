/**
 * CST8333
 * Cao, Jun
 * 040710235
 * Oct 21, 2013
 * 
 * This class has all the database connection functions
 */

package com.example.smarttips;

import info.androidhive.sqlite.helper.MySQLiteHelper;
import info.androidhive.sqlite.model.Grade;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GradesDataSource {

	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
	      MySQLiteHelper.COLUMN_NAME,MySQLiteHelper.COLUMN_ENVIROMENT,
	      MySQLiteHelper.COLUMN_FOOD,MySQLiteHelper.COLUMN_SERVICE,};
	  //Constructor
	  public GradesDataSource(Context context) {
		    dbHelper = new MySQLiteHelper(context);
		  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }
	  //Insert a new grade information to database
	  public long addGrade(Grade grade) {
		    ContentValues values = new ContentValues();
		    
		    values.put(MySQLiteHelper.COLUMN_NAME, grade.getName());
		    values.put(MySQLiteHelper.COLUMN_ENVIROMENT, grade.getEnviroment());
		    values.put(MySQLiteHelper.COLUMN_FOOD, grade.getFood());
		    values.put(MySQLiteHelper.COLUMN_SERVICE, grade.getService());
		    
		    long insertId = database.insert(MySQLiteHelper.TABLE_GRADES, null,
		        values);
		    
		    return insertId;
		  }
	  

	  //Return all grades information
	  public List<Grade> getAllGrades() {
		    List<Grade> grades = new ArrayList<Grade>();

		    Cursor cursor = database.query(MySQLiteHelper.TABLE_GRADES,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Grade grade = cursorToGrades(cursor);
		      grades.add(grade);
		      cursor.moveToNext();
		    }
		    cursor.close();
		    return grades;
		  }
	  
	  private Grade cursorToGrades(Cursor cursor) {
		  Grade grades = new Grade();
		  grades.setID(cursor.getLong(0));
		  grades.setName(cursor.getString(1));
		  grades.setEnviroment(cursor.getLong(2));
		  grades.setFood(cursor.getLong(3));
		  grades.setService(cursor.getLong(4));
		    return grades;
		  }
	  
}
