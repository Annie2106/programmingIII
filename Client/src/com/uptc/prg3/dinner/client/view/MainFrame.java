package com.uptc.prg3.dinner.client.view;

import com.sun.java.swing.plaf.motif.MotifProgressBarUI;
import com.sun.java.swing.plaf.windows.WindowsProgressBarUI;
import com.sun.java.swing.plaf.windows.WindowsScrollBarUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Main frame used for the whole UI of this program.
 *
 * @see JFrame
 */
public class MainFrame extends JFrame {
    // The main panel
    private JPanel mMainPanel;
    // The components of the main panel
    private JLabel mTitle;
    private JLabel mTimer;
    private JScrollPane mInfoAreaPane;
    private JTextArea mInfoArea;
    private JButton mEatButton;
    private JButton mStopEatingButton;
    private JButton mConnectButton;
    private JProgressBar mIndefiniteProgressBar;

    /**
     * Main constructor for the main frame, which handles the initialization of all its children.
     */
    public MainFrame() {
        this.initializeComponents();
        this.initializeMainPanel();
        this.initialize();
    }

    /**
     * Sets the message of the info area on the main panel.
     *
     * @param message The message to show in that area.
     */
    public void setMessage(String message) {
        this.mInfoArea.setText(message);
    }

    /**
     * Sets a certain listener for the eat button.
     *
     * @param listener The listener for the button.
     */
    public void setEatButtonListener(ActionListener listener) {
        this.mEatButton.addActionListener(listener);
    }

    /**
     * Sets a certain listener for the stop eating button.
     *
     * @param listener The listener for the button.
     */
    public void setStopEatingButtonListener(ActionListener listener) {
        this.mStopEatingButton.addActionListener(listener);
    }

    /**
     * Sets a certain listener for the connect button.
     *
     * @param listener The listener for the button.
     */
    public void setConnectButtonListener(ActionListener listener) {
        this.mConnectButton.addActionListener(listener);
    }

    /**
     * Sets the status of the indefinite bar, if it's visible or not.
     *
     * @param show Whether if it needs to be visible or not.
     */
    public void setIndefiniteProgressBar(boolean show) {
        this.mIndefiniteProgressBar.setVisible(show);
    }

    /**
     * Initialize the {@link MainFrame} as a whole, the main components come next.
     */
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.setTitle("Supper");
        this.setPreferredSize(new Dimension(700, 700));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(mMainPanel);
    }

    /**
     * Initialize all of the components inside the main panel.
     */
    private void initializeComponents() {
        this.initializeTitle();
        this.initializeTimer();
        this.initializeInfoAreaPane();
        this.initializeEatButton();
        this.initializeStopEatingButton();
        this.initializeConnectButton();
        this.initializeIndefiniteProgressBar();
    }

    /**
     * Initialize the main panel, which means, add all of the components.
     */
    private void initializeMainPanel() {
        this.mMainPanel = new JPanel();
        this.mMainPanel.setLayout(new BoxLayout(this.mMainPanel, BoxLayout.Y_AXIS));
        this.mMainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        this.mMainPanel.add(this.mTitle);
        this.mMainPanel.add(Box.createVerticalStrut(20));
        this.mMainPanel.add(this.mTimer);
        this.mMainPanel.add(Box.createVerticalStrut(20));
        this.mMainPanel.add(this.mInfoAreaPane);
        this.mMainPanel.add(Box.createVerticalStrut(20));
        this.mMainPanel.add(this.mEatButton);
        this.mMainPanel.add(Box.createVerticalStrut(10));
        this.mMainPanel.add(this.mStopEatingButton);
        this.mMainPanel.add(Box.createVerticalStrut(30));
        this.mMainPanel.add(this.mConnectButton);
        this.mMainPanel.add(this.mIndefiniteProgressBar);
        this.mMainPanel.revalidate();
    }

    // Here starts the initialization of all of the components

    /**
     * Initialize the title component.
     *
     * @see JLabel
     */
    private void initializeTitle() {
        this.mTitle = new JLabel("Supper game");
        this.mTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mTitle.setFont(Fonts.getFont(Fonts.LIVVIC, 24));
    }

    /**
     * Initialize the timer container, from a {@link JLabel}.
     */
    private void initializeTimer() {
        this.mTimer = new JLabel("08:06");
        this.mTimer.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mTimer.setFont(Fonts.getFont(Fonts.QUICKSAND, 18));
    }

    /**
     * Initialize the information area panel, this method starts the {@link JTextArea} for the
     * information, and the {@link JScrollPane} for the scrolling of the text area.
     *
     * @see JTextArea
     * @see JScrollPane
     */
    private void initializeInfoAreaPane() {
        this.mInfoAreaPane = new JScrollPane();
        this.mInfoArea = new JTextArea();
        this.mInfoAreaPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
        this.mInfoAreaPane.getVerticalScrollBar().setUI(new WindowsScrollBarUI());
        this.mInfoAreaPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.mInfoArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mInfoArea.setBorder(new EmptyBorder(30, 30, 30, 30));
        this.mInfoArea.setLineWrap(true);
        this.mInfoArea.setWrapStyleWord(true);
        this.mInfoArea.setFont(Fonts.getFont(Fonts.QUICKSAND, 16));
        this.mInfoArea.setEditable(false);
        this.mInfoAreaPane.setViewportView(this.mInfoArea);
    }

    /**
     * Initializes the eat button.
     *
     * @see JButton
     */
    private void initializeEatButton() {
        this.mEatButton = new JButton("Eat");
        this.mEatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mEatButton.setFont(Fonts.getFont(Fonts.LIVVIC, 15));
    }

    /**
     * Initializes the stop eating button.
     *
     * @see JButton
     */
    private void initializeStopEatingButton() {
        this.mStopEatingButton = new JButton("Stop eating");
        this.mStopEatingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mStopEatingButton.setFont(Fonts.getFont(Fonts.LIVVIC, 15));
    }

    /**
     * Initializes the connect button.
     *
     * @see JButton
     */
    private void initializeConnectButton() {
        this.mConnectButton = new JButton("Connect");
        this.mConnectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mConnectButton.setFont(Fonts.getFont(Fonts.LIVVIC, 15));
    }

    /**
     * Initializes the indefinite progress bar.
     *
     * @see JProgressBar
     */
    private void initializeIndefiniteProgressBar() {
        this.mIndefiniteProgressBar = new JProgressBar();
        this.mIndefiniteProgressBar.setIndeterminate(true);
        this.mIndefiniteProgressBar.setVisible(false);
    }
}
