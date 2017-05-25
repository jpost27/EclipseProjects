package AFK;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String args[]) {
	  Robot bot = null;
	  try {
	   bot = new Robot();
	  } catch (Exception failed) {
	   System.err.println("Failed instantiating Robot: " + failed);
	  }
	  try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
			e.printStackTrace();}
      System.out.println("DONE!");
      Random rand = new Random();
	  int mask = InputEvent.BUTTON1_DOWN_MASK;
	  //bot.mouseMove(100, 100);
	  while(true)
	  {
		  int  n = rand.nextInt(500) + 10;
		  int  m = rand.nextInt(75) + 10;
		  int y = (n*m)%170;
		  y+=10;
		  bot.mousePress(mask);
		  bot.mouseRelease(mask);
		  try {
			TimeUnit.SECONDS.sleep(y);
		} catch (InterruptedException f) {
			f.printStackTrace();
			break;
		}
	  }
	 }
	}
