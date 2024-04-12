import java.util.List;

public class ClosureNode extends AstNode {
    private FuncNode fn;
    private Environment env;
    public List<String> getIdentifiers() {
        return fn.getIdentifiers();
    }

    Environment getEnv() {
        return env;
    }

    FuncNode getFunc() {
        return fn;
    }
    ClosureNode(FuncNode fn, Environment env) {
        this.fn = fn;
        this.env = env;
    }

    @Override
    AstNode eval(Environment env) {
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s = %s", fn.toString(), env.toString());
    }
}