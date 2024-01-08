package com.josh.rsbots.bots;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DragonHideCrafter {

	static Robot bot = null;
	static int mask = InputEvent.BUTTON1_DOWN_MASK;
	static int mask2 = InputEvent.BUTTON3_DOWN_MASK;

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter number of dragon hides: ");
		int hides = scan.nextInt();
		int sets = hides / 27;
		scan.close();
		try {
			bot = new Robot();
		} catch (Exception failed) {
			System.err.println("Failed instantiating Robot: " + failed);
			return;
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (; sets > 0; sets--) {
			// open bank
			click(900, 500, 2);
			
			// load preset 5
			click(1182, 721, 2);
			
			// click dragon hide in slot 2
			click(1662, 822, 3);
			
			// start crafting
			click(1000, 700, 2);
			
			try {
				TimeUnit.SECONDS.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("DONE!");
	}

	static void click(int x, int y, int duration) {
		bot.mouseMove(x, y);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bot.mousePress(mask);
		bot.mouseRelease(mask);
	}
}
