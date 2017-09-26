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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class DatabaseControl {

	//String with command to create/connect to a database named 'Library_02'
	private static final String connectionURL="jdbc:derby:Library_02;create=true";
	
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
		Connection connection = DriverManager.getConnection(connectionURL); //make a connection
		return connection.createStatement();								//Statement returned to method
	}
		
	/**
	 * creates a string that holds SQL information to create a Books table
	 * sends that string to dbCommunicate() to execute the SQL command
	 * @throws SQLException 
	 */
	public static void createBooksTable() throws SQLException
	{
		String myCommand = ("CREATE TABLE Books (BookID int, Title varchar(50))");
		dbCommunicate(myCommand);
	}
	
	/**
	 * creates a string that holds SQL information to create a Patrons table
	 * sends that string to dbCommunicate() to execute the SQL command
	 * @throws SQLException 
	 */
	public static void createPatronsTable() throws SQLException
	{
		String myCommand = ("CREATE TABLE Patrons (PatronID int, FName varchar(15), LName varchar(20))");
		dbCommunicate(myCommand);
	}
	
	/**
	 * creates a string that holds SQL command to insert Book information into Books table
	 * sends that string to dbCommunicate() to execute the SQL command
	 * @throws SQLException 
	 */
	public static void addBook() throws SQLException
	{
		String myCommand = ("INSERT INTO Books VALUES (002, 'The Cat In The Hat')");
		dbCommunicate(myCommand);
	}
	
	/**
	 * creates a string that holds SQL command to insert Patron information into Patrons table
	 * sends that string to dbCommunicate() to execute the SQL command
	 * @throws SQLException 
	 */
	public static void addPatron() throws SQLException
	{
		String myCommand = ("INSERT INTO Patrons VALUES (1002, 'Sara', 'Dey')");
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
