import java.util.Objects;

final class BoolNode extends AstNode {

  private final boolean VALUE;

  BoolNode(String value) {
    super();
    this.VALUE = Boolean.parseBoolean(value);
  }

  BoolNode(boolean value) {
    this(Boolean.toString(value));
  }

  @Override
  AstNode eval(Environment env) {
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof BoolNode)) {
      return false;
    } else {
      return this.VALUE == ((BoolNode) o).VALUE;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.VALUE);
  }

  @Override
  public String toString() {
    return Boolean.toString(this.VALUE);
  }

  public boolean getValue() {
    return this.VALUE;
  }
}