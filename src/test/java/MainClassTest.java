import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void checkNumber(){
        Assert.assertTrue("Number is not equal to 14!",getLocalNumber() == 14);
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("Number is less then 45!",getClassNumber() > 45);
    }
}
