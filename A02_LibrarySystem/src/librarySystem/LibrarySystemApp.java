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
		String[][] allBooks = db.makeBookGrid("ALL", "BookID");		
		
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
		String[][] allPatrons = db.makePatronGrid("PatronID");
		for (int i = 0; i < allPatrons.length; i++)
		{
			for (int j = 0; j < allPatrons[0].length; j++)
			{
				System.out.print(allPatrons[i][j] + " ");
			}
			System.out.println();
		}
		
		db.checkOutBookByIDs("6", "1");
		
		System.out.println();
		System.out.println("Books checked out by patron:");
		String[][] booksCheckedOutByPatron = db.BooksCheckedOutByPatronNames("Mike", "Dey");
		for (int i = 0; i < booksCheckedOutByPatron.length; i++)
		{
			for (int j = 0; j < booksCheckedOutByPatron[0].length; j++)
			{
				System.out.print(booksCheckedOutByPatron[i][j] + " ");
			}
			System.out.println();
		}
	}
}
