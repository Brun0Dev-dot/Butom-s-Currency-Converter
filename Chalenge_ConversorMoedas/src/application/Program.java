package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import entities.Converter;

public class Program {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		int choice = 13;
		
		while(choice != 0) {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("******************************************************");
			System.out.println("Welcome to Butom's Currency Converter");
			System.out.println("1) Dollar ==> Peso Argentino");
			System.out.println("2) Dollar ==> Real Brasileiro");
			System.out.println("3) Dollar ==> Euro");
			System.out.println("4) Peso Argentino ==> Dollar");
			System.out.println("5) Peso Argentino ==> Real Brasileiro");
			System.out.println("6) Peso Argentino ==> Euro");
			System.out.println("7) Real Brasileiro ==> Dollar");
			System.out.println("8) Real Brasileiro ==> Peso Argentino");
			System.out.println("9) Real Brasileiro ==> Euro");
			System.out.println("10) Euro ==> Dollar");
			System.out.println("11) Euro ==> Peso Argentino");
			System.out.println("12) Euro ==> Real Brasileiro");
			System.out.println("0) Quit");
			System.out.println("Choose a valid option:");
			System.out.println("******************************************************");
			
			choice = sc.nextInt();
			
			String moeda1 = "";
			String moeda2 = "";
			
			if (choice >=1 & choice <= 3) {
					moeda1 = "USD";
			} else if (choice > 3 && choice <= 6) {
					moeda1 = "ARS";
			} else if (choice > 6 && choice <= 9) {
					moeda1 = "BRL";
			}else if (choice > 9 && choice <= 12) {
					moeda1 = "EUR";
			} else if (choice == 0) {
					break;
			}
			
			if (choice == 1 || choice == 8 || choice == 11) {
				moeda2 = "ARS";
			} else if (choice == 2 || choice == 5 || choice == 12) {
				moeda2 = "BRL";
			} else if (choice == 3 || choice == 6 || choice  == 9) {
				moeda2 = "EUR";
			} else if (choice == 4 || choice == 7 || choice == 10) {
				moeda2 = "USD";
			}
			
			String adress = "https://v6.exchangerate-api.com/v6/0b85800d820c69290f94b25a/latest/" + moeda1;
			System.out.println("Enter the amount you want to convert:");
            double valor = sc.nextDouble();
            Converter conv = new Converter(valor, moeda1, moeda2);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(adress)).build();
			
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String json = response.body();
			
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
			JsonObject ratesObject = jsonObject.getAsJsonObject("conversion_rates");
			Type mapType = new TypeToken<Map<String, Double>>(){}.getType();
			Map<String, Double> conversionRates = gson.fromJson(ratesObject, mapType);
			
			conv.setValor2(conversionRates.get(moeda2));
			
			System.out.println(conv);
		}
		System.out.println("Thank you so much for using Butom Currency Converter!");
	}
}

