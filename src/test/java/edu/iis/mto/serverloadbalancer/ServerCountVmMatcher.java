package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Krystian on 2016-06-15.
 */
public class ServerCountVmMatcher extends TypeSafeMatcher<Server> {
    private int expectedVmCount;

    public ServerCountVmMatcher(int expectedVmCount) {
        this.expectedVmCount = expectedVmCount;
    }

    protected boolean matchesSafely(Server server) {
        return expectedVmCount == server.countVm();
    }

    public void describeTo(Description description) {
        description.appendText("server should have vms count of").appendValue(expectedVmCount);
    }

    @Override
    protected void describeMismatchSafely(Server server, Description description) {
        description.appendText("server should have vms count of").appendValue(server.countVm());

    }
}
