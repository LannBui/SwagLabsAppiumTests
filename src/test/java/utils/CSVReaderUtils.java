package utils;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtils {

    @DataProvider(name = "invalidLoginData")
    public static Iterator<Object[]> getInvalidLoginData() throws Exception {
        return getLoginData("src/test/resources/testdata/loginData.csv", "fail");
    }

    private static Iterator<Object[]> getLoginData(String path, String filter) throws Exception {
        List<Object[]> testData = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] line;
            boolean isFirstLine = true;

            while ((line = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.length < 3) continue;

                String username = line[0].trim();
                String password = line[1].trim();
                String expectedResult = line[2].trim();
                String expectedMessage = line.length > 3 ? line[3].trim() : "";

                if (expectedResult.equalsIgnoreCase(filter)) {
                    testData.add(new Object[]{username, password, expectedMessage});
                }
            }

        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        return testData.iterator();
    }
    @DataProvider(name = "checkoutData")
    public static Object[][] readCheckoutTestData() {
        List<Object[]> data = new ArrayList<>();
        String filePath = "src/test/resources/testdata/checkout_data.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;
            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String[] fields = line.split(",", -1); // -1 to include trailing empty strings
                String firstName = fields[0].trim();
                String lastName = fields[1].trim();
                String zipCode = fields[2].trim();
                String expectedResult = fields[3].trim().toLowerCase(); // "pass" or "failed"
                String expectedMessage = fields.length > 4 ? fields[4].trim() : "";
                data.add(new Object[]{firstName, lastName, zipCode, expectedResult, expectedMessage});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toArray(new Object[0][]);
    }
}
