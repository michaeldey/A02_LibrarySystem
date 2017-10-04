package librarySystem;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class LibraryFrame extends JFrame  {

		private JPanel contentPane;
		
		GridLayout libraryLayout;
		JPanel cardPanel;
		JPanel firstCard;
		JPanel secondCard;
		private JPanel tablePanel;
		private JPanel buttonPanel;
		private JPanel checkoutCard;
		private JPanel returnCard;
		private JPanel addBookCard;
		private JPanel addPatronCard;
		static char selection;
		Connection connection;
		static int currentBookID=0, currentPatronID=0;
		
		private static final String connectionURL="jdbc:derby:Library_07;create=true";

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
	
		JTable tableBooks; // The table for displaying data
		JTable tablePatrons; 

		  JLabel label_BOOK_ID;
		  JLabel label_TITLE;
		  JLabel label_TITLE2;
		  JLabel label_AUTHOR_FIRST;
		  JLabel label_AUTHOR_LAST;
		  JLabel label_GENRE;
		  JLabel label_CHECKED_OUT;
		  JLabel label_PATRON_ID_BOOKS;
		  JLabel addBook;
		  JLabel check_ret;
		  		  
		  JLabel label_PATRON_ID;
		  JLabel label_PATRON_FIRST;
		  JLabel label_PATRON_LAST;
		  JLabel label_PATRON_FIRST2;
		  JLabel label_PATRON_LAST2;
		  JLabel label_BOOKS_OUT;
		  JLabel addPatron;

		  JTextField textField_BOOK_ID;
		  JTextField textField_TITLE;
		  JTextField textField_TITLE2;
		  JTextField textField_AUTHOR_FIRST;
		  JTextField textField_AUTHOR_LAST;
		  JTextField textField_GENRE;
		  JTextField textField_CHECKED_OUT;
		  JTextField textField_PATRON_ID_BOOKS;
		  
		  JTextField textField_PATRON_ID;
		  JTextField textField_PATRON_FIRST;
		  JTextField textField_PATRON_LAST;
		  JTextField textField_PATRON_FIRST2;
		  JTextField textField_PATRON_LAST2;
		  JTextField textField_BOOKS_OUT;

		  JButton button_ADD_BOOK;
		  JButton button_ADD_PATRON;
		  JButton button_UPDATE_BOOKS_DATABASE;
		  JButton button_UPDATE_PATRONS_DATABASE;
		  JButton button_LIST_ALL_BOOKS;
		  JComboBox comboBox_LIST_AVAILABLE_BOOKS;
		  JButton button_LIST_PATRONS;
		  JButton button_CHECKOUT_RETURN_BOOK;
		 
		  
		  public LibraryFrame() throws SQLException {
			  
		    super("Library System"); // Set window title
		    	
		    // Close connections exit the application when the user
		    // closes the window

//		    addWindowListener(new WindowAdapter() {
//		        public void windowClosing(WindowEvent e) {

////		          try {
////		            connection.close();
////		          } catch (SQLException sqle) {
//////		        	System.out.println(e.getMessage());
//////		  			e.printStackTrace();
////		          }
////		          System.exit(0);
////		        }
//		     /// });
//		    
		    

		    // Initialize and lay out window controls
//
//		    ResultSet myBooksResultSet = getContentsOfBooksTable();
//		    myBooksTableModel = new BooksTableModel(myBooksResultSet);
//		    myBooksTableModel.addTableModelListener(this);
//		    
//		    ResultSet myPatronsResultSet = getContentsOfPatronsTable();
//		    myPatronsTableModel = new PatronsTableModel(myPatronsResultSet);
//		    myPatronsTableModel.addEventHandlersToRowSet(this);

//		    tableBooks = new JTable(); // Displays the table
//		    tableBooks.setPreferredScrollableViewportSize(new Dimension(450, 200));
//		    tableBooks.setModel(myBooksTableModel);
//		    tablePatrons = new JTable();
//		    tablePatrons.setModel(myPatronsTableModel);
		   
		    label_BOOK_ID = new JLabel("Book ID:  ", JLabel.TRAILING);
			label_TITLE = new JLabel("Title:  ", JLabel.TRAILING);
			label_TITLE2 = new JLabel("Title:  ", JLabel.TRAILING);
			label_AUTHOR_FIRST = new JLabel("Author's First Name:  ", JLabel.TRAILING);
			label_AUTHOR_LAST = new JLabel("Author's Last Name:  ", JLabel.TRAILING );
			label_GENRE = new JLabel("Genre:  ", JLabel.TRAILING);
			label_CHECKED_OUT = new JLabel();
			addBook = new JLabel("Add a Book to the Database", JLabel.CENTER);
			check_ret = new JLabel("Checkout or Return a Book", JLabel.CENTER);
			  
			label_PATRON_ID = new JLabel("Patron ID:  ", JLabel.TRAILING);
			label_PATRON_ID_BOOKS = new JLabel("Patron ID:   ", JLabel.TRAILING);
			label_PATRON_FIRST = new JLabel("Patron's First Name:  ", JLabel.TRAILING);
			label_PATRON_LAST = new JLabel("Patron's Last Name:  ", JLabel.TRAILING);
			label_PATRON_FIRST2 = new JLabel("Patron's First Name:  ", JLabel.TRAILING);
			label_PATRON_LAST2 = new JLabel("Patron's Last Name:  ", JLabel.TRAILING);
			label_BOOKS_OUT = new JLabel("Books Checked Out:  ", JLabel.TRAILING);
			addPatron = new JLabel("Add a Patron to the Database", JLabel.CENTER);
			
			textField_BOOK_ID = new JTextField();
			textField_TITLE = new JTextField();
			textField_TITLE2 = new JTextField();
			textField_AUTHOR_FIRST = new JTextField();
			textField_AUTHOR_LAST = new JTextField();
			textField_GENRE = new JTextField();
			textField_CHECKED_OUT = new JTextField();
			  
			textField_PATRON_ID = new JTextField();
			textField_PATRON_ID_BOOKS = new JTextField();
			textField_PATRON_FIRST = new JTextField();
			textField_PATRON_LAST = new JTextField();
			textField_PATRON_FIRST2 = new JTextField();
			textField_PATRON_LAST2 = new JTextField();
			textField_BOOKS_OUT = new JTextField();

			button_ADD_BOOK = new JButton();
			button_ADD_PATRON = new JButton();
			button_UPDATE_BOOKS_DATABASE = new JButton();
			button_UPDATE_PATRONS_DATABASE = new JButton();
			button_LIST_ALL_BOOKS = new JButton();
			comboBox_LIST_AVAILABLE_BOOKS = new JComboBox();
			button_LIST_PATRONS = new JButton();
			button_CHECKOUT_RETURN_BOOK = new JButton();
			
		    button_ADD_BOOK.setText("Add New Book");
		    button_ADD_PATRON.setText("Add New Patron");
			button_UPDATE_BOOKS_DATABASE.setText("Update Books Database");
			button_UPDATE_PATRONS_DATABASE.setText("Update Patrons Database");
			button_LIST_ALL_BOOKS.setText("List All Books");
			
			JButton buttonID, buttonTitle, buttonAuthorFirst, buttonAuthorLast, buttonGenre, buttonCheckedOut, buttonPatronIDBooks;
			
			buttonID = new JButton();
			buttonTitle = new JButton();
			buttonAuthorFirst= new JButton();
			buttonAuthorLast = new JButton();
			buttonGenre = new JButton();
			buttonCheckedOut = new JButton();
			buttonPatronIDBooks = new JButton();
			JButton[] bookColumns = { buttonID, buttonTitle, buttonAuthorFirst, buttonAuthorLast, buttonGenre, buttonCheckedOut, buttonPatronIDBooks };
			
			buttonID.setText("Book ID");
			buttonTitle.setText("Title");
			buttonAuthorFirst.setText("Author's First Name");
			buttonAuthorLast.setText("Author's Last Name");
			buttonGenre.setText("Genre");
			buttonCheckedOut.setText("Checked Out?");
			buttonPatronIDBooks.setText("Patron with Book");
			
			
			
//			String[] comboBoxItems = { "Available Books By Title", "Available Books By Author", "Available Books By Genre" };
//			comboBox_LIST_AVAILABLE_BOOKS = new JComboBox(comboBoxItems);
//			comboBox_LIST_AVAILABLE_BOOKS.setEditable(false);
			      
			button_CHECKOUT_RETURN_BOOK.setText("Checkout or Return Book");
			
		    // Place the components within the tabbedPane; use BoxLayout
		    					    
		    JTabbedPane tabbedPane = new JTabbedPane();
		    JComponent panelBookTab = new JPanel();
		    panelBookTab.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		    tabbedPane.addTab("Books", panelBookTab);		    

		    JComponent panelPatronsTab = new JPanel();
		    panelPatronsTab.setLayout(new GridBagLayout());
		    GridBagConstraints c2 = new GridBagConstraints();
		    tabbedPane.addTab("Patrons", panelPatronsTab);
		    
		    //Add the tabbed pane to this panel.
	        getContentPane().add(tabbedPane);
	        //The following line enables using scrolling tabs.
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	        panelBookTab.setLayout(new BoxLayout(panelBookTab, BoxLayout.PAGE_AXIS));
	        
	        libraryLayout = new GridLayout(0, 7); 
	        
	        JPanel panelNorth = new JPanel();
	        panelNorth.setLayout(libraryLayout);
	        panelNorth.setSize(new Dimension(500, 500));
	        panelBookTab.add(panelNorth);
	        panelBookTab.add(Box.createRigidArea(new Dimension(0,20)));
	        
	        JPanel panelSouth = new JPanel();
	        panelSouth.setBackground(Color.BLUE);
	        panelSouth.setSize(new Dimension(200, 200));
	        panelSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
	        panelSouth.setLayout(new GridLayout(4, 6));
	        panelBookTab.add(panelSouth);
	               
	        for (JButton jb : bookColumns) {
	        	panelNorth.add(jb);
	        }
	        	    
	        DatabaseControl db = new DatabaseControl("Library_07");
	        String[][] rowData;
	        rowData = db.showAllFromQuery("select * from Books");
	        
	        for (int i = 0; i < rowData.length; i++) {
	        	for (int j = 0; j < rowData[i].length; j++) {
	        		JLabel labelData = new JLabel();
	        		labelData.setText(rowData[i][j]);
	        		panelNorth.add(new JScrollPane(labelData));
	        		
	        	}
	        }
	        
	        JButton checkout = new JButton(" Checkout ");
		    JButton ret = new JButton("  Return  ");
		    
		    			
							  
//		    c.fill = GridBagConstraints.BOTH;
//		    c.anchor = GridBagConstraints.NORTH;
//		    c.weightx = 0.5;
//		    c.weighty = 1.0;
//		    c.gridx = 0;
//		    c.gridy = 0;
//		    c.gridwidth = 4;
//		    panelBookTab.add(new JScrollPane(tableBooks), c);
//
//		    c.fill = GridBagConstraints.HORIZONTAL;
//		    c.anchor = GridBagConstraints.LINE_START;
//		    c.weightx = 0.50;
//		    c.weighty = .50;
//		    c.gridx = 0;
//		    c.gridy = 1;
//		    c.gridwidth = 4;
//		    panelBookTab.add(cardPanel, c);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.anchor = GridBagConstraints.CENTER;
//		    cf.gridx = 1;
//		    cf.gridy = 0;
//		    cf.gridwidth = 1;
//		    firstCard.add(addBook, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.gridx = 0;
//		    cf.gridy = 1;
//		    cf.gridwidth = 1;
//		    firstCard.add(label_TITLE, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_START;
//		    cf.weightx = 0.75;
//		    cf.weighty = 0;
//		    cf.gridx = 1;
//		    cf.gridy = 1;
//		    cf.gridwidth = 1;
//		    firstCard.add(textField_TITLE, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 0;
//		    cf.gridy = 2;
//		    cf.gridwidth = 1;
//		    firstCard.add(label_AUTHOR_FIRST, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.75;
//		    cf.weighty = 0;
//		    cf.gridx = 1;
//		    cf.gridy = 2;
//		    cf.gridwidth = 1;
//		    firstCard.add(textField_AUTHOR_FIRST, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 0;
//		    cf.gridy = 3;
//		    cf.gridwidth = 1;
//		    firstCard.add(label_AUTHOR_LAST, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_START;
//		    cf.weightx = 0.75;
//		    cf.weighty = 0;
//		    cf.gridx = 1;
//		    cf.gridy = 3;
//		    cf.gridwidth = 1;
//		    firstCard.add(textField_AUTHOR_LAST, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 0;
//		    cf.gridy = 4;
//		    cf.gridwidth = 1;
//		    firstCard.add(label_GENRE, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_START;
//		    cf.weightx = 0.75;
//		    cf.weighty = 0;
//		    cf.gridx = 1;
//		    cf.gridy = 4;
//		    cf.gridwidth = 1;
//		    firstCard.add(textField_GENRE, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 1;
//		    cf.gridy = 5;
//		    cf.gridwidth = 1;
//		    firstCard.add(button_ADD_BOOK, cf);
//		    
//		    cf.fill = GridBagConstraints.NONE;
//		    cf.anchor = GridBagConstraints.LINE_START;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 3;
//		    cf.gridy = 5;
//		    //cf.gridwidth = 1;
//		    firstCard.add(checkout, cf);
//		    
//		    cf.fill = GridBagConstraints.NONE;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 3;
//		    cf.gridy = 5;
//		    //cf.gridwidth = 1;
//		    firstCard.add(ret, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.anchor = GridBagConstraints.CENTER;
//		    cf.gridx = 3;
//		    cf.gridy = 0;
//		    cf.gridwidth = 1;
//		    firstCard.add(check_ret, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.gridx = 2;
//		    cf.gridy = 1;
//		    cf.gridwidth = 1;
//		    firstCard.add(label_TITLE2, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_START;
//		    cf.weightx = 0.75;
//		    cf.weighty = 0;
//		    cf.gridx = 3;
//		    cf.gridy = 1;
//		    cf.gridwidth = 1;
//		    firstCard.add(textField_TITLE2, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 2;
//		    cf.gridy = 2;
//		    cf.gridwidth = 1;
//		    firstCard.add(label_PATRON_ID_BOOKS, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 3;
//		    cf.gridy = 2;
//		    cf.gridwidth = 1;
//		    firstCard.add(textField_PATRON_ID_BOOKS, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 2;
//		    cf.gridy = 3;
//		    cf.gridwidth = 1;
//		    firstCard.add(label_PATRON_FIRST, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.75;
//		    cf.weighty = 0;
//		    cf.gridx = 3;
//		    cf.gridy = 3;
//		    cf.gridwidth = 1;
//		    firstCard.add(textField_PATRON_FIRST, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 2;
//		    cf.gridy = 4 ;
//		    cf.gridwidth = 1;
//		    firstCard.add(label_PATRON_LAST, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_START;
//		    cf.weightx = 0.75;
//		    cf.weighty = 0;
//		    cf.gridx = 3;
//		    cf.gridy = 4;
//		    cf.gridwidth = 1;
//		    firstCard.add(textField_PATRON_LAST, cf);
//		    
//		    c.fill = GridBagConstraints.HORIZONTAL;
//		    c.anchor = GridBagConstraints.LINE_START;
//		    c.weightx = 0.50;
//		    c.weighty = .50;
//		    c.gridx = 0;
//		    c.gridy = 5;
//		    c.gridwidth = 3;
//		    panelBookTab.add(buttonPanel, c);
//		    
//		    c.fill = GridBagConstraints.HORIZONTAL;
//		    c.anchor = GridBagConstraints.LINE_END;
//		    c.weightx = 0.5;
//		    c.weighty = 0;
//		    c.gridx = 0;
//		    c.gridy = 6;
//		    c.gridwidth = 1;
//		    buttonPanel.add(button_UPDATE_BOOKS_DATABASE, c);
//		    
//		    c.fill = GridBagConstraints.HORIZONTAL;
//		    c.anchor = GridBagConstraints.LINE_END;
//		    c.weightx = 0.5;
//		    c.weighty = 0;
//		    c.gridx = 1;
//		    c.gridy = 6;
//		    c.gridwidth = 1;
//		    buttonPanel.add(button_LIST_ALL_BOOKS, c);
//		    
//		    c.fill = GridBagConstraints.HORIZONTAL;
//		    c.anchor = GridBagConstraints.LINE_START;
//		    c.weightx = 0.5;
//		    c.weighty = 0;
//		    c.gridx = 2;
//		    c.gridy = 6;
//		    c.gridwidth = 2;
//		    buttonPanel.add(comboBox_LIST_AVAILABLE_BOOKS, c);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.anchor = GridBagConstraints.CENTER;
//		    cf.gridx = 2;
//		    cf.gridy = 1;
//		    cf.gridwidth = 1;
//		    panelPatronsTab.add(addPatron, cf);
//		    
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 1;
//		    cf.gridy = 2;
//		    cf.gridwidth = 1;
//		    panelPatronsTab.add(label_PATRON_FIRST2, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.75;
//		    cf.weighty = 0;
//		    cf.gridx = 2;
//		    cf.gridy = 2;
//		    cf.gridwidth = 1;
//		    panelPatronsTab.add(textField_PATRON_FIRST2, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_END;
//		    cf.weightx = 0.25;
//		    cf.weighty = 0;
//		    cf.gridx = 1;
//		    cf.gridy = 3;
//		    cf.gridwidth = 1;
//		    panelPatronsTab.add(label_PATRON_LAST2, cf);
//
//		    cf.fill = GridBagConstraints.HORIZONTAL;
//		    cf.anchor = GridBagConstraints.LINE_START;
//		    cf.weightx = 0.75;
//		    cf.weighty = 0;
//		    cf.gridx = 2;
//		    cf.gridy = 3;
//		    cf.gridwidth = 1;
//		    panelPatronsTab.add(textField_PATRON_LAST2, cf);
//		    
//		    c.fill = GridBagConstraints.HORIZONTAL;
//		    c.anchor = GridBagConstraints.LINE_START;
//		    c.weightx = 0.5;
//		    c.weighty = 0;
//		    c.gridx = 2;
//		    c.gridy = 4;
//		    c.gridwidth = 1;
//		    panelPatronsTab.add(button_ADD_PATRON, c);
//		    
//		    c.fill = GridBagConstraints.HORIZONTAL;
//		    c.anchor = GridBagConstraints.LINE_START;
//		    c.ipady = 40;
//		    c.weightx = 0.5;
//		    c.weighty = 0;
//		    c.gridx = 0;
//		    c.gridy = 1;
//		    c.gridwidth = 1;
//		    panelPatronsTab.add(button_LIST_PATRONS, c);
//		    
//		    c.fill = GridBagConstraints.BOTH;
//		    c.anchor = GridBagConstraints.CENTER;
//		    c.weightx = 0.5;
//		    c.weighty = 1.0;
//		    c.ipady = 0;
//		    c.gridx = 0;
//		    c.gridy = 0;
//		    c.gridwidth = 4;
//		    panelPatronsTab.add(new JScrollPane(tablePatrons), c);
//		    
//		    // Add listeners for the buttons in the application

		    
		    
		    
		    		 
		    
		    button_ADD_BOOK.addActionListener(new ActionListener() {

		        public void actionPerformed(ActionEvent e) {
		        	       	
		        	JOptionPane.showMessageDialog(LibraryFrame.this,
		                                        new String[] {
		                "Adding the following row:",
		                "Title: [" + textField_TITLE.getText() + "]",
		                "Author's First Name: [" + textField_AUTHOR_FIRST.getText() + "]",
		                "Author's Last Name: [" + textField_AUTHOR_LAST.getText() + "]",
		                "Genre: [" + textField_GENRE.getText() + "]" });
		          
		          System.out.printf( "%s %s %s %s %b %n", textField_TITLE.getText(), textField_AUTHOR_FIRST.getText(), textField_AUTHOR_LAST.getText(), 
		        		  textField_GENRE.getText().trim(), false); //Integer.parseInt(textField_BOOK_ID.getText().trim())

		          Statement stmt = null;
		          try {

		        	  DatabaseControl db = new DatabaseControl("Library_05");
					  connection = DriverManager.getConnection(connectionURL);
					  stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					  ResultSet rs = getContentsOfBooksTable();
					  
					  db.addBook(
							  textField_TITLE.getText(),
                              textField_AUTHOR_FIRST.getText(),
                              textField_AUTHOR_LAST.getText(),
                              textField_GENRE.getText().trim()
					  		);
					  
					  
					  
					  					  
//					  boolean updatable = ((DatabaseMetaData) rsmd).supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY,
//						        ResultSet.CONCUR_UPDATABLE);
//
//						    System.out.println("Updatable ResultSet supported = " + updatable);
					 
//		        	  String myCommand = ("INSERT INTO Books VALUES" + 
//		  					"(currentBookID++)," + 
//		        			"textField_TITLE.getText()," + 
//		  					"textField_AUTHOR_FIRST.getText()," + 
//		        			"textField_AUTHOR_LAST.getText()," +
//		  					"textField_GENRE.getText()," +
//		        			"false, null"));
					  							  
//		        	  //DatabaseControl.dbCommunicate(myCommand);
//		        	  stmt.executeQuery(myCommand);
		        	  
//						    con = DriverManager.getConnection(
//						            "jdbc:derby://localhost/TestDB");
//						          Statement sta = con.createStatement(); 

						    // insert 3 rows
//						          int count = 0;
//						          int c = sta.executeUpdate("INSERT INTO HY_Address"
//						            + " (ID, StreetName, City)"
//						            + " VALUES (1, '5 Baker Road', 'Bellevue')");
//						          count = count + c;
						    
						    
//		        			  myBooksTableModel.insertRow(currentBookID++,
//		            							  textField_TITLE.getText(),
//		                                          textField_AUTHOR_FIRST.getText(),
//		                                          textField_AUTHOR_LAST.getText(),
//		                                          textField_GENRE.getText().trim(),
//		                                          false, 0);
		        	  
//		        	 
//		        	  rs.moveToInsertRow(); // moves cursor to the insert row
//		              rs.updateInt(1, Integer.parseInt(textField_BOOK_ID.getText().trim())); // updates the
//		                 // first column of the insert row 
//		              rs.updateString(2, textField_TITLE.getText()); // updates the second column
//		              rs.updateString(3, textField_AUTHOR_FIRST.getText()); 
//		              rs.updateString(4, textField_AUTHOR_LAST.getText()); 
//		              rs.updateString(5, textField_GENRE.getText().trim()); 
//		              rs.updateBoolean(6, false); // updates the sixth column to false
//		              rs.insertRow();
//		              rs.moveToCurrentRow();
		             // rs = stmt.executeQuery("SELECT * FROM Books");
		        	  
		          } catch (SQLException sqle) {
		            displaySQLExceptionDialog(sqle);
		          }
		          finally {
		        	  if (stmt != null) { try {
						stmt.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} }
		          }
		        }
		      });
		    
		    	  
		    comboBox_LIST_AVAILABLE_BOOKS.addActionListener(new ActionListener() {
		    	
		        public void actionPerformed(ActionEvent e) {
		        JComboBox cb = (JComboBox)e.getSource();
		        String comboBoxItems = (String)cb.getSelectedItem();
		        
		    }
		    
	      });
		    
//		    button_LIST_PATRONS.addActionListener(new ActionListener() {
//
//		        public void actionPerformed(ActionEvent e) {
//		        	 try {
//		        		 getContentsOfPatronsTable();
//		        		 c.fill = GridBagConstraints.BOTH;
//		     		    c.anchor = GridBagConstraints.CENTER;
//		     		    c.weightx = 0.5;
//		     		    c.weighty = 1.0;
//		     		    c.gridx = 0;
//		     		    c.gridy = 2;
//		     		    c.gridwidth = 2;
//		        		 contentPane.add(new JScrollPane(tablePatrons), c);
//		        	 }catch (SQLException sqle) {
//				            displaySQLExceptionDialog(sqle);
//				          }
//				        }
//				      });
		        	
		       
		    button_UPDATE_BOOKS_DATABASE.addActionListener(new ActionListener() {

		        public void actionPerformed(ActionEvent e) {
		        	DatabaseControl db;
					try {
						db = new DatabaseControl("Library_07");
						
						
						//myBooksTableModel.fireTableChanged(new TableModelEvent(myBooksTableModel));
						//tableBooks = new JTable();
						
						
//						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		      });
		  
		  
		  button_LIST_ALL_BOOKS.addActionListener(new ActionListener() {

		        public void actionPerformed(ActionEvent e) {
		          try {
		        	  DatabaseControl db = new DatabaseControl("Library_07");
					  connection = DriverManager.getConnection(connectionURL);
					  Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		                      				
					  ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKS");
					  //createNewBooksTableModel();
					  
//					  ResultSet myBooksResultSet = getContentsOfBooksTable();
//					  myBooksTableModel = new BooksTableModel(myBooksResultSet);
//					  JTable tableBooks = new JTable(); // Displays the table
//					  tableBooks.setPreferredScrollableViewportSize(new Dimension(450, 200));
//					  tableBooks.setModel(myBooksTableModel);
					  
					  				   
					 } catch (SQLException sqle) {
		            displaySQLExceptionDialog(sqle);
		            
		          }
		        }
		      });
		  } 
		  
		 // button_ADD_PATRON.addActionListener(new ActionListener() {
//		        public void actionPerformed(ActionEvent e) {
//		          try {
//		            createNewPatronsTableModel();
//		          } catch (SQLException sqle) {
//		            displaySQLExceptionDialog(sqle);
//		          }
//		        }
//		      });
//		  }

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

//		  private void createNewBooksTableModel() throws SQLException {
//		    myBooksTableModel = new BooksTableModel(getContentsOfBooksTable());
//		    myBooksTableModel.addTableModelListener(this);
//		    tableBooks.setModel(myBooksTableModel);
//		  }
//		  
//		  private void createNewPatronsTableModel() throws SQLException {
//			    myPatronsTableModel = new PatronsTableModel(getContentsOfPatronsTable());
//			    //myPatronsTableModel.addEventHandlersToRowSet(this);
//			    tablePatrons.setModel(myPatronsTableModel);
			 // }

		// Display the error in a dialog box.

	      
		  public ResultSet getContentsOfBooksTable() throws SQLException {
			  ResultSet rs = null;
			    try {
			    	DatabaseControl db = new DatabaseControl("Library_07");
			    	connection = DriverManager.getConnection(connectionURL);
			    	Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                      				
			    	rs = stmt.executeQuery("select * from BOOKS");
			  
			    } catch (SQLException e) {
			        System.out.println(e);
			      }
			  return rs;
		  }
		
		  public ResultSet getContentsOfPatronsTable() throws SQLException {
			  DatabaseControl db = new DatabaseControl("Library_07");
			  connection = DriverManager.getConnection(connectionURL);
			  Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                  				
			  ResultSet rs = stmt.executeQuery("select * from PATRONS");
		  	
		  return rs;
	  }
		 
		  protected JComponent makeTextPanel(String text) {
		        JPanel panel = new JPanel(false);
		        JLabel filler = new JLabel(text);
		        filler.setHorizontalAlignment(JLabel.CENTER);
		        panel.setLayout(new GridLayout(1, 1));
		        panel.add(filler);
		        return panel;
		    }     
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

		  
		  		  		  
		  public abstract class FilterDatabaseMetaData implements DatabaseMetaData
		  	  {
		      protected DatabaseMetaData inner;
		     
		      //System.out.println(supportsResultSetConcurrency(TYPE_SCROLL_SENSITIVE, CONCUR_UPDATABLE));
		     
		      
		      public FilterDatabaseMetaData(DatabaseMetaData inner)
		      { this.inner = inner; }
		      
		      public FilterDatabaseMetaData()
		      {}
		      
		      public void setInner( DatabaseMetaData inner )
		      { this.inner = inner; }
		      
		      public DatabaseMetaData getInner()
		      { return inner; }
//			  
		      public String getDriverName() throws SQLException
		         { return inner.getDriverName(); }
//			  
		      public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
				 return inner.supportsResultSetConcurrency(type, concurrency);
			 }
			  

		  	  }
//when does the table model change?
		  
//		  public void tableChanged(TableModelEvent e)
//		    {
//		        if (e.getType() == TableModelEvent.UPDATE)
//		        {
//		            int row = e.getFirstRow();
//		            int column = e.getColumn();
//
//		            if (column == 1 || column == 2)
//		            {
//		                TableModel model = (TableModel)e.getSource();
//		                int quantity = ((Integer)model.getValueAt(row, 1)).intValue();
//		                double price = ((Double)model.getValueAt(row, 2)).doubleValue();
//		                Double value = new Double(quantity * price);
//		                model.setValueAt(value, row, 3);
//		            }
//		        }
//		    }

//		@Override
//		public void tableChanged(TableModelEvent e) {
//			 //ResultSet rs = this.myBooksTableModel.booksResultSet;
//			 
//			 try {
//				 if (button_UPDATE_BOOKS_DATABASE.isSelected()) {
//				 myBooksTableModel = new BooksTableModel(myBooksTableModel.getbooksResultSet());
//				 tableBooks.setModel(myBooksTableModel);
//				 this.tableChanged(e);
//				 }
//			 		} catch (SQLException ex) {
//				 
//			 	System.out.println(ex);
//			 		
//			 // Display the error in a dialog box.
//			 	
//			 	JOptionPane.showMessageDialog(
//				        LibraryFrame.this,
//				        new String[] { // Display a 2-line message
//				        	ex.getClass().getName() + ": ",
//						    ex.getMessage()
//
//				      
//				        }
//				      );
//				    }
//			 }
		}
		
		
	

	
	


