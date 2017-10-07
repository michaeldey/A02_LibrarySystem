/********************************************************
 *
 *  Project :  A02 Library System
 *  File    :  DatabaseControlTest.java
 *  Name    :  Michael Dey Lisa Hammond
 *  Date    :  23 Sept 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		This Class tests the DatabaseControl.java methods
 *
 *    2) What data-structures are used.
 *    		DatabaseControl, database calls, and String[][]
 *
 *    3) What algorithms, techniques, etc. are used in implementing the data structures.
 *    		JUnit testing
 *
 ********************************************************/
package librarySystem;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class DatabaseControlTest {

	DatabaseControl testDB;
	
	@Before
	public void setUp() throws Exception {
		testDB = new DatabaseControl("dbTestCase01");
		testDB.addPatron("Patron1FName", "Patron1LName");
		testDB.addBook("Book1Title", "Book1FName", "Book1LName", "Book1genre");
		testDB.addBook("Book2Title", "Book2FName", "Book2LName", "Book2genre");
	}

	@Test
	public void testDoesDBExist() {
		assertTrue(testDB.doesDBExist());							//check if database exists
	}

	//tests if adding a book causes an exception
	@Test
	public void testAddBook()
	{
		boolean test = false;
		try
		{
			testDB.addBook("title", "fName", "lName", "genre");
			test = true;
		}
		catch(SQLException e)
		{
			test = false;
		}
		assertTrue(test);
	}
	
	//tests if adding a patron causes an exception
	@Test
	public void testAddPatron()
	{
		boolean test = false;
		try
		{
			testDB.addPatron("fName", "lName");
			test = true;
		}
		catch(SQLException e)
		{
			test = false;
		}
		assertTrue(test);
	}
	
	//adds values to database, and then checks to see they are implemented by checking the return values
	@Test
	public void testAddBookReturnValues() throws SQLException {
//		testDB.addBook("Book2Title", "Book2FName", "Book2LName", "Book2genre"); //second boook already implemented
		
		//we are testing the second book because the first book may or may not be turned in by the other tests
		String[][] myReturn = testDB.makeBookGrid("ALL", "BookID");
		assertTrue(myReturn[1][0].equals("2"));								//bookID
		assertTrue(myReturn[1][1].equals("Book2Title"));					//title
		assertTrue(myReturn[1][2].equals("Book2FName"));					//author first
		assertTrue(myReturn[1][3].equals("Book2LName"));					//author last
		assertTrue(myReturn[1][4].equals("Book2genre"));					//genre
		assertTrue(myReturn[1][5].equals("IN"));							//is book checked in
	}
	
	//adds values to database, and then checks to see they are implemented by checking the return value size
	@Test
	public void testMakeBookGridReturnSize() throws SQLException {
		String[][] myReturn = testDB.makeBookGrid("ALL", "BookID");
		assertTrue(myReturn[0].length==7);
	}

	//adds values to database, and then checks to see they are implemented by checking the return values
	@Test
	public void testMakePatronGridReturnValues() throws SQLException {
//		testDB.addPatron("Patron1FName", "Patron1LName"); 			//already implemented
		String[][] myReturn = testDB.makePatronGrid("PatronID");
		assertTrue(myReturn[0][0].equals("1"));						//patronID
		assertTrue(myReturn[0][1].equals("Patron1FName"));					//first name
		assertTrue(myReturn[0][2].equals("Patron1LName"));					//last name
	}

	//check out a book, it is correct if the return values are correct
	@Test
	public void testCheckOutBookByIDs() throws SQLException {
		testDB.checkOutBookByIDs("1", "1");							//check out book 1 to patron 1
		String[][] myReturn = testDB.makeBookGrid("OUT", "BookID");
		assertTrue(myReturn[0][0].equals("1"));						//bookID
		assertTrue(myReturn[0][5].equals("OUT"));					//checkedOut
		assertTrue(myReturn[0][6].equals("1"));						//patronID
	}

	//check out a book, check it back in, check return values to see that they are updated
	@Test
	public void testCheckInBookByIDs() throws SQLException{
		testDB.checkOutBookByIDs("1","1");							//check out book 1 to patron 1
		testDB.checkInBookByIDs("1");								//check book 1 back in
		String[][] myReturn = testDB.makeBookGrid("ALL", "BookID");
		assertTrue(myReturn[0][0].equals("1"));						//bookID
		assertTrue(myReturn[0][5].equals("IN"));					//checkedOut
	}

	@Test
	public void testBooksCheckedOutByPatronID() throws SQLException{
//		testDB.addBook("Book1Title", "Book1FName", "Book1LName", "Book1genre"); //already implemented
		testDB.checkOutBookByIDs("1", "1");								//check out book 1 to patron 1
		String[][] myReturn = testDB.BooksCheckedOutByPatronID("1");
		assertTrue(myReturn[0][0].equals("1"));								//bookID
		assertTrue(myReturn[0][1].equals("Book1Title"));							//title
		assertTrue(myReturn[0][2].equals("Book1FName"));							//author first
		assertTrue(myReturn[0][3].equals("Book1LName"));							//author last
		assertTrue(myReturn[0][4].equals("Book1genre"));							//genre
		assertTrue(myReturn[0][5].equals("OUT"));							//is book checked in
		assertTrue(myReturn[0][6].equals("1"));								//patron who has book out
	}

}
