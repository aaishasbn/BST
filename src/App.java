import Tree.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * This class implements a graphical user interface for a simple TikTok-like system.
 */
public class App extends JFrame implements ActionListener {

    private TiktokApp system;
    private JLabel accountLabel, descriptionLabel, titleLabel, fileLabel, likesLabel;
    private JTextField accountField, descriptionField, titleField, fileField, likesField;
    private JTextArea outputArea;
    private JButton addButton, deleteButton, addPostButton, viewButton;
  /**
     * Creates a new instance of the App class, which sets up the window and initializes the TiktokSystem.
     */
    public App() {
        // Set up the window
        setTitle("Tiktok App");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set up the TiktokSystem
        system = new TiktokApp();

        // Add components to the window
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        accountLabel = new JLabel("Account:");
        accountLabel.setForeground(Color.BLACK); 
        inputPanel.add(accountLabel);
        accountField = new JTextField();
        inputPanel.add(accountField);
        descriptionLabel = new JLabel("Profile Description:");
        descriptionLabel.setForeground(Color.BLACK);
        inputPanel.add(descriptionLabel);
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        titleLabel = new JLabel("Post Title:");
        titleLabel.setForeground(Color.BLACK);
        inputPanel.add(titleLabel);
        titleField = new JTextField();
        inputPanel.add(titleField);
        fileLabel = new JLabel("Video File:");
        fileLabel.setForeground(Color.BLACK);
        inputPanel.add(fileLabel);
        fileField = new JTextField();
        inputPanel.add(fileField);
        likesLabel = new JLabel("Likes: ", new ImageIcon("download.jpg"), JLabel.LEFT);
        inputPanel.add(likesLabel);
        likesField = new JTextField();
        inputPanel.add(likesField);
        add(inputPanel, BorderLayout.NORTH);
        

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        addButton = new Roundedbutton("Add Account:");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);
        deleteButton = new Roundedbutton("Delete Account");
        deleteButton.setBackground(Color.WHITE); 
        deleteButton.setForeground(Color.BLACK);
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);
        addPostButton = new Roundedbutton("Add Post");
        addPostButton.addActionListener(this);
        addPostButton.setBackground(Color.BLACK); // Set background color to green
        addPostButton.setForeground(Color.WHITE);
        buttonPanel.add(addPostButton);
        viewButton = new Roundedbutton("View Accounts");
        viewButton.addActionListener(this);
        viewButton.setBackground(Color.WHITE); // Set background color to blue
        viewButton.setForeground(Color.BLACK);
        buttonPanel.add(viewButton);
        add(buttonPanel, BorderLayout.CENTER);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
    }
/**
     * Performs the specified action when a button is pressed.
     * 
     * @param e the ActionEvent representing the button press
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String account = accountField.getText();
            String description = descriptionField.getText();
            if (account.isEmpty() || description.isEmpty()) {
                outputArea.setText("Error: please fill in both fields.");
                return;
            }
            if (system.getAccount(account) != null) {
                outputArea.setText("Error: account already exists.");
                return;
            }
            system.addAccount(account, description);
            accountField.setText("");
            descriptionField.setText("");
            outputArea.setText("Account added:\n" + account + "\n");
        } else if (e.getSource() == deleteButton) {
            String account = accountField.getText();
            if (account.isEmpty()) {
                outputArea.setText("Error: please enter an account name.");
                return;
            }
            if (system.getAccount(account) == null) {
                outputArea.setText("Error: account does not exist.");
                return;
            }
            system.deleteAccount(account);
            accountField.setText("");
            descriptionField.setText("");
            outputArea.setText("Account deleted:\n" + account + "\n");
        } else if (e.getSource() == addPostButton) {
            String account = accountField.getText();
            String title = titleField.getText();
            String file = fileField.getText();
            String likesStr = likesField.getText();
            if (account.isEmpty() || title.isEmpty() || file.isEmpty() || likesStr.isEmpty()) {
                outputArea.setText("Error: please fill in all fields.");
                return;
            }
            if (system.getAccount(account) == null) {
                outputArea.setText("Error: account does not exist.");
                return;
            }
            int likes;
            try {
                likes = Integer.parseInt(likesStr);
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: likes must be a number.");
                return;
            }
            system.addPost(account, title, file, likes);
            titleField.setText("");
            fileField.setText("");
            likesField.setText("");
            outputArea.setText("Post added to account:\n" + account + "\n");
        } else if (e.getSource() == viewButton) {
            ArrayList<String> accounts = system.listAccounts();
            if (accounts.isEmpty()) {
                outputArea.setText("No accounts found.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (String account : accounts) {
                    sb.append(account).append("\n");
                }
                outputArea.setText("List of accounts:\n" + sb.toString());
            }
        }
    }
    

public static void main(String[] args) {
    App ui = new App();
  
    ui.setVisible(true);
}
}




