package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveServlet
 */
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int matches=Integer.parseInt(request.getParameter("matches"));
		int score=Integer.parseInt(request.getParameter("score"));
		int wickets=Integer.parseInt(request.getParameter("wickets"));
		int ducks=Integer.parseInt(request.getParameter("ducks"));
		String type=request.getParameter("type");
		
		
		Player p=new Player();
		p.setId(id);
		p.setName(name);
		p.setMatches(matches);
		p.setScore(score);
		p.setWickets(wickets);
		p.setDucks(ducks);
		p.setType(type);
		
		int status=PlayerDao.save(p);
		if(status>0){
			out.print("<p>Record saved successfully!</p>");
			request.getRequestDispatcher("AddPlayer.html").include(request, response);
		}else{
			out.println("Sorry! unable to save record");
		}
		
		out.close();
	}

}
