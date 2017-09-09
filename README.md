## A parser and interpreter for a simple functional programming language.

Files:
```
        Actions.java    // action routines called by parser
        Parser.java     // strong LL(1) parser generated from cfg.pg
        Scanner.java    // lexical analyzer, written by hand
        Tokens.java     // lists of tokens, only for error messages

        cfg.pg          // LL(1) grammar, in ParseGen format
```

#### The concrete syntax is:

```
<pgm>     ::=  <defn>
              ::=  <defn> ; <pgm>
    <defn>    ::=  <id> = <const>
              ::=  <id> (<formals>) <expr>
    <const>   ::=  true 
              ::=  false
              ::=  <int>
    <lambda>  ::=  (λ ( <formals> ) <expr>)
    <expr>    ::=  <const>
              ::=  <id>
              ::=  <lambda>
              ::=  <arith>
              ::=  <call>
              ::=  <if>
              ::=  ( <expr> )
    <arith>   ::=  <expr> <op> <expr>
    <call>    ::=  <expr> (<exprs>)
    <if>      ::=  if <expr> then <expr> else <expr>
    
    <op>      ::=  < | = | > | + | - | *
    
    <formals>  ::=  <id>
               ::=  <id> , <formals>
    <exprs>    ::=  <expr>
               ::=  <expr> , <exprs>
    
    <int> and <id> are described by regular expressions:
    
        digit digit*
    
        letter (letter | digit)*
 ```
