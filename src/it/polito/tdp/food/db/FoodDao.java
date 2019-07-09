package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FoodDao {
	
	private List<Condiment> ingredienti;
	
	
	public List<Food> listAllFood(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_id"),
							res.getInt("food_code"),
							res.getString("display_name"), 
							res.getInt("portion_default"), 
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"),
							res.getDouble("calories")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Condiment> listAllCondiment(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("food_code"),
							res.getInt("condiment_id"),
							res.getString("display_name"), 
							res.getString("condiment_portion_size"), 
							res.getDouble("condiment_calories")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			
			e.printStackTrace();
			return null ;
		}

	}
	public List<Condiment> ingredientiPerCalorie(double calorie){
		String sql="SELECT  DISTINCT(c.`food_code`), c.`condiment_id`, c.`display_name` , c.`condiment_portion_size`, c.`condiment_calories` " + 
				"FROM `condiment` AS c " + 
				"WHERE c.`condiment_calories`<=? " + 
				"ORDER BY c.`food_code` ";
		ingredienti= new ArrayList<Condiment>();
		
		try {
			Connection conn=  DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setDouble(1, calorie);
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				Condiment c= new Condiment(rs.getInt("food_code"), rs.getInt("condiment_id"), rs.getString("display_name"), rs.getString("condiment_portion_size"), rs.getDouble("condiment_calories"));
				ingredienti.add(c);
			}
			conn.close();
			return ingredienti;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}
	}
	public void aggiungiListaCibi() {
		String sql="SELECT  DISTINCT(fc.`food_code`) " + 
				"FROM `food_condiment` fc " + 
				"WHERE fc.`condiment_food_code`=? && fc.`food_code`!=? ";
		try {
			Connection conn= DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			for(Condiment c: this.ingredienti) {
				st.setInt(1, c.getFood_code());
				st.setInt(2, c.getFood_code());
				ResultSet rs= st.executeQuery();
				while (rs.next()) {
					c.addFoodCode(rs.getInt("food_code"));
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
}
