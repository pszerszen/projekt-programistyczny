package gameData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;

import View.ViewManager;

/**
 * The Head class of entire project witch provides cooperation between
 * algorithmic and GUI parts except mini-games
 * 
 * @author Piotr Szerszeń
 * @author Wojciech Stokłosa
 * 
 * @see View.ViewManager
 */
public class Controller implements ActionListener {
	/**
	 * Standard main method witch calls constructor of Controller
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		try {
			new Controller();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Graphic interface of the game
	 */
	private ViewManager GUI;
	/**
	 * A Value used by JFileChooser
	 */
	private int returnValue;

	/**
	 * Creates Graphical Interface object and adds ActionListener to every
	 * component that has its action command
	 */
	public Controller() {
		GUI = new ViewManager();
		GUI.addActions(this);
		GUI.getNewGame().activateThread();
		GUI.getShop().activateThread();
		GUI.getBag().activateThread();
	}

	/**
	 * Opens main panel
	 */
	private void back() {
		GUI.setContentPane(GUI.getMainPan());
		GUI.getCalendar().setVisible(false);
		GUI.getIndex().setVisible(false);
		GUI.getBag().setVisible(false);
		GUI.getNewGame().setVisible(false);
		GUI.getShop().setVisible(false);
	}

	/**
	 * Opens player's equipment perspective
	 */
	private void Bag() {
		GUI.getBag().setItemList(DataBase.player.getInActiveItems());
		GUI.getBag().getItemlist().setSelectedIndex(0);

		GUI.setContentPane(GUI.getBag());
		GUI.getBag().setVisible(true);
	}

	/**
	 * Opens player's schedule perspective
	 */
	private void Callendar() {
		int NumberOfSemester = DataBase.Round / (120 * 24) + 1;
		int NumberOfDay = ((DataBase.Round - (NumberOfSemester - 1) * 120 * 24) / 24) + 1;
		if (NumberOfDay > 0 && NumberOfDay <= 120) {
			GUI.getCalendar().setDayTable(
					DataBase.cal.getDayActivities(NumberOfSemester - 1,
							NumberOfDay - 1));
			GUI.getCalendar()
					.getjLabel3()
					.setText(
							"Semestr "
									+ NumberOfSemester
									+ " Dzień "
									+ NumberOfDay
									+ ", "
									+ DataBase.cal.getDayName(
											NumberOfSemester - 1,
											NumberOfDay - 1));
			GUI.getCalendar().setCurrentDay(NumberOfDay);
			GUI.getCalendar().setCurrentSemester(NumberOfSemester);
			GUI.getCalendar().getjTextField1()
					.setText(Integer.toString(NumberOfDay));
		}
		GUI.setContentPane(GUI.getCalendar());
		GUI.getCalendar().setVisible(true);
	}

	/**
	 * Opens shop perspective
	 */
	private void Cart() {
		GUI.getShop().setItemList(DataBase.items);
		GUI.getShop().getItemlist().setSelectedIndex(0);

		GUI.setContentPane(GUI.getShop());
		GUI.getShop().setVisible(true);
	}

	private boolean DidWon() {
		boolean Won = true;
		for (int i = 0; i < DataBase.courses.size(); i++)
			if (DataBase.courses.get(i).getGrade() >= 3)
				Won = false;
		return Won;
	}

	/**
	 * Opens player's matricula perspective
	 */
	private void Indeks() {
		GUI.setContentPane(GUI.getIndex());
		GUI.getIndex().setVisible(true);
		GUI.getIndex().setTable(DataBase.courses);
	}

	/**
	 * Purchases an item by player
	 */
	private void Kup() {
		String itemName = (String) GUI.getShop().getItemlist()
				.getSelectedValue();
		int i = 0;
		while (!DataBase.items.get(i).getName().equals(itemName))
			i++;
		if (i < DataBase.items.size()) {
			Item item = DataBase.items.get(i);
			if (DataBase.player.getMoney() >= item.getPrice()) {
				DataBase.player.buyItem(item);
				DataBase.player.setMoney(DataBase.player.getMoney()
						- item.getPrice());
				GUI.getShop().getCash()
						.setText(Integer.toString(DataBase.player.getMoney()));
				String log = "Kupiles/las " + item.getName();
				DataBase.log.AddLine(log);
				// GUI.addLog(log);
			}
		}
		GUI.getBag().setItemList(DataBase.player.getInActiveItems());

	}

