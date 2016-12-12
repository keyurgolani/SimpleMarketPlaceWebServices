package utility;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import entities.UserEntity;

public class DAO {

	public static UserEntity getUserFromID(int user_id) {
		UserEntity user = null;
		try {
			PreparedStatement stmt = ConnectionPool.getConnection().prepareStatement(
					"select user_id, user_name, f_name, l_name, email, secret, salt, last_login from user_account where user_id = ?");
			stmt.setInt(1, user_id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDate(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static UserEntity getUserFromUserName(String user_name) {
		UserEntity user = null;
		try {
			PreparedStatement stmt = ConnectionPool.getConnection().prepareStatement(
					"select user_id, user_name, f_name, l_name, email, secret, salt, last_login from user_account where user_name = ?");
			stmt.setString(1, user_name);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDate(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean updateLastLogin(int user_id) {
		int rowsAffected = 0;
		try {
			PreparedStatement stmt = ConnectionPool.getConnection().prepareStatement(
					"update user_account set last_login = ? where user_id = ?");
			stmt.setDate(1, new Date(new java.util.Date().getTime()));
			stmt.setInt(2, user_id);
			rowsAffected = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected == 1 ? true : false;
	}

	public static boolean checkCartItemsAvailability(int user_id) {
		return true;
	}

	public static boolean fetchUserNotifications(int user_id) {
		return false;
	}

	public static JSONObject fetchUserCartItems(int user_id) {
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}

	public static JSONObject fetchUserAddresses(int user_id) {
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}

	public static JSONObject searchItems(String searchString) {
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}

	public static JSONObject searchItems(String searchString, int user_id) {
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}

	public static JSONObject fetchUserSuggestions(int user_id) {
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}

	public static JSONObject fetchItemBids(int item_id) {
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}

	public static JSONObject fetchItemDetails(int item_id) {
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}

	public static JSONObject fetchItemConditions() {
		JSONObject jsonObject = new JSONObject();
		return jsonObject;
	}

}
