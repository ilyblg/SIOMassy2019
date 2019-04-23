package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PassageQcmDao;
import entity.PassageQcm;
import entity.Personne;

@WebServlet("/passageQcm")
public class PassageQcmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String FORM_PATH = "/WEB-INF/affichagePassageQcm.jsp";
	private final String ERREUR_PATH = "/WEB-INF/erreur.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vue = ERREUR_PATH;
//		try {
//			Personne user = (Personne) request.getSession(true).getAttribute("user");
//			if (user == null) {
//				request.setAttribute("message", "Vous devez être connecté");
//			} else {
//				int idQcm = Integer.parseInt(request.getParameter("idQcm"));
//				PassageQcmDao dao = new PassageQcmDao();
//				PassageQcm passageQcm = new PassageQcm(idQcm, LocalDateTime.now(), user.getId());
//				dao.insert(passageQcm);
//				request.setAttribute("passageQcm", passageQcm);
//				vue = FORM_PATH;
//			}
//		} catch (NumberFormatException exc) {
//			request.setAttribute("message", "idQcm doit être indiqué et un entier");
//		} catch (SQLException exc) {
//			request.setAttribute("message", exc.getMessage());
//		}
//		System.out.println(vue);
		request.getRequestDispatcher(vue).forward(request, response);
	}
}
