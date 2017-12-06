package beans;

/**
 * Created by Baptiste on 02/12/2017.
 */
public class Horaire {
    private int numLigne;
    private double horaireArr1;
    private double horaireArr2;
    private double horaireArr3;
    private double horaireArr4;
    private double horaireArr5;
    private double horaireArr6;

    public Horaire() {
    }

    public Horaire (BuilderHoraire builder)
    {
        setNumLigne(builder.numLigne);
        setHoraireArr1(builder.horaireArr1);
        setHoraireArr2(builder.horaireArr2);
        setHoraireArr3(builder.horaireArr3);
        setHoraireArr4(builder.horaireArr4);
        setHoraireArr5(builder.horaireArr5);
        setHoraireArr6(builder.horaireArr6);
    }

    public static class BuilderHoraire {
        private int numLigne;
        private double horaireArr1;
        private double horaireArr2;
        private double horaireArr3;
        private double horaireArr4;
        private double horaireArr5;
        private double horaireArr6;


        public BuilderHoraire(int numLigne) {
            this.numLigne = numLigne;

        }

        public BuilderHoraire addHoraireArr1(double horaireArr1) {
            this.horaireArr1 = horaireArr1;
            return this;
        }

        public BuilderHoraire addHoraireArr2(double horaireArr2) {
            this.horaireArr2 = horaireArr2;
            return this;
        }

        public BuilderHoraire addHoraireArr3(double horaireArr3) {
            this.horaireArr3 = horaireArr3;
            return this;
        }

        public BuilderHoraire addHoraireArr4(double horaireArr4) {
            this.horaireArr4 = horaireArr4;
            return this;
        }

        public BuilderHoraire addHoraireArr5(double horaireArr5) {
            this.horaireArr5 = horaireArr5;
            return this;
        }

        public BuilderHoraire addHoraireArr6(double horaireArr6) {
            this.horaireArr6 = horaireArr6;
            return this;
        }

        public Horaire build(){
            return new  Horaire(this);
        }
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public void setHoraireArr1(double horaireArr1) {
        this.horaireArr1 = horaireArr1;
    }

    public void setHoraireArr2(double horaireArr2) {
        this.horaireArr2 = horaireArr2;
    }

    public void setHoraireArr3(double horaireArr3) {
        this.horaireArr3 = horaireArr3;
    }

    public void setHoraireArr4(double horaireArr4) {
        this.horaireArr4 = horaireArr4;
    }

    public void setHoraireArr5(double horaireArr5) {
        this.horaireArr5 = horaireArr5;
    }

    public void setHoraireArr6(double horaireArr6) {
        this.horaireArr6 = horaireArr6;
    }

    public double getHoraire1 () { return horaireArr1;}

    public double getHoraireArr2() {
        return horaireArr2;
    }

    public double getHoraireArr3() {
        return horaireArr3;
    }

    public double getHoraireArr4() {
        return horaireArr4;
    }

    public double getHoraireArr5() {
        return horaireArr5;
    }

    public double getHoraireArr6() {
        return horaireArr6;
    }

    @Override
    public String toString() {
        return "Horaire{" +
                "numLigne=" + numLigne +
                ", horaireArr1=" + horaireArr1 +
                ", horaireArr2=" + horaireArr2 +
                ", horaireArr3=" + horaireArr3 +
                ", horaireArr4=" + horaireArr4 +
                ", horaireArr5=" + horaireArr5 +
                ", horaireArr6=" + horaireArr6 +
                '}';
    }
}
