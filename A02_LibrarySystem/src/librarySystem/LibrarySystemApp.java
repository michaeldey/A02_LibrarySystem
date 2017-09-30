package librarySystem;

import java.sql.SQLException;

public class LibrarySystemApp {

	public static void main(String[] args) throws SQLException {
		DatabaseControl db = new DatabaseControl();
		
//		db.addBook("The Art of War", "Sun Tsu", "Self Help");
//		db.addBook("Siddhartha", "Herman Hesse", "Literature");
//		db.addBook("Hop On Pop", "Dr. Seusse", "Kids");
//		db.addBook("Green Eggs and Ham", "Dr. Seusse", "Kids");
//		db.addBook("Cat In The Hat", "Dr. Seusse", "Kids");
//		db.addBook("The Art of Peace", "Morihei Ueshiba", "Self Help");		
//		
		db.showAllFromQuery("SELECT * FROM Books");
//		db.showAllFromQuery("SELECT * FROM Books WHERE GENRE='Kids' ORDER BY Title");
//		
//		System.out.println();
//		
//
//		db.addPatron("Mike", "Dey");
//		db.addPatron("John", "Smith");
		db.showAllFromQuery("SELECT * FROM Patrons");
	}

}
