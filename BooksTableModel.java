package librarySystem;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class BooksTableModel extends AbstractTableModel {

	  ResultSet booksResultSet; // The ResultSet to interpret
	  ResultSetMetaData metadata; // Additional information about the results
	  int numcols, numrows; // How many rows and columns in the table

	  public ResultSet getbooksResultSet() {
	    return booksResultSet;
	  }
	
	public void fireTableDataChanged() {
		
	}
	  
	  public BooksTableModel(ResultSet booksResultSet)
		    throws SQLException {

		    this.booksResultSet = booksResultSet;
		    this.metadata = this.booksResultSet.getMetaData();
		    numcols = metadata.getColumnCount();

		    // Retrieve the number of rows.
		    this.booksResultSet.beforeFirst();
		    this.numrows = 0;
		    while (this.booksResultSet.next()) {
		        this.numrows++;
		    }
		    this.booksResultSet.beforeFirst();
		}
	
	 public void addTableModelListener(TableModelListener listener)
	 	    {
	     listenerList.add (TableModelListener.class, listener);
	   }
	 
//	 private void setupListeners(){
//   	  musicFilesModel.addTableModelListener(new TableModelListener(){
//   	    @Override public void tableChanged(    TableModelEvent e){
//   	      refresh();
//   	    }
//   	  }
//   	);
	
	public void insertRow(int bookid, String title, String authorFirst, String authorLast,
	                        String genre, boolean checkedOut, int patronID) throws SQLException {

	    try {
	      this.booksResultSet.moveToInsertRow();
	      this.booksResultSet.updateInt("BookID", bookid);
	      this.booksResultSet.updateString("Title", title);
	      this.booksResultSet.updateString("AuthorFirst", authorFirst);
	      this.booksResultSet.updateString("AuthorLast", authorLast);
	      this.booksResultSet.updateString("Genre", genre);
	      this.booksResultSet.updateBoolean("CheckedOut", checkedOut);
	      this.booksResultSet.updateInt("PatronID", patronID);
	      this.booksResultSet.insertRow();
	      this.booksResultSet.moveToCurrentRow();
	    } catch (SQLException e) {
	      //JDBCTutorialUtilities.printSQLException(e);
	    }
	  }

	public void tableChanged(TableModelEvent e) {
		fireTableChanged(new TableModelEvent(this, 0, Integer.MAX_VALUE, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
		
	}
	  
	  public void close() {
	    try {
	    	booksResultSet.getStatement().close();
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
	      this.booksResultSet.absolute(rowIndex + 1);
	      Object o = this.booksResultSet.getObject(columnIndex + 1);
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
		  fireTableChanged(new TableModelEvent(this));//0, Integer.MAX_VALUE, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
	  }

	   
	  public void removeTableModelListener(TableModelListener l) {
	  }
	  
	  	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

//	Test public void testMeasurementTypeEvents(){
//		  TableModelEvent event=new TableModelEvent(d_model,-1,-1,-1,TableModelEvent.UPDATE);
//		  TableModelListener mock=createStrictMock(TableModelListener.class);
//		  mock.tableChanged(TableModelEventMatcher.eqTableModelEvent(event));
//		  mock.tableChanged(TableModelEventMatcher.eqTableModelEvent(event));
//		  replay(mock);
//		  d_model.addTableModelListener(mock);
//		  d_measurementType.setValue(DataType.CONTINUOUS);
//		  d_measurementType.setValue(DataType.NONE);
//		  verify(mock);
//		}
	
//	Test public void testDrugAddedUpdatesTable(){
//		  int prevSize=d_tableModel.getRowCount();
//		  TableModelListener mock=JUnitUtil.mockTableModelListener(new TableModelEvent(d_tableModel));
//		  d_tableModel.addTableModelListener(mock);
//		  d_domain.getDrugs().add(ExampleData.buildDrugViagra());
//		  verify(mock);
//		  assertEquals(prevSize + 1,d_tableModel.getRowCount());
//		}
	
//	public void sortByColumn(int column,boolean ascending){
//		  columnSorted=column;
//		  sortingColumns.removeAllElements();
//		  sortingColumns.addElement(new Integer(column));
//		  sort(this);
//		  super.tableChanged(new TableModelEvent(this));
//		  this.ascending=!ascending;
//		}
	
//	public void setValueAt(Object aValue,int rowIndex,int columnIndex){
//		  if (data == null || columnIndex > 0 || rowIndex < 0 || rowIndex >= data.size()) {
//		    return;
//		  }
//		switch (columnIndex) {
//		case CONDITION_COLUMN:
//		{
//		      SavedCondition newValue=(SavedCondition)aValue;
//		      data.set(rowIndex,newValue);
//		      fireTableChange(new TableModelEvent(this,rowIndex,rowIndex,TableModelEvent.ALL_COLUMNS,TableModelEvent.UPDATE));
//		    }
//		}
//		}
	
//	public boolean add(Addon addon){
//		  if (addons.add(addon)) {
//		    fireTableChanged(new TableModelEvent(this,addons.size() - 1));
//		    return true;
//		  }
//		  return false;
//		}
	
//	public void tableChanged(TableModelEvent evt){
//		  if (filesTable.getSelectedRow() == -1) {
//		    return;
//		  } -- if (button_ADD_isSelected
	
	//if (e.getType() == TableModelEvent.UPDATE
	
//	@Override public void setObject(Serializable object){
//		  super.setObject(object);
//		  TableModel tableModel=(TableModel)object;
//		  if (autoCreateRowSorter) {
//		    setRowSorter(new SerializableTableRowSorter(tableModel));
//		  }
//		  tableModel.addTableModelListener(new TableModelListener(){
//		    @Override public void tableChanged(    TableModelEvent e){
//		      Table.this.tableChanged(e);
//		    }
//		  }
//		);
//		}
}
