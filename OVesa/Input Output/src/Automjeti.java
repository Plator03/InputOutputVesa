public abstract class Automjeti {
    private String nrShasis;
    private String prodhuesi;
    private int vitiProdhimit;

    public Automjeti(String nrShasis, String prodhuesi, int vitiProdhimit) throws AutomjetiException {
        if (nrShasis == null || nrShasis.trim().isEmpty()) {
            throw new AutomjetiException("nr shasis nuk mund te jete empty");
        }
        if (prodhuesi == null || prodhuesi.trim().isEmpty()) {
            throw new AutomjetiException("prodhuesi nuk mund te jete empty");
        }
        if (vitiProdhimit < 1950) {
            throw new AutomjetiException("viti prodhimit nuk mund te jete me i vogel se 1950");
        }
        this.nrShasis = nrShasis;
        this.prodhuesi = prodhuesi;
        this.vitiProdhimit = vitiProdhimit;
    }

    public String getNrShasis() {
        return nrShasis;
    }


    public String getProdhuesi() {
        return prodhuesi;
    }

    public void setProdhuesi (String prodhuesi) throws AutomjetiException{
        if (prodhuesi == null || prodhuesi.trim().isEmpty()) {
            throw new AutomjetiException("prodhuesi nuk mund te jete empty");
        }
        this.prodhuesi = prodhuesi;
    }

    public int getVitiProdhimit() {
        return vitiProdhimit;
    }

    public void setVitiProdhimit (int vitiProdhimit) throws AutomjetiException {
        if (vitiProdhimit < 1950) {
            throw new AutomjetiException("viti prodhimit nuk mund te jete me i vogel se 1950");
        }
        this.vitiProdhimit = vitiProdhimit;
    }

    public abstract boolean eshteAutomatik();

    public String toString(){
        return nrShasis + ":" + prodhuesi + "-" + vitiProdhimit;
    }
    public boolean equals(Object o){
        if(o instanceof Automjeti){
            Automjeti a = (Automjeti) o;
            if (nrShasis.equals(a.getNrShasis())){
                return true;
            }
        }
        return false;
    }
}
