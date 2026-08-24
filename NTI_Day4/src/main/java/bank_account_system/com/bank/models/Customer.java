package bank_account_system.com.bank.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer {
    private String name;
    private String email;
    private String id;

    private static final Logger logger = LoggerFactory.getLogger(Customer.class);

    public Customer(String name, String email, String id) {
        if (name == null || name.isEmpty())
            logger.error("Name must have a value");
        else
            this.name = name;
        if (email != null && email.contains("@"))
            this.email = email;
        else
            logger.error("Invalid email format");
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " (" + id + ", " + email + ")\n";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

}
