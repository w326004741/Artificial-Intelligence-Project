# Using-Simulated-Annealing-to-Break-a-Playfair-Cipher
> Module: Artificial Intelligence / 4th Year      
> Lecturer: Dr John Healy      
> [Project 2018: Using Simulated Annealing to Break a Playfair Cipher](https://github.com/w326004741/Artificial-Intelligence-Project/blob/master/Project-aiAssignment2018.pdf) 

> by - [Weichen Wang](https://w326004741.github.io/)

## Table of Contents:
1. [Overview](#overview)
2. [How to use this repository](#how-to-use-this-repository)
3. [How to Run](#how-to-run)
4. [About Project](#about-project)
5. [What is Playfair Cipher](#what-is-playfair-cipher)
6. [What is Simulated Annealing Algorithm](#what-is-simulated-annealing-algorithm)
7. [References](#references)
## Overview
You are required to use the simulated annealing algorithm to break a Playfair Cipher. Your application should have the following minimal set of features:
- A menu-driven command line UI that enables a cipher-text source to be specified (a file or URL) and an output destination file for decrypted plain-text.
- Decrypt cipher-text with a simulated annealing algorithm that uses a log-probability and n-gram statistics as a heuristic evaluation function. 

[Project Details](https://github.com/w326004741/Artificial-Intelligence-Project/blob/master/Project-aiAssignment2018.pdf)


## How to use this repository:
1. Click `Clone or Download` or Copy to clipboard.
2. Enter your Terminal(for mac) or cmd(for windows), and following below:
```
# Change directory to anywhere just you like put
cd anywhere.....

# Clone this repository
git clone https://github.com/w326004741/Artificial-Intelligence-Project.git
&
cd your folder(Artificial-Intelligence-SA-Cipher-Breaker)

# You can import this project to eclipse to run
or
Run at the terminal: java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker
```

## How to Run:
- Eclipse: **Right Click CipherBreaker.java ---> Run As ---> Java Application**

- Terminal or cmd: **java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker**

## About Project

This Project is **Using Simulated Annealing to Break a Playfair Cipher**🔑🔐🔓. There are 4 class files in the project: **Hash4Gram.java**,  **PlayFairs.java**,  **ShuffleKey.java**,  **CipherBreaker.java**.

- **Hash4Gram.java**: This file is used to store the contents of the 4-grams file. Key is gram, value is count. Create a static variable named total, used to calculate the total count of the entire file.

- **ShuffleKey.java**: This file uses the shuffle algorithm to obtain the initial key and split it into a 4-grams array.
Traverse the cryptogram 4-gram, if it exists, take count and calculate the logarithmic probability. And includes the simulated annealing algorithm,  the parameters are: temp, transitions, step and encMessgae(Ciphertext)

- **PlayFairs.java**: This file is an implementation of the Playfair Cipher algorithm. The file contains the following steps:
```
1. Process the plaintext, add X in the middle of repeated letters and add X in the end for non-even numbers.
2. Process key, convert J to I, remove duplicate letters
3. Add the letters of the key letters one by one to the 5x5 matrix, and the rest of the space will add unlettered English letters.  Add in the order of a-z. (Treat I and J as the same word)
4. Print Matrix.
5. The plaintext pair is encrypted according to the playfair algorithm.
6. Encrypt plaintext.
7. The ciphertext pair is decrypted according to the playfair algorithm.
8. Decrypt the ciphertext.
```
- **CipherBreaker.java**: Main Class

**Tips.txt**

![image](https://github.com/w326004741/Artificial-Intelligence-Project/blob/master/image/3691523109889_.pic_hd.jpg)

**TheHobbit-Cypher-Text**

![image](https://github.com/w326004741/Artificial-Intelligence-Project/blob/master/image/3701523109939_.pic_hd.jpg)



## What is [Playfair Cipher](https://en.wikipedia.org/wiki/Playfair_cipher):
The Playfair system was invented by Charles Wheatstone, who first described it in 1854.
The Playfair cipher or Playfair square or Wheatstone-Playfair cipher is a manual symmetric encryption technique and was the first literal digram substitution cipher. The scheme was invented in 1854 by Charles Wheatstone, but bears the name of Lord Playfair for promoting its use.

The Playfair cipher uses a 5 by 5 table containing a key word or phrase. 

To generate the key table, one would first fill in the spaces in the table with the letters of the keyword (dropping any duplicate letters), then fill the remaining spaces with the rest of the letters of the alphabet in order (usually omitting "J" or "Q" to reduce the alphabet to fit; other versions put both "I" and "J" in the same space).

To encrypt a message, one would break the message into digrams (groups of 2 letters) such that, for example, "HelloWorld" becomes "HE LL OW OR LD". These digrams will be substituted using the key table. Since encryption requires pairs of letters, messages with an odd number of characters usually append an uncommon letter, such as "X", to complete the final digram. The two letters of the digram are considered opposite corners of a rectangle in the key table. To perform the substitution, apply the following 4 rules, in order, to each pair of letters in the plaintext:
1. If both letters are the same (or only one letter is left), add an "X" after the first letter. Encrypt the new pair and continue. 
2. If the letters appear on the same row of your table, replace them with the letters to their immediate right respectively (wrapping around to the left side of the row if a letter in the original pair was on the right side of the row).
3. If the letters appear on the same column of your table, replace them with the letters immediately below respectively (wrapping around to the top side of the column if a letter in the original pair was on the bottom side of the column).
4. If the letters are not on the same row or column, replace them with the letters on the same row respectively but at the other pair of corners of the rectangle defined by the original pair. The order is important – the first letter of the encrypted pair is the one that lies on the same row as the first letter of the plaintext pair.

### Example
Using "playfair example" as the key (assuming that I and J are interchangeable), the table becomes:

![image](https://github.com/w326004741/Artificial-Intelligence-Project/blob/master/image/3711523112948_.pic.jpg)

Encrypting the message "Hide the gold in the tree stump" (note the null "X" used to separate the repeated "E"s) :

![image](https://github.com/w326004741/Artificial-Intelligence-Project/blob/master/image/3721523113033_.pic.jpg)


![image](https://github.com/w326004741/Artificial-Intelligence-Project/blob/master/image/3781523113860_.pic.jpg)
![image](https://github.com/w326004741/Artificial-Intelligence-Project/blob/master/image/3791523113874_.pic.jpg)


## What is [Simulated Annealing Algorithm](https://en.wikipedia.org/wiki/Simulated_annealing):
Simulated annealing (SA) is an excellent approach for breaking a cipher using a randomly generated key. Unlike conventional Hill Climbing algorithms, that are easily side-tracked by local optima, SA uses randomization to avoid heuristic plateaux and attempt to find a global maxima solution. The following pseudocode shows how simulated annealing can be used break a Playfair Cipher. Note that the initial value of the variables temp and transitions can have a major impact on the success of the SA algorithm. Both variables control the cooling schedule of SA and should be experimented with for best results

![image](https://github.com/w326004741/Artificial-Intelligence-Project/blob/master/image/3801523114485_.pic.jpg)


## References:
- [PlayFair Cipher - Wiki](https://en.wikipedia.org/wiki/Playfair_cipher)

- [Simulated annealing - Wiki](https://en.wikipedia.org/wiki/Simulated_annealing)
