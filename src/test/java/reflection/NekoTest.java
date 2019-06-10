package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov
 */
class NekoTest {

    @Test
    void reflectionTest(){
        Neko neko = new Neko("Hayori", 666);

        assertEquals(Modifier.PUBLIC, Neko.class.getModifiers());
        try {
            assertFalse(Neko.class.getMethod("getName").isSynthetic());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(Neko.class.getFields()));
        System.out.println(Arrays.toString(neko.getClass().getFields()));
        System.out.println(Arrays.toString(Neko.class.getMethods()));
    }

}