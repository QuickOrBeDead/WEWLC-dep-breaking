// Sometimes you have to work with a cluster of methods on a class, and the dependencies that keep you from instantiating the class are unrelated to the cluster. By “unrelated,” I mean that the methods you want to work with don’t directly or indirectly reference any of the bad dependencies. You could do Expose Static Method (345) or Break Out Method Object (330) repeatedly, but that wouldn’t necessarily be the most direct way to deal with the dependency.

// In this situation, you can pull up the cluster of methods, the feature, into an abstract superclass. When you have that abstract superclass, you can subclass it and create instances of the subclass in your tests. Here is an example:

public class Scheduler extends SchedulingServices {
  public void updateScheduleItem(ScheduleItem item) throws SchedulingException {
    // ...
  }
  private void validate(ScheduleItem item) throws ConflictException {
    // make calls to the database
  }
}

// We’ve pulled getDeadtime (the feature we want to test) and all of the features it uses into an abstract class.
public abstract class SchedulingServices {
  protected List items;
  protected boolean notShared(ScheduleItem item) {
    // ...
  }
  protected int getClockTime() {
    // ...
  }
  protected int getStandardFinish(ScheduleItem item) {
    // ...
  }

  public int getDeadTime() {
    int result = 0;
    for (Iterator it = items.iterator(); it.hasNext()) {
      ScheduleItem item = (ScheduleItem)it.next();
      if (item.getType() != ScheduleItem.TRANSIENT
          && notShared(item)) {
        result += item.getSetupTime() + clockTime();
          }
      if (item.getType() != ScheduleItem.TRANSIENT) {
        result += item.finishingTime();
      }
      else {
        result += getStandardFinish(item);
      }
      }
    return result;
  }
}

// Now we can make a testing subclass that allows us to access those methods in a test harness:
public class TestingSchedulingServices extends SchedulingServices {
  public TestingSchedulingServices() {

  }
  public void addItem(ScheduleItem item) {
    items.add(item);
  }
}

import junit.framework.*;

class SchedulingServicesTest extends TestCase {
  public void testGetDeadTime() {
    TestingSchedulingServices services = new TestingSchedulingServices();
    services.addItem(new ScheduleItem("a", 10, 20, ScheduleItem.BASIC));
    assertEquals(2, services.getDeadtime());
  }
}

