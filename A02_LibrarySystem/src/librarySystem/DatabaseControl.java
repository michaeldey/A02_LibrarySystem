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
import java.util.ArrayList;
import java.util.List;

public class DatabaseControl {
	static Connection connection;
	static int currentBookID=0, currentPatronID=0; //this number holds the new book and new patron ID numbers 
	
	//String with command to create/connect to a database
	private static String connectionURL;
	
	//Strings to hold the commands to create the tables
	static final String CREATE_BOOKS_TABLE_COMMAND = "CREATE TABLE Books "
			+ "("
			+ "BookID INT NOT NULL, "
			+ "Title VARCHAR(50), "
			+ "AuthorFirst VARCHAR(25), "
			+ "AuthorLast VARCHAR(25), "
			+ "Genre VARCHAR(15), "
			+ "CheckedOut BOOLEAN, "
			+ "PatronID INT, "
			+ "PRIMARY KEY (BookID), "
			+ "FOREIGN KEY (PatronID) REFERENCES Patrons(PatronID)"
			+ ")";
	
	static final String CREATE_PATRONS_TABLE_COMMAND = "CREATE TABLE Patrons "
			+ "("
			+ "PatronID INT NOT NULL, "
			+ "FName VARCHAR(25), "
			+ "LName VARCHAR(25), "
			+ "PRIMARY KEY(PatronID)"
			+ ")";
	
	/**
	 * Constructor creates a connection to the database
	 * it then searches the database for the Patrons table and creates it if it doesn't exist
	 * it then searches the database for the Books table and creates it if it doesn't exist
	 * @throws SQLException
	 */
	public DatabaseControl(String dbName) throws SQLException
	{
		connectionURL="jdbc:derby:" + dbName + ";create=true"; //String to create a database of the given name
		try
		{
			connection = DriverManager.getConnection(connectionURL); //make a connection
//			connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		createTable("PATRONS", CREATE_PATRONS_TABLE_COMMAND);
		createTable("BOOKS", CREATE_BOOKS_TABLE_COMMAND);
	}
	
	/**
	 *  connects to the database and executes an SQL command
	 * @throws SQLException 
	 * @param myCommand holds the SQL command to send to the database
	 */
	private static void dbCommunicate(String myCommand) throws SQLException {
		Statement statement = getStatement();
		try
		{
			statement.execute(myCommand);			
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
			/**If the Database Tables have already been created,
			 * currentPatronID and currentBookId counts will be inaccurate on Class creation
			 * This gets an accurate count of what is in the PATRONS and BOOKS tables and
			 * adjusts the currentPatronID and currentBookID numbers so they are accurate when
			 * adding to their respective tables
			 */
			else
			{
				if (tableName.equals("PATRONS"))
				{
					String[][] getPatronSize = showAllFromQuery("SELECT COUNT(PatronID) FROM Patrons");
					currentPatronID = Integer.parseInt(getPatronSize[0][0]);
				}
				else if (tableName.equals("BOOKS"))
				{					
					String[][] getBookSize = showAllFromQuery("SELECT COUNT(BookID) FROM Books");
					currentBookID = Integer.parseInt(getBookSize[0][0]);
				}
			}
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a string that holds SQL command to insert Book information into Books table
	 * sends that string to dbCommunicate() to execute the SQL command
	 * @throws SQLException 
	 * 
	 * CheckedOut and PatronID are set to FALSE, and null because
	 * a new book is inherently not checked out, and therefore does not have a Patron associated with it
	 */
	public static void addBook(String title, String authorFirst, String authorLast, String genre) throws SQLException
	{
		String myCommand = "INSERT INTO Books VALUES "
							+ "("+ ++currentBookID + ", "	// BookID INT
							+ "'"+ title +"', "				// Title VARCHAR(50)
							+ "'"+ authorFirst +"', "		// AuthorFirst VARCHAR(25)
							+ "'"+ authorLast +"', "		// AuthorLast VARCHAR(25)
							+ "'"+ genre +"', "				// Genre VARCHAR(15)
							+ "FALSE, "						// CheckedOut BOOLEAN
							+ "null)";						// PatronID INT

		dbCommunicate(myCommand);
	}
	
	/**
	 * creates a string that holds SQL command to insert Patron information into Patrons table
	 * sends that string to dbCommunicate() to execute the SQL command
	 * @throws SQLException 
	 */
	public static void addPatron(String fName, String lName) throws SQLException
	{
		String myCommand = ("INSERT INTO Patrons VALUES ("
							+ ++currentPatronID +", "
							+ "'" + fName + "', "
							+ "'" + lName + "')");
		dbCommunicate(myCommand);
	}

	/**
	 * This method takes a String which is an SQL command and makes
	 * the query to the database
	 * It then returns the query as a 2 diminsional Array of Strings
	 * 
	 * @param myCommand is a String representation of an SQL Command
	 * @throws SQLException when connection to database is incorrect
	 * @return String[][] returnString
	 */
	public static String[][] showAllFromQuery(String myCommand) throws SQLException
	{
		Statement statement = getStatement();	//get statement block from SQL Database
		String[][] returnString;
		try
		{
			List<String> resultsList = new ArrayList<String>();			//ArrayList to hold the resultSet values as Strings
			ResultSet resultSet = statement.executeQuery(myCommand); //send the query and store in resultSet
			
			ResultSetMetaData rsmd = resultSet.getMetaData(); 		//get metadata from resultSet
			int columnCount = rsmd.getColumnCount();				//number of columns in the resultSet
						
//			//print the Header information from the resultSet query
//			for (int i = 1; i <= columnCount; i++)
//			{			
//				System.out.printf("%s ",rsmd.getColumnLabel(i));
//			}
//			System.out.println();			
			
			/**Loop through all of the returned pieces in the query
			 * determine what data type they are, and retrieve them according to their type
			 */
			int type;
			while(resultSet.next())								//loop through each piece of data
			{
				for (int i = 1; i<=columnCount;i++) 	//loop through each column the data is in
				{
					type = rsmd.getColumnType(i);				//get the data type for the column
					
					if(type == Types.VARCHAR || type == Types.CHAR)			//type is a String or char
					{
						resultsList.add(resultSet.getString(i));
//						System.out.print(resultSet.getString(i)+" ");
					}
					else if (type == Types.BOOLEAN)							//type is a Boolean
					{
						resultsList.add(String.valueOf(resultSet.getBoolean(i)));
//						System.out.print(resultSet.getBoolean(i)+" ");
					}
					else													//type is an int
					{
						resultsList.add(String.valueOf(resultSet.getInt(i)));
//						System.out.print(resultSet.getInt(i)+" ");
					}
				}
			}
			
			//create a 2D array to return to the GUI
			returnString = new String[(resultsList.size()/columnCount)][columnCount];
			int ptr = 0;
			for (int i = 0; i < (resultsList.size()/columnCount); i++)
			{
				for (int j = 0; j < columnCount; j++)
				{
					returnString[i][j] = resultsList.get(ptr++);
				}
			}
			
			//print the 2D array to make sure it works
//			for (int i = 0; i < returnString.length; i++)
//			{
//				for (int j = 0; j < returnString[0].length; j++)
//				{
//					System.out.print(returnString[i][j] + " ");
//				}
//				System.out.println();
//			}			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			returnString = new String[][] {{null,null}};//returnString does not return a value
		}
		return returnString;
	}//end of ShowAllFromQuery
}
