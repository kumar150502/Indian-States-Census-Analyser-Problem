import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class IndianStatesCensusAnalyser {
    private static final String CSV_FILE_PATH = "states_census.csv";

    public static void main(String[] args) throws IOException {
        List<CSVRecord> records = readCSVFile(CSV_FILE_PATH);
        int numberOfRecords = records.size();
        System.out.println("Number of records: " + numberOfRecords);

        // Check for happy and sad test cases
        checkHappyTestCases(records);
        checkSadTestCases(records);
    }

    private static List<CSVRecord> readCSVFile(String filePath) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader();
        CSVParser csvParser = new CSVParser(new File(filePath), csvFormat);
        List<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    private static void checkHappyTestCases(List<CSVRecord> records) {
        // Check if the number of records matches
        int expectedNumberOfRecords = 37;
        if (numberOfRecords != expectedNumberOfRecords) {
            throw new RuntimeException("Number of records does not match");
        }

        // Check if the header is correct
        String[] expectedHeader = {"State Name", "State Code"};
        String[] actualHeader = records.get(0).get(0);
        if (!Arrays.equals(expectedHeader, actualHeader)) {
            throw new RuntimeException("Header is incorrect");
        }
    }

    private static void checkSadTestCases(List<CSVRecord> records) {
        // Check if the file is not found
        String filePath = "not_found.csv";
        try {
            readCSVFile(filePath);
            throw new RuntimeException("File not found exception should have been thrown");
        } catch (IOException e) {
            // Expected exception
        }

        // Check if the type is incorrect
        filePath = "states_census_incorrect_type.csv";
        try {
            readCSVFile(filePath);
            throw new RuntimeException("Type exception should have been thrown");
        } catch (IOException e) {
            // Expected exception
        }

        // Check if the delimiter is incorrect
        filePath = "states_census_incorrect_delimiter.csv";
        try {
            readCSVFile(filePath);
            throw new RuntimeException("Delimiter exception should have been thrown");
        } catch (IOException e) {
            // Expected exception
        }

        // Check if the header is incorrect
        filePath = "states_census_incorrect_header.csv";
        try {
            readCSVFile(filePath);
            throw new RuntimeException("Header exception should have been thrown");
        } catch (IOException e) {
            // Expected exception
        }
    }
}
