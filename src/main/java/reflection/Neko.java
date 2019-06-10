package reflection;

/**
 * @author Arthur Kupriyanov
 */
public class Neko {
    private final String name;
    private final int id;

    public static boolean isAlive;
    private int age = 19;
    public int height = 170;

    public Neko(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
