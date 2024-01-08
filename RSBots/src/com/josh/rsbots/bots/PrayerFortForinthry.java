package com.josh.rsbots.bots;

import com.josh.rsbots.Delay;
import com.josh.rsbots.RuneScapeBot;

import java.awt.*;
import java.util.Scanner;

/**
 * Point SOUTH, zoomed all the way out with camera all the way up.
 * Preset 2 needs all bones in inventory
 */
public class PrayerFortForinthry {

    private static int STARTUP_TIME = 5;
    private static RuneScapeBot rsBot;

    public static void main(String... args) throws AWTException {
        System.out.print("Enter number of bones: ");
        Scanner scan = new Scanner(System.in);
        int numBones = scan.nextInt();
        int numSets = numBones/28;
        scan.close();
        rsBot = new RuneScapeBot(STARTUP_TIME);
        rsBot.executeNTimes(PrayerFortForinthry::offerBones, numSets);
    }


    private static void offerBones() {
        // Right Click on altar
        rsBot.moveTo(465, 650);
        rsBot.rightClick();

        Delay.ofTicks(1);

        // Click on offer bones
        rsBot.moveTo(465, 710);
        rsBot.click();

        Delay.ofSeconds(55);

        // Click Bank chest
        rsBot.moveTo(1285, 475);
        rsBot.click();

        Delay.ofSeconds(4);

        // Click preset 2
        rsBot.moveTo(1085, 720);
        rsBot.click();

        Delay.ofSeconds(1);
    }
}
