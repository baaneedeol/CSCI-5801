# Voting Tabulation System

This system can calculate the number of votes and determine the winner of a plurality (PV), single transferable vote (STV), and municipal voting (MV) election. The user is prompted to enter one or more CSV files, one at a time. If multiple files are entered, ballots from all files will be counted for the election. This functionality allows for receiving ballot files from different polling locations and aggregating within our program with no preprocessing required. All pertinent election information is found in the header of the CSV file. The file header should have the following format:

* 1st Line: Election type (PV, STV, or MV)
* 2nd Line: Number of Seats
* 3rd Line: Number of Candidates
* 4th Line: Number of Ballots
* 5th Line: The candidates separated by commas
* Ballots follow the header

In an STV election, the ballots must be shuffled to ensure fairness, but this functionality can be disabled for testing purposes with a command line flag (see Installation and Usage Instructions).

# Installation and Usage Instructions

The Java Runtime Environment (JRE) and Java Development Kit (JDK) is required to compile and run this program. Version 21 was used for development and testing.

It is recommended that the program be run and compiled from the command line rather than using the "run" button in an IDE. Development environments such as IntelliJ set the current working directory (cwd) as the project root folder rather than where the source file is located. If using IntelliJ, make sure to set the cwd to the folder where the source file is being run from.

## Compilation

Navigate to Project2/src

Run the following command

``` javac Main.java ```

## Run

```java Main.java ```

If the user intends to run an STV election for test purposes and would like to disable the shuffling, they must add the following command-line flag:

```java Main.java -ns```

If you are using the files provided and don't move them which is recommended because some tests may fail then, use the following to upload a csv file:

```../testing/XXX.csv```

## Testing

When running test files, please follow the same instructions and notes above.

Navigate to Project2/testing

Run and compile using the following command

``` javac <testfile>.java ```

``` java <testfile>.java ```

If using IntelliJ, make sure to set the run parameters and cwd directory appropriately before running using the play button.

Also, to avoid file not found errors, it is recommended when running our tests to leave all testing ballot files in the /testing directory.

Also, all of our test do pass so if they are failing it is an issue with how the tests are being run. Possibly from the wrong cwd.

## Javadocs

To view Javadoc documentation, please locate the index.html file in Project2/documentation

