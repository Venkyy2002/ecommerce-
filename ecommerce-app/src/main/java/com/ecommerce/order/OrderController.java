package com.ecommerce.order;

import com.ecommerce.cart.CartService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet("/orders")
public class OrderController extends HttpServlet {

	private OrderService service = new OrderService();
	private CartService cartSvc = new CartService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getAttribute("email");
		try {
			List<Order> orders = service.getOrdersByEmail(email);
			resp.setContentType("application/json");
			resp.getWriter().write(new Gson().toJson(orders));
		} catch (Exception e) {
			resp.setStatus(500);
			resp.getWriter().write("[]");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = (String) req.getAttribute("email");

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null)
			sb.append(line);

		Gson gson = new Gson();
		Type listType = new TypeToken<List<OrderItem>>() {
		}.getType();
		List<OrderItem> items = gson.fromJson(sb.toString(), listType);

		Order order = new Order();
		order.setUserEmail(email);
		order.setItems(items);

		String result = service.placeOrder(order);

		if ("ORDER_PLACED".equals(result)) {
			cartSvc.clearCart(email);
		}

		resp.setContentType("text/plain");
		resp.getWriter().write(result);
	}
}
