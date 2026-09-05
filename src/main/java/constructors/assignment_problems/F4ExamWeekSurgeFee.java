package constructors.assignment_problems;

public final class F4ExamWeekSurgeFee {
    private final SurgeFeeCalculator calculator;

    public F4ExamWeekSurgeFeele minimumSurgePercent) {
        calculator = new SurgeFeeCalculator(minimumSurgePercent);
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        return calculator.calculateSurgeFee(orderValue, delayMinutes);
    }
}

final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    SurgeFeeCalculator(double minimumSurgePercent) {
        if (Double.isNaN(minimumSurgePercent)
                || Double.isInfinite(minimumSurgePercent)
                || minimumSurgePercent < 0) {
            throw new IllegalArgumentException("minimumSurgePercent cannot be negative");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (Double.isNaN(orderValue) || Double.isInfinite(orderValue) || orderValue < 0) {
            throw new IllegalArgumentException("orderValue cannot be negative");
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("delayMinutes cannot be negative");
        }
        if (delayMinutes == 0) {
            return 0.0;
        }

        int firstTierMinutes = Math.min(delayMinutes, 5);
        int secondTierMinutes = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int thirdTierMinutes = Math.max(delayMinutes - 15, 0);

        double tieredFee = orderValue * (firstTierMinutes * 0.005
                + secondTierMinutes * 0.01
                + thirdTierMinutes * 0.02);
        double minimumFee = orderValue * minimumSurgePercent / 100.0;
        return Math.max(tieredFee, minimumFee);
    }
}
