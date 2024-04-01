final class IfNode extends AstNode {

  IfNode(AstNode predicate, AstNode consequent, AstNode alternative) {
    super(predicate, consequent, alternative);
  }

  /**
   * Interpret an if statement.
   *
   * @param env - the environment in which to interpret the if statement.
   * @return The result of the if statement.
   */
  @Override
  AstNode eval(Environment env) {
    AstNode pred = this.getChildren().get(0);
    AstNode cons = this.getChildren().get(1);
    AstNode alt = this.getChildren().get(2);

    // Evaluate the predicate, then interpret one way or the other.
    boolean cond = ((BoolNode) pred.eval(env)).getValue();
    if (cond) {
      return cons.eval(env);
    } else {
      return alt.eval(env);
    }
  }

  @Override
  public String toString() {
    AstNode p = this.getChildren().get(0);
    AstNode c = this.getChildren().get(1);
    AstNode a = this.getChildren().get(2);
    return String.format("(if %s %s %s)", p.toString(), c.toString(), a.toString());
  }
}