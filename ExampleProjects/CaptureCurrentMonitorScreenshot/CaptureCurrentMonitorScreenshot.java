package CaptureCurrentMonitorScreenshot;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class CaptureCurrentMonitorScreenshot {

	public static void main(String args[]) {
		Graphics2D imageGraphics = null;
		try {
			Robot robot = new Robot();
			GraphicsDevice currentDevice = MouseInfo.getPointerInfo()
					.getDevice();
			BufferedImage exportImage = robot.createScreenCapture(currentDevice
					.getDefaultConfiguration().getBounds());

			imageGraphics = (Graphics2D) exportImage.getGraphics();
			File screenshotFile = new File("./CurrentMonitorScreenshot-"+System.currentTimeMillis()+".png");
			ImageIO.write(exportImage, "png",screenshotFile);
			System.out.println("Screenshot successfully captured to '"+screenshotFile.getCanonicalPath()+"'!");
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		finally {
			imageGraphics.dispose();
		}
	}

}