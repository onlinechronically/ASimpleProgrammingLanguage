import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterpTest {

  @Test
  void testInterpret() {
    assertAll(
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
                            .eval(new Environment())));
  }
}