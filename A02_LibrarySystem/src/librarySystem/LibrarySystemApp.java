package librarySystem;

import java.sql.SQLException;

public class LibrarySystemApp {

	public static void main(String[] args) throws SQLException {
		DatabaseControl db = new DatabaseControl("Library_08");
		
//		db.addBook("The Art of War", "Sun", "Tsu", "Self Help");
//		db.addBook("Siddhartha", "Herman","Hesse", "Literature");
//		db.addBook("Hop On Pop", "Dr.","Seusse", "Kids");
//		db.addBook("Green Eggs and Ham", "Dr.","Seusse", "Kids");
//		db.addBook("Cat In The Hat", "Dr.","Seusse", "Kids");
//		db.addBook("The Art of Peace", "Morihei","Ueshiba", "Self Help");	
//		db.addBook("The Martian", "Andy","Wier", "Self Help");
//		db.addPatron("Mike", "Dey");
//		db.addPatron("Lisa", "Hammond");
//		db.addPatron("John", "Smith");
//		db.addPatron("David", "Sedaris");
//		
//		db.checkOutBookByNames("Hop On Pop", "John", "Smith");
//		db.checkOutBookByIDs("1", "3");
//		
//		db.checkInBookByIDs("3");
//		
		System.out.println("All books:");
		String[][] allBooks = db.makeBookGrid("IN", "BookID");
		
		
		for (int i = 0; i < allBooks.length; i++)
		{
			for (int j = 0; j < allBooks[0].length; j++)
			{
				System.out.print(allBooks[i][j] + " ");
			}
			System.out.println();
		}

		
		System.out.println();

		
		System.out.println("All Patrons:");
		String[][] allPatrons = db.makePatronGrid("FName");
		for (int i = 0; i < allPatrons.length; i++)
		{
			for (int j = 0; j < allPatrons[0].length; j++)
			{
				System.out.print(allPatrons[i][j] + " ");
			}
			System.out.println();
		}
		
		String[][] getPatronSize = db.showAllFromQuery("SELECT COUNT(PatronID) FROM Patrons");
		System.out.println(getPatronSize[0][0]);
		
		String[][] getBookSize = db.showAllFromQuery("SELECT COUNT(BookID) FROM Books");
		System.out.println(getBookSize[0][0]);
	}
}
