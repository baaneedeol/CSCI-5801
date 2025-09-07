import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Test class for the STV (Single Transferable Vote) voting system.
 * This class tests various aspects of the STV algorithm including vote distribution,
 * handling of ties, droop quota calculation, and the generation of audit files.
 *
 * @author Amelia Lunning
 */
public class TestSTV {
    private ArrayList<Candidate> candidates;
    private ArrayList<Ballot> ballots;

  /**
   * Setup method to initialize test data before each test.
   * This method sets up a list of candidates and ballots with predefined rankings.
   */
    @BeforeEach
    public void setUpSTV() {
      candidates = new ArrayList<>();
      ballots = new ArrayList<>();

      Candidate can1 = new Candidate("A");
      Candidate can2 = new Candidate("B");
      Candidate can3 = new Candidate("C");
      Candidate can4 = new Candidate("D");
      Candidate can5 = new Candidate("E");
      candidates.add(can1);
      candidates.add(can2);
      candidates.add(can3);
      candidates.add(can4);
      candidates.add(can5);

      HashMap<Candidate,Integer> ballot1 = new HashMap<>();
      ballot1.put(can1, 1);
      ballot1.put(can2, 2);
      ballot1.put(can3, 3);
      ballot1.put(can4, 4);
      ballot1.put(can5, 5);

      HashMap<Candidate,Integer> ballot2 = new HashMap<>();
      ballot2.put(can1, 1);
      ballot2.put(can2, 2);
      ballot2.put(can3, 3);
      ballot2.put(can4, 4);
      ballot2.put(can5, 5);

      HashMap<Candidate,Integer> ballot3 = new HashMap<>();
      ballot3.put(can1, 1);
      ballot3.put(can2, 2);
      ballot3.put(can3, 3);
      ballot3.put(can4, 4);
      ballot3.put(can5, 5);

      HashMap<Candidate,Integer> ballot4 = new HashMap<>();
      ballot4.put(can1, 1);
      ballot4.put(can2, 2);
      ballot4.put(can3, 3);
      ballot4.put(can4, 4);
      ballot4.put(can5, 5);

      HashMap<Candidate,Integer> ballot5 = new HashMap<>();
      ballot5.put(can1, 1);
      ballot5.put(can2, 2);
      ballot5.put(can3, 3);
      ballot5.put(can4, 4);
      ballot5.put(can5, 5);

      HashMap<Candidate,Integer> ballot6 = new HashMap<>();
      ballot6.put(can1, 1);
      ballot6.put(can2, 2);
      ballot6.put(can3, 3);
      ballot6.put(can4, 4);
      ballot6.put(can5, 5);

      HashMap<Candidate,Integer> ballot7 = new HashMap<>();
      ballot7.put(can1, 2);
      ballot7.put(can2, 1);
      ballot7.put(can3, 3);
      ballot7.put(can4, 4);
      ballot7.put(can5, 5);

      HashMap<Candidate,Integer> ballot8 = new HashMap<>();
      ballot8.put(can1, 2);
      ballot8.put(can2, 1);
      ballot8.put(can3, 3);
      ballot8.put(can4, 4);
      ballot8.put(can5, 5);

      HashMap<Candidate,Integer> ballot9 = new HashMap<>();
      ballot9.put(can1, 2);
      ballot9.put(can2, 1);
      ballot9.put(can3, 3);
      ballot9.put(can4, 4);
      ballot9.put(can5, 5);

      HashMap<Candidate,Integer> ballot10 = new HashMap<>();
      ballot10.put(can1, 2);
      ballot10.put(can2, 1);
      ballot10.put(can3, 3);
      ballot10.put(can4, 4);
      ballot10.put(can5, 5);

      HashMap<Candidate,Integer> ballot11 = new HashMap<>();
      ballot11.put(can1, 2);
      ballot11.put(can2, 1);
      ballot11.put(can3, 3);
      ballot11.put(can4, 4);
      ballot11.put(can5, 5);

      HashMap<Candidate,Integer> ballot12 = new HashMap<>();
      ballot12.put(can1, 2);
      ballot12.put(can2, 1);
      ballot12.put(can3, 3);
      ballot12.put(can4, 4);
      ballot12.put(can5, 5);

      HashMap<Candidate,Integer> ballot13 = new HashMap<>();
      ballot13.put(can1, 2);
      ballot13.put(can2, 3);
      ballot13.put(can3, 1);
      ballot13.put(can4, 4);
      ballot13.put(can5, 5);

      HashMap<Candidate,Integer> ballot14 = new HashMap<>();
      ballot14.put(can1, 2);
      ballot14.put(can2, 3);
      ballot14.put(can3, 1);
      ballot14.put(can4, 4);
      ballot14.put(can5, 5);

      HashMap<Candidate,Integer> ballot15 = new HashMap<>();
      ballot15.put(can1, 2);
      ballot15.put(can2, 3);
      ballot15.put(can3, 1);
      ballot15.put(can4, 4);
      ballot15.put(can5, 5);

      HashMap<Candidate,Integer> ballot16 = new HashMap<>();
      ballot16.put(can1, 2);
      ballot16.put(can2, 3);
      ballot16.put(can3, 1);
      ballot16.put(can4, 4);
      ballot16.put(can5, 5);

      HashMap<Candidate,Integer> ballot17 = new HashMap<>();
      ballot17.put(can1, 2);
      ballot17.put(can2, 3);
      ballot17.put(can3, 1);
      ballot17.put(can4, 4);
      ballot17.put(can5, 5);

      HashMap<Candidate,Integer> ballot18 = new HashMap<>();
      ballot18.put(can1, 2);
      ballot18.put(can2, 3);
      ballot18.put(can3, 1);
      ballot18.put(can4, 4);
      ballot18.put(can5, 5);

      HashMap<Candidate,Integer> ballot19 = new HashMap<>();
      ballot19.put(can1, 2);
      ballot19.put(can2, 3);
      ballot19.put(can3, 4);
      ballot19.put(can4, 1);
      ballot19.put(can5, 5);

      HashMap<Candidate,Integer> ballot20 = new HashMap<>();
      ballot20.put(can1, 2);
      ballot20.put(can2, 3);
      ballot20.put(can3, 4);
      ballot20.put(can4, 1);
      ballot20.put(can5, 5);

      Ballot vote1 = new Ballot(1, ballot1);
      Ballot vote2 = new Ballot(2, ballot2);
      Ballot vote3 = new Ballot(3, ballot3);
      Ballot vote4 = new Ballot(4, ballot4);
      Ballot vote5 = new Ballot(5, ballot5);
      Ballot vote6 = new Ballot(6, ballot6);
      Ballot vote7 = new Ballot(7, ballot7);
      Ballot vote8 = new Ballot(8, ballot8);
      Ballot vote9 = new Ballot(9, ballot9);
      Ballot vote10 = new Ballot(10, ballot10);
      Ballot vote11 = new Ballot(11, ballot11);
      Ballot vote12 = new Ballot(12, ballot12);
      Ballot vote13 = new Ballot(13, ballot13);
      Ballot vote14 = new Ballot(14, ballot14);
      Ballot vote15 = new Ballot(15, ballot15);
      Ballot vote16 = new Ballot(16, ballot16);
      Ballot vote17 = new Ballot(17, ballot17);
      Ballot vote18 = new Ballot(18, ballot18);
      Ballot vote19 = new Ballot(19, ballot19);
      Ballot vote20 = new Ballot(20, ballot20);
      ballots.add(vote1);
      ballots.add(vote2);
      ballots.add(vote3);
      ballots.add(vote4);
      ballots.add(vote5);
      ballots.add(vote6);
      ballots.add(vote7);
      ballots.add(vote8);
      ballots.add(vote9);
      ballots.add(vote10);
      ballots.add(vote11);
      ballots.add(vote12);
      ballots.add(vote13);
      ballots.add(vote14);
      ballots.add(vote15);
      ballots.add(vote16);
      ballots.add(vote17);
      ballots.add(vote18);
      ballots.add(vote19);
      ballots.add(vote20);
    }

