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

    public List<GasData> loading(){
        loadedData = 0;
        File file = new File("C:\\Users\\Ola\\Documents\\java\\gasManagement\\file1.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                list.add(new GasData(Integer.valueOf(parts[1]), parts[0]));
                loadedData++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
