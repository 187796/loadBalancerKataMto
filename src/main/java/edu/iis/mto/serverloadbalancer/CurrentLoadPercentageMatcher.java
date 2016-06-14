package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Krystian on 2016-06-14.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    public static final double EPSILON = 0.01d;
    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    protected boolean matchesSafely(Server server) {
        return doubleAreEquals(this.expectedLoadPercentage, server.currentLoadPercentage);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with load percentage of").appendValue(item.currentLoadPercentage);
    }

    private boolean doubleAreEquals(double d1, double d2) {
        return (d1 == d2)
                || Math.abs(d1 - d2) < EPSILON;
    }

    public void describeTo(Description description) {
        description.appendText("a server with load percentage of").appendValue(expectedLoadPercentage);
    }

    public static CurrentLoadPercentageMatcher hasCurrentLoadOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }


}
