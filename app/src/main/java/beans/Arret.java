package beans;

/**
 * Created by Baptiste on 02/12/2017.
 */
public class Arret {
    private int numArret;
    private String nomArret;
    private boolean isAccessible;

    public Arret() {
    }

    public Arret(int numArret, String nomArret) {
        this.numArret = numArret;
        this.nomArret = nomArret;
        isAccessible = true;
    }

    public int getNumArret() {
        return numArret;
    }

    public void setNumArret(int numArret) {
        this.numArret = numArret;
    }

    public String getNomArret() {
        return nomArret;
    }

    public void setNomArret(String nomArret) {
        this.nomArret = nomArret;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    @Override
    public String toString() {
        return "Arret{" +
                "numArret=" + numArret +
                ", nomArret='" + nomArret + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arret arret = (Arret) o;

        if (numArret != arret.numArret) return false;
        return nomArret != null ? nomArret.equals(arret.nomArret) : arret.nomArret == null;
    }

    @Override
    public int hashCode() {
        int result = numArret;
        result = 31 * result + (nomArret != null ? nomArret.hashCode() : 0);
        return result;
    }
}
