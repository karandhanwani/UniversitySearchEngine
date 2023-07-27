package final_project;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlToTextConverter {
    /*
     * Description: Conversion of HTML files to text files
     */
	public static String readingFile(String file) throws IOException {
		/*
		 * Description: reading HTML content from the .html files
		 * Arguments: 
		 * 			file(String): name of the file from which data is to be extracted
		 */
		String data = reading("D:\\windsor summer 2023 term 1\\8547 files advanced computing concepts\\project\\CrwaledPages\\" + file, StandardCharsets.US_ASCII);
		return data;
	}
	
	public static String convertHTMLtoText(String htmldata) {
		/*
		 * Description: Converting HTML Content to Text Data.
		 * Arguments:
		 * 			htmldata(String): Data in html form
		 */
	
		// Conversion using Jsoup Library
		Document data = Jsoup.parse( htmldata );
		return data.text();
	}

	
	public static String[] RetrieveFileNames(String folder) {
		/*
		 * Description: Gives all the files present in folder
		 * Arguments: 
		 * 			folder(String): Name of the Folder from which we want to search filenames
		 */

		
		File directoryPath = new File(folder);

		// Retrieving List of Files present in Folder
		// takes only .html files
		File[] fileName = directoryPath.listFiles((dir, name) -> name.toLowerCase().endsWith(".html"));
		String[] fileNames =  new String[fileName.length];
		System.out.println("List of Files:");
		int i=0;
		 for (File f : fileName) {
	            if (f.isFile()) {
	                fileNames[i] = (f.getName());
	                i+=1;
	            }
	        }
		return fileNames;

	}

	//
	public static String reading(String path, Charset encoding) throws IOException {
		/*
		 * Description: Actual Reading of Files on byte-level
		 * Arguemnts: 
		 * 			path(String): path where the file is present
		 * 			encoding(Charset): checking which encoding is used.
		 */
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	// write array content into the given file
	public static void writing(String fileName, String content) throws IOException {
		/*
		 * Description: Writing content in the text file
		 * Arguments:
		 * 			fileName(String): Name of the text-file
		 * 			content(String): Text Content to be written in text file
		 */

		FileWriter wrt = new FileWriter("./Text/" + fileName.substring(0, fileName.lastIndexOf(".")) + ".txt");
		wrt.write(content);
		wrt.close();
	}

	public static void Html2text() throws IOException {
		/*
		 * Description: Main function to convert HTML files to TXT files
		 */
		
		// Getting all the available files in HTML folder
		String FileList[] = RetrieveFileNames("HTML");

		
		for (int i = 0; i < FileList.length; i++) {
			String file = FileList[i];

			System.out.println("Reading file: " + file);

			// Getting Content from the File
			String ContentOfFile = readingFile(file);

			// cleaning the content of the file
			System.out.println("Conversion in progress...");
			String simpleText = convertHTMLtoText(ContentOfFile);

			// Storing the clean content into the file
			System.out.println("Storing in progress...");
			writing(file, simpleText);
		}
	}
}
