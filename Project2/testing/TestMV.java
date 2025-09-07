import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for verifying the functionality of the Municipal voting system.
 *
 * @author Zach Larsen
 */
public class TestMV {
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
  public void setUpMV(){
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
    map1.put(can1, 1);
    map1.put(can2, 1);
    map1.put(can3, 0);
    map1.put(can4, 1);
    map1.put(can5, 1);
    map1.put(can6, 1);
    map1.put(can7, 0);
    map1.put(can8, 0);


    HashMap<Candidate,Integer> map2 = new HashMap<>();
    map2.put(can1, 1);
    map2.put(can2, 1);
    map2.put(can3, 0);
    map2.put(can4, 1);
    map2.put(can5, 0);
    map2.put(can6, 0);
    map2.put(can7, 0);
    map2.put(can8, 1);

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
    map4.put(can5, 1);
    map4.put(can6, 1);
    map4.put(can7, 1);
    map4.put(can8, 1);

    HashMap<Candidate,Integer> map5 = new HashMap<>();
    map5.put(can1, 0);
    map5.put(can2, 0);
    map5.put(can3, 0);
    map5.put(can4, 1);
    map5.put(can5, 1);
    map5.put(can6, 1);
    map5.put(can7, 0);
    map5.put(can8, 0);

    HashMap<Candidate,Integer> map6 = new HashMap<>();
    map6.put(can1, 1);
    map6.put(can2, 0);
    map6.put(can3, 1);
    map6.put(can4, 0);
    map6.put(can5, 0);
    map6.put(can6, 0);
    map6.put(can7, 1);
    map6.put(can8, 0);

    HashMap<Candidate,Integer> map7 = new HashMap<>();
    map7.put(can1, 1);
    map7.put(can2, 0);
    map7.put(can3, 1);
    map7.put(can4, 0);
    map7.put(can5, 0);
    map7.put(can6, 0);
    map7.put(can7, 0);
    map7.put(can8, 1);

    HashMap<Candidate,Integer> map8 = new HashMap<>();
    map8.put(can1, 0);
    map8.put(can2, 1);
    map8.put(can3, 1);
    map8.put(can4, 0);
    map8.put(can5, 1);
    map8.put(can6, 1);
    map8.put(can7, 1);
    map8.put(can8, 0);

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
  public void setUpMV2(){
    candidate2 = new ArrayList<>();
    ballots2 = new ArrayList<>();

    Candidate can1 = new Candidate("Liam");
    Candidate can2 = new Candidate("Tom");
    Candidate can3 = new Candidate("Kirk");
    candidate2.add(can1);
    candidate2.add(can2);
    candidate2.add(can3);

    HashMap<Candidate,Integer> map1 = new HashMap<>();
    map1.put(can1, 1);
    map1.put(can2, 1);
    map1.put(can3, 0);


    HashMap<Candidate,Integer> map2 = new HashMap<>();
    map2.put(can1, 0);
    map2.put(can2, 1);
    map2.put(can3, 1);

    HashMap<Candidate,Integer> map3 = new HashMap<>();
    map3.put(can1, 0);
    map3.put(can2, 1);
    map3.put(can3, 1);

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
    map7.put(can1, 1);
    map7.put(can2, 0);
    map7.put(can3, 1);

    HashMap<Candidate,Integer> map8 = new HashMap<>();
    map8.put(can1, 1);
    map8.put(can2, 1);
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
  public void setUpMV3_BadBallotValues(){
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
    map6.put(can1, 1);
    map6.put(can2, 0);
    map6.put(can3, 1);

    HashMap<Candidate,Integer> map7 = new HashMap<>();
    map7.put(can1, 0);
    map7.put(can2, 1);
    map7.put(can3, 1);

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
   * Tests the tabulation of votes in a Municipal voting system.
   * Verifies the correct vote count for each candidate after the tabulation.
   */
  @Test
  public void testTabulateVotes(){
    MV mv = new MV(5,8,8,candidate,ballots, null);
    mv.tabulateVotes();

    assertEquals(5,candidate.get(0).getVoteCount());
    assertEquals(5,candidate.get(0).getBallots().size());

    assertEquals(4,candidate.get(1).getVoteCount());
    assertEquals(4,candidate.get(1).getBallots().size());

    assertEquals(3,candidate.get(2).getVoteCount());
    assertEquals(3,candidate.get(2).getBallots().size());

    assertEquals(3,candidate.get(3).getVoteCount());
    assertEquals(3,candidate.get(3).getBallots().size());

    assertEquals(4,candidate.get(4).getVoteCount());
    assertEquals(4,candidate.get(4).getBallots().size());

    assertEquals(4,candidate.get(5).getVoteCount());
    assertEquals(4,candidate.get(5).getBallots().size());

    assertEquals(3,candidate.get(6).getVoteCount());
    assertEquals(3,candidate.get(6).getBallots().size());

    assertEquals(3,candidate.get(7).getVoteCount());
    assertEquals(3,candidate.get(7).getBallots().size());
  }

  /**
   * Tests the tabulation of votes in a Municipal voting system for the second set of ballots.
   * Verifies the correct vote count for each candidate after the tabulation.
   */
  @Test
  public void testTabulateVotes2(){
    MV mv = new MV(2,3,8,candidate2,ballots2, null);
    mv.tabulateVotes();

    assertEquals(5,candidate2.get(0).getVoteCount());
    assertEquals(5,candidate2.get(0).getBallots().size());
    assertEquals(4,candidate2.get(1).getVoteCount());
    assertEquals(4,candidate2.get(1).getBallots().size());
    assertEquals(4,candidate2.get(2).getVoteCount());
    assertEquals(4,candidate2.get(2).getBallots().size());

  }

  /**
   * Tests the tabulation of votes in a Municipal voting system for the third set of ballots.
   * Verifies the correct vote count for each candidate after the tabulation.
   */
  @Test
  public void testTabulateVotes3(){
    MV mv = new MV(2,3,8,candidate3,ballots3, null);
    mv.tabulateVotes();

    assertEquals(2,candidate3.get(0).getVoteCount());
    assertEquals(2,candidate3.get(0).getBallots().size());
    assertEquals(1,candidate3.get(1).getVoteCount());
    assertEquals(1,candidate3.get(1).getBallots().size());
    assertEquals(6,candidate3.get(2).getVoteCount());
    assertEquals(6,candidate3.get(2).getBallots().size());

  }


  /**
   * Tests the determine_Winner_Loser method when there are winners and losers,
   * including tied candidates.
   */
  @Test
  public void testDetermineWinnerandLosers(){
    MV mv = new MV(6,8,8,candidate,ballots, null);
    mv.setDeterministic();
    mv.tabulateVotes();
    mv.determine_Winner_Loser();

    ArrayList<Candidate> winner = mv.getWinners();
    ArrayList<Candidate> loser = mv.getLosers();

    assertEquals(8,candidate.size());
    assertEquals(6, winner.size());
    assertEquals(2,loser.size());
    assertEquals(candidate.get(0), winner.getFirst());
    assertEquals(candidate.getFirst().getVoteCount(),5);
    assertEquals(candidate.get(1), winner.get(1));
    assertEquals(candidate.get(2), winner.get(2));
    assertEquals(candidate.get(3),winner.get(3));
    assertEquals(candidate.get(3).getVoteCount(),4);
    assertEquals(candidate.get(3).getName(),"Bannee");
    //Tied Candidates
    assertEquals(candidate.get(7), winner.get(4));
    assertEquals(candidate.get(4), winner.get(5));

    assertEquals(candidate.get(5),loser.getFirst());
    assertEquals(candidate.get(6), loser.getLast());
  }


  /**
   * Tests the determine_Winner_Loser method when there are winners and losers,
   * including tied candidates.
   */
  @Test
  public void testDetermineWinnerandLosers2(){
    MV mv = new MV(2,3,8,candidate2,ballots2, null);
    mv.setDeterministic();
    mv.tabulateVotes();
    mv.determine_Winner_Loser();

    ArrayList<Candidate> winner = mv.getWinners();
    ArrayList<Candidate> loser = mv.getLosers();

    assertEquals(3,candidate2.size());
    assertEquals(2, winner.size());
    assertEquals(1,loser.size());

    assertEquals(candidate2.getFirst(), winner.getFirst());
    assertEquals(candidate2.getFirst().getVoteCount(),5);
    assertEquals(candidate2.getFirst().getName(), "Liam");

    assertEquals(candidate2.get(1), winner.get(1));
    assertEquals(candidate2.get(1).getVoteCount(),4);
    assertEquals(candidate2.get(1).getName(), "Tom");


    assertEquals(candidate2.get(2),loser.getFirst());
    assertEquals(candidate2.get(2).getVoteCount(),4);
    assertEquals(candidate2.get(2).getName(), "Kirk");
  }


  /**
   * Tests the tie-breaking functionality in an MV voting system.
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

    MV mv = new MV(1,3,3,tied,ballots, null);
    mv.setDeterministic(); //Test might fail without DETERMINISTIC set to true
    mv.settleTies(tied, 0);

    ArrayList<Candidate> winner = mv.getWinners();
    ArrayList<Candidate> loser = mv.getLosers();

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

    MV mv = new MV(2,3,3,tied,ballots, null);
    mv.setDeterministic(); //Test might fail without DETERMINISTIC set to true
    mv.settleTies(tied, 0);

    ArrayList<Candidate> winner = mv.getWinners();
    ArrayList<Candidate> loser = mv.getLosers();

    assertEquals(can2, winner.getFirst());
    assertEquals(can1, winner.get(1));
    assertEquals(2, winner.size());

    assertEquals(can3, loser.getFirst());
    assertEquals(1, loser.size());
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

    MV mv = new MV(5,8,8,candidate,ballots, null);
    mv.setDeterministic();
    mv.tabulateVotes();
    mv.determine_Winner_Loser();
    mv.displayResults("Municipal Voting");

    String output = outputContent.toString();

    assertTrue(output.contains("Election Type: Municipal Voting"));
    assertTrue(output.contains("Number of Ballots: 8"));
    assertTrue(output.contains("Seats to be Filled: 5"));
    assertTrue(output.contains("Number of Candidates: 8"));
    assertTrue(output.contains("Winners: Greg with a vote percentage of: 62.5%, Sally with a vote percentage of: 50.0%, Amelia with a vote percentage of: 50.0%, Bannee with a vote percentage of: 50.0%, Matt with a vote percentage of: 37.5%\n"));
    assertTrue(output.contains("Losers: Josh with a vote percentage of: 37.5%, Zach with a vote percentage of: 37.5%, Tim with a vote percentage of: 37.5%"));

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

    MV mv = new MV(3,3,8,candidate2,ballots2, null);
    mv.setDeterministic();
    mv.tabulateVotes();
    mv.determine_Winner_Loser();
    mv.displayResults("Municipal Voting");

    String output = outputContent.toString();

    assertTrue(output.contains("Election Type: Municipal Voting"));
    assertTrue(output.contains("Number of Ballots: 8"));
    assertTrue(output.contains("Seats to be Filled: 3"));
    assertTrue(output.contains("Number of Candidates: 3"));
    assertTrue(output.contains("Winners: Liam with a vote percentage of: 62.5%, Tom with a vote percentage of: 50.0%, Kirk with a vote percentage of: 50.0%\n"));
    //No losers output because there were no losers

    System.setOut(System.out);


  }


  /**
   * Tests the generateAudit method, ensuring the audit file is created for a given election scenario.
   */
  @Test
  public void testGenerateAudit(){
    MV mv = new MV(6,8,8,candidate,ballots, "testAuditMV1.txt");
    mv.setDeterministic();
    mv.tabulateVotes();
    mv.determine_Winner_Loser();
    File audit = mv.generateAudit("Municipal Voting");


    assertTrue(audit.exists());// file should already be there

    assertTrue(audit.delete());// delete file
  }

  /**
   * Tests the generateAudit method when there are no losers, ensuring the audit file is created.
   */
  @Test
  public void testGenerateAudit2(){
    MV mv = new MV(3,3,8,candidate2,ballots2, "testAuditMV_noLosers.txt");
    mv.setDeterministic();
    mv.tabulateVotes();
    mv.determine_Winner_Loser();
    File audit = mv.generateAudit("Municipal Voting");

    assertTrue(audit.exists()); // file should already be there

    assertTrue(audit.delete());
  }

  /**
   * A system test for MV that covers all methods called in a normal MV election.
   * Note that settleTies is called from determine_Winners_Losers().
   */
  @Test
  public void systemTestMV(){
    MV mv = new MV(6,8,8,candidate,ballots, "testMV_");
    mv.setDeterministic();
    mv.tabulateVotes();

    assertEquals(5,candidate.get(0).getVoteCount());
    assertEquals(5,candidate.get(0).getBallots().size());

    assertEquals(4,candidate.get(1).getVoteCount());
    assertEquals(4,candidate.get(1).getBallots().size());

    assertEquals(3,candidate.get(2).getVoteCount());
    assertEquals(3,candidate.get(2).getBallots().size());

    assertEquals(3,candidate.get(3).getVoteCount());
    assertEquals(3,candidate.get(3).getBallots().size());

    assertEquals(4,candidate.get(4).getVoteCount());
    assertEquals(4,candidate.get(4).getBallots().size());

    assertEquals(4,candidate.get(5).getVoteCount());
    assertEquals(4,candidate.get(5).getBallots().size());

    assertEquals(3,candidate.get(6).getVoteCount());
    assertEquals(3,candidate.get(6).getBallots().size());

    assertEquals(3,candidate.get(7).getVoteCount());
    assertEquals(3,candidate.get(7).getBallots().size());


    mv.determine_Winner_Loser();

    ArrayList<Candidate> winner = mv.getWinners();
    ArrayList<Candidate> loser = mv.getLosers();

    assertEquals(8,candidate.size());
    assertEquals(6, winner.size());
    assertEquals(2,loser.size());
    assertEquals(candidate.get(0), winner.getFirst());
    assertEquals(candidate.getFirst().getVoteCount(),5);
    assertEquals(candidate.get(1), winner.get(1));
    assertEquals(candidate.get(2), winner.get(2));
    assertEquals(candidate.get(3),winner.get(3));
    assertEquals(candidate.get(3).getVoteCount(),4);
    assertEquals(candidate.get(3).getName(),"Bannee");
    //Tied Candidates
    assertEquals(candidate.get(7), winner.get(4));
    assertEquals(candidate.get(4), winner.get(5));

    assertEquals(candidate.get(5),loser.getFirst());
    assertEquals(candidate.get(6), loser.getLast());



    ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputContent));


    mv.displayResults("Municipal Voting");

    String output = outputContent.toString();

    assertTrue(output.contains("Election Type: Municipal Voting"));
    assertTrue(output.contains("Number of Ballots: 8"));
    assertTrue(output.contains("Seats to be Filled: 6"));
    assertTrue(output.contains("Number of Candidates: 8"));
    assertTrue(output.contains("Winners: Greg with a vote percentage of: 62.5%, Sally with a vote percentage of: 50.0%, Amelia with a vote percentage of: 50.0%, Bannee with a vote percentage of: 50.0%, Matt with a vote percentage of: 37.5%, Josh with a vote percentage of: 37.5%\n"));
    assertTrue(output.contains("Losers: Zach with a vote percentage of: 37.5%, Tim with a vote percentage of: 37.5%"));

    System.setOut(System.out);


    File audit = mv.generateAudit("Municipal Voting");

    assertTrue(audit.exists()); // file should already be there

    assertTrue(audit.delete());
  }

  /**
   * Tests the complete election process,
   * verifying that winners are selected and an audit file is created.
   */
  @Test
  public void testStartElectionMV() {
    // Create candidates
    Candidate alice = new Candidate("Alice");
    Candidate bob = new Candidate("Bob");
    ArrayList<Candidate> candidates = new ArrayList<>();
    candidates.add(alice);
    candidates.add(bob);

    // Create ballots with vote counts (e.g., MV typically just picks 1st-choice)
    ArrayList<Ballot> ballots = new ArrayList<>();

    // Ballot 1: votes for Alice
    HashMap<Candidate, Integer> ranking1 = new HashMap<>();
    ranking1.put(alice, 1);
    ballots.add(new Ballot(1, ranking1));

    // Ballot 2: votes for Alice
    HashMap<Candidate, Integer> ranking2 = new HashMap<>();
    ranking2.put(alice, 1);
    ballots.add(new Ballot(2, ranking2));

    // Ballot 3: votes for Bob
    HashMap<Candidate, Integer> ranking3 = new HashMap<>();
    ranking3.put(bob, 1);
    ballots.add(new Ballot(3, ranking3));

    // Run MV election
    MV mv = new MV(1,8,8,candidate,ballots,"MVElectionAudit.txt");
    mv.startElection();

    // Check that a winner was selected (assuming 1 winner)
    assertEquals(1, mv.getWinners().size());

    // Check audit file was created
    File auditFile = new File("MVElectionAudit.txt");
    assertTrue(auditFile.exists(), "Audit file should be created");

    // Clean up
    auditFile.delete();
  }

}
