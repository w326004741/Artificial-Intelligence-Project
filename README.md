# Using-Simulated-Annealing-to-Break-a-Playfair-Cipher
> Module: Artificial Intelligence / 4th Year      
> Lecture: Dr John Healy      
> Project 2018: Using Simulated Annealing to Break a Playfair Cipher 

> by - [Weichen Wang](https://w326004741.github.io/)

## Overview
You are required to use the simulated annealing algorithm to break a Playfair Cipher. Your application should have the following minimal set of features:
- A menu-driven command line UI that enables a cipher-text source to be specified (a file or URL) and an output destination file for decrypted plain-text.
- Decrypt cipher-text with a simulated annealing algorithm that uses a log-probability and n-gram statistics as a heuristic evaluation function. 

[Project Details](https://github.com/w326004741/Artificial-Intelligence-Project)


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

## You should be have components:
- [Git](https://git-scm.com/)
- [Java](https://www.java.com/en/)

## How to Run:
- Eclipse: **Right Click CipherBreaker.java ---> Run As ---> Java Application**

- Terminal or cmd: **java -cp ./playfair.jar ie.gmit.sw.ai.CipherBreaker**

## About Project
[Project Document]()

This Project is **Using Simulated Annealing to Break a Playfair Cipher**. There are 4 class files in the project: **Hash4Gram.java**,  **PlayFairs.java**,  **ShuffleKey.java**,  **CipherBreaker.java**.

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
