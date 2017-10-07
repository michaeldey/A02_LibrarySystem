/********************************************************
 *
 *  Project :  A02 Library System
 *  File    :  DatabaseControl.java
 *  Name    :  Michael Dey Lisa Hammond
 *  Date    :  23 Sept 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		This Class serves as the application's main() in order to start the application
 *    
 *    		It also serves to add items to the database if it's a new database.
 *
 *    2) What data-structures are used.
 *    		
 *
 *    3) What algorithms, techniques, etc. are used in implementing the data structures.
 *    		it creates a DatabaseControl object and a LibraryFrame object to be used together
 *
 ********************************************************/
package librarySystem;

import java.sql.SQLException;

public class LibrarySystemApp {

	public static void main(String[] args) throws SQLException {
		DatabaseControl db = new DatabaseControl("Library_10"); //create a new database with the name indicated
		
		//check if database exists, if it does not, generate a list, otherwise, don't generate a list
		if (!db.doesDBExist())
		{
			System.out.println("Generating lists.");
			generateBookList(db);		//adds a list of 30 books to the database		
			generatePatronList(db);		//adds a list of 15 patrons to the database
		}
		
		LibraryFrame lf = new LibraryFrame(db);
		
		
	}//end of main

	/**
	 * generates a list of 15 patrons for the database
	 * @param db - database we are talking to
	 * @throws SQLException
	 */
	private static void generatePatronList(DatabaseControl db) throws SQLException {
		db.addPatron("Mike", "Dey"); 						//1
		db.addPatron("Lisa", "Hammond");					//2
		db.addPatron("John", "Smith");						//3
		db.addPatron("David", "Sedaris");					//4
		db.addPatron("Jorge", "Garcia"); 					//5
		db.addPatron("Josh", "Halloway");					//6
		db.addPatron("Daniel", "Kim");						//7
		db.addPatron("Emerson", "Michael");					//8
		db.addPatron("Bryan", "Shu"); 						//9
		db.addPatron("Adam", "Rodriguez");					//10
		db.addPatron("David", "Greenman");					//11
		db.addPatron("Bryan", "Callen");					//12
		db.addPatron("Scarlett", "Burke"); 					//13
		db.addPatron("Michelle", "LaRue");					//14
		db.addPatron("Jason", "Axinn");						//15
	}

	/**
	 * Generates a list of 30 books into the database
	 * @param db - name of the database
	 * @throws SQLException
	 */
	private static void generateBookList(DatabaseControl db) throws SQLException {
		db.addBook("The Art of War", "Sun", "Tsu", "Self Help");			//1
		db.addBook("Siddhartha", "Herman","Hesse", "Literature");			//2
		db.addBook("Hop On Pop", "Dr.","Seusse", "Kids");					//3
		db.addBook("Green Eggs and Ham", "Dr.","Seusse", "Kids");			//4
		db.addBook("Cat In The Hat", "Dr.","Seusse", "Kids");				//5
		db.addBook("The Art of Peace", "Morihei","Ueshiba", "Self Help");	//6
		db.addBook("The Art of Peace", "Morihei","Ueshiba", "Self Help");	//7	
		db.addBook("The Martian", "Andy","Wier", "Science Fiction");		//8
		db.addBook("Pride and Prejudice", "Jane", "Austin", "Literature");	//9
		db.addBook("Alice in Wonderland", "Lewis","Carroll", "Literature");	//10
		db.addBook("Sherlock Holmes", "Arthur", "Doyle", "Mystery");		//1
		db.addBook("Dracula", "Bram","Stoker", "Horror");					//2
		db.addBook("Tale of Two Cities", "Charles","Dickens", "Literature");//3
		db.addBook("Frankenstein", "Mary","Shelly", "Horror");				//4
		db.addBook("Dorian Grey", "Oscar","Wilde", "Literature");			//5
		db.addBook("The Hobbit", "J.R.R","Tolkien", "Fantasy");				//6	
		db.addBook("Dracula", "Bram","Stoker", "Horror");					//7
		db.addBook("Harry Potter", "J.K.","Rowling", "Fantasy");			//8
		db.addBook("The Girl On The Train", "Paula", "Hawkins", "Mystery");	//9
		db.addBook("Holes", "Louis","Sachar", "Kids");						//20
		db.addBook("Many Colours of Us", "Rachel", "Burton", "Humor");		//1
		db.addBook("Astrophysics", "Neil","Tyson", "Science");				//2
		db.addBook("City of Ember", "Jeanne","DuPrau", "Kids");				//3
		db.addBook("Shadow Bright and Burning", "Jessica","Cluess", "Kids");//4
		db.addBook("IT", "Stephen","King", "Horror");						//5
		db.addBook("The Shining", "Stephen","King", "Horror");				//6
		db.addBook("City of Ember", "Jeanne","DuPrau", "Kids");				//7
		db.addBook("Misery", "Stephen","King", "Horror");					//8
		db.addBook("A Game of Thrones", "George", "Martin", "Fantasy");		//9
		db.addBook("Huckleberry Finn", "Mark","Twain", "Literature");		//30		
	}
}
