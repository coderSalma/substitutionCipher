//package substitutionCipher;
 import java.util.*;
import java.io.*;

public class substitutioncipher 
{
	//creates plain text list and prints values
		public static char[] plainText()
		{
			char[] plainText = new char[26]; 
			for(int i = 0; i < 26; i++)
			{
				plainText[i] = (char) (97 + i);
			}
				
			System.out.print("plainText = { ");
				
			for(int i = 0; i < 26; i++)
			{
				System.out.print(plainText[i] + ", ");
			}
				
			System.out.print("}");
			System.out.println();
				
			return plainText;
			}

			
	//method creates a random shuffled array
		public static Integer[] shuffleArray()
		{
			
			Integer[] indexArray = new Integer[26];
			for(int i = 0; i < 26; i++)
			{
				indexArray[i] = 97 +  i;
			}
			
			List <Integer> indexList = Arrays.asList(indexArray);
			Collections.shuffle(indexList);
			indexList.toArray(indexArray);
		
			return indexArray;
		}
		
		//method creates encrypted array
		public static char[]encryptedText(Integer[] indexArray)
		{
			char[] encryptedText = new char[26];
			
			for(int i = 0; i < 26; i++)
			{
				int index = indexArray[i];
				encryptedText[i] = (char) index;
			}
			
			System.out.print("encrypted = { ");
			for(int i = 0; i < 26; i++)
			{
				System.out.print(encryptedText[i] + ", ");
			}
			System.out.println("}");
			
			return encryptedText;
		}
		//finds the index of the plain character to pair it 
		public static int findIndex(char[]plainText, char current)
		{
			int length  = plainText.length;
			int i = 0;
				
			while(i < length)
			{
				if(plainText[i] == current)
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
		
		//finds index of the encrypted char
		public static int findDecIndex(char[]encryptedText, char current)
		{
			int length  = encryptedText.length;
			int i = 0;
			
			while(i < length)
			{
				if(encryptedText[i] == current)
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

		
		//method returns encrypted letter 
		public static char encryptLetter(char letter, char[]plainText, char[]encryptedText)
		{
			//checks to see if letter is a special character and returns space if yes
			
			if(letter == 32)
			{
				return ' ';
			}
			if( (letter >= 33 && letter <= 64) ) 
			{
				return letter;
			}
			if( (letter >= 91 && letter < 96) )
				
			{
				return letter;
			}
			if( (letter >= 128) )	
			{
				return letter;
			}
			
			
			
			else if (letter >=97 && letter <= 122)
				{
					int index = findIndex(plainText, letter);
					char encrypted = encryptedText[index];
					return encrypted;
				}
			
			else
			{
				return ' ';
			}
								
		}
		
		//method returns decrypted letter
		public static char decryptLetter(char letter, char[]plainText, char[]encryptedText)
		{
			//checks to see if letter is a special character and returns space if yes
			if(letter == 32)
			{
				return ' ';
			}
			if( (letter >= 33 && letter <= 64) ) 
			{
				return letter;
			}
			if( (letter >= 91 && letter < 96) )
				
			{
				return letter;
			}
			if( (letter >= 128) )	
			{
				return letter;
			}
			
			else if (letter >=97 && letter <= 122)
			{
				int index = findDecIndex(encryptedText, letter);
				char decrypted = plainText[index];
				return decrypted;
			}
			
			else
			{
				return ' ';
			}
			
		}
		
		//method encrypts entire string using previous decrypt char method
		public static String decryptedLine(String input,char[]plainText, char[]encryptedText)
		{
			String decrypted = "";
			input = input.toLowerCase();
			for(int i = 0; i < input.length(); i++)
			{
				char current = input.charAt(i);
				char decrypt = decryptLetter(current, plainText, encryptedText);
				decrypted += decrypt;
			}
					
			return decrypted;
		}
		
		//method encrypts entire string using previous encrypt char method
		public static String encryptedLine(String input,char[]plainText, char[]encryptedText)
		{
			String encrypted = "";
			input = input.toLowerCase();
			for(int i = 0; i < input.length(); i++)
			{
				char current = input.charAt(i);
				char encrypt = encryptLetter(current, plainText, encryptedText);
				encrypted += encrypt;
			}
			
			return encrypted;
		}
		
		//method returns boolean according to user's input
		public static boolean question(String input)
		{
			while(!(input.equals("-1")))
			{
				return true;
			}
			
			return false;
		}
		
		//recursive method prints encrypted versions of users input until boolean is false
		public static void repeatEncryption(Scanner scnr, char[] plainArray, char[] encryptedArray)
		{
			String input = scnr.nextLine();
			
			if(question(input))
			{
				System.out.println();
				System.out.println(encryptedLine(input, plainArray, encryptedArray));
				System.out.println();
				System.out.println("Enter a string to encrypt (-1 to quit)");
				repeatEncryption(scnr, plainArray, encryptedArray);
			}
			
			else
			{
				System.out.print("Success!");
			}
			
		}
		
		//recursive method prints decrypted versions of users input until boolean is false
		public static void repeatDecryption(Scanner scnr, char[] plainArray, char[] encryptedArray)
		{
			String input = scnr.nextLine();
			
			if(question(input))
			{
				System.out.println();
				System.out.println(decryptedLine(input, plainArray, encryptedArray));
				System.out.println();
				System.out.println("Enter the encrypted text to decrypt (-1 to quit)");
				repeatDecryption(scnr, plainArray, encryptedArray);
			}
					
			else
			{
				System.out.print("Success!");
			}
					
		}
		
		public static void main(String[] args) 
		throws FileNotFoundException, IOException
		{

		Scanner scnr = new Scanner(System.in);
		char[] plainArray = plainText();
		Integer[] indexArray = shuffleArray();
		char[] encryptedArray = encryptedText(indexArray);
	
		
		System.out.println();
		//System.out.println(encryptedLine("i eat potatoes for lunch", plainArray, encryptedArray));
		
		//prompts user to enter a string to be encrypted
		System.out.println("Enter a string to encrypt (-1 to quit)");
		System.out.println();
		repeatEncryption(scnr, plainArray, encryptedArray);
		
		System.out.println();
		//prompts user to enter what will be decrypted
		System.out.println("Enter the encrypted text to decrypt (-1 to quit)");
		System.out.println();
		repeatDecryption(scnr, plainArray, encryptedArray);
		System.out.println();
		System.out.println();
		
		//scans file w/ large plain text and encrypts + prints each line for passive attacker step
		
		System.out.println("Would you like to encrypt the file for the Cipher Text Analysis Portion?");
		System.out.println("Warning large file might take long to run");
		
		System.out.println();
		System.out.println("Please make sure the cipherTextAnalysis.txt file is empty before running for most accurate results!");
		System.out.println("If you would like to proceed, type y");
		
		String answer = scnr.next();
		answer.toLowerCase();
		if (answer.charAt(0) == 'y')
		{
			System.out.println("File Encryption");
		 	Scanner scanFile = new Scanner(new File("PlainText.txt"));
		 	PrintWriter writer = new PrintWriter("cipherTextAnalysis.txt");
		 	String currentLine = "";
		 	
			while(scanFile.hasNextLine())
			{
				currentLine = scanFile.nextLine();
				String encryptedVersion = encryptedLine(currentLine, plainArray, encryptedArray);
				writer.println(encryptedVersion + " ");
				
			}
			
			writer.close();
		}
		
		System.out.print(" Complete, check out the cipher results in the cipherTextAnalysis.txt file");
	
		}
}
