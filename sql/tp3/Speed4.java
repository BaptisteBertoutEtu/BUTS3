import java.sql.*;

public class Speed4 {
    public static void main(String[] args) {
        try (Connection conn = DS.getConnection()){
            double debut = System.currentTimeMillis();
            PreparedStatement st = conn.prepareStatement("insert into client(nom) values(?)");
            for(int i=0;i<100000;i++)
            {
                st.setString(1, "nom"+i);
                st.addBatch();
            }
            st.executeBatch();
            System.out.println(System.currentTimeMillis() - debut);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}