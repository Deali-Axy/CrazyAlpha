package temp;

public class ClassGetResourceTest {
    public static void main(String...args){
        System.out.println(ClassGetResourceTest.class.getResource(""));
        System.out.println(ClassGetResourceTest.class.getResource("/"));

    }
}
