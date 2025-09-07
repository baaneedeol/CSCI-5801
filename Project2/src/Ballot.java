import java.util.HashMap;

/**
 * Represents a ballot used in an election, containing a unique ballot ID
 * and a set of rankings for candidates.
 *
 * @author Baanee Singh
 */
public class Ballot {

  /** Unique value given to every ballot */
  private int ballotID;
  /** Representation of a ranked ballot. Will have 0s where
   * there is no ranking provided. */
  private HashMap<Candidate, Integer> rankings;

  /**
   * Constructs a Ballot object with a specified ID and candidate rankings.
   *
   * @param ballotID The unique identifier for the ballot.
   * @param rankings A HashMap containing candidates as keys and their
   *                 corresponding rankings as values.
   */
  public Ballot(int ballotID, HashMap<Candidate, Integer> rankings){
    this.ballotID = ballotID;
    this.rankings = rankings;
  }

  /**
   * Retrieves the unique identifier of the ballot.
   *
   * @return The ballot ID.
   */
  public int getBallotID() {
    return ballotID;
  }

  /**
   * Updates the ballot ID.
   *
   * @param ballotID The new ballot ID.
   */
  public void setBallotID(int ballotID) {
    this.ballotID = ballotID;
  }

  /**
   * Retrieves the rankings of candidates.
   *
   * @return A HashMap containing candidates and their corresponding rankings.
   */
  public HashMap<Candidate, Integer> getRankings() {
    return rankings;
  }

  /**
   * Updates the rankings of candidates.
   *
   * @param rankings A HashMap containing updated candidate rankings.
   */
  public void setRankings(HashMap<Candidate, Integer> rankings) {
    this.rankings = rankings;
  }
}