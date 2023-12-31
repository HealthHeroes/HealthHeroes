package view;

import app.ChatUseCaseFactory;
import entity.Patient;
import interface_adapter.ViewModel;
import interface_adapter.chat.ChatViewModel;
import interface_adapter.patient.PatientController;
import interface_adapter.patientList.PatientListController;
import use_case.patientList.PatientListOutputData;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.*;

public class PatientListComponentBuilder {
    private PatientController controller;

    private static final Dimension PATIENT_CELL_SIZE = new Dimension(750, 30);
    private static final Dimension NAME_LABEL_SIZE = new Dimension(150, 30);
    private static final Dimension VIEW_BUTTON_SIZE = new Dimension(180, 30);
    private static final Color PATIENT_BACKGROUND = new Color(33, 38, 48);
    private static final Font PATIENT_FONT = new Font("Lato", Font.PLAIN, 18);
    private static final Font HEADER_FONT = new Font("Lato", Font.BOLD, 13);

    public static JPanel buildEmptyHeader(){
        JPanel panel = new JPanel();
        JLabel nameLabel = new JLabel("No Entries Found");
        nameLabel.setForeground(new Color(255, 255, 255));
        nameLabel.setFont(new Font("Lato", Font.BOLD, 20));
        panel.setBackground(new Color(224, 92, 112));
        panel.add(nameLabel);
        // set size
        panel.setMaximumSize(PATIENT_CELL_SIZE);
        panel.setMinimumSize(PATIENT_CELL_SIZE);
        return panel;
    }
    public static JPanel buildHeader(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel nameLabel = new JLabel("Name");
        panel.setBackground(new Color(212, 213, 214));
        // name label
        nameLabel.setBackground(ViewModel.BACKGROUND_COLOR);
        nameLabel.setForeground(new Color(84, 164, 255));
        nameLabel.setFont(HEADER_FONT);
        nameLabel.setMinimumSize(NAME_LABEL_SIZE);
        nameLabel.setMaximumSize(NAME_LABEL_SIZE);
        nameLabel.setSize(NAME_LABEL_SIZE);
        // id label
        JLabel idLabel = new JLabel("Appointment Date");
        idLabel.setBackground(ViewModel.BACKGROUND_COLOR);
        idLabel.setForeground(new Color(84, 164, 255));
        idLabel.setFont(HEADER_FONT);
        idLabel.setMinimumSize(NAME_LABEL_SIZE);
        idLabel.setMaximumSize(NAME_LABEL_SIZE);
        // add to panel
        panel.add(Box.createHorizontalStrut(5));
        panel.add(idLabel);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(nameLabel);
        panel.add(Box.createHorizontalStrut(5));
        // set size
        panel.setMaximumSize(PATIENT_CELL_SIZE);
        panel.setMinimumSize(PATIENT_CELL_SIZE);
        return panel;
    }
    public static JPanel build(PatientListOutputData patient, PatientController controller) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(ViewModel.HEADER_COLOR);

        // Name label
        JLabel nameLabel = new JLabel(patient.getFullName());
        nameLabel.setBackground(ViewModel.BACKGROUND_COLOR);
        nameLabel.setForeground(ViewModel.TEXT_COLOR);
        nameLabel.setFont(PATIENT_FONT);
        nameLabel.setMinimumSize(NAME_LABEL_SIZE);
        nameLabel.setMaximumSize(NAME_LABEL_SIZE);

        // ID label
        JLabel idLabel = new JLabel(patient.getAppointmentDate());
        idLabel.setBackground(ViewModel.BACKGROUND_COLOR);
        idLabel.setForeground(ViewModel.TEXT_HIGHLIGHT_COLOR);
        idLabel.setFont(PATIENT_FONT);
        idLabel.setMinimumSize(NAME_LABEL_SIZE);
        idLabel.setMaximumSize(NAME_LABEL_SIZE);


        // AI chat button
        JButton aiChatButton = new JButton("\uD83E\uDD16 AI CHAT");
        configureButton(aiChatButton, ViewModel.HEADING_FONT_BOLD, new Color(126, 149, 238));

        // View patient button
        JButton viewPatientButton = new JButton("🔎 View Patient");
        configureButton(viewPatientButton, ViewModel.HEADING_FONT_BOLD, new Color(18, 82, 161));

        // Add components to panel

        viewPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.execute(patient.getId());
            }
        });
        aiChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ai chat was created
                ChatViewModel chatViewModel = new ChatViewModel();
                ChatView chatView = ChatUseCaseFactory.create(chatViewModel, controller.getPatientById(patient.getId()));
            }
        });
        // add to panel

        panel.add(Box.createHorizontalStrut(5));
        panel.add(idLabel);
        panel.add(Box.createHorizontalStrut(5));
        panel.add(nameLabel);
        panel.add(Box.createHorizontalStrut(30));
        panel.add(aiChatButton);
        panel.add(viewPatientButton);

        // Set size of panel
        panel.setMaximumSize(PATIENT_CELL_SIZE);
        panel.setMinimumSize(PATIENT_CELL_SIZE);

        return panel;
    }

    private static void configureButton(JButton button, Font font, Color bgColor) {
        button.setOpaque(true);
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setMinimumSize(VIEW_BUTTON_SIZE);
        button.setMaximumSize(VIEW_BUTTON_SIZE);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
}