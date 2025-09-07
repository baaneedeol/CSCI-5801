import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying the functionality of the Plurality voting system.
 *
 * @author Zach Larsen
 */
public class TestPlurality {

  private ArrayList<Candidate> candidate;
  private ArrayList<Ballot> ballots;

  private ArrayList<Candidate> candidate2;
  private ArrayList<Ballot> ballots2;

  private ArrayList<Candidate> candidate3;
  private ArrayList<Ballot> ballots3;

  /**
   * Sets up the test environment for the first set of tests.
   * Initializes the candidates and ballots for the first test scenario.
   */
  @BeforeEach
  public void setUpPlurality(){
    candidate = new ArrayList<>();
    ballots = new ArrayList<>();

    Candidate can1 = new Candidate("Greg");
    Candidate can2 = new Candidate("Sally");
    Candidate can3 = new Candidate("Josh");
    Candidate can4 = new Candidate("Zach");
    Candidate can5 = new Candidate("Amelia");
    Candidate can6 = new Candidate("Bannee");
    Candidate can7 = new Candidate("Tim");
    Candidate can8 = new Candidate("Matt");
    candidate.add(can1);
    candidate.add(can2);
    candidate.add(can3);
    candidate.add(can4);
    candidate.add(can5);
    candidate.add(can6);
    candidate.add(can7);
    candidate.add(can8);

    HashMap<Candidate,Integer> map1 = new HashMap<>();
    map1.put(can1, 0);
    map1.put(can2, 1);
    map1.put(can3, 0);
    map1.put(can4, 0);
    map1.put(can5, 0);
    map1.put(can6, 0);
    map1.put(can7, 0);
    map1.put(can8, 0);


    HashMap<Candidate,Integer> map2 = new HashMap<>();
    map2.put(can1, 0);
    map2.put(can2, 1);
    map2.put(can3, 0);
    map2.put(can4, 0);
    map2.put(can5, 0);
    map2.put(can6, 0);
    map2.put(can7, 0);
    map2.put(can8, 0);

    HashMap<Candidate,Integer> map3 = new HashMap<>();
    map3.put(can1, 0);
    map3.put(can2, 1);
    map3.put(can3, 0);
    map3.put(can4, 0);
    map3.put(can5, 0);
    map3.put(can6, 0);
    map3.put(can7, 0);
    map3.put(can8, 0);

    HashMap<Candidate,Integer> map4 = new HashMap<>();
    map4.put(can1, 1);
    map4.put(can2, 0);
    map4.put(can3, 0);
    map4.put(can4, 0);
    map4.put(can5, 0);
    map4.put(can6, 0);
    map4.put(can7, 0);
    map4.put(can8, 0);

    HashMap<Candidate,Integer> map5 = new HashMap<>();
    map5.put(can1, 1);
    map5.put(can2, 0);
    map5.put(can3, 0);
    map5.put(can4, 0);
    map5.put(can5, 0);
    map5.put(can6, 0);
    map5.put(can7, 0);
    map5.put(can8, 0);

    HashMap<Candidate,Integer> map6 = new HashMap<>();
    map6.put(can1, 0);
    map6.put(can2, 0);
    map6.put(can3, 1);
    map6.put(can4, 0);
    map6.put(can5, 0);
    map6.put(can6, 0);
    map6.put(can7, 0);
    map6.put(can8, 0);

    HashMap<Candidate,Integer> map7 = new HashMap<>();
    map7.put(can1, 0);
    map7.put(can2, 0);
    map7.put(can3, 1);
    map7.put(can4, 0);
    map7.put(can5, 0);
    map7.put(can6, 0);
    map7.put(can7, 0);
    map7.put(can8, 0);

    HashMap<Candidate,Integer> map8 = new HashMap<>();
    map8.put(can1, 0);
    map8.put(can2, 0);
    map8.put(can3, 0);
    map8.put(can4, 0);
    map8.put(can5, 0);
    map8.put(can6, 0);
    map8.put(can7, 0);
    map8.put(can8, 1);

    Ballot ballot1 = new Ballot(1, map1);
    Ballot ballot2 = new Ballot(2, map2);
    Ballot ballot3 = new Ballot(3, map3);
    Ballot ballot4 = new Ballot(4, map4);
    Ballot ballot5 = new Ballot(5, map5);
    Ballot ballot6 = new Ballot(6, map6);
    Ballot ballot7 = new Ballot(7, map7);
    Ballot ballot8 = new Ballot(8, map8);
    ballots.add(ballot1);
    ballots.add(ballot2);
    ballots.add(ballot3);
    ballots.add(ballot4);
    ballots.add(ballot5);
    ballots.add(ballot6);
    ballots.add(ballot7);
    ballots.add(ballot8);
  }

