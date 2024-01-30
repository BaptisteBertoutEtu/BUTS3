<!DOCTYPE html>
<html lang="fr">
<head>
    <title>ma base</title>
    <%@ page import="java.sql.*, java.time.LocalDate"%>
    <%@ page pageEncoding="UTF-8" %>
    <style>
        table{
            border-collapse : collapse;
        }
        
        td, th{
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <%
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://psqlserv/but2","baptistebertoutetu", "moi");
        String query = "Select * from joueurs;";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
    %>
    <h1>Joueurs</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Pays</th>
            <th>Poste</th>
            <th>Maillot</th>
            <th>Date de naissance</th>
            <th>Club</th>
            <th>Salaire</th>
        </tr>

        <%
            while(rs.next())
            {
                int id = rs.getInt("num_joueur");
                String nom = rs.getString("nom_joueur");
                String pays = rs.getString("pays");
                String poste = rs.getString("poste");
                int maillot = rs.getInt("maillot");
                LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
                int club = rs.getInt("club");
                int salaire = rs.getInt("salaire");
        %>
        
        <tr>
            <td><%= id %></td>
            <td><%= nom %></td>
            <td><%= pays %></td>
            <td><%= poste %></td>
            <td><%= maillot %></td>
            <td><%= date %></td>
            <td><%= club %></td>
            <td><%= salaire %></td>
        </tr>

        <% } %>
    </table>
</body>
</html>