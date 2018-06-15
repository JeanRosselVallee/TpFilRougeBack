# TpFilRougeBack
Java JEE Course

Scope: Server-side. Back-End.  

Goal: Complete an e-Shop application by implementing a Cart with 3 features 
- Add a product from the catalog to the cart
- Remove a product from the cart
- Display the details of all the products in the cart

Context: Before this implementaton, the existing application only offers a Catalog with similar features.

Solution's choice:
The creation of a view in the database in order to obtain a detailed list of the elements in the cart obeys to the MVC principle. This solution was preferred to the implementation of this feature in the Front-End. Because a view of the jointure of tables Product and Cart in the Data Model follows the MVC principle where the organisation of data should be kept separate from the View in the Fron-End. This preferred MVC choice will ensure the persistance and the consistency of the data which is taken into charge by the database. While the discarded choice of data being handled by the Front-End or View keeps the data in a volatile state with the risk of data inconsistency and loss. 

Solution: 
- MVC pattern 
- send results to the View (Front-Side) via Json files
    - file CartView.jsp
- the services offered by the Controller are coded in Java
    - file CartController
- access the Model (database) via Hibernate  
    - files CartModel.java, Cart.java and Cart.hbm.xml for the table cart
    - files CartViewModel.java, CartView.java and ViewCartProducts.hbm.xml for the view cartView

Data Model:
- A new table "cart" contains the references of the products in the cart. 
- The details of the products are available in the table "products".  
- A new view contains the whole set of data ready to be directly diplayed as a cart by the client.


Database Creation SQL Commands
use database pyrates

1.1 Catalog Table Creation

CREATE TABLE products ( id bigint(20) NOT NULL, title varchar(255) DEFAULT NULL, url varchar(255) DEFAULT NULL, price float DEFAULT NULL, tva float DEFAULT NULL ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE products ADD PRIMARY KEY (id);

ALTER TABLE products MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

1.2 Cart's Items Table Creation

CREATE TABLE cart ( id bigint(20) NOT NULL, cart_id int(11) DEFAULT NULL, product_id int(11) DEFAULT NULL ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

1.3 Cart's Detailed Items View Creation

CREATE TABLE cart_view ( cart_item_id bigint(20) ,product_id bigint(20) ,title varchar(255) ,url varchar(255) ,price float ,tva float );

1.4 Catalog Records INSERT INTO products ( url, title, price, tva ) VALUES ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_01.jpg", "product_01", 101, 1), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_02.jpg", "product_02", 202, 2), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_03.jpg", "product_03", 303, 3), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_04.jpg", "product_04", 404, 4), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_05.jpg", "product_05", 505, 5), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_06.jpg", "product_06", 606, 6), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_07.jpg", "product_07", 707, 7), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_08.jpg", "product_08", 808, 8), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_09.jpg", "product_09", 909, 9), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_10.jpg", "product_10", 1010, 10), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_11.jpg", "product_11", 1111, 11), ("http://www.grassfedassociation.com/wp-content/uploads/2013/06/product_12.jpg", "product_12", 1212, 12)

Local root directory: C:\Users\ib\eclipse-workspace\TP_FilRouge\shop
