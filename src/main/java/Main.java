public class Main {

    public static void main(String[] args) {
        System.out.println("loading data from file...");
        GasService gs = new GasService();
        ListService loading = gs.loading();
        int n = gs.getCounter();
        System.out.println("loaded " + n + " lines.");
//        loading.printList();

        System.out.println("generating excel file...");
        GeneratorExcelFile generator = new GeneratorExcelFile(gs);
//        generator.print();
        generator.generate();
        System.out.println("generated successfully.");
    }

}
