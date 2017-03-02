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
        GameApp ga = new GameApp(s, t);
        
        // Call the start() method to start playing the game
        ga.start();
    }

    /**
     * Add Comments as per implementation
     */
    private void start(){
        //TODO: The interactive game logic goes here
    	while (!game.isOver()) {
    		int input = getIntegerInput("Put prompt here");
    	}
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