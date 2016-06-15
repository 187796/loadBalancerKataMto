package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Krystian on 2016-06-15.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    protected boolean matchesSafely(Server server) {
        return expectedLoadPercentage == server.currentLoadPercentage || Math.abs(expectedLoadPercentage - server.currentLoadPercentage) < 0.01d;
    }

    public void describeTo(Description description) {
        description.appendText("server with load percentage of").appendValue(expectedLoadPercentage);
    }

    @Override
    protected void describeMismatchSafely(Server server, Description description) {
        description.appendText("server with load percentage of").appendValue(server.currentLoadPercentage);

    }
}
