package info.blotta.transacaoteste.model;

public enum PaymentStatus {
    SUCCESS,
    PENDING,
    CANCELED,
    FAILED;

    public static PaymentStatus from(String str) {
        switch (str) {
            case "SUCCESS":
                return PaymentStatus.SUCCESS;
            case "PENDING":
                return PaymentStatus.PENDING;
            case "CANCELED":
                return PaymentStatus.CANCELED;
            case "FAILED":
                return PaymentStatus.FAILED;
            default:
                return null;
        }
    }
}
