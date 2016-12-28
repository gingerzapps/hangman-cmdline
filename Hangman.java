/** Hangman is a pretty obvious class : It's a hangman game! Command line isn't beautiful, but for now it works*/

import java.util.*;
import java.io.*;

class Hangman {
	//===========//
	//GAME FIELDS//
	//===========//
	ArrayList<String> words = new ArrayList<String>(); //list of words game pulls from
	void wordList(){/** ArrayList created with words, the "words.add()" must be in a method, so here we are.*/
			words.add("SWEET");						words.add("BUTTERFLY");
			words.add("YES");							words.add("PANTHER");
			words.add("PLAYFUL");					words.add("BALD EAGLE");
			words.add("CRAZY");						words.add("SEA TURTLE");
			words.add("YELLING");					words.add("BROWNIE");
			words.add("SNOWBALL");				words.add("SNICKERDOODLE");
			words.add("CHRISTMAS");				words.add("BREAKFAST");
			words.add("SOUTHGATE");		  	words.add("DINNER");
			words.add("NOT");							words.add("ROLLS");
			words.add("UGLY");							words.add("RUNNING");
			words.add("HELL");							words.add("FOUND");
			words.add("RIDICULOUS");				words.add("DUSTY");
			words.add("DELICIOUS");				words.add("COUGHING");
			words.add("PHONE");						words.add("BONNET");
			words.add("AUTOMOBILE");			words.add("HUMOR");
			words.add("ALPHABET");					words.add("RAY GUN");
			words.add("HANGMAN");					words.add("POLLUTION");
			words.add("GAMES");						words.add("DEATHRAY");
			words.add("CARROT");					words.add("PERSON");
			words.add("DRAGON");					words.add("RAINSTORM");
			words.add("LETTUCE");					words.add("WANDERING");
			words.add("HEALTY");						words.add("IMAGINE");
			words.add("PIZZA");							words.add("ATTEMPTING");
			words.add("WACKY");						words.add("MATURE");
	}
	ArrayList<Character> lettersGuessed = new ArrayList<Character>(); //list of letters guessed in word
	ArrayList<Character> guesses = new ArrayList<Character>(); //list of letters guessed throughout game in entire alphabet
	ArrayList<Character> alpha = new ArrayList<Character>(); //list of letters in alphabet
	void alphaList(){/** ArrayList created with alpha, the 'alpha.add()' must be in a method, so here we are.*/
			alpha.add('A');	alpha.add('B');	alpha.add('C');	alpha.add('D');	alpha.add('E');	alpha.add('F');	alpha.add('G');	alpha.add('H');	alpha.add('I');	alpha.add('J');	alpha.add('K');	alpha.add('L');	alpha.add('M');	alpha.add('N');	alpha.add('O');	alpha.add('P');	alpha.add('Q');	alpha.add('R');	alpha.add('S');	alpha.add('T');	alpha.add('U');	alpha.add('V');	alpha.add('W');	alpha.add('X');	alpha.add('Y');	alpha.add('Z');
	}
	String word;
	String message;
	int guessCount, misses;
	boolean wordGuessed, gameOn;
	
	
	int gameCount = 0;
	int winCount = 0;
	int lossCount = 0;
	String game_count, loss_count, win_count;
	
	void setup(){ /**reset the base game stats*/
		this.wordList();
		this.alpha.clear();
		this.alphaList();
		this.guesses.clear();
		this.lettersGuessed.clear();
		
		this.getWord();
		this.guessCount = 0;
		this.misses = 0;
		this.wordGuessed = false;
		this.gameOn = true;
		this.message = "";
	}

	//==============//
	//GAME METHODS//
	//==============//
	
	void getWord(){ /** use (Math.random()) to randomly select a word from the wordBank text file*/
		int random = (int) (Math.random() * words.size());
		word = words.get(random);
	}
	
	void showFields(){ /** Display fields for Hangman object (word array, miss count, guess count, ect)*/
		System.out.println("\tGame Fields:\n\n" + 
			"\twords =\t" 			+ words + "\n" + 
			"\tword = \t\t" 				+ word + "\n" + 
			"\tlettersGuessed = \t" + lettersGuessed + "\n" + 
			"\tguesses = \t" 			+ guesses + "\n" + 
			"\talpha = \t"				+ alpha + "\n" + 
			"\tmisses = \t" 			+ misses + "\n" + 
			"\twordGuessed = \t" 	+ wordGuessed + "\n" + 
			"\tgameOn = \t" 			+ gameOn + "\n" + 
			"\tguessCount = \t" 	+ guessCount);		
	}
	
