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
		Connection connection;
		static char selection;
		private DatabaseControl db = new DatabaseControl("Library_07");	
		//private static final String connectionURL="jdbc:derby:Library_07;create=true";		
		boolean checkedIn;
		String sortByName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryFrame frame = new LibraryFrame();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	  
		  public LibraryFrame() throws SQLException {
			  
		    super("Library System"); // Set window title
		    	
		    DatabaseControl db = new DatabaseControl("Library_07");
		    
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
			  JLabel label_BOOKS_OUT;
			  			 
			  JTextField textField_TITLE;
			  JTextField textField_TITLE_CHECKOUT_FORM;
			  JTextField textField_AUTHOR_FIRST;
			  JTextField textField_AUTHOR_LAST;
			  JTextField textField_GENRE;
			  		  
			  JTextField textField_PATRON_ID;
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
		    
		    checkedIn = false; //initialize sort by checkedIn is false
			sortByName = "BookID"; //initial sort is bookID
		    
		    label_TITLE = new JLabel("Title:  ", JLabel.TRAILING);
			label_TITLE_CHECKOUT_FORM = new JLabel("Title:  ", JLabel.TRAILING);
			label_AUTHOR_FIRST = new JLabel("Author's First Name:  ", JLabel.TRAILING);
			label_AUTHOR_LAST = new JLabel("Author's Last Name:  ", JLabel.TRAILING );
			label_GENRE = new JLabel("Genre:  ", JLabel.TRAILING);
			label_addBook = new JLabel("Add a Book to the Database", JLabel.CENTER);
			label_addBook.setFont(new Font("Helvetica", Font.PLAIN, 14));
			label_check_ret = new JLabel("Checkout or Return a Book", JLabel.CENTER);
			label_check_ret.setFont(new Font("Helvetica", Font.PLAIN, 14));
			  
			label_PATRON_ID = new JLabel("Patron ID:", JLabel.LEADING);
			//label_PATRON_ID_BOOKS = new JLabel("Patron ID:   ", JLabel.TRAILING);
			label_PATRON_FIRST = new JLabel("Patron's First Name:  ", JLabel.TRAILING);
			label_PATRON_LAST = new JLabel("Patron's Last Name:  ", JLabel.TRAILING);
			label_PATRON_FIRST2 = new JLabel("Patron's First Name:  ", JLabel.CENTER);
			label_PATRON_LAST2 = new JLabel("Patron's Last Name:  ", JLabel.CENTER);
			label_BOOKS_OUT = new JLabel("Books Checked Out:", JLabel.LEADING);
			label_addPatron = new JLabel("Add a Patron to the Database", JLabel.CENTER);
			label_addPatron.setFont(new Font("Helvetica", Font.PLAIN, 14));
			label_viewBooksOut = new JLabel("View Books Checked Out by Patron", JLabel.CENTER);
			label_viewBooksOut.setFont(new Font("Helvetica", Font.PLAIN, 14));
			JLabel label_blank = new JLabel("");
			
			textField_TITLE = new JTextField();
			textField_TITLE_CHECKOUT_FORM = new JTextField();
			textField_AUTHOR_FIRST = new JTextField();
			textField_AUTHOR_LAST = new JTextField();
			textField_GENRE = new JTextField();
						  
			textField_PATRON_ID = new JTextField();
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
//			button_UPDATE_BOOKS_DATABASE.setText("Update Books Database");
//			button_UPDATE_PATRONS_DATABASE.setText("Update Patrons Database");
						
			JButton buttonID, buttonTitle, buttonAuthorFirst, buttonAuthorLast, buttonGenre, buttonPatronIDBooks;
			
			buttonID = new JButton("Book ID");
			buttonTitle = new JButton("Title");
			buttonAuthorFirst= new JButton("Author's First Name");
			buttonAuthorLast = new JButton("Author's Last Name");
			buttonGenre = new JButton("Genre");
			buttonAvailable = new JButton("Book Status: All Books");
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
		    
		    //Add the tabbed pane to this panel.
	        getContentPane().add(tabbedPane);
	        //The following line enables using scrolling tabs.
	        //tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	        
	        panelBookTab.setLayout(new BoxLayout(panelBookTab, BoxLayout.PAGE_AXIS));
	        panelPatronTab.setLayout(new BoxLayout(panelPatronTab, BoxLayout.PAGE_AXIS));
	        
	        libraryLayout = new GridLayout(0, 7, 2, 0);
	        libraryLayout.preferredLayoutSize(panelBookTab);
	        patronLayout = new GridLayout(0, 3, 2, 0);
	               
	        JPanel boxBooksButtons = new JPanel();
	        //boxBooksButtons.setPreferredSize(new Dimension(30, 400));
	        boxBooksButtons.setLayout(libraryLayout);
	        panelBookTab.add(boxBooksButtons);
	        
	        boxBooksNorth = new JPanel();
	        boxBooksNorth.setLayout(libraryLayout);
	        boxBooksNorth.setPreferredSize(new Dimension(200, 400));
	        panelBookTab.add(new JScrollPane(boxBooksNorth));
	        panelBookTab.add(Box.createRigidArea(new Dimension(0,20)));
	        
	        JPanel boxBooksSouth = new JPanel();
	        boxBooksSouth.setSize(new Dimension(200, 300));
	        boxBooksSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
	        boxBooksSouth.setLayout(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();
	        panelBookTab.add(boxBooksSouth);
	               
	        for (JButton jb : bookColumns) {
	        	boxBooksButtons.add(jb);
	        }
	        
	        redrawBookGrid(db);
	        
//	        String[][] rowData;
//	        rowData = db.showAllFromQuery("select * from Books");
////	        rowData = db.makeBookGrid(checkedIn, sortByName);		//mike is making this method
//	        
//	        for (int i = 0; i < rowData.length; i++) {
//	        	for (int j = 0; j < rowData[i].length; j++) {
//	        		JLabel labelData = new JLabel();
//	        		labelData.setBorder(BorderFactory.createLineBorder(Color.black));
//	        		labelData.setText(rowData[i][j]);
//	        		boxBooksNorth.add(labelData);
//	        	}
//	        }
	        
	        JPanel boxPatronsButtons = new JPanel();
	        //boxBooksButtons.setPreferredSize(new Dimension(30, 400));
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
	        boxPatronsCenter.setPreferredSize(new Dimension(200, 100));
	        GridBagConstraints cc = new GridBagConstraints();
	        panelPatronTab.add(boxPatronsCenter);
	        
	        boxPatronsSouth = new JPanel();
	        boxPatronsSouth.setSize(new Dimension(200, 300));
	        boxPatronsSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
	        boxPatronsSouth.setLayout(new GridBagLayout());
	        GridBagConstraints cs = new GridBagConstraints();
	        panelPatronTab.add(boxPatronsSouth);
	               
	        for (JButton jb : patronColumns) {
	        	boxPatronsButtons.add(jb);
	        }
	        
	        redrawPatronGrid(db);
		   				  
		    //GridBag constraints for Books Tabbed Pane
	        
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
		    cf.gridy = 1;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_TITLE_CHECKOUT_FORM, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 1;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(textField_TITLE_CHECKOUT_FORM, cf);
		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 2;
//		    cf.gridy = 2;
//		    cf.gridwidth = 1;
//		    boxBooksSouth.add(label_PATRON_ID_BOOKS, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 3;
//		    cf.gridy = 2;
//		    cf.gridwidth = 1;
//		    boxBooksSouth.add(textField_PATRON_ID_BOOKS, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 2;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_PATRON_FIRST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(textField_PATRON_FIRST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 2;
		    cf.gridy = 4 ;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(label_PATRON_LAST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 4;
		    cf.gridwidth = 1;
		    boxBooksSouth.add(textField_PATRON_LAST, cf);
	    
		 
		    //GridBag constraints for Patrons Tabbed Pane
		    		    
		    cc.fill = GridBagConstraints.HORIZONTAL;
		    cc.weightx = 0.15;
		    cc.weighty = 0.25;
		    cc.anchor = GridBagConstraints.CENTER;
		    cc.gridx = 1;
		    cc.gridy = 1;
		    cc.gridwidth = 1;
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
		    cc.weightx = 0.15;
		    cc.weighty = 0.25;
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
		    cc.weightx = 0.15;
		    cc.weighty = 0.25;
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
		    c.weightx = 0.1;
		    c.weighty = 0.75;
		    c.gridx = 0;
		    c.gridy = 2;
		    c.gridwidth = 1;
		    boxPatronsSouth.add(label_PATRON_ID, c);
		    
		    c.fill = GridBagConstraints.BOTH;
		    c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.1;
		    c.weighty = 0.75;
		    c.gridx = 0;
		    c.gridy = 3;
		    c.gridwidth = 1;
		    boxPatronsSouth.add(textField_PATRON_ID, c);
		    
		    c.fill = GridBagConstraints.BOTH;
		    c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.5;
		    c.weighty = .75;
		    c.gridx = 1;
		    c.gridy = 2;
		    c.gridwidth = 3;
		    boxPatronsSouth.add(label_BOOKS_OUT, c);
		    
		    c.fill = GridBagConstraints.BOTH;
		    c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.5;
		    c.weighty = .75;
		    c.gridx = 1;
		    c.gridy = 3;
		    c.gridwidth = 3;
		    boxPatronsSouth.add(textField_BOOKS_OUT, c);
		    
		    c.fill = GridBagConstraints.BOTH;
		    c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.2;
		    c.weighty = .75;
		    c.gridx = 4;
		    c.gridy = 3;
		    c.gridwidth = 1;
		    boxPatronsSouth.add(button_SHOW_BOOKS, c);
		     

		    // Add listeners for the buttons in the application
			    
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
        	  
		        	  db.addBook(
							  textField_TITLE.getText(),
                              textField_AUTHOR_FIRST.getText(),
                              textField_AUTHOR_LAST.getText(),
                              textField_GENRE.getText().trim()
					  		);
		        	  
		        	  textField_TITLE.setText("");
		        	  textField_AUTHOR_FIRST.setText("");
		        	  textField_AUTHOR_LAST.setText("");
		        	  textField_GENRE.setText("");
					  
					  redrawBookGrid(db);
					 
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
		    
		    button_CHECKOUT_BOOK.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	 //dialog box for the user to verify what book was checked out to whom
			    	   JOptionPane.showMessageDialog(LibraryFrame.this,
                               new String[] {
                            		   	"Checking out this book:",
                            		   	"Title: [" + textField_TITLE_CHECKOUT_FORM.getText() + "]", 
                            		   	"Patron's First Name: [" + textField_PATRON_FIRST.getText() + "]",
                            		   	"Patron's Last Name: [" + textField_PATRON_LAST.getText() + "]"  });
                            		   				    	   
			    	   try {
						db.checkOutBookByNames( 
								textField_TITLE_CHECKOUT_FORM.getText(),
								textField_PATRON_FIRST.getText(),
								textField_PATRON_LAST.getText()
								   );
						
						textField_TITLE_CHECKOUT_FORM.setText("");
						textField_PATRON_FIRST.setText("");
						textField_PATRON_LAST.setText("");
						
						 redrawBookGrid(db);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		     }
		   });
		    
		    button_RETURN_BOOK.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
						db.checkOutBookByNames( 
								   textField_TITLE.getText(),
								   textField_PATRON_FIRST.getText(),
								   textField_PATRON_LAST.getText()
								   );
						
						textField_TITLE.setText("");
						textField_PATRON_FIRST.setText("");
						textField_PATRON_LAST.setText("");
						
						 redrawBookGrid(db);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		     }
		    
	      });
		    
		    buttonID.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "BookID";
						
						 redrawBookGrid(db);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		     }
		    
	      });
		    
		    buttonTitle.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "Title";
						
						 redrawBookGrid(db);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		     }
		    
	      });
		    
		    buttonAuthorFirst.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "AuthorFirst";
						
						 redrawBookGrid(db);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		     }
		    
	      });
		    
		    buttonAuthorLast.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "AuthorLast";
						
						 redrawBookGrid(db);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		     }
		    
	      });
		    
		    buttonGenre.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	   
			    	   try {
			    		   sortByName = "Genre";
						
						 redrawBookGrid(db);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		     }
	     });
		    
		    buttonAvailable.addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
			    	 
						//bookGrid.add(makeBookGrid(checkedIn, sortByName));
			    	   try { 
			    	   checkedIn = !checkedIn; //toggle boolean value for show only checked in
			    	   sortByName = "CheckedOut";
			    	   if (buttonAvailable.getText() == "Book Status: All Books") {
			    		   buttonAvailable.setText("Available Books");
			    	   } else {
			    		   if (buttonAvailable.getText() == "Available Books"){
			    			   buttonAvailable.setText("Book Status: All Books");
			    		   }
			    	   }
						
						 redrawBookGrid(db);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		     }
	      });
		    
		    button_ADD_PATRON.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		        	//dialog box for the user to verify what name was added
		        	JOptionPane.showMessageDialog(LibraryFrame.this,
                            new String[] {
                         		   	"Adding the following patron:",
                         		   	"Patron's First Name: [" + textField_PATRON_FIRST2.getText() + "]",
                         		   	"Patron's Last Name: [" + textField_PATRON_LAST2.getText() + "]"  });
                         		   				    	   
			    	   try {
			    		   sortByName = "Patron";
			    		   
			    		   db.addPatron( 
								textField_PATRON_FIRST2.getText(),
								textField_PATRON_LAST2.getText()
								   );
						
						textField_PATRON_FIRST2.setText("");
						textField_PATRON_LAST2.setText("");
						
						redrawPatronGrid(db);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		      });
		    
		    button_SHOW_BOOKS.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	                         		   				    	   
