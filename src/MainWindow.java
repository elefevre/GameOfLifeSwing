import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MainWindow extends JPanel {
	private static final long[][] ACORN = new long[][] { { 0, 0 }, { 1, 0 },
			{ 1, -2 }, { 3, -1 }, { 4, 0 }, { 5, 0 }, { 6, 0 } };
	private static final long serialVersionUID = 1L;
	private final GameOfLife game;

	public MainWindow() {
		super(new BorderLayout());

		JToolBar toolBar = new JToolBar("Still draggable");
		addButtons(toolBar);
		add(toolBar, BorderLayout.PAGE_START);

		game = new GameOfLife();
		game.setInitialLiveCells(ACORN);
	}

	private void addButtons(JToolBar toolBar) {
		toolBar.add(makeNavigationButton("Delete24", "CLEAR", "Clear screen",
				"Clear"));
	}

	protected JButton makeNavigationButton(String imageName,
			String actionCommand, String toolTipText, String altText) {
		String imgLocation = "toolbarButtonGraphics/general/" + imageName
				+ ".gif";
		URL imageURL = getClass().getResource(imgLocation);

		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.setIcon(new ImageIcon(imageURL, altText));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		return button;
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

	public void step() {
		game.step();
	}
}