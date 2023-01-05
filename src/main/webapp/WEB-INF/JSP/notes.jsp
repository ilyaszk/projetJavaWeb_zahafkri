<%@ page import="iut2.zahafkri_projet_java_web1.GestionFactory" %>
<%@ page import="iut2.zahafkri_projet_java_web1.Etudiant" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Etudiant</title>
</head>
<body>
<jsp:include page='<%= application.getInitParameter("entetedepage") %>'/>
<h2>Voici la moyennes des étudiants</h2>
<%--tableau --%>

<table
        class="table table-striped table-hover table-bordered table-sm">
    <thead>
    <tr>
        <th scope="col">Nom</th>
        <th scope="col">Prenom</th>
        <th scope="col">Moyenne</th>
    </tr>
    </thead>
    <tbody>
    <% for (Etudiant etudiant : GestionFactory.getEtudiants()) {%>
    <tr>
        <td><%= etudiant.getNom() %></td>
        <td><%= etudiant.getPrenom() %></td>
        <td><%= etudiant.getMoyenneGenerale() %></td>
    </tr>
    <%}%>
    </tbody>
</table>
<jsp:include page='<%= application.getInitParameter("pieddepage") %>'/>

</body>
</html>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</html>

