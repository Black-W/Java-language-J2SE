package lab7_2;

import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void removaAll() {
		Graphics g = getGraphics();
		super.paintComponent(g);
	}
	public void paintLine(int x1, int y1, int x2, int y2) {
		Graphics g = getGraphics();
		super.paintComponent(g);
		g.drawLine(x1, y1, x2, y2);
	}
	public void repaintLine(int x1, int y1, int x2, int y2) {
		Graphics g = getGraphics();
		g.drawLine(x1, y1, x2, y2);
	}
	public void paintRec(int x, int y, int width, int height) {
		Graphics g = getGraphics();
		super.paintComponent(g);
		g.drawRect(Math.min(x,width), Math.min(y, height), Math.abs(x-width), Math.abs(y-height));
	}
	public void repaintRec(int x, int y, int width, int height) {
		Graphics g = getGraphics();
		g.drawRect(Math.min(x,width), Math.min(y, height), Math.abs(x-width), Math.abs(y-height));
	}
}
