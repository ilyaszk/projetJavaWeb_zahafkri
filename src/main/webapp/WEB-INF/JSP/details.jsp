<%@ page import="iut2.zahafkri_projet_java_web1.Matiere" %>
<%@ page import="iut2.zahafkri_projet_java_web1.NoteExamen" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<jsp:useBean id="etudiant" type="iut2.zahafkri_projet_java_web1.Etudiant" scope="request"/>
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
<button type="button" class="btn btn-primary mx-2"
        onclick="window.location.href='<%=application.getContextPath()%>/do/index'">Retour
</button>
<% if (!edit) {%>
<h1>Details Etudiant</h1>
<%} else {%>
<h1>Modifier Etudiant</h1>
<%}%>
<%--card qui reprends toutes les informations de l'etudiant--%>
<div style="width: 100%; display: flex; justify-content: center;">
    <div class="card mb-3" style="width: 25rem; border: black solid 1px;">
        <div class="card-body text-center">
            <h5 class="card-title">Etudiant : <%=etudiant.getNom()%> <%=etudiant.getPrenom()%>
            </h5>
            <h6 class="card-subtitle mb-2 text-muted">Groupe : <%=etudiant.getGroupe().getNom()%>
            </h6>
            <img src="https://www.pngitem.com/pimgs/m/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png"
                 class="card-img-top img-thumbnail" alt="...">
            <p class="card-text">Nom : <%=etudiant.getNom()%>
            </p>
            <p class="card-text">Prenom : <%=etudiant.getPrenom()%>
            </p>
            <p class="card-text">Moyenne : <%=etudiant.getMoyenneGenerale()%>
            </p>
            <p class="card-text">Absence : <%=etudiant.getNbAbsences()%>
            </p>
        </div>
    </div>
</div>
<form action="<%=application.getContextPath()%>/do/details?id=<%= etudiant.getId() %>&edit=true" method="post"
      class="d-grid gap-2">
    <div style="width: 100%; display: flex; justify-content: center;flex-wrap: wrap; padding-right: 100px;padding-left: 100px">
        <% for (Matiere noteMatiere : etudiant.getMatieres()) {%>
        <div class="card my-3 mx-4 p-2" style="width: 15rem; border: black solid 1px;">
            <table class="table table-striped">
                <h3 class="card-title text-center"><%=noteMatiere.getNom()%>
                </h3>
                <thead>
                <tr>notes</tr>
                </thead>
                <tbody>
                <% for (NoteExamen note : noteMatiere.getNotesExamen()) {%>
                <tr>
                    <% if (note.getEtudiant().equals(etudiant)) {%>
                    <% if (!edit) {%>
                    <td><%=note.getNomExamen()%>
                    </td>
                    <td><%=note.getNote()%>
                    </td>
                    <%} else {%>
                    <td><%=note.getNomExamen()%>
                    </td>
                    <td><input class="form-control" type="text" name="note_<%=note.getId()%>"
                               value="<%=note.getNote()%>"></td>
                    <%}%>
                    <%}%>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
        <% } %>
    </div>

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

    <% if (edit) {%>
    <button type="submit" class="btn btn-primary mx-auto">
        Valider les modifications
    </button>
    <%}%>
</form>
<jsp:include page='<%= application.getInitParameter("pieddepage") %>'/>

</body>
</html>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</html>


