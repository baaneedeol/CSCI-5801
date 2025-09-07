import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Main class handles the setup and execution of an election system.
 * It supports multiple types of elections: Plurality, Single Transferable Vote (STV), and Multi-Vote (MV).
 * The program prompts the user for necessary election parameters, processes input files, and initializes
 * the election based on the selected type.
 * The election system processes votes, handles various election types, and records results for audits.
 *
 * @author Josh Subhan & Baanee Singh
 */
public class Main {

    /**
     * Parses the user's input to determine the election type.
     * Valid election types:
     * - "PV" for Plurality
     * - "STV" for Single Transferable Vote
     * - "MV" for Municipal-Vote
     *
     * @param fileReader A Scanner object to read user input.
     * @return 0 for Plurality, 1 for STV, 2 for MV, or -1 for invalid input.
     */
    public static int parseElectionType(Scanner fileReader){
        if (!fileReader.hasNextLine()) {
            return -1; // no input
        }

        int electionType;

        String electionInput = fileReader.nextLine().trim();

        if (electionInput.equals("PV")){
            electionType = 0;
        }
        else if (electionInput.equals("STV")){
            electionType = 1;
        }
        else if (electionInput.equals("MV")) {
            electionType = 2;
        }
        else{
            electionType = -1;
        }

        return electionType;
    }

    /**
     * Parses command-line arguments to determine if ballot shuffling is enabled for STV elections.
     *
     * @param args Command-line arguments.
     * @param electionType The type of election selected (0 for Plurality, 1 for STV, 2 for MV).
     * @return true if shuffling is enabled, false otherwise.
     */
    public static boolean parseShuffleOption(String[] args, int electionType){
        boolean shuffle = true;

        // Ballot shuffle is only toggleable for STV elections
        if (electionType == 1){
            if (args.length == 1 && args[0].equals("-ns")){
                shuffle = false;
                System.out.println("Ballot shuffling is disabled.\n");
            }
            else if (args.length == 0){
                System.out.println("Ballot shuffling is enabled.\n");
            }
            else {
                System.out.println("Invalid number or unrecognized command line arguments. Shuffle is enabled by default.\n");
            }
        }
        return shuffle;
    }

    /**
     * Prompts the user for the election data filename(s).
     * Multiple file paths can be entered, and the user ends the input with 'q'.
     *
     * @param userInput A Scanner object to read user input.
     * @return An ArrayList of all the filenames of the files to be used
     * for election tabulation
     */
    public static ArrayList<String> parseFilenames(Scanner userInput){
        // Retrieve filename(s) from the user
        ArrayList<String> filenames = new ArrayList<>();
        String nextFilename = "";


        // Until the user signals that they don't have any more ballot files
        while (!nextFilename.equals("q")){

            System.out.println("Please enter a file path, and then press 'Enter'");
            System.out.println("If you have multiple election files continue entering paths and then enter 'q' when finished");
            System.out.println("Filenames must end in .csv\n");

            nextFilename = userInput.nextLine();
            nextFilename = nextFilename.trim();

            // User is finished giving files
            if (nextFilename.equals("q")){
                System.out.println("Continuing with election tabulation...");
                continue;
            }

            // The file just given was not a csv
            if (!nextFilename.endsWith(".csv")) {
                System.out.println("You entered an invalid filename. This file has not been added to the list. Please try again");
                continue;
            }

            // The file just given was already entered
            if (filenames.contains((nextFilename))){
                System.out.println("This filename has already been entered");
                continue;
            }

            // Add filename to list of strings
            filenames.add(nextFilename);
        }

        return filenames;
    }

    /**
     * Prompts the user for an audit filename to store election logs.
     *
     * @param userInput A Scanner object to read user input.
     * @return A valid filename with a ".txt" extension or "Invalid" for invalid input.
     */
    public static String parseAuditFilename(Scanner userInput){
        // Tell the user to give their choice of name for the audit output file
        System.out.println("Please enter a filename for the audit to be save to");
        System.out.println("The output file will be in the form .txt");

        // Characters that can't be in filenames for most operating systems
        String illegalCharacters = "[#%&{}<>*?/$\"':@+`|=^ ]";
        Pattern pattern = Pattern.compile(illegalCharacters);

        String auditFilename = userInput.nextLine();
        Matcher match = pattern.matcher(auditFilename);

        if (match.find() || auditFilename.isEmpty()){
            return "Invalid";
        }

        auditFilename = auditFilename.concat(".txt");
        return auditFilename;
    }

