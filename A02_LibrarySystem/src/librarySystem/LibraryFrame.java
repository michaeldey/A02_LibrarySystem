/********************************************************
 *
 *  Project :  A02 Library System
 *  File    :  LibraryFrame.java
 *  Name    :  Michael Dey Lisa Hammond
 *  Date    :  23 Sept 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		This Class creates and shows the GUI
 *    		It interfaces with the DatabaseControl (db)
 *    			in order to send information to, and gather information from the database
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class LibraryFrame extends JFrame  {

		private GridLayout libraryLayout;
		private GridLayout patronLayout;
		private JPanel boxBooksNorth;
		private JPanel boxPatronsNorth;
		private JButton buttonAvailable;
		private JPanel boxPatronsSouth;
		String buttonText;
		Connection connection;
		static char selection;
		String checkedIn;
		String sortByName;
		String sortByNamePatrons;
		DatabaseControl db;


	/**
	 * Create the frame.
	 */
	  
		public LibraryFrame(DatabaseControl db) throws SQLException {
			  
		    super("Library System"); // Set window title
		    
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setMinimumSize(new Dimension(1000, 500));
		    	
		    
		    // Close connections exit the application when the user
		    // closes the window

//		    addWindowListener(new WindowAdapter() {
//		        
//				public void windowClosing(WindowEvent e) {
//
//		          try {
//		            connection.close();
//		          } catch (SQLException sqle) {
//		        	//System.out.println(e.getMessage());
//		  			//e.printStackTrace();
//		          }
//		          System.exit(0);
//		        }
//		      });
		        // Initialize and lay out window controls
		   
		      JLabel label_BOOKID;
		      JLabel label_TITLE;
			  JLabel label_TITLE_CHECKOUT_FORM;
			  JLabel label_AUTHOR_FIRST;
			  JLabel label_AUTHOR_LAST;
			  JLabel label_GENRE;
			  JLabel label_addBook;
			  JLabel label_addPatron;
			  JLabel label_check_ret;
			  		  
			  JLabel label_PATRON_FIRST;
			  JLabel label_PATRON_LAST;
			  JLabel label_PATRON_FIRST2;
			  JLabel label_PATRON_LAST2;
			  			  
			  JLabel label_viewBooksOut;
			  JLabel label_PATRON_ID;
			  JLabel label_PATRON_ID_BOOKS;
			  JLabel label_BOOKS_OUT;
			  			 
			  JTextField textField_BOOKID;
			  JTextField textField_TITLE;
			  JTextField textField_TITLE_CHECKOUT_FORM;
			  JTextField textField_AUTHOR_FIRST;
			  JTextField textField_AUTHOR_LAST;
			  JTextField textField_GENRE;
			  		  
			  JTextField textField_PATRON_ID;
			  JTextField textField_PATRON_ID_BOOKS;
			  JTextField textField_PATRON_FIRST;
			  JTextField textField_PATRON_LAST;
			  JTextField textField_PATRON_FIRST2;
			  JTextField textField_PATRON_LAST2;
			  JTextField textField_BOOKS_OUT;

			  JButton button_ADD_BOOK;
			  JButton button_ADD_PATRON;
			  JButton button_CHECKOUT_BOOK;
			  JButton button_RETURN_BOOK;
			  JButton button_SHOW_BOOKS;
		    
		    checkedIn = "ALL"; //initialize sort by checkedIn is false
			sortByName = "BookID"; //initial sort is bookID
			sortByNamePatrons = "PatronID";
		    
		    label_TITLE = new JLabel("Title:  ", JLabel.TRAILING);
			label_TITLE_CHECKOUT_FORM = new JLabel("Title:  ", JLabel.TRAILING);
			label_AUTHOR_FIRST = new JLabel("Author's First Name:  ", JLabel.TRAILING);
			label_AUTHOR_LAST = new JLabel("Author's Last Name:  ", JLabel.TRAILING );
			label_GENRE = new JLabel("Genre:  ", JLabel.TRAILING);
			label_addBook = new JLabel("Add a Book to the Database", JLabel.CENTER);
			label_addBook.setFont(new Font("Helvetica", Font.PLAIN, 14));
			label_check_ret = new JLabel("Checkout or Return a Book", JLabel.CENTER);
			label_check_ret.setFont(new Font("Helvetica", Font.PLAIN, 14));
			  
			label_PATRON_ID = new JLabel("Patron ID:  ", JLabel.TRAILING);
			label_PATRON_ID_BOOKS = new JLabel("Patron ID:   ", JLabel.TRAILING);
			label_BOOKID = new JLabel("Book ID:   ", JLabel.TRAILING);
			label_PATRON_FIRST = new JLabel("Patron's First Name:  ", JLabel.TRAILING);
			label_PATRON_LAST = new JLabel("Patron's Last Name:  ", JLabel.TRAILING);
			label_PATRON_FIRST2 = new JLabel("Patron's First Name:  ", JLabel.LEADING);
			label_PATRON_LAST2 = new JLabel("Patron's Last Name:  ", JLabel.LEADING);
			label_BOOKS_OUT = new JLabel("Books Checked Out:", JLabel.LEADING);
			label_addPatron = new JLabel("Add a Patron to the Database", JLabel.CENTER);
			label_addPatron.setFont(new Font("Helvetica", Font.PLAIN, 14));
			label_viewBooksOut = new JLabel("View Books Checked Out by Patron", JLabel.CENTER);
			label_viewBooksOut.setFont(new Font("Helvetica", Font.PLAIN, 14));
			JLabel label_blank = new JLabel("");
			
			textField_BOOKID = new JTextField();
			textField_TITLE = new JTextField();
			textField_TITLE_CHECKOUT_FORM = new JTextField();
			textField_AUTHOR_FIRST = new JTextField();
			textField_AUTHOR_LAST = new JTextField();
			textField_GENRE = new JTextField();
						  
			textField_PATRON_ID = new JTextField();
			textField_PATRON_ID_BOOKS = new JTextField();
			textField_PATRON_FIRST = new JTextField();
			textField_PATRON_LAST = new JTextField();
			textField_PATRON_FIRST2 = new JTextField();
			textField_PATRON_LAST2 = new JTextField();
			textField_BOOKS_OUT = new JTextField();

			button_ADD_BOOK = new JButton();
			button_ADD_PATRON = new JButton();
			button_CHECKOUT_BOOK = new JButton(" Checkout ");
			button_RETURN_BOOK = new JButton("  Return  ");
			button_SHOW_BOOKS = new JButton("Show Books");
			
		    button_ADD_BOOK.setText("Add New Book");
		    button_ADD_PATRON.setText("Add New Patron");
						
			JButton buttonID, buttonTitle, buttonAuthorFirst, buttonAuthorLast, buttonGenre, buttonPatronIDBooks;
			
			buttonID = new JButton("Book ID");
			buttonTitle = new JButton("Title");
			buttonAuthorFirst= new JButton("Author's First Name");
			buttonAuthorLast = new JButton("Author's Last Name");
			buttonGenre = new JButton("Genre");
			buttonText = "All Books";
			buttonAvailable = new JButton(buttonText);
			buttonPatronIDBooks = new JButton("Patron with Book");
			JButton[] bookColumns = { buttonID, buttonTitle, buttonAuthorFirst, buttonAuthorLast, buttonGenre, buttonAvailable, buttonPatronIDBooks };
					
			JButton buttonPatronID, buttonPatronFirst, buttonPatronLast;
			
			buttonPatronID = new JButton("Patron ID");
			buttonPatronFirst = new JButton("Patron's First Name");
			buttonPatronLast = new JButton("Patron's Last Name");
			JButton[] patronColumns = { buttonPatronID, buttonPatronFirst, buttonPatronLast };
					      
			//button_CHECKOUT_RETURN_BOOK.setText("Checkout or Return Book");
			
		    // Place the components within the tabbedPane; use BoxLayout
		    					    
		    JTabbedPane tabbedPane = new JTabbedPane();
		    JComponent panelBookTab = new JPanel();
		    panelBookTab.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		    tabbedPane.addTab("Books", panelBookTab);		    

		    JComponent panelPatronTab = new JPanel();
		    panelPatronTab.setLayout(new GridBagLayout());
		    GridBagConstraints cf = new GridBagConstraints();
		    tabbedPane.addTab("Patrons", panelPatronTab);
		    
		    getContentPane().add(tabbedPane);
	       	        
	        panelBookTab.setLayout(new BoxLayout(panelBookTab, BoxLayout.PAGE_AXIS));
	        panelPatronTab.setLayout(new BoxLayout(panelPatronTab, BoxLayout.PAGE_AXIS));
	        
	        libraryLayout = new GridLayout(0, 7, 2, 0);
	        patronLayout = new GridLayout(0, 3, 2, 0);
	               
	        JPanel boxBooksButtons = new JPanel();
	        boxBooksButtons.setLayout(libraryLayout);
	        panelBookTab.add(boxBooksButtons);
	        
	        boxBooksNorth = new JPanel();
	        boxBooksNorth.setLayout(libraryLayout);
	        boxBooksNorth.setPreferredSize(new Dimension(200, 600));
	        panelBookTab.add(new JScrollPane(boxBooksNorth));
	        panelBookTab.add(Box.createRigidArea(new Dimension(0,20)));
	        
	        JPanel boxBooksSouth = new JPanel();
	        boxBooksSouth.setSize(new Dimension(200, 300));
	        boxBooksSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
	        boxBooksSouth.setLayout(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();
	        panelBookTab.add(boxBooksSouth);
	               
	        //initial set up for books grid
	        for (JButton jb : bookColumns) {
	        	boxBooksButtons.add(jb);
	        }
	        
	        redrawBookGrid(db);
	        
	        JPanel boxPatronsButtons = new JPanel();
	        boxPatronsButtons.setLayout(patronLayout);
	        panelPatronTab.add(boxPatronsButtons);
	       	        
	        boxPatronsNorth = new JPanel();
	        boxPatronsNorth.setLayout(patronLayout);
	        boxPatronsNorth.setPreferredSize(new Dimension(200, 200));
	        panelPatronTab.add(new JScrollPane(boxPatronsNorth));
	        panelPatronTab.add(Box.createRigidArea(new Dimension(0,20)));
	        
	        JPanel boxPatronsCenter = new JPanel();
	        boxPatronsCenter.setLayout(new GridBagLayout());
	        boxPatronsCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
	        boxPatronsCenter.setPreferredSize(new Dimension(200, 200));
	        GridBagConstraints cc = new GridBagConstraints();
	        panelPatronTab.add(boxPatronsCenter);
	        
	        boxPatronsSouth = new JPanel();
	        boxPatronsSouth.setSize(new Dimension(200, 300));
	        boxPatronsSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
	        boxPatronsSouth.setLayout(new GridBagLayout());
	        GridBagConstraints cs = new GridBagConstraints();
	        panelPatronTab.add(boxPatronsSouth);
	        
	        JPanel panelShowBooks = new JPanel();
	        panelShowBooks.setSize(new Dimension(100, 100));
	        panelShowBooks.setLayout(new GridLayout(0, 7, 0, 0));
	        boxPatronsSouth.add(new JScrollPane(panelShowBooks));
	        	               
	        //initial setup for patron grid
	        for (JButton jb : patronColumns) {
	        	boxPatronsButtons.add(jb);
	        }
	        
	        redrawPatronGrid(db);
	        	        
	        //GridBag constraints for Books Tabbed Pane--Add Book Form
	        
	        cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.anchor = GridBagConstraints.CENTER;
		    cf.gridx = 1;
		    cf.gridy = 0;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_addBook, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.gridx = 0;
		    cf.gridy = 1;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_TITLE, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 1;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(textField_TITLE, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 0;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_AUTHOR_FIRST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(textField_AUTHOR_FIRST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 0;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_AUTHOR_LAST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(textField_AUTHOR_LAST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 0;
		    cf.gridy = 4;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_GENRE, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 4;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(textField_GENRE, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 5;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(button_ADD_BOOK, cf);
		    
		   
		    //GridBag Constraints for Books Tab--Checkout/Return Form
		    
		    cf.fill = GridBagConstraints.NONE;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 5;
		    //cf.gridwidth = 1;
		    boxBooksSouth.add(button_CHECKOUT_BOOK, cf);
		    
		    cf.fill = GridBagConstraints.NONE;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 5;
		    //cf.gridwidth = 1;
		    boxBooksSouth.add(button_RETURN_BOOK, cf);
		    	    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.anchor = GridBagConstraints.CENTER;
		    cf.gridx = 3;
		    cf.gridy = 0;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_check_ret, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.gridx = 2;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_BOOKID, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(textField_BOOKID, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 2;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_PATRON_ID_BOOKS, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(textField_PATRON_ID_BOOKS, cf);

		    
		    //GridBag constraints for Patrons Tabbed Pane--Add Patron Form
		    		    
		    cc.fill = GridBagConstraints.HORIZONTAL;
		    cc.weightx = 0.15;
		    cc.weighty = 0.5;
		    cc.anchor = GridBagConstraints.CENTER;
		    cc.gridx = 0;
		    cc.gridy = 1;
		    cc.gridwidth = 4;
		    boxPatronsCenter.add(label_addPatron, cc);
		    
		    cc.fill = GridBagConstraints.HORIZONTAL;
		    cc.anchor = GridBagConstraints.LINE_END;
		    cc.weightx = 0.15;
		    cc.weighty = 0.0;
		    cc.gridx = 0;
		    cc.gridy = 3;
		    cc.gridwidth = 1;
		    boxPatronsCenter.add(label_PATRON_FIRST2, cc);

		    cc.fill = GridBagConstraints.HORIZONTAL;
		    cc.anchor = GridBagConstraints.LINE_END;
		    cc.weightx = 0.75;
		    cc.weighty = 0.75;
		    cc.gridx = 0;
		    cc.gridy = 4;
		    cc.gridwidth = 1;
		    boxPatronsCenter.add(textField_PATRON_FIRST2, cc);

		    cc.fill = GridBagConstraints.HORIZONTAL;
		    cc.anchor = GridBagConstraints.LINE_END;
		    cc.weightx = 0.15;
		    cc.weighty = 0.0;
		    cc.gridx = 1;
		    cc.gridy = 3;
		    cc.gridwidth = 1;
		    boxPatronsCenter.add(label_PATRON_LAST2, cc);

		    cc.fill = GridBagConstraints.HORIZONTAL;
		    cc.anchor = GridBagConstraints.LINE_START;
		    cc.weightx = 0.75;
		    cc.weighty = 0.5;
		    cc.gridx = 1;
		    cc.gridy = 4;
		    cc.gridwidth = 1;
		    boxPatronsCenter.add(textField_PATRON_LAST2, cc);
		    
		    cc.fill = GridBagConstraints.HORIZONTAL;
		    cc.anchor = GridBagConstraints.LINE_START;
		    cc.weightx = 0.15;
		    cc.weighty = 0.25;
		    cc.gridx = 3;
		    cc.gridy = 4;
		    cc.gridwidth = 1;
		    boxPatronsCenter.add(button_ADD_PATRON, cc);
		    
		    
		  //GridBag constraints for Patrons Tabbed Pane--Show Books Form
		    
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.anchor = GridBagConstraints.LINE_START;
		    c.weightx = 0.25;
		    c.weighty = 0.25;
		    c.gridx = 0;
		    c.gridy = 1;
		    c.gridwidth = 5;
		    boxPatronsSouth.add(label_viewBooksOut, c);
		    
		    c.fill = GridBagConstraints.BOTH;
		    c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.25;
		    c.weighty = 0.0;
		    c.gridx = 2;
		    c.gridy = 3;
		    c.gridwidth = 1;
		    boxPatronsSouth.add(label_PATRON_ID, c);
		    
		    c.fill = GridBagConstraints.BOTH;
		    c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.5;
		    c.weighty = 0.0;
		    c.gridx = 3;
		    c.gridy = 3;
		    c.gridwidth = 1;
		    boxPatronsSouth.add(textField_PATRON_ID, c);
		    
		    c.fill = GridBagConstraints.BASELINE_LEADING;
		    c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.25;
		    c.weighty = 0.0;
		    c.gridx = 4;
		    c.gridy = 3;
		    c.gridwidth = 1;
		    boxPatronsSouth.add(button_SHOW_BOOKS, c);
		    
		    c.fill = GridBagConstraints.BOTH;
		    c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.5;
		    c.weighty = .75;
		    c.gridx = 0;
		    c.gridy = 4;
		    c.gridwidth = 6;
		    boxPatronsSouth.add(panelShowBooks, c);
		
		    
		    /**
		     * *************************** ACTION LISTENERS *******************************
		     */
		    
			
		    /***************************** BOOKS TAB ACTION LISTENERS ********************/   
		    
		    /**
		     * 	ADD_BOOK button
		     * 	
		     * 	Show dialog box
		     *  Get information from textFields and send to database for query
		     *  redraw book grid
		     */
		    button_ADD_BOOK.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		        	
		    	   //dialog box for the user to verify what book information was just added
		        	JOptionPane.showMessageDialog(LibraryFrame.this,
		                                        new String[] {
		                "Adding the following book:",
		                "Title: [" + textField_TITLE.getText() + "]",
		                "Author's First Name: [" + textField_AUTHOR_FIRST.getText() + "]",
		                "Author's Last Name: [" + textField_AUTHOR_LAST.getText() + "]",
		                "Genre: [" + textField_GENRE.getText() + "]" });
		          
		             Statement stmt = null;
		          try {
		        	  
		        	  //get text from textFields and send to database query
		        	  db.addBook(
							 textField_TITLE.getText(),
							 textField_AUTHOR_FIRST.getText(),
							 textField_AUTHOR_LAST.getText(),
							 textField_GENRE.getText().trim()
					  		);
		        	  
		        	  //reset text fields
		        	  textField_TITLE.setText("");
		        	  textField_AUTHOR_FIRST.setText("");
		        	  textField_AUTHOR_LAST.setText("");
		        	  textField_GENRE.setText("");
					  
					  redrawBookGrid(db);					//redraw book grid
					 
		          } catch (SQLException sqle) {
		            displaySQLExceptionDialog(sqle);
		          }
		          finally {
		        	  if (stmt != null) { try {
						stmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} }
		          }
		        }
		      });
		    //end Add Book Action Listener
		    
		    /**
		     *  CHECKOUT_BOOK button
		     *  
		     *  Show dialog box
		     *  get text from textField and send to database query
		     */
		    button_CHECKOUT_BOOK.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	 //dialog box for the user to verify what book was checked out to whom
			    	   JOptionPane.showMessageDialog(LibraryFrame.this,
                               new String[] {
                            		   	"Book is Checked Out"
                            		   	  });
                            		   				    	   
			    	   try {
			    		   //query database
			    		   db.checkOutBookByIDs( 
								textField_BOOKID.getText(),
								textField_PATRON_ID_BOOKS.getText()
								);
						
			    		   //reset textFields
			    		   textField_BOOKID.setText("");
			    		   textField_PATRON_ID_BOOKS.setText("");
							
			    		   redrawBookGrid(db);							   //redraw grid
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
		   });

		    /**
		     *  RETURN_BOOK Button
		     *  Makes dialog box indicating to user book has been returned
		     *  gets bookID from textField
		     *  queries database based on bookID
		     */
		    button_RETURN_BOOK.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {					
			    	   JOptionPane.showMessageDialog(LibraryFrame.this,				//show dialog box
                               new String[] {
                            		   	"Book has been returned"
                            		   	  });
			    	   
		    try {
						db.checkInBookByIDs( 							//query database with text in textField
								textField_BOOKID.getText()
								);
		
								//reset textFields
								textField_BOOKID.setText("");
								textField_PATRON_ID_BOOKS.setText("");
						
						 redrawBookGrid(db);							//redraw grid based on new query
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
		    
	      });
		  //end Return Book Action Listener
		    
		    /************************** BOOK GRID SORT BY BUTTONS *************************/
		    
		    /**
		     * Sort by BookID button
		     * 
		     * change sortByName for the query
		     * redraw the grid based on query
		     */
		    buttonID.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "BookID";		//changes ORDER BY to BookID row in database						
			    		   redrawBookGrid(db);			//redraw grid based on query						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
		    
	      });
		    
		    /**
		     * Sort by Book Title button
		     * 
		     * change sortByName for the query
		     * redraw the grid based on query
		     */
		    buttonTitle.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "Title";			//changes ORDER BY to Title row in database						
			    		   redrawBookGrid(db);				//redraw grid based on query						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
		    
	      });
		    
		    /**
		     * Sort by Author's First Name button
		     * 
		     * change sortByName for the query
		     * redraw the grid based on query
		     */
		    buttonAuthorFirst.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "AuthorFirst";		//changes ORDER BY to AuthorFirst row in database						
						 redrawBookGrid(db);				//redraw grid based on query						
			    	   } catch (SQLException e1) {
						e1.printStackTrace();
			    	   }
			       }		    
		    });
		    
		    /**
		     * Sort by Author's Last Name button
		     * 
		     * change sortByName for the query
		     * redraw the grid based on query
		     */
		    buttonAuthorLast.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "AuthorLast"; 		//changes ORDER BY to AuthorLast row in database
						
			    		   redrawBookGrid(db);				//redraw grid based on query
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
		    
	      });
		    
		    /**
		     * Sort by Genre button
		     * 
		     * change sortByName for the query
		     * redraw the grid based on query
		     */
		    buttonGenre.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "Genre";			//changes ORDER BY to Genre row in database
						
			    		   redrawBookGrid(db);				//redraw grid based on query
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
	     });
		    
		    /**
		     * Sort by checkedOut button
		     * 
		     * change sortByName for the query
		     * redraw the grid based on query
		     */
		    buttonAvailable.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	 
					try { 
			    	   toggleSortByCheckedInStatus(); 		//toggles checked in to next status
			    	   sortByName = "CheckedOut";			//changes ORDER BY to CheckedOut row in database
			    	      						
			    	   redrawBookGrid(db);					//redraw grid based on query
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
	      });
		    
		    /**
		     * Sort by PatronID button
		     * 
		     * draws grid sorted by PatronID
		     * change sortByName for the query
		     * redraw the grid based on query
		     */
		    buttonPatronIDBooks.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	 
					try { 
						sortByName = "PatronID";
						    	      						
						 redrawBookGrid(db);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
	      });

		    /**********************	PATRONS TAB ACTION LISTENERS	****************
		     * 
		     ************************************************************************/
		    
		    
		    /**
		     * 	****** ADD_PATRON Button
		     *  Gets text from text fields
		     *  Shows dialog box with the information to send
		     *  sends information to database to add patron
		     */
		    button_ADD_PATRON.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		        	//dialog box for the user to verify what name was added
		        	JOptionPane.showMessageDialog(LibraryFrame.this,
                            new String[] {
                         		   	"Adding the following patron:",
                         		   	"Patron's First Name: [" + textField_PATRON_FIRST2.getText() + "]",
                         		   	"Patron's Last Name: [" + textField_PATRON_LAST2.getText() + "]"  });
                         		   				    	   
			    	   try {
//			    		   sortByName = "Patron";
			    		   
			    		   //send first name and last name to database to add patron
			    		   db.addPatron( 
								textField_PATRON_FIRST2.getText(),
								textField_PATRON_LAST2.getText()
								   );
						
			    		   //reset textField values
			    		   textField_PATRON_FIRST2.setText("");
			    		   textField_PATRON_LAST2.setText("");
						
						redrawPatronGrid(db);		//redraw the patrons grid
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		        }
		      });
		    
		    /*********************** PATRON GRID SORT BY BUTTONS *********************/
		    
		    /**
		     * 	****** PatronID Button (sort by PatronID button)
		     * 	changes sort value
		     *  makes a new query
		     *  displays the results
		     */
		    buttonPatronID.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByNamePatrons = "PatronID";				//set query sort value to PatronID
						
						 redrawPatronGrid(db);							//redraw the patrons grid with new query
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
	     });
		    
		    /**
		     * 	****** PatronFirst Button (sort by Patrons first name button)
		     * 	changes sort value
		     *  makes a new query
		     *  displays the results
		     */
		    buttonPatronFirst.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByNamePatrons = "FName";					//set query sort value to FName
						
						 redrawPatronGrid(db);							//redraw the patrons grid with new query
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		     }
	     });
		    
		   
		    /**
		     * 	****** PatronLast Button (sort by Patrons last name button)
		     * 	changes sort value
		     *  makes a new query
		     *  displays the results
		     */
		    buttonPatronLast.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByNamePatrons = "LName";					//set query sort value to LName
						
						 redrawPatronGrid(db);							//redraw the patrons grid with new query
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		     }
	     });
		    
		    /**
		     * 	****** SHOW_BOOKS Button (sort by Patrons with Books button)
		     * 	changes sort value
		     *  makes a new query
		     *  displays the results in the PATRONS WITH BOOKS grid
		     */
		    button_SHOW_BOOKS.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	                         		   				    	   
			    	   try {			    		   
			    		    String patID = textField_PATRON_ID.getText();							//get text from patron ID field
			    		    String[][] checkedOutBooks = db.BooksCheckedOutByPatronID(patID);		//query database based on patronID
			    		    
			    		    panelShowBooks.removeAll();												//clear the books panel
			    		  				    		    
			    		    for (int i = 0; i < checkedOutBooks.length; i++) {						//re-populate the panel with new data	
			    		    	for (int j = 0; j < checkedOutBooks[0].length; j++){
			    		    		    		
			    		    		JLabel labelData = new JLabel();
			    					labelData.setBorder(BorderFactory.createLineBorder(Color.black));
			    					labelData.setText(checkedOutBooks[i][j]);
			    					panelShowBooks.add(labelData);
			    				}
			    			}
			    		    	panelShowBooks.revalidate();
			    		    	panelShowBooks.repaint();
			    		    												
												
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		        }
		      });
	
		  
		    setVisible(true);		  		  
} //end class  
	
		  /**
		   * Changes the status of checkedIn selection
		   * Toggles through 3 settings in the Books table button for checked in
		   */
		  private void toggleSortByCheckedInStatus() {
							
				if (checkedIn.equals("ALL")){				//change checkedIn button from ALL to IN
					checkedIn = "IN";
					buttonText = "Checked In Books";
				} else if (checkedIn.equals("IN")){			//change checkedIn button from IN to OUT
					checkedIn = "OUT";
					buttonText = "Checked Out Books";
				}
				else{										//change checkedIn button from OUT to ALL
						checkedIn = "ALL";
						buttonText = "All Books";
					}	
				buttonAvailable.setText(buttonText);		//set the button text to the new value
			}  
		  
		  /**
		   * Redraws the Patron Grid to reflect new query
		   * sortByNamePatrons is the value of the how the table is to be sorted
		   * @param db
		   * @throws SQLException
		   */
		  private void redrawPatronGrid(DatabaseControl db) throws SQLException {
		    	String[][] rowData;
		    	rowData = db.makePatronGrid(sortByNamePatrons);			//retrieve String[][] based on sort order indicated
		    	boxPatronsNorth.removeAll();							//remove current values written to the table
		
		    	for (int i = 0; i < rowData.length; i++) {				//re-populate the table with values from rowData
		    		for (int j = 0; j < rowData[i].length; j++) {
		    			JLabel labelData = new JLabel();
		    			labelData.setBorder(BorderFactory.createLineBorder(Color.black));
		    			labelData.setText(rowData[i][j]);
		    			boxPatronsNorth.add(labelData);
			}
		}
		    	boxPatronsNorth.revalidate();							//redraw values in boxPatronsNorth
	      		boxPatronsNorth.repaint();
	}

		  /**
		   * Display the SQLException in a dialog box
		   * @param e - SQL exception thrown
		   */
		  private void displaySQLExceptionDialog(SQLException e) {
		    JOptionPane.showMessageDialog(
		      LibraryFrame.this,
		      new String[] {
		        e.getClass().getName() + ": ",
		        e.getMessage()
		      }
		    );
		  }

		  public void redrawBookGrid(DatabaseControl db) throws SQLException {
				String[][] rowData;
				  rowData = db.makeBookGrid(checkedIn, sortByName);
				  boxBooksNorth.removeAll();
				    
				  for (int i = 0; i < rowData.length; i++) {
				    	for (int j = 0; j < rowData[i].length; j++) {
				    		JLabel labelData = new JLabel();
				    		labelData.setBorder(BorderFactory.createLineBorder(Color.black));
				    		labelData.setText(rowData[i][j]);
				    		boxBooksNorth.add(labelData);
				    	}
				    }
				  	boxBooksNorth.revalidate();
		      		boxBooksNorth.repaint();
			}

		  		  
}