# TpFilRougeBack
Java JEE Course

Scope: Server-side. Back-End.  

Goal: Complete an e-Shop application by implementing a Cart with 3 features 
- Add a product from the catalog to the cart
- Remove a product from the cart
- Display the details of all the products in the cart

Context: Before this implementaton, the existing application only offers a Catalog with similar features.

Solution: 
- MVC pattern 
- send results to the View (Front-Side) via Json files
- access the Model (database) via Hibernate  
- the services offered by the Controller are coded in Java

Data Model:
- A new table "cart" contains the references of the products in the cart. 
- The details of the products are available in the table "products".  
- A new view contains the whole set of data ready to be directly diplayed as a cart by the client.


