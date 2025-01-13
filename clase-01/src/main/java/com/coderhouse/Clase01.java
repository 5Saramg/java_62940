package com.coderhouse;

public class Clase01 {

	public static void main(String[] args) {
		//Declaración de variables
		//int varI = 12;
		//double varD = 12333.90d;
		//char primChar ='2';
		
		float numeroA = 1.5f;
		float numeroB = 2.6f;
		
		float resultado = numeroA / numeroB;
		if(numeroB != 0 && numeroA != 0){
			System.out.println("Resutlado: " + resultado);
		}
		else{
			System.err.println("Uno de los número no es válido");
		}
		
		double random1 = 0.0d;
		random1 = Math.random();
		System.out.println("Número random: "+ random1);
		
		//Random con rango
		int random = 0;
		int minimo = 1;
		int maximo = 100;
		int rango = maximo - minimo +1;
		
		random = (int) (Math.random() * rango) + minimo;
		System.out.println("Número random con rango: "+ random);
	}

}
