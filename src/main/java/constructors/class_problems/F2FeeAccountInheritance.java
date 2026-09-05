package oop.class_problems;

public class F2FeeAccountInheritance {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    public F2FeeAccountInheritance(String tripId, double totalFare, int passengerCount) {
        if (tripId == null || tripId.trim().isEmpty()) {
            throw new IllegalArgumentException("tripId cannot be blank");
        }
        if (Double.isNaN(totalFare) || Double.isInfinite(totalFare) || totalFare < 0) {
            throw new IllegalArgumentException("totalFare cannot be negative");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("passengerCount must be positive");
        }
        this.tripId = tripId.trim();
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public F2FeeAccountInheritance(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public F2FeeAccountInheritance(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        double[] breakdown = new double[passengerCount];
        long totalCents = Math.round(totalFare * 100.0);
        long baseCents = totalCents / passengerCount;
        long remainderCents = totalCents % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            long shareCents = baseCents + (i == passengerCount - 1 ? remainderCents : 0);
            breakdown[i] = shareCents / 100.0;
        }
        return breakdown;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public String getTripId() {
        return tripId;
    }
}

class FareSplitter extends F2FeeAccountInheritance {
    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        super(tripId, totalFare, passengerCount);
    }

    public FareSplitter(String tripId, double totalFare) {
        super(tripId, totalFare);
    }

    public FareSplitter(String tripId) {
        super(tripId);
    }
}
