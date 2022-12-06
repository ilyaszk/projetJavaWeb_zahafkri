<%@ page import="iut2.zahafkri_projet_java_web1.Etudiant" %>
<%@ page import="iut2.zahafkri_projet_java_web1.GestionFactory" %><%--
  Created by IntelliJ IDEA.
  User: ilyas
  Date: 06/12/2022
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%
    int idEtu = Integer.parseInt(request.getParameter("id"));
    Etudiant etudiantById = GestionFactory.getEtudiantById(idEtu);
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>


</head>
<body>

<h1 style="color: crimson">Details</h1>
<p style="color: darkmagenta">Nom : <%=etudiantById.getNom()%></p>
<p style="color: darkkhaki">Prenom : <%=etudiantById.getPrenom()%></p>
<p style="color: dodgerblue">Absences : <%=GestionFactory.getAbsencesByEtudiantId(idEtu)%></p>

</body>
</html>
