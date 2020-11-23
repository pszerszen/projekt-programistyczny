package View;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * A class is responsible for displaying Player's calendar
 * 
 * @author Wojciech Stokłosa
 */
@SuppressWarnings("serial")
public class calPanel extends JPanel {
	/**
	 * Private variable which contains day activities
	 */
	private Object[][] DayTable = new Object[0][2];
	/**
	 * Private button to show chosen day activities
	 */
	private JButton jButton1;
	/**
	 * Private button to show next day activities
	 */
	private JButton jButton2;
	/**
	 * Private button to show previous day activities
	 */
	private JButton jButton3;
	/**
	 * Private button to go back to main menu
	 */
	private JButton jButton4;
	/**
	 * Private combobox with semesters
	 */
	private JComboBox<String> jComboBox1;
	/**
	 * Private label with "Kalendarz"
	 */
	private JLabel jLabel1;
	/**
	 * Private label with current semester, day and day name
	 */
	private JLabel jLabel2;
	/**
	 * Private label with "Przejdź"
	 */
	private JLabel jLabel3;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JScrollPane jScrollPane1;
	/**
	 * Private table with day activities
	 */
	private JTable jTable1;
	/**
	 * Private textfield to choose number of day
	 */
	private JTextField jTextField1;
	/**
	 * Private variable which contains number of day that calendar that actually
	 * displays
	 */
	int currentDay = 0;
	/**
	 * Private variable which contains number of semester calendar that actually
	 * displays
	 */
	int currentSemester = 0;

	/**
	 * Creates new form NewJPanel
	 */
	public calPanel() {
		initComponents();
	}

	/**
	 * Initializing all components
	 */
	private void initComponents() {
		jPanel1 = new JPanel();
		jLabel1 = new JLabel();
		jPanel2 = new JPanel();
		jComboBox1 = new JComboBox<String>();
		jLabel2 = new JLabel();
		jTextField1 = new JTextField();
		jButton1 = new JButton();
		jButton1.setActionCommand("Przejdz");
		jPanel3 = new JPanel();
		jLabel3 = new JLabel();
		jPanel4 = new JPanel();
		jScrollPane1 = new JScrollPane();
		jTable1 = new JTable();
		jButton2 = new JButton();
		jButton2.setFocusable(false);
		jButton2.setActionCommand("poprzedni dzien");
		jButton3 = new JButton();
		jButton3.setFocusable(false);
		jButton3.setActionCommand("nastepny dzien");
		jButton4 = new JButton();
		jButton4.setActionCommand("back");
		jButton4.setHorizontalTextPosition(SwingConstants.CENTER);
		jButton4.setIcon(new ImageIcon("res/back.jpg"));
		jButton4.setBounds(822, 478, 109, 109);

		setPreferredSize(new Dimension(1000, 600));

		jLabel1.setText("Kalendarz");
		jLabel1.setToolTipText("");
		jLabel1.setVerticalAlignment(SwingConstants.TOP);

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addGap(164, 164, 164)
						.addComponent(jLabel1)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel1)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Semestr 1", "Semestr 2", "Semestr3", "Semestr4", "Semestr 5",
				"Semestr6", "Semestr7" }));

		jLabel2.setText("Dzień");

		jButton1.setText("Przejdź");

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jComboBox1,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jLabel2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jTextField1,
												GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton1)
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel2)
						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton1)));

		jLabel3.setText("");

		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout
						.createSequentialGroup()
						.addGap(21, 21, 21)
						.addComponent(jLabel3)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel3Layout.createSequentialGroup()
						.addGap(0, 11, Short.MAX_VALUE).addComponent(jLabel3)));

		jTable1.setModel(new DefaultTableModel(DayTable, new String[] {
				"Godziny", "Czynność" }) {
		});
		jScrollPane1.setViewportView(jTable1);

		jButton2.setText("<<");

		jButton3.setText(">>");

		GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				jPanel4Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jButton2)
						.addGap(18)
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE,
								826, Short.MAX_VALUE).addGap(18)
						.addComponent(jButton3).addContainerGap()));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																jButton2,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jButton3,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane1,
																Alignment.LEADING,
																GroupLayout.PREFERRED_SIZE,
																343,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel4.setLayout(jPanel4Layout);

		jButton4.setText("   ");

		GroupLayout layout = new GroupLayout(this);
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.TRAILING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 1000,
						Short.MAX_VALUE)
				.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 1000,
						Short.MAX_VALUE)
				.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, 1000,
						Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(
														jButton4,
														GroupLayout.PREFERRED_SIZE,
														97,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jPanel4,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jPanel1,
										GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jPanel2,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(jPanel3,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(jPanel4,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(jButton4,
										GroupLayout.PREFERRED_SIZE, 97,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(78, Short.MAX_VALUE)));
		this.setLayout(layout);
	}

	/**
	 * Add ActionListener to the buttons
	 * 
	 * @param a
	 *            ActionListener to add
	 */
	public void addActions(ActionListener a) {
		jButton1.addActionListener(a);
		jButton2.addActionListener(a);
		jButton3.addActionListener(a);
		jButton4.addActionListener(a);
	}

	/**
	 * Returns number of current day
	 * 
	 * @return number of current day
	 */
	public int getCurrentDay() {
		return currentDay;
	}

	/**
	 * Returns number of current semester
	 * 
	 * @return number of current semester
	 */
	public int getCurrentSemester() {
		return currentSemester;
	}

	/**
	 * Grants access to JComboBox with semesters
	 * 
	 * @return JComboBox with semesters
	 */
	public JComboBox<String> getjComboBox1() {
		return jComboBox1;
	}

	/**
	 * Grants access to JLabel1 component
	 * 
	 * @return jLabel1
	 */
	public JLabel getjLabel1() {
		return jLabel1;
	}

	/**
	 * Grants access to JLabel2 component
	 * 
	 * @return jLabel2
	 */
	public JLabel getjLabel2() {
		return jLabel2;
	}

	/**
	 * Grants access to JLabel3 component
	 * 
	 * @return jlabel3
	 */
	public JLabel getjLabel3() {
		return jLabel3;
	}

	/**
	 * Grants access to JTextField1 component
	 * 
	 * @return jtextfield1
	 */
	public JTextField getjTextField1() {
		return jTextField1;
	}

	/**
	 * Sets number of current day
	 * 
	 * @param currentDay
	 *            number of current day
	 */
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}

	/**
	 * Sets number of current semester
	 * 
	 * @param currentSemester
	 *            number of current semester
	 */
	public void setCurrentSemester(int currentSemester) {
		this.currentSemester = currentSemester;
	}

	/**
	 * Sets current day activities
	 * 
	 * @param DayTable
	 *            object with day activities
	 */
	public void setDayTable(Object[][] DayTable) {
		this.DayTable = DayTable;
		jTable1.setModel(new DefaultTableModel(DayTable, new String[] {
				"Godziny", "Czynnosc" }) {
		});
	}
}
