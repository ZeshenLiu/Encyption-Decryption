package edu.upenn.cis573.hwk1;

import java.io.*;
//import java.util.*;

public class Encrypt {
	
	private int shift = 13; // Set for substitution shift   private 
	private char[] encryptedText;// char array to store encrypted pure alphabet text  private 
	private char[] plainText;// char array to store plain pure alphabet text
	private char[] Text;// char array to store all characters in original plain text
	private int charNum = 0;// Variable for calculating the number of letters from a to z 
	
	
	/**
	 * This class is responsible for enciphering the specific text
	 * @param path the file absolute location
	 */
	public Encrypt(String path){
		
		readFile(path);
		
		countCharNum();
	
		encipherText();

	}
	
	/**
	 * Read the file in specific path and encipher it with the shift
	 * @param path the file absolute location
	 */
	private void readFile(String path){ //private
		
		try{
			/**
			 *  Read text file and export the content as a long char array
			 */
			FileReader fr = new FileReader(path);////may generate FileNotFoundException
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine(); //may generate IOException
			String newLine = "";
			while (line != null) {
				newLine = newLine + line;
				line = br.readLine();
			}
			newLine = newLine.toLowerCase();
			Text = newLine.toCharArray();			
			fr.close();
		}catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	 
	 /**
	  * Count total alphabet number in text
	  */
	private void countCharNum(){
		 
			for(int i=0; i < Text.length; i++){
				if(Text[i] >= 'a' && Text[i] <= 'z'){
					charNum++;
				}
			}
	 }
	 
	 
	/**
	* Encipher the plain alphabet text
	*/
	private void encipherText(){
		 
		plainText = new char[charNum];
		encryptedText = new char[charNum];
		int index = 0;
		for(int i=0; i < Text.length; i++){
			if(Text[i] >= 'a' && Text[i] <= 'z'){
				plainText[index] = Text[i];
				encryptedText[index] = (char)((Text[i] + shift - 'a' + 26) % 26 + 'a');
				index++;
			}
		}
	 }

	 
	/**
	 * 
	 * @return the encrypted char array from the text
	 */
	public char[] getEncryptedText(){
		return encryptedText;
	}
	
	/**
	 * 
	 * @return the original alphabet char array for cross validation use
	 */
	public char[] getPlainText(){
		return plainText;
	}

}
