import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MakeTable {


    static private Connection openDb(String filename) throws RuntimeException{

        // Replace with your file path
        String line = "";
        String jdbcUrl = "";
        String jdbcUser = "";
        String jdbcPassword = "";
        Connection connection = null;
        try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {

            while ((line = br.readLine()) != null) {
                if (line.startsWith("jdbcUrl=")) {
                    jdbcUrl = line.substring("jdbcUrl=".length());
                    System.out.println("JDBC URL: " + jdbcUrl);
                    // Assuming there's only one JDBC URL in the file
                }
                if (line.startsWith("jdbcUser=")) {
                    jdbcUser = line.substring("jdbcUser=".length());
                    System.out.println("JDBC USER: " + jdbcUser);
                    // Assuming there's only one JDBC URL in the file
                }
                if (line.startsWith("jdbcPassword=")) {
                    jdbcPassword = line.substring("jdbcPassword=".length());
                    System.out.println("JDBC PASSWORD: " + jdbcPassword);
                    // Assuming there's only one JDBC URL in the file
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
   try {

      // System.out.println("JDBC URL: " + jdbcUrl+"wwwwwwwwwwwwwww");
           connection =  DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
                   // DriverManager.getConnection(jdbcUrl);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);


        }
        return connection;

    }


        // Add a class method to insert an Icd10 record into the database
       static  public boolean insertIcd101 (Icd10 icd10, String jdbcUrl, String jdbcUser, String jdbcPassword){
            String sql = "INSERT INTO icd10 (previous_code, code, code_short_description, code_long_description, type) VALUES (?, ?, ?, ?, ?)";
           // Icd10 b = new Icd10();
            try {
             Connection connection = DriverManager.getConnection("jdbc:postgresql://radicaldevelop.com:5432/MedSAM_DEV", "postgres", jdbcPassword);
             //   Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
              /*   PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set parameters for the SQL query
                preparedStatement.setString(1, icd10.getPreviousCode());
                preparedStatement.setString(2, icd10.getCode());
                preparedStatement.setString(3, icd10.getCodeShortDescription());
                preparedStatement.setString(4, icd10.getCodeLongDescription());
                preparedStatement.setInt(5, icd10.getType());

                // Execute the SQL query to insert the record
                int rowsAffected = preparedStatement.executeUpdate();*/

            } catch (java.sql.SQLException e) {
                e.printStackTrace();

            };
       return true; }

    public static void insertIcd10(Connection connection, Icd10 icd10) throws java.sql.SQLException {
        String sql = "INSERT INTO icd10 (previous_code, code, code_short_description, code_long_description, type,  other_description, code_relations, text_search) " +
                "VALUES (?, ?, ?, ?, ?, ?,?,to_tsvector(? || ' ' || ? || ' ' || ? || ' ' || ?))";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, icd10.getPreviousCode());
            preparedStatement.setString(2, icd10.getCode());
            preparedStatement.setString(3, icd10.getCodeShortDescription());
            preparedStatement.setString(4, icd10.getCodeLongDescription());
            preparedStatement.setInt(5, icd10.getType());
            preparedStatement.setString(6, icd10.getCode_relations());
           preparedStatement.setString(7, icd10. getOther_description());



            // Append relevant text fields to create the tsvector

            preparedStatement.setString(8, icd10.getCode());
            preparedStatement.setString(9, icd10.getCodeShortDescription());
            preparedStatement.setString(10, icd10.getCodeLongDescription());
            preparedStatement.setString(11, icd10. getOther_description());


            try{  preparedStatement.executeUpdate();}
            catch(java.sql.SQLException e){
                System.out.println(e);

            }
        } catch (java.sql.SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args){


        try {
            Class.forName("org.postgresql.Driver");
        }
        catch ( ClassNotFoundException e) {
            System.err.println (e+"ola");
            System.exit (-1);
        }
      Connection connection=null;
      try{ connection= openDb(("config.txt"));
        Icd10 code= new Icd10(" ", "A00-B99", "Algumas doenças infecciosas e parasitárias", "Algumas doenças infecciosas e parasitárias", 0, ""," ");
        insertIcd10(connection,code);

      // insertIcd10(connection, code);

    } catch (RuntimeException | SQLException e) {

          System.out.println (e+"ola"); ;
      }

    }}


