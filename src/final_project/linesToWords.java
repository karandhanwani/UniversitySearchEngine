package final_project;

//Searching a word in all the converted text files using Edit Distance
//Splits all  words from the generated all text files
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class linesToWords {

	public static  void splitWords() throws FileNotFoundException, IOException 
	{

		ArrayList<String> result = new ArrayList<>();

		String filePath=  "D:\\windsor summer 2023 term 1\\8547 files advanced computing concepts\\project\\Output\\output.txt";
		try (FileReader f = new FileReader(filePath)) {
		    StringBuffer sb = new StringBuffer();
		    while (f.ready()) {
		        char c = (char) f.read();
		        if (c == ' '||c == '\n'||c == ','||c =='.'||c == ';'||c == ':'||c == '&'||c =='|') {
		            result.add(sb.toString());
		            sb = new StringBuffer();
		        } else {
		            sb.append(c);
		        }
		    }
		    if (sb.length() > 0) {
		        result.add(sb.toString());
		    }
		}       
		
		FileWriter writer = new FileWriter("D:\\windsor summer 2023 term 1\\8547 files advanced computing concepts\\project\\Output\\output.txt"); 
		for(String str: result) {
		  writer.write(str + System.lineSeparator());
		}
		writer.close();
	}

}

