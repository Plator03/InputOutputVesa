import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AutoSalloni {

    private String emri;
    private Automjeti[] automjetet;
    private int index = 0;
    String fileOut = null;
    FileWriter fw = null;
    FileReader fr = null;
    BufferedReader br = null;


    public AutoSalloni (String emri, int nrA, String fOut)throws AutomjetiException, IOException {
        if (emri == null || emri.trim().isEmpty()) {
            throw new AutomjetiException("emri nuk mund te jete zbrazet");
        }
        if (nrA <= 0){
            throw new AutomjetiException("Nuk lejohet 0 apo negativ");
        }
        this.emri = emri;
        automjetet = new Automjeti[nrA];

        if (fOut == null || fOut.trim().isEmpty()){
            throw new AutomjetiException("Nuk lejohet emri i output file te jet i zbrazet");
        }
        this.fileOut = fOut;
        fw = new FileWriter(fileOut);

    }


    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}