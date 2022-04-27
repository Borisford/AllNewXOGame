package su.ANV.models;

import java.util.Arrays;

public class DemoStep {
    private String name;
    private int stepNo;
    private char sign;
    private String[] field;

    public DemoStep() {
    }

    public DemoStep(String name, int stepNo, char sign, String[] field) {
        this.name = name;
        this.stepNo = stepNo;
        this.sign = sign;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStepNo() {
        return stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public String[] getField() {
        return field;
    }

    public void setField(String[] field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "DemoStep{" +
                "name='" + name + '\'' +
                ", stepNo=" + stepNo +
                ", sign=" + sign +
                ", field=" + Arrays.toString(field) +
                '}';
    }
}
