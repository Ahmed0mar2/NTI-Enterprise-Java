package final_project.com.hotel.models;

public class Customer {
    private String name;
    private String email;
    private String phoneNumber;

    public Customer(String name, String email, String phoneNumber) {
        if (name == null || name.isEmpty()) {
            System.out.println("Name must not be empty");
        } else
            this.name = name;
        if (email != null && email.contains("@"))
            this.email = email;
        else
            System.out.println("Invalid email format");

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            System.out.println("Invalid phone number: cannot be empty");
        } else
            this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name + " (" + email + ", " + phoneNumber + ")";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
