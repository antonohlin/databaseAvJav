package Database;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Serializer implements Runnable{

    Serializer(){

    }
    static void serialize(Object o, String fileName) {

        Path path = Path.of(Database.getFilesFolder()+fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path)))
        {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object deserialize(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Path.of(Database.getFilesFolder()+fileName)))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void run() {


    }
}
