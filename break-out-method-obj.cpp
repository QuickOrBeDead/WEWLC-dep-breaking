class GDIBrush {
public:
  void draw(
    vector<point> &renderingRoots,
    ColorMatrix &colors,
    vector<point> &selection
  );
 void drawPoint(int x, int y, COLOR color);

};

void GDIBrush::draw(
  vector<point> &renderingRoots,
  ColorMatrix &colors,
  vector<point> &selection
) {
  Renderer render(this, renderingRoots, colors, selection);
  renderer.draw();
}

class Renderer {
private:
    GBIBrush *brush;
    vector<point> &renderingRoots;
    ColorMatrix &colors;
    vector<point> &selection;

public:
  Renderer(
    GBIBrush *brush,
    vector<point> &renderingRoots,
    ColorMatrix &colors,
    vector<point> &selection
  ) : brush(brush), renderingRoots(renderingRoots), colors(colors), selection(selection) {}

  void Renderer::draw()
  {
    for (vector<points>::iterator it = renderingRoots.begin();
         it != renderingRoots.end(); ++it) {
      point p = *it;
      drawPoint(p.x, p.y, colors[n]);
    }
  }
};

/* 1. Create a class that will house the method code. */
/* 2. Create a constructor for the class and Preserve Signatures (312) to give it an exact copy of the arguments used by the method. If the method uses an instance data or methods from the original class, add a reference to the original class as the first argument to the constructor. */
/* 3. For each argument in the constructor, declare an instance variable and give it exactly the same type as the variable. Preserve Signatures (312) by copying all the arguments directly into the class and formatting them as instance variable declarations. Assign all of the arguments to the instance variables in the constructor. */
/* 4. Create an empty execution method on the new class. Often this method is called run(). We used the name draw in the example. */
/* 5. Copy the body of the old method into the execution method and compile to Lean on the Compiler (315). */
/* 6. The error messages from the compiler should indicate where the method is still using methods or variables from the old class. In each of these */
/* cases, do what it takes to get the method to compile. In some cases, this is as simple as changing a call to use the reference to the original class. In other cases, you might have to make methods public on the original class or introduce getters so that you don’t have to make instance variables public. */
/* 7. After the new class compiles, go back to the original method and change it so that it creates an instance of the new class and delegates its work to it. */
/* 8. If needed, use Extract Interface (362) to break the dependency on the original class. */
