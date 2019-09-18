/* dcg2150
 * 
 * COMS 1004 
 * Project P7.9
 * Adam Cannon
 * 
 * @author Dorothee Grant
 * 
 * Poker Game due April 2
 * 
 * The Game Class runs the game and chooses
 * how the game is played
 * */

import java.util.*; //import all

public class Game { //make the Game Class
    
	//instance variblaes
	private int game = 0; //a variable to know if it is first round
	private Player p;
	private Deck cards;
    Scanner scan = new Scanner(System.in);
    Scanner grant = new Scanner(System.in);
    Scanner ask = new Scanner(System.in);
    public double input;
    public double payout;
    private boolean wantsToPlayAgain = true; 
    private int global;
    ArrayList<Card> hand; 

    
    public Game(String[] testHand){
        //make the constructor for the CLA game
        //inisialize the instance variables
        p = new Player();
        cards = new Deck();
        payout = -5;
        global = 1; //make tihs one for my play method
        hand = new ArrayList<Card>(); //make a new hand
		for(String l : testHand){ //make an inhanced for loop
            //goes through args as a sting
            //cuts it into substrings
        String suit = l.substring(0,1); //"s11" -> "s"
            
            int r = 0;
            int s = 500;
            int x = l.length();
                r = Integer.parseInt(l.substring(1, x));
             //pars the second part of the string into a
             //substring, then turn into an int
 
            //set each substring into an int 
        if(suit.equals("c")){
        s = 1; 
        }
        else if(suit.equals("d")){
            s = 2;
        }
        else if(suit.equals("h")){
            s = 3;
        }
        else {
            s = 4;
        }
        hand.add(new Card(s, r));
        // sort method for this game
        p.sort(hand);
        //then call check hand
        }
	}
	
	public Game(){ //make the regualr game constructor 
        p = new Player();
        cards = new Deck();
        payout = -5;
        game = 0;
        global = 0; //set global to  1 for play method	
	}
    
    public boolean askToPlay(){ //boolean to see if player
        //wants to continue playing
        System.out.println("Would you like to play (again), yes or no?");
        //ask user if they want to play
        String answer = ask.nextLine();
               if(answer.equals("yes")){ 
                   //if yes clear the hand to start again
                   for(int i = 4; i >= 0; i--){ 
                     p.removeCard(i);
                       game = 1; //if continue set counter != 0
                    }
                   return true;    
               }
                else{
                    System.out.println("Thanks for playing!");
                    return false;
                }
     }
    
     public void play(){ //the method called by tester
        //if the int global is 1
        //play the CLA way
         if(global == 1){
            System.out.println(hand);
            checkHand(hand);
         }
         else{ //elese play the normal way
            normal();
         }
     }
    
     public void normal(){    
        while(wantsToPlayAgain){ // loop that asks if they want to play
            System.out.println("Your current bankroll is " + p.getBankroll());
            input = -4; //just to have it set to something so it is not null
            System.out.println("How many tokens would you like to bet (1-5)");
            input = scan.nextDouble();
            //keep asking user for appropriate amount of tokens given
            //until it matches the requirement 
            while((input > 5 || input == 0) || (input - (int) input != 0)){
                System.out.println("Not an apporpriate answer, try again");
                input = scan.nextDouble();  
            }
            p.bets(input); // ask them to make a bet before dealing
            System.out.println("Now your bankroll is " + p.getBankroll());
            //print out their bankroll 
            makeHand(); //make the hand and then checkHand
            p.winnings(payout);
            //find out how much they have
            //and print it out
            System.out.println("Now your bankroll is " + p.getBankroll());
            wantsToPlayAgain = askToPlay();
            //ask if they want to play
        }
     }
		
     public void makeHand(){ //the method that actually deals the hand
        String another = "";
        Card temp = cards.deal();
        //Give temp a value so it is not null
         if(game == 0){ //before reshuffling make sure that its the 1st round
             //that way like in normal poker you don't shuffle mid game
         for(int i = 0; i <1000; i++){
               cards.shuffle();
         } 
         } //run shuffle method 1000 times
          
          for(int i = 0; i <5; i++){ 
              //deal 5 cards
             temp = cards.deal();
              //add 5 cards to array list from method in player
             p.addCard(temp); 
          }
          System.out.println("Your cards are " + p.getHand());
          //ask user if they want to change any cards from last ot 1st
          for(int j = 5; j > 0; j--){
               System.out.println("Would you like to change your" + 
                          " card at position " + j + "?" + 
                           " Please print yes or no");  
               another = grant.nextLine();
               if(another.equals("yes")){
                   int index = j-1;
                   p.removeCard(index); //if yes remove the card
                   //then add a new one
                   p.addCard(cards.deal());
               }         
           }
            p.sortHand(); //sort the hand
            hand = p.getHand();
            System.out.println(hand); //print it out
            checkHand(hand); //check the hand
      }  

