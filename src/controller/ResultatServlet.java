package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReponseDonneeDao;
import dao.ReponsePossibleDao;
import entity.ReponseDonnee;
import entity.ReponsePossible;

/**
 * Servlet implementation class ResultatServlet
 */
@WebServlet("/ResultatServlet")
public class ResultatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String FORM_PATH = "/WEB-INF/resultatQcm.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPassageQcm = Integer.parseInt(request.getParameter("idPassageQcm"));
		
		List<ReponsePossible> reponses = new ArrayList<>();
		
		ReponsePossibleDao rpd = new ReponsePossibleDao();
		try {
			reponses = rpd.getReponsesPossiblesByIdPassageQcm(idPassageQcm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int max = reponses.size();
		int score = 0;
		
		for (ReponsePossible r : reponses) {
			if (r.isEstCorrect())
				score++;
		}
		
		double percentage = (100 * score) / max;
		
		//String result = "T'as fais = "+ score +" points sur "+ max +" ("+percentage+"%)";
		
		request.setAttribute("score", score);
		request.setAttribute("max", max);
		request.setAttribute("percentage", percentage);

		request.getRequestDispatcher(FORM_PATH).forward(request, response);
		
		//response.setContentType("text/plain");
	    //response.getWriter().write(result);
	}

}
