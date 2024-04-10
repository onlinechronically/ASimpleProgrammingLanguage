import java.util.Arrays;
import java.util.List;

final class ApplyNode extends AstNode {
  ApplyNode(AstNode fn, AstNode... args) {
    super(fn);
    this.getChildren().addAll(Arrays.stream(args).toList());
  }

  @Override
  AstNode eval(Environment env) {
    List<AstNode> args = this.getChildren().stream().map(node -> node.eval(env)).toList(); // evaluate all the argument nodes
    Environment invokeEnv = env.extend(((FuncNode) env.lookup(fn)).getIdentifiers(), args); // extend the given Environment to include all the argument for the given function
    AstNode functionNode = env.lookup(fn); // find the function node to evaluate
    return functionNode.eval(invokeEnv);
    List<AstNode> args = this.getChildren().stream().skip(1).map(node -> node.eval(env)).toList(); // evaluate all the argument nodes
    FuncNode fn2 = (FuncNode) this.getChildren().get(0).eval(env);
    Environment invokeEnv = env.extend(fn2.getIdentifiers(), args); // extend the given Environment to include all the argument for the given function
    return fn2.getChildren().get(0).eval(invokeEnv);
  }

  @Override
  public String toString() {
    return String.format("(%s%s)", this.getChildren().get(0).toString(), this.getChildren().isEmpty() ? "" : String.join(" ", this.getChildren().stream().map(n -> n.toString()).toList()));
  }
}
