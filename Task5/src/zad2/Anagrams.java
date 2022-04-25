/**
 *
 *  @author Dzik Szymon S24020
 *
 */

package zad2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import zad1.Purchase;

public class Anagrams {
	
	ArrayList<String> wordsList;
	
	public Anagrams(String path) {
		ArrayList<String> arrayList = new ArrayList<>();
		File file = new File(path);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				List<String> arr = Arrays.asList(scanner.nextLine().split(" "));
				arrayList.addAll(arr);
			}
		this.wordsList = arrayList;	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public static boolean anagramsOrNot(String s1, String s2) {
		
		if(s1.length() != s2.length()) {
			return false;
		} 
			
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		for(int i = 0; i < arr1.length; i++) {
			if(arr1[i] != arr2[i]) {
				return false;
			}
		} 
		return true;
	}
	
	
	public List<ArrayList<String>> getSortedByAnQty() {
		ArrayList<ArrayList<String>> listOfLists = new ArrayList<>();
		ArrayList<String> alreadyAdded = new ArrayList<>();
		ArrayList<ArrayList<String>> endList = new ArrayList<>();
		
		for(String s : this.wordsList) {
			if(!(alreadyAdded.contains(s))) {
			ArrayList<String> arrayList = new ArrayList<>();
			
			for(int i = 0; i < this.wordsList.size(); i++) {
				if(anagramsOrNot(s, this.wordsList.get(i)) == true) {
					arrayList.add(this.wordsList.get(i));
					alreadyAdded.add(this.wordsList.get(i));
					if(!(arrayList.contains(s))) {
						arrayList.add(s);
						alreadyAdded.add(this.wordsList.get(i));
					}
				}
			}
			if(!(arrayList.isEmpty())) {
				listOfLists.add(arrayList);
			}
		  }
		}
		ArrayList<Integer> sizes = new ArrayList<>();
		for(ArrayList<String> ar: listOfLists) {
			sizes.add(ar.size());
		}
		Collections.sort(sizes);
		Collections.reverse(sizes);
		for(Integer i : sizes) {
			for(ArrayList<String> arr : listOfLists) {
				if(arr.size() == i) {
					endList.add(arr);
				}
			}
		}
		return endList;
	} 
	
	
	public String getAnagramsFor(String word) {
		if(!(this.wordsList.contains(word))) {
			return word + "null";
		}
		ArrayList<String> arrayList = new ArrayList<>();
		for(String string : this.wordsList) {
			if(anagramsOrNot(string, word)) {
				if(!(string.equals(word))) {
				arrayList.add(string);
				}
			}
		}
		return word + ": " + arrayList;
	}
	
}  

