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

import dao.NoteDao;

/**
 * Servlet implementation class ListeNotesEvaluationServlet
 */
@WebServlet("/noter-stagiaire")
public class ListeNotesEvaluationServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3027079246108995799L;

	/**
	 * Traite la requete sur la page "evaluation", qui recupère au près de la base les notes des stagiaire
	 * d'une evaluation choisi par son ID.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoteDao dao = new NoteDao();
		try {
			int idEvaluation = Integer.parseInt(request.getParameter("id_evaluation"));
			List<HashMap<String, Object>> notes = dao.getNoteByIdEvaluation(idEvaluation);
			request.setAttribute("notes", notes);
			request.setAttribute("idEvaluation", idEvaluation);
			request.getRequestDispatcher("/WEB-INF/noterStagiaire.jsp").forward(request, response);
		}
		catch (NumberFormatException exc) {
			exc.printStackTrace();
		}
		catch (SQLException exc) {
			exc.printStackTrace();
		}
		
	}
}
