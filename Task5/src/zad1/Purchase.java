/**
 *
 *  @author Dzik Szymon S24020
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.Arrays;

public class Purchase{
	
	String id;
	String nazwisko;
	String imie;
	String nazwa_towaru;
	float cena;
	float zakupiona_ilosc;
	float total_cost;
	float koszty;
	
	
	public Purchase(String info) {
		String[] arr = info.split(";");
		this.id = arr[0];
		this.nazwa_towaru = arr[2];
		this.cena = Float.valueOf(arr[3]);
		this.zakupiona_ilosc = Float.valueOf(arr[4]);
		
		String[] arr2 = arr[1].split(" ");
		this.nazwisko = arr2[0];
		this.imie = arr2[1];
		this.koszty = this.cena * this.zakupiona_ilosc;
	}
	
	
	public String toString() {
		String a = this.id + ";" +
				this.nazwisko + " " + this.imie + ";" +
				this.nazwa_towaru + ";" +
				Float.toString(this.cena) + ";" +
				Float.toString(this.zakupiona_ilosc);
		
		return a;
	}
	
}
