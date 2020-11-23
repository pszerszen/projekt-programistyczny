package View;

import gameData.Course;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * A class responsible for displaying Player's courses
 * 
 * @author Piotr Szerszeń
 * 
 * @see gameData.Course
 * @see gameData.Player
 * 
 */
@SuppressWarnings("serial")
public class IndexView extends JPanel {
	/**
	 * A button causing return to main Panel of the game
	 */
	private JButton back;
	/**
	 * TableModel for table field
	 */
	private DefaultTableModel model;
	/**
	 * Graphical implementation of table containing all information about
	 * player's courses, lecturers, courses ECTS points, grades and if course is
	 * taken more than one time
	 */
	private JTable table;

	/**
	 * Initializes newly created panel of Player's matricula
	 */
	public IndexView() {
		setPreferredSize(new Dimension(1000, 650));
		setLayout(null);
		JLabel lblIndeks = new JLabel("INDEKS");
		lblIndeks.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIndeks.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndeks.setFocusable(false);
		lblIndeks.setFont(new Font("Calibri", Font.BOLD, 63));
		lblIndeks.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblIndeks.setBounds(10, 30, 980, 78);
		add(lblIndeks);

		back = new JButton("   ");
		back.setActionCommand("back");
		back.setHorizontalTextPosition(SwingConstants.CENTER);
		back.setIcon(new ImageIcon("res/back.jpg"));
		back.setBounds(824, 479, 109, 109);
		add(back);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 131, 915, 313);
		add(scrollPane);

		model = new DefaultTableModel(new String[][] {}, new String[] { "Kurs",
				"Prowdzący", "ECTS", "Powtórka", "Ocena" }) {
			@SuppressWarnings("rawtypes")
			Class[] columntypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columntypes[columnIndex];
			}
		};

		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrollPane.setViewportView(table);
	}

	/**
	 * Adds one course to table
	 * 
	 * @param c
	 *            Course which is added to table
	 */
	private void addCourseToIndex(Course c) {
		String t = c.getTitle();
		String n = c.getLecturer().getDegree() + " "
				+ c.getLecturer().getName() + " "
				+ c.getLecturer().getSurname();
		String r;
		if (c.isRetake())
			r = "tak";
		else
			r = "nie";
		String e = "" + c.getECTS();
		String g = "" + c.getGrade() / c.getNumberOfColloquium();
		model.addRow(new String[] { t, n, e, r, g });
	}

	/**
	 * Grants access to JButton component
	 * 
	 * @return JButton back
	 */
	public JButton getBack() {
		return back;
	}

	/**
	 * Cleans table up and then fills with Player's courses
	 * 
	 * @param courses
	 *            Player object whose courses are to be displayed
	 */
	public void setTable(ArrayList<Course> courses) {
		model.setRowCount(0);
		for (Course c : courses) {
			addCourseToIndex(c);
		}
	}

}
