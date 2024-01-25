# Challenges
## Representing Code
### Challenge 1
Start:
```
expr → expr ( "(" ( expr ( "," expr )* )? ")" | "." IDENTIFIER )+
     | IDENTIFIER
     | NUMBER
```
Result:

```
expr → expr tmp
expr → IDENTIFIER
expr → NUMBER

tmp  →  tmp tmp
tmp  →  "(" expr ")"
tmp  →  "(" expr tmp2 ")"
tmp  →  "." IDENTIFIER

tmp2  →  tmp2 tmp2
tmp2  →  "," expr
```
### Challenge 2
I don't know enough Haskell for this challenge
### Challenge 3
