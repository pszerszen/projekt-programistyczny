package View;

import gameData.DataBase;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 * Class which collects data about new player and creates new game Status
 * 
 * @author Piotr Szerszeń
 * 
 * @see gameData.DataBase
 * 
 */
@SuppressWarnings("serial")
public class NewGame extends JPanel {
	/**
	 * A button causing return to main Panel of the game
	 */
	private JButton back;
	/**
	 * A component where player is supposed to set his/her charm level
	 */
	private JSpinner charm;
	/**
	 * SpinnerNumberModel for charm JSpinner
	 */
	private SpinnerNumberModel cmodel;
	/**
	 * A component where player is supposed to choose his/her major
	 */
	private JComboBox<String> comboBox;
	/**
	 * A button that calls action that gathers all data and creates new player
	 */
	private JButton createPlayer;
	/**
	 * A button that calls action setting all components to default values
	 */
	private JButton flush;
	/**
	 * SpinnerNumberModel for intel JSpinner
	 */
	private SpinnerNumberModel imodel;
	/**
	 * A component where player is supposed to set his/her intelligence level
	 */
	private JSpinner intel;
	/**
	 * A field where player is supposed to type his/her name
	 */
	private JTextField name;
	/**
	 * A thread that shows onboard how many points left player can spend
	 */
	private Thread pointLeftSetter;
	/**
	 * Initial value of all points to spend
	 */
	private final int POINTS = 30;
	/**
	 * Shows how many more points player can spend
	 */
	private JTextField pointsLeft;
	/**
	 * A component where player is supposed to set his/her stress resistance
	 * level
	 */
	private JSpinner resistance;
	/**
	 * SpinnerNumberModel for resistance JSpinner
	 */
	private SpinnerNumberModel rmodel;
	/**
	 * SpinnerNumberModel for scroupulatio JSpinner
	 */
	private SpinnerNumberModel scmodel;
	/**
	 * A component where player is supposed to set his/her scrupulousness level
	 */
	private JSpinner scroupulatio;
	/**
	 * SpinnerNumberModel for stamina JSpinner
	 */
	private SpinnerNumberModel smodel;
	/**
	 * A component where player is supposed to set his/her endurance level
	 */
	private JSpinner stamina;
	/**
	 * A field where player is supposed to type his/her surname
	 */
	private JTextField surname;

