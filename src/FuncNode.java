import java.util.List;
import java.util.stream.Collectors;

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
    return this.getChildren().get(0).eval(env);
  }

  @Override
  public String toString() {
    return String.format("(λ (%s) %s)", String.join(", ", identifiers), this.getChildren().get(0));
  }
}
