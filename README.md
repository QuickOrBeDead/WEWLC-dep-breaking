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
