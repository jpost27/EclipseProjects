package com.josh.rsbots.bots;

import com.josh.rsbots.Delay;
import com.josh.rsbots.RuneScapeBot;

import java.awt.*;
import java.time.Duration;

public class Woodcutting {

    private static int STARTUP_TIME = 5;
    private static Duration runTime = Duration.ofHours(3);
    private static RuneScapeBot rsBot;

    public static void main(String... args) throws AWTException {
        rsBot = new RuneScapeBot(STARTUP_TIME);
        rsBot.executeForDuration(Woodcutting::clickAtRandom, runTime);
    }

    private static void clickAtRandom() {
        rsBot.click();

        Delay.ofDuration(rsBot.randomBetween(Duration.ofSeconds(10), Duration.ofSeconds(30)));
    }

}