  /**
   * Test the calculation of the droop quota.
   * This test checks if the correct droop quota is calculated for the given number of voters and seats.
   */
    @Test
    public void testDroop() {
      STV stv = new STV(false, 3, 5, 20, candidates, ballots, null);
      int droop = stv.calculateDroop(20, 3);
      assertEquals(6, droop);
    }

  /**
   * Test the vote distribution process after calculating the droop quota.
   * This test verifies if the votes are correctly distributed among candidates and checks the winners and losers.
   */
    @Test
    public void testDistributeVotes() {
      STV stv = new STV(false, 3, 5, 20, candidates, ballots, null);
      int droop = stv.calculateDroop(20, 3);
      stv.setDroopQuota(droop);
      stv.distributeVotes();

      assertEquals(6, candidates.get(0).getVoteCount());
      assertEquals(6, candidates.get(1).getVoteCount());
      assertEquals(6, candidates.get(2).getVoteCount());
      assertEquals(2, candidates.get(3).getVoteCount());
      assertEquals(0, candidates.get(4).getVoteCount());

      ArrayList<Candidate> winners= stv.getWinners();
      ArrayList<Candidate> losers = stv.getLosers();
      assertEquals(candidates.get(0), winners.get(0));
      assertEquals(candidates.get(1), winners.get(1));
      assertEquals(candidates.get(2), winners.get(2));
      assertEquals(candidates.get(3), losers.get(0));
      assertEquals(candidates.get(4), losers.get(1));

    }

