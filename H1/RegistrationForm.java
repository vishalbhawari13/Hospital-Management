package Hospital.H1;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class RegistrationForm {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Hospital";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Root@123";
    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "Admin@123";

    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> openLandingPage());
    }

    private static void openLandingPage() {
        JFrame landingFrame = new JFrame("ArogyaSync");
        landingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        landingFrame.setSize(400, 200);
        landingFrame.setLayout(new GridLayout(1, 3));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            landingFrame.dispose();
            openLoginForm();
        });

        JButton signupButton = new JButton("Signup");
        signupButton.addActionListener(e -> {
            landingFrame.dispose();
            createAndShowGUI();
        });

        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(e -> {
            landingFrame.dispose();
            openAdminLoginPage();
        });

        landingFrame.add(loginButton);
        landingFrame.add(signupButton);
        landingFrame.add(adminButton);

        landingFrame.setLocationRelativeTo(null); // Center the frame on the screen
        landingFrame.setVisible(true);
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(9, 2));

        // Form fields
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField mobileNumberField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        // Labels
        frame.add(new JLabel("Name:"));
        frame.add(nameField);

        frame.add(new JLabel("Age:"));
        frame.add(ageField);

        frame.add(new JLabel("Gender:"));
        frame.add(genderComboBox);

        frame.add(new JLabel("Mobile Number:"));
        frame.add(mobileNumberField);

        frame.add(new JLabel("Email:"));
        frame.add(emailField);

        frame.add(new JLabel("Username:"));
        frame.add(usernameField);

        frame.add(new JLabel("Password:"));
        frame.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            registerUser(
                    nameField.getText(),
                    ageField.getText(),
                    (String) genderComboBox.getSelectedItem(),
                    mobileNumberField.getText(),
                    emailField.getText(),
                    usernameField.getText(),
                    new String(passwordField.getPassword())
            );

            addLoginButton();
        });

        frame.add(new JLabel()); // Empty label for spacing
        frame.add(registerButton);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            closeRegistrationPage();
            openLoginForm();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            closeRegistrationPage();
            openLandingPage();
        });

        frame.add(loginButton);
        frame.add(backButton);

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private static void openAdminLoginPage() {
        JFrame adminLoginFrame = new JFrame("Admin Login");
        adminLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminLoginFrame.setSize(300, 150);
        adminLoginFrame.setLayout(new GridLayout(3, 2));

        JTextField adminUsernameField = new JTextField();
        JPasswordField adminPasswordField = new JPasswordField();

        adminLoginFrame.add(new JLabel("Admin Username:"));
        adminLoginFrame.add(adminUsernameField);
        adminLoginFrame.add(new JLabel("Admin Password:"));
        adminLoginFrame.add(adminPasswordField);

        JButton adminLoginButton = new JButton("Login");
        adminLoginButton.addActionListener(e -> {
            String adminUsername = adminUsernameField.getText();
            String adminPassword = new String(adminPasswordField.getPassword());

            if (adminUsername.equals(DEFAULT_ADMIN_USERNAME) && adminPassword.equals(DEFAULT_ADMIN_PASSWORD)) {
                adminLoginFrame.dispose();
                openAdminInterface();
            } else {
                JOptionPane.showMessageDialog(adminLoginFrame, "Invalid admin credentials. Please try again.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            adminLoginFrame.dispose();
            openLandingPage();
        });

        adminLoginFrame.add(adminLoginButton);
        adminLoginFrame.add(backButton);

        adminLoginFrame.setLocationRelativeTo(null); // Center the frame on the screen
        adminLoginFrame.setVisible(true);
    }

    private static void openAdminInterface() {
        JFrame adminFrame = new JFrame("Admin Interface");
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setSize(400, 200);
        adminFrame.setLayout(new GridLayout(3, 1));

        JButton userDatabaseButton = new JButton("User Database");
        userDatabaseButton.addActionListener(e -> {
            adminFrame.dispose();
            openUserDatabasePage();
        });

        JButton addHospitalInfoButton = new JButton("Add Hospital Info");
        addHospitalInfoButton.addActionListener(e -> {
            adminFrame.dispose();
            openAddHospitalInfoPage();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            adminFrame.dispose();
            openLandingPage();
        });

        adminFrame.add(userDatabaseButton);
        adminFrame.add(addHospitalInfoButton);
        adminFrame.add(backButton);

        adminFrame.setLocationRelativeTo(null); // Center the frame on the screen
        adminFrame.setVisible(true);
    }

    private static void openAddHospitalInfoPage() {
        JFrame addHospitalFrame = new JFrame("Add Hospital Info");
        addHospitalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addHospitalFrame.setSize(400, 300);
        addHospitalFrame.setLayout(new GridLayout(6, 2));

        // Form fields for Hospital Info
        JTextField hospitalNameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneNumberField = new JTextField();
        JTextField ratingField = new JTextField();
        JTextField doctorNameField = new JTextField();

        // Labels for Hospital Info fields
        addHospitalFrame.add(new JLabel("Hospital Name:"));
        addHospitalFrame.add(hospitalNameField);

        addHospitalFrame.add(new JLabel("Address:"));
        addHospitalFrame.add(addressField);

        addHospitalFrame.add(new JLabel("Phone Number:"));
        addHospitalFrame.add(phoneNumberField);

        addHospitalFrame.add(new JLabel("Rating:"));
        addHospitalFrame.add(ratingField);

        addHospitalFrame.add(new JLabel("Doctor Name:"));
        addHospitalFrame.add(doctorNameField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saveHospitalInfo(
                    hospitalNameField.getText(),
                    addressField.getText(),
                    phoneNumberField.getText(),
                    ratingField.getText(),
                    doctorNameField.getText()
            );

            // After saving, go back to Admin Interface
            addHospitalFrame.dispose();
            openAdminInterface();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            addHospitalFrame.dispose();
            openAdminInterface();
        });

        addHospitalFrame.add(saveButton);
        addHospitalFrame.add(backButton);

        addHospitalFrame.setLocationRelativeTo(null); // Center the frame on the screen
        addHospitalFrame.setVisible(true);
    }

    private static void saveHospitalInfo(String hospitalName, String address, String phoneNumber, String rating, String doctorName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO hospitals (name, address, phone_number, rating, doctor_name) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, hospitalName);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, phoneNumber);
                preparedStatement.setString(4, rating);
                preparedStatement.setString(5, doctorName);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Hospital info saved successfully!");
                } else {
                    System.out.println("Failed to save hospital info.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void openUserDatabasePage() {
        JFrame userDatabaseFrame = new JFrame("User Database");
        userDatabaseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userDatabaseFrame.setSize(800, 400);

        // Fetch and display user registration details using JTable
        JTable userDetailsTable = new JTable(getUserData(), getColumnNames());
        JScrollPane scrollPane = new JScrollPane(userDetailsTable);

        userDatabaseFrame.add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            userDatabaseFrame.dispose();
            openAdminInterface();
        });

        userDatabaseFrame.add(backButton, BorderLayout.SOUTH);

        userDatabaseFrame.setLocationRelativeTo(null); // Center the frame on the screen
        userDatabaseFrame.setVisible(true);
    }

    private static Object[][] getUserData() {
        ArrayList<Object[]> data = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM users";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Object[] row = {
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("mobile_number"),
                            resultSet.getString("email"),
                            resultSet.getString("username"),
                            resultSet.getString("password")
                    };
                    data.add(row);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }

    private static String[] getColumnNames() {
        return new String[]{"Name", "Age", "Gender", "Mobile Number", "Email", "Username", "Password"};
    }

    private static void registerUser(String name, String age, String gender, String mobileNumber, String email, String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO users (name, age, gender, mobile_number, email, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, Integer.parseInt(age));
                preparedStatement.setString(3, gender);
                preparedStatement.setString(4, mobileNumber);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, username);
                preparedStatement.setString(7, password);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User registered successfully!");
                } else {
                    System.out.println("Failed to register user.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void addLoginButton() {
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            closeRegistrationPage();
            openLoginForm();
        });

        frame.add(loginButton);
        frame.revalidate();
        frame.repaint();
    }

    private static void openLoginForm() {
        JFrame loginFrame = new JFrame("Login Form");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setLayout(new GridLayout(3, 2));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        loginFrame.add(new JLabel("Username:"));
        loginFrame.add(usernameField);
        loginFrame.add(new JLabel("Password:"));
        loginFrame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (loginUser(username, password)) {
                JOptionPane.showMessageDialog(loginFrame, "Successfully logged in!");
                openSearchPage();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid credentials. Please try again.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            loginFrame.dispose();
            openLandingPage();
        });

        loginFrame.add(loginButton);
        loginFrame.add(backButton);

        loginFrame.setLocationRelativeTo(null); // Center the frame on the screen
        loginFrame.setVisible(true);
    }

    private static void openSearchPage() {
        JFrame searchFrame = new JFrame("Search Options");
        searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchFrame.setSize(800, 600);
        searchFrame.setLayout(new FlowLayout());

        // Display registered hospitals' info in cards
        displayHospitalInfo(searchFrame);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            searchFrame.dispose();
            openLoginForm(); // Update this to navigate to the appropriate page
        });
        searchFrame.add(backButton);

        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
    }
    private static void displayHospitalInfo(JFrame frame) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM hospitals";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String hospitalName = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String phoneNumber = resultSet.getString("phone_number");
                    String rating = resultSet.getString("rating");
                    String doctorName = resultSet.getString("doctor_name");

                    JPanel cardPanel = new JPanel();
                    cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
                    cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    cardPanel.setPreferredSize(new Dimension(200, 200));

                    JLabel nameLabel = new JLabel("Hospital Name: " + hospitalName);
                    JLabel addressLabel = new JLabel("Address: " + address);
                    JLabel phoneLabel = new JLabel("Phone Number: " + phoneNumber);
                    JLabel ratingLabel = new JLabel("Rating: " + rating);
                    JLabel doctorLabel = new JLabel("Doctor Name: " + doctorName);

                    JButton appointmentButton = new JButton("Appointment");
                    appointmentButton.addActionListener(e -> {
                        frame.dispose();
                        openAppointmentPage();
                    });

                    cardPanel.add(nameLabel);
                    cardPanel.add(addressLabel);
                    cardPanel.add(phoneLabel);
                    cardPanel.add(ratingLabel);
                    cardPanel.add(doctorLabel);
                    cardPanel.add(appointmentButton);

                    frame.add(cardPanel);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void openAppointmentPage() {
        JFrame appointmentFrame = new JFrame("Appointment Booking");
        appointmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appointmentFrame.setSize(400, 300);
        appointmentFrame.setLayout(new GridLayout(4, 2));

        JLabel timeLabel = new JLabel("Select Time:");
        JComboBox<String> timeComboBox = new JComboBox<>(getAvailableTimeSlots());

        JLabel dateLabel = new JLabel("Select Date:");
        JTextField dateField = new JTextField(); // Use a date picker for a better user experience

        JButton confirmButton = new JButton("Confirm Appointment");
        confirmButton.addActionListener(e -> {
            String selectedTime = (String) timeComboBox.getSelectedItem();
            String selectedDate = dateField.getText();
            String doctorName = "Doctor Name"; // Replace with actual doctor name

            if (isAppointmentAvailable(selectedTime, selectedDate)) {
                // Book the appointment and store data to the database
                bookAppointment(selectedTime, selectedDate, doctorName);
                JOptionPane.showMessageDialog(appointmentFrame, "Your Appointment has been booked at "
                        + selectedTime + " on " + selectedDate + " with Dr. " + doctorName);
            } else {
                JOptionPane.showMessageDialog(appointmentFrame, "Appointment not available. Please choose another time or date.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            appointmentFrame.dispose();
            openSearchPage();
        });

        appointmentFrame.add(timeLabel);
        appointmentFrame.add(timeComboBox);
        appointmentFrame.add(dateLabel);
        appointmentFrame.add(dateField);
        appointmentFrame.add(confirmButton);
        appointmentFrame.add(backButton);

        appointmentFrame.setLocationRelativeTo(null);
        appointmentFrame.setVisible(true);
    }

    private static String[] getAvailableTimeSlots() {
        // For simplicity, assume all time slots are available
        return new String[]{"9am - 10am", "10am - 11am", "11am - 12pm", "12pm - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm", "4pm - 5pm", "5pm - 6pm", "6pm - 7pm"};
    }

    private static boolean isAppointmentAvailable(String selectedTime, String selectedDate) {
        // For simplicity, assume all appointments are available
        return true;
    }

    private static void bookAppointment(String selectedTime, String selectedDate, String doctorName) {
        // Add logic to store appointment details in the database
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO appointments (time, date, doctor_name) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, selectedTime);
                preparedStatement.setString(2, selectedDate);
                preparedStatement.setString(3, doctorName);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Appointment booked successfully!");
                } else {
                    System.out.println("Failed to book appointment.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean loginUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private static void closeRegistrationPage() {
        frame.dispose();
    }
}
