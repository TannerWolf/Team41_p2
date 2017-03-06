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
/**
 * The GameApp This is the main class that starts the program execution and 
 * starts the main program loop. It takes two command line arguments, 
 * a seed for the random number generator and a positive integer 
 * indicating the duration.
 *
 */
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
           if(s < 0 || t < 0){
           	System.out.println("Seed is non-positive integer, program is over.");
           	System.exit(0);
           }
           //Call the game to start after checking
           GameApp ga = new GameApp(s, t);
           ga.start();
    	//Gracefully catch exception, display them and exist the game   
       }catch (IllegalArgumentException e){
    	   System.out.println("Seed is not integer, program is over.");
    	   System.exit(0);
       }catch (IndexOutOfBoundsException e){
    	   System.out.println("The number of the seed is not 2, program is over.");
    	   System.exit(0);
       }catch (NullPointerException e){
    	   System.out.println("The seed is null, program is over.");
    	   System.exit(0);
       }catch (Exception e){
    	   System.out.println("Invalid seed, game is over.");
    	   System.exit(0);
       } 
    }

    /**
     * Add Comments as per implementation
     */
    private void start(){
        
    	//The objects should only be created on the first iteration
    	game.createJobs();
    	//loop while time is not finished
    	while (!game.isOver()) {
    		System.out.println("You have " + game.getTimeToPlay() + 
    					" in the game!");
    	    game.displayActiveJobs();//Display the jobs which are created
            	
            try{//Display the prompt and get input from user
                //input is the job that user want to work on
                int input = getIntegerInput("Select a job to work on: ");
                //amount is the amount of time that the user want to on the job
                int amount = getIntegerInput("For how long would you like to "
                			+ "work on this job?: ");
                //Check whether use enter in positive int
                if (input < 0 || amount < 0)
                	throw new IllegalArgumentException();
                //update the job for specific amount of time
            	Job newJob = game.updateJob(input, amount);
            	
            	//if the job is not completed, ask the user to insert it back
                if(!newJob.isCompleted()){
                	int index = getIntegerInput("At what position would "
                			+ "you like to insert the job back into the list?\n");
                	game.addJob(index, newJob);
                	}
                else{
                	System.out.println("Job completed! Current Score: " + 
                	game.getTotalScore());
                	game.displayCompletedJobs();
                	game.createJobs();//create jobs  if a job is successfully 
                	//executed either to completion or reinsertion into the list,
                	
            	}
            	}catch (IndexOutOfBoundsException e){
            	}catch (IllegalArgumentException e){//prompt the user to enter in
            		//a positive number
            		System.out.println("Please enter in a positive integer!");
            	}
        	
    	}//Display ending and show total score
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
