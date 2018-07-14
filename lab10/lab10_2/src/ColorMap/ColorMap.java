package ColorMap;

import java.util.HashMap;
import java.awt.Color;
public class ColorMap {
	private HashMap<String, Color> color;
	public ColorMap() {
		color=new HashMap<String,Color>();
		color.put("BLACK", Color.BLACK);
		color.put("BLUE", Color.BLUE);
		color.put("CYAN", Color.CYAN);
		color.put("DARK_GRAY", Color.DARK_GRAY);
		color.put("GRAY", Color.GRAY);
		color.put("GREEN", Color.GREEN);
		color.put("LIGHT_GRAY", Color.LIGHT_GRAY);
		color.put("MAGENTA", Color.MAGENTA);
		color.put("ORANGE", Color.ORANGE);
		color.put("PINK", Color.PINK);
		color.put("RED", Color.RED);
		color.put("WHITE", Color.WHITE);
		color.put("YELLOW", Color.YELLOW);
	}
	public Color getColor(String colorSelected) {
		return color.get(colorSelected);
	}
	public boolean contains(String colorSelected) {
		if(color.containsKey(colorSelected))
			return true;
		else 
			return false;
	}
}
