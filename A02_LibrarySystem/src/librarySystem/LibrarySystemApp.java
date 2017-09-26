package librarySystem;

import java.sql.SQLException;

public class LibrarySystemApp {

	public static void main(String[] args) throws SQLException {
		DatabaseControl db = new DatabaseControl();
		//db.createBooksTable();
		//db.createPatronsTable();
		//db.addBook();
		//db.addPatron();
		db.showAllFromQuery("SELECT * FROM Books");
		System.out.println();
		db.showAllFromQuery("SELECT * FROM Patrons");
	}

}
