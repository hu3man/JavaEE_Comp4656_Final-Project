package ca.bcit.comp4656.webapp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@SuppressWarnings("serial")
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet 
{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		if ( session !=null )
		{
			session.invalidate();
		}
		
		resp.sendRedirect(req.getContextPath());
	}
}
