package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SessionDao;
import entity.Candidature;

/**
 * Servlet implementation class ServletCandidature
 */
@WebServlet("/candidatures")
public class CandidatureServlet extends HttpServlet {
	private final String VUE_CANDIDATURES = "WEB-INF/candidatures.jsp";
	private final String VUE_ERREUR = "WEB-INF/erreur.jsp";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// En dur
		String vue = VUE_CANDIDATURES;
		try {
			int idSession = Integer.parseInt(request.getParameter("idSession"));
			SessionDao dao = new SessionDao();
			List<Candidature> candidatures = dao.getCandidatures(idSession);
			request.setAttribute("candidatures", candidatures);
			request.setAttribute("idSession", idSession);
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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		throw new UnsupportedOperationException("not yet implemented");
	}

}
