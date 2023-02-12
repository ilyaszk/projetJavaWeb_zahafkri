<%@ page import="iut2.zahafkri_projet_java_web1.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<jsp:useBean id="etudiants" type="java.util.List<iut2.zahafkri_projet_java_web1.Etudiant>" scope="request" />
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Etudiant</title>
</head>
<body>
<jsp:include page='<%= application.getInitParameter("entetedepage") %>'/>
<h2>Voici la liste des étudiants</h2>

    <a href="<%=application.getContextPath()%>/do/creationEtudiant" class="btn btn-primary">Creer un étudiant</a>
<%--realise un tableau avec la liste des etudiants avec une redirection vers la page details sur la ligne--%>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Groupe</th>
            <th>Nom</th>
            <th>Prénom</th>
        </tr>
        </thead>
        <tbody>
        <% for (Etudiant etudiant : etudiants) {%>
        <tr>
            <td><%= etudiant.getGroupe().getNom() %></td>
            <td><%= etudiant.getNom() %></td>
            <td><%= etudiant.getPrenom() %></td>
            <td><a href="<%=application.getContextPath()%>/do/details?id=<%= etudiant.getId() %>" class="btn btn-primary">Détails</a></td>
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