package temp;

import java.util.concurrent.ThreadLocalRandom;

public class AlphabetTest {
    public static void main(String... args) {
        System.out.println((int) 'A');
        System.out.println(ThreadLocalRandom.current().nextInt(65, 90));
        for (int i=65;i<=90;i++){
            System.out.println((char)i);
        }
    }
}
