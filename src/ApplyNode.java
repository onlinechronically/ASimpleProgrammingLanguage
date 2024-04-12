import java.util.Arrays;
import java.util.List;

final class ApplyNode extends AstNode {
  ApplyNode(AstNode fn, AstNode... args) {
    super(fn);
    this.getChildren().addAll(Arrays.stream(args).toList());
  }

  @Override
  AstNode eval(Environment env) {
    List<AstNode> args = this.getChildren().stream().skip(1).map(node -> node.eval(env)).toList();
    ClosureNode fn2 = (ClosureNode) this.getChildren().get(0).eval(env);
    Environment invokeEnv = fn2.getEnv().extend(fn2.getIdentifiers(), args);
    return fn2.getFunc().getChildren().get(0).eval(invokeEnv);
  }

  @Override
  public String toString() {
    return String.format("(%s%s)", this.getChildren().get(0).toString(), this.getChildren().isEmpty() ? "" : String.join(" ", this.getChildren().stream().map(AstNode::toString).toList()));
  }
}
