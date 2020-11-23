/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.ships;

import gameData.Player;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

class action implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ShipsController.infoFrame.getMessage().setText("Czas start!");
		ShipsController.infoFrame.setVisible(true);
		ShipsController.frame.StartGame();
	}
}

class action2 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean execute = true;
		for (int i = 0; i < 6; i++) {
			if (ShipsController.frame.shipsSetted[i])
				execute = false;
		}
		if (execute) {
			ShipsController.player.locateShips();
			for (int i = 0; i < 6; i++) {
				ShipsController.frame.shipsSetted[i] = true;
			}
		}
	}
}

/**
 * class contains Computer board panel @extends JPanel
 * 
 * @see Computer
 * @see ShipsBoard
 */
@SuppressWarnings("serial")
class ComputerPanel extends JPanel {
	/*
	 * /* MouseAdapter
	 */

	private ComputerMouseAdapter[][] mouseAdapter;

	/**
	 * Computer Panel's constructor
	 */
	public ComputerPanel() {
		mouseAdapter = new ComputerMouseAdapter[12][12];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				mouseAdapter[i][j] = new ComputerMouseAdapter(i, j);
			}
		}
		int i, j;
		setLayout(new GridLayout(12, 12, 1, 0));
		setPreferredSize(new java.awt.Dimension(360, 360));
		for (i = 0; i < 12; i++) {
			for (j = 0; j < 12; j++) {
				ShipsController.computer.getFields()[i][j]
						.getOponentViewField().addMouseListener(
								mouseAdapter[i][j]);
				add(ShipsController.computer.getFields()[i][j]
						.getOponentViewField());
			}
		}
	}

	/**
	 * Makes player able to shoot
	 */
	public final void Start() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				mouseAdapter[i][j].setStart(true);
			}
		}
	}
}

/**
 * class contains Player board panel @extends JPanel
 * 
 * @see Player
 * @see ShipsBoard
 */
@SuppressWarnings("serial")
class PlayerPanel extends JPanel {

	/**
	 * MouseAdapter
	 */
	private PlayerMouseAdapter[][] mouseAdapter;

	/**
	 * PlayerPanel's constructor
	 */
	public PlayerPanel() {
		mouseAdapter = new PlayerMouseAdapter[12][12];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				mouseAdapter[i][j] = new PlayerMouseAdapter(i, j);
			}
		}
		int i, j;
		// setBackground(Color.RED);
		setLayout(new GridLayout(12, 12, 1, 0));
		setPreferredSize(new java.awt.Dimension(360, 360));
		for (i = 0; i < 12; i++) {
			for (j = 0; j < 12; j++) {
				ShipsController.player.getFields()[i][j].getPlayerViewField()
						.addMouseListener(mouseAdapter[i][j]);
				add(ShipsController.player.getFields()[i][j]
						.getPlayerViewField());
			}
		}
	}

	/**
	 * Unabling to setting new ships
	 */
	public final void Start() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				mouseAdapter[i][j].setStart(true);
			}
		}
	}
}

/**
 * 
 * @author Wojtek
 */
