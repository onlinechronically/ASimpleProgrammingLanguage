import java.util.LinkedList;
import java.util.List;

public class ConsNode extends AstNode{
    private AstNode value;
    private ConsNode next;

//    private Node first;

    public ConsNode() {
        value = null;
        next = null;
    }

    public ConsNode(AstNode value, ConsNode rest) {
        this.value = value;
        this.next = rest;
    }

    AstNode car() {
        return isEmpty() ? null : value;
    }

    ConsNode cdr() {
        return isEmpty() ? null : next;
    }

    AstNode first() {
        return car();
    }

    ConsNode rest() {
        return cdr();
    }

    boolean isEmpty() {
        return value == null;
    }

    @Override
    ConsNode eval(Environment env) {
        return value == null ? this : new ConsNode(car().eval(env), cdr().eval(env));
    }

    @Override
    public String toString() {
        List<AstNode> nodes = new LinkedList<>();
        ConsNode curr = this;
        while (curr.next != null) {
            nodes.add(curr.value);
            curr = curr.next;
        }
        return "[" + String.join(", ", nodes.stream().map(AstNode::toString).toList()) + "]";
    }
}
