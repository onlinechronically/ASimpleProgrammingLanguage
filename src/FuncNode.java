import java.util.List;

final class FuncNode extends AstNode {
  public List<String> getIdentifiers() {
    return identifiers;
  }

  private List<String> identifiers;

  FuncNode(List<String> identifiers, AstNode body) {
    super(body);
    this.identifiers = identifiers;
  }

  @Override
  AstNode eval(Environment env) {
    return new ClosureNode(this, env);
  }

  @Override
  public String toString() {
    return String.format("(λ (%s) %s)", String.join(", ", identifiers), this.getChildren().get(0));
  }
}
