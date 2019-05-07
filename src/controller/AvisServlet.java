package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AvisDao;
import entity.Avis;
import entity.Personne;

/**
 * Servlet implementation class AvisServlet
 */
@WebServlet("/avis")
public class AvisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String FORM_PATH = "/WEB-INF/avis.jsp";
	private final String ERROR_PATH = "/WEB-INF/erreur.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vue = FORM_PATH;
		Personne user = (Personne) request.getSession(true).getAttribute("user");
		if (user == null) {
			request.setAttribute("message", "Vous devez vous connecter");
			vue = ERROR_PATH;
		}
		request.getRequestDispatcher(vue).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Personne user = (Personne) request.getSession(true).getAttribute("user");
		if (user == null) {
			request.setAttribute("message", "Vous devez vous connecter");
		} else {
			try {
				int idQcm = Integer.parseInt(request.getParameter("idQcm"));
				String commentaire = request.getParameter("commentaire");
				int note = Integer.parseInt(request.getParameter("note"));
				// System.out.println("La personne inscrite sous l'id :" + idPersonne +" a mis
				// pour le qcm n° :");
				System.out.println("Id du QCM n° :" + idQcm);
				System.out.println("le commentaire :" + commentaire);
				System.out.println("et la note de :" + note);
				AvisDao dao = new AvisDao();
				Avis instance = new Avis(user.getId(), idQcm, commentaire, note, LocalDateTime.now());
				dao.insert(instance);
				System.out.println("Avis" + FORM_PATH + ("posté"));
			} catch (SQLException exc) {
				System.out.println(exc.getMessage());
				request.setAttribute("erreurDao", exc.getMessage());
			}
		}
		request.getRequestDispatcher(FORM_PATH).forward(request, response);
	}

}
