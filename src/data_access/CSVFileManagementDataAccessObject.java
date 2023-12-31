package data_access;

import java.io.FileWriter;
import java.io.IOException;

public class CSVFileManagementDataAccessObject implements  CSVFileManagementDataAccessInterface{

    private final String OUTPUT_DIRECTORY = "csv_output/";

    public String getOutputDirectory() {
        return OUTPUT_DIRECTORY;
    }
    public void writeBasicCSV(String fileName, String csvData) {
        // Split the data into lines
        String[] lines = csvData.split("\n");
        try {
            // Create a FileWriter object
            FileWriter writer = new FileWriter(OUTPUT_DIRECTORY + fileName + ".csv");

            // Write each line to the CSV file
            for (String line : lines) {
                writer.write(line + "\n");
            }

            // Close the writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
