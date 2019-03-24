import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayFrom4TestCase {

    private Homework6 hw;

    @Before
    public void setUp() throws Exception {
        hw = new Homework6();
    }

    @Test(expected = RuntimeException.class)
    public void test_arrayFrom4_no4(){
        hw.arrayFrom4(new int[]{1, 3, 5});
    }

    @Test
    public void test_arrayFrom4_is_ok(){
        int[] arr = {1, 7, 4, 5, 2};
        Assert.assertArrayEquals(new int[]{5, 2}, hw.arrayFrom4(arr));
    }

    @Test
    public void test_arrayFrom4_many4(){
        int[] arr = {1, 7, 4, 5, 2, 4, 3, 9, 10};
        Assert.assertArrayEquals(new int[]{3, 9, 10}, hw.arrayFrom4(arr));
    }

    @Test
    public void test_arrayFrom4_last4(){
        int[] arr = {1, 7, 5, 4};
        Assert.assertArrayEquals(new int[]{}, hw.arrayFrom4(arr));
    }

    @Test(expected = RuntimeException.class)
    public void test_arrayFrom4_empty(){
        hw.arrayFrom4(new int[0]);
    }
}
