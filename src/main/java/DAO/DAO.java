package DAO;

import Entity.Account;
import Entity.Cart;
import Entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Context.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

//	public static List<Product> cart = new ArrayList<>();

	public static Map<Product, Integer> productAndAmount = new HashMap<>();

	public List<Product> getAllProduct() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product";
		try {
			new DBConnect();
			conn = DBConnect.getConn();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public Account login(String user, String pass) {
		String query = "SELECT * FROM account WHERE user = ? AND pass = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConn();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace(); // In lỗi để kiểm tra và xử lý sau
		}
		return null;
	}

	public void signup(String user, String pass) {
		String query = "INSERT INTO Account (user,pass,isSell,isAdmin) VALUES (?,?,0,0)";
		try {
			new DBConnect();
			conn = DBConnect.getConn();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public Account checkAccountExist(String user) {
		String query = "select * from account where user = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConn();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace(); // In lỗi để kiểm tra và xử lý sau
		}
		return null;
	}

	public static void main(String[] args) {
		DAO dao = new DAO();
		List<Product> list = dao.getAllProduct();
		for (Product o : list) {
			System.out.println(o);
		}
	}

	public Product getProductByID(String id) {
		String query = "select * from Product where id = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConn();
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public List<Product> searchByName(String txtSearch) {
		List<Product> list = new ArrayList<>();
		String query = "select * from product where title like ?";
		try {
			new DBConnect();
			conn = DBConnect.getConn();
			ps = conn.prepareStatement(query);
			ps.setString(1, '%' + txtSearch + '%');
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public List<Product> getProductBySellID(int id) {
		List<Product> list = new ArrayList<>();
		String query = "select * from product where sell_ID = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public boolean isAdmin(String user, String pass) {
		String query = "SELECT isAdmin FROM account WHERE user = ? AND pass = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConn();
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 1;
			}
		} catch (Exception e) {
			e.printStackTrace(); // In lỗi để kiểm tra và xử lý sau
		}
		return false;
	}

	public List<Cart> getCartByAccountId(int accountId) {
		List<Cart> cart = new ArrayList<>();
		String query = "SELECT * FROM Cart WHERE accountId = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int productId = rs.getInt("productId");
				int amount = rs.getInt("amount");
				cart.add(new Cart(accountId, productId, amount));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cart;
	}

	public static void addCart(Product p) {
		for (Product product : productAndAmount.keySet()) {
			System.out.println("\nLOL" + product.getId());
			if (product.getId() == p.getId()) {
				Integer oldAmount = productAndAmount.get(product);
				Integer newAmount = Integer.valueOf(oldAmount.intValue()+1);
				productAndAmount.put(product, newAmount);
				System.out.println("ID"+ product.getId() + oldAmount + "-" + newAmount);
				return;
			}
		}
		productAndAmount.put(p, Integer.valueOf(1));
		
	}

	public static void deleteCart(int productId) {
//		for (Product product : cart) {
//			if (product.getId() == productId) {
//				cart.remove(product);
//				idAndAmount.remove(Integer.valueOf(productId));
//				return;
//			}
//		}
		DAO dao = new DAO();
		Product p = dao.getProductByID(String.valueOf(productId));
		for (Product product : productAndAmount.keySet()) {
			if (product.getId() == p.getId()) {
				productAndAmount.remove(product);
				return;
			}
		}
	}

}
