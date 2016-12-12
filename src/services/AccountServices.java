package services;

import java.sql.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.json.simple.JSONObject;

import entities.UserEntity;
import utility.BCrypt;
import utility.ConnectionPool;
import utility.DAO;
import utility.DDO;

@WebService(name = "AccountServies", targetNamespace = "http://localhost:8080/WebServices/services")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class AccountServices {

	public AccountServices() {
		ConnectionPool.initializePool(10);
	}

	@WebMethod
	public boolean authenticate(String username, String password) {
		UserEntity user = DAO.getUserFromUserName(username);
		if (BCrypt.hashpw(password, user.getSalt()).equals(user.getSecret())) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@WebMethod
	public String fetchUser(String username) {
		JSONObject userJSON = new JSONObject();
		UserEntity user = DAO.getUserFromUserName(username);
		userJSON.put("user_id", user.getUser_id());
		userJSON.put("user_name", user.getUser_name());
		userJSON.put("f_name", user.getF_name());
		userJSON.put("l_name", user.getL_name());
		userJSON.put("email", user.getEmail());
		userJSON.put("last_login", user.getLast_login().toString());
		return userJSON.toJSONString();
	}

	@WebMethod
	public boolean addUser(String username, String f_name, String l_name, String email, String secret,
			Date last_login) {
		String salt = BCrypt.gensalt(10);
		return DDO.insertNewUser(
				new UserEntity(username, f_name, l_name, email, BCrypt.hashpw(secret, salt), salt, last_login));
	}
	
	@WebMethod
	public boolean updateLastLogin(int user_id) {
		return DAO.updateLastLogin(user_id);
	}
	
	@WebMethod
	public boolean checkout(int user_id) {
		return DDO.checkoutUserCartTransaction(user_id);
	}
	
	@WebMethod
	public boolean sendCartAvailability(int user_id) {
		return DAO.checkCartItemsAvailability(user_id);
	}
	
	@WebMethod
	public boolean removeFromCart(int user_id, int item_id) {
		return DDO.removeItemFromCart(user_id, item_id);
	}
	
	@WebMethod
	public boolean sendUserNotifications(int user_id) {
		return DAO.fetchUserNotifications(user_id);
	}
	
	@WebMethod
	public String sendUserCartItems(int user_id) {
		return DAO.fetchUserCartItems(user_id).toJSONString();
	}
	
	@WebMethod
	public String sendAddresses(int user_id) {
		return DAO.fetchUserAddresses(user_id).toJSONString();
	}
	
	@WebMethod
	public String sendUserSearchResults(String searchString) {
		return DAO.searchItems(searchString).toJSONString();
	}
	
	@WebMethod
	public String sendSearchResults(String searchString, int user_id) {
		return DAO.searchItems(searchString, user_id).toJSONString();
	}
	
	@WebMethod
	public String sendUserSuggestions(int user_id) {
		return DAO.fetchUserSuggestions(user_id).toJSONString();
	}
	
	@WebMethod
	public String sendBidDetails(int item_id) {
		return DAO.fetchItemBids(item_id).toJSONString();
	}
	
	@WebMethod
	public String sendItemDetails(int item_id) {
		return DAO.fetchItemDetails(item_id).toJSONString();
	}
	
	@WebMethod
	public boolean addItemToCart(int user_id, int item_id) {
		return DDO.addItemToCart(user_id, item_id);
	}
	
	@WebMethod
	public boolean addUserAddress(int user_id, String st_address, String apt, String city, String state, String zip) {
		return DDO.addUserAddress(user_id, st_address, apt, city, state, zip);
	}
	
	@WebMethod
	public String sendConditions() {
		return DAO.fetchItemConditions().toJSONString();
	}
	
	@WebMethod
	public boolean publishSale(int seller, int item, int condition, float sale_price, String title, String description, boolean is_bid, int sale_qty, boolean active) {
		return DDO.publishSale(seller, item, condition, sale_price, title, description, is_bid, sale_qty, active);
	}

}
