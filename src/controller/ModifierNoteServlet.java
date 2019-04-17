package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoNote;

/**
 * Servlet implementation class ModifierNoteServlet
 */
@WebServlet("/noter-stagiaire")
public class ModifierNoteServlet extends HttpServlet {
	/**
	 * Servlet de vérification des données saisie sur la page d'affichage des stagiaires avec leur note
	 * Permet de vérifier qu'il s'agit bien d'un Double entre 0 et 20.
	 * Le cas echéant renvoyer au scrip JS de la page le code erreur pour affichage.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int status = HttpServletResponse.SC_NO_CONTENT; // success
		String body = "";
		String idPerson = request.getParameter("idPersonne");
		String idEval = request.getParameter("idEvaluation");
		try {
			double note = Double.parseDouble(request.getParameter("note"));
			if (note < 0 || note > 20) {
				status = HttpServletResponse.SC_BAD_REQUEST;
				body = "Une note ne peut être négative ou supérieur à 20";
			}
			else {
				DaoNote dao = new DaoNote();
				if(dao.insertNoteStagiaire(idPerson , idEval, note)) {
					body = "La note à été modifiée.";
				}
			}
		}
		catch (NumberFormatException exc) {
			status = HttpServletResponse.SC_BAD_REQUEST;
			body = "La note doit être un nombre";
		} catch (SQLException e) {
			body = "Erreur d'insertion en base de données.";
		}		
		response.setStatus(status);
		response.getWriter().println(body);
	}

}
