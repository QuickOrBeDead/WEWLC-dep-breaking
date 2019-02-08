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
    Item newItem = Inventory.getInventory().itemForBarcode(code);
    items.add(newItem);
  }

  protected Inventory getInventory() {
    return Inventory.getInventory();
  }
}
