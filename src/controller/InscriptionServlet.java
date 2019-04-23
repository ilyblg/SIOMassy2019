package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonneDao;
import entity.Personne;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String VUE_FORMULAIRE = "/WEB-INF/inscription.jsp";
	private final String VUE_OK = "/WEB-INF/connexion.jsp";

	/**
	 * @see Méthode doGet qui renvoie vers la page de formulaire
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VUE_FORMULAIRE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vue = VUE_FORMULAIRE;
		// Vérifie le formulaire, si valide vérifie présence en base de donnée de la
		// personne
		// et son instanciation pour traitement
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("nom");
		String mail = request.getParameter("mail");
		String telephone = request.getParameter("telephone");
		String adresse = request.getParameter("adresse");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String password = request.getParameter("password");	
		
		try {
			// Preparation des attributs pour remplissage des champs sur la vue
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("mail", mail);
			request.setAttribute("telephone", telephone);
			request.setAttribute("cp", cp);
			request.setAttribute("ville", ville);
			request.setAttribute("password", password);
			
			// Vérification et insertion de la personne en base de données
			PersonneDao daoPers = new PersonneDao();
			if (checkFormData(request)) {
				// Instanciation de notre objet personne
				Personne personne = new Personne(-1,nom, prenom, mail, telephone, adresse, cp, ville, password, 
						false, false, Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));

				daoPers.inserer(personne);

			// ENVOIE MAIL REPORTE PROBLEME PROXY

				System.out.println("Inscription valide !");
				vue = VUE_OK;
			} 
		} catch (SQLException exc) {
			exc.printStackTrace();
			request.setAttribute("message", exc.getMessage());
		}
		// Renvoie sur la vue selon réussite ou non de l'insertion
		request.getRequestDispatcher(vue).forward(request, response);
	}

	/**
	 * Verifie que le formulaire est valide : champs requis renseignés, plages de
	 * valeur respectées. Si valide, crée l'objet personne correspondant. Sinon,
	 * paramètre les messages d'erreur sous la forme msgNomDuChamp (ex : msgEmail)
	 * en 'request'
	 *
	 * @param request
	 * @throws SQLException
	 */
	private boolean checkFormData(HttpServletRequest request) {
		boolean formIsValid = true;

		request.setAttribute("isInitial", "true");

		// Recuperer les parametres
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String telephone = request.getParameter("telephone");
		String mail = request.getParameter("mail");
		String mailVerif = request.getParameter("mailVerif");
		String password = request.getParameter("password");
		String passwordVerif = request.getParameter("passwordVerif");

		PersonneDao personneDao = new PersonneDao();
		// Les tests pour vérifier si les champs sont vides
		if (nom.isEmpty()) {
			formIsValid = false;
			request.setAttribute("msgNom", "Le nom est obligatoire.");
		}
		if (prenom.isEmpty()) {
			formIsValid = false;
			request.setAttribute("msgPrenom", "Le prénom est obligatoire.");
		}
		if (adresse.isEmpty() || adresse.length() < 5) {
			formIsValid = false;
			request.setAttribute("msgAdresse", "L'adresse est obligatoire.");
		}		
		if (cp.isEmpty() || cp.length() > 5) { // PROBLEME : !cp.matches("/^\\d{5}$/")
			formIsValid = false;
			request.setAttribute("msgCodePostal", "Le code postal est obligatoire et doit avoir 5 chiffres.");
		}
		if (ville.isEmpty() || ville.length() > 3) {
			formIsValid = false;
			request.setAttribute("msgVille", "La ville est obligatoire.");
		}
		if (telephone.isEmpty()  || telephone.length() < 10) {
			formIsValid = false;
			request.setAttribute("msgTel", "Un numéro de téléphone est obligatoire.");
		}
		if (mail.isEmpty() || !Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail)) {
			formIsValid = false;
			request.setAttribute("msgEmail", "L'email est obligatoire.");
		}
		if (mailVerif.isEmpty() || !mailVerif.contentEquals(mail)) {
			formIsValid = false;
			request.setAttribute("msgEmailVerif", "La confirmation du courriel n'est pas valide.");
		}
		if (password.isEmpty() || !Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$", password)) {
			formIsValid = false;
			request.setAttribute("msgMotDePasse", "Le mot de passe 'doit contenir au moins' : 8 caractères dont une majuscule, majuscule, chiffre et un spécial ($ @ % * + - _ !)");
		}
		if (passwordVerif.isEmpty() || !passwordVerif.contentEquals(password)) {
			formIsValid = false;
			request.setAttribute("msgPasswordVerif", "Les mots de passe ne correspondent pas.");
		}
		return formIsValid;
	}
}
