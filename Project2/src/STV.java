import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

/**
 * Creates and executes an STV election. Vote tabulation, calculate droop, remove ballots
 * and candidates, display results, and generate audit
 * @author Amelia Lunning
 */
public class STV extends Election {

    /** Number of ballots in which a candidate must
     * win to be considered a winner. */
    private int droopQuota;

    /** Hashmap of each candidate first vote that was allocated to them.  */
    private HashMap<Candidate, ArrayList<Integer>> candidateFirstVote = new HashMap<Candidate, ArrayList<Integer>>();

    /** Used for testing. Disables randomization of ballots.  */
    private boolean shuffleOn = false;


    /**
     * Constructor for STV
     * @param shuffle: toggle for shuffle on/off
     * @param numSeats: number of seats to fill
     * @param numCandidates: number of candidates
     * @param numBallots: number of ballots
     * @param candidates: list of candidate objects
     * @param ballots: list of ballot objects
     * @param fileName: name of the audit file to be created
     */
    public STV(boolean shuffle, int numSeats, int numCandidates, int numBallots, ArrayList<Candidate> candidates, ArrayList<Ballot> ballots, String fileName) {
        shuffleOn = shuffle; 
        this.numSeats = numSeats;
        this.numBallots = numBallots;
        this.numCandidates = numCandidates;
        this.candidates = candidates;
        this.ballots = ballots;
        this.fileName = fileName;
    }

    /**
     * Calculates the droop quota for the STV election.
     *
     * @param numBallots The number of ballots cast in the election.
     * @param numSeats   The number of seats to be filled in the election.
     * @return The droop quota used to determine the minimum number of votes required to win.
     */
    public int calculateDroop(int numBallots, int numSeats) {
        int quota = (int)Math.floor(numBallots / (numSeats + 1)) + 1;
        return quota;
    }

    /**
     * Sets the droop quota for the election.
     *
     * @param droop The droop quota to be set.
     */
    public void setDroopQuota(int droop) {
        droopQuota = droop;
    }

    /**
     * Retrieves the current droop quota.
     *
     * @return The current droop quota.
     */
    public int getDroopQuota() {
        return droopQuota;
    }

    /**
     * Shuffles the list of ballots randomly.
     */
    public void shuffleBallots() {
        Random rand = new Random();
        int max = ballots.size() - 1;
        for (int i = 0; i < max; i++) {
            int randomInt = rand.nextInt(max - i + 1) + i;
            Collections.swap(ballots, i, randomInt);
        }
    }

    /**
     * Removes the specified ballot from the list of ballots.
     *
     * @param ballot The ballot to be removed.
     */
    public void removeBallot(Ballot ballot) {
        ballots.remove(ballot);
    }

    /**
     * Distributes votes across candidates, eliminating candidates with the least votes
     * and re-tabulating ballots until the required number of seats is filled.
     */
    public void distributeVotes() {
        // if equal number or more of seats than candidates, all candidates win
        if (numSeats >= numCandidates) {
            for (Candidate candidate : candidates) {
                addWinner(candidate);
                candidate.setStatus("eliminated");
            }
        }
        int ballotCount = 0;
        int currRank = 1;
        while (winners.size() < numSeats) {
            boolean winnerAdded = false;
            while (ballots.size() != 0) {
                Ballot ballot = ballots.getFirst();

                // get ballot's candidate and check if it's active, eliminate ballot if no next candidate
                Candidate currCandidate = checkBallotCandidate(ballot, currRank);
                // if ballot isn't removed, get that ballot's vote and apply it to their candidate
                if (currCandidate != null) {
                    currCandidate.addBallot(ballot);
                    currCandidate.incrementVoteCount();
                    removeBallot(ballot);
                    if (currCandidate.getVoteCount() == droopQuota && winners.size() < numSeats) {
                        addWinner(currCandidate);
                        currCandidate.setStatus("eliminated");
                        winnerAdded = true;
                    }
                    if (currCandidate.getVoteCount() == 1) {
                        // record candidate's first vote and that vote's rank
                        ArrayList<Integer> candidateVoteIndexRank = new ArrayList<Integer>();
                        candidateVoteIndexRank.add(currRank);
                        candidateVoteIndexRank.add(ballotCount);
                        candidateFirstVote.put(currCandidate, candidateVoteIndexRank);
                    }
                }
                ballotCount++;
            }
            // if no winners this round, then eliminate a candidate
            if (winnerAdded == false && winners.size() < numSeats) {
                Candidate eliminatedCandidate = eliminateCandidate();

                // add eliminated candidate's ballots back to be re-tabulated
                boolean addedBallots = false;
                for (Ballot ballot : eliminatedCandidate.getBallots()) {
                    addedBallots = true;
                    ballots.add(ballot);
                }

                // if ballots are added back, increment current rank (rank looked at for each ballot)
                if (addedBallots) {
                    currRank++;
                }
            }
        }
        // add any remaining candidates to losers list
        for (Candidate candidate : candidates) {
            if (candidate.getStatus() == "active") {
                addLoser(candidate);
                candidate.setStatus("eliminated");
            }
        }
        return;
    }

