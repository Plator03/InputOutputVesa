public class Limuzina extends Automjeti{
    private String ngjyra;

    public Limuzina(String nrShasis, String prodhuesi, int vitiProdhimit, String ngjyra) throws AutomjetiException {
        super(nrShasis, prodhuesi, vitiProdhimit);
        if (ngjyra == null || ngjyra.trim().isEmpty()){
            throw new AutomjetiException("Ngjyra nuk mund te jete empty");
        }
        this.ngjyra = ngjyra;
    }

    public String getNgjyra() {
        return ngjyra;
    }

    public void setNgjyra(String ngjyra) throws AutomjetiException{
        if (ngjyra == null || ngjyra.trim().isEmpty()){
            throw new AutomjetiException("Ngjyra nuk mund te jete empty");
        }
        this.ngjyra = ngjyra;
    }

    public String toString(){
        return "Limuzina " + super.toString() + ":" + ngjyra;
    }

    @Override
    public boolean eshteAutomatik() {
        return true;
    }
}
