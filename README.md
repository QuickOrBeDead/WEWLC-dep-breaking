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
