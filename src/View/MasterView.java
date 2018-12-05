package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.CreateBookController;
import Controller.ProfessorController;
import Controller.RentBookController;
import Controller.SearchController;
import Controller.StudentController;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Button;
import java.awt.Component;

import javax.swing.JTable;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class MasterView extends JFrame {

	private JPanel contentPane;
	private JTableview tablebooks;
	private JTableview tableUsers;
	private JTextField textFieldBuchname;
	private JTextField textFieldAnzahlExemplare;
	private JTextField textFieldVornameStudent;
	private JTextField textFieldNachnameStudent;
	private JTextField textFieldStudiengruppe;
	private JTextField textFieldMarNR;
	private JTextField textfieldStraﬂeStudent;
	private JTextField textFieldHausnummerStudent;
	private JTextField textFieldOrtStudent;
	private JTextField textFieldPLZStudent;
	private JTextField textFieldVornameProf;
	private JTextField textFieldNachnameProf;
	private JTextField textFieldFakProf;
	private JTextField texFieldSraﬂeProf;
	private JTextField textFieldHNrProf;
	private JTextField textFieldOrtProf;
	private JTextField textFieldPLZProf;
	private JTextField textFieldBuchNameAusleihen;
	private JTextField textFieldPersonArtAusleiher;
	private JTextField textFieldNachnameAusleiher;
	private JTextField textFieldVornameAusleihender;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JPanel searchUserTab;
	private JPanel searchBooksTab;
	private JComboBox comboBoxBS;
	private JComboBox comboBoxAddBook;
	private JRadioButton rdbtnAdresseVorhandenStudent;
	private JRadioButton radioButtonProf;
	private JComboBox comboBoxPersonArtAusleiher;
	
	public SearchController control;
	public StudentController stuControl;
	public ProfessorController profControl;
	public CreateBookController createBookControl;
	public RentBookController rentBookControl;
	
	// Action Commands for Action Listener
	public final static String ACTION_SEARCH_USER = "SEARCH USER";
	public final static String ACTION_SEARCH_BOOK = "SEARCH BOOK";
	public final static String ACTION_CREATE_STUDENT = "CREATE STUDENT";
	public final static String ACTION_CREATE_PROFESSOR = "CREATE PROFESSOR";
	public final static String ACTION_CREATE_BOOK = "CREATE BOOK";
	public final static String ACTION_RENT_BOOK = "RENT BOOK";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterView frame = new MasterView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public MasterView() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 650, 370);
		contentPane.add(tabbedPane);
		
		control = new SearchController(this);
		stuControl = new StudentController(this);
		profControl = new ProfessorController(this);
		createBookControl = new CreateBookController(this);
		rentBookControl = new RentBookController(this);
		
		JPanel buchAusleihenTab = new JPanel();
		tabbedPane.addTab("Buch Ausleihen", null, buchAusleihenTab, null);
		buchAusleihenTab.setLayout(null);
		
		JLabel labelBuchAusleihen = new JLabel("Neues Buch ausleihen");
		labelBuchAusleihen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelBuchAusleihen.setBounds(10, 11, 146, 23);
		buchAusleihenTab.add(labelBuchAusleihen);
		
		JLabel labelBuchNameAusleihen = new JLabel("Buchname:");
		labelBuchNameAusleihen.setBounds(20, 48, 136, 14);
		buchAusleihenTab.add(labelBuchNameAusleihen);
		
		textFieldBuchNameAusleihen = new JTextField();
		textFieldBuchNameAusleihen.setColumns(10);
		textFieldBuchNameAusleihen.setBounds(191, 45, 146, 20);
		buchAusleihenTab.add(textFieldBuchNameAusleihen);
		
		Button buttonAusleihen = new Button("Ausleihen");
		buttonAusleihen.setBounds(123, 268, 70, 22);
		buchAusleihenTab.add(buttonAusleihen);
		buttonAusleihen.setActionCommand(ACTION_RENT_BOOK);
		buttonAusleihen.addActionListener(rentBookControl);
		
		comboBoxPersonArtAusleiher = new JComboBox();
		comboBoxPersonArtAusleiher.setToolTipText("all");
		comboBoxPersonArtAusleiher.setBounds(20, 176, 136, 22);
		buchAusleihenTab.add(comboBoxPersonArtAusleiher);
		comboBoxPersonArtAusleiher.addItem("Matrikelnummer");
		comboBoxPersonArtAusleiher.addItem("Fakult‰t");
		
		textFieldPersonArtAusleiher = new JTextField();
		textFieldPersonArtAusleiher.setColumns(10);
		textFieldPersonArtAusleiher.setBounds(191, 177, 146, 20);
		buchAusleihenTab.add(textFieldPersonArtAusleiher);
		
		JLabel lblNachnameAusleihender = new JLabel("Nachname Ausleihender:");
		lblNachnameAusleihender.setBounds(20, 133, 161, 14);
		buchAusleihenTab.add(lblNachnameAusleihender);
		
		textFieldNachnameAusleiher = new JTextField();
		textFieldNachnameAusleiher.setBounds(191, 130, 146, 20);
		buchAusleihenTab.add(textFieldNachnameAusleiher);
		textFieldNachnameAusleiher.setColumns(10);
		
		JLabel lblVornameAusleihender = new JLabel("Vorname Ausleihender:");
		lblVornameAusleihender.setBounds(20, 88, 161, 14);
		buchAusleihenTab.add(lblVornameAusleihender);
		
		textFieldVornameAusleihender = new JTextField();
		textFieldVornameAusleihender.setBounds(191, 85, 146, 20);
		buchAusleihenTab.add(textFieldVornameAusleihender);
		textFieldVornameAusleihender.setColumns(10);
		
		JPanel newBookTab = new JPanel();
		tabbedPane.addTab("Buch hinzuf\u00FCgen", null, newBookTab, null);
		newBookTab.setLayout(null);
		
		JLabel descripionBH = new JLabel("Neues Buch anlegen");
		descripionBH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descripionBH.setBounds(10, 11, 132, 23);
		newBookTab.add(descripionBH);
		
		JLabel lblBuchname = new JLabel("Buchname:");
		lblBuchname.setBounds(20, 48, 96, 14);
		newBookTab.add(lblBuchname);
		
		JLabel lblAnzahlDerExemplare = new JLabel("Anzahl der Exemplare:");
		lblAnzahlDerExemplare.setBounds(20, 88, 139, 14);
		newBookTab.add(lblAnzahlDerExemplare);
		
		textFieldBuchname = new JTextField();
		textFieldBuchname.setBounds(166, 45, 146, 20);
		newBookTab.add(textFieldBuchname);
		textFieldBuchname.setColumns(10);
		
		textFieldAnzahlExemplare = new JTextField();
		textFieldAnzahlExemplare.setBounds(166, 85, 75, 20);
		newBookTab.add(textFieldAnzahlExemplare);
		textFieldAnzahlExemplare.setColumns(10);
		
		Button buttonBuchAnlegen = new Button("Anlegen");
		buttonBuchAnlegen.setBounds(120, 218, 70, 22);
		newBookTab.add(buttonBuchAnlegen);
		buttonBuchAnlegen.setActionCommand(ACTION_CREATE_BOOK);
		buttonBuchAnlegen.addActionListener(createBookControl);
		
		comboBoxAddBook = new JComboBox();
		comboBoxAddBook.setToolTipText("all");
		comboBoxAddBook.setBounds(85, 133, 121, 22);
		newBookTab.add(comboBoxAddBook);
		comboBoxAddBook.addItem("ausleihbar");
		comboBoxAddBook.addItem("nicht ausleihbar");
		
		JPanel studentTab = new JPanel();
		tabbedPane.addTab("Student hinzuf\u00FCgen", null, studentTab, null);
		studentTab.setLayout(null);
		
		JLabel lblNeuenStudentenAnlegen = new JLabel("Neuen Studenten anlegen");
		lblNeuenStudentenAnlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNeuenStudentenAnlegen.setBounds(10, 11, 169, 23);
		studentTab.add(lblNeuenStudentenAnlegen);
		
		JLabel lblVornameStudent = new JLabel("Vorname:");
		lblVornameStudent.setBounds(20, 55, 108, 14);
		studentTab.add(lblVornameStudent);
		
		JLabel lblNachname = new JLabel("Nachname:");
		lblNachname.setBounds(20, 88, 108, 14);
		studentTab.add(lblNachname);
		
		JLabel lblStudiengruppe = new JLabel("Studiengruppe:");
		lblStudiengruppe.setBounds(20, 120, 108, 14);
		studentTab.add(lblStudiengruppe);
		
		JLabel lblMartrikelnummer = new JLabel("Martrikelnummer:");
		lblMartrikelnummer.setBounds(20, 154, 108, 14);
		studentTab.add(lblMartrikelnummer);
		
		textFieldVornameStudent = new JTextField();
		textFieldVornameStudent.setBounds(138, 52, 152, 20);
		studentTab.add(textFieldVornameStudent);
		textFieldVornameStudent.setColumns(10);
		
		textFieldNachnameStudent = new JTextField();
		textFieldNachnameStudent.setColumns(10);
		textFieldNachnameStudent.setBounds(138, 85, 152, 20);
		studentTab.add(textFieldNachnameStudent);
		
		textFieldStudiengruppe = new JTextField();
		textFieldStudiengruppe.setColumns(10);
		textFieldStudiengruppe.setBounds(138, 117, 152, 20);
		studentTab.add(textFieldStudiengruppe);
		
		textFieldMarNR = new JTextField();
		textFieldMarNR.setColumns(10);
		textFieldMarNR.setBounds(138, 151, 152, 20);
		studentTab.add(textFieldMarNR);
		
		rdbtnAdresseVorhandenStudent = new JRadioButton("Adresse vorhanden");
		rdbtnAdresseVorhandenStudent.setBounds(411, 79, 152, 23);
		studentTab.add(rdbtnAdresseVorhandenStudent);
		
		JLabel lblStrae = new JLabel("Stra\u00DFe:");
		lblStrae.setBounds(360, 145, 93, 14);
		studentTab.add(lblStrae);
		
		JLabel lblHausnummer = new JLabel("Hausnummer:");
		lblHausnummer.setBounds(360, 179, 93, 14);
		studentTab.add(lblHausnummer);
		
		JLabel lblOrt = new JLabel("Ort:");
		lblOrt.setBounds(360, 217, 66, 14);
		studentTab.add(lblOrt);
		
		JLabel lblPlz = new JLabel("PLZ:");
		lblPlz.setBounds(360, 253, 93, 14);
		studentTab.add(lblPlz);
		
		textfieldStraﬂeStudent = new JTextField();
		textfieldStraﬂeStudent.setBounds(463, 142, 161, 20);
		studentTab.add(textfieldStraﬂeStudent);
		textfieldStraﬂeStudent.setColumns(10);
		
		textFieldHausnummerStudent = new JTextField();
		textFieldHausnummerStudent.setColumns(10);
		textFieldHausnummerStudent.setBounds(463, 176, 161, 20);
		studentTab.add(textFieldHausnummerStudent);
		
		textFieldOrtStudent = new JTextField();
		textFieldOrtStudent.setColumns(10);
		textFieldOrtStudent.setBounds(463, 214, 161, 20);
		studentTab.add(textFieldOrtStudent);
		
		textFieldPLZStudent = new JTextField();
		textFieldPLZStudent.setColumns(10);
		textFieldPLZStudent.setBounds(463, 250, 161, 20);
		studentTab.add(textFieldPLZStudent);
		
		Button buttonStudent = new Button("Anlegen");
		buttonStudent.setBounds(298, 292, 70, 22);
		studentTab.add(buttonStudent);
		buttonStudent.setActionCommand(ACTION_CREATE_STUDENT);
		buttonStudent.addActionListener(stuControl);
		
		JPanel profTab = new JPanel();
		tabbedPane.addTab("Professor hinzuf\u00FCgen", null, profTab, null);
		profTab.setLayout(null);
		
		JLabel descrProf = new JLabel("Neuen Professor anlegen");
		descrProf.setHorizontalAlignment(SwingConstants.LEFT);
		descrProf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descrProf.setBounds(10, 11, 169, 23);
		profTab.add(descrProf);
		
		JLabel labelVornameProf = new JLabel("Vorname:");
		labelVornameProf.setBounds(20, 55, 108, 14);
		profTab.add(labelVornameProf);
		
		textFieldVornameProf = new JTextField();
		textFieldVornameProf.setColumns(10);
		textFieldVornameProf.setBounds(138, 52, 152, 20);
		profTab.add(textFieldVornameProf);
		
		JLabel labelNachnameProf = new JLabel("Nachname:");
		labelNachnameProf.setBounds(20, 88, 108, 14);
		profTab.add(labelNachnameProf);
		
		textFieldNachnameProf = new JTextField();
		textFieldNachnameProf.setColumns(10);
		textFieldNachnameProf.setBounds(138, 85, 152, 20);
		profTab.add(textFieldNachnameProf);
		
		JLabel labelFakultaet = new JLabel("Fakult\u00E4t:");
		labelFakultaet.setBounds(20, 120, 108, 14);
		profTab.add(labelFakultaet);
		
		textFieldFakProf = new JTextField();
		textFieldFakProf.setColumns(10);
		textFieldFakProf.setBounds(138, 117, 152, 20);
		profTab.add(textFieldFakProf);
		
		radioButtonProf = new JRadioButton("Adresse vorhanden");
		radioButtonProf.setBounds(411, 79, 161, 23);
		profTab.add(radioButtonProf);
		
		JLabel labelSraﬂeProf = new JLabel("Stra\u00DFe:");
		labelSraﬂeProf.setBounds(360, 145, 93, 14);
		profTab.add(labelSraﬂeProf);
		
		JLabel labelHNrProf = new JLabel("Hausnummer:");
		labelHNrProf.setBounds(360, 179, 93, 14);
		profTab.add(labelHNrProf);
		
		JLabel labelOrtProf = new JLabel("Ort:");
		labelOrtProf.setBounds(360, 217, 46, 14);
		profTab.add(labelOrtProf);
		
		JLabel labelPLZProf = new JLabel("PLZ:");
		labelPLZProf.setBounds(360, 253, 46, 14);
		profTab.add(labelPLZProf);
		
		texFieldSraﬂeProf = new JTextField();
		texFieldSraﬂeProf.setColumns(10);
		texFieldSraﬂeProf.setBounds(463, 142, 161, 20);
		profTab.add(texFieldSraﬂeProf);
		
		textFieldHNrProf = new JTextField();
		textFieldHNrProf.setColumns(10);
		textFieldHNrProf.setBounds(463, 176, 161, 20);
		profTab.add(textFieldHNrProf);
		
		textFieldOrtProf = new JTextField();
		textFieldOrtProf.setColumns(10);
		textFieldOrtProf.setBounds(463, 214, 161, 20);
		profTab.add(textFieldOrtProf);
		
		textFieldPLZProf = new JTextField();
		textFieldPLZProf.setColumns(10);
		textFieldPLZProf.setBounds(463, 250, 161, 20);
		profTab.add(textFieldPLZProf);
		
		Button buttonProf = new Button("Anlegen");
		buttonProf.setBounds(298, 292, 70, 22);
		profTab.add(buttonProf);
		buttonProf.setActionCommand(ACTION_CREATE_PROFESSOR);
		buttonProf.addActionListener(profControl);
		
		searchUserTab = new JPanel();
		tabbedPane.addTab("User suchen", null, searchUserTab, null);
		searchUserTab.setLayout(null);
		
		JLabel descriptUS = new JLabel("Alle Registrierten User anzeigen");
		descriptUS.setBounds(10, 11, 210, 14);
		searchUserTab.add(descriptUS);
		
		Button buttonSearchUS = new Button("Suchen");
		buttonSearchUS.setBounds(226, 11, 70, 22);
		searchUserTab.add(buttonSearchUS);
		buttonSearchUS.setActionCommand(ACTION_SEARCH_USER);
		buttonSearchUS.addActionListener(control);
		

