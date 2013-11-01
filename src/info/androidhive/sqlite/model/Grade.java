/**
 * CST8333
 * Cao, Jun
 * 040710235
 * Oct 21, 2013
 * 
 * The instance of this project will store all needed values from running prorgam.
 * There also has functions for get or change values.
 */

package info.androidhive.sqlite.model;

public class Grade {
	
	private long id;
	private String name;
	private long enviroment;
	private long food;
	private long service;
	
	// Default Constructor
	public Grade(){
		
	}
	
	//Constructor with input values
	public Grade(String n, int e, int f, int s){
		name = n;
		enviroment = e;
		food = f ;
		service =s;
	}
	
	public long getID(){
		return id;
	}

	public String getName(){
		return name;
	}
	
	public long getEnviroment(){
		return enviroment;
	}
	
	public long getFood(){
		return food;
	}
	
	public long getService(){
		return service;
	}
	
	public void setID(long l){
		id = l;
	}

	public void setName(String n){
		name = n; 
	}
	
	public void setEnviroment(long e){
		enviroment = e;
	}
	
	public void setFood(long f){
		food = f;
	}
	
	public void setService(long s ){
		service = s;
	}



	
}
