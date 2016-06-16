package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by student on 2016-06-16.
 */
public class ServerVmLoadMatcher extends TypeSafeMatcher<Server>{
    private int expectedVmCount;

    public ServerVmLoadMatcher(int expectedVmCount) {
        this.expectedVmCount = expectedVmCount;
    }

    protected boolean matchesSafely(Server server) {
        return expectedVmCount == server.vmCount();
    }

    public void describeTo(Description description) {
        description.appendText("server conaints vm conut of").appendValue(expectedVmCount);
    }

    @Override
    protected void describeMismatchSafely(Server server, Description description) {
        description.appendText("server conaints vm conut of").appendValue(server.vmCount());

    }

    public static ServerVmLoadMatcher hasCountOf(int expectedVmCount) {
        return new ServerVmLoadMatcher(expectedVmCount);
    }
}
