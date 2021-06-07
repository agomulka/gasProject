import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GasService {
    List<GasData> list;
    GasList gasList = new GasList();
    int loadedData;
//    GasDAO gasDAO = new GasDAO();


    public GasService() {
        this.list = new ArrayList<>();
    }

    public void addGasData(String strings) {
        GasData gasData;
        String[] parts = strings.split(" +");
        String gasValue = parts[0];
        String date = parts[1];
        if (date.equals("TODAY")) {
            gasData = new GasData(Integer.valueOf(gasValue));
        } else {
            gasData = new GasData(Integer.valueOf(gasValue), date);
        }
        gasList.addToList(gasData);
    }

    public GasList getGasList() {
        return gasList;
    }

    public int getCounter(){
        return loadedData;
    }


}
