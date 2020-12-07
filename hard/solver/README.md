# Linear Equation Solver

Program uses Gauss elimination to solve linear equation AX = B.
Input matrix may contain complex numbers. 
Possible results:
- no solutions
- infinitely many solutions
- result

Arguments: -in in.txt -out out.txt
```
Start solving the equation.
The solution is: [-0.08790367017180234+0.16860430293708895i, -0.07066655546255268-0.08768535579270159i, 0.6986968792228412+0.8726063493397374i]
Saved to file out.txt
```

File in.txt
>3 3\
>1+i 2+6i 7-8i 12\
>-7i 123 12+i i\
>11-11i 12+i -i 1+i


File out.txt:
>-0.08790367017180234+0.16860430293708895i\
>-0.07066655546255268-0.08768535579270159i\
>0.6986968792228412+0.8726063493397374i
