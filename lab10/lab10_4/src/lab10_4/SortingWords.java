package lab10_4;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class SortingWords {

	public static void main(String[] args) {
		TreeSet<String> treeSet=new TreeSet<String>();
		
		createSet(treeSet);
		
		displaySet(treeSet);
	}
	public static void createSet(TreeSet<String> treeSet) {
		//输入一行字符串
		Scanner input=new Scanner(System.in);
		System.out.println("Please enter a line of text");
		String str=input.nextLine();
		
		//用正则表达式分组,\\W代表非单词字符
		String[] tokens = str.split("[\\W]+");
		
		//将每个分组放到treeSet中
		for(String token:tokens) {
			treeSet.add(token);
		}
	}
	public static void displaySet(TreeSet<String> treeSet) {
		Iterator<String> iterator=treeSet.iterator();
		System.out.println("Sorting result:");
		
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+"\t");
		}
	}
}
