import java.io.*;
import java.util.*;

public class Page implements Serializable {
    public Vector<Hashtable<String, Object>> getTuples() {
        return tuples;
    }

    public void setTuples(Vector<Hashtable<String, Object>> tuples) {
        this.tuples = tuples;
    }

    private int maxNumOfRows;
    private String tableName;
    private int id;
    private Vector<Hashtable<String,Object>> tuples;
    private static String PAGE_DIRECTORY = "./src/Resources/";
    private String path;
    public Page(String tableName, int id) throws IOException {
        this.tableName = tableName;
        this.id = id;
        this.maxNumOfRows = Integer.parseInt(readConfig("./src/Resources/DBApp.config").getProperty("MaximumRowsCountinPage"));
        this.path = PAGE_DIRECTORY + tableName + "-" + id + ".ser";
        this.tuples = new Vector<>();
    }
    public static Properties readConfig(String path) throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(path);
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }

    public boolean Serialize(){
        String filename = this.path;
        try{

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            System.out.println(this.tuples);

            out.writeObject(this.tuples);

            out.close();
            file.close();

            System.out.println("Tuples have been serialized");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Vector<Hashtable<String,Object>> deSerialize(){

        Vector<Hashtable<String,Object>> toBeReturned = null;

        String filename = this.path;

        try{
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            toBeReturned = (Vector<Hashtable<String,Object>>)in.readObject();

            in.close();
            file.close();

            System.out.println("Tuples have been deserialized");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return toBeReturned;
    }
    public boolean isFull() {
        return this.tuples.size() == this.maxNumOfRows;
    }

    public boolean isOverFlow() {
        return this.tuples.size() > this.maxNumOfRows;
    }

    public boolean isEmpty() {
        return this.tuples.size() == 0;
    }

}
