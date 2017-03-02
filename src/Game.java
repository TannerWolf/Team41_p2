/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          team41_p2
// FILE:             Game
//
// TEAM:    Team 41, IDGAF
// Authors: 
// Author1: (Jarrett Benson, jbenson6@wisc.edu, jbenson6, Lec 002)
// Author2: (Cameron Carlson, ccarlson24@wisc.edu, ccarlson, Lec 002) 
// Author3: (Isaac Heinrich, iheinrich@wisc.edu, iheinrich, Lec 002) 
// Author4: (Jiayue Lai, jlai28@wisc.edu, jlai, Lec 002)
// Author5: (William Mustari, willmustari@gmail.com, mustari, Lec 002) 
// Author6: (Tanner Wolf, tmwolf2@wisc.edu, tmwolf2, Lec 002) 
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.Random;

public class Game{

    /**
     * A list of all jobs currently in the queue.
     */
    private ListADT<Job> list;
    /**
     * Whenever a Job is completed it is added to the scoreboard.
     */
    private ScoreboardADT scoreBoard;
    private int timeToPlay;
    private JobSimulator jobSimulator;

    /**
     * Constructor. Initializes all variables.
     * @param seed
     * seed used to seed the random number generator in the Jobsimulator class.
     * @param timeToPlay
     * duration used to determine the length of the game.
     */
    public Game(int seed, int timeToPlay){
    	jobSimulator = new JobSimulator(seed);
    	this.timeToPlay = timeToPlay;
    }

    /**
     * Returns the amount of time currently left in the game.
     * @returns the amount of time left in the game.
     */
    public int getTimeToPlay() {
        // return the amount of time left
        return timeToPlay;
    }

    /**
     * Sets the amount of time that the game is to be executed for.
     * Can be used to update the amount of time remaining.
     * @param timeToPlay
     *        the remaining duration of the game
     */
    public void setTimeToPlay(int timeToPlay) {
    	this.timeToPlay = timeToPlay;
    }

    /**
     * States whether or not the game is over yet.
     * @returns true if the amount of time remaining in
     * the game is less than or equal to 0,
     * else returns false
     */
    public boolean isOver(){
        return timeToPlay <= 0;
    }
    
    /**
     * This method simply invokes the simulateJobs method
     * in the JobSimulator object.
     */
    public void createJobs(){
    	jobSimulator.simulateJobs(list, timeToPlay);
    }

    /**
     * @returns the length of the Joblist.
     */
    public int getNumberOfJobs(){
        return list.size();
    }

    /**
     * Adds a job to a given position in the joblist.
     * Also requires to calculate the time Penalty involved in
     * adding a job back into the list and update the timeToPlay
     * accordingly
     * @param pos
     *      The position that the given job is to be added to in the list.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(int pos, Job item){
    	if (pos == list.size()) {
    		list.add(item);
        	timeToPlay += list.size();
    	}
    	else {
    		if (pos < 0 || pos > list.size()) {
    			pos = list.size();
    		}
    		list.add(pos, item);
    		timeToPlay = timeToPlay - pos;
    	}
    }

    /**
     * Adds a job to the joblist.
     * @param item
     *      The job to be inserted in the list.
     */
    public void addJob(Job item){
    	list.add(item);
    }

    /**
     * Given a valid index and duration,
     * executes the given job for the given duration.
     *
     * This function should remove the job from the list and
     * return it after applying the duration.
     *
     * This function should set duration equal to the
     * amount of time remaining if duration exceeds it prior
     * to executing the job.
     * After executing the job for a given amount of time,
     * check if it is completed or not. If it is, then
     * it must be inserted into the scoreBoard.
     * This method should also calculate the time penalty involved in
     * executing the job and update the timeToPlay value accordingly
     * @param index
     *      The job to be inserted in the list.
     * @param duration
     *      The amount of time the given job is to be worked on for.
     */
    public Job updateJob(int index, int duration){
    	if (index < 0 || index >= list.size()) {
    		// throw exception? say try again?
    		System.out.println("Invalid index");
    	}
    	// remove the job from the lsit
    	Job workJob = list.remove(index);
    	// time penalty
    	timeToPlay -= index;
    	// check if the duration is greater than the remaining work required
    	int diff = workJob.getTimeUnits()-workJob.getSteps();
    	if (diff < duration) {
    		duration = diff;
    	}
    	timeToPlay -= duration;
    	workJob.setSteps(workJob.getSteps() + duration);
    	if (workJob.isCompleted()) {
    		scoreBoard.updateScoreBoard(workJob);
    	}
    	// return the changed job
        return workJob;
    }

    /**
     * This method produces the output for the initial Job Listing, IE:
     * "Job Listing
     *  At position: job.toString()
     *  At position: job.toString()
     *  ..."
     *
     */
    public void displayActiveJobs(){
    	Iterator<Job> itr = list.iterator();
    	String s = "Job Listing\n";
    	while (itr.hasNext()) {
    		s += "At position: " + itr.next().toString() + "\n";
    	}
    	System.out.println(s);
    }

    /**
     * This function simply invokes the displayScoreBoard method in the ScoreBoard class.
     */
    public void displayCompletedJobs(){
    	scoreBoard.displayScoreBoard();
    }

    /**
     * This function simply invokes the getTotalScore method of the ScoreBoard class.
     * @return the value calculated by getTotalScore
     */
    public int getTotalScore(){
        return scoreBoard.getTotalScore();
    }
}
