package controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
    /**
 * Reads the console input
 */
    public static String ReadInput(){

        BufferedReader keyboard =
                new BufferedReader( new InputStreamReader(System.in));
        try {
            return keyboard.readLine();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }
}
