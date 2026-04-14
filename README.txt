REQUIREMENTS:
- Java JDK 17+
- Apache Tomcat 10+
- MySQL Server 9.x
- Any modern browser

Steps to run :-
1. Clone the repository:
   git clone https://github.com/Venkyy2002/ecommerce-.git
2. Open MySQL and run:
   mysql -u root -p < database.sql
3. Open DBConnection.java and update your MySQL password
4. Import project into Eclipse as existing Java project
5. Build and export as WAR file
6. Drop WAR file into Tomcat/webapps/ folder
7. Start Tomcat
8. Open browser → http://localhost:8080/ecommerce/signin.html

FEATURES:
1. Register with email — OTP sent for verification
2. Password hashed and saved securely in database
3. Login generates JWT token for authenticated sessions
4. Browse, filter and search products
5. Add products to cart, adjust quantity, remove items
6. Place order — saved to database under your account
7. View your own order history 
