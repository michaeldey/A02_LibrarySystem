package librarySystem;

import java.sql.SQLException;

public class LibrarySystemApp {

	public static void main(String[] args) throws SQLException {
		DatabaseControl db = new DatabaseControl("Library_07");
		
//		db.addBook("The Art of War", "Sun", "Tsu", "Self Help");
//		db.addBook("Siddhartha", "Herman","Hesse", "Literature");
//		db.addBook("Hop On Pop", "Dr.","Seusse", "Kids");
//		db.addBook("Green Eggs and Ham", "Dr.","Seusse", "Kids");
//		db.addBook("Cat In The Hat", "Dr.","Seusse", "Kids");
//		db.addBook("The Art of Peace", "Morihei","Ueshiba", "Self Help");	
		db.addBook("The Martian", "Andy","Wier", "Self Help");
//		db.addPatron("Mike", "Dey");
//		db.addPatron("Lisa", "Hammond");
//		db.addPatron("John", "Smith");
		db.addPatron("David", "Sedaris");
		
//		System.out.println("All books:");
//		db.showAllFromQuery("SELECT * FROM Books");
//		
//		System.out.println("Children's books:");
//		db.showAllFromQuery("SELECT * FROM Books WHERE GENRE='Kids' ORDER BY Title");
//		
//		System.out.println();
//
//		
//		System.out.println("All Patrons:");
//		db.showAllFromQuery("SELECT * FROM Patrons");
		
		String[][] getPatronSize = db.showAllFromQuery("SELECT COUNT(PatronID) FROM Patrons");
		System.out.println(getPatronSize[0][0]);
		
		String[][] getBookSize = db.showAllFromQuery("SELECT COUNT(BookID) FROM Books");
		System.out.println(getBookSize[0][0]);
	}

}
