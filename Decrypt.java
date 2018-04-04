package edu.upenn.cis573.hwk1;

//import java.io.*;
import java.util.*;

public class Decrypt {
	

	private char[] aryText; //aryText used to store encrypted alphabet text
	private char[] decryptedText; //decryptedText used to store decrypted text
	private List<Map.Entry<Character, Integer>> Charlist;//list for storing ordered alphabet table
	private Map<Character,Integer> TextCharFreq = new HashMap<Character, Integer>();
	private Map<Character,Integer> sortedTextCharFreq = new HashMap<Character, Integer>();
	//decryptMapping is a map whose key is encryption alphabet value is frequency model alphabet
	// ordered in frequency ascending 
	private Map<Character, Character> decryptMapping = new HashMap<Character, Character>();


	/**
	 * This class is dealing with decryption process
	 * @param encryText:  alphabet text from encryption process
	 * @param charFreqlist:  alphabet frequency list
	 */
	public Decrypt(char[] encryText, List<Map.Entry<Character, Integer>> charFreqlist){
		
		aryText = encryText;
		decryptedText = new char[encryText.length];
		readFile(encryText);
		decryptFile(charFreqlist);
				
	}
	

	/**
	 * Read file and get frequency of each alphabet, then put them into map with key being each alphabet
	 * and value being its frequency
	 * @param encryText
	 */
	private void readFile(char[] encryText){
		
		for(int i=0; i < aryText.length; i++){
			if(aryText[i] >= 'a' && aryText[i] <= 'z'){
				if(TextCharFreq.containsKey(aryText[i])){
					int count = TextCharFreq.get(aryText[i]);
					TextCharFreq.put(aryText[i], count+1);
				}
				else{
					TextCharFreq.put(aryText[i], 1);
				}
			}
		}
	
		Charlist = new ArrayList<Map.Entry<Character, Integer>>(TextCharFreq.entrySet());
		
		Collections.sort(Charlist, new charFreqComparator());
		
		for(Map.Entry<Character, Integer> mapping : Charlist){
			sortedTextCharFreq.put(mapping.getKey(), mapping.getValue());
		}		
		
	}

	
	/**
	 * Decipher the encrypted text 
	 * @param charFreqlist:  list for alphabet arranged by its ascending frequency
	 */
	private void decryptFile(List<Map.Entry<Character, Integer>> charFreqlist){
		
		List<Character> encryption = new ArrayList<Character>();
		List<Character>  freqModel = new ArrayList<Character>();
		for(Map.Entry<Character, Integer> mapping : Charlist){
			encryption.add(mapping.getKey());
		}
		
		for(Map.Entry<Character, Integer> mapping : charFreqlist){
			freqModel.add(mapping.getKey());
		}
		for(int l=0; l<freqModel.size(); l++){
			decryptMapping.put(encryption.get(l), freqModel.get(l));
		}
		for(int n=0; n<aryText.length; n++){
			decryptedText[n] = decryptMapping.get(aryText[n]);
		}

	}
	
	/**
	 * get method to return the decryptedtext
	 * @return char array
	 */
	public char[] getDecryptedText(){
		return decryptedText;
	}
}
