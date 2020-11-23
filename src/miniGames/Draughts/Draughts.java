package miniGames.Draughts;

public class Draughts {

	private static long startTime = 0;
	private static long stopTime = 0;

	public static long getGameTime() {
		return stopTime - startTime;
	}

	public static long getStartTime() {
		return startTime;
	}

	public static long getStopTime() {
		return stopTime;
	}

	public static String getTime() {
		long h = 0, m = 0, s = 0, ss = 0;
		long ms = System.currentTimeMillis() - startTime;

		if (ms >= 100)
			ss = ms / 10;
		else
			ss = ms;

		if (ms >= 1000) {
			s = ms / 1000;
			ms -= s * 1000;
			if (ms >= 100)
				ss = ms / 10;
			else
				ss = ms;
		}
		if (s >= 60) {
			m = s / 60;
			s -= m * 60;
		}
		if (m >= 60) {
			h = m / 60;
			m -= h * 60;
		}

		String stm, sts, stss;
		if (m < 10)
			stm = "0" + m;
		else
			stm = "" + m;
		if (s < 10)
			sts = "0" + s;
		else
			sts = "" + s;
		if (ss < 10)
			stss = "0" + ss;
		else
			stss = "" + ss;
		String out;
		out = stm + ":" + sts + ":" + stss;

		return out;
	}

	public static void main(String[] args) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Board().setVisible(true);
			}
		});
	}

	public static void startTime() {
		startTime = System.currentTimeMillis();
	}

	public static void stopTime() {
		stopTime = System.currentTimeMillis();
	}

	private final String[] args = new String[0];

	public Draughts() {
		main(args);
	}
}
