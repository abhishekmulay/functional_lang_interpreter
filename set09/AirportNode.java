/**
 * An AirportNode is a:
 * CONSTRUCTOR TEMPLATE:
 * =====================
 *    new AirportNode(String Int)
 *
 * Interpretation:
 * ===============
 * An AirportNode represents an airport node with following properties:
 *
 * name :  a String representing the name of an airport with
 *         a 3 letter code for the city
 *
 * priority : an int value representing the priority of this airportnode for
 *            Dijikstras algorithm.
 */
public class AirportNode {

  // airport code of this airport
  private String name;
  // priority for this node
  private int priority;

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param name     a String representing the name of the airport
   * @param priority an Int representing the priority of the airport
   *
   * RETURNS:
   * @returns implicitly returns this AirportNode with the given name and
   * priority
   *
   * EXAMPLE:
   * AirportNode test = new AirportNode("BOS", 2);
   *
   */
  AirportNode(String name, int priority) {
    this.name = name;
    this.priority = priority;
  }

  //___________________________________________________________________________
  /**
   * RETURNS:
   * @return a String representing the name of this AirportNode
   *
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).getName() => "BOS"
   * (new AirportNode("LAX", 20)).getName() => "LAX"
   *
   */
  public String getName() {
    return name;
  }


  //___________________________________________________________________________
  /**
   * RETURNS:
   * @return an Int representing the priority of this AirportNode
   *
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).getPriority() => 2
   * (new AirportNode("LAX", 20)).getPriority() => 20
   *
   */
  public int getPriority() {
    return priority;
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param priority an Int representing the priority of this AirportNode
   *
   * RETURNS:
   * @return: this AirportNode with the given priority and pre-existing name
   *
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).setPriority(18).getPriority() => 18
   * (new AirportNode("LAX", 20)).setPriority(4).getPriority() => 4
   *
   */
  public AirportNode setPriority(int priority) {
    this.priority = priority;
    return this;
  }

  //___________________________________________________________________________
  /**
   * GIVEN:
   * @param name a String representing the new name of this AirportNode
   *
   * RETURNS:
   * @return: this AirportNode with the given name and pre-existing priority
   *
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).setName("DET").getName() => "DET"
   * (new AirportNode("LAX", 20)).setName("LGA").getName() => "LGA"
   */
  public AirportNode setName(String name) {
    this.name = name;
    return this;
  }

  //___________________________________________________________________________
  /**
   * RETURNS:
   * @return: String representing this AirportNode
   *
   * EXAMPLE:
   * (new AirportNode("BOS", 2)).setName("DET").toString() => " BOS:2"
   * (new AirportNode("LAX", 20)).setName("LGA").toString() => " LAX:20"
   *
   */
  @Override
  public String toString() {
    return " " + name + ":" + priority;
  }

  //___________________________________________________________________________

  /**
   * GIVEN:
   * @param o an Object to be compared with current object
   *
   * RETURNS:
   * @return true iff the two AirportNodes have same properties name and
   * priorities.
   *
   * EXAMPLE:
   *  (new AirportNode("BOS", 2)).equal(new AirportNode("BOS", 2)) => true
   *  (new AirportNode("BOS", 2)).equal(new AirportNode("LGA", 12)) => false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    AirportNode that = (AirportNode) o;
    return getName().equals(that.getName()) && getPriority() == that
        .getPriority();
  }

  //___________________________________________________________________________

  /**
   * GIVEN:
   * No implicit parameter, the method can access the object as "this"
   *
   * EXAMPLE:
   *    (new AirportNode("BOS", 2)).hashCode() => 2044700
   *
   * @return hashcode value for this object as int
   */
  @Override
  public int hashCode() {
    int result = getName() != null ? getName().hashCode() : 0;
    result = 31 * result + getPriority();
    return result;
  }
  //___________________________________________________________________________
}



