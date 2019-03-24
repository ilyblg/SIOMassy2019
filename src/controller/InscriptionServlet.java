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
	private Personne personne;
	DaoPersonne daoPers;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
    }
    
    /**
	 * @see Méthode doGet qui renvoie vers la page de formulaire
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Inscription.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Vérifie le formulaire, si valide vérifie présence en base de donnée de la personne
		// et son instanciation pour traitement
		try {
			daoPers = new DaoPersonne();
			if(checkFormData(request)){
				// Instanciation de notre objet personne
				personne = new Personne(
				request.getParameter("nom"),
				request.getParameter("prenom"),
				request.getParameter("adresse"),
				Integer.parseInt(request.getParameter("cp")),
				request.getParameter("ville"),
				request.getParameter("telephone"),
				request.getParameter("mail"),
				request.getParameter("password"),
				false,
				false,
				Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
			
				// Si formulaire verifié valide et que la personne n'est pas enregistrée
				// On envoie l'enregistrement en base de données et envoie le mail de confirmation
				if (!daoPers.ifExist(personne)) {
					// Insertion de la personne en base de données
					// Retourne un bouléen indiquant la bonne exécution de la requête
					if(daoPers.insertInDB(personne)) {
						// ENVOIE MAIL
						response.sendRedirect(request.getContextPath() + "/Connexion.jsp");
					}
				}
			}
			else { // Si formulaire invalide, renvoie sur la page avec les msg d'erreurs correspondant
				//response.sendRedirect("/Inscription.jsp");
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

		boolean formIsValid = true;

		// Recuperer les parametres
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		//String cp = request.getParameter("cp");
		int cp = Integer.parseInt(request.getParameter("cp"));
		String ville = request.getParameter("ville");
		String telephone = request.getParameter("telephone");
		String mail = request.getParameter("mail");
		String mailVerif = request.getParameter("mailVerif");
		String password = request.getParameter("mail");

		// Non utiliser pour vérifications basique
		//boolean isFormateur = Boolean.parseBoolean(request.getParameter("estFormateur"));
		//boolean isAdmin = Boolean.parseBoolean(request.getParameter("estAdmin"));

		DaoPersonne daoPersonne = new DaoPersonne();
		if (!daoPersonne.checkEmail(mail)) {
			formIsValid = false;
			request.setAttribute("msgEmail", "L'email " + mail
					+ " existe déjà.");
			System.out.println("MAIL in base");
		}

		// Les tests pour vérifier si les champs sont vides
		if (nom.isEmpty()) {
			formIsValid = false;
			request.setAttribute("msgNom", "Le nom est obligatoire.");
			System.out.println("nom");
		}
		if (prenom.isEmpty()) {
			formIsValid = false;
			request.setAttribute("msgPrenom", "Le prénom est obligatoire.");
			System.out.println("prenom");
		}
		if (mail.isEmpty()) {
			formIsValid = false;
			request.setAttribute("msgEmail", "L'email est obligatoire.");
			System.out.println("mail");
		}
		if (mailVerif.matches("^ *") && !mailVerif.contentEquals(mail)) {
			formIsValid = false;
			request.setAttribute("msgEmailVerif",
					"La confirmation du courriel n'est pas valide.");
			System.out.println("mailVerif");
		}		
		if (password.isEmpty()) {
			formIsValid = false;
			request.setAttribute("msgMotDePasse",
					"Le mot de passe est obligatoire.");
			System.out.println("password");
		}
		if (adresse.isEmpty()) {
			formIsValid = false;
			request.setAttribute("msgAdresse", "L'adresse est obligatoire.");
			System.out.println("adresse");
		}
		if (cp < 0) {
			formIsValid = false;
			request.setAttribute("msgCodePostal", "Le code postal est obligatoire.");
			System.out.println("cp");
		}
		if (ville.isEmpty()) {
			formIsValid = false;
			request.setAttribute("msgVille", "La ville est obligatoire.");
			System.out.println("ville");
		}
		if (telephone.isEmpty()) {
			formIsValid = false;
			request.setAttribute("msgTel", "Un numéro de téléphone est obligatoire.");
			System.out.println("telephone");
		}

		// Si formulaire valide alors...
		if (formIsValid) {
			Personne personne = new Personne(request.getParameter("nom"), request.getParameter("prenom"),
											request.getParameter("adresse"),
											Integer.parseInt(request.getParameter("cp")), request.getParameter("ville"),
											request.getParameter("telephone"), request.getParameter("mail"), "password",
											Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
			System.out.println(personne);
		}
		return formIsValid;
	}
}
