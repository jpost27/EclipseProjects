package com.josh.rsbots;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class RuneScapeBot {

    protected static final Random random = new Random();
    protected final Robot robot;

    public RuneScapeBot() throws AWTException {
        this.robot = new Robot();
    }

    public RuneScapeBot(final int initDelay) throws AWTException {
        this.robot = new Robot();
        System.out.println("Starting in " + initDelay + " seconds...");
        Delay.ofSeconds(initDelay);
    }

    public void executeForDuration(Runnable runnable, Duration runTime) {
        Instant endTime = Instant.now().plus(runTime);
        while (Instant.now().isBefore(endTime)) {
            System.out.println("Executing new cycle. " + Duration.between(Instant.now(), endTime).toSeconds() + " seconds left.");
            runnable.run();
        }
    }

    public void executeNTimes(Runnable runnable, int n) {
        Instant startTime = Instant.now();
        int totalRuns = n;
        for (; n > 0; n--) {
            System.out.println(n + " cycles left.");
            if (n < totalRuns) {
                Duration timePerCycle = Duration.between(Instant.now(), startTime).dividedBy(totalRuns - n);
                System.out.println("Avg. time per cycle: " + timePerCycle.toString());
                System.out.println("Time remaining: " + timePerCycle.multipliedBy(n).toString());
            }
            runnable.run();
        }
    }

    public void moveTo(int x, int y) {
        robot.mouseMove(x, y);
    }

    public void click() {
        robot.mousePress(RsConstants.LEFT_CLICK_MASK);
        robot.mouseRelease(RsConstants.LEFT_CLICK_MASK);
    }

    public void rightClick() {
        robot.mousePress(RsConstants.RIGHT_CLICK_MASK);
        robot.mouseRelease(RsConstants.RIGHT_CLICK_MASK);
    }

    public int randomBetween(int min, int max) {
        return max == min ? min : min + random.nextInt(max - min);
    }

    public Duration randomBetween(Duration min, Duration max) {
        return (max.equals(min) ?
                min : min.plus(Duration.ofMillis(random.nextInt((int) max.minus(min).toMillis()))));
    }

}
