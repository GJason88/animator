/**
 * An interaction with the user consists of some input to send the program and some output to
 * expect.  We represent it as an object that takes in two StringBuilders and produces the intended
 * effects on them.
 */
public interface Interaction {

  /**
   * Method for testing different inputs, appending inputs to a given input StringBuilder, and
   * appending outputs to a given output StringBuilder.
   *
   * @param in  A given input
   * @param out The output based on the given input
   */
  void apply(StringBuilder in, StringBuilder out);
}