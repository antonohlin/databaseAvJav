package Database;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Serializer implements Runnable{
    private static final String filesFolder = "src\\src\\Database\\Files\\";
    Serializer(){

    }
    static void serialize(List<String> info, String fileName) {

        Path path = Path.of(filesFolder +fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path)))
        {
            oos.writeObject(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object deserialize(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Path.of(filesFolder +fileName)))) {
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
