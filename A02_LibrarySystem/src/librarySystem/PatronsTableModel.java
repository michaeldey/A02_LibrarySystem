package librarySystem;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class PatronsTableModel implements TableModel{
	
	  ResultSet patronsResultSet; // The ResultSet to interpret
	  ResultSetMetaData metadata; // Additional information about the results
	  int numcols, numrows; // How many rows and columns in the table

	  public ResultSet getPatronsResultSet() {
	    return patronsResultSet;
	  }
	
	public PatronsTableModel(ResultSet patronsResultSet)
		    throws SQLException {

		    this.patronsResultSet = patronsResultSet;
		    this.metadata = this.patronsResultSet.getMetaData();
		    numcols = metadata.getColumnCount();

		    // Retrieve the number of rows.
		    this.patronsResultSet.beforeFirst();
		    this.numrows = 0;
		    while (this.patronsResultSet.next()) {
		        this.numrows++;
		    }
		    this.patronsResultSet.beforeFirst();
		}
	
	

	  public void insertRow(int patronID, String firstName, String lastName) throws SQLException {

	    try {
	      this.patronsResultSet.moveToInsertRow();
	      this.patronsResultSet.updateInt("PatronID", patronID);
	      this.patronsResultSet.updateString("FName", firstName);
	      this.patronsResultSet.updateString("LName", lastName);
	      //this.patronsResultSet.updateString("BooksOut", );
	      this.patronsResultSet.insertRow();
	      this.patronsResultSet.moveToCurrentRow();
	    } catch (SQLException e) {
	      //JDBCTutorialUtilities.printSQLException(e);
	    }
	  }

	  public void close() {
	    try {
	    	patronsResultSet.getStatement().close();
	    } catch (SQLException e) {
	      //JDBCTutorialUtilities.printSQLException(e);
	    }
	  }

	  /** Automatically close when we're garbage collected */
	  protected void finalize() {
	    close();
	  }

	  /** Method from interface TableModel; returns the number of columns */

	  public int getColumnCount() {
	    return numcols;
	  }

	    /** Method from interface TableModel; returns the number of rows */

	  public int getRowCount() {
	    return numrows;
	  }

	  /** Method from interface TableModel; returns the column name at columnIndex
	   *  based on information from ResultSetMetaData
	   */

	  public String getColumnName(int column) {
	    try {
	      return this.metadata.getColumnLabel(column + 1);
	    } catch (SQLException e) {
	      return e.toString();
	    }
	  }

	  /** Method from interface TableModel; returns the most specific superclass for
	   *  all cell values in the specified column. To keep things simple, all data
	   *  in the table are converted to String objects; hence, this method returns
	   *  the String class.
	   */

	  public Class getColumnClass(int column) {
		  return getValueAt(0, column).getClass();	  
		  }

	  /** Method from interface TableModel; returns the value for the cell specified
	   *  by columnIndex and rowIndex. TableModel uses this method to populate
	   *  itself with data from the row set. SQL starts numbering its rows and
	   *  columns at 1, but TableModel starts at 0.
	   */

	  public Object getValueAt(int rowIndex, int columnIndex) {

	    try {
	      this.patronsResultSet.absolute(rowIndex + 1);
	      Object o = this.patronsResultSet.getObject(columnIndex + 1);
	      if (o == null)
	        return null;
	      else
	        return o;  //toString();
	    } catch (SQLException e) {
	      return e.toString();
	    }
	  }

	    /** Method from interface TableModel; returns true if the specified cell
	     *  is editable. This sample does not allow users to edit any cells from
	     *  the TableModel (rows are added by another window control). Thus,
	     *  this method returns false.
	     */

	  public boolean isCellEditable(int rowIndex, int columnIndex) {
	    return false;
	  }

	  // Because the sample does not allow users to edit any cells from the
	  // TableModel, the following methods, setValueAt, addTableModelListener,
	  // and removeTableModelListener, do not need to be implemented.

	  public void setValueAt(Object value, int row, int column) {
	    System.out.println("Calling setValueAt row " + row + ", column " + column);
	  }

	  public void addTableModelListener(TableModelListener listener) {
	 }
	  
	  public void removeTableModelListener(TableModelListener l) {
	  }

	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


