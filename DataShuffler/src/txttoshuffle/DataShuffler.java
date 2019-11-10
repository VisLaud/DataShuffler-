package txttoshuffle;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class DataShuffler {
	
	public String inputPath;
	public String[] newData;
	
	//calculates the number of lines on the file
	public int numberOfLines() throws IOException{
		try {
			
			FileReader file = new FileReader(inputPath);
			BufferedReader br = new BufferedReader(file);
			int numberOfLines =0;
			String line;
			while((line = br.readLine())!=null){
				numberOfLines++;
			}
			br.close();
			file.close();
			return numberOfLines;
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File not found in this location");
		}
	}
	//this takes the txt file and converts it into an String[]
	public String[] txtToArray(String inputPath) throws IOException{
		this.inputPath= inputPath;
		FileReader file = new FileReader(inputPath);
		BufferedReader br = new BufferedReader(file);
		int numberOfLines = numberOfLines()-1;
		String[] data = new String[numberOfLines];
		br.readLine();
		for(int i=0;i<numberOfLines;i++) {
			data[i]= br.readLine();
		}
		br.close();
		file.close();
		return data;
	}
	//this takes the list of numbers and shuffles it and puts it into new String[]
	public void shuffeler(String[] data) throws IOException{
		Random random = new Random();
		for(int i=numberOfLines()-2;i>0;i--) {
			int next = random.nextInt(i);
			String temp = data[i];
			data[i]= data[next];
			data[next]= temp;
			
		}	
		newData = data;
	}
	//this takes the shuffled list and converts it into a txt file
	public void outputFile(String outputPath) throws IOException{
		try {
			FileOutputStream outPut = new FileOutputStream(outputPath);
			PrintWriter outputWriter = new PrintWriter(outPut);
			for(String b: newData) {
				outputWriter.println(b);
			}
			outputWriter.close();
			
			
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File not found in this location");
		}
	}
	public static void main(String[] args) throws IOException {
		DataShuffler test = new DataShuffler();
		// path to your input file
		String[] a = test.txtToArray("C:\\Users\\ximan\\Desktop\\ErdosCA.txt");//takes the data and puts in a files
		test.shuffeler(a); //Shuffles the data in the list
		test.outputFile("C:\\Users\\ximan\\Desktop\\ThapaSimantaShuffled.txt");//location where you want your file to be outputed
	}
	
}
