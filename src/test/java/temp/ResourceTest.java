package temp;

public class ResourceTest {
    public static void main(String... args) throws Exception {
        System.out.println(ResourceTest.class.getClassLoader().getResource("music/all-your-heart.mp3"));

    }
}
