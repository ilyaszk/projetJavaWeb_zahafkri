<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<jsp:useBean id="etudiant" class="iut2.zahafkri_projet_java_web1.Etudiant" scope="request"/>
<jsp:useBean id="nbAbsences" type="java.lang.Integer" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title>Etudiant</title>
</head>
<body>
    <h1>Details Etudiant</h1>
    <p style="color: darkmagenta">Nom : <jsp:getProperty name="etudiant" property="nom"/></p>
    <p style="color: darkkhaki">Prenom : <jsp:getProperty name="etudiant" property="prenom"/></p>
    <p style="color: dodgerblue">Absences : <%= nbAbsences %></p>

    <a href="index.jsp">Retour</a>
</body>
</html>


