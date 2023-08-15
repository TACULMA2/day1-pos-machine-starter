package pos.machine;

import java.util.List;
import java.util.Map;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<Item> items = ItemsLoader.loadAllItems();
        Map<String, Integer> itemCounts = countItemOccurrences(barcodes);

        StringBuilder receipt = new StringBuilder("***<store earning no money>Receipt***\n");
        int total = generateReceipt(items, receipt, itemCounts);

        appendTotal(receipt, total);
        return receipt.toString();
    }

}
