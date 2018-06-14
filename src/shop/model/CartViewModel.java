package shop.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import shop.model.bean.Cart;
import shop.model.bean.CartView;

public class CartViewModel {
	

	private static CartViewModel instance = null;
	
	
		public static CartViewModel getInstance() {
			System.out.println(" Cart View Model Get Instance ");
			if( CartViewModel.instance == null ) {
				CartViewModel.instance = new CartViewModel();
			}
			
			return CartViewModel.instance;
		}

		
		/***********************************************/
		/***********************************************/
		/***********************************************/
		private boolean connected;
		private Connection bdd;
		private SessionFactory fact;
		
		private CartViewModel(){
			super();
			
			System.out.println(" Cart View Model Constructor ");
			
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
			System.out.println(" Cart View Model Is Connected ");
			return this.connected;
		}
		/***********************************************/
		/***********************************************/
		/**
		 * @throws SQLException *********************************************/
		
		public ArrayList<CartView> getCart(){
			
			System.out.println(" Cart View Model get ");
			//on crée un tableau de produits vide
			ArrayList<CartView> results = new ArrayList<CartView>();
			
			Session bdd = this.fact.openSession();
			bdd.beginTransaction();
			
			results = (ArrayList<CartView>) bdd.createQuery("from CartView").list();
			
			bdd.getTransaction().commit();
			
			bdd.close();
			
			System.out.println(" Cart Model get " + results);
			
			return results;
		}

		
	}
