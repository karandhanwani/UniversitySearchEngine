package final_project;

//Calculates frequency of particular word in all web pages  and  give ranking the pages on the basis of occurances
import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;


public class frequncyCount { 

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

	public static void Frequency_Count() 
	{

		Hashtable<String, Integer> ht;
		try {
			ht = frequncyCount.WordCount();

			/*for (int i = 0; i< ht.size(); i++) {
				System.out.println(ht);
			}*/

			ArrayList<Map.Entry<?, Integer>> l = new ArrayList(ht.entrySet());

			for(int i = 0; i < ht.size(); i++) {
				Map.Entry<String, Integer> e = (Entry<String, Integer>) l.get(i);

				if(e.getValue()!=0)
					System.out.println("The word appeared " + e.getValue() + " times in the file " + e.getKey());
			}



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args)   throws IOException
	{
	}

}
