package Models;

public class Encuesta {

    private boolean cb1;
    private boolean cb2;
    private boolean cb3;
    private boolean cb4;
    private boolean cb5;
    private boolean cb6;
    private boolean cb7;
    private boolean cb8;
    private boolean cb9;
    private boolean cb10;
    private boolean cb11;
    private boolean cb12;
    private boolean cb13;
    private String sintomas;

    public Encuesta() {
    }

    public Encuesta(boolean cb1, boolean cb2, boolean cb3, boolean cb4, boolean cb5, boolean cb6, boolean cb7, boolean cb8, boolean cb9, boolean cb10, boolean cb11, boolean cb12, boolean cb13, String sintomas) {
        this.cb1 = cb1;
        this.cb2 = cb2;
        this.cb3 = cb3;
        this.cb4 = cb4;
        this.cb5 = cb5;
        this.cb6 = cb6;
        this.cb7 = cb7;
        this.cb8 = cb8;
        this.cb9 = cb9;
        this.cb10 = cb10;
        this.cb11 = cb11;
        this.cb12 = cb12;
        this.cb13 = cb13;
        this.sintomas = sintomas;
    }

    public boolean isCb1() {
        return cb1;
    }

    public void setCb1(boolean cb1) {
        this.cb1 = cb1;
    }

    public boolean isCb2() {
        return cb2;
    }

    public void setCb2(boolean cb2) {
        this.cb2 = cb2;
    }

    public boolean isCb3() {
        return cb3;
    }

    public void setCb3(boolean cb3) {
        this.cb3 = cb3;
    }

    public boolean isCb4() {
        return cb4;
    }

    public void setCb4(boolean cb4) {
        this.cb4 = cb4;
    }

    public boolean isCb5() {
        return cb5;
    }

    public void setCb5(boolean cb5) {
        this.cb5 = cb5;
    }

    public boolean isCb6() {
        return cb6;
    }

    public void setCb6(boolean cb6) {
        this.cb6 = cb6;
    }

    public boolean isCb7() {
        return cb7;
    }

    public void setCb7(boolean cb7) {
        this.cb7 = cb7;
    }

    public boolean isCb8() {
        return cb8;
    }

    public void setCb8(boolean cb8) {
        this.cb8 = cb8;
    }

    public boolean isCb9() {
        return cb9;
    }

    public void setCb9(boolean cb9) {
        this.cb9 = cb9;
    }

    public boolean isCb10() {
        return cb10;
    }

    public void setCb10(boolean cb10) {
        this.cb10 = cb10;
    }

    public boolean isCb11() {
        return cb11;
    }

    public void setCb11(boolean cb11) {
        this.cb11 = cb11;
    }

    public boolean isCb12() {
        return cb12;
    }

    public void setCb12(boolean cb12) {
        this.cb12 = cb12;
    }

    public boolean isCb13() {
        return cb13;
    }

    public void setCb13(boolean cb13) {
        this.cb13 = cb13;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }
}
