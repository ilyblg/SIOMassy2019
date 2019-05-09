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

import dao.PassageQcmDao;
import dao.QcmDao;
import dao.QuestionDao;
import entity.PassageQcm;
import entity.Personne;
import entity.Qcm;
import entity.Question;

/**
 * Servlet implementation class PassageQcm
 */
@WebServlet("/PassageQcmServlet")
public class PassageQcmServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String FORM_PATH = "/WEB-INF/affichagePassageQcm.jsp";
	private final String ERREUR_PATH = "/WEB-INF/erreur.jsp";
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vue = ERREUR_PATH;
		try {
			Personne user = (Personne) request.getSession(true).getAttribute("user");
			if (user != null) { // changer en if (user == null) {
				request.setAttribute("message", "Vous devez être connecté");
			} else {
				int idq = Integer.parseInt(request.getParameter("qcmApasser"));
				int idp = 1; // changer en user.getId()

				LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
				
				PassageQcmDao pqd = new PassageQcmDao();
				PassageQcm passageQcm = new PassageQcm(now, idq, idp);
				pqd.insert(passageQcm);

				String titreQcm = null;
				QuestionDao questionDao = new QuestionDao();
				List<Question> questions = new ArrayList<>();
				
				titreQcm = questionDao.getTitreQcmByIdQcm(idq);
				questions = questionDao.getByIdQcm(idq);
				
				request.setAttribute("idPassageQcm", passageQcm.getId());
				request.setAttribute("questions", questions);
				request.setAttribute("titreQcm", titreQcm);
				
				vue = FORM_PATH;
			}
		} catch (NumberFormatException exc) {
			request.setAttribute("message", "idQcm doit être indiqué et un entier");
		} catch (SQLException exc) {
			request.setAttribute("message", exc.getMessage());
		}
		System.out.println(vue);

		request.getRequestDispatcher(vue).forward(request, response);
	}
}

