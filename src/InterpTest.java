import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterpTest {

    @Test
    void testInterpret() {
    assertAll(
            // Given Tests
            () -> assertEquals(new NumberNode("42"),
                    new NumberNode("42")
                            .eval(new Environment())),
            () -> assertEquals(new BoolNode(true),
                    new PrimNode("eq?",
                            new NumberNode(42),
                            new PrimNode("-",
                                    new NumberNode(100),
                                    new NumberNode(58)))
                            .eval(new Environment())),
            () -> assertEquals(new NumberNode("42"),
                    new LetNode("x", new NumberNode("42"), new VarNode("x"))
                            .eval(new Environment())),
            () -> assertEquals(new NumberNode("42"),
                    new PrimNode("+", new NumberNode("1"), new NumberNode("41"))
                            .eval(new Environment())),
            () -> assertEquals(new NumberNode("42"),
                    new LetNode("x",
                            new NumberNode("1"),
                            new LetNode("y",
                                    new NumberNode("41"),
                                    new PrimNode("+", new VarNode("x"), new VarNode("y"))))
                            .eval(new Environment())),
            //Other tests
                () -> assertEquals(new BoolNode(true), new BoolNode(true).eval(new Environment())),
                () -> assertEquals(new NumberNode(100), new LetNode("x",
                        new NumberNode(10),
                        new LetNode("y",
                                new NumberNode(32),
                                new IfNode(
                                        new PrimNode("eq?", new PrimNode("+", new VarNode("x"), new VarNode("y")), new NumberNode(42)),
                                        new NumberNode(100),
                                        new NumberNode(0)
                                ))).eval(new Environment())),
                () -> assertEquals(new NumberNode(10), new LetNode("x", new NumberNode(10), new VarNode("x")).eval(new Environment())),
                () -> assertEquals(new NumberNode(42), new ProgramNode(
                        new PrimNode("+", new NumberNode(5), new PrimNode("*", new NumberNode(10), new PrimNode("-", new NumberNode(5), new NumberNode(2)))),
                        new PrimNode("-", new NumberNode(100), new NumberNode(58))
                ).eval(new Environment())),
                () -> assertEquals(new NumberNode(5), new ProgramNode(
                        new DefNode("x", new NumberNode(5)),
                        new VarNode("x")
                ).eval(new Environment())),
                () -> assertEquals(new NumberNode(12), new ProgramNode(
                        new DefNode("add2", new FuncNode(List.of("n"), new PrimNode("+", new VarNode("n"), new NumberNode(2)))),
                        new NumberNode(12),
                        new ApplyNode("add2", new NumberNode(10))
                ).eval(new Environment())),
                () -> assertEquals(new NumberNode(120), new ProgramNode(
                        new DefNode("!",
                                new FuncNode(
                                        List.of("n"),
                                        new IfNode(
                                                new PrimNode("eq?",
                                                        new VarNode("n"), new NumberNode(0)), new NumberNode(1),
                                                new PrimNode("*",
                                                        new VarNode("n"),
                                                        new ApplyNode("!",
                                                                new PrimNode("-",
                                                                        new VarNode("n"),
                                                                        new NumberNode(1))))))),
                        new ApplyNode("!", new NumberNode(5))).eval(new Environment()))
        );
    }

    @Test
    void testToString() {
        assertAll(
                () -> assertEquals("(define a 10.0)", new DefNode("a", new NumberNode(10)).toString()),
                () -> assertEquals("(define b true)", new DefNode("b", new BoolNode(true)).toString()),
                () -> assertEquals("(define add2 (λ (n) (+ [n, 2.0])))", new DefNode("add2", new FuncNode(List.of("n"), new PrimNode("+", new VarNode("n"), new NumberNode(2)))).toString()),
                () -> assertEquals("""
                        (define a 5.0)
                        (define b 10.0)
                        (define c 15.0)""", new ProgramNode(
                        new DefNode("a", new NumberNode(5.0)),
                        new DefNode("b", new NumberNode(10.0)),
                        new DefNode("c", new NumberNode(15.0))
                ).toString())
        );
    }
}