import java.sql.*;

public class TestBdd{
    public static void main(String args[]) throws Exception{
        DS bdd = new DS();
        Connection con = DS.getConnection();
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from CLIENT");
        con.close();
    }
}