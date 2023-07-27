package final_project;
// Searching a word in all the converted text files using Edit Distance
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class mergeTextFiles {

	public static void mergeFiles() throws IOException {
		// TODO Auto-generated method stub

		// create instance of directory
        File dir = new File("D:\\windsor summer 2023 term 1\\8547 files advanced computing concepts\\project\\crawled_output\\");
  
        // create object of PrintWriter for output file
        PrintWriter pw = new PrintWriter("D:\\windsor summer 2023 term 1\\8547 files advanced computing concepts\\project\\Output\\output.txt");
  
        // Get list of all the files in form of String Array
        String[] fileNames = dir.list();
  
        // loop for reading the contents of all the files 
        for (String fileName : fileNames) {
          //  System.out.println("Reading from " + fileName);
  
            // create instance of file from Name of 
            // the file stored in string Array
            File f = new File(dir, fileName);
  
            // create object of BufferedReader
            BufferedReader br = new BufferedReader(new FileReader(f));
            //pw.println("Contents of file " + fileName);
  
            // Read from current file
            String line = br.readLine();
            while (line != null) {
  
                // write to the output file
                pw.println(line);
                line = br.readLine();
            }
            pw.flush();
        }
	}
}
