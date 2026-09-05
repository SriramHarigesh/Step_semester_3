package oop.class_problems;

public final class F4StaticInstanceBoundary {
    private final BoardingPenaltyCalculator calculator;

    public F4StaticInstanceBoundary(double minimumPenaltyPercent) {
        calculator = new BoardingPenaltyCalculator(minimumPenaltyPercent);
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        return calculator.calculatePenalty(ticketFare, minutesLate);
    }
}

final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (Double.isNaN(minimumPenaltyPercent)
                || Double.isInfinite(minimumPenaltyPercent)
                || minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException("minimumPenaltyPercent cannot be negative");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    final double calculatePenalty(double ticketFare, int minutesLate) {
        if (Double.isNaN(ticketFare) || Double.isInfinite(ticketFare) || ticketFare < 0) {
            throw new IllegalArgumentException("ticketFare cannot be negative");
        }
        if (minutesLate < 0) {
            throw new IllegalArgumentException("minutesLate cannot be negative");
        }
        if (minutesLate == 0) {
            return 0.0;
        }

        int firstTierMinutes = Math.min(minutesLate, 5);
        int secondTierMinutes = Math.min(Math.max(minutesLate - 5, 0), 10);
        int thirdTierMinutes = Math.max(minutesLate - 15, 0);
        double tieredPenalty = ticketFare * (firstTierMinutes * 0.005
                + secondTierMinutes * 0.01
                + thirdTierMinutes * 0.02);
        double minimumPenalty = ticketFare * minimumPenaltyPercent / 100.0;
        return Math.max(tieredPenalty, minimumPenalty);
    }
}
