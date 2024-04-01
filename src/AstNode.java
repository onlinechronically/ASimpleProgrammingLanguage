import java.util.List;

abstract class AstNode {

  private final List<AstNode> CHILDREN;

  AstNode(List<AstNode> children) {
    this.CHILDREN = children;
  }

  AstNode(AstNode... children) {
    this(List.of(children));
  }

  abstract AstNode eval(Environment env);

  List<AstNode> getChildren() {
    return this.CHILDREN;
  }

  public abstract String toString();
}