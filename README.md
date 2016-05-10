# lazy-arguments
This project aims to increment a functional language by giving the developer the power to decide if a function argument should have eager or lazy evaluation.

## Grammar
```
Programa ::= Expressao
Expressao ::= Valor
| ExpUnaria
| ExpBinaria
| ExpDeclaracao
| Id 
| Aplicacao
| IfThenElse
 
Valor ::= ValorConcreto
ValorConcreto ::= ValorInteiro | ValorBooleano | ValorString

ExpUnaria ::= "-" Expressao | "not" Expressao | "length" Expressao

ExpBinaria ::= Expressao "+" Expressao
| Expressao "-" Expressao
| Expressao "and" Expressao
| Expressao "or" Expressao
| Expressao "==" Expressao
| Expressao "++" Expressao
 
ExpDeclaracao ::= "let" DeclaracaoFuncional "in" Expressao
DeclaracaoFuncional ::= DecVariavel
| DecFuncao
| DeclaracaoFuncional "," DeclaracaoFuncional

DecVariavel ::= "var" Id "=" Expressao
DecFuncao ::= "fun" ListArg "=" Expressao

ArgumentoFuncao ::= ArgumentoEager | ArgumentoLazy
ArgumentoLazy ::= "#" ArgumentoEager
ArgumentoEager ::= Id
ListArg ::= ArgumentoFuncao  |  ArgumentoFuncao ListArg


Aplicacao:= Id"(" ListExp ")"
ListExp ::= Expressao  |  Expressao, ListExp

IfThenElse ::= "if" Expressao "then" Expressao "else" Expressao
```

## Using the language

### Input
Your input code should be in the file input.txt

### Running
Run the class Func1Parser

### Modifying the grammar
Modify the file javacc/Functional1.jj and run mvn clean package

## Code Examples
For the following examples consider:
- ```fat(number)```: factorial of a number
- ```infinite(number)```: an infinite calculation

### Scenario 1: Avoiding slow computations
- Consider the function: ```fun first a b = a```
- Consider the application: ```first(20, fat(100))```

This function evaluates ```20```, then evaluates ```fat(100)```, and only after that it returns ```a```, that is ```20```.
In this case, having ```#b``` as a lazy argument would result in a much faster computation: ```fun first a #b = a```.
That happens because ```b``` is never called in the function body, so it doesn't need to be evaluated.

### Scenario 2: Avoiding infinite computations
- Consider the function: ```fun first a b = a```
- Consider the application: ```first(20, infinite(1))```

This program would never terminate, because the second argument ```infinite(1)``` never ends.
In this case, having ```#b``` as a lazy argument the program would compute an answer: ```fun first a #b = a```.
That happens because ```b``` is not needed for computing the answer, so it's never evaluated.

### Scenario 3: When there's no difference using lazy evaluation
- Consider the function: ```fun minus a b = a - b```
- Consider the application: ```minus(fat(5), fat(4))```

This function would have no performance differences if any of the arguments were lazy: ```fun minus #a #b = a - b```.
That happens because both ```a``` and ```b``` are used only once in the function body, so they would have to be evaluated once, at some point. Performance wise, it doesn't matter if they get evaluated before executing the function body or during body evaluation.

### Scenario 4: What's needed to achieve true lazy evaluation
- Consider the function: ```fun triple #a = a + a + a```
- Consider the application: ```triple(fat(100))```

In this case, the argument ```#a``` is evaluated 3 times. (The factorial of 100 would be calculated 3 times)
But, if the argument was ```a``` instead of ```#a```, it would be evaluated only once, and the execution would be much faster.

In this project, what was achieved until now was actually outermost evaluation. If a lazy argument appears more than once in the body of the function, it will be evaluated more than once. That's what this scenario illustrates, and shows that it slows down the performance. In true lazy evaluation, the lazy argument is evaluated only once, even when it appears multiple time in the body of the function.

True lazy evaluation is the next step in evolving this project. Contributors are welcome =)
