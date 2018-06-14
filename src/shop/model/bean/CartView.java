package shop.model.bean;

//import shop.$missing$;

public class CartView {
	

	private Long id;
	//private Long cart_item_id; 
	private Long product_id; 
	private String url;
	private String title;
	private float price;
	private float tva;
	
	
	public CartView( ){
		super();
	}
	
	public CartView( 
			Long p_id,
			//Long p_cart_item_id, 
			Long p_product_id, 
			String p_title, 
			String p_url, 
			float p_price, 
			float p_tva
	) {
		super();
		this.id 	= p_id;
		//this.cart_item_id 	= p_cart_item_id;
		this.product_id 	= p_product_id;
		this.tva 	= p_tva;
		this.url 	= p_url;
		this.title 	= p_title;
		this.price 	= p_price;
	}
	


	public String toString() {		
		String data = "{";
	
		//data += " \"id\": \""	+ this.cart_item_id		+ "\",";
		data += " \"id\": \""	+ this.id		+ "\",";
		data += " \"title\": \""+ this.title	+ "\",";
		data += " \"url\": \""	+ this.url		+ "\",";
		data += " \"tva\": \""	+ this.tva		+ "\",";
		data += " \"price\": \""+ this.price	+ "\"";
		
		data += "}";
		
		return data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
/*
	public Long getCart_item_id() {
		return cart_item_id;
	}

	public void setCart_item_id(Long cart_item_id) {
		this.cart_item_id = cart_item_id;
	}
*/
	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTva() {
		return tva;
	}

	public void setTva(float tva) {
		this.tva = tva;
	}
	
}