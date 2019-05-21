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

import dao.QcmDao;
import entity.PassageQcm;
import entity.Qcm;

/**
 * Servlet implementation class ListerQcmServlet
 */
@WebServlet("/passageQcm") // @WebServlet("/ListerQcmServlet")
public class ListerQcmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String FORM_PATH = "/WEB-INF/listeQcms.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerQcmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QcmDao qcmd = new QcmDao();
		List<Qcm> qcms = new ArrayList<>();
		
		try {
			qcms = qcmd.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(qcms);
		
		request.setAttribute("qcms", qcms);

		request.getRequestDispatcher(FORM_PATH).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
