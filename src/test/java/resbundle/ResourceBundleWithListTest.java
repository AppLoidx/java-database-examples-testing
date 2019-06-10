package resbundle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov
 */
class ResourceBundleWithListTest {

    @Test
    void getContents() {
        ResourceBundleWithList rb = new ResourceBundleWithList();

        assertEquals("nyan-nyan!", rb.getString("hello"));
        assertNotEquals("hello", rb.getString("hello"));

        assertEquals("nya-nya!", rb.getString("bye"));
        assertNotEquals("bye", rb.getString("bye"));


    }
}