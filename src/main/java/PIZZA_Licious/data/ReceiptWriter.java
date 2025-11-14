package PIZZA_Licious.data;


import java.io.FileWriter;
import java.io.IOException;

public class ReceiptWriter {

    private final String fileName;

    public ReceiptWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeReceipt(String receipt) {
        try (FileWriter fileWriter = new FileWriter("src/main/java/PIZZA_Licious/data/OrderReceipt", true)) {
            fileWriter.write(receipt);
            fileWriter.write(System.lineSeparator());
            System.out.println("🧾 Receipt appended to " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error writing receipt: " + e.getMessage());
        }
    }
}
