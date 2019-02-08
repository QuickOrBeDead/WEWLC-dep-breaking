// Sometimes you have to work with a cluster of methods on a class, and the dependencies that keep you from instantiating the class are unrelated to the cluster. By “unrelated,” I mean that the methods you want to work with don’t directly or indirectly reference any of the bad dependencies. You could do Expose Static Method (345) or Break Out Method Object (330) repeatedly, but that wouldn’t necessarily be the most direct way to deal with the dependency.

// In this situation, you can pull up the cluster of methods, the feature, into an abstract superclass. When you have that abstract superclass, you can subclass it and create instances of the subclass in your tests. Here is an example:

public class Scheduler {
  private List items;
  public void updateScheduleItem(ScheduleItem item) throws SchedulingException {
    try {
      validate(item);
    }
    catch (ConflictException e) {
      throw new SchedulingException(e);
    }
  }
  private void validate(ScheduleItem item) throws ConflictException {
    // make calls to a database
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

// Suppose that we want to make modifications to getDeadTime, but we don’t care about updateScheduleItem.
// It would be nice not to have to deal with the dependency on the database at all. We could try to use Expose Static Method (345), but we are using many non-static features of the Scheduler class. Break Out Method Object (330) is another possibility, but this is a rather small method, and those dependencies on other methods and fields of the class will make the work more involved than we want it to be just to get the method under test.
