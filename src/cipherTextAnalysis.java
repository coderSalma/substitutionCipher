//package substitutionCipher;
import java.util.*;
import java.io.*;

public class cipherTextAnalysis
{
	//method creates an array containing the letters of the alphabet
	public static char[] alphabet()
	{
		char[] alphabet = new char[26];
		for(int i = 0; i < 26; i++)
		{
			alphabet[i] = (char) (97 + i);
		}
		
		return alphabet;
	}
	
	//method returns the index of the letter from the character array
	public static int findIndex(char[]alphabet, char letter)
	{
		int length  = alphabet.length;
		int i = 0;
		
		if(letter == 32)
		{
			return -1;
		}
		if( (letter >= 33 && letter <= 64) ) 
		{
			return -1;
		}
		if( (letter >= 91 && letter < 96) )
			
		{
			return -1;
		}
		if( (letter >= 128) )	
		{
			return -1;
		}
			
		while(i < length)
		{
			if(alphabet[i] == letter)
			{
				return i;
			}
			
			else
			{
				i += 1;
			}
		}
		
		return -1;
	}
	
	
	//method stores an array that contains the letter count needed to analyze
	public static int[] letterCount(int[] letterCountArray, char[] alphabet, char letter)
	{
		int index = -1;
		int number;

			index = findIndex(alphabet, letter);
			
			if(index == -1)
			{
				return letterCountArray;
			}
			else
			{
				number = letterCountArray[index];
				letterCountArray[index] = ++number; 
				return letterCountArray;
			}
		
	}
	
	//method calculates the frequency of each letter
	public static double[] calcFrequency(int[] letterCountArray, int total)
	{
		double[] frequencyArray = new double[26];
		
		for(int i = 0; i < letterCountArray.length; i++)
		{
			double occurrence = letterCountArray[i];
			double frequency = occurrence / (double) total;
			frequency = frequency * (double) 100;
			frequencyArray[i] = frequency;
		}
		
		return frequencyArray;
	}
	
	//method rearranges and prints the array in order
	public static void arrangeArray(char[] alphabetArray, double[]frequencyArray)
	{
		for(int i = 0; i < frequencyArray.length - 1; i++)
			{
				if(frequencyArray[i] > frequencyArray[i+1])
				{
					double temp = frequencyArray[i];
					frequencyArray[i] = frequencyArray[i+1];
					frequencyArray[i+1] = temp;
					
					char tempo = alphabetArray[i];
					alphabetArray[i] = alphabetArray[i+1];
					alphabetArray[i+1] = tempo; 
					
					i = -1;
				}
			}
		
		for(int i = 0; i < alphabetArray.length; i++)
		{
			System.out.print(alphabetArray[i] + " : ");
			System.out.printf("%.7f %n",frequencyArray[i]);
		}
	}
	
	
	public static void main(String[] args) 
		throws FileNotFoundException
	{
		char[] alphabetArray = alphabet();
		int [] letterCountArray = new int[26];
				
		Scanner scanFile = new Scanner(new File("cipherTextAnalysis.txt"));
		String currentLine = "";
		int length = 0;
		int index = 0;
		char letter;
		int total = 0;
		
		while(scanFile.hasNextLine())
		{
			currentLine = scanFile.nextLine();
			Scanner scanLine = new Scanner (currentLine);
			
			while(scanLine.hasNext())
			{
				String oneWord = scanLine.next();
				length = oneWord.length();
				
				for(int i = 0; i < length; i++)
					{
						letter = oneWord.charAt(i);
						
						if(findIndex(alphabetArray,letter) != -1 )
						{
							total++;
						}
						
						letterCountArray = letterCount(letterCountArray, alphabetArray, letter);
						
					}
				
			}

		}
		double[] frequencyArray = calcFrequency(letterCountArray,total);
		
		System.out.println();
		System.out.println("Most occuring letter frequencies:");
		arrangeArray(alphabetArray,frequencyArray);
		}
	}

