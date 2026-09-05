package oop.class_problems;

public class F5FeeHostelMiniSystem {
    private static double defaultMinimumPenaltyPercent;
    private static int processedCount;
    private static int nullSkippedCount;
    private static int sleeperCount;
    private static int regularCount;
    private static double grandTotal;

    static {
        defaultMinimumPenaltyPercent = 1.0;
        processedCount = 0;
        nullSkippedCount = 0;
        sleeperCount = 0;
        regularCount = 0;
        grandTotal = 0.0;
    }

    private final String bookingId;
    private final double ticketFare;

    public F5FeeHostelMiniSystem(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("bookingId cannot be blank");
        }
        if (Double.isNaN(ticketFare) || Double.isInfinite(ticketFare) || ticketFare < 0) {
            throw new IllegalArgumentException("ticketFare cannot be negative");
        }
        this.bookingId = bookingId.trim();
        this.ticketFare = ticketFare;
    }

    public F5FeeHostelMiniSystem(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        return calculatePenaltyForFare(ticketFare, minutesLate);
    }

    public void processAccount(F5FeeHostelMiniSystem account, double amount, int minutesLate) {
        if (account == null) {
            nullSkippedCount++;
            return;
        }

        double penalty = calculatePenaltyForFare(amount, minutesLate);
        if (account instanceof Sleeper) {
            sleeperCount++;
            penalty *= 1.25;
        } else {
            regularCount++;
        }
        processedCount++;
        grandTotal += penalty;
    }

    public static void processBatch(F5FeeHostelMiniSystem[] accounts,
                                    double[] amounts,
                                    int[] minutesLateArray) {
        validateBatch(accounts, amounts, minutesLateArray);
        resetSummary();

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                nullSkippedCount++;
            } else {
                accounts[i].processAccount(accounts[i], amounts[i], minutesLateArray[i]);
            }
        }

        printSummary();
    }

    private static void validateBatch(F5FeeHostelMiniSystem[] accounts,
                                      double[] amounts,
                                      int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null
                || accounts.length != amounts.length
                || accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Parallel arrays must have equal lengths");
        }
    }

    private static double calculatePenaltyForFare(double fare, int minutesLate) {
        if (Double.isNaN(fare) || Double.isInfinite(fare) || fare < 0) {
            throw new IllegalArgumentException("fare cannot be negative");
        }
        if (minutesLate < 0) {
            throw new IllegalArgumentException("minutesLate cannot be negative");
        }
        if (minutesLate == 0) {
            return 0.0;
        }

        int first = Math.min(minutesLate, 5);
        int second = Math.min(Math.max(minutesLate - 5, 0), 10);
        int third = Math.max(minutesLate - 15, 0);
        double tiered = fare * (first * 0.005 + second * 0.01 + third * 0.02);
        double floor = fare * defaultMinimumPenaltyPercent / 100.0;
        return Math.max(tiered, floor);
    }

    private static void resetSummary() {
        processedCount = 0;
        nullSkippedCount = 0;
        sleeperCount = 0;
        regularCount = 0;
        grandTotal = 0.0;
    }

    private static void printSummary() {
        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = %.2f%n",
                processedCount, nullSkippedCount, sleeperCount, regularCount, grandTotal);
    }

    public String getBookingId() {
        return bookingId;
    }
}

class BusTicketAccount extends F5FeeHostelMiniSystem {
    public BusTicketAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public BusTicketAccount(String bookingId) {
        super(bookingId);
    }

    public static void processBatch(BusTicketAccount[] accounts,
                                    double[] amounts,
                                    int[] minutesLateArray) {
        F5FeeHostelMiniSystem.processBatch(accounts, amounts, minutesLateArray);
    }
}

class Sleeper extends BusTicketAccount {
    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public Sleeper(String bookingId) {
        super(bookingId);
    }
}
