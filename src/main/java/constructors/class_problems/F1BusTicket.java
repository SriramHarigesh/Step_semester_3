package oop.class_problems;

import java.util.HashSet;
import java.util.Set;

public class F1AttendanceSystem {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn;

    public F1AttendanceSystem(String passengerName, String destination) {
        if (!isMeaningful(passengerName)) {
            throw new IllegalArgumentException("Invalid passenger name");
        }
        if (!isMeaningful(destination)) {
            throw new IllegalArgumentException("Invalid destination");
        }
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
    }

    private static boolean isMeaningful(String value) {
        return value != null && value.trim().matches("[A-Za-z]+(?:[ '-][A-Za-z]+)*");
    }

    public void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Passenger checked in.");
        } else {
            System.out.println("Passenger was already checked in.");
        }
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicatesSkipped = 0;
        Set<String> acceptedPairs = new HashSet<>();

        if (rawBookings != null) {
            for (String[] rawBooking : rawBookings) {
                try {
                    if (rawBooking == null || rawBooking.length < 2) {
                        throw new IllegalArgumentException("Incomplete booking");
                    }
                    F1AttendanceSystem ticket = new F1AttendanceSystem(rawBooking[0], rawBooking[1]);
                    String key = (ticket.passengerName + "|" + ticket.destination).toLowerCase();
                    if (!acceptedPairs.add(key)) {
                        duplicatesSkipped++;
                    } else {
                        valid++;
                    }
                } catch (IllegalArgumentException exception) {
                    rejected++;
                }
            }
        }
        System.out.println("Valid: " + valid + " | Rejected: " + rejected
                + " | Duplicates skipped: " + duplicatesSkipped);
    }
}

class BusTicket extends F1AttendanceSystem {
    public BusTicket(String passengerName, String destination) {
        super(passengerName, destination);
    }
}
