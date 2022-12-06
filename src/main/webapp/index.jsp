<%@ page import="iut2.zahafkri_projet_java_web1.GestionFactory" %>
<%@ page import="iut2.zahafkri_projet_java_web1.Etudiant" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Etudiant</title>
</head>
<body>
    <h1>Etudiant</h1>
<% for (Etudiant etudiant : GestionFactory.getEtudiants()) {%>
    <a href="servlettraitementdetails?id=<%= etudiant.getId() %>"><%= etudiant.getPrenom() %> <%=etudiant.getNom()%></a>
<%}%>
</body>
</html>

</body>
</html>