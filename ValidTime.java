import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ValidTime implements Chain{

	private  Chain nextInChain;
	
	// Defines the next Object to receive the
	// data if this one can't use it
	public void setNextChain(Chain nextChain) {
		nextInChain = nextChain;
		
	}

	// Tries to calculate the data, or passes it
	// to the Object defined in method setNextChain()
	public String validateBooking(String username, String lp, String pl, String ps, String date, String start, String duration, ArrayList<Integer> allTimes) {
		boolean valid = true;
		
		String[] parts = start.split(":");
    	String finalStart = parts[0];
		int startI = Integer.valueOf(finalStart);
		
		String[] parts2 = duration.split(" Hour");
    	String finalDuration = parts2[0];
		int durationI = Integer.valueOf(finalDuration);
		
		String[] parts3 = pl.split(": ");
    	String finalPL = parts3[1];
		
		String[] parts4 = ps.split(": ");
    	String finalPS = parts4[1];
		
		for(int i = startI; i < startI + durationI; i++) {
			if(!allTimes.contains(i)) {
				valid = false;
			}
		}
		
		ArrayList<String> user = ReadCSV.findUserName(username, "Clients.txt");
		ReadCSV.findUserName(username, "Clients.txt");
		String amountDueI = user.get(6);
		String[] parts9 = amountDueI.split("\r");
		int amountDue = Integer.valueOf(parts9[0]);
		
		if(user.get(5).equals("Visitor")) {
			amountDue += 15;
		}
		else if(user.get(5).equals("Faculty")) {
			amountDue += 8;
		}
		else if(user.get(5).equals("Staff")) {
			amountDue += 10;
		}
		else if (user.get(5).equals("Student")){
			amountDue += 5;
		}
		else {
			amountDue += 20;
		}
		
		if(valid){
			
			WriteCSV.CreateBooking("HappyBuddy77", lp, finalPL, finalPS, date, finalStart, finalDuration);
			try {
				DeleteCSV.CancelClient("HappyBuddy77", user.get(1), user.get(2), user.get(3), user.get(4), user.get(5), user.get(6));
				WriteCSV.saveClient("HappyBuddy77", user.get(1), user.get(2), user.get(3), user.get(4), user.get(5), amountDue);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "Booking Created";
		} 
		else {	
			return "Booking not Created, time and duration of booking is unavalible";
		}
		
	}
}