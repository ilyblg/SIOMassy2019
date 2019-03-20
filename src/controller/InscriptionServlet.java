package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoPersonne;
import entity.Personne;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean formIsValid;
	private Personne personne;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Instanciation de notre objet personne
		Personne personne = new Personne(
		request.getParameter("nom"),
		request.getParameter("prenom"), request.getParameter("adresse"),
		Integer.parseInt(request.getParameter("cp")), request.getParameter("ville"),
		request.getParameter("telephone"), request.getParameter("mail"),"password",
		Boolean.parseBoolean(request.getParameter("estFormateur")),
		Boolean.parseBoolean(request.getParameter("estAdmin")),
		Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));

		
		Personne personne2 = new Personne(request.getParameter("nom"), request.getParameter("prenom"), "RUE DES ANES",
				1234, "VILLENOIRE", "0101010101", request.getParameter("mail"), "password", false, false,
				Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));

		System.out.println(personne2);

		// ---------------------------------------------------
		
		// Vérifie le formulaire, si valide vérifie présence en base de donnée de la personne
		DaoPersonne daoPers;
		try {
			daoPers = new DaoPersonne();
			if(checkFormData(request)){
				if (!daoPers.ifExist(personne2)) {
					// ENREGISTREMENT + ENVOIE MAIL
					// ---------------------------------------------------
				}
			}
			else { // Si formulaire invalide, renvoie sur la page avec les msg d'erreurs correspondant
				getServletContext().getRequestDispatcher("/Inscription.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ATTENTION : GERER AFFICHAGE JSP DES MSG D'ERREURS du CHECK

	/**
	 * Verifie que le formulaire est valide : champs requis renseignés, plages
	 * de valeur respectées. Si valide, crée l'objet personne correspondant.
	 * Sinon, paramètre les messages d'erreur sous
	 * la forme msgNomDuChamp (ex : msgEmail) en 'request'
	 *
	 * @param request
	 * @throws SQLException
	 */
	private boolean checkFormData(HttpServletRequest request)
			throws SQLException {
		// Recuperer les parametres
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		Integer cp = Integer.parseInt(request.getParameter("cp"));
		String ville = request.getParameter("ville");
		String telephone = request.getParameter("telephone");
		String mail = request.getParameter("mail");
		String mailVerif = request.getParameter("mailVerif");
		String password = request.getParameter("mail");
		boolean isFormateur = Boolean.parseBoolean(request.getParameter("estFormateur"));
		boolean isAdmin = Boolean.parseBoolean(request.getParameter("estAdmin"));
		Timestamp dateInscription= Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));

		DaoPersonne daoPersonne = new DaoPersonne();
		
		if (daoPersonne.checkEmail(mail)) {
			formIsValid = false;
			request.setAttribute("msgEmail", "L'email " + mail
					+ " existe déjà.");
		}

		// Les tests pour vérifier si les champs sont vides
		if (nom.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgNom", "Le nom est obligatoire.");
		}
		if (prenom.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgPrenom", "Le prénom est obligatoire.");
		}
		if (mail.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgEmail", "L'email est obligatoire.");
		}
		if (mailVerif.matches("^ *") && !mailVerif.contentEquals(mail)) {
			formIsValid = false;
			request.setAttribute("msgEmailVerif",
					"La confirmation du courriel n'est pas valide.");
		}		
		if (password.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgMotDePasse",
					"Le mot de passe est obligatoire.");
		}
		if (adresse.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgAdresse", "L'adresse est obligatoire.");
		}
		if (cp < 0) {
			formIsValid = false;
			request.setAttribute("msgCodePostal", "Le code postal est obligatoire.");
		}
		if (ville.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgVille", "La ville est obligatoire.");
		}
		if (telephone.matches("^ *")) {
			formIsValid = false;
			request.setAttribute("msgTel", "Un numéro de téléphone est obligatoire.");
		}

		// Si formulaire valide alors...
		if (formIsValid) {
			Personne personne2 = new Personne(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"),
				Integer.parseInt(request.getParameter("cp")), request.getParameter("ville"), request.getParameter("telephone"), request.getParameter("mail"), "password", false, false,
				Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
			System.out.println(personne);
		}

		return formIsValid;
	}
}
