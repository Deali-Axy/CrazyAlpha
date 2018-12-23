package temp;

import game.App;

public class HashCodeTest {
    public static void main(String... args) {
        App app = new App();
        App app1 = new App();
        System.out.println(app.hashCode());
        System.out.println(app1.hashCode());

        System.out.println("0x" + Integer.toHexString(app.hashCode()).toUpperCase());
    }
}
