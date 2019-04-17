package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoNote;

/**
 * Servlet implementation class ListeNotesEvaluationServlet
 */
@WebServlet("/noter-stagiaire")
public class ListeNotesEvaluationServlet extends HttpServlet {
	/**
	 * Traite la requete sur la page "evaluation", qui recupère au près de la base les notes des stagiaire
	 * d'une evaluation choisi par son ID.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoNote dao = new DaoNote();
		try {
			int idEvaluation = 6;
			List<HashMap<String, Object>> notes = dao.getNoteByIdEvaluation(idEvaluation);
			request.setAttribute("notes", notes);
			request.setAttribute("idEvaluation", idEvaluation);
			request.getRequestDispatcher("/WEB-INF/noterStagiaire.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
