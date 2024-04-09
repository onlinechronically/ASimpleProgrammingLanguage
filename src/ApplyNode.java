import java.util.List;

final class ApplyNode extends AstNode {
  private String fn;

  ApplyNode(String fn, AstNode... args) {
    super(args);
    this.fn = fn;
  }

  @Override
  AstNode eval(Environment env) {
    List<AstNode> args = this.getChildren().stream().map(node -> node.eval(env)).toList(); // evaluate all the argument nodes
    Environment invokeEnv = env.extend(((FuncNode) env.lookup(fn)).getIdentifiers(), args); // extend the given Environment to include all the argument for the given function
    AstNode functionNode = env.lookup(fn); // find the function node to evaluate
    return functionNode.eval(invokeEnv);
  }

  @Override
  public String toString() {
    return String.format("(%s%s)", this.fn, this.getChildren().isEmpty() ? "" : String.join(" ", this.getChildren().stream().map(n -> n.toString()).toList()));
  }
}
