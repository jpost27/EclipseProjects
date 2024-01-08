package com.josh.rsbots.bots;

import com.josh.rsbots.Delay;
import com.josh.rsbots.RuneScapeBot;

import java.awt.*;
import java.time.Duration;

public class FishingChristmasEvent {

    private static int STARTUP_TIME = 5;
    private static Duration runTime = Duration.ofHours(8);
    private static RuneScapeBot rsBot;

    public static void main(String... args) throws AWTException {
        rsBot = new RuneScapeBot(STARTUP_TIME);
        rsBot.executeForDuration(FishingChristmasEvent::goFish, runTime);
    }

    private static void goFish() {
        // Move to and click water
        rsBot.moveTo(250, 530);
        rsBot.click();

        // Wait between 2min and 2min 30sec
        Delay.ofDuration(rsBot.randomBetween(Duration.ofSeconds(120), Duration.ofSeconds(150)));

        // Move to and click barrel to deposit fish
        rsBot.moveTo(1450, 400);
        rsBot.click();

        // Wait between 5 and 8 sec
        Delay.ofDuration(rsBot.randomBetween(Duration.ofSeconds(5), Duration.ofSeconds(8)));
    }

}