    /**
     * Eliminates candidate with least number of votes.
     *
     * @return The candidate to be eliminated.
     */
    public Candidate eliminateCandidate() {
        Candidate eliminatedCandidate;
        ArrayList<Candidate> eliminatedCandidates = new ArrayList<Candidate>();
        int minVoteCount = Integer.MAX_VALUE;

        // find candidate(s) with least number of votes and add them to array
        for (Candidate candidate : candidates) {
            if (candidate.getVoteCount() < minVoteCount && candidate.getStatus() == "active") {
                minVoteCount = candidate.getVoteCount();
                eliminatedCandidates.clear();
            }
            if (candidate.getVoteCount() == minVoteCount) {
                eliminatedCandidates.add(candidate);
            }
        }
        // settle ties if more than 1 candidate has same least number of votes
        if (eliminatedCandidates.size() > 1) {
            eliminatedCandidate = settleTies(eliminatedCandidates);
        }
        else {
            eliminatedCandidate = eliminatedCandidates.getFirst();
        }
        addLoser(eliminatedCandidate);
        eliminatedCandidate.setStatus("eliminated");

        return eliminatedCandidate;
    }

    /**
     * Retrieves ballot's highest ranked active candidate or eliminates ballot if no such candidate.
     *
     * @param ballot The ballot whose ranked candidates are being checked.
     * @param rank   The rank to be checked.
     * @return The active candidate at the highest possible rank or null if so such candidate.
     */
    public Candidate checkBallotCandidate(Ballot ballot, int rank) {
        Candidate nextCandidate = null;
        Candidate currCandidate = getRankCandidate(ballot, rank);
        while (currCandidate != null) {
            if (currCandidate.getStatus() == "active") {
                nextCandidate = currCandidate;
                break;
            }
            else {
                rank++;
                currCandidate = getRankCandidate(ballot, rank);
            }
        }
        // if no active candidates left on ballot, eliminate that ballot
        removeBallot(ballot);
        return nextCandidate;
    }

    /**
     * Retrieves the candidate ranked at the given rank for the specified ballot.
     *
     * @param ballot The ballot whose ranked candidates are being checked.
     * @param rank   The rank to be checked.
     * @return The candidate at the specified rank.
     */
    public Candidate getRankCandidate(Ballot ballot, int rank) {
        HashMap<Candidate, Integer> ranking = ballot.getRankings();
            
        Candidate currCandidate = null;
        for (Map.Entry<Candidate,Integer> entry : ranking.entrySet()){
            int value = entry.getValue();
            if (value == rank){
                currCandidate = entry.getKey();
                break;
            }
        }
        return currCandidate;
    }

    /**
     * Resolves a tie between candidates by prioritizing the candidate with the highest rank
     * on their first vote, and then by the ballot index.
     *
     * @param tiedCandidates A list of candidates who are tied.
     * @return The candidate who is to be eliminated from the tie.
     */
    public Candidate settleTies(ArrayList<Candidate> tiedCandidates) {
        // the loser is the candidate who got their first vote last
        // prioritize candidate's rank, then candidate's ballot index
        int maxRank = -1;
        int maxIndex = -1;
        Candidate tieLoser = null;
        for (Candidate candidate : tiedCandidates) {
            ArrayList<Integer> voteIndexRank = candidateFirstVote.get(candidate);
            int currRank = voteIndexRank.get(0);
            int currIndex = voteIndexRank.get(1);
            if (currRank > maxRank) {
                maxRank = currRank;
                maxIndex = currIndex;
                tieLoser = candidate;
            }
            else if ((currRank == maxRank) && (currIndex > maxIndex)){
                maxIndex = currIndex;
                tieLoser = candidate;
            }
        }
        return tieLoser;
    }

