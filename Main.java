package edu.upenn.cis573.hwk1;

//import java.util.*;
import java.io.*;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * Dealing with different error-generating conditions
		 */
		if(args.length == 0){
			System.out.println("The number of runtime arguments is not correct!");
			System.exit(0);
		}
		String path = args[0];
		File folder = new File(path);
		if(!folder.exists() || !folder.isDirectory()){
			System.out.println("The specified directory does not exit!");
			System.exit(0);
		}
		else if(!folder.canRead()){
			System.out.println("The specified directory cannot be opened for reading!");
			System.exit(0);
		}
		else if(folder.listFiles().length == 0){
			System.out.println("The specified directory is empty!");
			System.exit(0);
		}
		
		/**
		 * Do four main processes for each file
		 */
		File[] files = folder.listFiles();
		int fileNum = files.length;
		
		float totRightNum = 0, totWrongNum = 0;
		for (int i = 0; i < fileNum; i++) {
			if (!files[i].isFile()) {
				System.exit(0);
			}
			String newPath = path;
			newPath = newPath + "\\" + files[i].getName();
			Encrypt e = new Encrypt(newPath);
			FreqModel f = new FreqModel(path, fileNum, i);
			Decrypt d = new Decrypt(e.getEncryptedText(), f.getFreqModel());
			CrossVal c = new CrossVal(e, d, files[i].getName());
			totRightNum = totRightNum + c.getRightNum();
			totWrongNum = totWrongNum + c.getWrongNum();
						
		}
		
		System.out.println();
		System.out.println("Total: " + (int)totRightNum + " correct, " + (int)totWrongNum + " incorrect");
		double accuracy = Math.round(10000 * (totRightNum / (totRightNum + totWrongNum)))/100.0;
		System.out.println("Accuracy: " + accuracy + "%");

	}

}
