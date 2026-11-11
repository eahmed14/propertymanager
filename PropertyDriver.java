/*
 * Class: CMSC203 
 * Instructor: 
 * Description: Property management application driver class demonstrating core functionality.
 * Due: 11/10/2026
 * Platform/compiler: Eclipse / Java
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print Your Name Here: Ezedin
*/

public class PropertyDriver {
    public static void main(String[] args) {
        ManagementCompany company = new ManagementCompany("Campus Realty", "123-45-6789", 6.0);

        Property p1 = new Property("Apt 1", "Rockville", 1200.0, "Bob", 1, 1, 2, 2);
        Property p2 = new Property("Apt 2", "Gaithersburg", 1500.0, "Carol", 4, 4, 2, 2);

        System.out.println("Adding Property 1 index: " + company.addProperty(p1));
        System.out.println("Adding Property 2 index: " + company.addProperty(p2));

        System.out.println("Property Count: " + company.getPropertyCount());
        System.out.println("Total Rent: " + company.totalRent());

        System.out.println("\nCompany Summary:");
        System.out.println(company.toString());
    }
}
