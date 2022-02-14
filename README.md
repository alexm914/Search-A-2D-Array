// Searching a 2-D Array using Recursion 
// COSC 211 Programming Data Structures, Elsa Poh
// Winter 2021
// Authors: Alex McNulty & Farha Zinda
// READ ME file
// Searching a 2-D Array using Recursion 

// About the program

/*
This program asks the user to enter the number of rows and number of columns for a 
two-dimensional array. The program then populates the array with a random selection of 
lower case letters, searches the array horizontally, vertically, and diagonally for 
repeats of two or more of the same letter, then prints the array with each letter
separated from the next by a space. 

Below the array, the search results are printed after a header for each type of search 
(horizontal, vertical, or diagonal). Each result includes the row and column for the 
last letter in each run, the length of the run, and the included letter.

At the end of the results is a line stating the length of the longest run, and the letter. 
*/

/* 
List of classes
RecursionProject3.java
*/

/*
How to Compile and Run this Program
1. Download the zip file.
2. Expand the zip file.
3. Import the jar file into the IDE.
4. Run the program from the RecursionProject3 class, the main method is located 
at the top of this class file.
*/


/*
Expected Input & Output
The expected input for this project are two positive integers, entered via the keyboard 
by the user. 

The expected output for this project is a rectangular array of the entered height and 
width, with Horizontal, Vertical, and Lower Right to Upper Left Diagonal Search results 
printed after the array. A second, vertically flipped array is printed after the header 
Lower Left to Upper Right Diagonal Search.

The row and column values are instantiated from 1, so row 1, column 1 refers to the letter in
the upper left corner. 

If there are no repeated runs for a particular section, the header for that section will 
be followed by a blank line and then the next section's header and results. 
*/

/*
Example Output 
What are the dimensions of the array? 
5
5
l b w c s 
r f f o a 
w n t t c 
g g u a h 
v d y d h 

Horizontal search: 
row: 4 col: 2
The run is 2 for g
row: 3 col: 4
The run is 2 for t
row: 2 col: 3
The run is 2 for f

Vertical search: 
row: 5 col: 4
The run is 2 for h

Lower Right to Upper Left Diagonal Search: 

Lower Left to Upper Right Diagonal Search
v d y d h 
g g u a h 
w n t t c 
r f f o a 
l b w c s 


Longest run: 2 for letters: f t g 
*/
