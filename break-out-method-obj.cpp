class GDIBrush {
public:
  void draw(
    vector<point> &renderingRoots,
    ColorMatrix &colors,
    vector<point> &selection
  );
  private : void drawPoint(int x, int y, COLOR color);

};

void GDIBrush::draw(
  vector<point> &renderingRoots,
  ColorMatrix &colors,
  vector<point> &selection
) {
  for (vector<points>::iterator it = renderingRoots.begin();
       it != renderingRoots.end(); ++it) {
    point p = *it;
    drawPoint(p.x, p.y, colors[n]);
  }
}