//			    	   try {
//			    		   sortByName = "Title";
//			    		   
//			    		  						
//			    		   textField_BOOKS_OUT.setText("");
//												
//												
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
		        }
		      });
	
		  
		  
		  
		  
		  
		  
} //end class  
	
		    private void redrawPatronGrid(DatabaseControl db) throws SQLException {
		    	String[][] rowData;
		    	rowData = db.showAllFromQuery("select * from Patrons");
//	        	rowData = db.makeBookGrid(checkedIn, sortByName);		//mike is making this method
		    	boxPatronsNorth.removeAll();
		
		    	for (int i = 0; i < rowData.length; i++) {
		    		for (int j = 0; j < rowData[i].length; j++) {
		    			JLabel labelData = new JLabel();
		    			labelData.setBorder(BorderFactory.createLineBorder(Color.black));
		    			labelData.setText(rowData[i][j]);
		    			boxPatronsNorth.add(labelData);
			}
		}
	}

		  private void displaySQLExceptionDialog(SQLException e) {

		    // Display the SQLException in a dialog box
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
				  rowData = db.showAllFromQuery("select * from Books");
				  boxBooksNorth.removeAll();
				    
				  for (int i = 0; i < rowData.length; i++) {
				    	for (int j = 0; j < rowData[i].length; j++) {
				    		JLabel labelData = new JLabel();
				    		labelData.setBorder(BorderFactory.createLineBorder(Color.black));
				    		labelData.setText(rowData[i][j]);
				    		boxBooksNorth.add(labelData);
				    	}
				    }
			}
	      
		  }	  
		 
//		  protected JComponent makeTextPanel(String text) {
//		        JPanel panel = new JPanel(false);
//		        JLabel filler = new JLabel(text);
//		        filler.setHorizontalAlignment(JLabel.CENTER);
//		        panel.setLayout(new GridLayout(1, 1));
//		        panel.add(filler);
//		        return panel;
//		    }     
//
//			      // In MySQL, to disable auto-commit, set the property relaxAutoCommit to
//			      // true in the connection URL.
//
////			      if (this.settings.dbms.equals("mysql")) {
////			        crs.setUrl(settings.urlString + "?relaxAutoCommit=true");
////			      } else {
////			        crs.setUrl(settings.urlString);
////			      }
//
//			      // Regardless of the query, fetch the contents of PATRONS
//
//			      //crs2.setCommand("select PATRON_ID, PATRON_FIRST, PATRON_LAST, BOOKS_OUT from PATRONS");
//			      //crs2.execute();
//
//			    } catch (SQLException e) {
//			      //JDBCTutorialUtilities.printSQLException(e);
//			    }
//			    return crs2;
//			  }

		  
		  		  		  
		  

		
		
		
	

	
	


