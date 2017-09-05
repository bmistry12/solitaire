package solitare;

import java.util.Stack;

import cards.Cards;
import cards.Deck;
import cards.Suit;
import cards.Value;

public class Solitare {
	private Stack<Cards> wasteDeck = new Stack<Cards>();
	private Deck startDeck = new Deck();
	private Deck stockDeck;
	private Stack<Cards>[] foundation = (Stack<Cards>[]) new Stack[4];
	public Stack<Cards> col1 = new Stack<Cards>();
	public Stack<Cards> col2 = new Stack<Cards>();
	public Stack<Cards> col3 = new Stack<Cards>();
	public Stack<Cards> col4 = new Stack<Cards>();
	public Stack<Cards> col5 = new Stack<Cards>();
	public Stack<Cards> col6 = new Stack<Cards>();
	public Stack<Cards> col7 = new Stack<Cards>();
	private SolitareDisplay display;
	
	public Solitare(){
		display = new SolitareDisplay(this);
		deal();
		printCurrentCol();
	}
	
	public Cards getStockCard(){
		if(stockDeck.getDeckSize() == 0){
			return null;
		} else {
			return stockDeck.getTopOfDeck();
		}
	}
	
	public Cards getWasteCard(){
		if(wasteDeck.size() == 0){
			return null;
		} else {
			return wasteDeck.peek();
		}
	}
	
	public Cards getFoundCard(int index){
		if(foundation[index] == null || foundation[index].isEmpty()){
			return null;
		} else {
			return foundation[index].peek();
		}		
	}
	
	public Stack<Cards> getPile(Stack<Cards> pile){
		return pile;
	}
	
	public void resetStock(){
		while(!(wasteDeck.size()==0)){
			Cards temp = wasteDeck.pop();
			temp.turnDown();
			stockDeck.addCard(temp);
		}
	}
	
	public void dealCard(){
		if(! (stockDeck.getDeckSize() == 0)){
			Cards temp = stockDeck.removeTopOfDeck();
			wasteDeck.push(temp).turnUp();
		}
	}
	
	private void deal() {
		startDeck.shuffleDeck();
		col1.push(startDeck.removeTopOfDeck()).turnUp();
		col2.push(startDeck.removeTopOfDeck());
		col2.push(startDeck.removeTopOfDeck()).turnUp();
		col3.push(startDeck.removeTopOfDeck());
		col3.push(startDeck.removeTopOfDeck());
		col3.push(startDeck.removeTopOfDeck()).turnUp();
		col4.push(startDeck.removeTopOfDeck());
		col4.push(startDeck.removeTopOfDeck());
		col4.push(startDeck.removeTopOfDeck());
		col4.push(startDeck.removeTopOfDeck()).turnUp();
		col5.push(startDeck.removeTopOfDeck());
		col5.push(startDeck.removeTopOfDeck());
		col5.push(startDeck.removeTopOfDeck());
		col5.push(startDeck.removeTopOfDeck());
		col5.push(startDeck.removeTopOfDeck()).turnUp();
		col6.push(startDeck.removeTopOfDeck());
		col6.push(startDeck.removeTopOfDeck());
		col6.push(startDeck.removeTopOfDeck());
		col6.push(startDeck.removeTopOfDeck());
		col6.push(startDeck.removeTopOfDeck());
		col6.push(startDeck.removeTopOfDeck()).turnUp();
		col7.push(startDeck.removeTopOfDeck());
		col7.push(startDeck.removeTopOfDeck());
		col7.push(startDeck.removeTopOfDeck());
		col7.push(startDeck.removeTopOfDeck());
		col7.push(startDeck.removeTopOfDeck());
		col7.push(startDeck.removeTopOfDeck());
		col7.push(startDeck.removeTopOfDeck()).turnUp();
		stockDeck = startDeck;
		
		
	}
	public void printCurrentCol(){
		//prints each pile
		System.out.println("1 ->" + iterateStack(col1));
		System.out.println("2 ->" + iterateStack(col2));
		System.out.println("3 ->" + iterateStack(col3));
		System.out.println("4 ->" + iterateStack(col4));
		System.out.println("5 ->" + iterateStack(col5));
		System.out.println("6 ->" + iterateStack(col6));
		System.out.println("7 ->" + iterateStack(col7));
	}
	
	public String iterateStack(Stack<Cards> s1){
		//iterate through a stack. If a card if face down display X instead of card details
		String pile = "[";
		for (Cards obj : s1){
			if (!obj.isFaceUp){
				pile += ("X | ");
			} else {
				pile += (obj + " | ");
			}
		}
		pile += "]";
		return(pile);
	}
	public void stockDeck() {
		System.out.println("Stock clicked");
		display.unselect();
		if(!display.isWasteSelected() && !display.isPileSelected()){
			if (stockDeck.getDeckSize() == 0){
				resetStock();
			} else {
				dealCard();
			}
		}
	}

	public void wasteCardClicked() {
		System.out.println("Waste card clicked");
		if(!wasteDeck.isEmpty()){
			if(!display.isWasteSelected()){
				display.selectWaste();
			} else {
				display.unselect();
			}
		}
	}

	public void foundationClicked(int i) {
		System.out.println("foundation" + i + " clicked");
		if (display.isWasteSelected()) {
			System.out.println("here");
			if (addToFoundation(wasteDeck.peek(), i)){
				Cards card = wasteDeck.pop();
				System.err.println(card.getSuit());
				if (foundation[i] == null){
					foundation[i] = new Stack<Cards>();
				}
				foundation[i].push(card);
				display.unselect();
			} else {
				System.err.println("Not a valid move");
			}
		}
		if (display.isPileSelected()){
			System.err.println("foundation clicked display on pile");
		}
	}

	private boolean addToFoundation(Cards card, int i) {
		boolean isValidMove = false;
		if(foundation[i] == null || foundation[i].isEmpty()){
			if (card.getValue() == Value.ACE){
				isValidMove =  true;
			}
		} else {
			Cards onFound = foundation[i].peek();
			if (onFound.getSuit() == card.getSuit()){
				if(onFound.valueToInt(onFound.getValue()) + 1 == card.valueToInt(card.getValue())){
					isValidMove = true;
				}
			}
		}
		return isValidMove;
	}

	public void pileClicked(int col) {
		// TODO Auto-generated method stub
		System.out.println("pile clicked " + col);
	}

}
