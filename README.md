## Dependency Breaking Techniques from Working Effectively With Legacy Code (WEWLC)

### Adapt Parameter
1. Create the new interface that you will use in the method. Make it as sim- ple and communicative as possible, but try not to create an interface that will require more than trivial changes in the method.
2. Create a production implementer for the new interface.
3. Create a fake implementer for the interface.
4. Write a simple test case, passing the fake to the method.
5. Make the changes you need to in the method to use the new parameter.
6. Run your test to verify that you are able to test the method using the fake.

### Break Out Method Object
1. Create a class that will house the method code.
2. Create a constructor for the class and Preserve Signatures (312) to give it an exact copy of the arguments used by the method. If the method uses an instance data or methods from the original class, add a reference to the original class as the first argument to the constructor.
3. For each argument in the constructor, declare an instance variable and give it exactly the same type as the variable. Preserve Signatures (312) by copying all the arguments directly into the class and formatting them as instance variable declarations. Assign all of the arguments to the instance variables in the constructor.
4. Create an empty execution method on the new class. Often this method is called run(). We used the name draw in the example.
5. Copy the body of the old method into the execution method and compile to Lean on the Compiler (315).
6. The error messages from the compiler should indicate where the method is still using methods or variables from the old class. In each of these
cases, do what it takes to get the method to compile. In some cases, this is as simple as changing a call to use the reference to the original class. In other cases, you might have to make methods public on the original class or introduce getters so that you don’t have to make instance variables public.
7. After the new class compiles, go back to the original method and change it so that it creates an instance of the new class and delegates its work to it.
8. If needed, use Extract Interface (362) to break the dependency on the original class.

### Definition Completion
1. Identify a class with definitions you’d like to replace.
2. Verify that the method definitions are in a source file, not a header.
3. Include the header in the test source file of the class you are testing.
4. Verify that the source files for the class are not part of the build.
5. Build to find missing methods.
6. Add method definitions to the test source file until you have a complete build.

### Encapsulate Global References
1. Identify the globals that you want to encapsulate.
2. Create a class that you want to reference them from.
3. Copy the globals into the class. If some of them are variables, handle their initialization in the class.
4. Comment out the original declarations of the globals.
5. Declare a global instance of the new class.
6. Lean on the Compiler (315) to find all the unresolved references to the old globals.
7. Precede each unresolved reference with the name of the global instance of the new class.
8. In places where you want to use fakes, use Introduce Static Setter (372), Parameterize Constructor (379), Parameterize Method (383) or Replace Global Reference with Getter (399).

### Expose Static Method
1. Write a test that accesses the method that you want to expose as a public
static method of the class.
2. Extract the body of the method to a static method. Remember to Pre- serve Signatures (312). You’ll have to use a different name for the method. Often you can use the names of parameters to help you come up with a new method name. For example, if a method named validate accepts a Packet, you can extract its body as a static method named vali- datePacket.
3. Compile.
4. If there are errors related to accessing instance data or methods, take a look at those features and see if they can be made static also. If they can, make them static so that the system will compile.

### Extract And Override Call
1. Identify the call that you want to extract. Find the declaration of its method.
Copy its method signature so that you can Preserve Signatures (312).
2. Create a new method on the current class. Give it the signature you’ve
copied.
3. Copy the call to the new method and replace the call with a call to the new method.

### Extract And Override Factory Method
1. Identify an object creation in a constructor.
2. Extract all of the work involved in the creation into a factory method.
3. Create a testing subclass and override the factory method in it to avoid dependencies on problematic types under test.

### Extract Implementer
1. Make a copy of the source class’s declaration. Give it a different name. It’s useful to have a naming convention for classes you’ve extracted. I often use the prefix Production to indicate that the new class is the pro- duction code implementer of an interface.
2. Turn the source class into an interface by deleting all non-public methods and all variables.
3. Make all of the remaining public methods abstract. If you are working in C++, make sure that none of the methods that you make abstract are overridden by non-virtual methods.
4. Examine all imports or file inclusions in the interface file, and see if they are necessary. Often you can remove many of them. You can Lean on the Compiler (315) to detect these. Just delete each in turn, and recompile to see if it is needed.
5. Make your production class implement the new interface.
6. Compile the production class to make sure that all method signatures in the interface are implemented.
7. Compile the rest of the system to find all of the places where instances of the source class were created. Replace these with creations of the new production class.
8. Recompile and test.

### Extract Interface
1. Create a new interface with the name you’d like to use. Don’t add any
methods to it yet.
2. Make the class that you are extracting from implement the interface. This can’t break anything because the interface doesn’t have any meth- ods. But it is good to compile and run your test just to verify that.
3. Change the place where you want to use the object so that it uses the interface rather than the original class.
4. Compile the system and introduce a new method declaration on the interface for each method use that the compiler reports as an error.

### Parameterize Constructor
1. Identify the constructor that you want to parameterize and make a copy
of it.
2. Add a parameter to the constructor for the object whose creation you are going to replace. Remove the object creation and add an assignment from the parameter to the instance variable for the object.
3. If you can call a constructor from a constructor in your language, remove the body of the old constructor and replace it with a call to the old constructor. Add a new expression to the call of the new constructor in the old constructor. If you can’t call a constructor from another con- structor in your language, you may have to extract any duplication among the constructors to a new method.
NOTE: In languages that allow default arguments can simply use a default argument to the existing constructor

### Pull Up Feature
1. Identify the methods that you want to pull up.
2. Create an abstract superclass for the class that contains the methods.
3. Copy the methods to the superclass and compile.
4. Copy each missing reference that the compiler alerts you about to the new superclass. Remember to Preserve Signatures (312) as you do this, to reduce the chance of errors.
5. When both classes compile successfully, create a subclass for the abstract class and add whatever methods you need to be able to set it up in your tests.

### Push Down Dependency
1. Attempt to build the class that has dependency problems in your test
harness.
2. Identify which dependencies create problems in the build.
3. Create a new subclass with a name that communicates the specific envi- ronment of those dependencies.
4. Copy the instance variables and methods that contain the bad dependen- cies into the new subclass, taking care to preserve signatures. Make methods protected and abstract in your original class, and make your original class abstract.
5. Create a testing subclass and change your test so that you attempt to instantiate it.
6. Build your tests to verify that you can instantiate the new class.

### Replace Function With Function Pointer (C)
1. Find the declarations of the functions you want to replace.
2. Create function pointers with the same names before each function dec- laration.
3. Rename the original function declarations so that their names are not the same as the function pointers you’ve just declared.
4. Initialize the pointers to the addresses of the old functions in a C file.
5. Run a build to find the bodies of the old functions. Rename them to the new function names.

### Replace Global Reference With Getter
1. Identify the global reference that you want to replace.
2. Write a getter for the global reference. Make sure that the access protec- tion of the method is loose enough for you to be able to override the get- ter in a subclass.
3. Replace references to the global with calls to the getter.
4. Create a testing subclass and override the getter.

### Subclass and Override Method
1. Identify the dependencies that you want to separate or the place where you want to sense. Try to find the smallest set of methods that you can override to achieve your goals.
2. Make each method overridable. The way to do this varies among pro- gramming languages. In C++, the methods have to be made virtual if they aren’t already. In Java, the methods need to be made non-final. In many .NET languages, you explicitly have to make the method overrid- able also.
3. If your language requires it, adjust the visibility of the methods that you will override to so that they can be overridden in a subclass. In Java and C#, methods must at least have protected visibility to be overridden in subclasses. In C++, methods can remain private and still be overridden in subclasses.
4. Create a subclass that overrides the methods. Verify that you are able to build it in your test harness.
