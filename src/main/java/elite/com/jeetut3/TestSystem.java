package elite.com.jeetut3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TestSystem {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = 
			Persistence.createEntityManagerFactory("jeetut3");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// we can call our functions of this class , note they are declared as static so we dont 
		
		// have to instantiate the class we  call method 
		
		// to add  customers 
		
		addCustomer(1,"mark","johnston");
		
		
		ENTITY_MANAGER_FACTORY.close();		

	}

	public static void addCustomer(int id , String fname , String lastname) {
		// to create a customer we need an entity manager 
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		// entity transaction manager class is used to transfer and update data on DB 
		EntityTransaction et = null ; 
		// check for issues  with queries not going through to db 
		
		try {
			// to start transaction we call method get Transaction 
			et =em.getTransaction();
			// start the transaction process
			et.begin();
			// create the customer to add to DB 
			Customer cust = new Customer(); 
			// now set the data 
			cust.setID(id);
			cust.setFName(fname);
			cust.setLName(lastname);
			// now save this to the database  The entity manager is  the class that performs this task 
			// so we pass this the customer object 
			em.persist(cust);
			// finally we commit the transaction  with the entity transaction class 
			et.commit();
			
			
		}catch (Exception ex )
					{
			           // if there is an exception thrown then we roll back changes 
						if(et!= null) {
							et.rollback();
						}
						ex.printStackTrace();
					}
		finally {
			// we close the entity manager 
			em.close();
		}
		
	
		
	}// end method 
	
	// define a method to obtain a customer 
	
	public static void getCustomer(int id ) {
		// to get a customer we need an entity manager  class 
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		// now we create a query to fetch data 
		
		// c below is our customer object that we want to retrieve  we define the  parameterize'd ID i.e :custID
		// below 
		
		String query = "SELECT c FROM Customer c WHERE c.id:custID " ;
		
		// now create the query  by using the TypedQuery class 
		// we are expecting to get customer object hence we pass this class the Customer object to the template 
		// and pass the entity manager the query and our customer class 
		
		TypedQuery<Customer> tqc =  em.createQuery(query, Customer.class);
		// once we have done then we set the  parameterize'd variable of the query  which in this case is the :custID
		
		// the id is passed  to the function  getCustomer
		tqc.setParameter("custID", id );
		
		// create a class variable to hold our Customer object and set this to null 
		
		Customer cust = null ;
				
		// now retrieve the record set if cust result set successful 
		
		try 
		{
			cust = tqc.getSingleResult();
			
			// if all went well then we can use the getter methods from the customer class to print results
			System.out.println(cust.getFName() + " " + cust.getlName()) ;
		}
		catch (NoResultException nre )
			{
				// if exception thrown then show this 
				
				System.out.println(nre.getMessage());
			
			}
		finally 
		{ 
			// must close the entitymanager 
			em.close(); 
			
		}
		
	} // end method 
	
	
}
