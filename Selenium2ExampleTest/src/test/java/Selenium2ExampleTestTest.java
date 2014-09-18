import junit.framework.Assert;
import junit.framework.TestCase;

public class Selenium2ExampleTestTest extends TestCase {

    public void testGetTitle() throws Exception {
        Selenium2ExampleTest test = new Selenium2ExampleTest();
        Assert.assertTrue("Cheese!".equals(test.getTitle("Cheese!")));
    }
}