public class PageLayout {
  private int id = 0;
  private List styles;
  private StyleTemplate template;

  protected void rebindStyles() {
    styles = formStyles(template, id);
  }

  protected List formStyles(StyleTemplate template, int id) {
    return StyleMaster.formStyles(template, id);
  }
}

public class TestingPageLayout extends PageLayout {
  protected List formStyles(StyleTemplate template, int id) {
    return new ArrayList();
  }
}

// PageLayout makes a call to a static function named formStyles on a class named StyleMaster. It assigns the return value to an instance variable: styles.
  // What can we do if we want to sense through formStyles or separate our dependency on StyleMaster? One option is to extract the call to a new method and override it in a testing subclass.
