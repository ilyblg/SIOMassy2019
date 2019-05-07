package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SessionDao;
import entity.Candidature;

/**
 * Servlet implementation class CandidatureServlet
 */
@WebServlet("/candidatures")
public class CandidatureServlet extends HttpServlet {
	private final String VUE_CANDIDATURES = "WEB-INF/candidatures.jsp";
	private final String VUE_SESSIONS = "WEB-INF/sessionPourCandidatures.jsp";
	private final String VUE_ERREUR = "WEB-INF/erreur.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// En dur
		String vue = VUE_CANDIDATURES;

		try {
			SessionDao dao = new SessionDao();
			System.out.println("idSession : " + request.getParameter("idSession"));
			if (request.getParameter("idSession") == null) {
				System.out.println("pas de idSession");
				Map<Integer, String> sessions = dao.getToutesLesSessions();
				request.setAttribute("sessions", sessions);
				vue = VUE_SESSIONS;
			} else {
				int idSession = Integer.parseInt(request.getParameter("idSession"));
				List<Candidature> candidatures = dao.getCandidatures(idSession);
				request.setAttribute("candidatures", candidatures);
				request.setAttribute("idSession", idSession);
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
			// positionner un message
			request.setAttribute("message", exc.getMessage());
			vue = VUE_ERREUR;
		} catch (NumberFormatException exc) {
			exc.printStackTrace();
			request.setAttribute("message", "Le paramètre idSession est absent ou pas entier");
			vue = VUE_ERREUR;
		}
		request.getRequestDispatcher(vue).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new UnsupportedOperationException("not yet implemented");
	}

}