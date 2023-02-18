<%@ page import="iut2.zahafkri_projet_java_web1.Groupe" %>
<%@ page import="iut2.zahafkri_projet_java_web1.Matiere" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<jsp:useBean id="groupes" type="java.util.List<iut2.zahafkri_projet_java_web1.Groupe>" scope="request"/>
<jsp:useBean id="matieres" type="java.util.List<iut2.zahafkri_projet_java_web1.Matiere>" scope="request"/>
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
<h1>Creation Etudiant</h1>
<%--card qui reprends toutes les informations de l'etudiant--%>
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
<div style="width: 100%; display: flex; justify-content: center;">
    <div class="card mb-3" style="width: 25rem; border: black solid 1px;">
        <div class="card-body text-center">
            <img src="https://www.pngitem.com/pimgs/m/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png"
                 class="card-img-top img-thumbnail" alt="...">
            <form action="<%=application.getContextPath()%>/do/creationEtudiant" method="post">
                <input type="text" name="nom" placeholder="Nom" class="form-control my-2" required>
                <input type="text" name="prenom" placeholder="Prenom" class="form-control my-2" required>
                <select name="groupe" class="form-select my-2">
                    <% for (Groupe groupe : groupes) { %>
                        <option value="<%=groupe.getId()%>"><%=groupe.getNom()%></option>
                    <% } %>
                </select>
                    <% for (Matiere matiere : matieres) { %>
                        <input type="checkbox" class="btn-check" name="matiere" id="<%=matiere.getId()%>" value="<%=matiere.getId()%>" autocomplete="off">
                        <label class="btn btn-outline-primary m-1" for="<%=matiere.getId()%>"><%=matiere.getNom()%></label>
                    <% } %>
                <input type="submit" value="Creer l'etudiant" class="btn btn-primary m-4 w-75">
            </form>
        </div>
    </div>
</div>


</body>
</html>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</html>