@SuppressWarnings("serial")
public class MyFrame extends javax.swing.JFrame {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/*
		 * Set the Nimbus look and feel
		 */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MyFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/*
		 * Create and display the form
		 */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new MyFrame().setVisible(true);
			}
		});
	}

	/**
	 * panel with CPU board
	 */
	private ComputerPanel computer;

	/**
	 * Container panel
	 */
	private javax.swing.JPanel Container;

	/**
	 * checkbox to set ship plane as vertical/hprizontal
	 */
	private javax.swing.JCheckBox Pion;

	/**
	 * panel with Player board
	 */
	private PlayerPanel player;

	/**
	 * start button
	 */
	private javax.swing.JButton Start;

	/**
	 * combobox with ships
	 */
	@SuppressWarnings("rawtypes")
	private javax.swing.JComboBox statek;
	/**
	 * Panel with tools
	 */
	private javax.swing.JPanel Tools;
	/**
	 * top panel
	 */
	private javax.swing.JPanel Top;
	/**
	 * button to set a ship
	 */
	private javax.swing.JButton UstawStatek;
	/**
	 * true if ship is already setted
	 */
	public boolean[] shipsSetted;

	/**
	 * Creates new form NewJFrame
	 */
	public MyFrame() {
		initComponents();
		setResizable(false);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		Container = new javax.swing.JPanel();
		Top = new javax.swing.JPanel();
		computer = new ComputerPanel();
		player = new PlayerPanel();
		Tools = new javax.swing.JPanel();
		Pion = new javax.swing.JCheckBox();
		statek = new javax.swing.JComboBox();
		UstawStatek = new javax.swing.JButton();
		Start = new javax.swing.JButton();
		shipsSetted = new boolean[6];

		for (int i = 0; i < 6; i++) {
			shipsSetted[i] = false;
		}
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		Top.setPreferredSize(new java.awt.Dimension(900, 20));

		javax.swing.GroupLayout TopLayout = new javax.swing.GroupLayout(Top);
		Top.setLayout(TopLayout);
		TopLayout.setHorizontalGroup(TopLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0,
				Short.MAX_VALUE));
		TopLayout.setVerticalGroup(TopLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 20,
				Short.MAX_VALUE));

		computer.setPreferredSize(new java.awt.Dimension(360, 360));

		player.setPreferredSize(new java.awt.Dimension(360, 360));

		Pion.setLabel("Pionowo");
		Pion.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PionActionPerformed(evt);
			}
		});

		statek.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"dwójka", "trójka", "czwórka", "piątka" }));

		UstawStatek.setText("Ustawiaj");
		UstawStatek.addActionListener(new action2());
		Start.setText("Odpalamy");
		Start.addActionListener(new action());

		javax.swing.GroupLayout ToolsLayout = new javax.swing.GroupLayout(Tools);
		Tools.setLayout(ToolsLayout);
		ToolsLayout
				.setHorizontalGroup(ToolsLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								ToolsLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												ToolsLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																statek,
																0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																Pion,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																ToolsLayout
																		.createSequentialGroup()
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)
																		.addComponent(
																				Start))
														.addComponent(
																UstawStatek,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		ToolsLayout
				.setVerticalGroup(ToolsLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								ToolsLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												statek,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(Pion)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(UstawStatek)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												269, Short.MAX_VALUE)
										.addComponent(Start).addContainerGap()));

		javax.swing.GroupLayout ContainerLayout = new javax.swing.GroupLayout(
				Container);
		Container.setLayout(ContainerLayout);
		ContainerLayout.setHorizontalGroup(ContainerLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(Top, javax.swing.GroupLayout.DEFAULT_SIZE, 883,
						Short.MAX_VALUE)
				.addGroup(
						ContainerLayout
								.createSequentialGroup()
								.addGap(16, 16, 16)
								.addComponent(computer, 340, 340, 340)
								.addGap(18, 18, 18)
								.addComponent(player, 340, 340, 340)
								.addGap(18, 18, 18)
								.addComponent(Tools,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap()));
		ContainerLayout
				.setVerticalGroup(ContainerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								ContainerLayout
										.createSequentialGroup()
										.addComponent(
												Top,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												ContainerLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																player,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																394,
																Short.MAX_VALUE)
														.addComponent(
																computer,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																394,
																Short.MAX_VALUE)
														.addComponent(
																Tools,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(Container,
								javax.swing.GroupLayout.DEFAULT_SIZE, 883,
								Short.MAX_VALUE).addGap(36, 36, 36)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addComponent(Container,
								javax.swing.GroupLayout.DEFAULT_SIZE, 431,
								Short.MAX_VALUE).addContainerGap()));

		pack();
	}// </editor-fold>

	private void PionActionPerformed(java.awt.event.ActionEvent evt) {
	}

	/**
	 * return ship that player check to locate
	 * 
	 * @param x
	 *            horizontal start of the ship
	 * @param y
	 *            vertical start of the ship
	 */
	public Ship getShip(int x, int y) {
		Ship ship = null;
		int size = statek.getSelectedIndex() + 2;
		boolean plane;
		plane = Pion.isSelected();
		if (size != 0 && !shipsSetted[size]) {
			if (plane) {
				ship = new Ship(size, x, y, x + size - 1, y);
				shipsSetted[size] = true;
			} else {
				ship = new Ship(size, x, y, x, y + size - 1);
				shipsSetted[size] = true;
			}
		}
		return ship;
	}

	/**
	 * beginning the battle
	 */
	public final void StartGame() {
		if (shipsSetted[2] == true && shipsSetted[3] == true
				&& shipsSetted[4] == true && shipsSetted[5] == true) {
			computer.Start();
			player.Start();
		}
	}
}