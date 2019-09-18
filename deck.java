import java.util.Random; //import the random class for shuffling

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck
	
	public Deck(){ //constructor
		// make a 52 card deck here
		cards = new Card[52];
        //array that way the size is set
        //have top be -1 to not be null
        top = -1;
        //nested forloop to put the cards in the array
        for(int i =1 ; i < 5; i++){
            for(int j = 1; j < 14; j++){
                cards[(i-1)*13+(j-1)] = new Card(i, j);  
                //makes sure they go in with the proper order
                }
            }
        }
       
	public void shuffle(){
        //method to shuffle
        int x;
        Card buf;
        //import a random number
        Random rand = new Random();
        for(int i = 0; i< cards.length; i++){
            x = rand.nextInt(cards.length);
            buf = cards[i];
			cards[i] = cards[x];
			cards[x] = buf;
        } //shuffle by switching random card with every element in array
        top = -1;
		// shuffle the deck here
	}
	
	public Card deal(){
        //deal the cards, make sure that you haven't reached 
        //the end of the deck with this if statement
        if(top == 51){
            shuffle();
        }top++; //increment top
        return cards[top]; //return the card at top
        
		// deal the top card in the deck
	}

} //end class 
