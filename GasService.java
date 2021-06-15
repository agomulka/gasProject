public class GasService {
    private ListService gasList;
    private FileLoader fileLoader;

    public GasService() {
        this.gasList = new ListService();
    }

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
