package lab10_3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class CountingDuplicateWords {

	public static void main(String[] args) {
		// HashMap保存单词和出现次数
		Map<String, Integer> myMap = new HashMap<String, Integer>();

		// 创建Map
		createMap(myMap);

		// 输出Map
		displayMap(myMap);
	}

	public static void createMap(Map<String, Integer> map) {
		String news = new String(
				"PITTSBURGH (AP) — Carnegie Mellon University will hire a researcher from the Library of Congress to help it decode a collection that includes two WWII German Enigma machines.\r\n"
						+ "The university wants to encourage the study of 19th and 20th century computers, calculators, encryption machines and other materials related to the history of computer science.\r\n"
						+ "“When we look back and we see this, we see who we remember,” Andrew Moore, dean of CMU’s School of Computer Science, said, adding his students are increasingly asking for courses about the history of the field. “We see people who took technology to save lives and save the world.”\r\n"
						+ "Pamela McCorduck, a prolific author on the history and future of artificial intelligence and the widow of Joseph Traub, a renowned computer scientist and the former head of CMU’s Computer Science Department, permanently loaned to the university a collection of early computers, books and letters. The collection, anchored by a three-rotor and four-rotor Enigma machine, is on display in the Fine and Rare Book Room in CMU’s Hunt Library in Oakland. The gift makes CMU one of a few institutions in the United States with Enigma machines. Even fewer display them.\r\n"
						+ "");

		// 用正则表达式分组
		String[] tokens = news.split("[“”()—.,\\s]+");

		// 统计单词个数
		for (String token : tokens) {
			String word = token.toLowerCase();// 不区分大小写，所以统一改为小写
			if (map.containsKey(word)) {
				int count = map.get(word);
				map.put(word, count + 1);
			} else {
				map.put(word, 1);
			}
		}
	}

	public static void displayMap(Map<String, Integer> map) {
		//用TreeSet排序
		TreeSet<String> keys=new TreeSet<String>(map.keySet());
		
		//用迭代器输出
		Iterator<String> iterator=keys.iterator();
		System.out.println("count\tword");
		while(iterator.hasNext()) {
			String key=iterator.next();
			System.out.println(map.get(key)+"\t"+key);
		}
		
	}
}
