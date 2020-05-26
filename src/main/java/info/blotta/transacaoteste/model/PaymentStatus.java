package info.blotta.transacaoteste.model;

public enum PaymentStatus {
    SUCCESS,
    PENDING,
    CANCELED,
    FAILED;

    public static int toInt(PaymentStatus p) {
        switch(p) {
            case SUCCESS:
                return 0;
            case PENDING:
                return 1;
            case CANCELED:
                return 2;
            case FAILED:
                return 3;
            default:
                return -1;
        }
    }
}
