import java.util.ArrayList;

/**
 * Represents a candidate in an election, tracking their name, status,
 * assigned ballots, and vote count.
 *
 * @author Baanee Singh
 */
public class Candidate {

  /** Name of the candidate */
  private String name;

  /** Candidate's status in the election.
   * Either active or eliminated depending on
   * if they've been removed from the election. */
  private String status;

  /** List of all the ballots that have
   * been awarded to a candidate. */
  private ArrayList<Ballot> ballots;

  /** Number of votes the candidate has been awarded*/
  private int voteCount;

  /**
   * Constructs a Candidate object with a specified name.
   * The candidate's status is initialized as "active",
   * and the vote count is set to zero.
   *
   * @param name The name of the candidate.
   */
  public Candidate(String name){
    this.name = name;
    this.status = "active";
    this.ballots = new ArrayList<>();
    this.voteCount = 0;
  }

  /**
   * Retrieves the name of the candidate.
   *
   * @return The candidate's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Updates the candidate's name.
   *
   * @param newName The new name of the candidate.
   */
  public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Retrieves the current status of the candidate.
   *
   * @return The candidate's status.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Updates the candidate's status.
   *
   * @param newStatus The new status of the candidate.
   */
  public void setStatus(String newStatus) {
    this.status = newStatus;
  }

  /**
   * Retrieves the list of ballots assigned to the candidate.
   *
   * @return An ArrayList of Ballot objects.
   */
  public ArrayList<Ballot> getBallots() {
    return ballots;
  }

  /**
   * Adds a new ballot to the candidate's list of ballots.
   *
   * @param newBallot The ballot to be added.
   */
  public void addBallot(Ballot newBallot) {
    this.ballots.add(newBallot);
  }

  /**
   * Retrieves the current vote count of the candidate.
   *
   * @return The number of votes the candidate has received.
   */
  public int getVoteCount() {
    return voteCount;
  }

  /**
   * Updates the vote count of the candidate.
   *
   * @param newVoteCount The new vote count.
   */
  public void setVoteCount(int newVoteCount) {
    this.voteCount = newVoteCount;
  }

  /**
   * Increments the vote count by one.
   */
  public void incrementVoteCount() {
    this.voteCount++;
  }
}
