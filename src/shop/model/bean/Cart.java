package shop.model.bean;

//import shop.$missing$;

public class Cart {
	
	private Long id; 
	private int cartid;
	private int productid;


	public Cart( ){
		super();

		System.out.println(" Model Bean Cart Constructor ");
	}

	public Cart( 
		Long p_id, 
		int p_Product_id) 
		{
		this.id=p_id;
		this.productid=p_Product_id;
		
		System.out.println(" Model Bean Cart Constructor w/ arguments");
	}
	
	
	public String toString() {		
		String data = "{";
	
		data += " \"id\": \""	+ this.id		+ "\",";
		data += " \"Product_id\": \""+ this.productid	+ "\"";
		data += "}";
		
		return data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}
}