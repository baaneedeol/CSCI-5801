import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;


/**
 * Test class for methods in the Main class.
 * This class contains unit tests for different methods related to election types, shuffle options, file parsing,
 * and candidate/ballot handling.
 *
 * @author Josh Subhan & Baanee Singh
 */
public class TestMain {

    /**
     * Tests the parseElectionType(Scanner) method with valid inputs: PV, STV, MV.
     */
    @Test
    public void testParseElectionTypeValid() {
        assertEquals(0, Main.parseElectionType(new Scanner("PV")));
        assertEquals(1, Main.parseElectionType(new Scanner("STV")));
        assertEquals(2, Main.parseElectionType(new Scanner("MV")));
    }

    /**
     * Tests the parseElectionType(Scanner) method with invalid inputs.
     */
    @Test
    public void testParseElectionTypeInvalid() {
        String[] invalidInputs = {"", " ", "0", "Plurality", "stv", "pv", "1", "mv", "xyz"};
        for (String input : invalidInputs) {
            assertEquals(-1, Main.parseElectionType(new Scanner(input)));
        }
    }


    /**
     * Test the parseShuffleOption(String[], int) method for valid shuffle flag.
     */
    @Test
    public void testParseShuffleOptionFlag(){
        String[] args2 = {"-ns"};
        assertFalse(Main.parseShuffleOption(args2, 1));

    }
    /**
     * Test the parseShuffleOption(String[], int) method for missing shuffle flag.
     */
    @Test
    // No flag provided
    public void testParseShuffleOptionNoFlag(){
        String[] args1 = {};
        assertTrue(Main.parseShuffleOption(args1, 1));

    }
    /**
     * Test the parseShuffleOption(String[], int) method for invalid shuffle flag.
     */
    @Test
    // Incorrect flag provided
    public void testParseShuffleOptionWrongFlag(){
        String[] args3 = {"-ns", "-s"};
        String[] args4 = {" ", "-ns"};

        assertTrue(Main.parseShuffleOption(args3, 1));
        assertTrue(Main.parseShuffleOption(args4, 1));
    }

