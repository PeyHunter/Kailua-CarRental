package Models;

public class CarGroup {

    private int carGroupID;
    private String groupName;
    private String description;

    // Constructor for new car group (without ID)
    public CarGroup(String groupName, String description) {
        this.groupName = groupName;
        this.description = description;
    }

    // Constructor for existing car group (with ID)
    public CarGroup(int carGroupID, String groupName, String description) {
        this.carGroupID = carGroupID;
        this.groupName = groupName;
        this.description = description;
    }


    // Getters
    public int getCarGroupID()      {return carGroupID;}
    public String getGroupName()    {return groupName;}
    public String getDescription()  {return description;}


    // Setters
    public void setCarGroupID(int carGroupID)         {this.carGroupID = carGroupID;}
    public void setGroupName(String groupName)        {this.groupName = groupName;}
    public void setDescription(String description)    {this.description = description;}



    @Override
    public String toString() {
        return "Car Group ID: " + carGroupID + "\n" +
                "Group Name: " + groupName + "\n" +
                "Description: " + description + "\n";
    }
}