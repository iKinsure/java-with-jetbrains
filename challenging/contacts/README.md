# Contacts

Program for creating contacts (like on the mobile phone) and searching for people, organizations or custom categories.

```
[menu] Enter action (add, list, search, count, exit): add
Enter the type (person, organization): organization
Enter the name: local pizzeria
Enter the address: localpizzeria@email.com
Enter the number: +48123456789
The record added.

[menu] Enter action (add, list, search, count, exit): add
Enter the type (person, organization): person
Enter the name: John
Enter the surname: Kovalsky
Enter the birth: 05-03
Enter the gender (F, M): O
Wrong format!
Enter the number: 997
The record added.

[menu] Enter action (add, list, search, count, exit): count
The Phone Book has 5 records.

[menu] Enter action (add, list, search, count, exit): search
Enter search query: john
Found 3 results:
1. John Kovalsky
2. John Cenaa
3. Johnson & Johnson

[search] Enter action ([number], back, again): again
Enter search query: koval
Found 1 results:
1. John Kovalsky

[search] Enter action ([number], back, again): 1
Name: John
Surname: Kovalsky
Birth date: 05-03
Gender: [no data]
Number: 997
Time created: 2021-01-11T23:01
Time last edit: 2021-01-11T23:01

[record] Enter action (edit, delete, menu): edit
Select a field (name, surname, birth, gender, number): gender
Enter the gender (F, M): M
Saved
Name: John
Surname: Kovalsky
Birth date: 05-03
Gender: M
Number: 997
Time created: 2021-01-11T23:01
Time last edit: 2021-01-11T23:03

[menu] Enter action (add, list, search, count, exit): exit

```
