package oop.class_problems;

public class F3HostelRoomAllocation {
    private final String routeCode;
    private final String routeName;
    private final int priority;

    public F3HostelRoomAllocation(String routeCode, String routeName, int priority) {
        if (routeCode == null || routeCode.trim().isEmpty()) {
            throw new IllegalArgumentException("routeCode cannot be blank");
        }
        if (routeName == null || routeName.trim().isEmpty()) {
            throw new IllegalArgumentException("routeName cannot be blank");
        }
        this.routeCode = routeCode.trim();
        this.routeName = routeName.trim();
        this.priority = priority;
    }

    public F3HostelRoomAllocation(String routeCode, String routeName) {
        this(routeCode, routeName, 3);
    }

    public int compareTo(F3HostelRoomAllocation other) {
        if (other == null) {
            throw new IllegalArgumentException("other cannot be null");
        }

        int result = Integer.compare(other.priority, priority);
        if (result != 0) {
            return result;
        }
        result = routeCode.compareToIgnoreCase(other.routeCode);
        if (result != 0) {
            return result;
        }
        result = Integer.compare(routeName.length(), other.routeName.length());
        if (result != 0) {
            return result;
        }
        result = routeName.compareToIgnoreCase(other.routeName);
        if (result != 0) {
            return result;
        }
        return 0;
    }

    public static F3HostelRoomAllocation[] rankRoutes(F3HostelRoomAllocation[] routes) {
        if (routes == null) {
            return new F3HostelRoomAllocation[0];
        }

        F3HostelRoomAllocation[] ranked = routes.clone();
        for (int i = 1; i < ranked.length; i++) {
            F3HostelRoomAllocation current = ranked[i];
            int j = i - 1;
            while (j >= 0 && current.compareTo(ranked[j]) < 0) {
                ranked[j + 1] = ranked[j];
                j--;
            }
            ranked[j + 1] = current;
        }
        return ranked;
    }

    public String getRouteCode() {
        return routeCode;
    }
}

class BusRoute extends F3HostelRoomAllocation {
    public BusRoute(String routeCode, String routeName, int priority) {
        super(routeCode, routeName, priority);
    }

    public BusRoute(String routeCode, String routeName) {
        super(routeCode, routeName);
    }

    public int compareTo(BusRoute other) {
        return super.compareTo(other);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null) {
            return new BusRoute[0];
        }
        BusRoute[] ranked = routes.clone();
        for (int i = 1; i < ranked.length; i++) {
            BusRoute current = ranked[i];
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
