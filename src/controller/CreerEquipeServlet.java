package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EquipeDao;
import entity.Equipe;
import entity.Personne;

/**
 * Servlet implementation class CreerEquipe
 */
@WebServlet("/creerEquipe")
public class CreerEquipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String FORM_PATH = "/WEB-INF/nouvelleEquipe.jsp";
	private final String ERREUR_PATH = "/WEB-INF/erreur.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vue = ERREUR_PATH;
		boolean saisieOk = true;
		boolean daoOk = true;
		// Verifier conditions
		try {
			int idProjet = Integer.parseInt(request.getParameter("idProjet")); // si idProjet entier
			request.setAttribute("idProjet", idProjet); // mettre l'attribut idProjet
		} catch (NumberFormatException exc) { // sinon (pas un entier)
			request.setAttribute("message", "idProjet doit être un entier"); // mettre le message d'erreur
			saisieOk = false; // saisieOk faux
		}

		// Si ok, verifier dao
		if (saisieOk && daoOk) {
			ArrayList<Equipe> data = new ArrayList<>(); // nouvelle liste data
			data.add(new Equipe(1,2,3)); // ajouter equipe dans la liste data
			data.add(new Equipe(6,8,4));
			request.setAttribute("data", data); // mettre l'attribut liste data
			request.getRequestDispatcher("/WEB-INF/projet.jsp").forward(request, response); // destiné au fichier
																							// projet.jsp
		} else {
			request.getRequestDispatcher("/WEB-INF/erreur.jsp").forward(request, response); // sinon destiné a
																								// message.jsp
		}
		try {
		Personne user = (Personne) request.getSession(true).getAttribute("user");
			if (user == null) {
			request.setAttribute("message", "Vous devez être connecté");
		} else {
			int idProjet= Integer.parseInt(request.getParameter("idProjet"));
			EquipeDao dao = new EquipeDao();
			Equipe equipe = new Equipe(-1,idProjet, user.getId());
			dao.insert(equipe);
			request.setAttribute("equipe", equipe);
			vue = FORM_PATH;
			}
		} catch (NumberFormatException exc) {
			request.setAttribute("message", "idEquipe doit être indiqué et un entier");
		} catch (SQLException exc) {
			request.setAttribute("message", exc.getMessage());
		}
		System.out.println(vue);
		// request.getRequestDispatcher(vue).forward(request, response);
	}

}
