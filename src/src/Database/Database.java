package Database;

public class Database implements Runnable{

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

    @Override
    public void run() {
        new FileManager();
        new Serializer();
        new Utility();
    }
}
