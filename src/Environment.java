import java.util.HashMap;
import java.util.Map;

final class Environment {

  private final Map<String, AstNode> ENV;
  private final Environment PARENT;

  Environment() {
    this(null);
  }

  Environment(Environment parent) {
    this.ENV = new HashMap<>();
    this.PARENT = parent;
  }

  /**
   * Looks up a variable in the current environment.
   *
   * @param id - the variable name.
   * @return the value bound to the variable, or null if it does not exist.
   */
  AstNode lookup(String id) {
    if (this.ENV.containsKey(id)) {
      return this.ENV.get(id);
    } else if (this.PARENT != null) {
      return this.PARENT.lookup(id);
    } else {
      return null;
    }
  }

  /**
   * Extends the current environment to contain a new variable binding.
   *
   * @param id    - the variable name.
   * @param value - the value to bind to the variable.
   * @return a new environment with the new binding.
   */
  Environment extend(String id, AstNode value) {
    Environment env = new Environment(this);
    env.ENV.put(id, value);
    return env;
  }
}