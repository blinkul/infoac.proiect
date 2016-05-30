package Abonament;

public class Abonat {
    String cnp;
    String nume, prenume;

    public Abonat(String cnp, String nume, String prenume) {
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
    }

    public Abonat() {
    }    
        
    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        //Se accepta si litere pentru cazul altor tari
        //sa implementeze exceptii
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        //sa implementeze exceptii
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        //sa implementeze exceptii
        this.prenume = prenume;
    }
    
    
}
