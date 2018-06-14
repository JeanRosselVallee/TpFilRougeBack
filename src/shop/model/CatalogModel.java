package shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import shop.model.bean.Product;

public class CatalogModel {
	
	private static CatalogModel instance = null;
	
	public static CatalogModel getInstance() {
		if( CatalogModel.instance == null ) {
			CatalogModel.instance = new CatalogModel();
		}
		
		return CatalogModel.instance;
	}

	
	/***********************************************/
	/***********************************************/
	/***********************************************/
	private boolean connected;
	private Connection bdd;
	private SessionFactory fact;
	
	private CatalogModel(){
		
		super();
		
		// on n'est pas connecté par défaut
		this.connected 	= false;
		
		// on va chercher un fabricant de "registre hibernate" ( un objet de config )
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		
		// le fabricant va utilier le fichier hibernate.cfg.xml pour construire
		// un registre.
		StandardServiceRegistry reg = builder.configure().build();
		
		// maintenant on va donner le registre à la SessionFactory
		SessionFactory fact = new MetadataSources(reg)
									.buildMetadata()
									.buildSessionFactory();
		
		this.fact = fact;
		this.connected 	= true;
		
		
	}
	
	public boolean isConnected() {
		return this.connected;
	}
	/***********************************************/
	/***********************************************/
	/**
	 * @throws SQLException *********************************************/
	
	public ArrayList<Product> getCatalog(){
		
		
		//on crée un tableau de produits vide
		ArrayList<Product> results = new ArrayList<Product>();
		
		Session bdd = this.fact.openSession();
		bdd.beginTransaction();
		
		results = (ArrayList<Product>) bdd.createQuery("from Product").list();
		
		bdd.getTransaction().commit();
		
		bdd.close();
		
		return results;
	}
	
	public void add(String p_title, String p_url, float p_price, float p_tva) {
		
		
		Session bdd = this.fact.openSession();
		bdd.beginTransaction();
		bdd.save( new Product(p_title, p_url, p_price, p_tva) );
		bdd.getTransaction().commit();
		bdd.close();
		
	}
	
	public boolean removeProductById(Long p_id ) {                  
		Session bdd = this.fact.openSession();         
		bdd.beginTransaction();         
		bdd.remove( bdd.get(Product.class, p_id) );         
		bdd.getTransaction().commit();         
		bdd.close();                  
		return true;     
	}
	
	

}
