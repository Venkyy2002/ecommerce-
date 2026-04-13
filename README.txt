REQUIREMENTS:
- Java JDK 17+
- Apache Tomcat 10+
- MySQL Server 9.x
- Any modern browser

Steps to run :-
1. Open MySQL and run:  mysql -u root -p < database.sql
2. Open DBConnection.java and update your MySQL password
3. Drop ecommerce.war into Tomcat/webapps/ folder
4. Start Tomcat
5. Open browser → http://localhost:8080/ecommerce/signin.html

FEATURES:
1. Register with email — OTP sent for verification
2. Password hashed and saved securely in database
3. Login generates JWT token for authenticated sessions
4. Browse, filter and search products
5. Add products to cart, adjust quantity, remove items
6. Place order — saved to database under your account
7. View your own order history only