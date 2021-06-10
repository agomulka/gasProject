public class GasService {
    private ListService gasList;
    int loadedData;
    FileLoader fileLoader;

    public GasService() {
        this.gasList = new ListService();
    }

//    for menu version
//    public void addGasData(String strings) {
//        GasData gasData;
//        String[] parts = strings.split(" +");
//        String gasValue = parts[0];
//        String date = parts[1];
//        if (date.equals("TODAY")) {
//            gasData = new GasData(Integer.valueOf(gasValue));
//        } else {
//            gasData = new GasData(Integer.valueOf(gasValue), date);
//        }
//        gasList.addToList(gasData);
//    }

    public ListService getListService() {
        return gasList;
    }

    public int getCounter() {
        return fileLoader.getNumberLoadedData();
    }

    public ListService loading() {
        this.fileLoader = new FileLoader(this);
        return fileLoader.loading();
    }

}
