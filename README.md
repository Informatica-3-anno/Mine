Realizzazione  del gioco del Campo Minato semplificato

Obiettivo:
Implementare un programma in Java che simuli una versione semplificata del gioco "Campo Minato" su una griglia NxN,
utilizzando una matrice per rappresentare la griglia di gioco.

Specifiche:
  Griglia di gioco:
  La griglia è di dimensione 4x4. Ogni cella può contenere: Una mina (*) o una cella vuota (0).
  Le mine sono posizionate casualmente.

  Il programma deve:
  Mostrare la griglia di gioco inizialmente con tutte le celle coperte (.).
  Consentire al giocatore di scegliere una cella inserendo la riga e la colonna.
  Controllare la cella scelta:     
        - Se contiene una mina (*), il gioco termina con un messaggio di "Game Over".
        - Se è vuota (0), il programma deve rivelare la cella e consentire di continuare.
  Terminare il gioco con un messaggio di vittoria se il giocatore scopre tutte le celle senza mine.
  Alla fine della partita, mostrare la griglia completa (tutte le celle rivelate).

Requisiti tecnici:
Usare una matrice boolean[][] per rappresentare la griglia.
Implementare metodi separati per:
    -  Inizializzare la griglia.
    - Posizionare casualmente le mine.
    - Stampare la griglia di gioco.      
    - Gestire l'input del giocatore.
    - Controllare lo stato di vittoria o sconfitta.

Esempio di esecuzione:
Benvenuto nel Campo Minato!
0 1 2 3
0 . . . .
1 . . . .
2 . . . .
3 . . . .

Inserisci riga (0-3): 1
Inserisci colonna (0-3): 2

Cella sicura! Continua.
0 1 2 3
0 . . . .
1 . . 0 .
2 . . . .
3 . . . .

Inserisci riga (0-3): 0
Inserisci colonna (0-0): 0
Hai trovato una mina! Game Over.

Griglia finale:
0 1 2 3
0 * . . .
1 . . 0 .
2 . . * .
3 . . . *

Compito extra (opzionale):
Modificare il programma per mostrare, nelle celle vuote, il numero di mine adiacenti.
