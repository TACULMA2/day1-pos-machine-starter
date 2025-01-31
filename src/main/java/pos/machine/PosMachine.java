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

    private void appendTotal(StringBuilder receipt, int total) {
        receipt.append("----------------------\n");
        receipt.append("Total: ").append(total).append(" (yuan)\n");
        receipt.append("**********************");
    }

    private int generateReceipt(List<Item> items, StringBuilder receipt, Map<String, Integer> itemCounts) {
        int [] total = {0};
        itemCounts.forEach((barcode, quantity) -> {
            Item item = findItemByBarcode(items, barcode);
            if (item != null) {
                int subtotal = item.getPrice()*quantity;
                appendLineToReceipt(receipt, item.getName(), quantity, item.getPrice(), subtotal);
                total[0] += subtotal;
            }
        });
        return total[0];
    }

    private void appendLineToReceipt(StringBuilder receipt, String name, Integer quantity, int price, int subtotal) {
        receipt.append("Name: ").append(name).append(", Quantity: ").append(quantity);
        receipt.append(", Unit price: ").append(price).append(" (yuan), Subtotal: ").append(subtotal)
                .append(" (yuan)\n");
    }

    private Item findItemByBarcode(List<Item> items, String barcode) {
        return items.stream()
                .filter(item -> item.getBarcode().equals(barcode)).findFirst().orElse(null);
    }

    private Map<String, Integer> countItemOccurrences(List<String> barcodes) {
        Map<String, Integer> itemCounts = new LinkedHashMap<>();
        for (String barcode:barcodes) {
            itemCounts.put(barcode, itemCounts.getOrDefault(barcode, 0) +1);
        }
        return itemCounts;
    }

}
