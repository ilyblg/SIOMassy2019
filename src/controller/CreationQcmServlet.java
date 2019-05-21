package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QcmDao;
import entity.Personne;
import entity.Qcm;

/**
 * Servlet implementation class CreationTitreQcmServlet
 */
@WebServlet("/CreationQcmServlet")
public class CreationQcmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationQcmServlet() {
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
		Qcm qcm = null;
		
		try {
			Personne user = (Personne) request.getSession(true).getAttribute("user");
			if (user != null) {
				String titreQcm = request.getParameter("titreQcm1");
				int idFormateur = user.getId();

				LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
				
				QcmDao qcmDao = new QcmDao();
				qcm = new Qcm(now, titreQcm, idFormateur);
				qcmDao.insert(qcm);
				
				ok = true;
			}
		} catch (SQLException exc) {
			System.out.println(exc.getMessage());
		}
		
		String reponse = ok ? ""+qcm.getId() : "-1";
		
	    response.setContentType("text/plain");
	    response.getWriter().write(reponse);
	}

}
