package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by student on 2016-06-16.
 */
public class CurrentServerLoadMatcher extends TypeSafeMatcher<Server> {
    private double expectedLoad;

    public CurrentServerLoadMatcher(double expectedLoad) {
        this.expectedLoad = expectedLoad;
    }

    protected boolean matchesSafely(Server server) {
        return expectedLoad == server.currentLoadOf || Math.abs(expectedLoad - server.currentLoadOf) < 0.01d;
    }

    public void describeTo(Description description) {
        description.appendText("server has vm load of").appendValue(expectedLoad);
    }

    @Override
    protected void describeMismatchSafely(Server server, Description description) {
        description.appendText("server has vm load of").appendValue(server.currentLoadOf);
    }
}
