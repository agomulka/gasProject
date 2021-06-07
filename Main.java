
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        System.out.println("loading data from file...");
        GasService gs = new GasService();
        List<GasData> loading = gs.loading();
        int n = gs.getCounter();
        System.out.println("loaded " + n + " data.");
        for (GasData g : loading) {
            System.out.println(g);
        }

    }

}
