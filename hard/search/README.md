# Simple Search Engine

Application allows to read input data from files containing names and emails.
When program runs user can choose strategy and search for a name or email.

Searching strategies:
- all - intersections 
- any - union
- none - difference

-file persons.txt
```
=== Menu ===
1. Find a person
2. Print all people
0. Exit
> 2

=== List of people ===
Stephany War Galley
Feromard Marburies fernando_xxarbury@test.com
Kristyn Nix-Mixer nix-kris@test.com
Regenia Enderpearl
...
...
...
Kaaycee Graay
Victorinaa Froehlich victory@test.com
Roseaanne Graay
Ericaa Raadford hisaam@test.com
Elyse Paaulinger
Maalenaa Schwommer test1
Maalenaa Schozmmer test2
Maalenaa Schommer test3
Maalenaa Schommxers test5

=== Menu ===
1. Find a person
2. Print all people
0. Exit
> 1

Select a matching strategy: ALL, ANY, NONE
> ALL

Enter a name or email to search all suitable people.
> Maalenaa

Found 12 people:
Maalenaa Schommert
Maalenaa Schwommer test1
Maalenaa Schozmmer test2
Maalenaa Graaey
Maalenaa Schommer test3
Maalenaa Schommers test5
Maalenaa Schommers test1
Maalenaa Schommesr test2
Maalenaa Graaey
Maalenaa Schwommer test3
Maalenaa Schommxers test5
Maalenaa Schomcmer
```
