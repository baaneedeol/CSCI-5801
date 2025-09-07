import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Unit tests for the Candidate class.
 * These tests verify the behavior of the Candidate class, including its constructor, methods,
 * and vote count functionality.
 *
 * @author Baanee Singh
 */
public class TestCandidate {

  private Candidate candidate;
  private Ballot ballot;

  /**
   * Sets up the necessary objects for testing before each test method is run.
   * This method initializes the Candidate and Ballot objects to ensure tests are conducted on a known setup.
   */
  @BeforeEach
  public void setUp() {
    candidate = new Candidate("John Doe");

    // Create a HashMap for the Ballot
    HashMap<Candidate, Integer> candidateRanking = new HashMap<>();
    candidateRanking.put(candidate, 1);

    // Instantiate Ballot with required parameters
    ballot = new Ballot(1, candidateRanking);
  }

  /**
   * Tests the constructor of the Candidate class and checks the default values.
   * It verifies that the candidate is correctly initialized with the given name and default values for status and vote count.
   */
  @Test
  public void testConstructor() {
    // Testing the constructor and default values
    assertEquals("John Doe", candidate.getName());
    assertEquals("active", candidate.getStatus());
    assertEquals(0, candidate.getVoteCount());
    assertNotNull(candidate.getBallots());
  }

  /**
   * Tests the setName method of the Candidate class.
   * This test ensures that the name of the candidate can be correctly updated.
   */
  @Test
  public void testSetName() {
    // Testing setName method
    candidate.setName("Jane Doe");
    assertEquals("Jane Doe", candidate.getName());
  }

  /**
   * Tests the setStatus method of the Candidate class.
   * This test verifies that the candidate's status can be updated correctly.
   */
  @Test
  public void testSetStatus() {
    // Testing setStatus method
    candidate.setStatus("inactive");
    assertEquals("inactive", candidate.getStatus());
  }

  /**
   * Tests the addBallot method of the Candidate class.
   * This test ensures that ballots can be added to a candidate and the ballot list is updated correctly.
   */
  @Test
  public void testAddBallot() {
    // Testing adding ballots
    candidate.addBallot(ballot);
    assertEquals(1, candidate.getBallots().size());
  }

  /**
   * Tests the getVoteCount method of the Candidate class.
   * This test verifies that the initial vote count is correct (0 by default).
   */
  @Test
  public void testGetVoteCount() {
    // Testing the vote count getter
    assertEquals(0, candidate.getVoteCount());
  }

  /**
   * Tests the setVoteCount method of the Candidate class.
   * This test ensures that the vote count can be updated correctly.
   */
  @Test
  public void testSetVoteCount() {
    // Testing the setVoteCount method
    candidate.setVoteCount(10);
    assertEquals(10, candidate.getVoteCount());
  }

  /**
   * Tests the incrementVoteCount method of the Candidate class.
   * This test verifies that the vote count is incremented correctly.
   */
  @Test
  public void testIncrementVoteCount() {
    // Testing the incrementVoteCount method
    candidate.incrementVoteCount();
    assertEquals(1, candidate.getVoteCount());
    candidate.incrementVoteCount();
    assertEquals(2, candidate.getVoteCount());
  }

  /**
   * Tests adding multiple ballots to the Candidate.
   * This test ensures that the addBallot method works correctly when adding multiple ballots.
   */
  @Test
  public void testAddBallotMultiple() {
    // Testing adding multiple ballots
    candidate.addBallot(ballot);
    candidate.addBallot(ballot);
    assertEquals(2, candidate.getBallots().size());
  }
}
