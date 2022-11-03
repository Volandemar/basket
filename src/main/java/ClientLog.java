import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {

    private List<String[]> listLog = new ArrayList<>();


    //    public void log(String[] value, File nameLog) throws IOException {
//        try (CSVWriter writer = new CSVWriter(new FileWriter(nameLog, true))) {
//            writer.writeNext(value);
//        }
//    }
    public void log(int productNumber, int amount) {
        String[] userLog = {String.valueOf(productNumber), String.valueOf(amount)};
        listLog.add(userLog);
    }

    public void exportAsCSV(File txtFile) throws IOException {
        try (FileWriter log = new FileWriter(txtFile)) {
            log.write("productNum, amount" +"\n");
            for (String[] result:listLog){
                log.write(result[0] + "," + result[1] + "\n");
            }
        }
    }
}
