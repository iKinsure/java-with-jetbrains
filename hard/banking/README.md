# Simple Banking System

Application is simple account manager which stores all data in sqlite database.
User can generate cards, logs into them and send money. 
Bank check each card by luhm formula and provide unique number.

Arguments: -fileName \*database name\*
```
1. Create an account
2. Log into account
0. Exit
> 1

Your card has been created
Your card number:
4000002688071370
Your card PIN:
0855

1. Create an account
2. Log into account
0. Exit
> 1

Your card has been created
Your card number:
4000003040832244
Your card PIN:
9550

1. Create an account
2. Log into account
0. Exit
> 2

Enter your card number:
> 4000002688071370
Enter your PIN:
> 0855

You have successfully logged in

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
> 2

Enter income
> 155
Income was added!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
> 3

Transfer
Enter card number:
> 4000002688071373
Probably you made mistake in the card number. Please try again!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
> 3

Transfer
Enter card number:
> 4000002688071370
You cannot send money to yourself!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
> 3

Transfer
Enter card number:
> 4000003040832244
Enter how much money you want to transfer:
> 155
Success!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
> 1

Balance: 0

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
> 4

The account has been closed!

1. Create an account
2. Log into account
0. Exit
> 2

Enter your card number:
> 4000002688071370
Enter your PIN:
> 0855

Wrong card number or PIN!
```
