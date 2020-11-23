/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniGames.memory;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * 
 * @author marcinse
 */
@SuppressWarnings("serial")
public class MemoryFrame extends JFrame {
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MemoryFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(MemoryFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(MemoryFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(MemoryFrame.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MemoryFrame().setVisible(true);
			}
		});
	}

	private JLabel jLabel1;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	protected JButton EndButton;

	protected JPanel GamePanel;
	protected JLabel movesleftLabel;
	// End of variables declaration//GEN-END:variables
	protected JLabel Pole1;
	protected JLabel Pole10;
	protected JLabel Pole11;
	protected JLabel Pole12;
	protected JLabel Pole13;
	protected JLabel Pole14;
	protected JLabel Pole15;
	protected JLabel Pole16;
	protected JLabel Pole17;
	protected JLabel Pole18;
	protected JLabel Pole19;
	protected JLabel Pole2;
	protected JLabel Pole20;
	protected JLabel Pole3;
	protected JLabel Pole4;
	protected JLabel Pole5;
	protected JLabel Pole6;
	protected JLabel Pole7;
	protected JLabel Pole8;
	protected JLabel Pole9;
	protected JButton StartButton;

	/**
	 * Creates new form MemoryFrame
	 */
	public MemoryFrame() {
		initComponents();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {

		StartButton = new JButton();
		EndButton = new JButton();
		GamePanel = new JPanel();
		Pole1 = new JLabel();
		Pole2 = new JLabel();
		Pole3 = new JLabel();
		Pole4 = new JLabel();
		Pole5 = new JLabel();
		Pole6 = new JLabel();
		Pole7 = new JLabel();
		Pole8 = new JLabel();
		Pole9 = new JLabel();
		Pole10 = new JLabel();
		Pole11 = new JLabel();
		Pole12 = new JLabel();
		Pole13 = new JLabel();
		Pole14 = new JLabel();
		Pole15 = new JLabel();
		Pole16 = new JLabel();
		Pole17 = new JLabel();
		Pole18 = new JLabel();
		Pole19 = new JLabel();
		Pole20 = new JLabel();
		jLabel1 = new JLabel();
		movesleftLabel = new JLabel();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(new Color(150, 150, 150));
		setResizable(false);

		StartButton.setText("Start");

		EndButton.setText("End");

		GamePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		GamePanel.setMaximumSize(new Dimension(780, 508));
		GamePanel.setLayout(new GridLayout(4, 5, 10, 0));

		Pole1.setForeground(new Color(240, 240, 240));
		GamePanel.add(Pole1);
		GamePanel.add(Pole2);
		GamePanel.add(Pole3);
		GamePanel.add(Pole4);
		GamePanel.add(Pole5);
		GamePanel.add(Pole6);
		GamePanel.add(Pole7);
		GamePanel.add(Pole8);
		GamePanel.add(Pole9);
		GamePanel.add(Pole10);
		GamePanel.add(Pole11);
		GamePanel.add(Pole12);
		GamePanel.add(Pole13);
		GamePanel.add(Pole14);
		GamePanel.add(Pole15);
		GamePanel.add(Pole16);
		GamePanel.add(Pole17);
		GamePanel.add(Pole18);
		GamePanel.add(Pole19);
		GamePanel.add(Pole20);

		jLabel1.setText("Liczba ruchów:");

		movesleftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		movesleftLabel.setText("0");

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														GamePanel,
														GroupLayout.PREFERRED_SIZE,
														780,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		StartButton,
																		GroupLayout.PREFERRED_SIZE,
																		128,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(129,
																		129,
																		129)
																.addComponent(
																		jLabel1)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		movesleftLabel,
																		GroupLayout.PREFERRED_SIZE,
																		20,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		EndButton,
																		GroupLayout.PREFERRED_SIZE,
																		128,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(GamePanel,
										GroupLayout.PREFERRED_SIZE, 508,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														EndButton,
														GroupLayout.DEFAULT_SIZE,
														64, Short.MAX_VALUE)
												.addComponent(
														StartButton,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														movesleftLabel,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jLabel1,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents
}