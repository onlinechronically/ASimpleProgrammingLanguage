class DefNode extends AstNode {
  private String id;

  DefNode(String id, AstNode arg) {
    super(arg);
    this.id = id;
  }

  @Override
  AstNode eval(Environment env) {
    env.set(id, getChildren().get(0));
    return env.lookup(id);
  }

  @Override
  public String toString() {
    return String.format("(define %s %s)", this.id, this.getChildren().get(0).toString());
  }
}
