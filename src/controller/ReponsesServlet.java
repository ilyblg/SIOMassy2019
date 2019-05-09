package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PassageQcmDao;
import dao.ReponseDonneeDao;
import entity.PassageQcm;
import entity.ReponseDonnee;

/**
 * Servlet implementation class ReponsesServlet
 */
@WebServlet("/ReponsesServlet")
public class ReponsesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReponsesServlet() {
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
		int idQuestion = Integer.parseInt(request.getParameter("idQuestion1"));
		int idPassageQcm = Integer.parseInt(request.getParameter("idPassageQcm1"));
		int idReponse = Integer.parseInt(request.getParameter("idReponse1"));
		
		ReponseDonnee reponseDonnee = new ReponseDonnee(idQuestion, idPassageQcm, idReponse);
		
		ReponseDonneeDao rdd = new ReponseDonneeDao();
		try {
			rdd.insert(reponseDonnee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = "Reponse envoyée";
		
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    //response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(result);
	}

}
