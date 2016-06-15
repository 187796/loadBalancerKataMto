package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Krystian on 2016-06-15.
 */
public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedCount;

    public ServerVmsCountMatcher(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    protected boolean matchesSafely(Server server) {
        return expectedCount == server.countVms();
    }

    public void describeTo(Description description) {
        description.appendText("server with Vms count of").appendValue(expectedCount);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("server with Vms count of").appendValue(item.countVms());
    }
}
