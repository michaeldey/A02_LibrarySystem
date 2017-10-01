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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.rowset.RowSetProvider;  

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
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
//import net.miginfocom.swing.MigLayout;									/*********/
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Insets;

public class LibraryFrame extends JFrame {

		private JPanel contentPane;
		
		CardLayout cards = new CardLayout();
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
		
		private static final String connectionURL="jdbc:derby:Library_02;create=true";

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
		 
		  BooksTableModel myBooksTableModel;								/*********/
		  PatronsTableModel myPatronsTableModel;							/*********/

		  public LibraryFrame() throws SQLException {
			  
		    super("Library System"); // Set window title
		    

		    DatabaseControl db = new DatabaseControl();
			
		    // Close connections exit the application when the user
		    // closes the window

		    addWindowListener(new WindowAdapter() {
		        public void windowClosing(WindowEvent e) {

		          try {
		            connection.close();
		          } catch (SQLException sqle) {
//		        	System.out.println(e.getMessage());
//		  			e.printStackTrace();
		          }
		          System.exit(0);
		        }
		      });

		    // Initialize and lay out window controls

		    ResultSet myBooksResultSet = getContentsOfBooksTable();
		    myBooksTableModel = new BooksTableModel(myBooksResultSet);							/*********/
		    //myBooksTableModel.addEventHandlersToRowSet(this);
		    
		    ResultSet myPatronsResultSet = getContentsOfPatronsTable();
		    myPatronsTableModel = new PatronsTableModel(myPatronsResultSet);					/*********/
//		    myPatronsTableModel.addEventHandlersToRowSet(this);

		    tableBooks = new JTable(); // Displays the table
		    tableBooks.setPreferredScrollableViewportSize(new Dimension(450, 200));
		    tableBooks.setModel(myBooksTableModel);												/*********/
		    tablePatrons = new JTable();
		    tablePatrons.setModel(myPatronsTableModel);											/*********/
		   
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
			
		    //textField_BOOK_ID.setText("1001");
//			textField_TITLE.setText("Title");
//		    textField_AUTHOR_FIRST.setText("Author's First Name");
//			textField_AUTHOR_LAST.setText("Author's Last Name");
//			textField_GENRE.setText("Genre");
			
//			textField_PATRON_ID.setText("101");
//			textField_PATRON_FIRST.setText("Enter Patron's First Name");
//			textField_PATRON_LAST.setText("Enter Patron's Last Name");
//			textField_BOOKS_OUT.setText("Enter Books Checked Out");

		    button_ADD_BOOK.setText("Add New Book");
		    button_ADD_PATRON.setText("Add New Patron");
			button_UPDATE_BOOKS_DATABASE.setText("Update Books Database");
			button_UPDATE_PATRONS_DATABASE.setText("Update Patrons Database");
			button_LIST_ALL_BOOKS.setText("List All Books");
			
			String[] comboBoxItems = { "Available Books By Title", "Available Books By Author", "Available Books By Genre" };
			comboBox_LIST_AVAILABLE_BOOKS = new JComboBox(comboBoxItems);
			comboBox_LIST_AVAILABLE_BOOKS.setEditable(false);
			      
			button_LIST_PATRONS.setText("List All Patrons");
			button_CHECKOUT_RETURN_BOOK.setText("Checkout or Return Book");
			
		    // Place the components within the container contentPane; use GridBagLayout
		    // as the layout.
					    
		    JTabbedPane tabbedPane = new JTabbedPane();
		    		    
		    JComponent panel1 = new JPanel();
		    GridBagLayout gbl_panel1 = new GridBagLayout();
		    gbl_panel1.rowWeights = new double[]{0.0};
		    gbl_panel1.columnWeights = new double[]{0.0};
		    panel1.setLayout(gbl_panel1);
		    GridBagConstraints c = new GridBagConstraints();
		    c.insets = new Insets(0, 0, 5, 0);
		    tabbedPane.addTab("Books", panel1);		    

		    JComponent panel2 = new JPanel();
		    panel2.setLayout(new GridBagLayout());
		    GridBagConstraints c2 = new GridBagConstraints();
		    tabbedPane.addTab("Patrons", panel2);
		    
		    //Add the tabbed pane to this panel.
	        getContentPane().add(tabbedPane);
	         
	        //The following line enables using scrolling tabs.
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	        
	        cardPanel = new JPanel();
	        
		    JComponent firstCard = new JPanel();
		    firstCard.setSize(new Dimension(500, 200));
		    firstCard.setLayout(new GridBagLayout());
		    GridBagConstraints cf = new GridBagConstraints();
		    cf.insets = new Insets(0, 0, 0, 5);
		    cf.gridy = 1;
		    cf.gridx = 0;
		    
		    //new CardLayout(0, 0)
		    cardPanel.setLayout(cards);
		    cardPanel.add(firstCard, "add");
		    cardPanel.setVisible(true);
		    panel1.add(cardPanel, cf);
		    		    
		    JButton checkout = new JButton(" Checkout ");
		    JButton ret = new JButton("  Return  ");
		    firstCard.add(checkout);
		    firstCard.add(ret);
		    			
			buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(500, 200));
			buttonPanel.setSize(new Dimension(500, 200));
			buttonPanel.setLayout(new GridBagLayout());
					  