//		tableUsers = new JTable();
//		tableUsers.setBounds(21, 50, 603, 263);
//		searchUserTab.add(tableUsers);
		

		
		searchBooksTab = new JPanel();
		tabbedPane.addTab("B\u00FCcher suchen", null, searchBooksTab, null);
		searchBooksTab.setLayout(null);
		
		JLabel descriptBS = new JLabel("Zeige alle B\u00FCcher mit dem folgenden status an:");
		descriptBS.setBounds(10, 11, 298, 14);
		searchBooksTab.add(descriptBS);
		
		comboBoxBS = new JComboBox();
		comboBoxBS.setToolTipText("all");
		comboBoxBS.setBounds(356, 7, 109, 22);
		searchBooksTab.add(comboBoxBS);
		comboBoxBS.addItem("alle");
		comboBoxBS.addItem("ausleihbar");
		
		Button searchButtonBS = new Button("Suchen");
		searchButtonBS.setBounds(483, 7, 70, 22);
		searchBooksTab.add(searchButtonBS);
		searchButtonBS.setActionCommand(ACTION_SEARCH_BOOK);
		searchButtonBS.addActionListener(control);
		
//		tablebooks = new JTable();
//		tablebooks.setBounds(20, 50, 603, 263);
//		searchBooksTab.add(tablebooks);
		

		
	}

	public String getTextFieldBuchname() {
		return this.textFieldBuchname.getText();
	}
	
	public void setTextFieldBuchname(String o) {
		this.textFieldBuchname.setText(o);
	}

	public String getTextFieldAnzahlExemplare() {
		return this.textFieldAnzahlExemplare.getText();
	}

	public String getTextFieldVornameStudent() {
		return this.textFieldVornameStudent.getText();
	}
	
	public void setTextFieldVornameStudent(String o) {
		this.textFieldVornameStudent.setText(o);
	}

	public String getTextFieldNachnameStudent() {
		return this.textFieldNachnameStudent.getText();
	}
	
	public void setTextFieldNachnameStudent(String o) {
		this.textFieldNachnameStudent.setText(o);
	}

	public String getTextFieldStudiengruppe() {
		return this.textFieldStudiengruppe.getText();
	}
	
	public void setTextFieldStudiengruppe(String o) {
		this.textFieldStudiengruppe.setText(o);
	}

	public String getTextFieldMarNR() {
		return this.textFieldMarNR.getText();
	}
	
	public void setTextFieldMarNR(String o) {
		this.textFieldMarNR.setText(o);
	}

	public String getTextfieldStraﬂeStudent() {
		return this.textfieldStraﬂeStudent.getText();
	}
	
	public void setTextfieldStraﬂeStudent(String o) {
		this.textfieldStraﬂeStudent.setText(o);
	}

	public String getTextFieldHausnummerStudent() {
		return this.textFieldHausnummerStudent.getText();
	}
	
	public void setTextFieldHausnummerStudent(String o) {
		this.textFieldHausnummerStudent.setText(o);
	}

	public String getTextFieldOrtStudent() {
		return this.textFieldOrtStudent.getText();
	}
	
	public void setTextFieldOrtStudent(String o) {
		this.textFieldOrtStudent.setText(o);
	}

	public String getTextFieldPLZStudent() {
		return this.textFieldPLZStudent.getText();
	}
	
	public void setTextFieldPLZStudent(String o) {
		this.textFieldPLZStudent.setText(o);
	}

	public String getTextFieldVornameProf() {
		return this.textFieldVornameProf.getText();
	}
	
	public void setTextFieldVornameProf(String o) {
		this.textFieldVornameProf.setText(o);
	}

	public String getTextFieldNachnameProf() {
		return this.textFieldNachnameProf.getText();
	}
	
	public void setTextFieldNachnameProf(String o) {
		this.textFieldNachnameProf.setText(o);
	}

	public String getTextFieldFakProf() {
		return this.textFieldFakProf.getText();
	}
	
	public void setTextFieldFakProf(String o) {
		this.textFieldFakProf.setText(o);
	}

	public String getTexFieldSraﬂeProf() {
		return this.texFieldSraﬂeProf.getText();
	}
	
	public void setTexFieldSraﬂeProf(String o) {
		this.texFieldSraﬂeProf.setText(o);
	}

	public String getTextFieldHNrProf() {
		return this.textFieldHNrProf.getText();
	}
	
	public void setTextFieldHNrProf(String o) {
		this.textFieldHNrProf.setText(o);
	}

	public String getTextFieldOrtProf() {
		return this.textFieldOrtProf.getText();
	}
	
	public void setTextFieldOrtProf(String o) {
		this.textFieldOrtProf.setText(o);
	}

	public String getTextFieldPLZProf() {
		return this.textFieldPLZProf.getText();
	}
	
	public void setTextFieldPLZProf(String o) {
		this.textFieldPLZProf.setText(o);
	}

	public String getTextFieldBuchNameAusleihen() {
		return this.textFieldBuchNameAusleihen.getText();
	}
	
	public void setTextFieldBuchNameAusleihen(String o) {
		this.textFieldBuchNameAusleihen.setText(o);
	}
	
	public String getTextFieldPersonArtAusleiher() {
		return this.textFieldPersonArtAusleiher.getText();
	}
	
	public void setTextFieldPersonArtAusleiher(String o) {
		this.textFieldPersonArtAusleiher.setText(o);
	}

	public String getTextFieldNachnameAusleiher() {
		return this.textFieldNachnameAusleiher.getText();
	}
	
	public void setTextFieldNachnameAusleiher(String o) {
		this.textFieldNachnameAusleiher.setText(o);
	}

	public String getTextFieldVornameAusleihender() {
		return this.textFieldVornameAusleihender.getText();
	}
	
	public void setTextFieldVornameAusleihender(String o) {
		this.textFieldVornameAusleihender.setText(o);
	}
	
	public void setTextFieldAnzahlExemplare(String o) {
		this.textFieldAnzahlExemplare.setText(o);
	}

	public void displaySearchedUsers() throws Exception {
		
			// 1. create a JTable to a generic SQL Query
			tableUsers = new JTableview(SearchController.getAllUsers());
			// 2. create JScrollPane with reference to the JTable
			scrollPane = new JScrollPane(tableUsers.getSQLTable());
			// 3. set bounds of the JTable with the JScrollPane
			scrollPane.setBounds(21, 50, 603, 263);
			// 4. add JTable with JScrollPane to the content pane
			searchUserTab.add(scrollPane);
		
	}
	
	public void displaySearchedBooksAll() throws Exception {
			
			// 1. create a JTable to a generic SQL Query
			tablebooks = new JTableview(SearchController.getAllBooks());
			// 2. create JScrollPane with reference to the JTable
			scrollPane2 = new JScrollPane(tablebooks.getSQLTable());
			// 3. set bounds of the JTable with the JScrollPane
			scrollPane2.setBounds(20, 50, 603, 263);
			// 4. add JTable with JScrollPane to the content pane
			searchBooksTab.add(scrollPane2);
		
	}
	
	public void displaySearchedBooksSelected() throws Exception {
		
			// 1. create a JTable to a generic SQL Query
			tablebooks = new JTableview(SearchController.getSelectedBooks());
			// 2. create JScrollPane with reference to the JTable
			scrollPane2 = new JScrollPane(tablebooks.getSQLTable());
			// 3. set bounds of the JTable with the JScrollPane
			scrollPane2.setBounds(20, 50, 603, 263);
			// 4. add JTable with JScrollPane to the content pane
			searchBooksTab.add(scrollPane2);
		
	}
	
	public String getComboBoxSelectedItem() {
		String selectedItem = (String)comboBoxBS.getSelectedItem();
		return selectedItem;
	}
	
	public String getComboBoxSelectedItemRentBook() {
		String selectedItem = (String)comboBoxAddBook.getSelectedItem();
		return selectedItem;
	}
	
	public String getComboBoxSelectedItemArtAusleihender() {
		String selectedItem = (String)comboBoxPersonArtAusleiher.getSelectedItem();
		return selectedItem;
	}

	public boolean getAdresseVorhandenStudent() {
		return (boolean)(rdbtnAdresseVorhandenStudent.isSelected());
	}
	
	public boolean getAdresseVorhandenProfessor() {
		return (boolean)(radioButtonProf.isSelected());
	}
}
