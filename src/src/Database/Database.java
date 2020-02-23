package Database;

public class Database implements Runnable{
    private static final String filepath = "src\\src\\Database\\Files\\filesText\\files.txt";

    //TODO:A database is a collection of information
    // that is organized so that it can be easily accessed, managed and updated.

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

    @Override
    public void run() {
        new FileManager();
        new Serializer();
        new Utility();
    }
}
