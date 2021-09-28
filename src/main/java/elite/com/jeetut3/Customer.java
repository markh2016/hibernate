package elite.com.jeetut3;

import java.io.Serializable; // make this serializeable so we can transfer data to database 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id ;
// this define which objects should be persisted inside  database 
@Entity 
// This defines the table that we use for our entity 
@Table(name ="customer")

public class Customer implements Serializable{

 //  we need a serial version id so that we have the right classes working along side this 
	private static final long serialVersionUID = 1L;
	
	
	// All your entities must define a primary key 
	// these are defined with annotation , "the @ sign " ,  hence we look this up in table defined as customer
	// in dB test4   that you create in Maria-db 
	// define your column names that you will access 

	// this is a must have "@Id" 
	@Id
	
	@Column(name = "custID", unique =true) 
	private int id ;
	
	@Column(name ="firstName" , nullable =false )
	private String fName ;
	
	@Column(name ="lastName" , nullable =false )
	private String lName ;
	
	// now create  getter and setter methods for fields in DB 
	
	public int getID()
	{
		return this.id ;
	}
	
	public void setID (int id) {
		this.id = id ; 
	}
	
	public String getFName()
	{
		return this.fName ;
	}
	
	public void setFName(String fName)
	{
		this.fName = fName ;
	}
	
	public String getlName()
	{
		return this.lName ;
	}
	
	public void setLName(String lName)
	{
		this.lName = lName ;
	}
	
	// having defined all getter and setter nethod's we then create 2 folders  one  being a resource folder called resources
	// and under this we create another  folder  named "META-INF" 
	
	/* Next step is create an xml file in the META_INF folder */ 
	
	
}