  /**
   * Test the vote distribution for 2 seats.
   * This test verifies if the votes are correctly distributed among candidates and checks the winners and losers.
   */
  @Test
  public void testDistributeVotes2() {
    STV stv = new STV(false, 2, 5, 20, candidates, ballots, null);
    int droop = stv.calculateDroop(20, 2);
    stv.setDroopQuota(droop);
    stv.distributeVotes();

    assertEquals(7, candidates.get(0).getVoteCount());
    assertEquals(7, candidates.get(1).getVoteCount());
    assertEquals(6, candidates.get(2).getVoteCount());
    assertEquals(2, candidates.get(3).getVoteCount());
    assertEquals(0, candidates.get(4).getVoteCount());

    ArrayList<Candidate> winners= stv.getWinners();
    ArrayList<Candidate> losers = stv.getLosers();
    assertEquals(candidates.get(0), winners.get(0));
    assertEquals(candidates.get(1), winners.get(1));
    assertEquals(candidates.get(2), losers.get(2));
    assertEquals(candidates.get(3), losers.get(1));
    assertEquals(candidates.get(4), losers.get(0));

  }

  /**
   * Test the vote distribution for 1 seat.
   * This test verifies if the votes are correctly distributed among candidates and checks the winners and losers.
   */
  @Test
  public void testDistributeVotes3() {
    STV stv = new STV(false, 1, 5, 20, candidates, ballots, null);
    int droop = stv.calculateDroop(20, 1);
    stv.setDroopQuota(droop);
    stv.distributeVotes();

    assertEquals(8, candidates.get(0).getVoteCount());
    assertEquals(11, candidates.get(1).getVoteCount());
    assertEquals(6, candidates.get(2).getVoteCount());
    assertEquals(2, candidates.get(3).getVoteCount());
    assertEquals(0, candidates.get(4).getVoteCount());

    ArrayList<Candidate> winners= stv.getWinners();
    ArrayList<Candidate> losers = stv.getLosers();
    assertEquals(candidates.get(0), losers.get(3));
    assertEquals(candidates.get(1), winners.get(0));
    assertEquals(candidates.get(2), losers.get(2));
    assertEquals(candidates.get(3), losers.get(1));
    assertEquals(candidates.get(4), losers.get(0));

  }

