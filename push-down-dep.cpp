/* Some classes have only a few problematic dependencies. If the dependencies are contained in only a few method calls, you can use Subclass and Override Method (401) to get them out of the way when you are writing tests. But if the dependencies are pervasive, Subclass and Override Method might not work. You might have to use Extract Interface (362) several times to remove depen- dencies on particular types. Push Down Dependency is another option.

 * This technique helps you to separate problematic dependencies from the rest of the class, making it easier to work with in a test harness. */

class OffMarketTradeValidator : public TradeValidator {
private:
  Trade &trade;
  bool flag;
  void showMessage() {
    int status = AfxMessageBox(makeMessage(), MB_ABORTRETRYIGNORE);
    if (status == IDRETRY) {
      SubmitDialog dlg(this, "Press okay if this is a valid trade");
      dlg.DoModal();
      if (dlg.wasSubmitted()) {
        g_dispatcher.undoLastSubmission();
        flag = true;
      }
    } else if (status == IDABORT) {
      flag = false;
    }
  }

public:
  OffMarketTradeValidator(Trade &trade) : trade(trade), flag(false) {}
  bool isValid() const {
    if (inRange(trade.getDate())
      && validDestination(trade.destination)
      && inHours(trade) {
          flag = true;
    }
    showMessage();
    return flag;
  }
};
