package com.josh.rsbots.bots;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AFK {

	static Random rand = new Random();
	static final int CLICK_MASK = InputEvent.BUTTON1_DOWN_MASK;

    public static void main(String args[]) throws InterruptedException, AWTException {
        Robot bot = new Robot();
		System.out.println("Setup complete!");
        delayForSecondsWithPadding(8, 0);
        for (int i = 0; i < 120; i++) {
            bot.mousePress(CLICK_MASK);
            bot.mouseRelease(CLICK_MASK);
			delayForSecondsWithPadding(15, 15);
        }
    }

	private static void delayForSecondsWithPadding(int seconds, int maxSecondsOfPadding) throws InterruptedException {
		int secondsToWait =
				(maxSecondsOfPadding > 0 ? rand.nextInt(maxSecondsOfPadding) : 0)
						+ seconds;
		TimeUnit.SECONDS.sleep(secondsToWait);
	}
}
