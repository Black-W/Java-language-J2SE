package lab9_1;

import java.util.ArrayList;
import java.util.Iterator;

public class outputWords {

	public static void main(String[] args) {
		String news=new String("PITTSBURGH (AP) — Carnegie Mellon University will hire a researcher from the Library of Congress to help it decode a collection that includes two WWII German Enigma machines.\r\n" + 
				"The university wants to encourage the study of 19th and 20th century computers, calculators, encryption machines and other materials related to the history of computer science.\r\n" + 
				"“When we look back and we see this, we see who we remember,” Andrew Moore, dean of CMU’s School of Computer Science, said, adding his students are increasingly asking for courses about the history of the field. “We see people who took technology to save lives and save the world.”\r\n" + 
				"Pamela McCorduck, a prolific author on the history and future of artificial intelligence and the widow of Joseph Traub, a renowned computer scientist and the former head of CMU’s Computer Science Department, permanently loaned to the university a collection of early computers, books and letters. The collection, anchored by a three-rotor and four-rotor Enigma machine, is on display in the Fine and Rare Book Room in CMU’s Hunt Library in Oakland. The gift makes CMU one of a few institutions in the United States with Enigma machines. Even fewer display them.\r\n" + 
				"");
		//用正则表达式分组
		String []tokens=news.split("[“”()—.,\\s]+");
		//用LIST过滤出现多次的单词
		ArrayList<String> words=new ArrayList<String>();
		for(String token:tokens) {
			if(words.contains(token)) {	
				continue;
			}
			words.add(token);
		}
		//用迭代器输出
		Iterator<String> iterator=words.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
