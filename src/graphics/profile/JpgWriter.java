package graphics.profile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class JpgWriter {
	
	public void write(File outfile, BufferedImage bi) throws FileNotFoundException, IOException{
		
		FileOutputStream out = new FileOutputStream(outfile);
		
		ImageWriter imagewriter = ImageIO.getImageWritersByFormatName("jpg").next();
		
		ImageWriteParam writerparam = imagewriter.getDefaultWriteParam();
		
		writerparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		
		writerparam.setCompressionQuality(1.0f);
		
		ImageOutputStream ios = ImageIO.createImageOutputStream(out);
		
		imagewriter.setOutput(ios);
		
		imagewriter.write(null, new IIOImage(bi, null, null), writerparam);
		
		imagewriter.dispose();

	}
}
