/**
 * CST8333
 * Cao, Jun
 * 040710235
 * Oct 21, 2013
 * 
 * This class will create the required database when program starts running
 */

package info.androidhive.sqlite.helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	//Database name
	private static final String DATABASE_NAME = "grades.db";
	//Database version
	private static final int DATABASE_VERSION = 1;
	//Table name  
	public static final String TABLE_GRADES = "grades";
	//Column name
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_ENVIROMENT = "enviroment";
	public static final String COLUMN_FOOD = "food";
	public static final String COLUMN_SERVICE = "service";


  // Database creation SQL statement
  private static final String DATABASE_CREATE = "create table "
  + TABLE_GRADES + "(" + COLUMN_ID + " integer primary key autoincrement, " 
  + COLUMN_NAME + " text not null,"
  + COLUMN_ENVIROMENT + " integer not null,"
  + COLUMN_FOOD + " integer not null,"
  + COLUMN_SERVICE + " integer not null);";

  // Constructor 
  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  // Create database table
  @Override
  public void onCreate(SQLiteDatabase database) {
	  database.execSQL("DROP TABLE IF EXISTS " + TABLE_GRADES);
    database.execSQL(DATABASE_CREATE);
  }
	
  
  //Upgrade database table. delete old table then create a new one with ner version number
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	    + newVersion + ", which will destroy all old data");
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRADES);
	    onCreate(db);
	  }


}
