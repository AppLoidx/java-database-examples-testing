package resbundle;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov
 */
class ResourseBundleWithFileTest {

    @Test
    void test(){
        Locale loc = new Locale("en_US");
        Locale locDef = Locale.getDefault();

        ResourceBundle rbEn = ResourceBundle.getBundle("Label", loc);
        ResourceBundle rbDef = ResourceBundle.getBundle("Label", locDef);

        assertEquals("Hi", rbEn.getString("hello"));
        assertNotEquals("bye", rbEn.getString("bye") );
        // Должна использовать значение по-умолчанию
        assertEquals("Poka", rbEn.getString("bye"));

        assertEquals("Privet", rbDef.getString("hello"));
        assertEquals("Poka", rbDef.getString("bye"));
    }


}