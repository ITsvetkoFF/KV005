import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Selenimu2ExampleChromeTest {
    @Test
    public void testGetTitle() throws Exception {
        Selenimu2ExampleChrome test = new Selenimu2ExampleChrome();
        Assert.assertTrue("Cheese!".equals(test.getTitle("Cheese!")));
        }
    }