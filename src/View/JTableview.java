package View;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Database.DBConnection;

public class JTableview {
	JTable SQLTable = null;

	// 1. create JTable view based on a generic SQL query
	public JTableview(String SQLquery) throws Exception {
		SQLTable = genSQLTable(SQLquery);
	}

	public JTable getSQLTable() {
		return SQLTable;
	}

	// 1. based on the result set of the SQL query a JTable is created
	private JTable genSQLTable(String SQLquery) throws Exception {
		Connection conn = DBConnection.getConnection();
		Statement st = conn.createStatement();
		// 1.1 create JTable
		int columnCount = 0;
		int cnt = 1;
		// initialize local JTable view
		JTable tableview = new JTable();
		tableview.enableInputMethods(false);
		tableview.setDragEnabled(false);
		tableview.setColumnSelectionAllowed(false);
		// selection of exact one field value
		tableview.setColumnSelectionAllowed(true);
		tableview.setRowSelectionAllowed(true);
		tableview.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 1.2 create model and initialize model with reference to JTable view
		DefaultTableModel model = (DefaultTableModel) tableview.getModel();
		
		try {
			// use generic SQL query to get the result set
			ResultSet rs = st.executeQuery(SQLquery);
			if (rs != null) { 
				// 1.3 fill model: identify column size of the result set
				ResultSetMetaData rsmd = rs.getMetaData();
				columnCount = rsmd.getColumnCount();
				// add column labels to the model
				for (int column = 1; column <= columnCount; column++) {
					model.addColumn(rsmd.getColumnLabel(column));
				}
				// add rows content to the model
				Object[] objects = new Object[columnCount];
				while (rs.next()) {
					cnt = 0;
					// add column content of next row to the model
					while (cnt < columnCount) {
						objects[cnt] = rs.getObject(cnt + 1);
						cnt++;
					}
					model.addRow(objects);
				}
				// to avoid side effects close all connection objects
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(new JFrame(), ex);
		}
		// 1.4 set model content to JTable view and return JTable view
		
		tableview.setModel(model);
		return tableview;
	}
}