    /**
     * Prompts the user to enter the number of seats available in the election.
     *
     * @param fileReader A Scanner object to read user input.
     * @return The number of seats if valid, or -1 for invalid input.
     */
    public static int parseNumSeats(Scanner fileReader){
        int numSeats;

        // Get the number of seats from the election file
        try{
            String strNumSeats = fileReader.nextLine();
            numSeats = Integer.parseInt(strNumSeats);
        }
        catch (NumberFormatException e) {
            return -1;
        }
        // number of seats can't be negative or obnoxiously large
        if (numSeats < 1 || numSeats > 999){
            return -1;
        }

        return numSeats;
    }

    /**
     * Prompts the user to enter the number of candidates in the election.
     * The number of candidates must be a positive integer.
     *
     * @param fileReader A Scanner object to read user input.
     * @return The number of candidates if valid, or -1 for invalid input.
     */
    public static int parseNumCandidates(Scanner fileReader){
        int numCandidates;

        // Get the number of candidates as given in the election file
        try{
            String strNumCandidates = fileReader.nextLine();
            numCandidates = Integer.parseInt(strNumCandidates);
        }
        catch (NumberFormatException e) {
            return -1;
        }

        if (numCandidates < 1){
            return -1;
        }

        return numCandidates;
    }

    /**
     * Creates a Scanner object to read the election file.
     *
     * @param filenames A list of election filesname strings
     * @return A Scanner object if the file is found, otherwise null.
     */
    public static ArrayList<Scanner> createFileScanners(ArrayList<String> filenames) {
        ArrayList<Scanner> fileScanners = new ArrayList<>();

        // Iterate over the list of filename strings and create file scanners for each
        for (String filename : filenames) {
            File electonFile = new File(filename);
            try {
                Scanner fileScanner = new Scanner((electonFile));
                fileScanners.add(fileScanner);
            } catch (FileNotFoundException e) {
                return null;
            }
        }
        return fileScanners;
    }

    /**
     * Reads and parses candidate names from the election file.
     *
     * @param fileReader A Scanner object to read from the file.
     * @return A list of Candidate objects.
     */
    public static ArrayList<Candidate> readCandidates(Scanner fileReader){
        // Get all the candidate names

        ArrayList<Candidate> candidates = new ArrayList<>();
        String[] candidateNames = fileReader.nextLine().trim().split(",");

        // Create all candidate objects, add to ArrayList
        for (String candidateName : candidateNames) {
            Candidate newCandidate = new Candidate(candidateName.trim());
            candidates.add(newCandidate);
        }
        return candidates;
    }

    /**
     * Reads ballots from the election file and associates rankings with candidates.
     *
     * @param fileReaders A list of Scanner objects to read from the files
     * @param candidates The list of candidates.
     * @return A list of Ballot objects.
     */
    public static ArrayList<Ballot> readBallots(ArrayList<Scanner> fileReaders, ArrayList<Candidate> candidates){
        int ballotID = 1;
        int numCandidates = candidates.size();
        ArrayList<Ballot> ballots = new ArrayList<>();

        // Iterate over every file scanner to get all the ballots for every file
        // the election official gave to the system
        for (Scanner fileReader : fileReaders){
            while (fileReader.hasNextLine()){

                // Get the lines with the rankings from each ballot
                String[] rankingStrings = fileReader.nextLine().trim().split(",", -1);

                // Store them in a hashmap
                HashMap<Candidate, Integer> ranks = new HashMap<>();
                for (int i = 0; i < numCandidates; i++){
                    if (rankingStrings[i].isEmpty()){
                        ranks.put(candidates.get(i), 0);
                    }
                    else {
                        ranks.put(candidates.get(i), Integer.valueOf(rankingStrings[i]));
                    }
                }
                // create all the ballot objects
                Ballot newBallot = new Ballot(ballotID, ranks);
                ballots.add(newBallot);

                ballotID++;
            }
        }

        return ballots;
    }

