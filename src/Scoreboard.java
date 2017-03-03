/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          team41_p2
// FILE:             Scoreboard
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

/**
 * The Scoreboard class keeps a record of completed jobs and the total score. 
 * It can be updated when necessary.
 */
public class Scoreboard implements ScoreboardADT
{
	ListADT<Job> listOfJobs; // Job list for scoreboard
	private int totalScore;	 // Number of total score

	Scoreboard() 
	{
		this.listOfJobs = new JobList();
		this.totalScore = 0;
	}
	
	/**
	 * Returns the total score of all completed jobs.
	 * 
	 * @return total score
	 */
	public int getTotalScore()
	{
		return totalScore;
	}

	/**
	 * Updates the scoreboard by accepting a job, adding the jobs points to the
	 * total score, and adding the job to the list of completed jobs.
	 * 
	 * @param Job to be added to scoreboard
	 */
	public void updateScoreBoard(Job job)
	{
		totalScore += job.getPoints();
		listOfJobs.add(job);
	}

	/*
	 * Prints out a summary of all the jobs currently stored in the scoreboard.
	 */
	public void displayScoreBoard()
	{
		System.out.println("Total Score: " + getTotalScore());
		System.out.println("The jobs completed: ");
		for (Job j : listOfJobs)
		{
			System.out.println("Job Name: " + j.getJobName());
			System.out.println("Points earned for this job: " + j.getPoints());
			System.out.println("--------------------------------------------");
		}
	}
}
