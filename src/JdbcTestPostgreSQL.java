import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

class JdbcTestPostgreSQL {
    public static void main (String args[]) {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            System.err.println (e+"ola");
            System.exit (-1);
        }
        try {
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password",  "J9o7a7n7a");

            // open connection to database
          // Connection connection = DriverManager.getConnection("jdbc:postgresql:pgdb.radicaldevelop.com:5432/Logs", "medsam_dev", "medsam_dev_pw");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Teste",props);// "postgres","J9o7a7n7a");
            //M\\>[#Jvc<Cw\\LEsA4;5,a<T#  ");
            //Connection connection = DriverManager.getConnection("jdbc:postgresql://radicaldevelop.com:5432/MedSAM_DEV", "postgres", "M\\>[#Jvc<Cw\\LEsA4;5,a<T#");
           // jdbc:postgresql://localhost:5432/teste?use= postgres&password= ");
            // build query, here we get info about all databases"
          //  String query = "SELECT datname FROM pg_ database WHERE datistemplate = false";

            // execute query
           // Statement statement = connection.createStatement ();
          //  ResultSet rs = statement.executeQuery (query);

            // return query result
           // while ( rs.next () )
                // display table name
               // System.out.println ("PostgreSQL Query result: " + rs.getString ("datname"));
            connection.close ();
        }
        catch (java.sql.SQLException e) {
            System.err.println (e.toString()+"+ola");
            System.exit (-1);
        }
    }
}
