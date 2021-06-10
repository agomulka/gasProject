import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GasList {
    private List<GasData> list;

    public GasList() {
        this.list = new ArrayList<>();
    }

    public List<GasData> addToList(GasData gasData) {
        list.add(gasData);
        return list;
    }

    public void printList() {
        Collections.sort(list);
        for (GasData gasData : list) {
            System.out.println(gasData);
        }
    }

    public List<GasData> getList(){
        return list;
    }


}