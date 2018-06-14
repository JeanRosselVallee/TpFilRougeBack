package shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import shop.model.CatalogModel;

//@WebServlet( name="Catalog", urlPatterns = {"/catalog", "/catalog/add", "/catalog/rem"} )
public class Catalog extends HttpServlet {

	public Catalog() {
		super();
	}

	
	public void doGet(

			HttpServletRequest p_request, 
			HttpServletResponse p_response

	) throws ServletException, IOException {

		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setCharacterEncoding("utf-8");

		// si la connexion à la bdd a échoué alors..
		if (CatalogModel.getInstance().isConnected() == false) 
		{
			// on redirige vers la page FailureSQL
			this.getServletContext().getRequestDispatcher("/FailureSQL.jsp").forward(p_request, p_response);
		} 
		else // sinon ...
		{
			// on récupère le catalogue et on l'affiche
			p_request.setAttribute("catalog", CatalogModel.getInstance().getCatalog());
			this.getServletContext().getRequestDispatcher("/Catalog.jsp").forward(p_request, p_response);
		}

	}

	public void doPost(

			HttpServletRequest p_request, HttpServletResponse p_response

	) throws ServletException, IOException {
		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setCharacterEncoding("utf-8");

		int len = CatalogModel.getInstance().getCatalog().size();
		boolean success = false;

		if (p_request.getParameter("price") != null && p_request.getParameter("tva") != null
				&& p_request.getParameter("url") != null && p_request.getParameter("title") != null) {

			float price = Float.parseFloat(p_request.getParameter("price"));
			float tva = Float.parseFloat(p_request.getParameter("tva"));
			String url = p_request.getParameter("url");
			String title = p_request.getParameter("title");

			CatalogModel.getInstance().add(title, url, price, tva);

			success = (CatalogModel.getInstance().getCatalog().size() > len);
		}

		if (success == true) {
			this.getServletContext().getRequestDispatcher("/Success.jsp").forward(p_request, p_response);
		} else {
			this.getServletContext().getRequestDispatcher("/Failure.jsp").forward(p_request, p_response);
		}

	}

	public void doOptions(HttpServletRequest p_request, HttpServletResponse p_response)
			throws ServletException, IOException {

		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		p_response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		p_response.setCharacterEncoding("utf-8");

		this.getServletContext().getRequestDispatcher("/Success.jsp").forward(p_request, p_response);

	}

	public void doDelete(

			HttpServletRequest p_request, HttpServletResponse p_response

	) throws ServletException, IOException {
		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setCharacterEncoding("utf-8");

		boolean success = false;

		if (p_request.getParameter("id") != null && p_request.getParameter("api") != null) {
			long id = Integer.parseInt(p_request.getParameter("id"));
			String apiKey = p_request.getParameter("api");

			if (apiKey.equals("azerty123")) {

				success = CatalogModel.getInstance().removeProductById( id);

			}
		}

		if (success == true) {
			this.getServletContext().getRequestDispatcher("/Success.jsp").forward(p_request, p_response);
		} else {
			this.getServletContext().getRequestDispatcher("/Failure.jsp").forward(p_request, p_response);
		}

	}

}
