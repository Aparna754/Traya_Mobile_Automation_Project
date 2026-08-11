package com.company.framework.stepgroups;

import java.util.Collections;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import static com.company.framework.driver.DriverManager.getDriver;

public class ClickAtCoordinates {
    
    public void clickAtCoordinates(int x, int y) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    Sequence tap = new Sequence(finger, 1);
    tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    getDriver().perform(Collections.singletonList(tap));
}

}
