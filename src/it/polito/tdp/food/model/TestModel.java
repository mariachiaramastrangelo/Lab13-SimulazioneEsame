package it.polito.tdp.food.model;

public class TestModel {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model= new Model();
		model.creaGrafo(5);
		
		System.out.println(model.grafoCreato());
	}
}
