import java.util.*; //import all
import java.io.*;

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
        suit = s;
        rank = r;
        // I used rank rather than value to keep consistancy 
	}
	
    public int getRank(){ 
        //made this method to access the rank from Game class
        return rank;
    }
    public int getSuit(){
        //made this method to access the suit from Game class
        return suit;
    }
    
	public int compareTo(Card c){
		// use this method to compare cards so they 
		// may be easily sorted
		// first compare the ranks, if the ranks are equal sort by suit
		if(this.rank == c.rank){
            if(this.suit > c.suit){
            return 1;
            }
            else if(this.suit < c.suit){
                return -1;
            }
            return 0;   
        }
        else if(this.rank > c.rank){
            return 1;
        }
        else{
            return -1;
        } 
        //used the traditional way of return 1, -1, and 0
        //to order is acsending order
	}
	
	public String toString(){ //to string method 
        //made a string variable to be the description 
        //hard coded for the suits
        String description;
        String theSuit = "null";
        if (suit == 1){
            theSuit = "clubs";
        }
        else if(suit == 2){
            theSuit = "diamonds";
        }
        else if(suit == 3){
            theSuit = "hearts";
        }
        else if(suit == 4){
            theSuit = "spades";
        }
        //hard coded ranges for the rank
        if(rank > 1 && rank < 11){
            description = rank + " of " + theSuit;
        }
		else if(rank == 1){
            description = "Ace of " + theSuit;
        }
        else if(rank == 11){
            description = "Jack of " + theSuit;
        }
        else if(rank == 12){
            description = "Queen of " + theSuit;
        }
        else{
            description = "King of " + theSuit;
        }
        return description;
        
            // use this method to easily print a Card object
	}

} //end Class
