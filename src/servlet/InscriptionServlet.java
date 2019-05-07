package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entite.Personne;
import dao.PersonneDao;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String FORM_PATH = "/WEB-INF/formulaireInscription.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("estFormateur", false);
		request.setAttribute("estAdministration", false);
		request.getRequestDispatcher(FORM_PATH).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean saisieOk = true;
		boolean daoOk = true;
		// V�rifier parametres
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String tel = request.getParameter("tel");
		String adresse = request.getParameter("adresse");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		String motDePasse2 = request.getParameter("motDePasse2");
		boolean estFormateur = Boolean.parseBoolean(request.getParameter("estFormateur"));
		boolean estAdministration = Boolean.parseBoolean(request.getParameter("estAdministration"));
		System.out.println(nom);
		System.out.println(prenom);
		System.out.println(mail);
		System.out.println(tel);
		System.out.println(adresse);
		System.out.println(codePostal);
		System.out.println(ville);
		System.out.println(estFormateur);
		System.out.println(estAdministration);

		if (motDePasse == null || motDePasse.isEmpty()) {
			request.setAttribute("erreurMotDePasse", "Le mot de passe doit être saisi");
			saisieOk = false;
		} else if (!motDePasse.equals(motDePasse2)) {
			request.setAttribute("erreurMotDePasse2", "Les mots de passe doivent être identiques");
			saisieOk = false;
		}
		// etc.
		// Si les parametres de saisie sont ok j'ins�re la personne dans la dao
		if (saisieOk) {
			try {
				PersonneDao dao = new PersonneDao();
				Personne instance = new Personne(-1, nom, prenom, mail, tel, adresse, codePostal, ville, motDePasse2,
						estFormateur, estAdministration, LocalDateTime.now());
				dao.inserer(instance);
				System.out.println("personne inscrite sous le id " + instance.getId());
			}
			// si l'insertion de la personne dans la dao ne se fait j'affiche le message
			// erreur dao
			catch (SQLException exc) {
				System.out.println(exc.getMessage());
				request.setAttribute("erreurDao", exc.getMessage());
				daoOk = false;
			}
		}

		if (saisieOk && daoOk) {
			response.sendRedirect("./accueil");
		} else {
			request.getRequestDispatcher(FORM_PATH).forward(request, response);
		}
	}

	private Timestamp TimeStamp() {
		// TODO Auto-generated method stub
		return null;
	}

}
