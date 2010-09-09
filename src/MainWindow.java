import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainWindow extends JPanel {

	private static final long[][] ACORN = new long[][] { { 0, 0 }, { 1, 0 },
			{ 1, -2 }, { 3, -1 }, { 4, 0 }, { 5, 0 }, { 6, 0 } };
	private static final long serialVersionUID = 1L;
	private static Timer timer;
	private final GameOfLife game;

	public MainWindow() {
		game = new GameOfLife();
		game.setInitialLiveCells(ACORN);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.blue);
		Dimension size = getSize();
		Insets insets = getInsets();

		int w = size.width - insets.left - insets.right;
		int h = size.height - insets.top - insets.bottom;
		int zoom = 3;

		for (GameOfLife.Coordinates liveCell : game.allLiveCells()) {
			int x = (int) (liveCell.x * zoom) % w + w / 2;
			int y = (int) (liveCell.y * zoom) % h + h / 2;
			g2d.drawRect(x, y, zoom, zoom);
			g2d.fillRect(x, y, zoom, zoom);
		}

	}

	public static void main(String[] args) throws InterruptedException {
		final MainWindow points = new MainWindow();
		final JFrame frame = new JFrame("Points");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(points);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				points.game.step();
				timer.restart();
				frame.repaint();
			}
		});
		timer.start();
	}

	public void step() {
		game.step();
	}
}