  /**
   * Test the vote distribution for 5 seats.
   * This test verifies if the votes are correctly distributed among candidates and checks the winners and losers.
   */
  @Test
  public void testDistributeVotes4() {
    STV stv = new STV(false, 5, 5, 20, candidates, ballots, null);
    int droop = stv.calculateDroop(20, 5);
    stv.setDroopQuota(droop);
    stv.distributeVotes();

    ArrayList<Candidate> winners= stv.getWinners();
    assertEquals(candidates.get(0), winners.get(0));
    assertEquals(candidates.get(1), winners.get(1));
    assertEquals(candidates.get(2), winners.get(2));
    assertEquals(candidates.get(3), winners.get(3));
    assertEquals(candidates.get(4), winners.get(4));

  }

  /**
   * Test the candidate elimination for 2 candidates with different vote counts.
   * This test verifies if the candidates are eliminated in the correct order and checks losers.
   */
  @Test
  public void testEliminateCandidate() {
    ArrayList<Candidate> eliminatedCandidates = new ArrayList<>();
    Candidate can1 = new Candidate("A");
    Candidate can2 = new Candidate("B");
    eliminatedCandidates.add(can1);
    eliminatedCandidates.add(can2);
    can1.setVoteCount(15);
    can2.setVoteCount(5);

    STV stv = new STV(false, 0, 2, 20, eliminatedCandidates, ballots, null);
    assertEquals(eliminatedCandidates.get(1), stv.eliminateCandidate());
    assertEquals(eliminatedCandidates.get(0), stv.eliminateCandidate());

  }

  /**
   * Test that the ballot's highest ranked active candidate is selected in case where the provided rank has an active candidate.
   */
  @Test
  public void testCheckBallotCandidate() {
    ArrayList<Candidate> testCandidates = new ArrayList<>();
    ArrayList<Ballot> testBallots = new ArrayList<>();
    Candidate can1 = new Candidate("A");
    Candidate can2 = new Candidate("B");
    testCandidates.add(can1);
    testCandidates.add(can2);

    HashMap<Candidate,Integer> ballot1 = new HashMap<>();
    ballot1.put(can1, 1);
    ballot1.put(can2, 2);
    Ballot vote1 = new Ballot(1, ballot1);
    testBallots.add(vote1);

    STV stv = new STV(false, 0, 2, 20, testCandidates, testBallots, null);
    assertEquals(testCandidates.get(0), stv.checkBallotCandidate(vote1, 1));
  }

  /**
   * Test that the ballot's highest ranked active candidate is selected in case where the provided rank doesn't have an active candidate.
   */
  @Test
  public void testCheckBallotCandidate2() {
    ArrayList<Candidate> testCandidates = new ArrayList<>();
    ArrayList<Ballot> testBallots = new ArrayList<>();
    Candidate can1 = new Candidate("A");
    Candidate can2 = new Candidate("B");
    testCandidates.add(can1);
    testCandidates.add(can2);
    can1.setStatus("eliminated");

    HashMap<Candidate,Integer> ballot1 = new HashMap<>();
    ballot1.put(can1, 1);
    ballot1.put(can2, 2);
    Ballot vote1 = new Ballot(1, ballot1);
    testBallots.add(vote1);

    STV stv = new STV(false, 0, 2, 20, testCandidates, testBallots, null);
    assertEquals(testCandidates.get(1), stv.checkBallotCandidate(vote1, 1));
  }

  /**
   * Test that the ballot's highest ranked active candidate is selected in case where the provided ballot doesn't have any active candidates.
   */
  @Test
  public void testCheckBallotCandidate3() {
    ArrayList<Candidate> testCandidates = new ArrayList<>();
    ArrayList<Ballot> testBallots = new ArrayList<>();
    Candidate can1 = new Candidate("A");
    Candidate can2 = new Candidate("B");
    testCandidates.add(can1);
    testCandidates.add(can2);
    can1.setStatus("eliminated");
    can2.setStatus("eliminated");

    HashMap<Candidate,Integer> ballot1 = new HashMap<>();
    ballot1.put(can1, 1);
    ballot1.put(can2, 2);
    Ballot vote1 = new Ballot(1, ballot1);
    testBallots.add(vote1);

    STV stv = new STV(false, 0, 2, 20, testCandidates, testBallots, null);
    assertEquals(null, stv.checkBallotCandidate(vote1, 1));
  }

