
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<GasData> list = new ArrayList<>();
        GasData gd = new GasData(1, "2019-05-21");
        list.add(gd);
        list.add(new GasData(22, "2021-01-30"));
        list.add(new GasData(282, "2021-05-14"));
        list.add(new GasData(42));
        list.add(new GasData(23, "2021-03-30"));
        list.add(new GasData(282, "2020-01-30"));
        Collections.sort(list);
        for(GasData g : list){
            System.out.println(g);

            }


    }
}
