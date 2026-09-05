package oop.assignment_problems;

public class F3ParkingSlotAllocation {
    private final String canteenCode;
    private final String canteenName;
    private final int trustScore;

    public F3ParkingSlotAllocation(String canteenCode, String canteenName, int trustScore) {
        if (canteenCode == null || canteenCode.trim().isEmpty()) {
            throw new IllegalArgumentException("canteenCode cannot be blank");
        }
        if (canteenName == null || canteenName.trim().isEmpty()) {
            throw new IllegalArgumentException("canteenName cannot be blank");
        }
        if (trustScore < 0 || trustScore > 5) {
            throw new IllegalArgumentException("trustScore must be between 0 and 5");
        }
        this.canteenCode = canteenCode.trim();
        this.canteenName = canteenName.trim();
        this.trustScore = trustScore;
    }

    public F3ParkingSlotAllocation(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    public int compareTo(F3ParkingSlotAllocation other) {
        if (other == null) {
            throw new IllegalArgumentException("other cannot be null");
        }

        int result = Integer.compare(other.trustScore, trustScore);
        if (result != 0) {
            return result;
        }

        result = canteenCode.compareToIgnoreCase(other.canteenCode);
        if (result != 0) {
            return result;
        }

        result = Integer.compare(canteenName.length(), other.canteenName.length());
        if (result != 0) {
            return result;
        }

        result = canteenName.compareToIgnoreCase(other.canteenName);
        if (result != 0) {
            return result;
        }

        result = canteenCode.compareTo(other.canteenCode);
        if (result != 0) {
            return result;
        }
        return canteenName.compareTo(other.canteenName);
    }

    public static F3ParkingSlotAllocation[] rankCanteens(F3ParkingSlotAllocation[] canteens) {
        if (canteens == null) {
            return new F3ParkingSlotAllocation[0];
        }

        F3ParkingSlotAllocation[] ranked = canteens.clone();
        for (int i = 1; i < ranked.length; i++) {
            F3ParkingSlotAllocation current = ranked[i];
            int j = i - 1;
            while (j >= 0 && current.compareTo(ranked[j]) < 0) {
                ranked[j + 1] = ranked[j];
                j--;
            }
            ranked[j + 1] = current;
        }
        return ranked;
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public int getTrustScore() {
        return trustScore;
    }
}

class Canteen extends F3ParkingSlotAllocation {
    public Canteen(String canteenCode, String canteenName, int trustScore) {
        super(canteenCode, canteenName, trustScore);
    }

    public Canteen(String canteenCode, String canteenName) {
        super(canteenCode, canteenName);
    }

    public int compareTo(Canteen other) {
        return super.compareTo(other);
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null) {
            return new Canteen[0];
        }
        Canteen[] ranked = canteens.clone();
        for (int i = 1; i < ranked.length; i++) {
            Canteen current = ranked[i];
            int j = i - 1;
            while (j >= 0 && current.compareTo(ranked[j]) < 0) {
                ranked[j + 1] = ranked[j];
                j--;
            }
            ranked[j + 1] = current;
        }
        return ranked;
    }
}
