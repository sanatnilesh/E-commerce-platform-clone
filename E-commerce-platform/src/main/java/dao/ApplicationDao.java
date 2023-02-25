package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Order;
import beans.Product;
import beans.User;

public class ApplicationDao {

	public List<Order> getOrderHistory(String userName) {
		Order order = new Order();
		List<Order> orderlist = new ArrayList<Order>();
		Connection connection = DBConnection.getConnectionToDatabase();
		String query = "Select * from orders where user_name like '%" + userName + "%'";
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			while (set.next()) {
				order.setOrderId(set.getInt("order_id"));
				order.setProductName(set.getString("product_name"));
				order.setProductImgPath(set.getString("image_path"));
				order.setOrderDate(set.getDate("order_date"));
				order.setUserName(set.getString("user_name"));
				orderlist.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderlist;
	}

	public boolean authenticateUser(String username, String password) {
		boolean isUserAuthenticated = false;
		Connection connection = DBConnection.getConnectionToDatabase();
		String query = "Select * from users where username like '%" + username + "%' and password like '%" + password
				+ "%'";
		try {
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			while (set.next()) {
				isUserAuthenticated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUserAuthenticated;
	}

	public List<Product> searchProducts(String searchString) {
		List<Product> products = new ArrayList<Product>();
		Connection connection = DBConnection.getConnectionToDatabase();
		String query = "Select * from products where product_name like '%" + searchString + "%'";
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Product product = new Product();
				product.setProductId(resultSet.getInt("product_Id"));
				product.setProductName(resultSet.getString("product_name"));
				product.setProductImgPath(resultSet.getString("image_path"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public int insertUser(User user, Connection connection) {
		int rowsAffected = 0;
		try {
			String inserQuery = "Insert into users values(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(inserQuery);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setInt(5, user.getAge());
			preparedStatement.setString(6, user.getActivity());
			rowsAffected = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	public User getUser(String userName) {
		User user = new User();
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String query = "Select * from users where username like '%" + userName + "%'";
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			while (set.next()) {
				user.setUsername(set.getString("username"));
				user.setFirstName(set.getString("first_name"));
				user.setLastName(set.getString("last_name"));
				user.setAge(set.getInt("age"));
				user.setActivity(set.getString("activity"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
