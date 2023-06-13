import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AutoSalloni {

    private String emri;
    private Automjeti[] automjetet;
    private int index = 0;
    String fileOut;
    FileWriter fw;
    FileReader fr = null;
    BufferedReader br = null;


    public AutoSalloni(String emri, int nrA, String fOut) throws AutomjetiException, IOException {
        if (emri == null || emri.trim().isEmpty()) {
            throw new AutomjetiException("emri nuk mund te jete zbrazet");
        }
        if (nrA <= 0) {
            throw new AutomjetiException("Nuk lejohet 0 apo negativ");
        }
        this.emri = emri;
        automjetet = new Automjeti[nrA];

        if (fOut == null || fOut.trim().isEmpty()) {
            throw new AutomjetiException("Nuk lejohet emri i output file te jet i zbrazet");
        }
        this.fileOut = fOut;
        fw = new FileWriter(fileOut);

    }

    public boolean ekziston(Automjeti a) throws AutomjetiException {
        if (a == null) {
            throw new AutomjetiException("Automjeti nuk guxon te jete i zbrazet");
        }
        for (int i = 0; i < index; i++) {
            if (automjetet[i].equals(a)) {
                return true;
            }
        }
        return false;
    }

    public void shtoAutomjeti(Automjeti a) throws AutomjetiException {
        if (a == null) {
            throw new AutomjetiException("Automjeti nuk guxon te jete i zbrazet");
        }
        if (ekziston(a)) {
            throw new AutomjetiException("Automjeti ekziston");
        } else if (index == automjetet.length) {
            throw new AutomjetiException("Nuk ka vend ne varg");
        }
        automjetet[index++] = a;
    }

    public void lexoAutomjetet(String fileIn) throws AutomjetiException, IOException {
        if (fileIn == null || fileIn.trim().isEmpty()) {
            throw new AutomjetiException("Emri i input file nuk lejohet te jete i zbrazet");
        }
        fr = new FileReader(fileIn);
        br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] atributet = line.split(";");

            if (atributet.length != 5) {
                throw new AutomjetiException("Numri i atributeve eshte dhene gabim");
            }

            String lloji = atributet[0];
            String nrShasis = atributet[1];
            String prodhuesi = atributet[2];
            int vitiProdhimit = Integer.parseInt(atributet[3]);

            Automjeti a = null;
            if (lloji.equals("Limuzina")) {
                String ngjyra = atributet[4];
                a = new Limuzina(nrShasis, prodhuesi, vitiProdhimit, ngjyra);
            } else if (lloji.equals("SUV")) {
                boolean eshte4x4 = Boolean.parseBoolean(atributet[4]);
                a = new SUV(nrShasis, prodhuesi, vitiProdhimit, eshte4x4);
            } else {
                throw new AutomjetiException("Automjeti duhet te jete ose limuzin ose SUV");
            }
            shtoAutomjeti(a);
        }
    }

    public SUV max4x4() throws AutomjetiException {
        if (index == 0) {
            throw new AutomjetiException("Nuk ka asnje automjet ne varg");
        }

        SUV iFundit = null;
        for (int i = 0; i < index; i++) {
            if (automjetet[i] instanceof SUV) {
                SUV temp = (SUV) automjetet[i];
                if (iFundit == null || temp.isEshte4x4()) {
                    iFundit = temp;
                }
            }
        }
        if (iFundit == null) {
            System.out.println("Nuk kemi asnje SUV ne varg");
        }
        return iFundit;
    }

    public Limuzina firstNgjyra(String n) throws AutomjetiException {
        if (index == 0) {
            throw new AutomjetiException("Nuk ka asnje automjet ne vvarg");
        }
        for (int i = 0; i < index; i++) {
            if (automjetet[i] instanceof Limuzina) {
                if (((Limuzina) automjetet[i]).getNgjyra().equals(n)) {
                    return (Limuzina) automjetet[i];
                }
            }
        }
        System.out.println("Nuk eshte gjetur asnje limuzin me ket ngjyr");
        return null;
    }

    public void shkruaj(String s) throws IOException {
        fw.write(s);
        fw.flush();
    }

    public void shkruajAutomjetet() throws IOException {
        for (int i = 0; i < index; i++) {
            shkruaj(automjetet[i].toString());
            if (i < index - 1) {
                shkruaj("\n");
            }
        }
    }

    public void closeResources() {
        try {
            if (fw != null) {
                fw.close();
            }
            if (fr != null) {
                fr.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static void main(String[] args) {
        AutoSalloni a = null;
        try {

            String fileIn = "C:\\Users\\muhax\\Desktop\\Kode Java\\shk2Ushtrime\\InputOutputVesa\\OVesa\\Input Output\\src\\Automjeti.in";
            String fileOut = "C:\\Users\\muhax\\Desktop\\Kode Java\\shk2Ushtrime\\InputOutputVesa\\OVesa\\Input Output\\src\\test.out";

            a = new AutoSalloni("ABC", 50, fileOut);

            a.lexoAutomjetet(fileIn);

            a.shkruaj("Lista e automjeteve ne autosallon: \n");
            a.shkruajAutomjetet();
            a.shkruaj("-------------------------------");
            a.shkruaj("I fundit  4x4 eshte:\n");
            SUV ifundit = a.max4x4();
            if (ifundit != null){
                a.shkruaj(ifundit.toString());
            }
            a.shkruaj("--------------------------");
            a.shkruaj("First ngjyra:\n");
            Limuzina ngjyra = a.firstNgjyra("E zeze");
            if (ngjyra != null){
                a.shkruaj(ngjyra.toString());
            }


        } catch (AutomjetiException | IOException | NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if (a != null) {
                a.closeResources();
            }
        }
    }
}