  /**
   * Sets up the test environment for the second set of tests.
   * Initializes the candidates and ballots for the second test scenario.
   */
  @BeforeEach
  public void setUpPlurality2(){
    candidate2 = new ArrayList<>();
    ballots2 = new ArrayList<>();

    Candidate can1 = new Candidate("Liam");
    Candidate can2 = new Candidate("Tom");
    Candidate can3 = new Candidate("Kirk");
    candidate2.add(can1);
    candidate2.add(can2);
    candidate2.add(can3);

    HashMap<Candidate,Integer> map1 = new HashMap<>();
    map1.put(can1, 0);
    map1.put(can2, 1);
    map1.put(can3, 0);


    HashMap<Candidate,Integer> map2 = new HashMap<>();
    map2.put(can1, 0);
    map2.put(can2, 1);
    map2.put(can3, 0);

    HashMap<Candidate,Integer> map3 = new HashMap<>();
    map3.put(can1, 0);
    map3.put(can2, 1);
    map3.put(can3, 0);

    HashMap<Candidate,Integer> map4 = new HashMap<>();
    map4.put(can1, 1);
    map4.put(can2, 0);
    map4.put(can3, 0);

    HashMap<Candidate,Integer> map5 = new HashMap<>();
    map5.put(can1, 1);
    map5.put(can2, 0);
    map5.put(can3, 0);

    HashMap<Candidate,Integer> map6 = new HashMap<>();
    map6.put(can1, 0);
    map6.put(can2, 0);
    map6.put(can3, 1);

    HashMap<Candidate,Integer> map7 = new HashMap<>();
    map7.put(can1, 0);
    map7.put(can2, 0);
    map7.put(can3, 1);

    HashMap<Candidate,Integer> map8 = new HashMap<>();
    map8.put(can1, 1);
    map8.put(can2, 0);
    map8.put(can3, 0);

    Ballot ballot1 = new Ballot(1, map1);
    Ballot ballot2 = new Ballot(2, map2);
    Ballot ballot3 = new Ballot(3, map3);
    Ballot ballot4 = new Ballot(4, map4);
    Ballot ballot5 = new Ballot(5, map5);
    Ballot ballot6 = new Ballot(6, map6);
    Ballot ballot7 = new Ballot(7, map7);
    Ballot ballot8 = new Ballot(8, map8);
    ballots2.add(ballot1);
    ballots2.add(ballot2);
    ballots2.add(ballot3);
    ballots2.add(ballot4);
    ballots2.add(ballot5);
    ballots2.add(ballot6);
    ballots2.add(ballot7);
    ballots2.add(ballot8);
  }