		    c.fill = GridBagConstraints.BOTH;
		    c.anchor = GridBagConstraints.NORTH;
		    c.weightx = 0.5;
		    c.weighty = 1.0;
		    c.gridx = 0;
		    c.gridy = 0;
		    c.gridwidth = 4;
		    panel1.add(new JScrollPane(tableBooks), c);

		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.anchor = GridBagConstraints.LINE_START;
		    c.weightx = 0.50;
		    c.weighty = .50;
		    c.gridx = 0;
		    c.gridy = 1;
		    c.gridwidth = 4;
		    panel1.add(cardPanel, c);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.anchor = GridBagConstraints.CENTER;
		    cf.gridx = 1;
		    cf.gridy = 0;
		    cf.gridwidth = 1;
		    firstCard.add(addBook, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.gridx = 0;
		    cf.gridy = 1;
		    cf.gridwidth = 1;
		    firstCard.add(label_TITLE, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 1;
		    cf.gridwidth = 1;
		    firstCard.add(textField_TITLE, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 0;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    firstCard.add(label_AUTHOR_FIRST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    firstCard.add(textField_AUTHOR_FIRST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 0;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    firstCard.add(label_AUTHOR_LAST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    firstCard.add(textField_AUTHOR_LAST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 0;
		    cf.gridy = 4;
		    cf.gridwidth = 1;
		    firstCard.add(label_GENRE, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 4;
		    cf.gridwidth = 1;
		    firstCard.add(textField_GENRE, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 5;
		    cf.gridwidth = 1;
		    firstCard.add(button_ADD_BOOK, cf);
		    
		    cf.fill = GridBagConstraints.NONE;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 4;
		    //cf.gridwidth = 1;
		    firstCard.add(checkout, cf);
		    
		    cf.fill = GridBagConstraints.NONE;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 4;
		    //cf.gridwidth = 1;
		    firstCard.add(ret, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.anchor = GridBagConstraints.CENTER;
		    cf.gridx = 3;
		    cf.gridy = 0;
		    cf.gridwidth = 1;
		    firstCard.add(check_ret, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.gridx = 2;
		    cf.gridy = 1;
		    cf.gridwidth = 1;
		    firstCard.add(label_TITLE2, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 1;
		    cf.gridwidth = 1;
		    firstCard.add(textField_TITLE2, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 2;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    firstCard.add(label_PATRON_FIRST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    firstCard.add(textField_PATRON_FIRST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 2;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    firstCard.add(label_PATRON_LAST, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 3;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    firstCard.add(textField_PATRON_LAST, cf);
		    
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.anchor = GridBagConstraints.LINE_START;
		    c.weightx = 0.50;
		    c.weighty = .50;
		    c.gridx = 0;
		    c.gridy = 5;
		    c.gridwidth = 3;
		    panel1.add(buttonPanel, c);
		    
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.anchor = GridBagConstraints.LINE_END;
		    c.weightx = 0.5;
		    c.weighty = 0;
		    c.gridx = 0;
		    c.gridy = 6;
		    c.gridwidth = 1;
		    buttonPanel.add(button_UPDATE_BOOKS_DATABASE, c);
		    
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.anchor = GridBagConstraints.LINE_END;
		    c.weightx = 0.5;
		    c.weighty = 0;
		    c.gridx = 1;
		    c.gridy = 6;
		    c.gridwidth = 1;
		    buttonPanel.add(button_LIST_ALL_BOOKS, c);
		    
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.anchor = GridBagConstraints.LINE_START;
		    c.weightx = 0.5;
		    c.weighty = 0;
		    c.gridx = 2;
		    c.gridy = 6;
		    c.gridwidth = 2;
		    buttonPanel.add(comboBox_LIST_AVAILABLE_BOOKS, c);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.anchor = GridBagConstraints.CENTER;
		    cf.gridx = 2;
		    cf.gridy = 1;
		    cf.gridwidth = 1;
		    panel2.add(addPatron, cf);
		    
		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    panel2.add(label_PATRON_FIRST2, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 2;
		    cf.gridy = 2;
		    cf.gridwidth = 1;
		    panel2.add(textField_PATRON_FIRST2, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_END;
		    cf.weightx = 0.25;
		    cf.weighty = 0;
		    cf.gridx = 1;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    panel2.add(label_PATRON_LAST2, cf);

		    cf.fill = GridBagConstraints.HORIZONTAL;
		    cf.anchor = GridBagConstraints.LINE_START;
		    cf.weightx = 0.75;
		    cf.weighty = 0;
		    cf.gridx = 2;
		    cf.gridy = 3;
		    cf.gridwidth = 1;
		    panel2.add(textField_PATRON_LAST2, cf);
		    
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.anchor = GridBagConstraints.LINE_START;
		    c.weightx = 0.5;
		    c.weighty = 0;
		    c.gridx = 2;
		    c.gridy = 4;
		    c.gridwidth = 1;
		    panel2.add(button_ADD_PATRON, c);
		    
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.anchor = GridBagConstraints.LINE_START;
		    c.ipady = 40;
		    c.weightx = 0.5;
		    c.weighty = 0;
		    c.gridx = 0;
		    c.gridy = 1;
		    c.gridwidth = 1;
		    panel2.add(button_LIST_PATRONS, c);
		    
		    c.fill = GridBagConstraints.BOTH;
		    c.anchor = GridBagConstraints.CENTER;
		    c.weightx = 0.5;
		    c.weighty = 1.0;
		    c.ipady = 0;
		    c.gridx = 0;
		    c.gridy = 0;
		    c.gridwidth = 4;
		    panel2.add(new JScrollPane(tablePatrons), c);
		    
		    // Add listeners for the buttons in the application

		    button_ADD_BOOK.addActionListener(new ActionListener() {

		        public void actionPerformed(ActionEvent e) {
		        	       	
		        	JOptionPane.showMessageDialog(LibraryFrame.this,
		                                        new String[] {
		                "Adding the following row:",
		                "Title: [" + textField_TITLE.getText() + "]",
		                "Author's First Name: [" + textField_AUTHOR_FIRST.getText() + "]",
		                "Author's Last Name: [" + textField_AUTHOR_LAST.getText() + "]",
		                "Genre: [" + textField_GENRE.getText() + "]" });
		          
		          System.out.printf("%d %s %s %s %s %b", Integer.parseInt(textField_BOOK_ID.getText().trim()), 
		        		  textField_TITLE.getText(), textField_AUTHOR_FIRST.getText(), textField_AUTHOR_LAST.getText(), 
		        		  textField_GENRE.getText().trim(), false);

		          try {

		        	  DatabaseControl db = new DatabaseControl();
					  connection = DriverManager.getConnection(connectionURL);
					  Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//		        	  String myCommand = ("INSERT INTO Books VALUES" + 
//		  					"(Integer.parseInt(textField_BOOK_ID.getText().trim())," + 
//		        			"textField_TITLE.getText()," + 
//		  					"textField_AUTHOR_FIRST.getText()," + 
//		        			"textField_AUTHOR_LAST.getText()," +
//		  					"textField_GENRE.getText()," +
//		        			"false)");
//					  
//		        	  //DatabaseControl.dbCommunicate(myCommand);
//		        	  stmt.executeQuery(myCommand);
		        	  
//		        			  
//		        			  myBooksTableModel.insertRow(Integer.parseInt(textField_BOOK_ID.getText().trim()),
//		            							  textField_TITLE.getText(),
//		                                          textField_AUTHOR_FIRST.getText(),
//		                                          textField_AUTHOR_LAST.getText(),
//		                                          textField_GENRE.getText().trim(),
//		                                          Boolean.parseBoolean(textField_CHECKED_OUT.getText().trim()));
		        	  
		        	  ResultSet rs = getContentsOfBooksTable();
		        	  rs.moveToInsertRow(); // moves cursor to the insert row
		              rs.updateInt(1, Integer.parseInt(textField_BOOK_ID.getText().trim())); // updates the
		                 // first column of the insert row 
		              rs.updateString(2, textField_TITLE.getText()); // updates the second column
		              rs.updateString(3, textField_AUTHOR_FIRST.getText()); 
		              rs.updateString(4, textField_AUTHOR_LAST.getText()); 
		              rs.updateString(5, textField_GENRE.getText().trim()); 
		              rs.updateBoolean(6, false); // updates the sixth column to false
		              rs.insertRow();
		              rs.moveToCurrentRow();
		              rs = stmt.executeQuery("SELECT * FROM Books");
		        	  
		          } catch (SQLException sqle) {
		            displaySQLExceptionDialog(sqle);
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
		          try {
		        	  DatabaseControl db = new DatabaseControl();
					  connection = DriverManager.getConnection(connectionURL);
					  Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		                      				
					  ResultSet rs = stmt.executeQuery("SELECT * FROM Books");
					  	
					  
		        	  
//		        	  ResultSet rs = getContentsOfBooksTable();
//		        	  rs.moveToInsertRow(); // moves cursor to the insert row
//		              rs.updateInt(1, 1005); // updates the
//		                 // first column of the insert row to be AINSWORTH
//		              rs.updateString(2, "The Stranger"); // updates the second column to be 35
//		              rs.updateString(3, "Albert"); 
//		              rs.updateString(4, "Camus"); 
//		              rs.updateString(5, "20th Century"); 
//		              rs.updateBoolean(6, false); // updates the sixth column to true
//		              rs.insertRow();
//		              rs.moveToCurrentRow();
		          } catch (SQLException sqle) {
		            displaySQLExceptionDialog(sqle);
		            // Now revert back changes
		            try {
		              createNewBooksTableModel();
		            } catch (SQLException sqle2) {
		              displaySQLExceptionDialog(sqle2);
		            }
		          }
		        }
		      });
		  }
		  

//		    button_ADD_PATRON.addActionListener(new ActionListener() {
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

		  private void createNewBooksTableModel() throws SQLException {
		    myBooksTableModel = new BooksTableModel(getContentsOfBooksTable());								/*********/
		    //myBooksTableModel.addEventHandlersToTableModel(this);
		    tableBooks.setModel(myBooksTableModel);															/*********/
		  }
		  
		  private void createNewPatronsTableModel() throws SQLException {
			    myPatronsTableModel = new PatronsTableModel(getContentsOfPatronsTable());					/*********/
			    //myPatronsTableModel.addEventHandlersToRowSet(this);
			    tablePatrons.setModel(myPatronsTableModel);													/*********/
			  }

		// Display the error in a dialog box.

	      
		  public ResultSet getContentsOfBooksTable() throws SQLException {
			  DatabaseControl db = new DatabaseControl();
			  connection = DriverManager.getConnection(connectionURL);
			  Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                      				
			  ResultSet rs = stmt.executeQuery("select BOOKS_ID, TITLE, AUTHOR_FIRST, AUTHOR_LAST, GENRE, CHECKED_OUT from Books");
			  	
			  return rs;
		  }
		
		  public ResultSet getContentsOfPatronsTable() throws SQLException {
			  DatabaseControl db = new DatabaseControl();
			  connection = DriverManager.getConnection(connectionURL);
			  Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                  				
			  ResultSet rs = stmt.executeQuery("select * from Patrons");
		  	
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

		  
		  

		  

//		  public void rowInsert(RowSetEvent event) {
//
//			  ResultSet rs = this.myBooksTableModel.booksResultSet;
//			  rs.moveToInsertRow(); // moves cursor to the insert row
//		      rs.updateString(1, "AINSWORTH"); // updates the
//		          // first column of the insert row to be AINSWORTH
//		       rs.updateInt(2,35); // updates the second column to be 35
//		       rs.updateBoolean(3, true); // updates the third column to true
//		       rs.insertRow();
//		       rs.moveToCurrentRow();
			  
			 

//		    try {
//		      currentRowSet.moveToCurrentRow();
//		      myBooksTableModel =
//		        new BooksTableModel(myBooksTableModel.getBooksRowSet());
//		      table.setModel(myBooksTableModel);
//
//		    } catch (SQLException ex) {

		      //JDBCTutorialUtilities.printSQLException(ex);
//		  JOptionPane.showMessageDialog(
//			        LibraryFrame.this,
//			        new String[] { // Display a 2-line message
//			          ex.getClass().getName() + ": ",
//			          ex.getMessage()
//			        }
//			      );
//			    }
//		      
		  

		  public void cursorMoved(RowSetEvent event) {  }

		  
//			  
//			  
//			  
//			  

		
	}

	
	


