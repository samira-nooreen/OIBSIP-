package com.online.exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineTestMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizStartScreen());
    }
}

class QuizStartScreen extends JFrame implements ActionListener {
    private JTextField nameField, emailField;
    private JButton btnStart, btnEditProfile;
    private JComboBox<String> difficultyBox;
    private JLabel welcomeLabel, instructionsLabel;

    public static String userName = "User";
    public static String userEmail = "user@example.com";
    public static String difficultyLevel = "Medium";

    public QuizStartScreen() {
        setTitle("Prepare for Quiz");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(220, 230, 250));

        welcomeLabel = new JLabel("Welcome to the Quiz!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(50, 50, 150));
        welcomeLabel.setBounds(50, 20, 320, 30);
        add(welcomeLabel);

        // Instructions
        instructionsLabel = new JLabel("<html>Read the instructions carefully.<br>Ensure you are ready before starting.</html>");
        instructionsLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        instructionsLabel.setForeground(new Color(80, 80, 80));
        instructionsLabel.setBounds(50, 60, 320, 60);
        add(instructionsLabel);

        // Name input
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setForeground(new Color(30, 30, 30));
        nameLabel.setBounds(50, 130, 80, 30);
        add(nameLabel);
        nameField = new JTextField(userName);
        nameField.setBounds(150, 130, 200, 30);
        nameField.setBackground(Color.WHITE);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nameField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 200), 2));
        add(nameField);

        // Email input
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        emailLabel.setForeground(new Color(30, 30, 30));
        emailLabel.setBounds(50, 170, 80, 30);
        add(emailLabel);
        emailField = new JTextField(userEmail);
        emailField.setBounds(150, 170, 200, 30);
        emailField.setBackground(Color.WHITE);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 200), 2));
        add(emailField);

        // Difficulty level selection
        JLabel difficultyLabel = new JLabel("Select Difficulty:");
        difficultyLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        difficultyLabel.setForeground(new Color(30, 30, 30));
        difficultyLabel.setBounds(50, 210, 130, 30);
        add(difficultyLabel);
        String[] levels = { "Easy", "Medium", "Hard" };
        difficultyBox = new JComboBox<>(levels);
        difficultyBox.setSelectedItem(difficultyLevel);
        difficultyBox.setBounds(180, 210, 170, 30);
        difficultyBox.setBackground(new Color(255, 255, 255));
        difficultyBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(difficultyBox);

        // Buttons
        btnStart = new JButton("Start Quiz");
        btnStart.setBounds(90, 290, 120, 40);
        btnStart.setBackground(new Color(60, 180, 75));
        btnStart.setForeground(Color.WHITE);
        btnStart.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnStart.setFocusPainted(false);
        btnStart.addActionListener(this);
        add(btnStart);

        btnEditProfile = new JButton("Edit Profile");
        btnEditProfile.setBounds(230, 290, 120, 40);
        btnEditProfile.setBackground(new Color(0, 122, 255));
        btnEditProfile.setForeground(Color.WHITE);
        btnEditProfile.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnEditProfile.setFocusPainted(false);
        btnEditProfile.addActionListener(this);
        add(btnEditProfile);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStart) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields to proceed.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            userName = name;
            userEmail = email;
            difficultyLevel = (String) difficultyBox.getSelectedItem();

            dispose();
            new OnlineTest(userName, userEmail, difficultyLevel);
        } else if (e.getSource() == btnEditProfile) {
            String currentName = nameField.getText();
            String currentEmail = emailField.getText();
            String newName = JOptionPane.showInputDialog(this, "Edit Name:", currentName);
            String newEmail = JOptionPane.showInputDialog(this, "Edit Email:", currentEmail);
            if (newName != null && !newName.trim().isEmpty()) nameField.setText(newName);
            if (newEmail != null && !newEmail.trim().isEmpty()) emailField.setText(newEmail);
        }
    }
}

class OnlineTest extends JFrame implements ActionListener {
    private static final int TOTAL_TIME = 60; // seconds
    private JLabel questionLabel, timerLabel;
    private JRadioButton[] optionsRadio;
    private JButton btnNext, btnSubmit, btnLogout;
    private ButtonGroup optionsGroup;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem profileItem, logoutItem;

    private String[] questions = {
            "What is Java?",
            "Which is used to write Java programs?",
            "Correct file extension for Java?",
            "Java was developed by?",
            "Which is a Java keyword?"
    };
    private String[][] options = {
            {"A drink", "A game", "A programming language", "A city"},
            {"Notepad", "Pen", "Paper", "Typewriter"},
            {".txt", ".doc", ".java", ".exe"},
            {"Google", "James Gosling", "Steve Jobs", "Elon Musk"},
            {"run", "start", "class", "code"}
    };
    private int[] answers = {2, 0, 2, 1, 2};
    private int score = 0;
    private int currentQuestionIndex = 0;
    private int totalQuestions = questions.length;