  /**
   * Sets up the test environment for the third set of tests, which includes invalid ballot values.
   * Initializes candidates and ballots with some invalid vote counts.
   */
  @BeforeEach
  public void setUpPlurality3_BadBallotValues(){
    candidate3 = new ArrayList<>();
    ballots3 = new ArrayList<>();

    Candidate can1 = new Candidate("Ewin");
    Candidate can2 = new Candidate("Tillian");
    Candidate can3 = new Candidate("Betty");
    candidate3.add(can1);
    candidate3.add(can2);
    candidate3.add(can3);

    HashMap<Candidate,Integer> map1 = new HashMap<>();
    map1.put(can1, 5);
    map1.put(can2, 0);
    map1.put(can3, 1);

    HashMap<Candidate,Integer> map2 = new HashMap<>();
    map2.put(can1, -6);
    map2.put(can2, 0);
    map2.put(can3, 1);

    HashMap<Candidate,Integer> map3 = new HashMap<>();
    map3.put(can1, 0);
    map3.put(can2, 0);
    map3.put(can3, 0);

    HashMap<Candidate,Integer> map4 = new HashMap<>();
    map4.put(can1, 0);
    map4.put(can2, 0);
    map4.put(can3, 1);

    HashMap<Candidate,Integer> map5 = new HashMap<>();
    map5.put(can1, 1);
    map5.put(can2, 0);
    map5.put(can3, 1);

    HashMap<Candidate,Integer> map6 = new HashMap<>();
    map6.put(can1, 0);
    map6.put(can2, 0);
    map6.put(can3, 1);

    HashMap<Candidate,Integer> map7 = new HashMap<>();
    map7.put(can1, 0);
    map7.put(can2, 1);
    map7.put(can3, 0);

    Ballot ballot1 = new Ballot(1, map1);
    Ballot ballot2 = new Ballot(2, map2);
    Ballot ballot3 = new Ballot(3, map3);
    Ballot ballot4 = new Ballot(4, map4);
    Ballot ballot5 = new Ballot(5, map5);
    Ballot ballot6 = new Ballot(6, map6);
    Ballot ballot7 = new Ballot(7, map7);
    ballots3.add(ballot1);
    ballots3.add(ballot2);
    ballots3.add(ballot3);
    ballots3.add(ballot4);
    ballots3.add(ballot5);
    ballots3.add(ballot6);
    ballots3.add(ballot7);
  }

  /**
   * Tests the tabulation of votes in a Plurality voting system.
   * Verifies the correct vote count for each candidate after the tabulation.
   */
  @Test
  public void testTabulateVotes(){
    Plurality plurality = new Plurality(6,8,8,candidate,ballots, null);
    plurality.tabulateVotes();

    assertEquals(3,candidate.get(1).getVoteCount());
    assertEquals(2,candidate.get(0).getVoteCount());
    assertEquals(2,candidate.get(2).getVoteCount());
    assertEquals(1,candidate.get(7).getVoteCount());
    assertEquals(0,candidate.get(3).getVoteCount());
    assertEquals(0,candidate.get(4).getVoteCount());
    assertEquals(0,candidate.get(5).getVoteCount());
    assertEquals(0,candidate.get(6).getVoteCount());
  }

  /**
   * Tests the tabulation of votes for the second test scenario.
   * Verifies the correct vote count and the size of the ballots for each candidate.
   */
  @Test
  public void testTabulateVotes2(){
    Plurality plurality = new Plurality(3,3,8,candidate2,ballots2, null);
    plurality.tabulateVotes();

    assertEquals(3,candidate2.getFirst().getVoteCount());
    assertEquals(3,candidate2.getFirst().getBallots().size());
    assertEquals(3,candidate2.get(1).getVoteCount());
    assertEquals(3,candidate2.get(1).getBallots().size());
    assertEquals(2,candidate2.getLast().getVoteCount());
    assertEquals(2,candidate2.getLast().getBallots().size());
  }

  /**
   * Tests the tie-breaking functionality in a Plurality voting system.
   * Verifies that ties are correctly resolved based on predefined rules.
   */
  @Test
  public void testSettleTies(){
    ArrayList<Candidate> tied = new ArrayList<>();
    Candidate can1 = new Candidate("Greg");
    Candidate can2 = new Candidate("Sally");
    Candidate can3 = new Candidate("Josh");
    tied.add(can1);
    tied.add(can2);
    tied.add(can3);

    Plurality plurality = new Plurality(1,3,3,tied,ballots, null);
    plurality.setDeterministic(); //Test might fail without DETERMINISTIC set to true
    plurality.settleTies(tied, 0);

    ArrayList<Candidate> winner = plurality.getWinners();
    ArrayList<Candidate> loser = plurality.getLosers();

    assertEquals(can2, winner.getFirst());
    assertEquals(1, winner.size());

    assertEquals(can1, loser.getFirst());
    assertEquals(can3, loser.getLast());
    assertEquals(2, loser.size());
  }

