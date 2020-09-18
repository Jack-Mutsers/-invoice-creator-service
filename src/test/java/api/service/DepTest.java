package api.service;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DepTest {

    /**
     * Rigourous test :-)
     */
    @Test
    public void testDep(){
        String toCompare = Dep.hello("Joe");
        assertEquals("Hello Joe", toCompare);
    }
}
