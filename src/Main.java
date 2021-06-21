import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class Main {

    public static void main(String[] args)
    {
        Input.playmusic("shadmehr.wav",2000);
        Input input = new Input();
        input.menu();
    }
}
