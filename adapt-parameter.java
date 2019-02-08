public class ARMDispatcher {
public void populate(ParamSource src) {
    String[] values = src.getParameterValues(pageStateName);
    // ...
  }
  // ...
}

interface ParamSource {
  getParameterValues(String);
}

class FakeParameterSource implements ParamSource {
  public String value;
  public String getParameterForName(String name) {
    return value;
  }
}

// production impl
class ServletParameterSource implements ParameterSource {
  private HttpServletRequest request;

  public ServletParameterSource(HttpServletRequest request) {
    this.request = request;
  }

  String getParameterValue(String name) {
    String [] values = request.getParameterValues(name);
    if (values == null || values.length < 1)
      return null;
    return values[0];
  }
}

// 1. Create the new interface that you will use in the method. Make it as sim- ple and communicative as possible, but try not to create an interface that will require more than trivial changes in the method.
// 2. Create a production implementer for the new interface.
// 3. Create a fake implementer for the interface.
// 4. Write a simple test case, passing the fake to the method.
// 5. Make the changes you need to in the method to use the new parameter.
// 6. Run your test to verify that you are able to test the method using the fake.
