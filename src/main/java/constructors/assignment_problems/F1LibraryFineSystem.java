package oop.assignment_problems;

public class F1LibraryFineSystem {
    private final String studentName;
    private final String dishName;
    private boolean delivered;

    public F1LibraryFineSystem(String studentName, String dishName) {
        if (isBlank(studentName)) {
            throw new IllegalArgumentException("studentName cannot be blank");
        }
        if (isBlank(dishName)) {
            throw new IllegalArgumentException("dishName cannot be blank");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order marked as delivered.");
        } else {
            System.out.println("Order was already marked as delivered.");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        if (rawOrders != null) {
            for (String[] rawOrder : rawOrders) {
                try {
                    if (rawOrder == null || rawOrder.length < 2) {
                        throw new IllegalArgumentException("Incomplete order");
                    }
                    new F1LibraryFineSystem(rawOrder[0], rawOrder[1]);
                    valid++;
                } catch (IllegalArgumentException exception) {
                    rejected++;
                }
            }
        }
        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDishName() {
        return dishName;
    }
}

class FoodOrder extends F1LibraryFineSystem {
    public FoodOrder(String studentName, String dishName) {
        super(studentName, dishName);
    }
}
