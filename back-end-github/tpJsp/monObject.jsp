<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Document</title>
    <%@ page import="java.Util.*, metier.Personne"%>
</head>
<body>
    <% Personne p = (Personne)session.getAttribute("p");
    
    if(p==null){
        p = new Personne();
        session.setAttribute("p",p);
    }
    %>
    
    <%= p %>
</body>
</html>