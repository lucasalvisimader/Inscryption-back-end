package br.senai.sc.trunfo.model.enums;

public enum PriceType {
    NONE(0), BLOOD1(-1), BLOOD2(-2), BLOOD3(-3), BLOOD4(-4),

    BONE1(1), BONE2(2), BONE3(3), BONE4(4), BONE5(5), BONE6(6), BONE7(7), BONE8(8), BONE9(9), BONE10(10);
    private final int type;

    PriceType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
