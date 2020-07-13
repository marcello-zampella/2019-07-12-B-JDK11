package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.food.modell.CollegamentoCibi;
import it.polito.tdp.food.modell.Condiment;
import it.polito.tdp.food.modell.Food;
import it.polito.tdp.food.modell.Portion;

public class FoodDao {
	public List<Food> listAllFoods(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_code"),
							res.getString("display_name")
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
	
	public List<Condiment> listAllCondiments(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"),
							res.getDouble("condiment_calories"), 
							res.getDouble("condiment_saturated_fats")
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
	
	public List<Portion> listAllPortions(){
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Portion> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"),
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"), 
							res.getDouble("calories"),
							res.getDouble("saturated_fats"),
							res.getInt("food_code")
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
	
	public List<Food> numberPortion(int porzioni){
		String sql = "SELECT distinct(f.food_code) AS codice, f.display_name AS nome " + 
				"FROM portion p, food f " + 
				"WHERE p.portion_default>=? AND p.food_code=f.food_code " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, porzioni);
			
			List<Food> list = new ArrayList<Food>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("codice"), res.getString("nome")));
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

	public ArrayList<CollegamentoCibi>getPorzioneCibo() {
		String sql = "SELECT p.food_code AS cibo1 ,p2.food_code AS cibo2 ,(p.saturated_fats-p2.saturated_fats) AS diff " + 
				"FROM portion p, portion p2 " + 
				"WHERE p.food_code>p2.food_code " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
						
			ArrayList<CollegamentoCibi> list = new ArrayList<CollegamentoCibi>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new CollegamentoCibi(new Food(res.getInt("cibo1"),null), new Food(res.getInt("cibo2"),null), res.getDouble("diff")));
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
}