  /**
   * Test the tie-breaking mechanism in case of an equal vote count.
   * This test checks whether ties are resolved correctly between candidates.
   */
    @Test
    public void testSettleTies() {
      ArrayList<Candidate> tiedCandidates = new ArrayList<>();
      ArrayList<Ballot> tiedBallots = new ArrayList<>();

      Candidate can1 = new Candidate("A");
      Candidate can2 = new Candidate("B");
      tiedCandidates.add(can1);
      tiedCandidates.add(can2);

      HashMap<Candidate,Integer> ballot1 = new HashMap<>();
      ballot1.put(can1, 1);
      ballot1.put(can2, 2);

      HashMap<Candidate,Integer> ballot2 = new HashMap<>();
      ballot2.put(can1, 1);
      ballot2.put(can2, 2);

      HashMap<Candidate,Integer> ballot3 = new HashMap<>();
      ballot3.put(can1, 2);
      ballot3.put(can2, 1);

      HashMap<Candidate,Integer> ballot4 = new HashMap<>();
      ballot4.put(can1, 2);
      ballot4.put(can2, 1);

      Ballot vote1 = new Ballot(1, ballot1);
      Ballot vote2 = new Ballot(2, ballot2);
      Ballot vote3 = new Ballot(3, ballot3);
      Ballot vote4 = new Ballot(4, ballot4);
      tiedBallots.add(vote1);
      tiedBallots.add(vote2);
      tiedBallots.add(vote3);
      tiedBallots.add(vote4);

      STV stv = new STV(false, 1, 2, 4, tiedCandidates, tiedBallots, "testSTV2.txt");
      int droop = stv.calculateDroop(4, 1);
      stv.setDroopQuota(droop);
      stv.distributeVotes();

      assertEquals(3, tiedCandidates.get(0).getVoteCount());
      assertEquals(2, tiedCandidates.get(1).getVoteCount());

      ArrayList<Candidate> winners= stv.getWinners();
      ArrayList<Candidate> losers = stv.getLosers();
      assertEquals(tiedCandidates.get(0), winners.get(0));
      assertEquals(tiedCandidates.get(1), losers.get(0));
    }

  /**
   * Test the generation of an audit file after the voting process.
   * This test checks if the system correctly generates an audit file for the election process.
   */
    @Test
    public void testGenerateAudit() {
      STV stv = new STV(false, 3, 5, 20, candidates, ballots, "testSTV1.txt");
      int droop = stv.calculateDroop(20, 3);
      stv.setDroopQuota(droop);
      stv.distributeVotes();
      File auditFilePointer = stv.generateAudit("STV");

      assertTrue(auditFilePointer.exists());
      assertTrue(auditFilePointer.delete());
    }

  /**
   * Test the shuffling of ballots before distributing votes.
   * This test ensures that the ballots are shuffled correctly before the STV process begins.
   */
    @Test
    public void testShuffleBallots() {
      ArrayList<Ballot> ballotsCopy = new ArrayList<>();
      for (Ballot ballot : ballots) {
        ballotsCopy.add(ballot);
      }
      STV stv = new STV(false, 3, 5, 20, candidates, ballots, null);
      stv.shuffleBallots();
      int numSameBallots = 0;
      for (int i = 0; i < ballots.size(); i++) {
        if (ballots.get(i) == ballotsCopy.get(i)) {
          numSameBallots += 1;
        }
      }
      assertTrue(numSameBallots != ballots.size());
    }

    /**
    * Tests the displayResults method, ensuring the output includes correct election details and results.
    */
    @Test
    public void testDisplayResults() {
      ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
      PrintStream originalOut = System.out;
      System.setOut(new PrintStream(outputContent));

      STV stv = new STV(false, 3, 5, 20, candidates, ballots, null);
      int droop = stv.calculateDroop(20, 3);
      stv.setDroopQuota(droop);
      stv.distributeVotes();
      stv.displayResults("STV");

      String output = outputContent.toString();

      assertTrue(output.contains("Election Type: STV"));
      assertTrue(output.contains("Number of Ballots: 20"));
      assertTrue(output.contains("Seats to be Filled: 3"));
      assertTrue(output.contains("Number of Candidates: 5"));
      assertTrue(output.contains("Winners: A, B, C\n"));
      assertTrue(output.contains("Losers: D, E"));

      System.setOut(System.out);
    }

