/**
 *
 *  @author Dzik Szymon S24020
 *
 */

package zad1;


public class Main {

  public static void main(String[] args)  {
    CustomersPurchaseSortFind cpsf = new CustomersPurchaseSortFind();
    String fname = "customers.txt";
    cpsf.readFile(fname);
    cpsf.showSortedBy("Nazwiska");
    cpsf.showSortedBy("Koszty");


    String[] custSearch = { "c00001", "c00002" };

    for (String id : custSearch) {
      cpsf.showPurchaseFor(id);
    }
  }

}
