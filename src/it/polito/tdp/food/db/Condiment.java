package it.polito.tdp.food.db;

import java.util.ArrayList;
import java.util.List;

public class Condiment implements Comparable<Condiment>{
	private Integer food_code;
	private Integer condiment_id;
	private String display_name;
	private String condiment_portion_size;
	private Double condiment_calories;
	
	private List<Integer> food_codes;
	
	public Condiment(Integer food_code, Integer condiment_id, String display_name, String condiment_portion_size,
			Double condiment_calories) {
		super();
		this.food_code = food_code;
		this.condiment_id = condiment_id;
		this.display_name = display_name;
		this.condiment_portion_size = condiment_portion_size;
		this.condiment_calories = condiment_calories;
		food_codes= new ArrayList<Integer>();
	}

//	public Condiment(Integer condiment_id, Integer food_code, String display_name, String condiment_portion_size,
//			Double condiment_calories) {
//		super();
//		this.condiment_id = condiment_id;
//		this.food_code = food_code;
//		this.display_name = display_name;
//		this.condiment_portion_size = condiment_portion_size;
//		this.condiment_calories = condiment_calories;
//	}
	
	public Integer getCondiment_id() {
		return condiment_id;
	}
	public void setCondiment_id(Integer condiment_id) {
		this.condiment_id = condiment_id;
	}
	public Integer getFood_code() {
		return food_code;
	}
	public void setFood_code(Integer food_code) {
		this.food_code = food_code;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getCondiment_portion_size() {
		return condiment_portion_size;
	}
	public void setCondiment_portion_size(String condiment_portion_size) {
		this.condiment_portion_size = condiment_portion_size;
	}
	public Double getCondiment_calories() {
		return condiment_calories;
	}
	public void setCondiment_calories(Double condiment_calories) {
		this.condiment_calories = condiment_calories;
	}
	
	

	public List<Integer> getFood_codes() {
		return food_codes;
	}

	public void setFood_codes(List<Integer> food_codes) {
		this.food_codes = food_codes;
	}
	
	public void addFoodCode(Integer codice) {
		this.food_codes.add(codice);
	}

	@Override
	public String toString() {
		return condiment_id + " - "+ display_name + " ("
				+ condiment_calories + " cal) - contenuto in"+ this.food_codes.size()+" cibi";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condiment_id == null) ? 0 : condiment_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condiment other = (Condiment) obj;
		if (condiment_id == null) {
			if (other.condiment_id != null)
				return false;
		} else if (!condiment_id.equals(other.condiment_id))
			return false;
		return true;
	}
	
	public Integer confrontaCibi(Condiment c2) {
		int cibiComuni=0;
		for(Integer i1: this.food_codes) {
			for(Integer i2: c2.getFood_codes()) {
				if(i1.equals(i2)) {
					cibiComuni++;
				}
			}
		}
		return cibiComuni;
	}

	@Override
	public int compareTo(Condiment o) {
		
		return (int)-((this.condiment_calories*1000)-(o.getCondiment_calories()*1000));
	}
	
	
}
