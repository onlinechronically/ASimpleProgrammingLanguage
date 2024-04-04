final class ProgramNode extends AstNode {

  ProgramNode(AstNode... args) {
    super(args);
  }

  /**
   * Evaluate a sequence of statements, in a given Environment
   * @param env The given Environment
   * @return The resulting AstNode
   */
  @Override
  AstNode eval(Environment env) {
    AstNode lastNode = null;
    for (AstNode child : getChildren()) {
      if (child instanceof DefNode) {
        lastNode = null;
        child.eval(env);
      } else {
        lastNode = child.eval(env);
      }
    }
    return lastNode;
  }

  /**
   * Get a String representation of a sequence of statements
   * @return The resulting String
   */
  @Override
  public String toString() {
    return String.join("\n", this.getChildren().stream().map(AstNode::toString).toList());
  }
}
