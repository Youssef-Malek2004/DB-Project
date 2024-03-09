import javax.swing.text.html.HTMLDocument;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.io.*;
import java.util.Vector;



public class DBApp {
    public void init(){
        Table.initMetaData();
    }


    public void createTable(String strTableName,
                            String strClusteringKeyColumn,
                            Hashtable<String,String> htblColNameType)
            throws DBAppException{

    }

    public void createIndex(String strTableName,
                            String strColName,
                            String strIndexName) throws DBAppException{

    }

    public void insertIntoTable(String strTableName,
                                Hashtable<String,Object> htblColNameValue)
            throws DBAppException{

    }

    public void updateTable(String strTableName,
                            String strClusteringKeyValue,
                            Hashtable<String,Object> htblColNameValue )
            throws DBAppException{

    }

    public void deleteFromTable(String strTableName,
                                Hashtable<String,Object> htblColNameValue)
            throws DBAppException{

    }

    public Iterator selectFromTable(SQLTerm[] arrSQLTerms,
                                    String[] strarrOperators)
            throws DBAppException{
            return null;

    }

    public static void main(String []args) throws DBAppException, IOException {
        DBApp myApp = new DBApp();
        myApp.init();
       // Table test = new Table();
        //test.addMetaData();

        Hashtable htblColNameType = new Hashtable( );
        htblColNameType.put("id", "java.lang.Integer");
        htblColNameType.put("name", "java.lang.String");
        htblColNameType.put("gpa", "java.lang.double");
        String strTableName = "Student";
        Table myTable = new Table(strTableName,"id",htblColNameType);

       // myTable.addMetaData();


    }
}
