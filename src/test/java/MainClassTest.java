import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClassTest extends MainClass {

    @Test
    public void checkNumber(){
        Assert.assertTrue("Number is not equal to 14!",getLocalNumber() == 14);
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("Number is less then 45!",getClassNumber() > 45);
    }

    @Test
    public void testGetClassString(){
        Pattern pattern = Pattern.compile("(?i:h)ello");
        Matcher matcher = pattern.matcher(getClassString());
        Assert.assertTrue("The string not contain 'Hello' or 'hello'!",matcher.find());
    }
}
