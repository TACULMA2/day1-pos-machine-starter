package pos.machine;

import java.util.LinkedHashMap;
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

    private Map<String, Integer> countItemOccurrences(List<String> barcodes) {
        Map<String, Integer> itemCounts = new LinkedHashMap<>();
        for (String barcode:barcodes) {
            itemCounts.put(barcode, itemCounts.getOrDefault(barcode, 0) +1);
        }
        return itemCounts;
    }

}
