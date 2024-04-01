import java.util.Objects;

final class NumberNode extends AstNode {

  private final double VALUE;

  NumberNode(String value) {
    super();
    this.VALUE = Double.parseDouble(value);
  }

  NumberNode(double value) {
    this(Double.toString(value));
  }

  @Override
  AstNode eval(Environment env) {
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof NumberNode)) {
      return false;
    } else {
      return this.VALUE == ((NumberNode) o).VALUE;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.VALUE);
  }

  @Override
  public String toString() {
    return Double.toString(this.VALUE);
  }

  public double getValue() {
    return this.VALUE;
  }
}