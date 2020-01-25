package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Food;

public class FoodDao {
	
	private Connection connection;
	private final String GET_FOODS_QUERY = "SELECT * FROM food";
	private final String GET_FOOD_BY_ID_QUERY = "SELECT * FROM food WHERE id = ?";
	private final String CREATE_NEW_FOOD_QUERY = "INSERT INTO food(name) VALUES(?)";
	private final String DELETE_FOOD_BY_ID_QUERY = "DELETE FROM food WHERE id = ?";
	private final String UPDATE_FOOD_BY_ID_QUERY = "UPDATE food SET name = ? WHERE id = ?";
	
	public FoodDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Food> getFoods() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_FOODS_QUERY).executeQuery();
		List<Food> foods = new ArrayList<Food>();
		
		while (rs.next() ) {
			foods.add(populateFood(rs.getInt(1), rs.getString(2)));
		}
		
		return foods;
	}
	
	public Food getFoodById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_FOOD_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateFood(rs.getInt(1), rs.getString(2));
	}
	
	public void createNewFood(String foodName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_FOOD_QUERY);
		ps.setString(1, foodName);
		ps.executeUpdate();
	}
	
	public void deleteFoodById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_FOOD_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updateFoodById (String foodName, int foodId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_FOOD_BY_ID_QUERY);
		ps.setString(1, foodName);
		ps.setInt(2, foodId);
		ps.executeUpdate();
	}
	
	private Food populateFood(int id, String name) {
		return new Food(id, name);
	}

}