	void game(){ /**do while loop containing game message logic*/
		do{ /** iterate through indefinitely, until all letters of word are guessed*/
			switch(enterGuess(word, lettersGuessed)){
				case 0: //IF GUESS NOT IN WORD increment miss and guess counts and set "message" to relevant phrase
					guessCount++;
					misses++;
					message = " is not in the word. \n\tTry again.";
					break;
				case 1: //IF GUESS IS IN WORD increment guess count only and set "message" to relevant phrase
					guessCount++;
					message = " is in the word. \n\tKeep it up!";
					break;
				case 2: //IF LETTER WAS ALREADY GUESSED set "message" to relevant phrase
					message = " has already been guessed. Check the remaining letters above the gallows for a list of un-guessed letters.";
					break;
				case 3: //IF WORD COMPLETELY GUESSED print "message" relevant phrase
					switch(misses){ //change word "time" or "times" depending on the number of misses
						case 1:
							System.out.println("\n\n\tYou guessed the word! The word was " + word + ". You missed " + misses + " time");
							break;
						default:
							System.out.println("\n\n\tYou guessed the word! The word was " + word + ". You missed " + misses + " times");
							break;
					}
					gameCount++;		//increment count of games
					winCount++;
					wordGuessed = true;
					//THIS IS WHERE YOU WOULD CALL CONTINUEPLAYING();
					break;
				case 4: //IF USER RAN OUT OF GUESSES - GAMEOVER - print relevant message
					gameCount++;
					lossCount++;
					wordGuessed = true;
					System.out.println("\tOh no! You ran out of guesses! The word was " + word + ".");
					//THIS IS WHERE YOU WOULD CALL CONTINUEPLAYING();
					break;
				default:
					message = " is not a valid entry. \n\tTry again.";
			}
		}
		while(!wordGuessed); //while word has not been guessed continue block above
		
		//CONTINUE PLAYING MENU AFTER GAME HAS FINISHED
		boolean answered = false;
		while(!answered){
			if(gameCount == 1) {game_count = "game";}
			else {game_count = "games";}
			
			if(winCount == 1) {win_count = "win";}
			else {win_count = "wins";}
			
			if(lossCount == 1) {loss_count = "loss";}
			else {loss_count = "losses";}
			
			System.out.println("\n\n\tYou have played " + 
				gameCount + " " + game_count + ". You have " +
				winCount + " " + win_count + " and " + 
				lossCount + " " + loss_count + ".");
			System.out.println("\n\tDo you want to play again? Y/N: ");
			switch(continuePlaying()){
				case 0:
					answered = true;
					setup();
					game();
				case 1: 
					answered = true;
					break;
				default:
					System.out.println("\n\tThat is unrecognized. Please enter a 'y' or a 'n' for yes or no: ");
			}
		}
	}
	
	void displayStatus(String word, ArrayList<Character> lettersGuessed, String message){ /** display gallows and remaning letters for player*/
		String spc = " ";
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t-----------------");
		System.out.println("\tRemaining Letters"); //print alphabet, substituting letters that have been guessed with underscores
		System.out.println("\t" + "  " + alpha.get(0) + spc + alpha.get(1) + spc + alpha.get(2) + spc + alpha.get(3) + spc + alpha.get(4) + spc + alpha.get(5) + spc + alpha.get(6));
		System.out.println("\t" + "  " + alpha.get(7) + spc + alpha.get(8) + spc + alpha.get(9) + spc + alpha.get(10) + spc + alpha.get(11) + spc + alpha.get(12) + spc + alpha.get(13));
		System.out.println("\t" + "  " + alpha.get(14) + spc + alpha.get(15) + spc + alpha.get(16) + spc + alpha.get(17) + spc + alpha.get(18) + spc + alpha.get(19) + spc + alpha.get(20));
		System.out.println("\t" + "    " + alpha.get(21) + spc + alpha.get(22) + spc + alpha.get(23) + spc + alpha.get(24) + spc + alpha.get(25));
		
		System.out.println("\tMisses left : " + (6 - misses));
		
		switch(misses){ //Display hanging man based on life left
			case 0:
				System.out.println("\t     _____");
				System.out.println("\t     |   |");
				System.out.println("\t     |");
				System.out.println("\t     |");
				System.out.println("\t     |");
				System.out.println("\t   _____");
				System.out.println("\t  /     \\");
				break;
			case 1:
				System.out.println("\t     _____");
				System.out.println("\t     |   |");
				System.out.println("\t     |   O");
				System.out.println("\t     |");
				System.out.println("\t     |");
				System.out.println("\t   _____");
				System.out.println("\t  /     \\");
				break;
			case 2:
				System.out.println("\t     _____");
				System.out.println("\t     |   |");
				System.out.println("\t     |   O");
				System.out.println("\t     |   |");
				System.out.println("\t     |");
				System.out.println("\t     |");
				System.out.println("\t   _____");
				System.out.println("\t  /     \\");
				break;
			case 3:
				System.out.println("\t     _____");
				System.out.println("\t     |   |");
				System.out.println("\t     |   O");
				System.out.println("\t     |  \\|");
				System.out.println("\t     |");
				System.out.println("\t     |");
				System.out.println("\t   _____");
				System.out.println("\t  /     \\");
				break;
			case 4:
				System.out.println("\t     _____");
				System.out.println("\t     |   |");
				System.out.println("\t     |   O");
				System.out.println("\t     |  \\|/");
				System.out.println("\t     |");
				System.out.println("\t     |");
				System.out.println("\t   _____");
				System.out.println("\t  /     \\");
				break;
			case 5:
				System.out.println("\t     _____");
				System.out.println("\t     |   |");
				System.out.println("\t     |   O");
				System.out.println("\t     |  \\|/");
				System.out.println("\t     |  /");
				System.out.println("\t     |");
				System.out.println("\t   _____");
				System.out.println("\t  /     \\");
				break;
			case 6:
				System.out.println("\t     _____");
				System.out.println("\t     |   |");
				System.out.println("\t     |   O");
				System.out.println("\t     |  \\|/");
				System.out.println("\t     |  / \\");
				System.out.println("\t     |");
				System.out.println("\t   _____");
				System.out.println("\t  /     \\");
				break;
		}
		
		if(guesses.size() > 0){
		System.out.println("\n\t" + guesses.get(guesses.size()-1) + message);	//print message (ex: "E is not in the word") 
		}
		else{
			System.out.println("\n\t  Make a guess by entering a letter below");
		}
	}
	
