
/**
 * The Scoreboard class keeps a record of completed jobs and the total score. 
 * It can be updated when necessary.
 */
public class Scoreboard implements ScoreboardADT
{
	ListADT<Job> ListOfJobs; // Job list for scoreboard
	private int totalScore;

	Scoreboard() 
	{
		this.ListOfJobs = new JobList();
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
		ListOfJobs.add(job);
	}

	/*
	 * Prints out a summary of all the jobs currently stored in the scoreboard.
	 */
	public void displayScoreBoard()
	{
		System.out.println("Total Score: " + getTotalScore());
		System.out.println("The jobs completed: ");
		for (Job j : ListOfJobs)
		{
			System.out.println("Job Name: " + j.getJobName());
			System.out.println("Points earned for this job: " + j.getPoints());
			System.out.println("--------------------------------------------");
		}
	}
}
