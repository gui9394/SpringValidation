package com.gui9394.apivalidation.enums;

public enum Sexo {
    MASCULINO(1),
    FEMININO(2);

    private int value;

    Sexo(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Sexo fromValue(int value) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.getValue() == value) {
                return sexo;
            }
        }

        return null;
    }

}