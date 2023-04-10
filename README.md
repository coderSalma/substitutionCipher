# substitutionCipher

This project was created for my Data Security and Cryptography course and is an implementation of one of the first ever cryptography ciphers, the substitution cipher also known as "Caesar's cipher".
It implements three main algorithms including the generation of the key, encryption and decryption.
In this case, the key will be randomly generated by the program every time it is run and only the sender and the reciever will have a copy of the key.

The substitutionCipher.java file is the sender/reciever view of the text, as it includes the funamental encrypting and decrypting methods.
The cipherTextAnalysis.java file is the malicious user's view, aka any third party attempting to intercept the information, and uses the concept of letter frequency cryptoanalysis to attempt to decrypt the messages.

To obtain accurate letter frequency results, you need a large body of text with approximately hundreds of thousands of words to feed into the encryption algorithm so i obtained my corpora of text from Project Gutenberg and gathered them all into a file called plainText.txt which is what will be used to run the letter frequency portion of the progam.

Note:
Make sure the cipherTextAnalysis.TXT file is EMPTY before running the file encryption portion to ensure accurate letter frequency results
