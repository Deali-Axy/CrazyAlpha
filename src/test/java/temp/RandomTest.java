package temp;

import game.Engine.Utils.RandomEx;

public class RandomTest {
    public static void main(String... args) {
        for (int i = 0; i < 99; i++) {
            System.out.println(RandomEx.nextInt(10));
        }
    }
}
