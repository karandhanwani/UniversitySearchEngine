package final_project;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class SearchEngine {

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException{

		//Web crawling 
		
		String links="https://www.universitystudy.ca/canadian-universities/";
		boolean keepSearching = true;
		webCrawler web=new webCrawler();
//		web.crawl(links);  		
//		htmltotext.Html2text();
		//Conversion finished from html to text

		// User Interaction start 
		Scanner userInput = new Scanner(System.in); 
		while(keepSearching == true)
        {
            System.out.println("------------------------------------------------");
            System.out.println("Please enter the feature to be searched: "
                    + "\n 1. Spell Checker using edit distance"
                    + "\n 2. Inverted Index"
                    + "\n 3. Frequency Count"
                    + "\n 4. Sort Pages using Rank");
            System.out.println("------------------------------------------------");

            int feature = userInput.nextInt();  // Read user input

            switch (feature)
            {
                case 1:
                    mergeTextFiles.mergeFiles();
                    linesToWords.splitWords();
                    editDistance.run();
                    break;

                case 2:
                    InvertedIndex ii = new InvertedIndex();
                    ii.InvertedIndexCreate();
                    break;

                case 3:
                    frequncyCount.Frequency_Count();
                    break;

                case 4:
                    pageRank.sortbyRank();
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }

            System.out.println("Do you want to search more ? Yes or No");
            Scanner continueSearch = new Scanner(System.in);
            String name = continueSearch.nextLine();
            switch (name.toLowerCase())
            {
                case "yes":
                    keepSearching = true;
                    break;

                case "no":
                    keepSearching = false;
                    break;
                    
                default:
                    keepSearching = false;
                    System.out.println("Invalid choice your search is ended");
                    break;
            }
        }
	}
}
