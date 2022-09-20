package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1>Update Employee</h1>");
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		
		Player e=PlayerDao.getEmployeeById(id);
		
		out.print("<form action='EditServlet1' method='post'>");
		out.print("<table>");
		out.print("<tr><td></td><td><input type='number' name='id' value='"+e.getId()+"'/></td></tr>");
		out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+e.getName()+"'/></td></tr>");
		out.print("<tr><td></td><td><input type='number' name='matches' value='"+e.getMatches()+"'/></td></tr>");
		out.print("<tr><td></td><td><input type='number' name='score' value='"+e.getScore()+"'/></td></tr>");
		out.print("<tr><td></td><td><input type='number' name='wickets' value='"+e.getWickets()+"'/></td></tr>");
		out.print("<tr><td></td><td><input type='number' name='ducks' value='"+e.getDucks()+"'/></td></tr>");		
		out.print("<tr><td>Type:</td><td>");
		out.print("<select name='type' style='width:150px'>");
		out.print("<option>Batsman</option>");
		out.print("<option>Bowler</option>");
		out.print("<option>WicketKeeper</option>");		
		out.print("</select>");
		out.print("</td></tr>");
		out.print("<tr><td colspan='2'><input type='submit' value='Edit &amp; Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		
		out.close();
	}

}
