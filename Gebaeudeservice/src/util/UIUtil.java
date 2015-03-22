package util;

import java.awt.Color;

import org.jdesktop.swingx.painter.CompoundPainter;
import org.jdesktop.swingx.painter.GlossPainter;
import org.jdesktop.swingx.painter.MattePainter;

public class UIUtil {
	
	public static CompoundPainter getButtonPanelPainter() {
		GlossPainter gloss = new GlossPainter(
				new Color(1.0f, 1.0f, 1.0f, 0.2f),
				GlossPainter.GlossPosition.TOP);
		MattePainter matte = new MattePainter(Color.DARK_GRAY);
		return new CompoundPainter(matte, gloss);
	}
	
	public static Color getStandardColor(){
		return new Color(19,123,64);
	}

	public static Color getSecondColor(){
		return new Color(165,202,152);
	}
}
