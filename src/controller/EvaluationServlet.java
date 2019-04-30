package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.EvaluationDao;
import entity.Evaluation;

/**
 * Servlet implementation class of EvaluationServlet
 * Permet de traiter les pages d'affichage de la liste des Evluation en cours
 * pour les personne "Formateur"
 */
@WebServlet("/liste-evaluations")
public class EvaluationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Evaluation> evaluations = null;
		EvaluationDao dao = new EvaluationDao();
		try {
			evaluations = dao.getListByIdFormateur(7);
			request.setAttribute("listeEvaluations", evaluations);
			request.getRequestDispatcher("/WEB-INF/listeEvaluations.jsp").forward(request, response);
		} catch (SQLException e) {
			response.getWriter().println("PROBLEMATIQUE DE SELECTION");
			e.printStackTrace();
		}
	}
}
