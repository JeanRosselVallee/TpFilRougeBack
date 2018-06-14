package shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import shop.model.CartModel;
import shop.model.CartViewModel;

//@WebServlet( name="Cart", urlPatterns = {"/Cart", "/Cart/add", "/Cart/rem"} )

public class CartController extends HttpServlet {
	


	private static final int Long = 0;

	public CartController() {
		super();
	}

	
	public void doGet(
			HttpServletRequest p_request, 
			HttpServletResponse p_response

	) throws ServletException, IOException {

		System.out.println(" Cart Controller doGet ");
		
		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setCharacterEncoding("utf-8");

		// si la connexion à la bdd a échoué alors..
		if (CartModel.getInstance().isConnected() == false) 
		{
			// on redirige vers la page FailureSQL
			this.getServletContext().getRequestDispatcher("/FailureSQL.jsp").forward(p_request, p_response);
		} 
		else // sinon ...
		{
			// on récupère le Cartue et on l'affiche
			//p_request.setAttribute("Cart", CartModel.getInstance().getCart());
			
			// Modified by Jean on Tuesday 12 June
			// Display view of products in cart instead of 2 id's only
			p_request.setAttribute("CartView", CartViewModel.getInstance().getCart());
			
			//this.getServletContext().getRequestDispatcher("/Cart.jsp").forward(p_request, p_response);
			this.getServletContext().getRequestDispatcher("/CartView.jsp").forward(p_request, p_response);
		}

	}

	public void doPost(

			HttpServletRequest p_request, HttpServletResponse p_response

	) throws ServletException, IOException {
		
		long cartId= 0;  
		//System.out.println(" Cart Controller doPost ");
		
		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setCharacterEncoding("utf-8");

		int len = CartModel.getInstance().getCart().size();
		boolean success = false;

		String s_productid = p_request.getParameter("Product_id");
		
		
		if (s_productid != null) {
			int Product_id =Integer.parseInt(s_productid);

			CartModel.getInstance().add(cartId, Product_id);

			success = (CartModel.getInstance().getCart().size() > len);
		}

		if (success == true) {
			System.out.println("insert success"  );

			this.getServletContext().getRequestDispatcher("/Success.jsp").forward(p_request, p_response);
		} else {
			System.out.println("insert failure"  );
			this.getServletContext().getRequestDispatcher("/Failure.jsp").forward(p_request, p_response);
		}

	}

	public void doOptions(HttpServletRequest p_request, HttpServletResponse p_response)
			throws ServletException, IOException {


		//System.out.println(" Cart Controller doOptions ");
		
		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		p_response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		p_response.setCharacterEncoding("utf-8");

		this.getServletContext().getRequestDispatcher("/Success.jsp").forward(p_request, p_response);

	}

	public void doDelete(

			HttpServletRequest p_request, HttpServletResponse p_response

	) throws ServletException, IOException {
		

		//System.out.println(" Cart Controller doDelete ");
		
		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setCharacterEncoding("utf-8");

		boolean success = false;
		//System.out.println("id=" + p_request.getParameter("id"));
		//System.out.println("api=" + p_request.getParameter("api") );
		if (p_request.getParameter("id") != null && p_request.getParameter("api") != null) {
			//System.out.println(" not null params ");
			long id = Integer.parseInt(p_request.getParameter("id"));
			String apiKey = p_request.getParameter("api");

			if (apiKey.equals("azerty123")) {
				//System.out.println(" apiKey OK");

				success = CartModel.getInstance().removeProductById(id);

			}
		}

		if (success == true) {
			System.out.println(" delete succeeded");
			this.getServletContext().getRequestDispatcher("/Success.jsp").forward(p_request, p_response);
		} else {
			System.out.println(" delete failed");
			this.getServletContext().getRequestDispatcher("/Failure.jsp").forward(p_request, p_response);
		}

	}

}
