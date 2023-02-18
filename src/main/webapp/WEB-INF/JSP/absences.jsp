<%@ page import="iut2.zahafkri_projet_java_web1.GestionFactory" %>
<%@ page import="iut2.zahafkri_projet_java_web1.Etudiant" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<jsp:useBean id="etudiants" type="java.util.List<iut2.zahafkri_projet_java_web1.Etudiant>" scope="request"/>
<jsp:useBean id="edit" type="java.lang.Boolean" scope="request"/>
<jsp:useBean id="erreur" type="java.lang.String" scope="request"/>
<jsp:useBean id="succes" type="java.lang.String" scope="request"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Etudiant</title>
</head>
<body>
<jsp:include page='<%= application.getInitParameter("entetedepage") %>'/>
<h2>Voici le nombre d'absences des étudiants</h2>

<div class="d-grid">
    <% if (!edit) {%>
    <a class="btn btn-primary" href="<%=application.getContextPath()%>/do/absences?edit=true">Editer les absences</a>
    <%} else {%>
    <a class="btn btn-primary" href="<%=application.getContextPath()%>/do/absences">Terminer l'édition</a>
    <%}%>
    <% if (!Objects.equals(erreur, "")) {%>
    <div class="alert alert-danger" role="alert">
        <%=erreur%>
    </div>
    <%}%>

    <% if (!Objects.equals(succes, "")) {%>
    <div class="alert alert-success" role="alert">
        <%=succes%>
    </div>
    <%}%>
    <table
            class="table table-striped table-hover table-bordered table-sm">
        <thead>
        <tr>
            <th scope="col">Groupe</th>
            <th scope="col">Nom</th>
            <th scope="col">Prénom</th>
            <th scope="col">Nombre d'absence</th>
        </tr>
        </thead>
        <tbody>
        <% for (Etudiant etudiant : etudiants) {%>
        <tr>
            <td><%= etudiant.getGroupe().getNom() %>
            </td>
            <td><%= etudiant.getNom() %>
            </td>
            <td><%= etudiant.getPrenom() %>
            </td>

            <td>
                <% if (!edit) {%>
                <%= etudiant.getNbAbsences() %>
                <%} else {%>
                <form action="<%=application.getContextPath()%>/do/absences?edit=true" method="post" class="d-flex">
                    <input type="hidden" name="id" value="<%= etudiant.getId() %>">
                    <input type="number" name="nbAbsences" value="<%= etudiant.getNbAbsences() %>" class="form-control w-50" min="0" max="100">
                    <input type="submit" value="Modifier" class="btn btn-primary ms-2">
                </form>
                <%}%>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>


</body>
</html>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</html>
