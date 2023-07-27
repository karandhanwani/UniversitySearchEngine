package final_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class pageRank {
	Hashtable<String, Integer> t = new Hashtable<String, Integer>();// new one

	public static  Hashtable<String, Integer> WordCount() throws IOException
	{
		Hashtable<String, Integer> hashTable = new Hashtable<String, Integer>();// new one


		BufferedReader br = null; 
		try 
		{
			Scanner sc= new Scanner(System.in);
			File myFolder = new File("D:\\windsor summer 2023 term 1\\8547 files advanced computing concepts\\project\\crawled_output\\"); //reading the converted text files
			File[] files = myFolder.listFiles();
			System.out.println("Enter a word to search: "); 
			String userInput= sc.nextLine();                 //reading user input


			for (File file : files) 
			{
				HashMap <String,Integer> hm = new HashMap<String,Integer>(); //hashmap object to store the words
				br =  new BufferedReader(new FileReader(file)); 
				String str = br.readLine();  					// reading the content lines in the files

				while(str!=null)
				{
					String words[]= str.toLowerCase().split(" ");   // getting the words
					for(String word:words) {
						if(hm.containsKey(word)) {       //search for the word in hashmap
							hm.put(word,hm.get(word)+1);   

						}
						else {
							hm.put(word,1);	 
						}

					}
					str=br.readLine();
				}

				if(hm.containsKey(userInput)) 
				{	 
					//System.out.println(userInput + " repeated " +hm.get(userInput)+" times in "+ file.getName()); 
					hashTable.put( file.getName(), hm.get(userInput));
				}
				else
				{
					//System.out.println("Word not found in "+ file.getName());
					hashTable.put( file.getName(), 0); 
				}
			}

		}catch(Exception e) {

			e.printStackTrace();

		}
		finally {
			br.close();

		}

		return hashTable;
	}
	
	public static void sortbyRank()                   // sorts the web pages and give ranks
	{
		try
		{
			Hashtable<String, Integer>t = WordCount();

			ArrayList<Map.Entry<?, Integer>> l = new ArrayList(t.entrySet());

			Collections.sort(l, new Comparator<Map.Entry<?, Integer>>()
			{

				public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2)
				{
					return o1.getValue().compareTo(o2.getValue());
				}});
			Collections.reverse(l);
			//System.out.println(l);

			System.out.println("The Top 5 search results");               // Prints top 5 results 
			for(int i = 0; i < 5; i++) {
				Map.Entry<String, Integer> e = (Entry<String, Integer>) l.get(i);
				System.out.println("The word appeared " + e.getValue() + " times in the file " + e.getKey());
			}

		}
		catch(Exception e) {

			e.printStackTrace();

		}

		
	}

	public static void main(String[] args)
	{
		
	}

}
