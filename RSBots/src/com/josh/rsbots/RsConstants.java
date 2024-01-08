package com.josh.rsbots;

import java.awt.event.InputEvent;
import java.time.Duration;

public class RsConstants {

    public static final int LEFT_CLICK_MASK = InputEvent.BUTTON1_DOWN_MASK;
    public static final int RIGHT_CLICK_MASK = InputEvent.BUTTON3_DOWN_MASK;
    public static final Duration ONE_TICK = Duration.ofMillis(600);
}
