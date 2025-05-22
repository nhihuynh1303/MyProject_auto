package webdriver;

import java.nio.charset.StandardCharsets;

public class testByte {
    public static void main(String[] args) {
        String messageKOR = "가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가";
        int byteLengthKOR = messageKOR.getBytes(StandardCharsets.UTF_8).length;
        System.out.println(byteLengthKOR);

        String messageENG = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ex purus, tempor nec maximus varius, lacinia at tellus. Phasellus volutpat augue estd";
        int byteLengthENG = messageENG.getBytes(StandardCharsets.UTF_8).length;
        System.out.println(byteLengthENG);
    }
}
