/*
* This code has been generated by the Rebel: a code generator for modern Java.
* 
* For more details, please visit www.archetypesoftware.com.
*
* Drop us a line or two at feedback@archetypesoftware.com. We would love to hear from you.
*/
package boundaries;

import java.util.Scanner;

import application.Constants;

public class DecideToStartOrToQuitForm {

	
  public int choose(Scanner sc) {
	  int restartGame;
		System.out.printf("Do you want to start or quit the game (%d = start, %d = quit)?\n", 
				Constants.START_CODE, Constants.QUIT_CODE);
		restartGame = sc.nextInt();
		return restartGame;
  }
}