    /**
     * Displays the election results, including the election type, number of ballots,
     * seats to be filled, number of candidates, winners, and losers (if any).
     *
     * For each winner and loser, their name and vote percentage relative to the total
     * number of ballots are displayed. Winners and losers are listed in the order they
     * appear in their respective lists.
     *
     * @param electionName The name or type of the election to be displayed.
     */
    public void displayResults(String electionName){
        System.out.printf("Election Type: %s\n", electionName);
        System.out.printf("Number of Ballots: %d\n", numBallots);
        System.out.printf("Seats to be Filled: %d\n", numSeats);
        System.out.printf("Number of Candidates: %d\n", numCandidates);

        System.out.print("Winners:");
        for (int i=0; i < winners.size(); i++){
            if(i == winners.size() - 1){
                System.out.printf(" %s\n", winners.get(i).getName());
            } else{
                System.out.printf(" %s,", winners.get(i).getName());
            }  
        }

        // If there are no losers skip this output
        if(!losers.isEmpty()) {
            System.out.print("Losers:");
            for (int i = 0; i < losers.size(); i++) {
                if (i == losers.size() - 1) {
                    System.out.printf(" %s\n", losers.get(i).getName());
                } else {
                    System.out.printf(" %s,", losers.get(i).getName());
                }
            }
        }
    }

    /**
     * Generates an audit file containing detailed election results, including winners and losers,
     * their vote percentages, and the order of ballots cast for each candidate.
     *
     * If the file already exists, the method prints an error message and terminates the program.
     * If an I/O error occurs during file creation or writing, an error message is printed and the program exits.
     *
     * @param electionName The name or type of the election to be recorded in the audit file.
     * @return A file pointer to the newly created audit file
     */
    public File generateAudit(String electionName){
        File auditFile = new File(fileName);

        try{
            if(!auditFile.createNewFile()){
                System.err.println("File already exists\n Choose a different name for the audit file.");
                System.exit(-1);

            }
        } catch (IOException error){
            System.err.printf("An error occurred: %s\n", error);
            System.exit(-1);
        }

        try{
            FileWriter writer = new FileWriter(auditFile);
            writer.write(String.format("Election Type: %s\n", electionName));
            writer.write(String.format("Number of Ballots: %d\n", numBallots));
            writer.write(String.format("Seats to be Filled: %d\n", numSeats));
            writer.write(String.format("Number of Candidates: %d\n", numCandidates));

            for (Candidate winner : winners) {
                writer.write(String.format("Winner: %s\n", winner.getName()));

                ArrayList<Ballot> ballots = winner.getBallots();
                for(int i = 0; i < ballots.size(); i++){
                    if(ballots.size() == 1){
                        writer.write(String.format("Order of ballots cast for %s: %d\n", winner.getName(), ballots.getFirst().getBallotID()));
                    } else if(i == 0){
                        writer.write(String.format("Order of ballots cast for %s: %d, ", winner.getName(), ballots.getFirst().getBallotID()));
                    } else if (i == ballots.size() - 1) {
                        writer.write(String.format("%d\n", ballots.get(i).getBallotID()));
                    } else{
                        writer.write(String.format("%d, ", ballots.get(i).getBallotID()));
                    }
                }
            }

            for (Candidate loser : losers) {
                writer.write(String.format("Loser: %s\n", loser.getName()));

                ArrayList<Ballot> ballots = loser.getBallots();
                for(int i = 0; i < ballots.size(); i++){
                    if(ballots.size() == 1){
                        writer.write(String.format("Order of ballots cast for %s: %d\n", loser.getName(), ballots.getFirst().getBallotID()));
                    } else if(i == 0){
                        writer.write(String.format("Order of ballots cast for %s: %d, ", loser.getName(), ballots.getFirst().getBallotID()));
                    } else if (i == ballots.size() - 1) {
                        writer.write(String.format("%d\n", ballots.get(i).getBallotID()));
                    } else{
                        writer.write(String.format("%d, ", ballots.get(i).getBallotID()));
                    }
                }
            }
            writer.close();
        } catch(IOException error){
            System.err.printf("An error occurred: %s\n", error);
            System.exit(-1);
        }

        return auditFile;
    }

    /**
     * Calls all helper functions to run a STV election from start to finish.
     */
    public void startElection() {
        if (shuffleOn) {
            shuffleBallots();
        }
        int droop = calculateDroop(numBallots, numSeats);
        setDroopQuota(droop);
        distributeVotes();
        displayResults("STV");
        generateAudit("STV");
    }
}