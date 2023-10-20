import java.sql.*;

public class Lister {
    public static void main(String[] args){
        try(Connection con = DS.getConnection();){
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from "+args[0]);
            int nbColonne = rs.getMetaData().getColumnCount();
            for(int i=0;i<nbColonne;i++){
                System.out.print(rs.getMetaData().getColumnLabel(i+1)+" ");
            }
            while(rs.next()){
                System.out.println();
                for(int i=1;i<=nbColonne;i++){
                    System.out.print(rs.getString(i) + ", ");
                }
                System.out.println();
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
