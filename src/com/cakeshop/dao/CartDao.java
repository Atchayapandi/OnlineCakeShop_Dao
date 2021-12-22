package com.cakeshop.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.cakeshop.model.Cart;
import com.cakeshop.model.Products;
import com.cakeshop.model.User;

public class CartDao {

	public void insertCart(Cart cart) {
		

		String insert = "INSERT INTO CART_ITEMS (CAKE_ID,USER_ID,ORDER_QUANTITY,TOTAL_PRICE) VALUES(?,?,?,?) ";

		ConnectionUtil conUtil = new ConnectionUtil();
		Connection con = conUtil.getDbConnection();
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(insert);
			pst.setInt(1, cart.getProductId());
			pst.setInt(2, cart.getUserId());
			pst.setInt(3, cart.getQuantity());
			pst.setDouble(4, cart.getTotalPrice());
			pst.executeUpdate();
			System.out.println("Value inserted Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Value not inserted in the table");
		}

	}

//view cart items

	public List<Cart> viewCart(User currentUser) {
		
		
		List<Cart> userCartList = new ArrayList<Cart>();
		String query = "select * from cart_items";
		Connection con = ConnectionUtil.getDbConnection();	
		ProductDao productDao = new ProductDao();		
		
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {				
				
			    userCartList.add(new Cart(rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5)));
		   
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userCartList;
	}

	// update cart
	public static void updateCart(String updateCart) throws ClassNotFoundException, SQLException {
		String updateQuery = "update cart_items set quantity =? where cart_id=?";

		Connection con = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = con.prepareStatement(updateQuery);
		pstmt.setInt(1, Integer.parseInt(updateCart.split(",")[0]));
		pstmt.setInt(2, Integer.parseInt(updateCart.split(",")[1]));
		int i = pstmt.executeUpdate();
		System.out.println(i + "row updated");
		pstmt.close();
		con.close();
	}

	// delete cart

	public static void deleteCart(String delete) throws ClassNotFoundException, SQLException {
		String deleteQuery = "delete from cart_items where cart_id=?";

		Connection con = ConnectionUtil.getDbConnection();
		PreparedStatement pstmt = con.prepareStatement(deleteQuery);
		pstmt.setInt(1, Integer.parseInt(delete));
		int i = pstmt.executeUpdate();
		System.out.println(i + "row deleted");
		pstmt.close();
		con.close();
	}

// find cart id		

	public static int findCartId(Cart cart) {
		String query = "select cart_id from product_details where cake_id=?";

		Connection con = ConnectionUtil.getDbConnection();
		int cartId = 0;
		try {
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, cartId);
			ResultSet rs = pre.executeQuery(query);
			if (rs.next()) {
				cartId = rs.getInt(1);
			}
			System.out.println(cartId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cartId;

	}

//find cart details		

	public static Cart findCart(int cartId) {
		String query = "select * from cart_items where cart_id=?";

		Connection con = ConnectionUtil.getDbConnection();
		Cart cart = null;
		try {
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, cartId);

			ResultSet rs = pre.executeQuery(query);

			if (rs.next()) {
				// cart=new Cart(rs.getString(2), rs.getString(3),
				// Double.parseDouble(rs.getString(4)), rs.getString(5));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cart;

	}

//	public List<Cart> viewUserCart(String Product) {
//		List<Cart> productsList = new ArrayList<Cart>();
//
//		String query = "select * from cart_items";
//		
//		int userId = UserDao.findUserId(Product);
//		List<Cart> userCartList = new ArrayList<Cart>();
//
//		Connection con = ConnectionUtil.getDbConnection();
//		try {
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while (rs.next()) {
//				Cart cart = new Cart(Product,);
//				
//				
//				Cart cart1=userCartList.add(new Products(rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5)));
//				
//				
//			}			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return productsList;

}