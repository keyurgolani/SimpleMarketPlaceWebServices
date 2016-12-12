package utility;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.UserEntity;

public class DDO {
	
	public static boolean insertNewUser(UserEntity user) {
		boolean result = false;
		try {
			PreparedStatement stmt = ConnectionPool.getConnection().prepareStatement(
					"insert into user_account set user_name = ?, f_name = ?, l_name = ?, email = ?, secret = ?, salt = ?, last_login = ?");
			stmt.setString(1, user.getUser_name());
			stmt.setString(2, user.getF_name());
			stmt.setString(3, user.getL_name());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getSecret());
			stmt.setString(6, user.getSalt());
			stmt.setDate(7, user.getLast_login());
			result = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean checkoutUserCartTransaction(int user_id) {
		return false;
	}

	public static boolean removeItemFromCart(int user_id, int item_id) {
		return true;
	}

	public static boolean addItemToCart(int user_id, int item_id) {
		return false;
	}

	public static boolean addUserAddress(int user_id, String st_address, String apt, String city, String state,
			String zip) {
		return false;
	}

	public static boolean publishSale(int seller, int item, int condition, float sale_price, String title,
			String description, boolean is_bid, int sale_qty, boolean active) {
		return false;
	}

}
