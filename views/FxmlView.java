package com.oopclass.breadapp.views;

import java.util.ResourceBundle;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
public enum FxmlView {

    USER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/User.fxml";
        }
    },PRODUCT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("product.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Product.fxml";
        }
    },RESERVATION {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("reservation.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Reservation.fxml";
        }
    },EMPLOYEE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("employee.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Employee.fxml";
        }
    },CUSTOMER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("customer.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Customer.fxml";
        }
    };

    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
