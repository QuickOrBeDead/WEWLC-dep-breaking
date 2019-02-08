class CLateBindingDispatchDriver : public CDispatchDriver {
public:
  CLateBindingDispatchDriver();
  virtual ~CLateBindingDispatchDriver();
  ROOTID GetROOTID(int id) const;
  void BindName(int id, OLECHAR FAR *name);

private:
  CArray<ROOTID, ROOTID &> rootids;
};


/* This is the declaration of a little class in a C++ application. Users create CLate- BindingDispatchDrivers and then use the BindName method to associate names with IDs. We want to provide a different way of binding names when we use this class in a test. In C++, we can do this using Definition Completion. The BindName method was declared in the class header file.
 * How can we give it a different definition under test?
 * We include the header containing this class declaration in the test file and provide alternate definitions for the methods before our tests. */

// in the test file:
#include "LateBindingDispatchDriver.h"

// alternative ("mocked out") definitions
CLateBindingDispatchDriver::CLateBindingDispatchDriver() {}
CLateBindingDispatchDriver::~CLateBindingDispatchDriver() {}
ROOTID GetROOTID (int id) const { return ROOTID(-1); }
void BindName(int id, OLECHAR FAR *name) {}

TEST(AddOrder,BOMTreeCtrl) {
  CLateBindingDispatchDriver driver;
  CBOMTreeCtrl ctrl(&driver);

  ctrl.AddOrder(COrderFactory::makeDefault());
  LONGS_EQUAL(1, ctrl.OrderCount());
}

/* When we use Definition Completion in C or C++, we are pretty much obligated to create a separate executable for the tests that use the completed definitions. If we don’t, they will clash with the real definitions at link time. One other downside is that we now have two different sets of definitions for the methods of a class, one in a test source file and another in a production source file. This can be a big maintenance burden. It can also confuse debuggers if we don’t set up the environment correctly. For these reasons, I don’t recommend using Definition Completion except in the worst dependency situations. Even then, I recommend doing it just to break initial dependencies. Afterwards, you should bring the class under test quickly so that the duplicate definitions can be removed. */
