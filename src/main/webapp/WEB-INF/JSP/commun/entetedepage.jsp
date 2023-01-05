<%@ page contentType="text/html;charset=UTF-8" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-bottom: 40px">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<%=application.getContextPath()%>/do/index">Liste des étudiants</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=application.getContextPath()%>/do/notes">Consulter les notes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=application.getContextPath()%>/do/absences">Consulter les absences</a>
            </li>
        </ul>
    </div>
</nav>