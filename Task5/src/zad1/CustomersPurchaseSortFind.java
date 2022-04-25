/**
 *
 *  @author Dzik Szymon S24020
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomersPurchaseSortFind {
	
	ArrayList<Purchase> customers;
	
	int test;
	
	public void readFile(String path) {
		ArrayList<Purchase> arrayList = new ArrayList<>();
		File file = new File(path);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				arrayList.add(new Purchase(scanner.nextLine()));
			}
		this.customers = arrayList;	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	} 
	
	
	public void showSortedBy(String sortBy) {
		if (sortBy.equals("Nazwiska")) {
			
			Comparator<Purchase> compareByNazwisko = Comparator.comparing(p -> p.nazwisko);
			 
			Comparator<Purchase> compareById = Comparator.comparing(p -> p.id);
			
			Comparator<Purchase> compareByFull = compareByNazwisko.thenComparing(compareById);
			
			ArrayList<Purchase> sorted = (ArrayList<Purchase>) customers.stream().
					sorted(compareByFull).
					collect(Collectors.toList());
			
			this.customers = sorted;
			System.out.println(sortBy);
			for(Purchase purchase : this.customers) {
				System.out.println(purchase);
			}
			System.out.println();
		}
		
		if(sortBy.equals("Koszty")) {
			
			Comparator<Purchase> compareByKoszty = Comparator.comparing(p -> p.koszty);
			
			Comparator<Purchase> compareByKoszty2 = compareByKoszty.reversed();
			
			Comparator<Purchase> compareById = Comparator.comparing(p -> p.id);
			
			Comparator<Purchase> compareByFull = compareByKoszty2.thenComparing(compareById);
			
			
			ArrayList<Purchase> sorted = (ArrayList<Purchase>) customers.stream().
					sorted(compareByFull).collect(Collectors.toList());
			
			this.customers = sorted;
			System.out.println(sortBy);
			for(Purchase purchase : this.customers) {
				System.out.println(purchase + " (koszt: " + purchase.koszty + ")");
			}
			System.out.println();
		}
	}
	
	public void showPurchaseFor(String clientId){
		System.out.println("Klient " + clientId);
		for(Purchase p: this.customers) {
			if(p.id.equals(clientId)) {
				System.out.println(p);
			}
		}
		System.out.println();
	}
	
	
}
