import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

/**
 * Unit tests for the Ballot class.
 * These tests ensure that the Ballot constructor, getters, setters, and ranking logic work correctly.
 *
 * @author Baanee Singh
 */
public class TestBallot {

  /**
   * Test the constructor and getters of the Ballot class.
   * This test verifies that the Ballot object is correctly initialized with the provided ballot ID and rankings.
   */
  @Test
  public void testBallotConstructorAndGetters() {
    // Create a sample candidate ranking
    HashMap<Candidate, Integer> rankings = new HashMap<>();
    Candidate candidate1 = new Candidate("Alice");
    Candidate candidate2 = new Candidate("Bob");
    rankings.put(candidate1, 1);
    rankings.put(candidate2, 2);

    // Create a ballot object
    Ballot ballot = new Ballot(123, rankings);

    // Verify the ballot ID
    assertEquals(123, ballot.getBallotID());

    // Verify the rankings
    assertEquals(1, ballot.getRankings().get(candidate1));
    assertEquals(2, ballot.getRankings().get(candidate2));
  }

  /**
   * Test the setter method for the ballot ID.
   * This test ensures that the ballot ID can be updated after the object is created.
   */
  @Test
  public void testSetBallotID() {
    // Create an initial ballot
    Ballot ballot = new Ballot(123, new HashMap<>());

    // Change the ballot ID
    ballot.setBallotID(456);

    // Verify that the ballot ID was updated
    assertEquals(456, ballot.getBallotID());
  }

  /**
   * Test the setter method for the rankings.
   * This test ensures that the rankings can be updated after the object is created.
   * It also verifies that the old rankings are correctly replaced.
   */
  @Test
  public void testSetRankings() {
    // Create a sample ballot with initial rankings
    HashMap<Candidate, Integer> rankings = new HashMap<>();
    Candidate candidate1 = new Candidate("Alice");
    rankings.put(candidate1, 1);
    Ballot ballot = new Ballot(123, rankings);

    // Create new rankings to set
    HashMap<Candidate, Integer> newRankings = new HashMap<>();
    Candidate candidate2 = new Candidate("Bob");
    newRankings.put(candidate2, 1);

    // Set new rankings
    ballot.setRankings(newRankings);

    // Verify that the rankings were updated
    assertEquals(1, ballot.getRankings().get(candidate2));
    assertNull(ballot.getRankings().get(candidate1)); // Alice should no longer be in the rankings
  }
}