	  public String checkHand(ArrayList<Card> hand){ 
          //check the hand
          //set string so it is not null
          String check = "NADA";
          if(royalFlush(hand) == true){
               check = "YOU HAVE A ROYAL FLUSH!";
                  //update check for each
               payout = 250; //update payout for each
          } 
          else if(straightFlush(hand) == true){
               check = "YOU HAVE A STRAIGHT FLUSH!";
              payout = 50;
              //do else if to only check the next one if the first isnt true
          }  
          else if(four(hand) == true){
               check = "YOU HAVE FOUR OF A KIND!";
              payout = 25;
          } 
          else if(fullHouse(hand) == true){
               check = "You have a fullHouse!";
              payout = 6;
          }
          else if(flush(hand) == true){
               check = "You have a flush!";
              payout = 5;
          } 
          else if(straight(hand) == true){
              check = "You have a Straight!";
              payout = 4;
          }
          else if(three(hand) == true){
               check = "You have three of a kind!";
              payout = 3;
          }
          else if(twoPair(hand) == true){
               check = "You have two paris!";
              payout = 2;
          }
          else if(two(hand) == true){
               check = "You have a pair";
              payout = 1;
          }
          else{
              //esle just print out the highest card
              int high = highCard(hand);
              String alto = Integer.toString(high);
              //make it an int
              //assing the int values to the names
              if(high == 1){
                  alto = "Ace";
              }
              if(high == 11){
                  alto = "Jack";
              }
              if(high == 12){
                  alto = "Queen";
              }
              if(high == 13){
                  alto = "King";
              }
              payout = 0;
              check = ("Your high card is " + alto);
          }
          System.out.println(check); //print out the check
          System.out.println("Payout: " + payout); //print out the payout
          return check;
      }
    
      private boolean royalFlush(ArrayList<Card> hand){
           //check each hand
           //use the straight and flush as apart of this test
           if(straightFlush(hand) == true &&
             (hand.get(0).getRank() == 10) ||
             (hand.get(0).getRank() == 1 &&
             hand.get(1).getRank() == 10 &&
             hand.get(2).getRank() == 11 &&
             hand.get(3).getRank() == 12 &&
             hand.get(4).getRank() == 13 &&
             flush(hand) == true)){
               return true;
           }
           else{   
           } return false;
      }
    
      private boolean straightFlush(ArrayList<Card> hand){
           //make each of these booleans 
           //to return only true if it is this hand
           //otherwise return false to check the next method
           if(straight(hand) == true && flush(hand) == true){
               return true;
           }
           else{   
           } return false;
      }
       
      private boolean four(ArrayList<Card> hand){ 
          if((hand.get(0).getRank() == hand.get(3).getRank() &&
            hand.get(0).getRank() != hand.get(4).getRank()) ||
            (hand.get(1).getRank() == hand.get(4).getRank() &&
            hand.get(0).getRank() != hand.get(4).getRank())){
              return true; //only return true if all the conditions are met
          }
           else{   
           } return false;
      }
        
       private boolean fullHouse(ArrayList<Card> hand){
           if(hand.get(0).getRank() == hand.get(1).getRank() &&
            hand.get(3).getRank() == hand.get(4).getRank() &&
            (hand.get(2).getRank() == hand.get(4).getRank() ||
            hand.get(2).getRank() == hand.get(1).getRank())){
               return true;
           }
           else{   
           } return false;
       }
    
        private boolean three(ArrayList<Card> hand){ 
          if((hand.get(0).getRank() == hand.get(2).getRank() &&
            hand.get(0).getRank() != hand.get(3).getRank()) ||
            (hand.get(1).getRank() == hand.get(3).getRank() &&
            hand.get(0).getRank() != hand.get(4).getRank()) ||
            (hand.get(2).getRank() == hand.get(4).getRank() &&
            hand.get(1).getRank() != hand.get(3).getRank())){
              return true;
          }
           else{   
           } return false;
       } 
       
        private boolean twoPair(ArrayList<Card> hand){
               int counter = 0;
            //use a counter to keep track of how many there have been
            for(int i = 1; i < 5; i++){
                if(hand.get(i-1).getRank() == hand.get(i).getRank()){
                    counter++;
                } 
            }
            if(counter == 2){
                return true;
            }
            else{    
            } return false;
        }
    
        private boolean flush(ArrayList<Card> hand){
               int counter = 0;
            for(int i = 1; i < 5; i++){
                if(hand.get(0).getSuit() == hand.get(i).getSuit()){
                    counter++;
                } 
                else{
                    i = 100;
                }
            }
            if(counter == 4){
                return true;
            }
            else{
                
            } return false;
        }
     private boolean straight(ArrayList<Card> hand){ 
         int counter = 0;
         for(int i = 0; i < 4; i++){
             if(hand.get(i).getRank() + 1 == hand.get(i+1).getRank()){
                 counter++;
             }
              if(counter == 4){
                return true;
            }
         }
            if(hand.get(0).getRank() == 1 &&
             hand.get(1).getRank() == 10 &&
             hand.get(2).getRank() == 11 &&
             hand.get(3).getRank() == 12 &&
             hand.get(4).getRank() == 13){     
                 return true;
            } 
            else{   
            }  return false; 
     }
        
    private boolean two(ArrayList<Card> hand){
            for(int i = 0; i < 4; i++){
                if(hand.get(i).getRank() == hand.get(i+1).getRank()){
                    return true;
                }
                else{    
                }
                }return false;
        }
        
        private int highCard(ArrayList<Card> hand){
            //return an int as apposed to a boolean
            //if all the others fail than print out the high card
            int high = hand.get(4).getRank();
             return high;
        }
    
} //end class

