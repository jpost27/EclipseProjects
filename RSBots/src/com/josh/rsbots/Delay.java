package com.josh.rsbots;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Delay {

    private Delay() {}

    public static void ofTicks(int numOfTicks) {
        ofDuration(RsConstants.ONE_TICK.multipliedBy(numOfTicks));
    }

    public static void ofSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void ofDuration(Duration duration) {
        try {
            TimeUnit.MILLISECONDS.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}