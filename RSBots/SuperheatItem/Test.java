package SuperheatItem;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String args[]) {
	  Robot bot = null;
	  Scanner scan = new Scanner(System.in);
	  int bars = scan.nextInt();
	  int sets = bars/11;
	  scan.close();
	  try {
	   bot = new Robot();
	  } catch (Exception failed) {
	   System.err.println("Failed instantiating Robot: " + failed);
	  }
	  try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  int mask = InputEvent.BUTTON1_DOWN_MASK;
	  int mask2 = InputEvent.BUTTON3_DOWN_MASK;
	  for(;sets>0;sets--){
	  for(int x=0;x<11;x++)
	  {
		  bot.mouseMove(1300, 525);
		  try {
			TimeUnit.MILLISECONDS.sleep(600);
		  } catch (InterruptedException e) {
			e.printStackTrace();}
		  bot.mousePress(mask);
		  bot.mouseRelease(mask);
	      try {
				TimeUnit.MILLISECONDS.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();}
	      bot.mouseMove(1285, 545);
	      try {
				TimeUnit.MILLISECONDS.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();}
	      bot.mousePress(mask);
		  bot.mouseRelease(mask);
		  try {
				TimeUnit.MILLISECONDS.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();}
	  }
	  bot.mouseMove(700, 400);
	  try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  bot.mousePress(mask);
	  bot.mouseRelease(mask);
	  try {
			TimeUnit.MILLISECONDS.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  bot.mouseMove(1185, 615);
	  try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  bot.mousePress(mask2);
	  bot.mouseRelease(mask2);
	  try {
			TimeUnit.MILLISECONDS.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  bot.mouseMove(1180, 660);
	  try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  bot.mousePress(mask);
	  bot.mouseRelease(mask);
	  try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  bot.mouseMove(560, 555);
	  try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  bot.mousePress(mask);
	  bot.mouseRelease(mask);
	  try {
			TimeUnit.MILLISECONDS.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  }
      System.out.println("DONE!");
	 }
	}
