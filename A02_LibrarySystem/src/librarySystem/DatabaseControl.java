/********************************************************
 *
 *  Project :  A02 Library System
 *  File    :  DatabaseControl.java
 *  Name    :  Michael Dey
 *  Date    :  23 Sept 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		This Class serves as a controller to create, and edit a Library database
 *    		It serves as a back-end interface between the GUI and the SQL database
 *
 *    2) What data-structures are used.
 *
 *    3) What algorithms, techniques, etc. are used in implementing the data structures.
 *
 *    4) What methods are implemented (optional).
 *
 *  Changes :  <Description|date of modifications>
 *
 ********************************************************/

package librarySystem;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class DatabaseControl {
	static Connection connection;
	static int currentBookID=0, currentPatronID=0; //this number holds the new book and new patron ID numbers 
	
	//String with command to create/connect to a database named 'Library_01'
	private static final String connectionURL="jdbc:derby:Library_01;create=true";
	
	public DatabaseControl() throws SQLException
	{
		try
		{
			connection = DriverManager.getConnection(connectionURL); //make a connection
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		createPatronsTable();
		createBooksTable();
	}
	
	/**
	 *  connects to the database and executes an SQL command
	 * @throws SQLException 
	 */
	private static void dbCommunicate(String myCommand) throws SQLException {
		Statement statement = getStatement();
		try
		{
			statement.execute(myCommand);
			
			System.out.println("Done communicating with Database.");
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a statement which is used in Database queries
	 * @return Statement used to Query the database
	 * @throws SQLException
	 */
	private static Statement getStatement() throws SQLException
	{

		return connection.createStatement();								//Statement returned to method
	}
	
	/**
	 * Connect to database, check if the table exists, if it does not exist, send SQL command to dbCommunicate()
	 * 
	 * @param tableName is the name of the table to create
	 * @param myCommand is the string value of the SQL command to create the table
	 */
	private static void createTable(String tableName, String myCommand)
	{
		try
		{
			DatabaseMetaData dbmd = connection.getMetaData();						//get database metadata
			ResultSet resultSet = dbmd.getTables(null, "APP", tableName, null);		//create a resultset of the metadata
			
			//if the resultSet has next(), then the table is already created and should not try to create a table
			//otherwise send the SQL command to the database to create a table
			if (!resultSet.next())
			{
				dbCommunicate(myCommand);	//send the SQL command to dbCommunicate()
			}
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a string that holds SQL information to create a Books table
	 * sends that string to createTable() to execute the SQL command
	 * @throws SQLException 
	 */
	private static void createBooksTable() throws SQLException
	{
		String myCommand = "CREATE TABLE Books "
						+ "("
						+ "BookID INT NOT NULL, "
						+ "Title VARCHAR(50), "
						+ "Author VARCHAR(50), "
						+ "Genre VARCHAR(15), "
						+ "CheckedOut BOOLEAN, "
						+ "PatronID INT,"
						+ "PRIMARY KEY (BookID),"
						+ "FOREIGN KEY (PatronID) REFERENCES Patrons(PatronID)"
						+ ")";
		createTable("BOOKS", myCommand);
	}
	
	/**
	 * creates a string that holds SQL information to create a Patrons table
	 * sends that string to createTable() to execute the SQL command
	 * @throws SQLException 
	 */
	private static void createPatronsTable() throws SQLException
	{
		String myCommand = "CREATE TABLE Patrons "
						+ "("
						+ "PatronID INT NOT NULL, "
						+ "FName VARCHAR(25), "
						+ "LName VARCHAR(25),"
						+ "PRIMARY KEY(PatronID)"
						+ ")";
		createTable("PATRONS",myCommand);
	}
	
	/**
	 * creates a string that holds SQL command to insert Book information into Books table
	 * sends that string to dbCommunicate() to execute the SQL command
	 * @throws SQLException 
	 * 
	 * CheckedOut and PatronID are set to FALSE, and null because
	 * a new book is inherently not checked out, and therefore does not have a Patron associated with it
	 */
	public static void addBook(String title, String author, String genre) throws SQLException
	{
		/*
		 * 	"BookID INT, "
			"Title VARCHAR(50), "
			"Author VARCHAR(50), "
			"Genre VARCHAR(15), "
			"CheckedOut BOOLEAN, "
			"PatronID INT"
		 */
		String myCommand = "INSERT INTO Books VALUES "
							+ "("+ ++currentBookID + ", "
							+ "'"+ title +"', "
							+ "'"+ author +"', "
							+ "'"+ genre +"', "
							+ "FALSE, "
							+ "null)";

		dbCommunicate(myCommand);
	}
	
	/**
	 * creates a string that holds SQL command to insert Patron information into Patrons table
	 * sends that string to dbCommunicate() to execute the SQL command
	 * @throws SQLException 
	 */
	public static void addPatron(String fName, String lName) throws SQLException
	{
//		String myCommand = ("INSERT INTO Patrons VALUES (1002, 'Sara', 'Dey')");
		String myCommand = ("INSERT INTO Patrons VALUES ("
							+ ++currentPatronID +", "
							+ "'" + fName + "', "
							+ "'" + lName + "')");
		dbCommunicate(myCommand);
	}

	/**
	 * This method takes a String which is an SQL command and makes
	 * the query to the database
	 * It then displays the information in the returned statement
	 * 
	 * @param myCommand is a String representation of an SQL Command
	 * @throws SQLException when connection to database is incorrect
	 */
	public static void showAllFromQuery(String myCommand) throws SQLException
	{
		Statement statement = getStatement();	//get statement block from SQL Database
		try
		{
			ResultSet resultSet = statement.executeQuery(myCommand); //send the query and store in resultSet
			
			ResultSetMetaData rsmd = resultSet.getMetaData(); 		//get metadata from resultSet
			
			//print the Header information from the resultSet query
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
			{			
				System.out.printf("%s ",rsmd.getColumnLabel(i));
			}
			System.out.println();
			
			/**Loop through all of the returned pieces in the query
			 * determine what data type they are, and retrieve them according to their type
			 */
			int type;
			while(resultSet.next())								//loop through each piece of data
			{
				for (int i = 1; i<=rsmd.getColumnCount();i++) 	//loop through each column the data is in
				{
					type = rsmd.getColumnType(i);				//get the data type for the column
					
					if(type == Types.VARCHAR || type == Types.CHAR)			//type is a String or char
					{
						System.out.print(resultSet.getString(i)+" ");
					}
					else if (type == Types.BOOLEAN)							//type is a Boolean
					{
						System.out.print(resultSet.getBoolean(i)+" ");
					}
					else													//type is an int
					{
						System.out.print(resultSet.getInt(i)+" ");
					}
				}
				System.out.println();
			}

		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
