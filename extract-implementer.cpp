// ModelNode.h
class ModelNode {
private:
  list<ModelNode *> m_interiorNodes;
  list<ModelNode *> m_exteriorNodes;
  double m_weight;
  void createSpanningLinks();

public:
  void addExteriorNode(ModelNode *newNode);
  void addInternalNode(ModelNode *newNode);
  void colorize();
};

/* When the name of a class is perfect for the name of an interface and I don’t have automated refactoring tools, I use Extract Implementer to get the separation I need. To extract an implementer of a class, we turn the class into an interface by subclassing it and pushing all of its concrete methods down into that subclass. Here is an example in C++: */
