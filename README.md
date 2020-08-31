
# Guide to run the project JobsityTenPinBowling

#### You can run the application in a command line, in windows:
+ Press **Windows + R**
+ Type **cmd**
+ In the cmd you go to the directory where the project is.
+ Then type **javac MainBowling.java** to compile the code
+ After that type **java MainBowling**, to run the application.

#### If you prefer you can run the project in the IDE:

+ The project was developed in IntelliJ Idea, so you can run it since the main class **MainBowling**

### Running

+ Initially it ask for a directory and a file name with extension **.txt** (sample: D:\\bowlingGame.txt), if you don't give a directory by default it takes the relative path **src/main/resources/bowlingGame.txt**
+ After you give a valid directory with the input file, the application show you the result of the process.

### Notes
+ The application validates:
	+ if the pinballs info is right (i.e., it is a number betwen 0 to 10 or F).
   + A player only can have 10 turns.

+ The application support according to the test cases
    + Perfect score
    + Zero score
    + The sample input with 2 players.
    + 2 players game, with one of them a perfect score
	    + The input files to test these cases are in the folder **src/main/resources/** in the directory project

+ The project has unit testing running OK
+ I use some lambda and stream java features
+ The project was buitl with 1.8 java version.