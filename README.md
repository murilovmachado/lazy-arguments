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
