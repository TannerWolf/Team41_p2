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
     */
    public static void main(String[] args){

        System.out.println("Welcome to the Job Market!");

        // Take input from command line, process it and add error checking
        if (args.length < 2 || args.length > 2) {
        	throw new IllegalArgumentException();
        }
        // Parse to int
        // TODO: check if parsing fails
        if (true) {
        	
        }
        int s = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        
        if(s < 0 || t < 0){
        	System.exit(0);
        }
        GameApp ga = new GameApp(s, t);
        // Call the start() method to start playing the game
        ga.start();
        ga.main_menu_loop();
    }

    /**
     * File says to use this method????
     */
    private void main_menu_loop() {
		// TODO Auto-generated method stub
		
	}

	/**
     * Add Comments as per implementation
     */
    private void start(){
        //TODO: The interactive game logic goes here
    	while (!game.isOver()) {
    		System.out.println("You have " + game.getTimeToPlay() + " seconds in the game!");
        	game.createJobs();
        	game.displayActiveJobs();
        	int input = getIntegerInput("Select a job to work on: ");
        	int amount = getIntegerInput("For how long would you like to work on this job?: ");
        	Job newJob = game.updateJob(input, amount);
        	if(!newJob.isCompleted()){
        		int index = getIntegerInput("At what position would you like to insert the job back into the list? ");
        		game.addJob(index, newJob);
        	}
        	else{
        		System.out.println("Job completed! Current Score: " + game.getTotalScore());
        	}
    	}
    	System.out.println("Game Over! Your score is: " + game.getTotalScore());
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
            System.out.print(STDIN.next()+" is not an int.  Please enter an integer.");
        }
        return STDIN.nextInt();
    }
}
