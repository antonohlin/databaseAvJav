package Database;

public class Database implements Runnable{
    private static final String filepath = "src\\src\\Database\\Files\\filesText\\files.txt";
    private static final String filesFolder = "src\\src\\Database\\Files\\";

    public Database(){
        initThread();
    }

    private void initThread() {
        Thread databaseThread = new Thread(this,"DBthread");
        databaseThread.setDaemon(true);
        databaseThread.start();
    }
    static String getFilepath() {
        return filepath;
    }

    static String getFilesFolder() {
        return filesFolder;
    }

    @Override
    public void run() {
        new Serializer();
        new Utility();
    }
}
