package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoPersonne;
import entity.Personne;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private final String FORM_PATH = "/WEB-INF/connexion.jsp";
	private final String HOME_PATH = "./accueil";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(FORM_PATH).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recup�re les param�tres
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		boolean saisieOk = (mail != null && !mail.isEmpty() && mdp != null && !mdp.isEmpty());
		boolean daoOk = false;
		Personne user;
		if (saisieOk) {
			try {
				DaoPersonne dao = new DaoPersonne();
				user = dao.getByLoginMdp(mail, mdp);
				if (user != null) {
					request.getSession(true).setAttribute("user", user);
					daoOk = true;
				} else {
					request.setAttribute("msgConnexion", "Utilisateur inconnu ou mot de passe invalide");
				}
			} catch (SQLException exc) {
				request.setAttribute("msgConnexion", exc.getMessage());
			}
		}
		else {
			request.setAttribute("msgConnexion", "Login et mot de passe sont obligatoires");
		}
		if (saisieOk && daoOk) {
			response.sendRedirect(HOME_PATH);
		}
		else {
			request.getRequestDispatcher(FORM_PATH).forward(request, response);
		}
	}
}
