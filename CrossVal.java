package edu.upenn.cis573.hwk1;

//import java.util.*;

public class CrossVal {
	
	private int rightNum;// int variable for calculating the number of correct deciphered character
	private int wrongNum;// int variable for calculating the number of incorrect deciphered character
	private String name;// storing the given filename 
	
	
	/**
	 * CrossVal class is doing cross validation
	 * @param e:  one instance from the Encrypt class  
	 * @param d:  one instance from the Decrypt class
	 * @param filename: a string of the specific filename
	 */
	public CrossVal(Encrypt e, Decrypt d, String filename){
		name = filename;
		char[] plainText = e.getPlainText();
		char[] Decipher = d.getDecryptedText();
		comp(plainText, Decipher);
		System.out.println(output());
	}
		
	
	/**
	 * compare the two input texts and calculate correct and incorrect number for accuracy
	 * @param plainText: plain text char array
	 * @param Decipher: decrypted text char array
	 */
	private void comp(char[] plainText, char[] Decipher){
		
		rightNum = 0;
		wrongNum = 0;
		for(int i=0; i<plainText.length; i++){
				if(plainText[i] == Decipher[i]){
					rightNum ++;
				}
				else{
					wrongNum++;
				}		
		}	
	}
	
	
	/**
	 * return the number of successfully deciphering
	 * @return
	 */
	public int getRightNum(){
		return rightNum;
	}
	
	
	/**
	 * return the number of failure of deciphering
	 * @return
	 */
	public int getWrongNum(){
		return wrongNum;
	}
	
	
	/**
	 * output in command-linethe UI as requirement
	 * @return
	 */
	public String output(){
		return name + ": " + rightNum + " correct, " + wrongNum + " incorrect";
	}

}
