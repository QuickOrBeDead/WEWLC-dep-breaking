/* example of fn ptrs */
struct base_operations {
  double (*project)(double, double);
  double (*maximize)(double, double);
};

double default_projection(double first, double second) {
  return second;
}

double maximize(double first, double second) {
  return first + second;
}

void init_ops(struct base_operations *operations) {
  operations->project = default_projection;
  operations->maximize = default_maximize;
}

void run_tesselation(struct node *base, struct base_operations *operations) {
  double value = operations->project(base.first, base.second);
  /* ... */
}
