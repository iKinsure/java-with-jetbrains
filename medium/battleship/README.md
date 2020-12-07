# Battleship

The console battleship game for two players.
Available ships for each player:
- the Aircraft Carrier, 5
- the Battleship, 4
- the Submarine, 3
- the Cruiser, 3
- the Destroyer, 2

```
  1 2 3 4 5 6 7 8 9 10
A ~ ~ ~ ~ ~ ~ O ~ ~ ~
B ~ ~ ~ ~ ~ ~ O ~ ~ ~
C ~ ~ ~ ~ ~ ~ O ~ ~ ~
D ~ ~ ~ ~ ~ ~ O ~ ~ ~
E ~ ~ ~ ~ ~ ~ O ~ ~ ~
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
J O O O O ~ ~ ~ ~ ~ ~

Enter the coordinates of the Submarine (3 cells):
> J6 J9
Error! Wrong length of the Submarine! Try again: 
> J6 J8

  1 2 3 4 5 6 7 8 9 10
A ~ ~ ~ ~ ~ ~ O ~ ~ ~
B ~ ~ ~ ~ ~ ~ O ~ ~ ~
C ~ ~ ~ ~ ~ ~ O ~ ~ ~
D ~ ~ ~ ~ ~ ~ O ~ ~ ~
E ~ ~ ~ ~ ~ ~ O ~ ~ ~
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
J O O O O ~ O O O ~ ~

...

  1 2 3 4 5 6 7 8 9 10
A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
I M M ~ ~ ~ ~ ~ ~ ~ ~
J X X X ~ ~ ~ ~ ~ ~ ~
---------------------
  1 2 3 4 5 6 7 8 9 10
A X X X X X ~ ~ ~ ~ ~
B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
F O O O O ~ ~ ~ ~ ~ ~
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
H O O O ~ ~ ~ ~ ~ ~ ~
I ~ ~ ~ ~ ~ ~ ~ ~ ~ O
J O O O ~ ~ ~ ~ ~ ~ O

Player 1, it's your turn: 

> J4

You sank a ship!

Press Enter and pass the move to another player

  1 2 3 4 5 6 7 8 9 10
A X X X X X ~ ~ ~ ~ ~
B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
---------------------
  1 2 3 4 5 6 7 8 9 10
A ~ ~ ~ ~ ~ ~ O ~ ~ ~
B ~ ~ ~ ~ ~ ~ O ~ ~ ~
C ~ ~ ~ ~ ~ ~ O ~ ~ ~
D O ~ ~ ~ ~ ~ O ~ ~ ~
E O ~ ~ ~ ~ ~ O ~ ~ ~
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
H O O O ~ ~ ~ ~ ~ ~ ~
I M M ~ ~ ~ ~ ~ ~ ~ ~
J X X X X ~ O O O ~ ~

Player 2, it's your turn: 

> A6

You missed!

Press Enter and pass the move to another player

  1 2 3 4 5 6 7 8 9 10
A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
I M M ~ ~ ~ ~ ~ ~ ~ ~
J X X X X ~ ~ ~ ~ ~ ~
---------------------
  1 2 3 4 5 6 7 8 9 10
A X X X X X M ~ ~ ~ ~
B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
F O O O O ~ ~ ~ ~ ~ ~
G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
H O O O ~ ~ ~ ~ ~ ~ ~
I ~ ~ ~ ~ ~ ~ ~ ~ ~ O
J O O O ~ ~ ~ ~ ~ ~ O

Player 1, it's your turn: 

...

```