    /**
     * Tests the parseFilename(Scanner) method with a single valid CSV filename.
     */
    @Test
    public void testParseFilenameSingleValid(){
        String input = "/plurality.csv\nq";
        Scanner test1 = new Scanner(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("/plurality.csv");

        assertEquals(expected, Main.parseFilenames(test1));
    }

    /**
     * Tests parseFilename() with multiple valid CSV filenames
     */
    @Test
    public void testParseFilenameMultipleValid(){
        String testString1 = "/plurality.csv";
        String testString2 = "/plurality2.csv";
        String input = "/plurality.csv\n/plurality2.csv\nq\n";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

        Scanner testScanner = new Scanner(inputStream);

        ArrayList<String> expected =  new ArrayList<>();
        expected.add(testString1);
        expected.add(testString2);

        assertEquals(expected, Main.parseFilenames(testScanner));
    }

    /**
     * Tests the parseFilename(Scanner) method with a single invalid file extension.
     */
    @Test
    // Incorrect file extension
    public void testParseFilenameSingleInvalid(){
        String input = "/plurality.txt\nq";
        Scanner test1 = new Scanner(input);

        ArrayList<String> expected = new ArrayList<>();

        assertEquals(expected, Main.parseFilenames(test1));
    }

    /**
     * Tests the parseFilename() method for multiple invalid filenames
     */
    @Test
    public void testParseFilenameMultipleInvalid(){
        String testString1 = "/plurality.csv";
        String testString2 = "/plurality.txt";
        String testString3 = "/plurality2.csv";

        String input = "/plurality.csv\n/plurality.csv\n/plurality2.csv\nq";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

        Scanner testScanner = new Scanner(inputStream);

        ArrayList<String> expected = new ArrayList<>();
        expected.add(testString1);
        expected.add(testString3);

        ArrayList<String> actual =  Main.parseFilenames(testScanner);

        assertEquals(expected, actual);

    }

    /**
     * Tests parseFilename() when a valid file has already been entered
     */
    @Test
    public void testParseFilenameAlreadyEntered(){
        String testString1 = "/plurality.csv";
        String testString2 = "/plurality2.csv";

        String input = "/plurality.csv\n/plurality2.csv\n/plurality.csv\nq";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner testScanner = new Scanner(inputStream);

        ArrayList<String> expected = new ArrayList<>();

        expected.add((testString1));
        expected.add(testString2);

        assertEquals(expected, Main.parseFilenames(testScanner));

    }

    /**
     * Tests the parseAuditFilename(Scanner) method with valid audit filenames
     */
    @Test
    public void testParseAuditFilenameValid(){
        String auditFileTest1 = "audit_plurality";
        String auditFileTest2 = "audit.stv";
        Scanner test1 = new Scanner(auditFileTest1);
        Scanner test2 = new Scanner(auditFileTest2);

        assertEquals("audit_plurality.txt", Main.parseAuditFilename(test1));
        assertEquals("audit.stv.txt", Main.parseAuditFilename(test2));
    }

    /**
     * Tests the parseAuditFilename(Scanner) method with invalid audit filenames
     */
    @Test
    public void testParseAuditFilenameInvalid(){
        String auditFileTest1 = "audit@plurality";
        String auditFileTest2 = "audit{STV}";

        Scanner test1 = new Scanner(auditFileTest1);
        Scanner test2 = new Scanner(auditFileTest2);

        assertEquals("Invalid", Main.parseAuditFilename(test1));
        assertEquals("Invalid", Main.parseAuditFilename(test2));
    }

    /**
     * Tests the parseNumSeats(Scanner) method with valid inputs for number of seats.
     */
    @Test
    // Valid numSeats input
    public void testParseNumSeatsValid(){
        String testSeats = "6\n";
        Scanner test1 = new Scanner(testSeats);

        assertEquals(6, Main.parseNumSeats(test1));
    }
    /**
     * Tests the parseNumSeats(Scanner) method with invalid inputs for number of seats.
     */
    @Test
    // Invalid numSeats input
    public void testParseNumSeatsInvalid(){
        String testSeats1 = "-1\n";
        String testSeats2 = "123456\n";
        String testSeats3 = "p\n";
        String testSeats4 = "3 3\n";

        Scanner test1 = new Scanner(testSeats1);
        Scanner test2 = new Scanner(testSeats2);
        Scanner test3 = new Scanner(testSeats3);
        Scanner test4 = new Scanner(testSeats4);

        assertEquals(-1, Main.parseNumSeats(test1));
        assertEquals(-1, Main.parseNumSeats(test2));
        assertEquals(-1, Main.parseNumSeats(test3));
        assertEquals(-1, Main.parseNumSeats(test4));
    }

    /**
     * Tests the createFileScanner(String) method for creating file scanners from valid filenames.
     */
    @Test
    // Successful creation
    public void testCreateFileScannerSuccess(){
        String filenameTest1 = "plurality.csv";
        String fileNameTest2 = "election-stv.csv";
        ArrayList<String> filenames = new ArrayList<>();
        filenames.add(filenameTest1);
        filenames.add(fileNameTest2);

        assertNotNull(Main.createFileScanners(filenames));
    }
    /**
     * Tests the createFileScanner(String) method for creating a file scanner from an invalid filename.
     */
    @Test
    // Unsuccessful creation
    public void testCreateFileScannerFail1(){
        String filenameTest1 = "election1.csv";
        String filenameTest2 = "election_2.csv";

        ArrayList<String> testFilenames =  new ArrayList<>();
        testFilenames.add(filenameTest1);
        testFilenames.add(filenameTest2);

       assertNull(Main.createFileScanners(testFilenames));
    }

    @Test
    public void testCreateFileScannerFail2(){
        String filenameTest1 = "plurality.csv";
        String filenameTest2 = "election_2.csv";

        ArrayList<String> testFilenames =  new ArrayList<>();
        testFilenames.add(filenameTest1);
        testFilenames.add(filenameTest2);

        assertNull(Main.createFileScanners(testFilenames));
    }

    /**
     * Tests the readCandidates(Scanner) method with multiple candidates.
     */
    @Test
    public void testReadCandidatesMultiple() throws FileNotFoundException {

        File plurality = new File("plurality.csv");
        File plurality2 = new File("plurality2.csv");

        Scanner test1 = new Scanner(plurality);
        for (int i = 0; i < 4; i++){
            test1.nextLine();
        }

        Scanner test2 = new Scanner(plurality2);

        for (int i = 0; i < 4; i++){
            test2.nextLine();
        }

        ArrayList<Candidate> set1 = Main.readCandidates(test1);
        ArrayList<Candidate> set2 = Main.readCandidates(test2);

        // set1 candidates
        assertEquals("A", set1.getFirst().getName());
        assertEquals("B", set1.get(1).getName());
        assertEquals("C", set1.get(2).getName());
        assertEquals("D", set1.get(3).getName());
        assertEquals("E", set1.get(4).getName());
        assertEquals("F", set1.get(5).getName());

        // set2 candidates
        assertEquals("Abby", set2.getFirst().getName());
        assertEquals("Bobby", set2.get(1).getName());
        assertEquals("Cody", set2.get(2).getName());
        assertEquals("Dave", set2.get(3).getName());
        assertEquals("Ellie", set2.get(4).getName());
        assertEquals("Fred", set2.get(5).getName());
        assertEquals("Anna", set2.get(6).getName());
    }

    /**
     * Tests the getNumCandidates() method for correct number of candidates.
     */
    @Test
    public void testParseNumCandidatesValid() throws FileNotFoundException {
        File plurality = new File("plurality.csv");

        Scanner test1 = new Scanner(plurality);
        for (int i = 0; i < 2; i++){
            test1.nextLine();
        }


        assertEquals(6, Main.parseNumCandidates(test1));

    }

    @Test
    public void testParseNumCandidatesInvalid() throws FileNotFoundException {
        File pluralityInvalid = new File("invalidNumCandidates.csv");
        Scanner test = new Scanner(pluralityInvalid);

        for (int i = 0; i < 2; i++){
            test.nextLine();
        }

        assertEquals(-1, Main.parseNumCandidates(test));

    }

    /**
     * Tests the readBallots(Scanner, ArrayList) method for reading ballots for plurality files
     */
    @Test
    public void testReadBallotsSingleFilePlurality() throws FileNotFoundException {
        File plurality = new File("plurality.csv");

        Scanner test1 = new Scanner(plurality);

        for (int i = 0; i < 4; i++){
            test1.nextLine();
        }


        ArrayList<Scanner> testScanners = new ArrayList<>();
        testScanners.add(test1);

        ArrayList<Candidate> cSet1 = Main.readCandidates(test1);
        ArrayList<Ballot> bSet1 = Main.readBallots(testScanners, cSet1);

        // First ballot from plurality.csv
        assertEquals(1, bSet1.getFirst().getBallotID());

        assertEquals(1, bSet1.getFirst().getRankings().get(cSet1.get(0)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(1)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(2)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(3)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(4)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(5)));

        // Second ballot from plurality.csv
        assertEquals(2, bSet1.get(1).getBallotID());

        assertEquals(0, bSet1.get(1).getRankings().get(cSet1.get(0)));
        assertEquals(0, bSet1.get(1).getRankings().get(cSet1.get(1)));
        assertEquals(1, bSet1.get(1).getRankings().get(cSet1.get(2)));
        assertEquals(0, bSet1.get(1).getRankings().get(cSet1.get(3)));
        assertEquals(0, bSet1.get(1).getRankings().get(cSet1.get(4)));
        assertEquals(0, bSet1.get(1).getRankings().get(cSet1.get(5)));


    }

    /**
     * Tests the readBallots(Scanner, ArrayList) method for reading ballots for STV files
     */
    @Test
    public void testReadBallotsSingleFileSTV() throws FileNotFoundException{
        File stv1 = new File("stv1.csv");

        Scanner test2 = new Scanner(stv1);

        for (int i = 0; i < 4; i++){
            test2.nextLine();
        }

        ArrayList<Scanner> testScanners = new ArrayList<>();
        testScanners.add(test2);

        ArrayList<Candidate> cSet2 = Main.readCandidates(test2);

        ArrayList<Ballot> bSet2 = Main.readBallots(testScanners, cSet2);

        // First ballot from stv1.csv
        assertEquals(1, bSet2.getFirst().getBallotID());

        assertEquals(1, bSet2.getFirst().getRankings().get(cSet2.getFirst()));
        assertEquals(0, bSet2.getFirst().getRankings().get(cSet2.get(1)));
        assertEquals(2, bSet2.getFirst().getRankings().get(cSet2.get(2)));
        assertEquals(0, bSet2.getFirst().getRankings().get(cSet2.get(3)));
        assertEquals(3, bSet2.getFirst().getRankings().get(cSet2.get(4)));
        assertEquals(0, bSet2.getFirst().getRankings().get(cSet2.get(5)));

        // Second ballot from stv1.csv
        assertEquals(2, bSet2.get(1).getBallotID());

        assertEquals(3, bSet2.get(1).getRankings().get(cSet2.getFirst()));
        assertEquals(2, bSet2.get(1).getRankings().get(cSet2.get(1)));
        assertEquals(1, bSet2.get(1).getRankings().get(cSet2.get(2)));
        assertEquals(4, bSet2.get(1).getRankings().get(cSet2.get(3)));
        assertEquals(6, bSet2.get(1).getRankings().get(cSet2.get(4)));
        assertEquals(5, bSet2.get(1).getRankings().get(cSet2.get(5)));
    }

    /**
     * Tests the readBallots(Scanner, ArrayList) method for reading ballots for reading MV files
     */
    @Test
    public void testReadBallotsSingleFileMV() throws FileNotFoundException {
        File mv1 = new File("mv.csv");
        Scanner test = new Scanner(mv1);

        for (int i = 0; i < 4; i++){
            test.nextLine();
        }

        ArrayList<Scanner> testScanners = new ArrayList<>();
        testScanners.add(test);

        ArrayList<Candidate> cSet2 = Main.readCandidates(test);

        ArrayList<Ballot> bSet2 = Main.readBallots(testScanners, cSet2);

        // First ballot from mv.csv
        assertEquals(1, bSet2.getFirst().getBallotID());

        assertEquals(1, bSet2.getFirst().getRankings().get(cSet2.getFirst()));
        assertEquals(1, bSet2.getFirst().getRankings().get(cSet2.get(1)));
        assertEquals(1, bSet2.getFirst().getRankings().get(cSet2.get(2)));
        assertEquals(0, bSet2.getFirst().getRankings().get(cSet2.get(3)));
        assertEquals(0, bSet2.getFirst().getRankings().get(cSet2.get(4)));
        assertEquals(0, bSet2.getFirst().getRankings().get(cSet2.get(5)));

        // Second ballot from mv.csv
        assertEquals(2, bSet2.get(1).getBallotID());

        assertEquals(1, bSet2.get(1).getRankings().get(cSet2.getFirst()));
        assertEquals(0, bSet2.get(1).getRankings().get(cSet2.get(1)));
        assertEquals(0, bSet2.get(1).getRankings().get(cSet2.get(2)));
        assertEquals(1, bSet2.get(1).getRankings().get(cSet2.get(3)));
        assertEquals(0, bSet2.get(1).getRankings().get(cSet2.get(4)));
        assertEquals(1, bSet2.get(1).getRankings().get(cSet2.get(5)));

    }

    /**
     * Tests the readBallots(Scanner, ArrayList) method for reading ballots from multiple elections file
     * In this case for a plurality election
     */
    @Test
    public void testReadBallotsMultipleFiles() throws FileNotFoundException {
        File plurality = new File("plurality.csv");
        File plurality2 = new File("pluralityp2.csv");

        Scanner test1 = new Scanner(plurality);
        Scanner test2 = new Scanner(plurality2);

        for (int i = 0; i < 4; i++){
            test1.nextLine();
        }

        for (int i = 0; i < 5; i++){
            test2.nextLine();
        }

        ArrayList<Scanner> testScanners = new ArrayList<>();
        testScanners.add(test1);
        testScanners.add(test2);

        ArrayList<Candidate> cSet1 = Main.readCandidates(test1);
        ArrayList<Ballot> bSet1 = Main.readBallots(testScanners, cSet1);

        // checking the first ballot from the first file
        assertEquals(1, bSet1.getFirst().getBallotID());

        assertEquals(1, bSet1.getFirst().getRankings().get(cSet1.get(0)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(1)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(2)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(3)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(4)));
        assertEquals(0, bSet1.getFirst().getRankings().get(cSet1.get(5)));

        // Checking the first ballot from the second file
        assertEquals(6, bSet1.get(5).getBallotID());

        assertEquals(1, bSet1.get(5).getRankings().get(cSet1.get(0)));
        assertEquals(0, bSet1.get(5).getRankings().get(cSet1.get(1)));
        assertEquals(0, bSet1.get(5).getRankings().get(cSet1.get(2)));
        assertEquals(0, bSet1.get(5).getRankings().get(cSet1.get(3)));
        assertEquals(0, bSet1.get(5).getRankings().get(cSet1.get(4)));
        assertEquals(0, bSet1.get(5).getRankings().get(cSet1.get(5)));

        assertEquals(8, bSet1.get(7).getBallotID());
    }

    /**
     * Test parseNumBallots() method for a single election file.
     * @throws FileNotFoundException e
     */
    @Test
    public void testParseNumBallotsSingleFile() throws FileNotFoundException{
        File plurality = new File("plurality.csv");

        Scanner test1 = new Scanner(plurality);

        for (int i = 0; i < 3; i++){
            test1.nextLine();
        }
        ArrayList<Scanner> testScanners = new ArrayList<>();
        testScanners.add(test1);

        assertEquals(5, Main.parseNumBallots(testScanners));

    }

    /**
     * Test parseNumBallots() method for multiple election files entered
     * @throws FileNotFoundException e
     */
    @Test
    public void testParseNumBallotsMultipleFiles() throws FileNotFoundException{
        File plurality = new File("plurality.csv");
        File plurality2 = new File("pluralityp2.csv");

        Scanner test1 = new Scanner(plurality);
        Scanner test2 = new Scanner(plurality2);

        for (int i = 0; i < 3; i++){
            test1.nextLine();
            test2.nextLine();
        }

        ArrayList<Scanner> testScanners = new ArrayList<>();
        testScanners.add(test1);
        testScanners.add(test2);

        assertEquals(11, Main.parseNumBallots(testScanners));
    }

    /**
     * Test for continue on yes based on user input.
     */
    @Test
    public void testPromptForMoreFilesYes() {
        String simulatedInput = "file1.csv\nfile2.csv\nq\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        ArrayList<String> result = Main.parseFilenames(new Scanner(System.in));

        assertEquals(2, result.size());
        assertTrue(result.contains("file1.csv"));
        assertTrue(result.contains("file2.csv"));
    }

    /**
     * Test for termination on no based on user input.
     */
    @Test
    public void testPromptForMoreFilesNo() {
        String simulatedInput = "file1.csv\nfile1.csv\ninvalidfile.txt\nq\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        ArrayList<String> result = Main.parseFilenames(new Scanner(System.in));

        assertEquals(1, result.size());
        assertTrue(result.contains("file1.csv"));
    }

}
