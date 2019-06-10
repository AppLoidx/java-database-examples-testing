package resbundle;

import java.util.ListResourceBundle;

/**
 * @author Arthur Kupriyanov
 */
public class ResourceBundleWithList extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][]{
                {"hello", "nyan-nyan!"},
                {"bye", "nya-nya!"}
        };
    }
}
