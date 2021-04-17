package Data_Layer;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import Bussiness_Layer.*;


public class File_Writer {

	
	 public void fileWrite(String s, String outFile){
	        try( 
	            FileWriter fw = new FileWriter(outFile, true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            PrintWriter out = new PrintWriter(bw)){
	            out.println(s);
	        }

	        catch (IOException e) {
	            //exception handling left as an exercise for the reader
	            e.printStackTrace();
	        }
	    }
	
	
}



