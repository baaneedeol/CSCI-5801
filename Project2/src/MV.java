import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Creates and execute an MV election. This includes adding votes, determining the winners
 * and losers and displaying the results as well as generating an audit file.
 *
 * @author Zach Larsen
 */

public class MV extends DirectSelection{

  /**
   * Constructor for MV class.
   * @param numSeats Number of seats available in the Election
   * @param numCandidates Number of candidates running in the Election
   * @param numBallots Number of ballots cast in the Election
   * @param candidates List of all candidates running in the Election
   * @param ballots List of all ballots cast in the Election
   * @param fileName A String specifying the name of the audit file
   */
  public MV(int numSeats, int numCandidates, int numBallots, ArrayList<Candidate> candidates, ArrayList<Ballot> ballots, String fileName){
    this.numSeats = numSeats;
    this.numBallots = numBallots;
    this.numCandidates = numCandidates;
    this.candidates = candidates;
    this.ballots = ballots;
    this.fileName = fileName;
  }

  /**
   * Calls all helper functions to run a Plurality election from start to finish.
   */
  public void startElection(){
    tabulateVotes();
    determine_Winner_Loser();
    displayResults("Municipal Voting");
    generateAudit("Municipal Voting");
  }

  /**
   * Go through all cast ballots and increment a Candidates vote count by
   * one for every vote they receive in a ballot.
   */
  public void tabulateVotes(){
    //Loop through all ballots in the list
    for (Ballot ballot : ballots){
      HashMap<Candidate, Integer> ranking = ballot.getRankings();

      Candidate personVotedFor = null;

      //Loop through all candidate,int pairs in the HashMap stored in the ballot
      for (Map.Entry<Candidate,Integer> entry : ranking.entrySet()){
        Integer value = entry.getValue();
        //If the value is 1 that means the candidate received the vote
        if(value == 1){
          personVotedFor = entry.getKey();
        }

        //Assign a candidate a vote by incrementing the vote count and assign that ballot to them
        if(personVotedFor != null){
          personVotedFor.incrementVoteCount();
          personVotedFor.addBallot(ballot);
          personVotedFor = null;
        }
      }
    }
  }

}