  /**
   * Tests the settleTies method when there are three
   * candidates tied, and one is selected as a winner.
   */
  @Test
  public void testSettleTies2(){
    ArrayList<Candidate> tied = new ArrayList<>();
    Candidate can1 = new Candidate("John");
    Candidate can2 = new Candidate("Jake");
    Candidate can3 = new Candidate("Josh");
    tied.add(can1);
    tied.add(can2);
    tied.add(can3);

    Plurality plurality = new Plurality(2,3,3,tied,ballots, null);
    plurality.setDeterministic(); //Test might fail without DETERMINISTIC set to true
    plurality.settleTies(tied, 0);

    ArrayList<Candidate> winner = plurality.getWinners();
    ArrayList<Candidate> loser = plurality.getLosers();

    assertEquals(can2, winner.getFirst());
    assertEquals(can1, winner.get(1));
    assertEquals(2, winner.size());

    assertEquals(can3, loser.getFirst());
    assertEquals(1, loser.size());
  }

  /**
   * Tests the settleTies method when there are four candidates tied,
   * and one is selected as a winner.
   */
  @Test
  public void testSettleTies3(){
    ArrayList<Candidate> tied = new ArrayList<>();
    Candidate can1 = new Candidate("John");
    Candidate can2 = new Candidate("Jake");
    Candidate can3 = new Candidate("Josh");
    Candidate can4 = new Candidate("Andrew");
    tied.add(can1);
    tied.add(can2);
    tied.add(can3);
    tied.add(can4);

    Plurality plurality = new Plurality(2,4,3,tied,ballots, null);
    plurality.setDeterministic(); //Test might fail without DETERMINISTIC set to true
    plurality.settleTies(tied, 1);

    ArrayList<Candidate> winner = plurality.getWinners();
    ArrayList<Candidate> loser = plurality.getLosers();

    assertEquals(can4, winner.getFirst());
    assertEquals(1, winner.size());

    assertEquals(can1, loser.getFirst());
    assertEquals(can2, loser.get(1));
    assertEquals(can3, loser.getLast());
    assertEquals(3, loser.size());
  }

  /**
   * Tests the determine_Winner_Loser method when there are winners and losers,
   * including tied candidates.
   */
  @Test
  public void testDetermineWinnerandLosers(){
    Plurality plurality = new Plurality(6,8,8,candidate,ballots, null);
    plurality.setDeterministic();
    plurality.tabulateVotes();
    plurality.determine_Winner_Loser();

    ArrayList<Candidate> winner = plurality.getWinners();
    ArrayList<Candidate> loser = plurality.getLosers();

    assertEquals(8,candidate.size());
    assertEquals(6, winner.size());
    assertEquals(2,loser.size());
    assertEquals(candidate.get(0), winner.getFirst());
    assertEquals(candidate.get(1), winner.get(1));
    assertEquals(candidate.get(2), winner.get(2));
    assertEquals(candidate.get(3),winner.get(3));
    //Tied Candidates
    assertEquals(candidate.get(7), winner.get(4));
    assertEquals(candidate.get(4), winner.get(5));

    assertEquals(candidate.get(5),loser.getFirst());
    assertEquals(candidate.get(6), loser.getLast());
  }

  /**
   * Tests the determine_Winner_Loser method when there are three candidates, all of whom are winners.
   */
  @Test
  public void testDetermineWinnerandLosers2(){
    Plurality plurality = new Plurality(3,3,8,candidate2,ballots2, null);
    plurality.setDeterministic();
    plurality.tabulateVotes();
    plurality.determine_Winner_Loser();

    ArrayList<Candidate> winner = plurality.getWinners();
    ArrayList<Candidate> loser = plurality.getLosers();

    assertEquals(3,candidate2.size());
    assertEquals(3, winner.size());
    assertEquals(0,loser.size());
    assertEquals(candidate2.get(0), winner.getFirst());
    assertEquals(candidate2.get(1), winner.get(1));
    assertEquals(candidate2.get(2), winner.get(2));
  }

  /**
   * Tests the determine_Winner_Loser method when there are three candidates, and all are losers.
   */
  @Test
  public void testDetermineWinnerandLosers3(){
    Plurality plurality = new Plurality(0,3,3,candidate3,ballots3, null);
    plurality.setDeterministic();
    plurality.tabulateVotes();
    plurality.determine_Winner_Loser();

    ArrayList<Candidate> winner = plurality.getWinners();
    ArrayList<Candidate> loser = plurality.getLosers();

    assertEquals(3,candidate3.size());
    assertEquals(0, winner.size());
    assertEquals(3,loser.size());
    assertEquals(candidate3.get(0), loser.getFirst());
    assertEquals(candidate3.get(1), loser.get(1));
    assertEquals(candidate3.get(2), loser.get(2));
  }

