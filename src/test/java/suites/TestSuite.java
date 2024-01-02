package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.SearchTests;

//https://www.baeldung.com/java-junit-test-suite
@RunWith(Suite.class)

@Suite.SuiteClasses({
        SearchTests.class
})
public class TestSuite {


}