    /**
    * Tests the displayResults method for when there are no losers in the election.
    */
    @Test
    public void testDisplayResults2() {
      ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
      PrintStream originalOut = System.out;
      System.setOut(new PrintStream(outputContent));

      STV stv = new STV(false, 5, 5, 20, candidates, ballots, null);
      int droop = stv.calculateDroop(20, 5);
      stv.setDroopQuota(droop);
      stv.distributeVotes();
      stv.displayResults("STV");

      String output = outputContent.toString();

      assertTrue(output.contains("Election Type: STV"));
      assertTrue(output.contains("Number of Ballots: 20"));
      assertTrue(output.contains("Seats to be Filled: 5"));
      assertTrue(output.contains("Number of Candidates: 5"));
      assertTrue(output.contains("Winners: A, B, C, D, E\n"));
      System.setOut(System.out);
    }

  /**
   * Tests the getRankCandidate method to verify it correctly returns the candidate
   * at the specified rank in a ballot's rankings, or null if no candidate exists at that rank.
   */
  @Test
  public void testGetRankCandidate() {
    ArrayList<Candidate> testCandidates = new ArrayList<>();
    ArrayList<Ballot> testBallots = new ArrayList<>();
    Candidate can1 = new Candidate("A");
    Candidate can2 = new Candidate("B");
    testCandidates.add(can1);
    testCandidates.add(can2);

    HashMap<Candidate, Integer> ballot1 = new HashMap<>();
    ballot1.put(can1, 1);
    ballot1.put(can2, 2);
    Ballot vote1 = new Ballot(1, ballot1);
    testBallots.add(vote1);

    STV stv = new STV(false, 0, 2, 1, testCandidates, testBallots, null);
    assertEquals(can1, stv.getRankCandidate(vote1, 1));
    assertEquals(can2, stv.getRankCandidate(vote1, 2));
    assertNull(stv.getRankCandidate(vote1, 3)); // No candidate at rank 3
  }

  /**
   * Tests the removeBallot method to ensure it correctly removes a ballot
   * from the election's ballot list.
   */
  @Test
  public void testRemoveBallot() {
    ArrayList<Ballot> testBallots = new ArrayList<>();
    Ballot vote1 = new Ballot(1, new HashMap<>());
    testBallots.add(vote1);

    STV stv = new STV(false, 0, 0, 1, new ArrayList<>(), testBallots, null);
    assertEquals(1, stv.ballots.size());
    stv.removeBallot(vote1);
    assertEquals(0, stv.ballots.size());
  }

  /**
   * Tests the complete election process with ballot shuffling enabled,
   * verifying that winners are selected and an audit file is created.
   */
  @Test
  public void testStartElectionWithShuffle() {
    STV stv = new STV(true, 2, 5, 20, candidates, ballots, "ShuffledElectionAudit.txt");
    stv.startElection();

    // Verify winners were selected
    assertEquals(2, stv.getWinners().size());

    // Verify audit file was created
    File auditFile = new File("ShuffledElectionAudit.txt");
    assertTrue(auditFile.exists());
    auditFile.delete(); // Clean up
  }

  /**
   * Tests the complete election process with ballot shuffling disabled,
   * verifying that winners are selected and an audit file is created.
   */
  @Test
  public void testStartElectionWithNoShuffle() {
    // Set shuffleOff to false for no shuffling
    STV stv = new STV(false, 2, 5, 20, candidates, ballots, "NoShuffleElectionAudit.txt");
    stv.startElection();

    // Verify winners were selected
    assertEquals(2, stv.getWinners().size());

    // Verify audit file was created
    File auditFile = new File("NoShuffleElectionAudit.txt");
    assertTrue(auditFile.exists());
    auditFile.delete(); // Clean up
  }
}
