# HANGMAN #

## Hangman Implementation Overview ##

Hangman is a word-guessing game where one player thinks of a word and the other player tries to guess it by suggesting letters within a certain number of attempts. In this implementation, there are three main components:

### HangmanController ###

* Manages the game logic.
* Selects a random word from a predefined list.
* Initializes game settings like the number of allowed attempts.
* Tracks correct and incorrect guesses.
* Handles user input validation.
* Updates the hidden word to reveal correct guesses.
* Determines the end game state (win or lose).

### HangmanView ###

* Handles the user interface.
* Displays Hangman ASCII art representing the current game state.
* Prompts user for input.
* Displays feedback such as remaining chances and incorrect letters guessed.

### Hangman ###

* Acts as the entry point to the Hangman game.
* Contains the main method.
* Initializes HangmanController and HangmanView instances.
* Sets up the game.
* Starts the game loop.

## Commands to compile/run the game ##

To compile/run the Hangman game using Maven, you can use the following commands:

* Run the Packaged JAR: `java -jar target/Hangman-1.0-SNAPSHOT.jar`
* Compile the Code: `mvn compile`
* Execute the Application Directly: `mvn exec:java`
* Run Tests: `mvn test`
* Package the Application: `mvn package`
* Clean Build: `mvn clean install`
