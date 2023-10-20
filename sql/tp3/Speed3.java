import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Speed3 {
    public static void main(String[] args) {
        try (Connection conn = DS.getConnection()){
            double debut = System.currentTimeMillis();
            Statement st = conn.createStatement();
            int i = 0;
            while(i<10000){
                st.addBatch("insert into client(nom) values('nom"+i+"')");
                i++;
            }
            st.executeBatch();
            System.out.println(System.currentTimeMillis() - debut);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}