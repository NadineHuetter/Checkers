package controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
    public static String Readinput(){
        /**
         * Reads the console input
         */
        BufferedReader keyboard =
                new BufferedReader( new InputStreamReader(System.in));
        try {
            return keyboard.readLine();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }
}
