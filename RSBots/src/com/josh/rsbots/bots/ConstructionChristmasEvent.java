package com.josh.rsbots.bots;

import com.josh.rsbots.Delay;
import com.josh.rsbots.RuneScapeBot;

import java.awt.*;
import java.time.Duration;

public class ConstructionChristmasEvent {

    private static int STARTUP_TIME = 5;
    private static Duration runTime = Duration.ofHours(8);
    private static RuneScapeBot rsBot;

    public static void main(String... args) throws AWTException {
        rsBot = new RuneScapeBot(STARTUP_TIME);
        rsBot.executeForDuration(ConstructionChristmasEvent::constructPresents, runTime);
    }

    private static void constructPresents() {
        int numPresents = 24;

        // Click unfinished bin
        for (int i = 0; i < numPresents - 1; i++) {
            rsBot.moveTo(825, 475);
            rsBot.click();
            Delay.ofTicks(2);
        }

        // Go to crafting table
        rsBot.moveTo(1125, 350);
        rsBot.click();
        Delay.ofSeconds(3);

        // Click crafting table after each item is done
        for (int i = 0; i < numPresents; i++) {
            Delay.ofSeconds(155);
            rsBot.moveTo(970, 370);
            rsBot.click();
        }

        // Deposit in bin
        rsBot.moveTo(1300, 400);
        rsBot.click();
        Delay.ofSeconds(5);

        // Go to unfinished bin
        rsBot.moveTo(230, 540);
        rsBot.click();
        Delay.ofSeconds(5);
    }

}
