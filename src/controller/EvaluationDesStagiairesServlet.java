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
 * Servlet implementation class EvaluationDesStagiairesServlet
 */
@WebServlet("/evaluation")
public class EvaluationDesStagiairesServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoNote dao = new DaoNote();
		try {
			List<HashMap<String, Object>> notes = dao.getNoteByIdEvaluation(Integer.parseInt(request.getParameter("idEvaluation")));
			request.setAttribute("notes", notes);
			request.getRequestDispatcher("/WEB-INF/evaluation.jsp").forward(request, response);
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
