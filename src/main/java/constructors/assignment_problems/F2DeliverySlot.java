package oop.assignment_problems;

public class F2EmployeeInheritance {
    private final String orderId;
    private final String timeSlot;

    public F2EmployeeInheritance(String orderId, String timeSlot) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("orderId cannot be blank");
        }
        if (timeSlot == null || timeSlot.trim().isEmpty()) {
            throw new IllegalArgumentException("timeSlot cannot be blank");
        }
        this.orderId = orderId.trim();
        this.timeSlot = timeSlot.trim();
    }

    public F2EmployeeInheritance(String orderId) {
        this(orderId, "ASAP");
    }

    public boolean isPeakHour() {
        return timeSlot.equals("12:00-13:00")
                || timeSlot.equals("13:00-14:00")
                || timeSlot.equals("19:00-20:00")
                || timeSlot.equals("20:00-21:00");
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }
}

class DeliverySlot extends F2EmployeeInheritance {
    public DeliverySlot(String orderId, String timeSlot) {
        super(orderId, timeSlot);
    }

    public DeliverySlot(String orderId) {
        super(orderId);
    }
}
