import java.util.Random;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;
/*
 * Mine - 2025 - 3F esercizio svolto in classe
 */
public class MineBoard {

	public static boolean[][] board;	// True ->> mina
	public static boolean[][] visible;  // True ->> visible
	public static Scanner sc=new Scanner(System.in);
	public static int bSize;
	public static int nMine;
	public static int nCell;

	//
	// Verifica se alle coordinate (riga,colonna) nella cella ci sia una mina
	//
	public static boolean isMine(int i, int j) {
		return board[i][j];
	}
	
	//
	// Verifica se alle coordinate (riga,colonna) la cella sia visibile
	//
	public static boolean isVisible(int i, int j) {
		return visible[i][j];
	}
	
	//
	// Rende visibile una cella alle coordinate (riga,colonna) ricevute
	//
	public static void setVisible(int i, int j) {
		visible[i][j]|=true;
	}
	
	//
	// Controlla che le coordinate siano entro i limiti del tabellone
	//
	public static boolean outOfLimits(int r, int c) {
		return (r<0 || r>=bSize || c<0 || c>=bSize);
	}
	
	//
	// Costruisce il campo di gioco
	//
	public static boolean setBoard(int size, int mine) {
		// set dimensions
		bSize=size;
		if (size< 0 || size > 9) { // board troppo grande
			return false;
		}
		board  =new boolean[size][size];
		visible=new boolean[size][size];  
		return true;
	}
		
	//
	// Piazza le mine
	//
	public static void setMines(int mine) {
		int r,c;
		nMine=mine;
		Random rnd=new Random();
		int i=0;
		do {
			r=rnd.nextInt(bSize);
			c=rnd.nextInt(bSize);
			if (isMine(r,c)) { // c'è gia una mina
				continue;
			}
			i++;
			board[r][c]=true;
		} while (i<mine);
	}
	
	
	
	//
	// Effettua il conteggio delle mine attorno ad una cella data
	//
	public static int contaMine(int i,int j) {
		int mineCount=0;
		for (int y=-1;y<2;y++) {
			for (int x=-1;x<2;x++) {
				// non conta una eventule mina nella cella corrente e scarta
				// le coordinate fuori dai limiti del campo di gioco.
				if ((x==0 && y==0) || outOfLimits((i+y),(j+x)))  continue;
			    if (isMine(i+y,j+x)) mineCount++;	
			}
		}
		return mineCount;
	}
	
	
	//
	// Restituisce il simbolo da stampare sulla base del contenuto della cella
	// e della modalità di gioco
	//
	public static char showSymbol(int i, int j, boolean easyMode, boolean visible) {
		final char sym_close='-';
		final char sym_void='o';
		final char sym_mine='*';
		
		if (visible) { // Se la cella è visibile
			if (isMine(i,j)) return sym_mine; // C'è una mina
			if (easyMode)                     // Modo easy restituisce il numero mine
 				return (char)(contaMine(i,j)+'0'); // Il carattere corrispondente al numero.
			else return sym_void;             // La cella è vuota    
		} else { 
			return sym_close;                 // La cella è chiusa
		}
	}
	
	//
	// Stampa la prima riga con le coordinate
	//
	public static void printHeader() {
		int c=1;
		System.out.print("  ");
		for (int j=0;j<bSize;j++) {
			System.out.print(c+" ");
			c++;
		}
		System.out.println();
	}
	
	//
	// Stampa il campo di gioco
	//
	public static void printBoard(boolean visible, boolean easyMode) {
		int c=1;
		printHeader();
		c=1;
		for (int i=0;i<bSize;i++) {
			System.out.print(c+" ");
			for (int j=0;j<bSize;j++) {
				System.out.print(showSymbol(i,j,easyMode,visible || isVisible(i,j))+" ");
			}
			c++;
			System.out.println();
		}
	}
		
	//
	// Motore del gioco
	//
	public static boolean gamePlay(boolean easyMode) {
		int r,c;
		// Calcola le celle da aprire al netto delle mine
		//
		nCell=(bSize*bSize)-nMine;
		// 
		// Loop del gioco
		do {
			//
			// Stampa campo di gioco
			//
			printBoard(false,easyMode);
			System.out.println();
			System.out.println("RESTANO "+nCell+" - Inserisci riga e colonna");	
		    //
			// Gestione input utente
			//
			r=(sc.nextInt()-1);
			c=(sc.nextInt()-1);	
			if (outOfLimits(r, c)) continue;
			//
			// Controlla la giocata 
			//
			if (isVisible(r,c)) {
				System.out.println("Cella già selezionata");
				continue;
			}
			if (isMine(r,c)) {
				return false;
			}
			//
			// Aggiorna il tabellone
			//
			nCell--;
			setVisible(r,c);
		} while (nCell>0); 
		return true;
	}
	
	public static void main(String[] args) {
		//
		// Definisce i limiti del tabellone di gioco
		//
		Random rnd=new Random();
		int size = rnd.nextInt(7)+3;
		int mine = rnd.nextInt((int)(size*size*0.1)+1)+1;
		boolean easyMode;
		//
		// Costruisce il tabellone
		//
		if (setBoard(size,mine)) {
			//
			// Piazza le mine
			//
			setMines(mine);
			//
			// Seleziona la modalità di gioco
			//
			System.out.println("Permi 1 per gioco easy");
			easyMode=(sc.nextInt()==1); 
			//
			// Gestione del gioco
			//
			if (gamePlay(easyMode)) {
				System.out.println("VITTORIA hai aperto tutte le celle");
			} else {
				System.err.println("  BOOM!!!!!!!!!!!! ");
				System.err.flush();
				System.out.println("Hai perso, ti restavano "+nCell+" da aprire");
			}
			//
			// Fine del gioco mostra il tabellone aperto
			//
			printBoard(true,false);
			//
		} else // errore nella creazione del tabellone 
			System.err.println("Dimensioni non valide");
	}
}
