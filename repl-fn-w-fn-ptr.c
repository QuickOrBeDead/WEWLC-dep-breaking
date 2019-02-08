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

/* -------------------------------------------------------------------- */
/* new example */
void db_store(struct receive_record *record, struct time_stamp receive_time);
struct receive_record * db_retrieve(time_stamp search_time);

/* We could use Link Substitution to link to new bodies for these fns, but sometimes Link Substitution causes nontrivial build changes. */
  /* We might have to break down libraries to separate out the functions that we want to fake. More important, the seams we get with Link Substitution are not the sort you’d want exploit to vary behavior in production code. */

/* If you want to get your code under test and provide flexibility to, for instance, vary the type of database that your code can talk to, Replace Function with Function Pointer can be useful. Let’s go through the steps: */

/* 1. Find decl of fn we want to replace */
// db.h
void db_store(struct receive_record *record, struct time_stamp receive_time);

/* 2. Declare fn ptr w same name */
// db.h
void db_store(struct receive_record *record, struct time_stamp receive_time);
void (*db_store)(struct receive_record *record, struct time_stamp receive_time);

/* 3. Rename original declaration */
// db.h
void db_store_production(struct receive_record *record, struct time_stamp receive_time);
void (*db_store)(struct receive_record *record, struct time_stamp receive_time);

/* 4. Init the ptrs to @ of old fns in C file */
// main.c
extern void db_store_production(
    struct receive_record *record, struct time_stamp receive_time);

void initializeEnvironment() {
  db_store = db_store_production;
  // ...
}

int main(int ac, char **av) {
  initializeEnvironment();
}

/* Now we find the definition of the db_store function and rename it db_store_production. */
// db.c
void db_store_production(struct receive_record *record, struct time_stamp receive_time) {
  // ...
}

