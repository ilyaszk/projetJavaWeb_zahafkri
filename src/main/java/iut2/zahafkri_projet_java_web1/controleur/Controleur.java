package iut2.zahafkri_projet_java_web1.controleur;


import iut2.zahafkri_projet_java_web1.Etudiant;
import iut2.zahafkri_projet_java_web1.GestionFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;


public class Controleur extends HttpServlet {

    // URL
    private String urlIndex;
    private String urlDetails;
    private String urlNotes;
    private String urlAbsences;

    // INIT
    public void init() throws ServletException {
        urlIndex = getInitParameter("urlIndex");
        urlDetails = getInitParameter("urlDetails");
        urlNotes = getInitParameter("urlNotes");
        urlAbsences = getInitParameter("urlAbsences");
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // on passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        // On récupère la méthode d'envoi de la requête
        String methode = request.getMethod().toLowerCase();

        // On récupère l'action à exécuter
        String action = request.getPathInfo();
        getServletContext().log("Gestion des absences - Action demandée : " + action);

        if (action == null) {
            action = "/index";
            getServletContext().log("Gestion des absences - Action par défaut : " + action);
        }

        // Exécution action
        if (methode.equals("get") && action.equals("/index")) {
            doIndex(request, response);

        } else if (methode.equals("get") && action.equals("/details")) {
            doDetails(request, response);

        } else if (methode.equals("get") && action.equals("/notes")){
            doNotes(request, response);
        }else if (methode.equals("get") && action.equals("/absences")) {
            doAbsences(request, response);
        } else {
            doIndex(request, response);
        }
    }

    //
    private void doIndex(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        loadJSP(urlIndex, request, response);
    }

    //
    private void doDetails(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.valueOf(request.getParameter("id"));

        // Récupérer les détails
        Etudiant etudiant = GestionFactory.getEtudiantById(id);
        Integer nbAbsences = GestionFactory.getAbsencesByEtudiantId(id);

        // Mettre les détails dans la requête
        request.setAttribute("etudiant", etudiant);
        request.setAttribute("nbAbsences", nbAbsences);

        loadJSP(urlDetails, request, response);
    }

    private void doNotes(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        loadJSP(urlNotes, request, response);
    }

    private void doAbsences(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        loadJSP(urlAbsences, request, response);
    }



    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loadJSP(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

//		L'interface RequestDispatcher permet de transférer le contrôle à une autre servlet
//		Deux méthodes possibles :
//		- forward() : donne le contrôle à une autre servlet. Annule le flux de sortie de la servlet courante
//		- include() : inclus dynamiquement une autre servlet
//			+ le contrôle est donné à une autre servlet puis revient à la servlet courante (sorte d'appel de fonction). 
//			+ Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }


}