	/**
	 * Creates new game by using {link View.NewGame #CreateNewPlayer()} and sets
	 * the {@link DataBase} up
	 */
	private void makePlayer() {
		try {
			DataBase.theEnd();
			DataBase.loadActivities();
			DataBase.Round = 0;
			GUI.getNewGame().CreateNewPlayer();
			GUI.setPName(DataBase.player.getName() + "  "
					+ DataBase.player.getSurname());
			GUI.getNewGame().setVisible(false);
			GUI.setContentPane(GUI.getMainPan());
			DataBase.loadItems();
			DataBase.loadKursy("res/" + GUI.getNewGame().getMajor() + ".xml");
			DataBase.drawLecturers();
			DataBase.loadDistributions();
			GUI.setLog("Jak dotąd w grze:");
			GUI.refreshTime();
			for (int i = 0; i < DataBase.courses.size(); i++) {
				DataBase.courses.get(i).RandomDates();
			}
			GUI.refreshActivitiesView();
			// GUI.getNewGame().deActivateThread();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Blednie wprowadzone dane",
					"Blad", JOptionPane.WARNING_MESSAGE);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Passes to the next day in calendar perspective
	 */
	private void Nastepny() {
		int NumberOfSemester = GUI.getCalendar().getCurrentSemester();
		int NumberOfDay = GUI.getCalendar().getCurrentDay() + 1;
		if (NumberOfDay > 0 && NumberOfDay <= 120) {
			GUI.getCalendar().setDayTable(
					DataBase.cal.getDayActivities(NumberOfSemester - 1,
							NumberOfDay - 1));
			GUI.getCalendar()
					.getjLabel3()
					.setText(
							"Semestr "
									+ NumberOfSemester
									+ " Dzień "
									+ NumberOfDay
									+ ", "
									+ DataBase.cal.getDayName(
											NumberOfSemester - 1,
											NumberOfDay - 1));
			GUI.getCalendar().setCurrentDay(NumberOfDay);
			GUI.getCalendar().getjTextField1()
					.setText(Integer.toString(NumberOfDay));
		}
	}

	/**
	 * Opens new game creator perspective
	 */
	private void NowaGra() {
		GUI.setContentPane(GUI.getNewGame());
		GUI.getNewGame().setVisible(true);
		GUI.refreshActivitiesView();
	}

	/**
	 * Passes to the previous day in calendar perspective
	 */
	private void Poprzedni() {
		int NumberOfSemester = GUI.getCalendar().getCurrentSemester();
		int NumberOfDay = GUI.getCalendar().getCurrentDay() - 1;
		if (NumberOfDay > 0 && NumberOfDay <= 120) {
			GUI.getCalendar().setDayTable(
					DataBase.cal.getDayActivities(NumberOfSemester - 1,
							NumberOfDay - 1));
			GUI.getCalendar()
					.getjLabel3()
					.setText(
							"Semestr "
									+ NumberOfSemester
									+ " Dzień "
									+ NumberOfDay
									+ ", "
									+ DataBase.cal.getDayName(
											NumberOfSemester - 1,
											NumberOfDay - 1));
			GUI.getCalendar().setCurrentDay(NumberOfDay);
			GUI.getCalendar().getjTextField1()
					.setText(Integer.toString(NumberOfDay));
		}
	}

	/**
	 * Passes to the specified by player day in calendar perspective
	 */
	private void Przejdz() {
		int NumberOfSemester = GUI.getCalendar().getjComboBox1()
				.getSelectedIndex() + 1;
		try {
			int NumberOfDay = Integer.parseInt(GUI.getCalendar()
					.getjTextField1().getText());
			if (NumberOfDay > 0 && NumberOfDay <= 120) {
				GUI.getCalendar().setDayTable(
						DataBase.cal.getDayActivities(NumberOfSemester - 1,
								NumberOfDay - 1));
				GUI.getCalendar()
						.getjLabel3()
						.setText(
								"Semestr "
										+ NumberOfSemester
										+ " Dzień "
										+ NumberOfDay
										+ ", "
										+ DataBase.cal.getDayName(
												NumberOfSemester - 1,
												NumberOfDay - 1));
				GUI.getCalendar().setCurrentDay(NumberOfDay);
				GUI.getCalendar().setCurrentSemester(NumberOfSemester);
			}
		} catch (NumberFormatException ex1) {
		}
	}

	/**
	 * Uses up the item by player
	 */
	private void Uzyj() {
		String itemName = (String) GUI.getBag().getItemlist()
				.getSelectedValue();
		int i = 0;
		while (!DataBase.items.get(i).getName().equals(itemName))
			i++;
		Item item = DataBase.items.get(i);
		DataBase.player.useItem(item);
		GUI.getBag().setItemList(DataBase.player.getInActiveItems());
		String log = "Użyles/las " + item.getName();
		DataBase.log.AddLine(log);
		// GUI.addLog(log);
	}

	/**
	 * Loads the game from XML file
	 */
	private void Wczytaj() {
		returnValue = GUI.getFileChooser().showOpenDialog(null);
		switch (returnValue) {
		case JFileChooser.APPROVE_OPTION:
			try {
				DataBase.loadSavedGame(GUI.getFileChooser().getSelectedFile()
						.getPath());
				GUI.setPName(DataBase.player.getName() + "  "
						+ DataBase.player.getSurname());
				for (int i = 0; i < DataBase.log.getLog().size(); i++) {
					// GUI.addLog(DataBase.log.getLog().get(i));
				}
				GUI.refreshActivitiesView();
			} catch (FileNotFoundException | XMLStreamException e) {
				e.printStackTrace();
			}
			break;
		case JFileChooser.ERROR_OPTION:
			System.out.println("ERR");
			break;
		}
	}

	/**
	 * Performs a certain activity by player
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void Wykonaj() throws FileNotFoundException, IOException {
		try {
			String a = GUI.getActivityList().getSelectedValue();
			int DB = DataBase.Round;
			DataBase.getActivity(a).Effects(GUI.getRounds());
			DataBase.player.makeRounds(DataBase.Round - DB);
			GUI.refreshActivitiesView();
			if (DataBase.Round >= 20159) {
				if (DidWon())
					DataBase.won();
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}

	/**
	 * Saves game to XML file, which is currently operated on. If not specified
	 * method acts as ZapiszJako() method.
	 */
	private void Zapisz() {
		if (DataBase.currentFileName != "") {
			try {
				DataBase.saveGame(DataBase.currentFileName);
			} catch (IOException | XMLStreamException e) {
				e.printStackTrace();
			}
		} else {
			ZapiszJako();
		}
	}

	/**
	 * Saves game to XML file.
	 */
	private void ZapiszJako() {
		returnValue = GUI.getFileChooser().showSaveDialog(null);
		switch (returnValue) {
		case JFileChooser.APPROVE_OPTION:
			try {
				DataBase.saveGame(GUI.getFileChooser().getSelectedFile()
						.getPath());
			} catch (XMLStreamException | IOException e) {
				e.printStackTrace();
			}
			break;
		case JFileChooser.ERROR_OPTION:
			System.out.println("ERR");
			break;
		}
		GUI.getFileChooser().setSelectedFile(new File(""));
	}

	/**
	 * An overridden method monitoring actions called in the game and calling
	 * proper method to do the action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Nowa gra")) {
			NowaGra();

		} else if (e.getActionCommand().equals("Wczytaj")) {
			Wczytaj();

		} else if (e.getActionCommand().equals("Zapisz")) {
			Zapisz();

		} else if (e.getActionCommand().equals("Zapisz jako...")) {
			ZapiszJako();

		} else if (e.getActionCommand().equals("Wyjdz")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("Callendar")) {
			Callendar();

		} else if (e.getActionCommand().equals("Indeks")) {
			Indeks();

		} else if (e.getActionCommand().equals("Bag")) {
			Bag();

		} else if (e.getActionCommand().equals("Cart")) {
			Cart();

		} else if (e.getActionCommand().equals("Wykonaj")) {
			try {
				Wykonaj();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else if (e.getActionCommand().equals("back")) {
			back();

		} else if (e.getActionCommand().equals("uzyj")) {
			Uzyj();

		} else if (e.getActionCommand().equals("Kup")) {
			Kup();

		} else if (e.getActionCommand().equals("poprzedni dzien")) {
			Poprzedni();

		} else if (e.getActionCommand().equals("nastepny dzien")) {
			Nastepny();

		} else if (e.getActionCommand().equals("Przejdz")) {
			Przejdz();

		} else if (e.getActionCommand().equals("makePlayer")) {
			makePlayer();

		} else if (e.getActionCommand().equals("flush")) {
			GUI.getNewGame().flush();
		}

	}
}
