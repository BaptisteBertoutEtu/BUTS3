import java.sql.*;
import java.util.Scanner;

public class Rechercher {
    public static void main(String[] args){
        try(Connection con = DS.getConnection(); 
            Scanner sc = new Scanner(System.in)){

            PreparedStatement ps = con.prepareStatement("select * from client where nom=?");
            System.out.print("Nom du client : ");
            ps.setString(1, sc.next());
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println(rs.getInt("ano") + " : " + rs.getString("nom")+ ", " + rs.getString("prenom") + ", " + rs.getDate("naissance") + ", " + rs.getDate("datefinabo") + ", " + rs.getInt("parno"));
            }else{
                System.out.println("Client inconnu");
            }
            sc.close();
            con.close();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
}