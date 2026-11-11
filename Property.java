/*
 * Class: CMSC203 
 * Instructor: 
 * Description: Represents a rental property with a name, city, rent, owner, and plot.
 * Due: 11/10/2026
 * Platform/compiler: Eclipse / Java
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print Your Name Here: Ezedin
*/

public class Property {
    private String propertyName;
    private String city;
    private double rentAmount;
    private String owner;
    private Plot plot;

    public Property() {
        this("", "", 0.0, "", 0, 0, 1, 1);
    }

    // 4-argument constructor auto-assigns (0,0,1,1) by default
    public Property(String propertyName, String city, double rentAmount, String owner) {
        this(propertyName, city, rentAmount, owner, 0, 0, 1, 1);
    }

    public Property(String propertyName, String city, double rentAmount, String owner, int x, int y, int width, int depth) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentAmount = rentAmount;
        this.owner = owner;
        this.plot = new Plot(x, y, width, depth);
    }

    public Property(Property otherProperty) {
        if (otherProperty != null) {
            this.propertyName = otherProperty.propertyName;
            this.city = otherProperty.city;
            this.rentAmount = otherProperty.rentAmount;
            this.owner = otherProperty.owner;
            this.plot = new Plot(otherProperty.plot);
        } else {
            this.propertyName = "";
            this.city = "";
            this.rentAmount = 0.0;
            this.owner = "";
            this.plot = new Plot();
        }
    }

    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getRentAmount() { return rentAmount; }
    public void setRentAmount(double rentAmount) { this.rentAmount = rentAmount; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public Plot getPlot() { return new Plot(plot); }
    public void setPlot(Plot plot) { this.plot = new Plot(plot); }

    @Override
    public String toString() {
        return propertyName + "," + city + "," + owner + "," + rentAmount;
    }
}
