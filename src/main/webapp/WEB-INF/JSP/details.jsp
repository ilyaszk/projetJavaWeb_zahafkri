<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<jsp:useBean id="etudiant" class="iut2.zahafkri_projet_java_web1.Etudiant" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Etudiant</title>
</head>
<body>
<jsp:include page='<%= application.getInitParameter("entetedepage") %>'/>
    <h1>Details Etudiant</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Nom</th>
            <th scope="col">Prenom</th>
            <th scope="col">Groupe</th>
            <th scope="col">Moyenne</th>
            <th scope="col">Absence</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><jsp:getProperty name="etudiant" property="nom"/></td>
            <td><jsp:getProperty name="etudiant" property="prenom"/></td>
            <td><jsp:getProperty name="etudiant" property="groupe"/></td>
            <td><jsp:getProperty name="etudiant" property="moyenneGenerale"/></td>
            <td><jsp:getProperty name="etudiant" property="nbAbsence"/></td>
        </tr>
        </tbody>
    </table>

    <button type="button" class="btn btn-primary" onclick="window.location.href='<%=application.getContextPath()%>/do/index'">Retour</button>
<jsp:include page='<%= application.getInitParameter("pieddepage") %>'/>

</body>
</html>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</html>


