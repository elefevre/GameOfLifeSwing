import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {
	private static final long serialVersionUID = 1L;
	private static Timer timer;

	public static void main(String[] args) throws InterruptedException {
		final MainWindow window = new MainWindow();
		final JFrame frame = new JFrame("Points");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(window);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.step();
				timer.restart();
				frame.repaint();
			}
		});
		timer.start();
	}
}