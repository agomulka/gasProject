import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListService {
    private List<GasData> list;

    public ListService() {
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

    public List<GasData> getList() {
        return list;
    }

    public List<GasData> getYearList(int year) {
        String yearAsString = String.valueOf(year);
        List<GasData> stream = list.stream().filter(gasData -> gasData.getYear().equals(yearAsString))
                .collect(Collectors.toList());

        return stream;
    }


}
