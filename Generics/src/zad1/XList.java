package zad1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class XList <T> extends ArrayList{
	
	public XList() {
	}
	
	
	public XList(Collection collection) {
		super(collection);
	}
	
	public XList(Object... things) {
		Collections.addAll(this, things);
	}
	
	
	public static <T>XList<T> of (Collection<T> collection) {
		return new XList<>(collection);
	}
	
	public static <T>XList<T> of (Object... elements) {
		return new XList<>(elements);
	}
	
	public static <T>XList<T> charsOf (String string) {
		
		ArrayList<Character> arrayList = new ArrayList<>();
		int stringLength = string.length();
		
		for(int i = 0; i < stringLength; i++) {
			arrayList.add(string.charAt(i));
		}
		return new XList(arrayList);
		
	}
	
	
	public static <T>XList<T> tokensOf(String string) {
		ArrayList<String> arrayList = new ArrayList<>();
		char tempChar;
		String finalString = "";
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) != ' ') {
				tempChar = string.charAt(i);
				finalString = finalString + tempChar;
			} else {
				if(!(finalString.equals(""))){
				arrayList.add(finalString);
				}
				finalString = "";
			}
			if (i == string.length() - 1 && string.charAt(i) != ' ') {
				arrayList.add(finalString);
			}
		}
		return new XList<>(arrayList);
	}
	
	
	public static <T>XList<T> tokensOf(String string, String sep) {
		String[] splitWord = string.split(sep);
		ArrayList<String> arrayList = new ArrayList<>();
		for(String s : splitWord) {
			arrayList.add(s);
		}
		return new XList(arrayList);
	}
	
	public <T>XList<T> union(Collection<T> collection) {
		
		ArrayList arrayList = new XList<>(collection);
		ArrayList arrayList2 = this;
		
		arrayList.addAll(arrayList2);
		
		return new XList<>(arrayList);	
	}
	
	
	public <T>XList<T> union(T[] arr) {
			
			ArrayList arrayList = new XList<>(arr);
			ArrayList arrayList2 = this;
			
			arrayList.addAll(arrayList2);
			
			return new XList<>(arrayList);	
	}
	
	
	
	
	public <T>XList<T> diff(Collection<T> collection) {
		
		ArrayList arrayList = new ArrayList<>();
		
		int size = this.size();
		int colsize = collection.size();
		
		for(Object thing : this) {
			if(!(collection.contains(thing))) {
				arrayList.add(thing);
			}
		}	
		return new XList<>(arrayList);	
	}
	
	
	public <T>XList<T> unique() {
		
		LinkedHashSet<T> hs = new LinkedHashSet();
        hs.addAll(this);
        
        return new XList<>(hs);
        
	}
	
	
	public <T>XList<T> collect(Function function){
		ArrayList arrayList = new ArrayList<>();
		for(Object t : this) {
			function.apply(t);
			arrayList.add(t);
		}
		
		return new XList<>(arrayList);
	}
	
	public String join() {
		return this.join();
	}
	
	public String join(String sep) {
		return this.join(sep);
	}
	
}
