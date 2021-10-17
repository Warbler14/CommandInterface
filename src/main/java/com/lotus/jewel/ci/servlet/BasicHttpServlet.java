package com.lotus.jewel.ci.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicHttpServlet extends HttpServlet {

	private static final long serialVersionUID = -735269100966639923L;

	private ServletConfig config = null;
	
	private AtomicLong accessCount = new AtomicLong(0);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		System.out.println("Http servlet is initialized");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		Calendar cal = Calendar.getInstance();
		
		StringBuffer page = new StringBuffer();
		page.append("<html>").append("<body>")
		.append("<b>HttpServlet doGet</b>")
		.append("<p><span>").append(cal.getTime()).append("</span></p>")
		.append("<p><span>access count : ").append(accessCount.incrementAndGet()).append("</span></p>")
		.append("</body></html>");
		
		PrintWriter out = res.getWriter();
		out.print(page.toString());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		Calendar cal = Calendar.getInstance();
		
		StringBuffer page = new StringBuffer();
		page.append("<html>").append("<body>")
		.append("<b>HttpServlet doPost</b>")
		.append("<p><span>").append(cal.getTime()).append("</span></p>")
		.append("<p><span>access count : ").append(accessCount.incrementAndGet()).append("</span></p>")
		.append("</body></html>");
		
		PrintWriter out = res.getWriter();
		out.print(page.toString());
		
	}
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		accessCount.incrementAndGet();
		res.setStatus(200);
		
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.addHeader("Access-Control-Allow-Origin", "*");
		if (req.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(req.getMethod())) {
			res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			res.addHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type, Accept, Authorization");
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		res.setContentType("text/html");
//		
//		Calendar cal = Calendar.getInstance();
//		
//		StringBuffer page = new StringBuffer();
//		page.append("<html>").append("<body>")
//		.append("<b>HttpServlet doPut</b>")
//		.append("<p><span>").append(cal.getTime()).append("</span></p>")
//		.append("<p><span>access count : ").append(accessCount.incrementAndGet()).append("</span></p>")
//		.append("</body></html>");
//		
//		PrintWriter out = res.getWriter();
//		out.print(page.toString());
		
	}
	
	/*
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		Calendar cal = Calendar.getInstance();
		
		StringBuffer page = new StringBuffer();
		page.append("<html>").append("<body>")
		.append("<b>HttpServlet doTrace</b>")
		.append("<p><span>").append(cal.getTime()).append("</span></p>")
		.append("<p><span>access count : ").append(accessCount.incrementAndGet()).append("</span></p>")
		.append("</body></html>");
		
		PrintWriter out = res.getWriter();
		out.print(page.toString());
		
	}
	*/
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		res.setContentType("text/html");
//		
//		Calendar cal = Calendar.getInstance();
//		
//		StringBuffer page = new StringBuffer();
//		page.append("<html>").append("<body>")
//		.append("<b>HttpServlet doDelete</b>")
//		.append("<p><span>").append(cal.getTime()).append("</span></p>")
//		.append("<p><span>access count : ").append(accessCount.incrementAndGet()).append("</span></p>")
//		.append("</body></html>");
//		
//		PrintWriter out = res.getWriter();
//		out.print(page.toString());
		
	}
	
	@Override
	protected long getLastModified(HttpServletRequest req)  {
		return super.getLastModified(req);
	}
	
	@Override
	public ServletConfig getServletConfig() {
		return config;
	}

	@Override
	public String getServletInfo() {
		return "servlet test 2021-";
	}

	@Override
	public void destroy() {
		System.out.println("servlet is destroyued");
		
	}
}