    /**
     * Parses the number of ballots from the election files.
     *
     * @param fileReaders A list of Scanner objects to read from the files.
     * @return The total number of ballots if valid, or -1 for invalid input.
     */
    public static int parseNumBallots(ArrayList<Scanner> fileReaders){
        int numBallots = 0;

        // For every file given to the system,
        // parse them all for the number of ballots they have and add it all up
        for (Scanner fileReader : fileReaders) {
            try {
                String strNumBallots = fileReader.nextLine();
                numBallots += Integer.parseInt(strNumBallots);
            } catch (NumberFormatException e) {
                System.out.println("Exception thrown");
                return -1;
            }

            if (numBallots < 1) {
                System.out.println("Ballots less than one");
                return -1;
            }
        }

        return numBallots;
    }

    /**
     * Main method that does the setup, parsing, and execution of the election.
     * Depending on the election type, the appropriate election process is started.
     *
     * @param args Command-line arguments for ballot shuffling.
     */
    public static void main(String[] args) {
        // Main driver

        // Let the user begin their input
        Scanner userInput = new Scanner(System.in);
        // Get all the filenames
        ArrayList<String> electionFilenames = parseFilenames(userInput);

        // Create the fie scanners
        ArrayList<Scanner> fileScanners = createFileScanners(electionFilenames);

        // error checking
        if (fileScanners == null){
            System.exit(-1);
            System.err.println("List of scanners is null, an error occurred");
        }

        // Get the election type
        int electionType = parseElectionType(fileScanners.getFirst());
        if (electionType == -1){
            System.err.println("Invalid election type.");
            System.exit(-1);
        }

        // Get shuffle status
        boolean shuffle = parseShuffleOption(args, electionType);

        // Get number of seats
        int numSeats = parseNumSeats(fileScanners.getFirst());
        if (numSeats == -1){
            System.err.println("Invalid number of seats entered in file.");
            System.exit(-1);
        }

        // Get number of candidates
        int numCandidates = parseNumCandidates(fileScanners.getFirst());
        if (numCandidates == -1){
            System.err.println("Invalid number of candidates entered in file.");
            System.exit(-1);
        }

        // The following nested loop moves the file pointer to the same place that the
        // file pointer at index 0 is now at after going through the previous functions
        for (int i = 0; i < fileScanners.size(); i++) {
            if (i == 0){
                continue;
            }
            for (int j = 0; j < 3; j++) {
                fileScanners.get(i).nextLine();
            }
        }

        // Get the number of ballots
        int numBallots = parseNumBallots(fileScanners);
        System.out.println(numBallots);
        if (numBallots == -1){
            System.err.println("Invalid number of ballots entered in file.");
            System.exit(-1);
        }

        // Get the list of candidates
        ArrayList<Candidate> candidates = readCandidates(fileScanners.getFirst());

        // Same situation as above, get the file pointers for the rest of the
        // files to be pointing at the same line as the scanner at index 0
        for (int i = 0; i < fileScanners.size(); i++){
            if (i == 0){
                continue;
            }
            fileScanners.get(i).nextLine();
        }

        // Get all the ballots
        ArrayList<Ballot> ballots = readBallots(fileScanners, candidates);

        // Get the filename string for the audit file
        String auditFilename = parseAuditFilename(userInput);
        if (auditFilename.equals("Invalid")){
            System.err.println("Gave an invalid filename");
            System.exit(-1);
        }

        // Close all the scanners
        userInput.close();

        for (Scanner filescanner : fileScanners){
            filescanner.close();
        }

        // Call the selected election type
        if (electionType == 1){
            STV stv = new STV(shuffle, numSeats, numCandidates, numBallots, candidates, ballots, auditFilename);
            stv.startElection();
        }
        else if (electionType == 0) {
            Plurality plurality = new Plurality(numSeats, numCandidates, numBallots, candidates, ballots, auditFilename);
            plurality.startElection();
        }
        else if (electionType == 2){
            MV mv = new MV(numSeats, numCandidates, numBallots, candidates, ballots, auditFilename);
            mv.startElection();
        }
    }
}
