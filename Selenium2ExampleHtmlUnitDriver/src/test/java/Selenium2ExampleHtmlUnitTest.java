import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

public class Selenium2ExampleHtmlUnitTest extends TestCase {
    @Test
    public void testGetTitle() throws Exception {
        Selenium2ExampleHtmlUnit test = new Selenium2ExampleHtmlUnit();
        Assert.assertTrue("Cheese".equals(test.getTitle("Cheese!")));
    }
}