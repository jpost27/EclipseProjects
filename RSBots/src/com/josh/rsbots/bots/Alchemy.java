package com.josh.rsbots.bots;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Alchemy {
	public static void main(String args[]) {
          Scanner sc = new Scanner(System.in);
          int i = sc.nextInt();
          sc.close();
	  Robot bot = null;
	  
	  try {
	   bot = new Robot();
	  } catch (Exception failed) {
	   System.err.println("Failed instantiating Robot: " + failed);
	  }
	  try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();}
	  /*bot.keyPress(KeyEvent.VK_Q);
	  try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();}
      bot.keyRelease(KeyEvent.VK_Q);*/
	  /*bot.mouseMove(800, 500);
      try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();}
      System.out.println("DONE!");*/
      
	  int mask = InputEvent.BUTTON1_DOWN_MASK;
	  //bot.mouseMove(100, 100);
	  int x = 0;
	  while(x < (i*2))
	  {
		  bot.mousePress(mask);
		  bot.mouseRelease(mask);
		  try {
			TimeUnit.MILLISECONDS.sleep(1500);
		} catch (InterruptedException f) {
			f.printStackTrace();
			break;
		}
		  x++;
	  }
	 }
	}
