import java.util.*;
import java.util.stream.Collectors;

public class ListService {
    private List<GasData> list;
    private Set<String> yearSet;

    public ListService() {
        this.yearSet = new HashSet<>();
        this.list = new ArrayList<>();
    }

    public List<GasData> addToList(GasData gasData) {
        yearSet.add(gasData.getYear());
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
        Collections.sort(list);
        return list;
    }

    public List<GasData> getYearList(int year) {
        String yearAsString = String.valueOf(year);
        List<GasData> stream = list.stream().filter(gasData -> gasData.getYear().equals(yearAsString))
                .collect(Collectors.toList());

        return stream;
    }

    public Set<String> getYearSet() {
        return yearSet;
    }
}
