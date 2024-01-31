package lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    Environment() {
        enclosing = null;
    }

    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    void define(String name, Object value) {
        values.put(name, value);
        //String valString = value == null ? "null" : value.toString();
        //System.out.println(name + " defined " + valString + " (define)");
    }

    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            //System.out.println(name.lexeme + " found value. (get)");
            return values.get(name.lexeme);
        }

        if (enclosing != null) {
            //System.out.println(name.lexeme + " enclosing not null. (get)");
            return enclosing.get(name);
        }

        throw new RuntimeError(name,
                "Undefined variable '" + name.lexeme + "'.(get)");
    }

    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            //System.out.println(name.lexeme + " assigned " + value.toString() + " (assign)");
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name,
                "Undefined variable '" + name.lexeme + "'.(assign)");
    }
}