import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

public class Table {
    private String TableName;
    private String ClusterKey;
    private Hashtable<String,String> htblColumnType;
    private  Hashtable<String,String> htblColumnHasIndex; //TO BE ADDED TO META DATA FUNC
    private int colNum;
    private Vector<String> pageNames;
    private static String PAGE_DIRECTORY = "./src/Resources/";
    public Table(String TableName,String Clusterkey,Hashtable<String,String> htblColumnType){

        this.TableName = TableName;
        this.ClusterKey = Clusterkey;
        this.htblColumnType = htblColumnType;
        this.colNum = this.htblColumnType.size();
        this.pageNames = new Vector<String>();
        this.htblColumnHasIndex = new Hashtable<String,String>();

    }
    public static void initMetaData(){

        String folderPath = "./src/Resources/";
        String fileName = "metadata.csv";
        File folder = new File(folderPath);
        File file = new File(folder, fileName);

        if(file.exists()) return;

        String filePath = PAGE_DIRECTORY + "metadata.csv";
        String[][]initialData = {{"Table Name","Column Name","Column Type","ClusteringKey","IndexName","IndexType"}};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : initialData) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    line.append(row[i]);
                    if (i < row.length - 1) {
                        line.append(",");
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }
            System.out.println("Data has been written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to the CSV file: " + e.getMessage());
        }
    }
    public boolean addMetaData(){

        String filePath = PAGE_DIRECTORY +  "metadata.csv";
        String[][] data = new String[this.colNum][6];

        int counter = 0;

        for(Object key : this.htblColumnType.keySet()){

            Boolean clusteringKey = false;

            if(this.ClusterKey == key.toString()){
                clusteringKey = true;
            }

            String []singleRow = {this.TableName,key.toString(),this.htblColumnType.get(key),clusteringKey.toString(),null,null};

            System.out.println(singleRow);

            data[counter] = singleRow;

            counter++;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            for (String[] row : data) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    line.append(row[i]);
                    if (i < row.length - 1) {
                        line.append(",");
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }
            System.out.println("Data has been written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to the CSV file: " + e.getMessage());
        }
        return true;
    }

}
