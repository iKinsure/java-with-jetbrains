# Guess the Animal

Simple interactive game where computer will try to guess the animal
that the person has in mind with helping questions. Project uses 
tree data structure and traversing through its nodes. Tree decision 
is stored in a file.

User can control storage format by defining -type argument:
- xml
- yaml
- json

More settings in application.xml file:
- base storage file name
- language

Example game
```
can it fly?
> no
is it a cat?
> no
I give up. What animal do you have in mind?
Enter the animal:
> elephant
Specify a fact that distinguishes a cat from an elephant.
The sentence should be of the format: It can/has/is ....
> it is big
Is the statement correct for an elephant?
> yes
I have learned the following facts about animals:
 - the elephant is big
 - the cat isn't big
I can distinguish these animals by asking the question:
 - is it big?
Awesome! I’ve learned so much about animals!
Want to play again?
> yes
Excellent! I’ve learned so much about animals!
Let’s play a game!
You think of an animal, and I guess it.
Press enter when you’re ready.

can it fly?
> no
is it big?
> no
is it a cat?
> yes
It''s great that I got it right!
Thank you for playing! 
Want to play again?
> no

What do you want to do:

1. Play the guessing game
2. List of all animals
3. Search for an animal
4. Calculate statistics
5. Print the Knowledge Tree
6. Delete an animal
0. Exit
```

File animals.json after game
```
{
  "root" : {
    "data" : "it can fly",
    "left" : {
      "data" : "it is big",
      "left" : {
        "data" : "a cat"
      },
      "right" : {
        "data" : "an elephant"
      }
    },
    "right" : {
      "data" : "a bird"
    }
  }
}
```
