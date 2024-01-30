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
## Parsing Expressions
### Challenge 1a
``` java
// in  Parser.java
private Expr comma() {
        Expr expr = ternary();
        if (match(COMMA)) {
            Token operator = previous();
            Expr right = ternary();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }
```
### Challenge 1b
``` java
// in Parser.java
private Expr comma() {
        Expr expr = ternary();
        if (match(COMMA)) {
            Token operator = previous();
            Expr right = ternary();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }
```
### Challenge 2
``` java
// in Parser.java
private Expr ternary() {
        Expr expr = equality();
        if (match(QUESTION_MARK)) {
            Expr middle = equality();
            if (match(COLON)) {
                Token ternaryOperator = new Token(TERNARY_CONDITIONAL_OPERATOR, "?:", null, previous().line);
                Expr right = equality();
                return new Expr.Ternary(ternaryOperator, expr, middle, right);
            }
            else {
                throw error(peek(), "Ternary operator missing colon.");
            }
        }
        return expr;
    }
```

```` java
// in Expr.java
static class Ternary extends Expr {
    Ternary(Token operator, Expr left, Expr middle, Expr right) {
      this.operator = operator;
      this.left = left;
      this.middle = middle;
      this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitTernaryExpr(this);
    }

    final Token operator;
    final Expr left;
    final Expr middle;
    final Expr right;
  }
````

```` java
// in Expr.java add to Visitor interface
    R visitTernaryExpr(Ternary expr);
  }
````
### Challenge 3

