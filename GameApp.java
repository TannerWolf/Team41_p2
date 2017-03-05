/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          team41_p2
// FILE:             GameApp
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

import java.util.Scanner;

public class GameApp{
	/**
	 * Scanner instance for reading input from console
	 */
	private static final Scanner STDIN = new Scanner(System.in);
	/**
	 * Game instance to interact
	 */
	private Game game;

	/**
	 * Constructor for instantiating game class
	 * @param seed: Seed value as processed in command line
	 * @param timeToPlay: Total time to play from command line
	 */
	public GameApp(int seed, int timeToPlay){
		game = new Game(seed, timeToPlay);
	}

	/**
	 * Main function which takes the command line arguments and instantiate the
	 * GameApp class.
	 * The main function terminates when the game ends.
	 * Use the getIntegerInput function to read inputs from console
	 *
	 * @param args: Command line arguments <seed> <timeToPlay>
	 * 
	 */
	public static void main(String[] args) {

		System.out.println("Welcome to the Job Market!");

		// Take input from command line, process it and add error checking
		try {
			if (args.length < 2 || args.length > 2) {//Check the length of 
				//command line
				throw new IndexOutOfBoundsException();
			}
			// Parse to int
			//Check whether the input is integer
			int s = Integer.parseInt(args[0]);
			int t = Integer.parseInt(args[1]);

			//Check whether the input is positive number
			if(s < 0 || t <= 0){
				System.out.println("Please input positive integers, program is over.");
				System.exit(0);
			}
			//Call the game to start after checking
			GameApp ga = new GameApp(s, t);
			ga.start();

		}catch (IllegalArgumentException e){
			System.out.println("Seed is not integer, program is over.");
			System.exit(0);
		}catch (IndexOutOfBoundsException e){
			System.out.println("The number of arguments is not 2, program is over.");
			System.exit(0);
		}catch (NullPointerException e){
			System.out.println("The seed is null, program is over.");
			System.exit(0);
		}catch (Exception e){
			System.out.println("Invalid input, game is over.");
			System.exit(0);
		}
	}

	/**
	 * Add Comments as per implementation
	 */
	private void start(){
		//TODO: The interactive game logic goes here
		//loop while time is not finished
		while (!game.isOver()) {
			try{
				System.out.println("You have " + game.getTimeToPlay() + 
						" in the game!");
				//Haven't finished
				game.createJobs();//only be done on the first iteration, or 

				//if a job is successfully executed either to completion or 
				//reinsertion into the list
				game.displayActiveJobs();//Display the jobs which are created

				//Display the prompt and get input from user
				int time = game.getTimeToPlay();
				int input = getIntegerInput("Select a job to work on: ");
				int amount = getIntegerInput("For how long would you like to "
						+ "work on this job?: ");

				//Get the job and update it back
				Job newJob = game.updateJob(input, amount);
				if(!newJob.isCompleted()){
					int index = getIntegerInput("At what position would you "
							+ "like to insert the job back into the list?\n");
					if(index < game.getNumberOfJobs()){
						game.addJob(index, newJob);
					}
					else{
						game.addJob(newJob);
					}
				}
				else{
					System.out.println("Job completed! Current Score: " + 
							game.getTotalScore());
					game.displayCompletedJobs();
				}

			}catch (Exception e){
				//TAs' explanations are different, looping or existing?
			}

		}
		System.out.println("Game Over!\nYour final score: " + 
				game.getTotalScore());
	}

	/**
	 * Displays the prompt and returns the integer entered by the user
	 * to the standard input stream.
	 *
	 * Does not return until the user enters an integer.
	 * Does not check the integer value in any way.
	 * @param prompt The user prompt to display before waiting for this integer.
	 */
	public static int getIntegerInput(String prompt) {
		System.out.print(prompt);
		while ( ! STDIN.hasNextInt() ) {
			System.out.print(STDIN.next()+" is not an int.  Please enter "
					+ "an integer.");
		}
		return STDIN.nextInt();
	}
}