	/**
	 * Initializes newly created panel for creating new game status
	 */
	public NewGame() {
		super();
		setPreferredSize(new Dimension(1000, 600));
		setSize(new Dimension(1000, 600));
		setLayout(null);

		JLabel lblKreatorGracza = new JLabel("Kreator Gracza");
		lblKreatorGracza.setFont(new Font("Calibri", Font.BOLD, 30));
		lblKreatorGracza.setHorizontalTextPosition(SwingConstants.CENTER);
		lblKreatorGracza.setHorizontalAlignment(SwingConstants.CENTER);
		lblKreatorGracza.setBounds(231, 54, 575, 37);
		add(lblKreatorGracza);

		JLabel lblImie = new JLabel("Imi\u0119");
		lblImie.setBounds(132, 170, 126, 14);
		lblImie.setFont(ViewManager.f);
		add(lblImie);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(132, 195, 126, 14);
		lblNazwisko.setFont(ViewManager.f);
		add(lblNazwisko);

		JLabel lblInteligencja = new JLabel("Inteligencja");
		lblInteligencja.setBounds(132, 220, 126, 14);
		lblInteligencja.setFont(ViewManager.f);
		add(lblInteligencja);

		JLabel lblCharyzma = new JLabel("Charyzma");
		lblCharyzma.setBounds(132, 245, 126, 14);
		lblCharyzma.setFont(ViewManager.f);
		add(lblCharyzma);

		JLabel lblWytrzymaosc = new JLabel("Wytrzymao\u015B\u0107");
		lblWytrzymaosc.setBounds(132, 270, 126, 14);
		lblWytrzymaosc.setFont(ViewManager.f);
		add(lblWytrzymaosc);

		JLabel lblOdporno = new JLabel("Odporno\u015B\u0107 na stres");
		lblOdporno.setBounds(132, 295, 126, 14);
		lblOdporno.setFont(ViewManager.f);
		add(lblOdporno);

		JLabel lblSkrupulatno = new JLabel("Skrupulatno\u015B\u0107");
		lblSkrupulatno.setBounds(132, 320, 126, 14);
		lblSkrupulatno.setFont(ViewManager.f);
		add(lblSkrupulatno);

		name = new JTextField();
		name.setBounds(268, 169, 86, 20);
		name.setFont(ViewManager.fs);
		add(name);
		name.setColumns(10);

		surname = new JTextField();
		surname.setBounds(268, 192, 86, 20);
		surname.setFont(ViewManager.fs);
		add(surname);
		surname.setColumns(10);

		imodel = new SpinnerNumberModel(new Integer(1), new Integer(1), null,
				new Integer(1));

		intel = new JSpinner();
		intel.setModel(imodel);
		intel.setBounds(268, 217, 86, 20);
		add(intel);

		cmodel = new SpinnerNumberModel(new Integer(1), new Integer(1), null,
				new Integer(1));
		charm = new JSpinner();
		charm.setBounds(268, 242, 86, 20);
		charm.setModel(cmodel);
		add(charm);

		smodel = new SpinnerNumberModel(new Integer(1), new Integer(1), null,
				new Integer(1));
		stamina = new JSpinner();
		stamina.setBounds(268, 267, 86, 20);
		stamina.setModel(smodel);
		add(stamina);

		rmodel = new SpinnerNumberModel(1, 1, null, 1);
		resistance = new JSpinner();
		resistance.setBounds(268, 292, 86, 20);
		resistance.setModel(rmodel);
		add(resistance);

		scmodel = new SpinnerNumberModel(new Integer(1), new Integer(1), null,
				new Integer(1));
		scroupulatio = new JSpinner();
		scroupulatio.setBounds(268, 317, 86, 20);
		scroupulatio.setModel(scmodel);
		add(scroupulatio);

		pointsLeft = new JTextField("30");
		pointsLeft.setEditable(false);
		pointsLeft.setBackground(Color.WHITE);
		pointsLeft.setBounds(268, 357, 86, 20);
		pointsLeft.setFont(ViewManager.fs);
		add(pointsLeft);

		JLabel lblPozostalePunkty = new JLabel("Pozostale punkty");
		lblPozostalePunkty.setBounds(132, 359, 126, 16);
		lblPozostalePunkty.setFont(ViewManager.f);
		add(lblPozostalePunkty);

		createPlayer = new JButton("Stw\u00F3rz Gracza");
		createPlayer.setFont(new Font("Calibri", Font.PLAIN, 25));
		createPlayer.setActionCommand("makePlayer");
		createPlayer.setBounds(588, 170, 192, 45);
		add(createPlayer);

		flush = new JButton("Wyczy\u015B\u0107");
		flush.setFont(new Font("Calibri", Font.PLAIN, 25));
		flush.setActionCommand("flush");
		flush.setBounds(588, 233, 192, 45);
		add(flush);

		back = new JButton("   ");
		back.setActionCommand("back");
		back.setHorizontalTextPosition(SwingConstants.CENTER);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setIcon(new ImageIcon("res/back.jpg"));
		back.setBounds(836, 414, 109, 109);
		add(back);

		JLabel lblKierunekStudiw = new JLabel("Kierunek Studi\u00F3w:");
		lblKierunekStudiw.setHorizontalAlignment(SwingConstants.CENTER);
		lblKierunekStudiw.setHorizontalTextPosition(SwingConstants.CENTER);
		lblKierunekStudiw.setBounds(132, 400, 126, 32);
		lblKierunekStudiw.setFont(ViewManager.f);
		add(lblKierunekStudiw);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Informatyka", "Matematyka" }));
		comboBox.setBounds(132, 431, 126, 22);
		add(comboBox);

		pointLeftSetter = new Thread() {
			public void run() {
				while (true) {
					try {
						int p = POINTS
								- (imodel.getNumber().intValue()
										+ cmodel.getNumber().intValue()
										+ smodel.getNumber().intValue()
										+ rmodel.getNumber().intValue() + scmodel
										.getNumber().intValue());
						setPointsLeft(p);
						sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	/**
	 * Checks if player typed proper data
	 * 
	 * @return true if data given from player are correct
	 */
	private boolean dataChecker() {
		boolean ok = true;
		if ((name.getText() == "")
				|| (surname.getText() == "" || getPointsLeft() < 0)) {
			ok = false;
		}
		System.out.println(ok);
		return ok;
	}

	/**
	 * Gives value of points player can spend as int
	 * 
	 * @return value of points player can spend as int
	 */
	private int getPointsLeft() {
		return Integer.parseInt(pointsLeft.getText());
	}

	/**
	 * Sets value of points player can spend
	 * 
	 * @param pointsLeft
	 *            value of remaining points to spend
	 */
	private void setPointsLeft(Integer pointsLeft) {
		this.pointsLeft.setText(pointsLeft.toString());
	}

	/**
	 * Activates Thread object responsible for proper displaying parameters on
	 * this panel
	 */
	public void activateThread() {
		pointLeftSetter.start();
	}

	/**
	 * Adds specified ActionListener to JButtons on this panel
	 * 
	 * @param a
	 *            ActionListener object to add
	 */
	public void addAction(ActionListener a) {
		createPlayer.addActionListener(a);
		flush.addActionListener(a);
		back.addActionListener(a);
	}

	/**
	 * Gathers data on panel and creates new game
	 */
	public void CreateNewPlayer() throws IOException {
		if (dataChecker()) {
			DataBase.player.setName(name.getText());
			DataBase.player.setSurname(surname.getText());
			DataBase.player.setInteligence(imodel.getNumber().intValue());
			DataBase.player.setCharisma(cmodel.getNumber().intValue());
			DataBase.player.setEndurance(smodel.getNumber().intValue());
			DataBase.player.setStressResistance(rmodel.getNumber().intValue());
			DataBase.player.setScrupulousness(scmodel.getNumber().intValue());
			DataBase.player.setMoney(500);
			DataBase.FieldOfStudy = comboBox.getSelectedItem().toString();
		} else
			throw (new IOException());
	}

	/**
	 * Deactivates Thread objects responsible for proper displaying parameters
	 * on this panel
	 */
	@SuppressWarnings("deprecation")
	public void deActivateThread() {
		pointLeftSetter.stop();
	}

	/**
	 * Resets every editable component on this panel to default value
	 */
	public void flush() {
		name.setText("");
		surname.setText("");
		imodel.setValue(1);
		cmodel.setValue(1);
		smodel.setValue(1);
		rmodel.setValue(1);
		scmodel.setValue(1);
		setPointsLeft(0);
	}

	/**
	 * Returns Major selected by Player as String
	 * 
	 * @return Major selected by player
	 */
	public String getMajor() {
		return comboBox.getSelectedItem().toString();
	}
}
