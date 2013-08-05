package graphics.profile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Profiler {
	
	public BufferedImage draw(double[] data, int imageWidth, int imageHeight, double maxPlottedData, double minPlottedData) {
		
		BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = (Graphics2D) bi.getGraphics();
		
		g.setColor(Color.white);
		
		g.fillRect(0, 0, imageWidth, imageHeight);

		g.setColor(Color.black);
		
		for (int i=1;i<data.length;i++) {
			
			int x1 = (int) Math.rint((i-1) * imageWidth / data.length);
			
			int x2 = (int) Math.rint(i * imageWidth / data.length);
			
			int y1 = (int) Math.rint((data[i-1] - minPlottedData) / (maxPlottedData- minPlottedData) * imageHeight );
			
			int y2 = (int) Math.rint((data[i] - minPlottedData) / (maxPlottedData- minPlottedData) * imageHeight );

			System.err.println("drawing line from X: "+x1+ " , Y: "+y1+" to X: " + x2 + " , Y: " + y2);
			
			g.drawLine(x1, y1, x2, y2);
			
		}
		
		return bi;
	}

}
