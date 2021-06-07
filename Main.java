
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Menu m = new Menu(System.out, new BufferedReader(new InputStreamReader(System.in)), new GasService());
        m.runMenu();
    }
}