	int enterGuess(String word, ArrayList<Character> lettersGuessed){
		/** Prompt user to enter their letter guess.
			Return 0 - letter is not in word
			Return 1 - letter is in word
			Return 2 - letter was already guessed
			Return 3 - all letters guessed
			Return 4 - if user ran out of guesses
			Return 5 - if guess is invalid*/
			
			displayStatus(word, lettersGuessed, message);
			
			if(!printWord(word, lettersGuessed)) return 3;		//all letters have been guessed
			if(misses == 6) return 4; 		//ran out of misses
			
			System.out.print(" > ");
			Scanner input = new Scanner(System.in);		//processing user input
			String inputCheck = input.toString();
			
			if(inputCheck.length() < 1 || inputCheck.equals("\\n")) return 5;		//not a valid guess (string is empty)
				
			char userInput = Character.toUpperCase(input.nextLine().charAt(0));		//variable for guessed letter in CAPS
			guesses.add(userInput);
			if(!verifyGuess(userInput)) return 5; 		//if verifyGuess returns false it is not a valid guess
			for(int i = 0; i < alpha.size()-1; i++){
				if(alpha.get(i) == userInput) alpha.set(i, '_');		//if letter is guessed, find in alphabet and replace with underscore
			}
			
			if(inLettersGuessed(userInput, lettersGuessed)) return 2; 	//letter is already guessed
			else if(word.contains(String.valueOf(userInput))){
				lettersGuessed.add(userInput);		//letter is guessed correctly, add to guessed letter arraylist
				return 1;												// return 1 to set proper message
			}
			else return 0;	//if all else fails, return 0 for "letter is not in word"
	}
	
	boolean verifyGuess(char userIn){
		/** Verify the guess is a valid character*/
		for(int i = 0; i <= 25; i++){
			if(alpha.get(i) == userIn && alpha.get(i) != '_') return true;
		}
		return false; //if it is not in the alphabet, it is not a valid guess
	}
	
	boolean printWord(String word, ArrayList<Character> lettersGuessed){
		/** print word with dashes for hidden letters. Return true if at least 1 dash is printed, return false if word is completely guessed*/
		boolean dashPrinted = false;
		System.out.print("\n\n\t > ");
		
		for(int i = 0; i < word.length(); i++){		//iterate through all chars in word to check for guessed letters
			char letter = word.charAt(i);
			if(inLettersGuessed(letter, lettersGuessed)) System.out.print(" " + letter); 	//if already guessed, print the letter
			else{
				System.out.print(" -");		//if not already guessed, print dash
				dashPrinted = true;
			}
		}
		
		return dashPrinted; 			//return if dash was printed (if true, word is not complete)	
	}
	
	boolean inLettersGuessed(char letter, ArrayList<Character> lettersGuessed){
		/** Check letter input and letters already guessed, return true if letter has already been guessed*/
		return lettersGuessed.contains(letter);
	}
	
	int continuePlaying(){
		/** Return int value for yes, no, or invalid
			Return 0 - true/yes player wants to continue
			Return 1 - false/no player wants to quit
			Return 2+ - invalid/ player entered invalid character*/
		boolean cont = false;
			Scanner read = new Scanner(System.in);
			String readIn = read.toString();
			if(readIn.length() < 1 || read.equals("\\n"))	return 3;		//invalid entry, return anything other than 0 or 1
			
			char char_in = Character.toUpperCase(read.nextLine().charAt(0));
			if(char_in == 'Y')		return 0; 	//yes, return 0 to continue playing
			if(char_in == 'N')		return 1; 	//no, return 1 to quit game
			return 3;
	}
	
	public static void main(String[] args){
		testHangman game = new testHangman();
		System.out.println("\t  setup();\n\t-----------------");
		game.setup();
		game.game();
	}
}





















