import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrHave1And4TestCase {

    private Homework6 hw;

    @Before
    public void setUp() throws Exception {
        hw = new Homework6();
    }

    @Test
    public void test_arrayFrom4_no4(){
        Assert.assertFalse(hw.arrHave1and4(new int[]{1, 1, 1}));
    }

    @Test
    public void test_arrayFrom4_no1(){
        Assert.assertFalse(hw.arrHave1and4(new int[]{4, 4}));
    }

    @Test
    public void test_arrayFrom4_is_ok(){
        Assert.assertTrue(hw.arrHave1and4(new int[]{1, 4, 4}));
    }

    @Test
    public void test_arrayFrom4_have_another(){
        Assert.assertFalse(hw.arrHave1and4(new int[]{1, 5, 4}));
    }

    @Test
    public void test_arrayFrom4_empty(){
        // для разнообразия через assertThat
        Assert.assertThat(hw.arrHave1and4(new int[0]), Is.is(false));
    }

}
