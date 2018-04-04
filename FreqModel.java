package edu.upenn.cis573.hwk1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FreqModel {
	
	private char[] text;//To store text in the char array
	private Map<Character,Integer> charFreq = new HashMap<Character,Integer>();//Map for knowing char frequency
	private Map<Character,Integer> sortedCharFreq= new HashMap<Character,Integer>();//Sorted map with value in ascending order
	private List<Map.Entry<Character, Integer>> charFreqlist;// List for alphabets by their frequency in ascending order
	
	/**
	 * Set frequency model utilizing remaining files, generate one alphabet list whose order is according
	 * to frequency of corresponding alphabet in ascending order
	 * @param path:  the file absolute location
	 * @param fileNum:  the specific file name of the encrypted file
	 * @param i:  the order of the specific file in given file folder
	 */
	public FreqModel(String path, int fileNum, int i){
		File folder = new File(path);
		File[] files = folder.listFiles();
		for(int j=0; j<fileNum; j++){
			if(j != i){
				String filepath = path + "//" + files[j].getName(); 
				readFile(filepath);
				setCharFreq();
			}
		}
		sortfreqModel(charFreq);

	}
	
	/**
	 * Readfile method will read each character in specific file and put the alphabet and its frequency
	 * into the charFreq map as key and value
	 * @param path
	 */
	private void readFile(String path){
		try{
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine(); 
			String newLine = "";
			while (line != null) {		
				newLine = newLine + line;
				line = br.readLine();
			}
			newLine = newLine.toLowerCase();
			text = newLine.toCharArray();
				
			fr.close();
		}catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	/**
	 * set char frequency map
	 */
	private void setCharFreq(){
		for(int k=0; k < text.length; k++){
			if(text[k] >= 'a' && text[k] <= 'z'){
				if(charFreq.containsKey(text[k])){
					int count = charFreq.get(text[k]);
					charFreq.put(text[k], count+1);
				}
				else{
					charFreq.put(text[k], 1);
				}
			}
		}		
	}
	
	
	/**
	 * Sort the frequency model to get ordered alphabet list and put them into a map in 
	 * frequency ascending order
	 * @param charFreq
	 */
	private void sortfreqModel(Map<Character, Integer> charFreq){ //private
		
		charFreqlist = new ArrayList<Map.Entry<Character, Integer>>(charFreq.entrySet());
		
		Collections.sort(charFreqlist, new charFreqComparator());
		
		for(Map.Entry<Character, Integer> mapping : charFreqlist){
			sortedCharFreq.put(mapping.getKey(), mapping.getValue());
		}	
		
	}

	/**
	 * getFreqModel will return the ordered frequency model as a list
	 * @return the list arranged by alphabets' frequency is the corresponding frequency model
	 */
	public List<Map.Entry<Character, Integer>> getFreqModel() {
		return charFreqlist;
	}
	
	

}
