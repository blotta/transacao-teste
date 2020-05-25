package info.blotta.transacaoteste.model;

public enum CardApplication {
    DEBITO,
    CREDITO,
    VOUCHER;

    public static CardApplication from(String str) {
        switch (str) {
            case "DEBITO":
                return CardApplication.DEBITO;
            case "CREDITO":
                return CardApplication.CREDITO;
            case "VOUCHER":
                return CardApplication.VOUCHER;
            default:
                return null;
        }
    }
}
