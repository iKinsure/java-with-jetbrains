# Sorting Tool

Application can sort input from console or file. User choose data type and sort type.
Tool uses java comparators and get settings from standard arguments input.

|Argument|Params|Default|
|:---|:---:|---:|
|-dataType|word/line/long|word|
|-sortingType|natural/byCount|natural|
|-inputFile|\*filename\*|console|
|-outputFile|\*filename\*|console|



input.txt
>Lorem ipsum dolor sit amet, consectetur adipiscing elit.\
>Urna cursus eget nunc scelerisque viverra mauris in.\
>Pharetra vel turpis nunc eget lorem.


-dataType word -sortingType byCount -inputFile input.txt
``` 
Total: 22.
Lorem: 1 time(s), 5%
Pharetra: 1 time(s), 5%
Urna: 1 time(s), 5%
adipiscing: 1 time(s), 5%
amet,: 1 time(s), 5%
consectetur: 1 time(s), 5%
cursus: 1 time(s), 5%
dolor: 1 time(s), 5%
elit.: 1 time(s), 5%
in.: 1 time(s), 5%
ipsum: 1 time(s), 5%
lorem.: 1 time(s), 5%
mauris: 1 time(s), 5%
scelerisque: 1 time(s), 5%
sit: 1 time(s), 5%
turpis: 1 time(s), 5%
vel: 1 time(s), 5%
viverra: 1 time(s), 5%
eget: 2 time(s), 9%
nunc: 2 time(s), 9%
```



input.txt
>8 2 12 32 45 1 -32 32 32 \
>23 1 2 8 9 -3 23 32

-dataType long -sortingType natural -inputFile input.txt
``` 
Total: 17.
Sorted data: -32 -3 1 1 2 2 8 8 9 12 23 23 32 32 32 32 45 
``` 
