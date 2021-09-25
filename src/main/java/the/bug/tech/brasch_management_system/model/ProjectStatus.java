package the.bug.tech.brasch_management_system.model;

public enum ProjectStatus {

    //HASNOTSTARTED("Has not started", "Project has not yet started.", 1),
    //INPROGRESS("In Progress", "Project is in progress.", 2),
    COMPLETED("Completed", "The project has been completed.", 3),
    CANCELLED("Cancelled","Order has been cancelled, due to a stock inconsistency or other reasons.", 4);

    private final String name;
    private final String description;
    private final int orderStatusNumber;

    ProjectStatus(String name, String description, int orderStatusNumber) {
        this.name = name;
        this.description = description;
        this.orderStatusNumber = orderStatusNumber;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getOrderStatusNumber() {
        return orderStatusNumber;
    }
}
