/*
 * Class: CMSC203 
 * Instructor: 
 * Description: Manages an array of properties and boundary plots for a company.
 * Due: 11/10/2026
 * Platform/compiler: Eclipse / Java
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print Your Name Here: Ezedin
*/

public class ManagementCompany {
    public static final int MAX_PROPERTIES = 5;
    private static final int DEFAULT_W = 10;
    private static final int DEFAULT_D = 10;

    private String compName;
    private String idTax;
    private double feeRate;
    private Property[] unitList;
    private Plot boundary;

    public ManagementCompany() {
        this("", "", 0.0, 0, 0, DEFAULT_W, DEFAULT_D);
    }

    public ManagementCompany(String name, String taxID) {
        this(name, taxID, 0.0, 0, 0, DEFAULT_W, DEFAULT_D);
    }

    public ManagementCompany(String name, String taxID, double mgmFeePer) {
        this(name, taxID, mgmFeePer, 0, 0, DEFAULT_W, DEFAULT_D);
    }

    public ManagementCompany(String name, String taxID, double mgmFeePer, int x, int y, int width, int depth) {
        compName = name;
        idTax = taxID;
        feeRate = mgmFeePer;
        boundary = new Plot(x, y, width, depth);
        unitList = new Property[MAX_PROPERTIES];
    }

    public ManagementCompany(ManagementCompany other) {
        if (other != null) {
            compName = other.compName;
            idTax = other.idTax;
            feeRate = other.feeRate;
            boundary = new Plot(other.boundary);
            unitList = new Property[MAX_PROPERTIES];
            for (int i = 0; i < other.unitList.length; i++) {
                if (other.unitList[i] != null) {
                    unitList[i] = new Property(other.unitList[i]);
                }
            }
        } else {
            compName = "";
            idTax = "";
            feeRate = 0.0;
            boundary = new Plot(0, 0, DEFAULT_W, DEFAULT_D);
            unitList = new Property[MAX_PROPERTIES];
        }
    }

    public int addProperty(Property property) {
        if (property == null) return -2;
        if (isPropertiesFull()) return -1;

        // Auto-assign non-overlapping plot position if default coordinates overlap
        Property pToAdd = new Property(property);
        int currentCount = getPropertyCount();
        if (pToAdd.getPlot().getX() == 0 && pToAdd.getPlot().getY() == 0 && currentCount > 0) {
            pToAdd.setPlot(new Plot(currentCount * 2, 0, 1, 1));
        }

        if (!boundary.encompasses(pToAdd.getPlot())) return -3;

        int idx = 0;
        while (idx < unitList.length) {
            if (unitList[idx] != null && unitList[idx].getPlot().overlaps(pToAdd.getPlot())) {
                return -4;
            }
            idx++;
        }

        int targetIndex = -1;
        idx = 0;
        while (idx < unitList.length) {
            if (unitList[idx] == null) {
                targetIndex = idx;
                unitList[idx] = pToAdd;
                break;
            }
            idx++;
        }
        return targetIndex;
    }

    public int addProperty(String name, String city, double rent, String owner) {
        return addProperty(new Property(name, city, rent, owner));
    }

    public int addProperty(String name, String city, double rent, String owner, int x, int y, int width, int depth) {
        return addProperty(new Property(name, city, rent, owner, x, y, width, depth));
    }

    public double totalRent() {
        double sum = 0.0;
        int i = 0;
        while (i < unitList.length) {
            if (unitList[i] != null) {
                sum += unitList[i].getRentAmount();
            }
            i++;
        }
        return sum;
    }

    public double getTotalRent() {
        return totalRent();
    }

    public int maxRentPropertyIndex() {
        int bestIdx = -1;
        double highestRent = -1.0;
        int i = 0;
        while (i < unitList.length) {
            if (unitList[i] != null && unitList[i].getRentAmount() > highestRent) {
                highestRent = unitList[i].getRentAmount();
                bestIdx = i;
            }
            i++;
        }
        return bestIdx;
    }

    public Property maxRentProperty() {
        int idx = maxRentPropertyIndex();
        if (idx == -1) return null;
        return new Property(unitList[idx]);
    }

    public boolean isPropertiesFull() {
        int i = 0;
        while (i < unitList.length) {
            if (unitList[i] == null) return false;
            i++;
        }
        return true;
    }

    public int getPropertiesCount() {
        int tally = 0;
        int i = 0;
        while (i < unitList.length) {
            if (unitList[i] != null) tally++;
            i++;
        }
        return tally;
    }

    public int getPropertyCount() {
        return getPropertiesCount();
    }

    public Property getProperty(int i) {
        if (i >= 0 && i < unitList.length && unitList[i] != null) {
            return new Property(unitList[i]);
        }
        return null;
    }

    public String displayPropertyAtIndex(int i) {
        if (i >= 0 && i < unitList.length && unitList[i] != null) {
            return unitList[i].toString();
        }
        return "";
    }

    public String getName() { return compName; }
    public String getTaxId() { return idTax; }
    public String getTaxID() { return getTaxId(); }
    public double getMgmFeePer() { return feeRate; }
    public Plot getPlot() { return new Plot(boundary); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of the properties for ").append(compName).append(", taxID: ").append(idTax).append("\n");
        sb.append("______________________________________________________\n");
        int i = 0;
        while (i < unitList.length) {
            if (unitList[i] != null) {
                sb.append(unitList[i].toString()).append("\n");
            }
            i++;
        }
        sb.append("______________________________________________________\n");
        sb.append(" total management fee: ").append((totalRent() * feeRate) / 100.0);
        return sb.toString();
    }
}
