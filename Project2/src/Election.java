import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Provide an outline for methods that election types like STV and Plurality
 * must implement. Also gives protected attributes that both those election types can use.
 *
 * @author Zach Larsen
 */
abstract class Election{

    /** Number of seats being voted on in the election.*/
    protected int numSeats;

    /** Number of candidates in the election. */
    protected int numCandidates;

    /** Number of ballots cast.*/
    protected int numBallots;

    /** List of all the candidates*/
    protected ArrayList<Candidate> candidates = new ArrayList<>();

    /** List of candidates who are winners. */
    protected ArrayList<Candidate> winners = new ArrayList<>();

    /** List of candidates who are losers. */
    protected ArrayList<Candidate> losers = new ArrayList<>();

    /** List of all ballots cast. */
    protected ArrayList<Ballot> ballots = new ArrayList<>();

    /** Name of election input file. */
    protected String fileName;

    /** Abstract method for displaying final
     * election results. Realized in subclasses. */
    public abstract void displayResults(String electionName);

    /** Abstract method for generating
     * election audit file. Realized in subclasses.
     * @return A file pointer to the newly created audit file
     */
    public abstract File generateAudit(String electionName);

    /** Abstract method for driving the
     * election program. Realized in subclasses. */
    public abstract void startElection();

    /**
     * Get the number of seats open for an election
     * @return int numSeats
     */
    public int getNumSeats(){
        return numSeats;
    }

    /**
     * Set the number of seats
     * @param n int indicating the number of seats
     */
    public void setNumSeats(int n){
        numSeats = n;
    }

    /**
     * Add a candidate to the winners list
     * @param can A candidate running in the election
     */
    public void addWinner(Candidate can){
        winners.add(can);
    }

    /**
     * Get the winners list which contains candidates
     * @return winners ArrayList
     */
    public ArrayList<Candidate> getWinners(){
        return winners;
    }

    /**
     * Add a candidate to the losers list
     * @param can A candidate running in the election
     */
    public void addLoser(Candidate can){
        losers.add(can);
    }

    /**
     * Get the losers list which contains candidates
     * @return losers ArrayList
     */
    public ArrayList<Candidate> getLosers(){
        return losers;
    }

    /**
     * Get the list of candidates running in the election
     * @return List of candidates
     */
    public ArrayList<Candidate> getCandidates(){
        return candidates;
    }

    /**
     * Add a candidate to the List of candidates
     * @param candidate A candidate running in the election
     */
    public void setCandidate(Candidate candidate){
        candidates.add(candidate);
    }

    /**
     * Get the list of ballots cast in an election
     * @return List of ballots
     */
    public ArrayList<Ballot> getBallots(){
        return ballots;
    }

    /**
     * Add a ballot to the ballots list
     * @param ballot A ballot cast in an election
     */
    public void addBallots(Ballot ballot){
        ballots.add(ballot);
    }


}