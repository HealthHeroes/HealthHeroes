package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DrugEntry extends DrugEntryView {
    private JTextField nameField;
    private JTextField dosageField;
    private JTextField startField;
    private JTextField endField;
    private JPanel panel;

    public DrugEntry() {
        panel = new JPanel(new GridLayout(0, 4));
        nameField = new JTextField("Name");
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals("Name")) {
                    nameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText("Name");
                }
            }
        });

        dosageField = new JTextField("Dosage");
        dosageField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dosageField.getText().equals("Dosage (mg)")) {
                    dosageField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dosageField.getText().isEmpty()) {
                    dosageField.setText("Dosage (mg)");
                }
            }
        });


        startField = new JTextField("Start (YYYY-MM-DD)");
        startField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (startField.getText().equals("Start (YYYY-MM-DD)")) {
                    startField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (startField.getText().isEmpty()) {
                    startField.setText("Start (YYYY-MM-DD)");
                }
            }
        });

        endField = new JTextField("End (YYYY-MM-DD)");
        endField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (endField.getText().equals("End (YYYY-MM-DD)")) {
                    endField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (endField.getText().isEmpty()) {
                    endField.setText("End (YYYY-MM-DD)");
                }
            }
        });

        panel.add(nameField);
        panel.add(dosageField);
        panel.add(startField);
        panel.add(endField);
    }

    public JPanel getPanel() {
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JTextField) {
                JTextField field = (JTextField) comp;
                field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
            }
        }
        return panel;
    }

    public String[] getEntryData() {
        String[] data = {
          nameField.getText(), dosageField.getText(), startField.getText(), endField.getText()
        };
        return data;
    }

}
