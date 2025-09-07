import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Abstract class for Plurality and MV. Extends from Election.
 * Template class to reduce code duplication
 *
 * @author Zach Larsen and Josh Subhan
 */
abstract public class DirectSelection extends Election {

    /** Used for testing in deciding ties. */
    protected boolean DETERMINISTIC = false;

    /**
     * Used for testing. This will make tests not use random values and instead use a
     * specific seed.
     */
    public void setDeterministic(){
        DETERMINISTIC = true;
    }

    /**
     * Go through all cast ballots and increment a Candidates vote count by
     * one for every vote they receive in a ballot.
     */
    public abstract void tabulateVotes();

    /**
     * Go through all the candidates and put them in the winner or loser list depending
     * on how many votes they recieved and how many seats are avaialble. Settle ties by
     * calling a helper function.
     */
    public void determine_Winner_Loser(){
        //Sort candidates by vote count in descending order
        candidates.sort((can1, can2) -> can2.getVoteCount() - can1.getVoteCount());

        int seatsFilled = 0;
        int index = 0;
        //Loop through the candidates list as long as there are available seats and there are still candidates to check
        while((seatsFilled < numSeats) && (index < candidates.size())){
            int voteCount = candidates.get(index).getVoteCount();

            ArrayList<Candidate> tied = new ArrayList<>();

            //Add the initial candidate to the tied list and any other candidates that have the same vote count
            while((index < candidates.size()) && (candidates.get(index).getVoteCount() == voteCount)){
                tied.add(candidates.get(index));
                //Increment the index so we do not double-check candidates and add them twice
                index++;
            }

            //If there are more seats available than candidates in the tied list automatically add them all to the winners list
            if(seatsFilled + tied.size() <= numSeats){
                for (Candidate candidate : tied){
                    addWinner(candidate);
                    seatsFilled++;
                }

            }
            //Call a helper function settleTies to add candidates to the winner and loser list
            else{
                settleTies(tied, seatsFilled);
                seatsFilled = numSeats;
            }

        }

        //All remaining candidates are losers so add them to the loser list
        for(int i = index; i < candidates.size(); i++){
            addLoser(candidates.get(i));
        }
    }


    /**
     * Handle ties between Candidates by randomly selecting a specific number of Candidates in the
     * tied list and declaring them winners. The rest are losers. Only called if there are more tied
     * Candidates than remaining seats available.
     * @param tied an ArrayList holding Candidates that are tied in vote count
     * @param seatsFilled an int stating how many seats have already been filled
     */
    public void settleTies(ArrayList<Candidate> tied, int seatsFilled){
        //Not Random for testing
        if(DETERMINISTIC){
            Collections.shuffle(tied, new Random(5));
        }
        //Random
        else{
            //Shuffle the candidates around in the tied ArrayList using a Random seed
            Collections.shuffle(tied, new Random());
        }

        //Add candidates to the winners list based on how many seats are remaining
        int i = 0;
        while(seatsFilled < numSeats){
            addWinner(tied.get(i));
            seatsFilled++;
            i++;
        }

        //Add the remaining candidates to the losers list
        while(i < tied.size()){
            addLoser(tied.get(i));
            i++;
        }
    }


    /**
     * Display important election stats including the number of ballots, number of seats, number of candidates,
     * the winners and there vote percentage, and losers and there vote percentage.
     * @param electionName a String clarifying if it is a Plurality or Municipal Voting election.
     */
    public void displayResults(String electionName){
        System.out.printf("Election Type: %s\n", electionName);
        System.out.printf("Number of Ballots: %d\n", numBallots);
        System.out.printf("Seats to be Filled: %d\n", numSeats);
        System.out.printf("Number of Candidates: %d\n", numCandidates);

        System.out.print("Winners:");
        for (int i=0; i < winners.size(); i++){
            double percentage = (winners.get(i).getVoteCount() / (double)numBallots) * 100;
            if(i == winners.size() - 1){
                System.out.printf(" %s with a vote percentage of: %.1f%%\n", winners.get(i).getName(), percentage);
            } else{
                System.out.printf(" %s with a vote percentage of: %.1f%%,", winners.get(i).getName(), percentage);
            }
        }

        //If there are no losers skip this output
        if(!losers.isEmpty()) {
            System.out.print("Losers:");
            for (int i = 0; i < losers.size(); i++) {
                double percentage = (losers.get(i).getVoteCount() / (double) numBallots) * 100;
                if (i == losers.size() - 1) {
                    System.out.printf(" %s with a vote percentage of: %.1f%%\n", losers.get(i).getName(), percentage);
                } else {
                    System.out.printf(" %s with a vote percentage of: %.1f%%,", losers.get(i).getName(), percentage);
                }
            }
        }
    }

    /**
     * Generate an audit file after an election that contains all relevant election information.
     * @param electionName A String indicating if it is a Plurality or Municipal Voting election.
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
                double percentage = (winner.getVoteCount() / (double) numBallots) * 100;
                writer.write(String.format("Winner: %s with a vote percentage of %.1f%%\n", winner.getName(), percentage));

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
                double percentage = (loser.getVoteCount() / (double) numBallots) * 100;
                writer.write(String.format("Loser: %s with a vote percentage of %.1f%%\n", loser.getName(), percentage));

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

}
