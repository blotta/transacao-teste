package info.blotta.transacaoteste.model;

public enum CardApplication {
    DEBITO,
    CREDITO,
    VOUCHER;

    public static int toInt(CardApplication c) {
        switch(c) {
            case DEBITO:
                return 0;
            case CREDITO:
                return 1;
            case VOUCHER:
                return 2;
            default:
                return -1;
        }
    }
}
