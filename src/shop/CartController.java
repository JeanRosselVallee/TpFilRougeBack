package shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.CartModel;
import shop.model.CartViewModel;

/**
 * Offers the services related to the Cart: 
 * - get the full cart 
 * - post a new product to be added to the cart 
 * - delete an item present in the cart
 * The url must specify the required service as configured in web.xml 
 */
public class CartController extends HttpServlet {
	private static final int Long = 0;
	
	public CartController() {
		super();
	}

	/**
	 * Initialises the cart with the records in the table cart
	 * Author	Nicolas
	 * @param  	p_request 
	 * @param  	p_response 
	 */
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
			p_request.setAttribute("CartView", CartViewModel.getInstance().getCart());
			this.getServletContext().getRequestDispatcher("/CartView.jsp").forward(p_request, p_response);
		}

	}
	
	/**
	 * Adds an item to the cart
	 * Author	Lila
	 * @param  	p_request 
	 * @param  	p_response 
	 */
	public void doPost( HttpServletRequest p_request, HttpServletResponse p_response
		) throws ServletException, IOException {
		
		long cartId= 0;  
		
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
		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		p_response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
		p_response.setCharacterEncoding("utf-8");
		this.getServletContext().getRequestDispatcher("/Success.jsp").forward(p_request, p_response);
	}

	/**
	 * Deletes an item present in the cart
	 * Author	J. Vallee
	 * @param  	p_request 
	 * @param  	p_response 
	 */
	public void doDelete( HttpServletRequest p_request, HttpServletResponse p_response
		) throws ServletException, IOException {

		p_response.setHeader("Access-Control-Allow-Origin", "*");
		p_response.setCharacterEncoding("utf-8");

		/**
		* Checks the parameters in the request before executing the Delete operation
		*/
		boolean success = false;
		if (p_request.getParameter("id") != null && p_request.getParameter("api") != null) {
			long id = Integer.parseInt(p_request.getParameter("id"));
			String apiKey = p_request.getParameter("api");
			if (apiKey.equals("azerty123")) {
				success = CartModel.getInstance().removeProductById(id);
			}
		}

		/**
		* Notifies the result of the Delete operation
		*/
		if (success == true) {
			System.out.println(" deletion of Cart item succeeded");
			this.getServletContext().getRequestDispatcher("/Success.jsp").forward(p_request, p_response);
		} else {
			System.out.println(" deletion of Cart item failed");
			this.getServletContext().getRequestDispatcher("/Failure.jsp").forward(p_request, p_response);
		}
	}
}
