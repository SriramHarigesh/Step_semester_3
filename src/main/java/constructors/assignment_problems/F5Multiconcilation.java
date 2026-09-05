package oop.assignment_problems;

public class F5HrParkingMiniSystem {
    private static double defaultMinimumSurgePercent;
    private static int processedCount;
    private static int nullSkippedCount;
    private static int premiumCount;
    private static int regularCount;
    private static double grandTotal;

    static {
        defaultMinimumSurgePercent = 1.0;
        processedCount = 0;
        nullSkippedCount = 0;
        premiumCount = 0;
        regularCount = 0;
        grandTotal = 0.0;
    }

    private final String studentId;
    private final double orderValue;

    public F5HrParkingMiniSystem(String studentId, double orderValue) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("studentId cannot be blank");
        }
        if (Double.isNaN(orderValue) || Double.isInfinite(orderValue) || orderValue < 0) {
            throw new IllegalArgumentException("orderValue cannot be negative");
        }
        this.studentId = studentId.trim();
        this.orderValue = orderValue;
    }

    public F5HrParkingMiniSystem(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        return calculateFee(orderValue, delayMinutes, defaultMinimumSurgePercent);
    }

    public void processAccount(F5HrParkingMiniSystem account, double amount, int delayMinutes) {
        if (account == null) {
            nullSkippedCount++;
            return;
        }

        double fee = calculateFee(amount, delayMinutes, defaultMinimumSurgePercent);
        if (account instanceof Premium) {
            premiumCount++;
            fee *= 0.75;
        } else {
            regularCount++;
        }
        processedCount++;
        grandTotal += fee;
    }

    public static void processBatch(F5HrParkingMiniSystem[] accounts,
                                    double[] amounts,
                                    int[] delayMinutesArray) {
        validateBatch(accounts, amounts, delayMinutesArray);
        resetSummary();

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                nullSkippedCount++;
            } else {
                accounts[i].processAccount(accounts[i], amounts[i], delayMinutesArray[i]);
            }
        }

        printSummary();
    }

    private static void validateBatch(F5HrParkingMiniSystem[] accounts,
                                      double[] amounts,
                                      int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null
                || accounts.length != amounts.length
                || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Parallel arrays must have equal lengths");
        }
    }

    private static double calculateFee(double amount, int delayMinutes, double minimumPercent) {
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount < 0) {
            throw new IllegalArgumentException("amount cannot be negative");
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("delayMinutes cannot be negative");
        }
        if (delayMinutes == 0) {
            return 0.0;
        }

        int first = Math.min(delayMinutes, 5);
        int second = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int third = Math.max(delayMinutes - 15, 0);
        double tiered = amount * (first * 0.005 + second * 0.01 + third * 0.02);
        double floor = amount * minimumPercent / 100.0;
        return Math.max(tiered, floor);
    }

    private static void resetSummary() {
        processedCount = 0;
        nullSkippedCount = 0;
        premiumCount = 0;
        regularCount = 0;
        grandTotal = 0.0;
    }

    private static void printSummary() {
        System.out.printf("%d processed | %d null skipped | %d premium | %d regular | grand total surge fees = %.2f%n",
                processedCount, nullSkippedCount, premiumCount, regularCount, grandTotal);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getOrderValue() {
        return orderValue;
    }
}

class DeliveryAccount extends F5HrParkingMiniSystem {
    public DeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public DeliveryAccount(String studentId) {
        super(studentId);
    }

    public static void processBatch(DeliveryAccount[] accounts,
                                    double[] amounts,
                                    int[] delayMinutesArray) {
        F5HrParkingMiniSystem.processBatch(accounts, amounts, delayMinutesArray);
    }
}

class Premium extends DeliveryAccount {
    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public Premium(String studentId) {
        super(studentId);
    }
}
