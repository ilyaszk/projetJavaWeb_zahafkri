package iut2.zahafkri_projet_java_web1.controleur;


import iut2.zahafkri_projet_java_web1.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static iut2.zahafkri_projet_java_web1.EtudiantDAO.getEtudiantById;

public class Controleur extends HttpServlet {

    // URL
    private String urlIndex;
    private String urlDetails;
    private String urlNotes;
    private String urlAbsences;
    private String urlCreationEtudiant;
    private String urlSupprimerEtudiant;

    // INIT
    @Override
    public void init() throws ServletException {
        urlIndex = getInitParameter("urlIndex");
        urlDetails = getInitParameter("urlDetails");
        urlNotes = getInitParameter("urlNotes");
        urlAbsences = getInitParameter("urlAbsences");
        urlCreationEtudiant = getInitParameter("urlCreationEtudiant");
        urlSupprimerEtudiant = getInitParameter("urlSupprimerEtudiant");

        // Création de la factory permettant la création d'EntityManager
        // (gestion des transactions)
        GestionFactory.open();

        //dropTables();


        ///// INITIALISATION DE LA BD
        // Normalement l'initialisation se fait directement dans la base de données
        if ((GroupeDAO.getAll().size() == 0) &&
                (EtudiantDAO.getAll().size() == 0) &&
                (MatiereDAO.getAll().size() == 0) &&
                (NoteExamenDAO.getAll().size() == 0)) {

            // Création des MATIERES
            Matiere JAVA = MatiereDAO.create("Java");
            Matiere WEB = MatiereDAO.create("Web");
            Matiere BDD = MatiereDAO.create("BDD");
            Matiere ALGO = MatiereDAO.create("Algo");
            Matiere ANGLAIS = MatiereDAO.create("Anglais");
            Matiere MATHS = MatiereDAO.create("Maths");
            Matiere PHYSIQUE = MatiereDAO.create("Physique");


            // Creation des groupes
            Groupe MIAM = GroupeDAO.create("miam");
            Groupe SIMO = GroupeDAO.create("SIMO");
            Groupe MESSI = GroupeDAO.create("MESSI");

            // Creation des étudiants
            EtudiantDAO.create("Francis", "Brunet-Manquat", MIAM);
            EtudiantDAO.create("Philippe", "Martin", MIAM);
            EtudiantDAO.create("Mario", "Cortes-Cornax", MIAM);
            EtudiantDAO.create("Françoise", "Coat", SIMO);
            EtudiantDAO.create("Laurent", "Bonnaud", MESSI);
            EtudiantDAO.create("Sébastien", "Bourdon", MESSI);
            EtudiantDAO.create("Mathieu", "Gatumel", SIMO);
            EtudiantDAO.create("Jean", "Boulet", SIMO);
            EtudiantDAO.create("Juilien", "Boulet", SIMO);
            EtudiantDAO.create("lkdnhkhqs", "tatata", SIMO);


            //inscrire les étudiants dans les matières
            for (Etudiant etudiant : EtudiantDAO.getAll()) {
                if (etudiant.getGroupe().equals(MIAM)) {
                    BDD.addEtudiant(etudiant);
                    ALGO.addEtudiant(etudiant);
                    ANGLAIS.addEtudiant(etudiant);
                    MATHS.addEtudiant(etudiant);
                    WEB.addEtudiant(etudiant);
                } else if (etudiant.getGroupe().equals(SIMO)) {
                    JAVA.addEtudiant(etudiant);
                    WEB.addEtudiant(etudiant);
                    BDD.addEtudiant(etudiant);
                    ALGO.addEtudiant(etudiant);
                } else if (etudiant.getGroupe().equals(MESSI)) {
                    JAVA.addEtudiant(etudiant);
                    WEB.addEtudiant(etudiant);
                    BDD.addEtudiant(etudiant);
                    PHYSIQUE.addEtudiant(etudiant);
                }
            }
            MatiereDAO.update(JAVA);
            MatiereDAO.update(WEB);
            MatiereDAO.update(BDD);
            MatiereDAO.update(ALGO);
            MatiereDAO.update(ANGLAIS);
            MatiereDAO.update(MATHS);
            MatiereDAO.update(PHYSIQUE);
            // Creation des notes random float [0, 20] pour chaque matière de chaque étudiant
            for (Etudiant etudiant : EtudiantDAO.getAll()) {
                for (Matiere matiere : etudiant.getMatieres()) {
                    NoteExamenDAO.create(etudiant, matiere, matiere.getNom() + " Oral");
                    NoteExamenDAO.create(etudiant, matiere, matiere.getNom() + " TP");
                    NoteExamenDAO.create(etudiant, matiere, matiere.getNom() + " Examen");
                }
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
//<form action="<%=application.getContextPath()%>/do/details?id=<%= etudiant.getId() %>&edit=true'" method="post" class="d-grid gap-2">
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
        if (methode.equals("post") && action.equals("/details")) {
            doDetails(request, response);
        } else if (methode.equals("post") && action.equals("/absences")) {
            doAbsences(request, response);
        } else if (methode.equals("post") && action.equals("/creationEtudiant")) {
            doCreationEtudiant(request, response);
        } else if (methode.equals("post") && action.equals("/index")) {
            doIndex(request, response);
        } else if (methode.equals("post") && action.equals("/supprimerEtudiant")) {
            doSupprimerEtudiant(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
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
        } else if (methode.equals("get") && action.equals("/notes")) {
            doNotes(request, response);
        } else if (methode.equals("get") && action.equals("/absences")) {
            doAbsences(request, response);
        } else if (methode.equals("get") && action.equals("/creationEtudiant")) {
            doCreationEtudiant(request, response);
        } else if (methode.equals("get") && action.equals("/supprimerEtudiant")) {
            doSupprimerEtudiant(request, response);
        } else {
            doIndex(request, response);
        }
    }

    //
    private void doIndex(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les étudiants
        List<Etudiant> etudiants = EtudiantDAO.getAll();
        String erreur = "";
        String succes = "";
        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("erreur", erreur);
        request.setAttribute("succes", succes);
        loadJSP(urlIndex, request, response);
    }

    private void doSupprimerEtudiant(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {

        String erreur = "";
        String succes = "";
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            NoteExamenDAO.removeByIdEtudiant(id);
            EtudiantDAO.remove(id);
            succes = "Etudiant supprimé";
        } catch (Exception e) {
            erreur = "Impossible de supprimer l'étudiant";
        }
        List<Etudiant> etudiants = EtudiantDAO.getAll();
        request.setAttribute("erreur", erreur);
        request.setAttribute("succes", succes);
        request.setAttribute("etudiants", etudiants);
        loadJSP(urlSupprimerEtudiant, request, response);
    }


    private void doDetails(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {


        int id = Integer.parseInt(request.getParameter("id"));
        // Récupérer les détails
        Etudiant etudiant = getEtudiantById(id);
        String erreur = "";
        String succes = "";

        Boolean edit = Boolean.parseBoolean(request.getParameter("edit"));

        // je récupère les notes du formulaire seulement avec le prefixe note_ et je les mets dans une map avec comme clé l'id de la note sans le prefixe et comme valeur =! ""
        Map<String, String[]> map = request.getParameterMap().entrySet().stream()
                .filter(e -> e.getKey().startsWith("note_"))
                .collect(Collectors.toMap(e -> e.getKey().substring(5), Map.Entry::getValue));


        if (request.getMethod().equals("POST")) {
            // je parcours les notes de l'étudiant et je les mets à jour si elles sont différentes de celles du formulaire
            for (Matiere matiere : etudiant.getMatieres()) {
                for (NoteExamen noteExamen : matiere.getNotesExamen()) {
                    String[] note = map.get(String.valueOf(noteExamen.getId()));
                    if (note != null && note.length > 0 && !note[0].equals("")) {
                        float noteValue = Float.parseFloat(note[0]);
                        if (noteValue != noteExamen.getNote()) {
                            if ((noteValue >= 0 && noteValue <= 20) && String.valueOf(noteValue).matches("^[0-9]+([.][0-9]+)?$")) {
                                noteExamen.setNote(noteValue);
                                NoteExamenDAO.update(noteExamen);
                                //update de la moyenne generale de l'etudiant
                                float moyenne = 0;
                                int nbNotes = 0;
                                List<NoteExamen> notesEtudiant = NoteExamenDAO.retrieveByEtudiant(etudiant);
                                for (NoteExamen noteMoyenne : notesEtudiant) {
                                    if (noteMoyenne.getNote() != -1) {
                                        moyenne += noteMoyenne.getNote();
                                        nbNotes++;
                                    }
                                }
                                moyenne = moyenne / nbNotes;
                                etudiant.setMoyenneGenerale(moyenne);
                                try {
                                    EtudiantDAO.update(etudiant);
                                    succes = "Les notes ont été mises à jour";
                                } catch (Exception e) {
                                    erreur = "Une erreur est survenue lors de la mise à jour des notes";
                                }

                            } else {
                                erreur = "La note doit être comprise entre 0 et 20 et ne doit pas contenir de caractères spéciaux";
                            }
                        }
                    }
                }
            }
        }
        request.setAttribute("erreur", erreur);
        request.setAttribute("succes", succes);
        request.setAttribute("edit", edit);
        request.setAttribute("etudiant", etudiant);
        loadJSP(urlDetails, request, response);
    }

    private void doNotes(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        // Récupérer les étudiants
        List<Etudiant> etudiants = EtudiantDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("etudiants", etudiants);
        loadJSP(urlNotes, request, response);
    }

    private void doAbsences(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les étudiants
        String erreur = "";
        String succes = "";
        Boolean edit = Boolean.parseBoolean(request.getParameter("edit"));

        if (request.getMethod().equals("POST")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int nbAbsences = Integer.parseInt(request.getParameter("nbAbsences"));
            Etudiant etudiant = getEtudiantById(id);
            etudiant.setNbAbsences(nbAbsences);
            try{
                EtudiantDAO.update(etudiant);
                succes = "Les absences ont été mises à jour";
            }catch (Exception e){
                erreur = "Une erreur est survenue";
            }
        }
        List<Etudiant> etudiants = EtudiantDAO.getAll();


        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("edit", edit);
        request.setAttribute("erreur", erreur);
        request.setAttribute("succes", succes);
        loadJSP(urlAbsences, request, response);
    }

    private void doCreationEtudiant(HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les groupes
        // Récupérer les matières
        // Ajouter les groupes et les matières à la requête pour affichage
        // Afficher la page de création d'un étudiant

        if (request.getMethod().equals("POST")) {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            Groupe groupe = GroupeDAO.getGroupeById(Integer.parseInt(request.getParameter("groupe")));
            Etudiant etudiant = EtudiantDAO.create(nom, prenom, groupe);
            String[] matieres = request.getParameterValues("matiere");
            for (String matiere : matieres) {
                etudiant.addMatiere(MatiereDAO.getMatiereById(Integer.parseInt(matiere)));
                NoteExamenDAO.create(etudiant, MatiereDAO.getMatiereById(Integer.parseInt(matiere)), "Oral");
                NoteExamenDAO.create(etudiant, MatiereDAO.getMatiereById(Integer.parseInt(matiere)), "Examen");
                NoteExamenDAO.create(etudiant, MatiereDAO.getMatiereById(Integer.parseInt(matiere)), "TP");
            }
            EtudiantDAO.update(etudiant);
        }

        List<Groupe> groupes = GroupeDAO.getAll();
        List<Matiere> matieres = MatiereDAO.getAll();

        request.setAttribute("groupes", groupes);
        request.setAttribute("matieres", matieres);
        loadJSP(urlCreationEtudiant, request, response);
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
