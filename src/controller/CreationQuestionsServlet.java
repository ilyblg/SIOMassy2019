package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QcmDao;
import dao.QuestionDao;
import dao.ReponsePossibleDao;
import entity.Personne;
import entity.Qcm;
import entity.Question;
import entity.ReponsePossible;

/**
 * Servlet implementation class CreationQuestionsServlet
 */
@WebServlet("/CreationQuestionsServlet")
public class CreationQuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationQuestionsServlet() {
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
		boolean ok = false;
		Question question = null;
		List<ReponsePossible> reponsesPossibles = new ArrayList<>();
		
		try {
			Personne user = (Personne) request.getSession(true).getAttribute("user");
			if (user != null) {
				int idQcm = Integer.parseInt(request.getParameter("idQcm1"));
				String enonce = request.getParameter("enonceQuestion1");
				
				QuestionDao questionDao = new QuestionDao();
				question = new Question(enonce, idQcm);
				questionDao.insert(question);

				String reponse1 = request.getParameter("reponsePossible11");
				String reponse2 = request.getParameter("reponsePossible22");
				String reponse3 = request.getParameter("reponsePossible33");
				String reponse4 = request.getParameter("reponsePossible44");
				int reponseCorrect = Integer.parseInt(request.getParameter("reponseCorrect1"));

				ReponsePossibleDao reponsePossibleDao = new ReponsePossibleDao();
				
				reponsesPossibles.add(new ReponsePossible(reponse1, (reponseCorrect == 1) ? true : false, question.getId()));
				reponsesPossibles.add(new ReponsePossible(reponse2, (reponseCorrect == 2) ? true : false, question.getId()));
				reponsesPossibles.add(new ReponsePossible(reponse3, (reponseCorrect == 3) ? true : false, question.getId()));
				reponsesPossibles.add(new ReponsePossible(reponse4, (reponseCorrect == 4) ? true : false, question.getId()));
				
				reponsePossibleDao.insert(reponsesPossibles);
				
				ok = true;
			}
		} catch (SQLException exc) {
			System.out.println(exc.getMessage());
		}
		
		String reponse = ok ? ""+question.getId() : "-1";
		
	    response.setContentType("text/plain");
	    response.getWriter().write(reponse);
	}

}
