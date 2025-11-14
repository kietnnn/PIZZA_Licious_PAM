package PIZZA_Licious.data;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptWriter {

    private String filePath;

    public ReceiptWriter(String filePath) {
        this.filePath = filePath;
        createFolderIfNotExists(filePath);
    }

    private void createFolderIfNotExists(String path) {
        File file = new File(path).getParentFile();
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public void writeReceipt(String content) {
        try (BufferedWriter bufferedWriterwriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriterwriter.write(content);
        } catch (IOException e) {
            System.err.println("❌ Failed to write receipt: " + e.getMessage());
        }
    }
}

