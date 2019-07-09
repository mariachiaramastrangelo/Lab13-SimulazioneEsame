package it.polito.tdp.food.db;

import java.util.List;

public class TestDao {

	public static void main(String[] args) {
		FoodDao dao = new FoodDao();
		
		//System.out.println(dao.listAllFood());
		List<Condiment> result= dao.ingredientiPerCalorie(100);
		System.out.println(result);
		dao.aggiungiListaCibi();
		for(Condiment c: result ) {
			System.out.print(c.getFood_codes()+"\n");
		}

	}

}