  /**
   * Tests the displayResults method, ensuring the output
   * includes correct election details and results.
   */
  @Test
  public void testDisplayResults(){
    ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputContent));

    Plurality plurality = new Plurality(6,8,8,candidate,ballots, null);
    plurality.setDeterministic();
    plurality.tabulateVotes();
    plurality.determine_Winner_Loser();
    plurality.displayResults("Plurality");

    String output = outputContent.toString();

    assertTrue(output.contains("Election Type: Plurality"));
    assertTrue(output.contains("Number of Ballots: 8"));
    assertTrue(output.contains("Seats to be Filled: 6"));
    assertTrue(output.contains("Number of Candidates: 8"));
    assertTrue(output.contains("Winners: Sally with a vote percentage of: 37.5%, Greg with a vote percentage of: 25.0%, Josh with a vote percentage of: 25.0%, Matt with a vote percentage of: 12.5%, Tim with a vote percentage of: 0.0%, Zach with a vote percentage of: 0.0%\n"));
    assertTrue(output.contains("Losers: Amelia with a vote percentage of: 0.0%, Bannee with a vote percentage of: 0.0%"));

    System.setOut(System.out);
  }

  /**
   * Tests the displayResults method when there are no losers in the election.
   */
  @Test
  public void testDisplayResults2(){
    ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputContent));

    Plurality plurality = new Plurality(3,3,8,candidate2,ballots2, null);
    plurality.setDeterministic();
    plurality.tabulateVotes();
    plurality.determine_Winner_Loser();
    plurality.displayResults("Plurality");

    String output = outputContent.toString();

    assertTrue(output.contains("Election Type: Plurality"));
    assertTrue(output.contains("Number of Ballots: 8"));
    assertTrue(output.contains("Seats to be Filled: 3"));
    assertTrue(output.contains("Number of Candidates: 3"));
    assertTrue(output.contains("Winners: Liam with a vote percentage of: 37.5%, Tom with a vote percentage of: 37.5%, Kirk with a vote percentage of: 25.0%\n"));
    //No losers output because there were no losers

    System.setOut(System.out);
  }

  /**
   * Tests the generateAudit method, ensuring the audit file is created for a given election scenario.
   */
  @Test
  public void testGenerateAudit(){
    Plurality plurality = new Plurality(6,8,8,candidate,ballots, "testAuditPlurality1.txt");
    plurality.setDeterministic();
    plurality.tabulateVotes();
    plurality.determine_Winner_Loser();
    File audit = plurality.generateAudit("Plurality");


    assertTrue(audit.exists());// file should already be there

    assertTrue(audit.delete());// delete file
  }

  /**
   * Tests the generateAudit method when there are no losers, ensuring the audit file is created.
   */
  @Test
  public void testGenerateAudit2(){
    Plurality plurality = new Plurality(3,3,8,candidate2,ballots2, "testAuditPlurality_noLosers.txt");
    plurality.setDeterministic();
    plurality.tabulateVotes();
    plurality.determine_Winner_Loser();
    File audit = plurality.generateAudit("Plurality");

    assertTrue(audit.exists()); // file should already be there

    assertTrue(audit.delete());
  }

  /**
   * Tests the complete election process,
   * verifying that winners are selected and an audit file is created.
   */
  @Test
  public void testStartPluralityElection() {
    // Create a Plurality election with the given candidates and ballots
    Plurality plurality = new Plurality(3,3,8,candidate2,ballots2, "PluralityElectionAudit.txt");

    // Start the election
    plurality.startElection();

    // Verify that a winner was selected
    assertNotNull(plurality.getWinners(), "A winner should be selected in Plurality election");

    // Verify audit file was created
    File auditFile = new File("PluralityElectionAudit.txt");
    assertTrue(auditFile.exists(), "Audit file should be created");
    auditFile.delete(); // Clean up
  }

}