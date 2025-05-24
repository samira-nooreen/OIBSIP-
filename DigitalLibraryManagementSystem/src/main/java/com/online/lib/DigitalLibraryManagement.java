package com.online.lib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DigitalLibraryManagement extends JFrame {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JButton btnAdminLogin, btnUserAccess;
    private JButton btnAddBook, btnModifyBook, btnDeleteBook, btnViewAllBooks, btnLogoutAdmin;

    private JButton btnViewBooksUser, btnSearchBook, btnIssueBook, btnReturnBook, btnLogoutUser;

    private JTable booksTable;
    private DefaultTableModel adminTableModel;
    private DefaultTableModel userTableModel;

    private JPanel adminPanel, userPanel, bookTitlesPanel;


    private JTextArea userLogArea;


    private JTextArea bookTitleTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DigitalLibraryManagement());
    }

    public DigitalLibraryManagement() {
        setTitle("Digital Library Management System");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeData();

        issueRandomBooks();

        initializeUI();
        setVisible(true);
    }

    private void initializeData() {
        // Sample books
        books.add(new Book("Effective Java", "Joshua Bloch", "Programming"));
        books.add(new Book("Clean Code", "Robert C. Martin", "Programming"));
        books.add(new Book("The Alchemist", "Paulo Coelho", "Fiction"));
        books.add(new Book("Design Patterns", "Erich Gamma", "Software Engineering"));
        books.add(new Book("Refactoring", "Martin Fowler", "Software Engineering"));
        books.add(new Book("The Pragmatic Programmer", "Andrew Hunt", "Programming"));
        books.add(new Book("Introduction to Algorithms", "Thomas H. Cormen", "Computer Science"));
        books.add(new Book("Java Concurrency in Practice", "Brian Goetz", "Programming"));
        books.add(new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", "Interview Prep"));
        books.add(new Book("The Mythical Man-Month", "Frederick P. Brooks Jr.", "Software Engineering"));
        books.add(new Book("Thinking, Fast and Slow", "Daniel Kahneman", "Psychology"));
        books.add(new Book("The Art of Computer Programming", "Donald Knuth", "Computer Science"));
        books.add(new Book("Clean Architecture", "Robert C. Martin", "Software Design"));
        books.add(new Book("Deep Work", "Cal Newport", "Productivity"));
        books.add(new Book("Atomic Habits", "James Clear", "Self-Help"));
        books.add(new Book("Sapiens", "Yuval Noah Harari", "History"));
        books.add(new Book("Thinking, Fast and Slow", "Daniel Kahneman", "Psychology"));
        books.add(new Book("The Power of Habit", "Charles Duhigg", "Self-Help"));
        books.add(new Book("The Lean Startup", "Eric Ries", "Business"));
        books.add(new Book("Start with Why", "Simon Sinek", "Leadership"));
        books.add(new Book("The Subtle Art of Not Giving a F*ck", "Mark Manson", "Self-Help"));
        books.add(new Book("Educated", "Tara Westover", "Memoir"));
        books.add(new Book("The Four Winds", "Kristin Hannah", "Fiction"));
        books.add(new Book("Becoming", "Michelle Obama", "Biography"));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        books.add(new Book("1984", "George Orwell", "Dystopian"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction"));
        books.add(new Book("The Road", "Cormac McCarthy", "Post-Apocalyptic"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic"));
        books.add(new Book("Moby-Dick", "Herman Melville", "Adventure"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "Romance"));
        books.add(new Book("The Prince", "Niccolò Machiavelli", "Political Philosophy"));
        books.add(new Book("Thinking in Bets", "Annie Duke", "Decision Making"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy"));
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy"));
        books.add(new Book("The Da Vinci Code", "Dan Brown", "Thriller"));
        books.add(new Book("The Catch-22", "Joseph Heller", "Satire"));
        books.add(new Book("Brave New World", "Aldous Huxley", "Dystopian"));
        books.add(new Book("Little Women", "Louisa May Alcott", "Fiction"));

        members.add(new Member("Alice", "alice@example.com", "R001"));
        members.add(new Member("Bob", "bob@example.com", "R002"));
        members.add(new Member("Charlie", "charlie@example.com", "R003"));
        members.add(new Member("David", "david@example.com", "R004"));
        members.add(new Member("Eve", "eve@example.com", "R005"));
        members.add(new Member("Frank", "frank@example.com", "R006"));
        members.add(new Member("Grace", "grace@example.com", "R007"));
        members.add(new Member("Heidi", "heidi@example.com", "R008"));
        members.add(new Member("Ivan", "ivan@example.com", "R009"));
        members.add(new Member("Judy", "judy@example.com", "R010"));
        members.add(new Member("Karen", "karen@example.com", "R011"));
        members.add(new Member("Leo", "leo@example.com", "R012"));
        members.add(new Member("Mallory", "mallory@example.com", "R013"));
        members.add(new Member("Niaj", "niaj@example.com", "R014"));
        members.add(new Member("Olivia", "olivia@example.com", "R015"));
        members.add(new Member("Peggy", "peggy@example.com", "R016"));
        members.add(new Member("Quinn", "quinn@example.com", "R017"));
        members.add(new Member("Rita", "rita@example.com", "R018"));
        members.add(new Member("Steve", "steve@example.com", "R019"));
        members.add(new Member("Trudy", "trudy@example.com", "R020"));
        members.add(new Member("Uma", "uma@example.com", "R021"));
        members.add(new Member("Victor", "victor@example.com", "R022"));
        members.add(new Member("Wendy", "wendy@example.com", "R023"));
        members.add(new Member("Xavier", "xavier@example.com", "R024"));
        members.add(new Member("Yvonne", "yvonne@example.com", "R025"));
        members.add(new Member("Zach", "zach@example.com", "R026"));
    }

    private void initializeUI() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(250, 250, 250));
        add(mainPanel);

        // Selection Panel
        JPanel selectionPanel = new JPanel();
        selectionPanel.setBackground(new Color(200, 230, 250));
        selectionPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblWelcome = new JLabel("🎉 Welcome to the Digital Library 🎉");
        lblWelcome.setFont(new Font("Serif", Font.BOLD, 28));
        lblWelcome.setForeground(new Color(50, 50, 150));
        selectionPanel.add(lblWelcome, gbc);

        btnAdminLogin = createStyledButton("🔑 Admin Login", new Color(60, 180, 75));
        btnAdminLogin.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Admin logged in.");
            showAdminPanel();
            refreshAdminBookTable();
        });
        gbc.gridy = 1;
        selectionPanel.add(btnAdminLogin, gbc);

        btnUserAccess = createStyledButton("👤 User Access", new Color(0, 122, 255));
        btnUserAccess.addActionListener(e -> {
            String[] memberNames = members.stream().map(m -> m.name + " (" + m.rollNo + ")").toArray(String[]::new);
            String selectedMember = (String) JOptionPane.showInputDialog(this,
                    "Select User:", "User Login",
                    JOptionPane.PLAIN_MESSAGE, null, memberNames, memberNames[0]);
            if (selectedMember != null) {
                String memberName = selectedMember.substring(0, selectedMember.lastIndexOf(" ("));
                showUserPanel(memberName);
            }
        });
        gbc.gridy = 2;
        selectionPanel.add(btnUserAccess, gbc);

        mainPanel.add(selectionPanel, "Selection");

        setupAdminPanel();
        setupUserPanel();
        setupBookTitlesPanel();

        cardLayout.show(mainPanel, "Selection");
    }

    private void setupAdminPanel() {
        adminPanel = new JPanel(new BorderLayout(10, 10));
        adminPanel.setBackground(new Color(250, 245, 200));

        // Buttons Panel
        JPanel adminButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAddBook = createStyledButton("➕ Add Book", new Color(60, 180, 75));
        btnModifyBook = createStyledButton("✏️ Modify Book", new Color(255, 165, 0));
        btnDeleteBook = createStyledButton("🗑️ Delete Book", new Color(220, 20, 60));
        btnViewAllBooks = createStyledButton("📖 View All Books", new Color(0, 122, 255));
        btnLogoutAdmin = createStyledButton("🚪 Logout", new Color(128, 128, 128));
        // Removed the randomize button as per your instruction

        // Action Listeners
        btnAddBook.addActionListener(e -> addBookDialog());
        btnModifyBook.addActionListener(e -> modifyBookDialog());
        btnDeleteBook.addActionListener(e -> deleteBookDialog());
        btnViewAllBooks.addActionListener(e -> {
            refreshAdminBookTable();
            showAdminBookTablePanel();
        });
        btnLogoutAdmin.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                cardLayout.show(mainPanel, "Selection");
            }
        });

        // Add buttons to panel
        adminButtonsPanel.add(btnAddBook);
        adminButtonsPanel.add(btnModifyBook);
        adminButtonsPanel.add(btnDeleteBook);
        adminButtonsPanel.add(btnViewAllBooks);
        adminButtonsPanel.add(btnLogoutAdmin);

        // Table setup
        String[] columns = {"Title", "Author", "Category", "Issued", "Issued To"};
        adminTableModel = new DefaultTableModel(columns, 0);
        booksTable = new JTable(adminTableModel);
        JScrollPane scrollPane = new JScrollPane(booksTable);

        // Assemble admin panel
        adminPanel.add(adminButtonsPanel, BorderLayout.NORTH);
        adminPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(adminPanel, "Admin");
    }

    private void setupUserPanel() {
        userPanel = new JPanel(new BorderLayout(10, 10));
        userPanel.setBackground(new Color(250, 250, 250));

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnViewBooksUser = createStyledButton("👀 View Books", new Color(0, 122, 255));
        btnSearchBook = createStyledButton("🔍 Search Book", new Color(60, 180, 75));
        btnIssueBook = createStyledButton("📚 Borrow Book", new Color(255, 165, 0));
        btnReturnBook = createStyledButton("↩️ Return Book", new Color(220, 20, 60));
        btnLogoutUser = createStyledButton("🚪 Logout", new Color(128, 128, 128));

        // Action Listeners
        btnViewBooksUser.addActionListener(e -> {
            refreshUserBookTable();
            showUserBookTablePanel();
        });
        btnSearchBook.addActionListener(e -> searchBookDialog());
        btnIssueBook.addActionListener(e -> issueBookDialog());
        btnReturnBook.addActionListener(e -> returnBookDialog());
        btnLogoutUser.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                cardLayout.show(mainPanel, "Selection");
            }
        });

        // Top button panel
        JPanel topBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topBtnPanel.add(btnViewBooksUser);
        topBtnPanel.add(btnSearchBook);
        topBtnPanel.add(btnIssueBook);
        topBtnPanel.add(btnReturnBook);
        topBtnPanel.add(btnLogoutUser);

        // Log area
        userLogArea = new JTextArea(15, 80);
        userLogArea.setEditable(false);
        JScrollPane logScroll = new JScrollPane(userLogArea);

        // Assemble user panel
        userPanel.add(topBtnPanel, BorderLayout.NORTH);
        userPanel.add(logScroll, BorderLayout.CENTER);

        mainPanel.add(userPanel, "User");
    }

    private void setupBookTitlesPanel() {
        bookTitlesPanel = new JPanel(new BorderLayout());
        bookTitlesPanel.setBackground(Color.WHITE);
        JLabel lblTitles = new JLabel("📚 Book Titles List");
        lblTitles.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblTitles.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea titlesArea = new JTextArea();
        titlesArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        titlesArea.setBackground(Color.WHITE);
        titlesArea.setEditable(false);
        titlesArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(titlesArea);
        this.bookTitleTextArea = titlesArea;

        // Add components
        bookTitlesPanel.add(lblTitles, BorderLayout.NORTH);
        bookTitlesPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bookTitlesPanel, "BookTitles");
    }

    private JButton createStyledButton(String text, Color c) {
        JButton btn = new JButton(text);
        btn.setBackground(c);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        Dimension size = new Dimension(180, 50);
        btn.setPreferredSize(size);
        btn.setMaximumSize(size);
        btn.setMinimumSize(size);
        btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(c.darker());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(c);
            }
        });
        return btn;
    }

    private void showUserPanel(String username) {
        cardLayout.show(mainPanel, "User");
        refreshUserBookTable();
        if (userLogArea != null) {
            userLogArea.setText(""); // clear logs
        }
    }

    private void showAdminPanel() {
        cardLayout.show(mainPanel, "Admin");
        refreshAdminBookTable();
    }

    private void showAdminBookTablePanel() {
        cardLayout.show(mainPanel, "Admin");
    }

    private void showUserBookTablePanel() {
        cardLayout.show(mainPanel, "User");
    }

    private void showBookTitlesPanel() {
        String titles = books.stream().map(b -> b.title).collect(Collectors.joining("\n"));
        bookTitleTextArea.setText(titles);
        cardLayout.show(mainPanel, "BookTitles");
    }

    public void refreshAdminBookTable() {
        adminTableModel.setRowCount(0);
        for (Book b : books) {
            String issuedToDisplay = "";
            if (b.issued) {
                Member member = members.stream()
                        .filter(m -> m.name.equals(b.issuedTo))
                        .findFirst()
                        .orElse(null);
                if (member != null) {
                    issuedToDisplay = member.name + " (" + member.rollNo + ")";
                } else {
                    issuedToDisplay = b.issuedTo;
                }
            }
            adminTableModel.addRow(new Object[]{
                    b.title, b.author, b.category, b.issued ? "✅" : "❌", issuedToDisplay
            });
        }
    }

    public void refreshUserBookTable() {
        if (userTableModel == null) {
            String[] columns = {"Title", "Author", "Category"};
            userTableModel = new DefaultTableModel(columns, 0);
            JTable userTable = new JTable(userTableModel);
            JScrollPane scrollPane = new JScrollPane(userTable);
            // Rebuild user panel
            userPanel.removeAll();

            // Buttons panel
            btnViewBooksUser = createStyledButton("👀 View Books", new Color(0, 122, 255));
            btnSearchBook = createStyledButton("🔍 Search Book", new Color(60, 180, 75));
            btnIssueBook = createStyledButton("📚 Borrow Book", new Color(255, 165, 0));
            btnReturnBook = createStyledButton("↩️ Return Book", new Color(220, 20, 60));
            btnLogoutUser = createStyledButton("🚪 Logout", new Color(128, 128, 128));

            // Action Listeners
            btnViewBooksUser.addActionListener(e -> {
                refreshUserBookTable();
                showUserBookTablePanel();
            });
            btnSearchBook.addActionListener(e -> searchBookDialog());
            btnIssueBook.addActionListener(e -> issueBookDialog());
            btnReturnBook.addActionListener(e -> returnBookDialog());
            btnLogoutUser.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    cardLayout.show(mainPanel, "Selection");
                }
            });

            // Clear previous buttons
            userPanel.removeAll();

            // Top button panel
            JPanel topBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            topBtnPanel.add(btnViewBooksUser);
            topBtnPanel.add(btnSearchBook);
            topBtnPanel.add(btnIssueBook);
            topBtnPanel.add(btnReturnBook);
            topBtnPanel.add(btnLogoutUser);

            // Layout and add
            userPanel.setLayout(new BorderLayout(10, 10));
            userPanel.add(topBtnPanel, BorderLayout.NORTH);
            userPanel.add(scrollPane, BorderLayout.CENTER);

            userPanel.revalidate();
            userPanel.repaint();
        }

        // Populate data
        userTableModel.setRowCount(0);
        for (Book b : books) {
            userTableModel.addRow(new Object[]{b.title, b.author, b.category});
        }

        // Update titles list
        String titles = books.stream().map(b -> b.title).collect(Collectors.joining("\n"));
        if (bookTitleTextArea != null) {
            bookTitleTextArea.setText(titles);
        }
    }

    // --- Dialogs and Actions ---
    private void addBookDialog() {
        JTextField tfTitle = new JTextField(20);
        JTextField tfAuthor = new JTextField(20);
        JTextField tfCategory = new JTextField(15);
        Object[] message = {
                "Title:", tfTitle,
                "Author:", tfAuthor,
                "Category:", tfCategory
        };
        int option = JOptionPane.showConfirmDialog(this, message, "➕ Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String title = tfTitle.getText().trim();
            String author = tfAuthor.getText().trim();
            String category = tfCategory.getText().trim();
            if (!title.isEmpty() && !author.isEmpty() && !category.isEmpty()) {
                books.add(new Book(title, author, category));
                refreshAdminBookTable();
                JOptionPane.showMessageDialog(this, "📚 Book added.");
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required.", "❗ Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modifyBookDialog() {
        String t = JOptionPane.showInputDialog(this, "Enter Book Title to Modify:");
        if (t != null && !t.trim().isEmpty()) {
            for (Book b : books) {
                if (b.title.equalsIgnoreCase(t.trim())) {
                    JTextField tfTitle = new JTextField(b.title);
                    JTextField tfAuthor = new JTextField(b.author);
                    JTextField tfCategory = new JTextField(b.category);
                    Object[] msg = {
                            "Title:", tfTitle,
                            "Author:", tfAuthor,
                            "Category:", tfCategory
                    };
                    int option = JOptionPane.showConfirmDialog(this, msg, "✏️ Modify Book", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        b.title = tfTitle.getText().trim();
                        b.author = tfAuthor.getText().trim();
                        b.category = tfCategory.getText().trim();
                        refreshAdminBookTable();
                        JOptionPane.showMessageDialog(this, "✅ Book modified successfully.");
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "📚 Book not found.", "❌ Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBookDialog() {
        String t = JOptionPane.showInputDialog(this, "Enter Book Title to Delete:");
        if (t != null && !t.trim().isEmpty()) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).title.equalsIgnoreCase(t.trim())) {
                    Book b = books.remove(i);
                    refreshAdminBookTable();
                    JOptionPane.showMessageDialog(this, "🗑️ Deleted: " + b);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "📚 Book not found.", "❌ Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchBookDialog() {
        String t = JOptionPane.showInputDialog(this, "Enter Book Title to Search:");
        if (t != null && !t.trim().isEmpty()) {
            boolean found = false;
            for (Book b : books) {
                if (b.title.equalsIgnoreCase(t.trim())) {
                    JOptionPane.showMessageDialog(this, "🔍 Found: " + b, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }
            if (!found)
                JOptionPane.showMessageDialog(this, "📚 Book not found.", "Search", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void issueBookDialog() {
        String t = JOptionPane.showInputDialog(this, "Enter Book Title to Borrow:");
        if (t != null && !t.trim().isEmpty()) {
            for (Book b : books) {
                if (b.title.equalsIgnoreCase(t.trim())) {
                    if (!b.issued) {
                        String[] memberOptions = members.stream()
                                .map(m -> m.name + " (" + m.rollNo + ")")
                                .toArray(String[]::new);
                        String selectedMember = (String) JOptionPane.showInputDialog(this, "Select Member:", "Member Selection",
                                JOptionPane.PLAIN_MESSAGE, null, memberOptions, memberOptions[0]);
                        if (selectedMember != null) {
                            String memberName = selectedMember.substring(0, selectedMember.lastIndexOf(" ("));
                            b.issued = true;
                            b.issuedTo = memberName;
                            refreshAdminBookTable();
                            JOptionPane.showMessageDialog(this, "✅ Book issued to " + b.issuedTo);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "❌ Book already issued.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "📚 Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBookDialog() {
        String t = JOptionPane.showInputDialog(this, "Enter Book Title to Return:");
        if (t != null && !t.trim().isEmpty()) {
            for (Book b : books) {
                if (b.title.equalsIgnoreCase(t.trim())) {
                    if (b.issued) {
                        String oldUser = b.issuedTo;
                        b.issued = false;
                        b.issuedTo = "";
                        refreshAdminBookTable();
                        JOptionPane.showMessageDialog(this, "↩️ Book returned from " + oldUser);
                    } else {
                        JOptionPane.showMessageDialog(this, "❌ Book was not issued.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "📚 Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to randomly assign issued books to members
    private void issueRandomBooks() {
        Random rand = new Random();
        List<Book> unissuedBooks = books.stream()
                .filter(b -> !b.issued)
                .collect(Collectors.toList());
        int numberToIssue = Math.min(10, unissuedBooks.size());
        for (int i = 0; i < numberToIssue; i++) {
            Book b = unissuedBooks.get(i);
            Member m = members.get(rand.nextInt(members.size()));
            b.issued = true;
            b.issuedTo = m.name;
        }
    }

    // Inner classes
    class Book {
        String title, author, category;
        boolean issued;
        String issuedTo;

        Book(String t, String a, String c) {
            this.title = t;
            this.author = a;
            this.category = c;
            this.issued = false;
            this.issuedTo = "";
        }

        public String toString() {
            return String.format("%s by %s [%s]%s", title, author, category, issued ? " (Issued to " + issuedTo + ")" : "");
        }
    }

    class Member {
        String name, email, rollNo;

        Member(String n, String e, String r) {
            this.name = n;
            this.email = e;
            this.rollNo = r;
        }
    }
}