    private Timer quizTimer;
    private int timeLeft = TOTAL_TIME;


    private String userName, userEmail, difficultyLevel;

    public OnlineTest(String name, String email, String difficulty) {
        this.userName = name;
        this.userEmail = email;
        this.difficultyLevel = difficulty;

        setTitle("Online Exam");
        setSize(750, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 255));
        setLayout(null);


        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        profileItem = new JMenuItem("Update Profile");
        logoutItem = new JMenuItem("Logout");
        menu.add(profileItem);
        menu.add(logoutItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        profileItem.addActionListener(this);
        logoutItem.addActionListener(this);


        questionLabel = new JLabel();
        questionLabel.setBounds(20, 20, 700, 50);
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        questionLabel.setForeground(new Color(20, 20, 80));
        add(questionLabel);

        timerLabel = new JLabel("Time left: " + timeLeft + "s");
        timerLabel.setBounds(620, 20, 120, 20);
        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        timerLabel.setForeground(new Color(80, 80, 80));
        add(timerLabel);


        optionsRadio = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionsRadio[i] = new JRadioButton();
            optionsRadio[i].setBounds(40, 80 + (i * 35), 680, 30);
            optionsRadio[i].setFont(new Font("SansSerif", Font.PLAIN, 14));
            optionsRadio[i].setBackground(new Color(245, 245, 255));
            optionsRadio[i].setFocusPainted(false);
            optionsRadio[i].setBorder(BorderFactory.createLineBorder(new Color(150, 150, 200), 1));
            add(optionsRadio[i]);
            optionsGroup.add(optionsRadio[i]);
        }


        btnNext = new JButton("Next");
        btnNext.setBounds(150, 300, 100, 40);
        styleButton(btnNext, new Color(60, 180, 75));
        btnNext.addActionListener(this);
        add(btnNext);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(350, 300, 100, 40);
        styleButton(btnSubmit, new Color(255, 165, 0));
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(550, 300, 100, 40);
        styleButton(btnLogout, new Color(220, 20, 60));
        btnLogout.addActionListener(this);
        add(btnLogout);

        setQuestion();
        startTimer();

        setVisible(true);
    }

    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == profileItem) {
            updateProfile();
            return;
        }
        if (source == logoutItem || source == btnLogout) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (quizTimer != null) quizTimer.cancel();
                dispose();
                new QuizStartScreen();
            }
            return;
        }

        if (source == btnNext) {
            evaluateAnswer();
            currentQuestionIndex++;
            if (currentQuestionIndex < totalQuestions) {
                setQuestion();
                resetTimer();
            } else {
                JOptionPane.showMessageDialog(this, "This was the last question.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (source == btnSubmit) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to submit?", "Confirm Submission", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (quizTimer != null) quizTimer.cancel();
                submitExam();
            }
        }
    }

    private void updateProfile() {
        String newName = JOptionPane.showInputDialog(this, "Edit Name:", userName);
        String newEmail = JOptionPane.showInputDialog(this, "Edit Email:", userEmail);
        if (newName != null && !newName.trim().isEmpty()) userName = newName.trim();
        if (newEmail != null && !newEmail.trim().isEmpty()) userEmail = newEmail.trim();
        JOptionPane.showMessageDialog(this, "Profile updated!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void startTimer() {
        if (quizTimer != null) quizTimer.cancel();
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    timerLabel.setText("Time left: " + timeLeft + "s");
                });
                timeLeft--;
                if (timeLeft < 0) {
                    quizTimer.cancel();
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(OnlineTest.this, "Time's up!", "Timeout", JOptionPane.WARNING_MESSAGE);
                        submitExam();
                    });
                }
            }
        }, 0, 1000);
    }

    private void resetTimer() {
        timeLeft = TOTAL_TIME;
        startTimer();
    }

    private void setQuestion() {
        optionsGroup.clearSelection();
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            optionsRadio[i].setText(options[currentQuestionIndex][i]);
        }
        btnNext.setEnabled(currentQuestionIndex < totalQuestions - 1);
    }

    private int getSelectedAnswerIndex() {
        for (int i = 0; i < optionsRadio.length; i++) {
            if (optionsRadio[i].isSelected()) return i;
        }
        return -1;
    }

    private void evaluateAnswer() {
        int selected = getSelectedAnswerIndex();
        if (selected == answers[currentQuestionIndex]) {
            score++;
        }
    }

    private void submitExam() {
        evaluateAnswer();
        JOptionPane.showMessageDialog(this, "Exam Over!\nCorrect answers: " + score, "Results", JOptionPane.INFORMATION_MESSAGE);
        if (quizTimer != null) quizTimer.cancel();
        dispose();
        new QuizStartScreen();
    }
}