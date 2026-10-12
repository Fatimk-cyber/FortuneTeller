
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {

    private JTextArea fortuneArea;
    private int previousFortune = -1;
    private final Random random = new Random();

    private final String[] fortunes = {
            "You will find money in a pocket you forgot about.",
            "Your next snack will be the best one yet.",
            "A nap will solve more problems than you expect.",
            "Someone will laugh at your joke today.",
            "Your Wi-Fi will work perfectly when you need it most.",
            "You will discover a new favorite song soon.",
            "A surprise delivery may brighten your day.",
            "Your future contains pizza and happiness.",
            "You will win an argument in the shower.",
            "Your phone battery will survive the day.",
            "An unexpected compliment is heading your way.",
            "You will soon remember why you entered a room.",
            "Your plants may forgive your watering schedule.",
            "A great idea will arrive at the worst possible time."
    };

    public FortuneTellerFrame() {
        setTitle("Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Separate fonts for the title, fortunes, and buttons
        Font titleFont = new Font("Serif", Font.BOLD, 40);
        Font fortuneFont = new Font("Monospaced", Font.PLAIN, 16);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 16);

        // TOP PANEL: title and image
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(240, 230, 250));

        ImageIcon originalIcon =
                new ImageIcon("src/FortuneTeller.png");

        Image scaledImage = originalIcon.getImage()
                .getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        ImageIcon fortuneIcon = new ImageIcon(scaledImage);

        JLabel titleLabel =
                new JLabel("Fortune Teller", fortuneIcon,
                        JLabel.CENTER);

        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);

        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // MIDDLE PANEL: fortune display
        fortuneArea = new JTextArea(10, 35);
        fortuneArea.setEditable(false);
        fortuneArea.setLineWrap(true);
        fortuneArea.setWrapStyleWord(true);
        fortuneArea.setFont(fortuneFont);
        fortuneArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(fortuneArea);

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 10, 15));
        middlePanel.add(scrollPane, BorderLayout.CENTER);

        add(middlePanel, BorderLayout.CENTER);

        // BOTTOM PANEL: buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(240, 230, 250));

        JButton readButton = new JButton("Read My Fortune!");
        JButton quitButton = new JButton("Quit");

        readButton.setFont(buttonFont);
        quitButton.setFont(buttonFont);

        bottomPanel.add(readButton);
        bottomPanel.add(quitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Lambda expression for the Read My Fortune button
        readButton.addActionListener(e -> readFortune());

        // Lambda expression for the Quit button
        quitButton.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to quit?",
                    "Quit Fortune Teller",
                    JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Set the window to 3/4 of the screen width and center it
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int frameWidth = screenSize.width * 3 / 4;
        int frameHeight = screenSize.height * 3 / 4;

        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
    }

    private void readFortune() {
        int newFortune;

        do {
            newFortune = random.nextInt(fortunes.length);
        } while (newFortune == previousFortune);

        previousFortune = newFortune;

        fortuneArea.append(fortunes[newFortune] + "\n\n");
        fortuneArea.setCaretPosition(
                fortuneArea.getDocument().getLength());
    }
}
