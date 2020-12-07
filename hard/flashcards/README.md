# Flashcards

Flashcards program allows user to memorize definitions. User can load txt file or add manually cards and start learning. 
After study there is possibility to generate logs and save cards with statistics. 

Optional arguments:
- -import filename - load set of cards with stats before start
- -export filename - save set of cards with stats when closing

Example file with cards:
>great britain;london;1\
>poland;warsaw;3\
>russia;moscow;4\
>germany;berlin;5\
>austria;vienna;8

Programme action:
```
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> import
File name:
> capitals.txt
5 cards have been loaded.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> add
The card:
> france
The definition of the card:
> paris
The pair ("france":"paris") has been added.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> add
The card:
> france
The card "france" already exists.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> add
The card:
> usa
The definition of the card:
> paris
The definition "paris" already exists.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> ask
How many times to ask?
> 3

Print the definition of "great britain":
> london
Correct!

Print the definition of "poland":
> cracow
Wrong. The right answer is "warsaw".

Print the definition of "russia":
> berlin
Wrong. The right answer is "moscow", but your definition is correct for "germany".

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> hardest card
The hardest card is "austria". You have 8 errors answering it.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> log
File name:
> log.txt
The log has been saved.
```
