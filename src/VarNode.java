final class VarNode extends AstNode {

  private final String NAME;

  VarNode(String name) {
    super();
    this.NAME = name;
  }

  /**
   * Interpret a variable. We look up the variable in the environment and
   * return the value associated with it.
   *
   * @param env - the environment in which to interpret the variable.
   * @return The result of the variable lookup after interpretation.
   */
  @Override
  AstNode eval(Environment env) {
    String id = this.NAME;
    AstNode res = env.lookup(id);
    return res.eval(env);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof VarNode)) {
      return false;
    } else {
      return this.NAME.equals(((VarNode) o).NAME);
    }
  }

  @Override
  public String toString() {
    return this.NAME;
  }
}