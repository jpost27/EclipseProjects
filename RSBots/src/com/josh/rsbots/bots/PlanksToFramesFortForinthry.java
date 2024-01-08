package com.josh.rsbots.bots;

import com.josh.rsbots.Delay;
import com.josh.rsbots.RuneScapeBot;

import java.awt.*;
import java.util.Scanner;

/**
 * Point EAST, zoomed in with camera all the way up.
 * Preset 9 needs all planks in inventory
 */
public class PlanksToFramesFortForinthry {

    private static int STARTUP_TIME = 5;
    private static RuneScapeBot rsBot;

    public static void main(String... args) throws AWTException {
        System.out.print("Enter number of planks: ");
        Scanner scan = new Scanner(System.in);
        int numItems = scan.nextInt();
        int numSets = numItems/28;
        scan.close();
        rsBot = new RuneScapeBot(STARTUP_TIME);
        rsBot.executeNTimes(PlanksToFramesFortForinthry::makeFramesFromLogs, numSets);
//        rsBot.executeNTimes(PlanksToFramesFortForinthry::makeFramesFromRefinedPlanks, numSets);
    }


    private static void makeFramesFromRefinedPlanks() {
        // Click saw to make frames
        rsBot.moveTo(1560, 680);
        rsBot.click();

        Delay.ofSeconds(3);

        // Click prompt to start processing
        rsBot.moveTo(1000, 700);
        rsBot.click();

        Delay.ofSeconds(65);

        // Click Bank chest
        rsBot.moveTo(360, 450);
        rsBot.click();

        Delay.ofSeconds(3);

        // Click preset 8
        rsBot.moveTo(1115, 755);
        rsBot.click();

        Delay.ofSeconds(1);
    }


    private static void makeFramesFromPlanks() {
        // Click saw to process logs
        rsBot.moveTo(1650, 900);
        rsBot.click();

        Delay.ofSeconds(3);

        // Click prompt to start processing
        rsBot.moveTo(1000, 700);
        rsBot.click();

        Delay.ofSeconds(19);

        // Click saw to make frames
        rsBot.moveTo(1050, 400);
        rsBot.click();

        Delay.ofSeconds(3);

        // Click prompt to start processing
        rsBot.moveTo(1000, 700);
        rsBot.click();

        Delay.ofSeconds(18);

        // Click Bank chest
        rsBot.moveTo(360, 450);
        rsBot.click();

        Delay.ofSeconds(3);

        // Click preset 9
        rsBot.moveTo(1150, 755);
        rsBot.click();

        Delay.ofSeconds(1);
    }


    private static void makeFramesFromLogs() {
        // Click saw to process logs
        rsBot.moveTo(1650, 900);
        rsBot.click();

        Delay.ofSeconds(3);

        // Click prompt to start processing
        rsBot.moveTo(1000, 700);
        rsBot.click();

        Delay.ofSeconds(35);

        // Click saw to make frames
        rsBot.moveTo(1050, 560);
        rsBot.click();

        Delay.ofSeconds(3);

        // Click prompt to start processing
        rsBot.moveTo(1000, 700);
        rsBot.click();

        Delay.ofSeconds(19);

        // Click saw to make frames
        rsBot.moveTo(1050, 400);
        rsBot.click();

        Delay.ofSeconds(3);

        // Click prompt to start processing
        rsBot.moveTo(1000, 700);
        rsBot.click();

        Delay.ofSeconds(18);

        // Click Bank chest
        rsBot.moveTo(360, 450);
        rsBot.click();

        Delay.ofSeconds(3);

        // Click preset 9
        rsBot.moveTo(1150, 755);
        rsBot.click();

        Delay.ofSeconds(1);
    }

}
