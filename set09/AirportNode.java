/**
 * An AirportNode is a
 * new AirportNode(String Int)
 * Interpretation: An AirportNode represents an airport with
 * a Name representing the name of an airport with
 * a 3 letter code for the city
 * a Priority representing the Airport priority for
 * use in Dijikstra's algorithm.
 * Template:
 * public ?? AirportNode-fn() {
 * (...(getName())
 * (getPriority()))
 * }
 */
public class AirportNode {

  private String name;
  private int priority;

  /**
   * GIVEN:
   *
   * @param name     a String representing the name of the airport
   * @param priority an Int representing the priority of the airport
   *                 RETURNS:
   * @returns implicitly returns this AirportNode with the given name and priority
   * EXAMPLE:
   * AirportNode test = new AirportNode("BOS", 2);
   * STRATEGY:
   * use simpler functions (constructor)
   */
  AirportNode(String name, int priority) {
    this.name = name;
    this.priority = priority;
  }

  /**
   * RETURNS:
   *
   * @return a String representing the name of this AirportNode
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).getName() => "BOS"
   * (new AirportNode("LAX", 20)).getName() => "LAX"
   * STRATEGY:
   * use simpler functions (on private variables)
   */
  public String getName() {
    return name;
  }


  /**
   * RETURNS:
   *
   * @return an Int representing the priority of this AirportNode
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).getPriority() => 2
   * (new AirportNode("LAX", 20)).getPriority() => 20
   * STRATEGY:
   * use simpler functions (on private variables)
   */
  public int getPriority() {
    return priority;
  }

  /**
   * GIVEN:
   *
   * @param priority an Int representing the priority of this AirportNode
   *                 RETURNS:
   * @return: this AirportNode with the given priority and pre-existing name
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).setPriority(18).getPriority() => 18
   * (new AirportNode("LAX", 20)).setPriority(4).getPriority() => 4
   * STRATEGY:
   * use simpler functions (on private variables)
   */
  public AirportNode setPriority(int priority) {
    this.priority = priority;
    return this;
  }

  /**
   * GIVEN:
   *
   * @param name a String representing the new name of this AirportNode
   *             RETURNS:
   * @return: this AirportNode with the given name and pre-existing priority
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).setName("DET").getName() => "DET"
   * (new AirportNode("LAX", 20)).setName("LGA").getName() => "LGA"
   * STRATEGY:
   * use simpler functions (on private variables)
   */
  public AirportNode setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * RETURNS:
   *
   * @return: String representing this AirportNode
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).setName("DET").toString() => " BOS:2"
   * (new AirportNode("LAX", 20)).setName("LGA").toString() => " LAX:20"
   * STRATEGY:
   * use simpler functions (on private variables)
   */
  @Override
  public String toString() {
    return " " + name + ":" + priority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    AirportNode that = (AirportNode) o;
    return getName().equals(that.getName()) && getPriority() == that
        .getPriority();
  }

  @Override
  public int hashCode() {
    int result = getName() != null ? getName().hashCode() : 0;
    result = 31 * result + getPriority();
    return result;
  }
}



