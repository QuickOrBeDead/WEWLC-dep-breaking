public class RegisterSale
{
  public void addItem(Barcode code) {
    Item newItem = Inventory.getInventory().itemForBarcode(code);
    items.add(newItem);
  }
}

// In this code, the Inventory class is accessed as a global. “Wait?” I hear you say. “A global? It is just a call to a static method on a class.” For our purposes, that counts as a global. In Java, the class itself is a global object, and it seems that it must reference some state to be capable of doing its work (returning item objects given barcodes). Can we get past this with Replace Global Reference with Getter? Let’s try it.

// First we write the getter. Note that we make it protected so that we can override it under test.

public class RegisterSale {
  public void addItem(Barcode code) {
    Item newItem = getInventory().itemForBarcode(code);
    items.add(newItem);
  }

  protected Inventory getInventory() {
    return Inventory.getInventory();
  }
}

// Now we can create a subclass of Inventory that we can use in the test. Because Inventory is a singleton, we have to make its constructor protected rather than private. After we’ve done that, we can subclass it like this and put in whatever logic we want to use to convert barcodes to items in a test.
public class FakeInventory extends Inventory {
  // our stub
  public Item itemForBarcode(Barcode code) {
  }
}

// Now we can write the class we’ll use in the test.
class TestingRegisterSale extends RegisterSale {
  Inventory inventory = new FakeInventory();
  protected Inventory getInventory() {
    return inventory;
  }
}

