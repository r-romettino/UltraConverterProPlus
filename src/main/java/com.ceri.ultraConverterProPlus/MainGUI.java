package com.ceri.ultraConverterProPlus;

import javax.swing.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ceri.ultraConverterProPlus.outils.Convertisseur;
import com.ceri.ultraConverterProPlus.outils.convertHistory;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainGUI {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel initialPanel;
    private static JPanel conversionPanel;
    private static CardLayout cardLayout;

    // DÃ©clarations des Ã©lÃ©ments graphiques pour l'interface de conversion
    private static JComboBox<String> typeComboBox;
    private static JComboBox<String> fromUnitComboBox;
    private static JComboBox<String> toUnitComboBox;
    private static JTextField valueTextField;
    private static JButton convertButton;
    private static JScrollPane scrollPane = new JScrollPane();
    private static DefaultListModel<String> listModel = new DefaultListModel<>();
    private static JList<String> list;

    // UnitÃ©s pour chaque type
    private static String[] uniteTemps = {"Secondes", "Minutes", "Heures", "Jours", "Semaines"};
    private static String[] uniteTemperatures = {"Celsius", "Delisle", "Fahrenheit", "Kelvin", "Newton", "Rankine", "Reaumur"};
    private static String[] uniteDistances = {"Miles", "Metre", "Pouce", "Mile Nautique", "Yard", "Kilometre", "Centimetre", "Millimetre", "Micrometre", "Nanometre", "Pied"};
    private static String[] listeUnite;
    private static convertHistory lastConvert;
    private static List<convertHistory> history = new ArrayList<>();

    /**
     * Entrypoint
     * @param args args
     */
    public static void main(String[] args) {
        frame = new JFrame("Ultra Converter Pro Plus");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Panneau initial avec deux boutons
        initialPanel = new JPanel();
        initialPanel.setLayout(new GridBagLayout());
        JButton button1 = new JButton("Conversion simple");
        JButton button2 = new JButton("Bouton 2");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        initialPanel.add(buttonPanel);

        // Panneau de conversion (sera affichÃ© aprÃ¨s clic sur le bouton 1)
        conversionPanel = new JPanel();
        conversionPanel.setLayout(new FlowLayout());
        setupConversionPanel();

        // Ajouter les panneaux au CardLayout
        mainPanel.add(initialPanel, "initial");
        mainPanel.add(conversionPanel, "conversion");
        frame.add(mainPanel);

        // Action pour le bouton 1
        button1.addActionListener(e -> cardLayout.show(mainPanel, "conversion"));

        // Afficher la fenÃªtre
        frame.setVisible(true);
    }

    /**
     * Créé la fenêtre principale
     */
    private static void setupConversionPanel() {
        conversionPanel.setLayout(new BorderLayout()); // Changer le layout pour BorderLayout

        // Barre supÃ©rieure avec le bouton Retour
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Barre supÃ©rieure
        JButton backButton = new JButton("←");
        topPanel.add(backButton);
        conversionPanel.add(topPanel, BorderLayout.NORTH); // Ajouter en haut du panneau

        // Action du bouton Retour
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "initial"));

        // Le reste du panneau (contenu principal de la conversion)
        JPanel contentPanel = new JPanel(new FlowLayout());
        conversionPanel.add(contentPanel, BorderLayout.CENTER);

        String[] types = {"distances", "temps", "temperatures"};
        typeComboBox = new JComboBox<>(types);
        fromUnitComboBox = new JComboBox<>();
        toUnitComboBox = new JComboBox<>();
        valueTextField = new JTextField(10);
        convertButton = new JButton("Convertir");

        contentPanel.add(typeComboBox);
        contentPanel.add(fromUnitComboBox);
        contentPanel.add(toUnitComboBox);
        contentPanel.add(valueTextField);
        contentPanel.add(convertButton);

        // Historique
        scrollPane = new JScrollPane();
        list = new JList<>(listModel);
        scrollPane.setViewportView(list);
        contentPanel.add(scrollPane);

        updateUnitCombos();
        typeComboBox.addActionListener(e -> updateUnitCombos());

        loadFromJson();

        convertButton.addActionListener(e -> {
            convert();
            saveHistoryToJson(history);
        });
    }

    /**
     * Rafraichit les dropdown d'unités lors du changement de type d'unitée (temps, distance...)
     */
    private static void updateUnitCombos() {
        String selectedType = (String) typeComboBox.getSelectedItem();
        fromUnitComboBox.removeAllItems();
        toUnitComboBox.removeAllItems();

        if ("distances".equals(selectedType)) {
            for (String unit : uniteDistances) {
                fromUnitComboBox.addItem(unit);
                toUnitComboBox.addItem(unit);
            }
            listeUnite = uniteDistances;
        } else if ("temps".equals(selectedType)) {
            for (String unit : uniteTemps) {
                fromUnitComboBox.addItem(unit);
                toUnitComboBox.addItem(unit);
            }
            listeUnite = uniteTemps;
        } else if ("temperatures".equals(selectedType)) {
            for (String unit : uniteTemperatures) {
                fromUnitComboBox.addItem(unit);
                toUnitComboBox.addItem(unit);
            }
            listeUnite = uniteTemperatures;
        }
    }

    /**
     * Convertit et affiche le résultat
     */
    private static void convert() {
        String fromUnit = (String) fromUnitComboBox.getSelectedItem();
        String toUnit = (String) toUnitComboBox.getSelectedItem();
        String valueText = valueTextField.getText();
        String selectedType = (String) typeComboBox.getSelectedItem();

        if (!isValidNumber(valueText)) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer une valeur numérique valide.");
        } else {
            float value = Float.parseFloat(valueText);
            float result = fromUnit.equals(toUnit) ? value : Convertisseur.convertString(
                    selectedType + "." + fromUnit,
                    selectedType + "." + toUnit,
                    value,
                    listeUnite);

            showResult(result, fromUnit, toUnit, value);
            addHistory(result, fromUnit, toUnit, value, selectedType);
        }
    }

    /**
     * Valide si le nombre entré est valide
     * @param value Valeur à vérifier
     * @return True si la valeur est numérique
     */
    private static boolean isValidNumber(String value) {
        return value.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * Affiche la fenêtre de résultat de conversion
     * @param result Résultat de la conversion
     * @param fromUnit Unité de départ
     * @param toUnit Unité de fin
     * @param value Valeur de départ
     */
    private static void showResult(float result, String fromUnit, String toUnit, float value) {
        JFrame resultFrame = new JFrame("Résultat de la Conversion");
        resultFrame.setSize(300, 150);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String resultText = String.format("Résultat : %.2f %s = %.2f %s", value, fromUnit, result, toUnit);
        JLabel resultLabel = new JLabel(resultText, SwingConstants.CENTER);
        resultFrame.add(resultLabel);

        resultFrame.setVisible(true);
    }

    /**
     * Ajoute un résultat à l'historique
     * @param result Résultat de la conversions
     * @param fromUnit Unité de départ
     * @param toUnit Unité de fin
     * @param value Valeur de départ
     * @param selectedType Type d'unité (distance, temps...)
     */
    private static void addHistory(float result, String fromUnit, String toUnit, float value, String selectedType) {
        lastConvert = new convertHistory(result, fromUnit, toUnit, value, selectedType);
        history.add(lastConvert);
        listModel.add(0, lastConvert.toString());
    }

    /**
     * Charge l'historique à partir du JSON
     */
    private static void loadFromJson() {
        loadHistoryFromJson();
        for (int i = history.size() - 1; i >= 0; i--) {
            listModel.addElement(history.get(i).toString());
        }
    }

    /**
     * Charge l'historique à partir du JSON
     */
    public static void loadHistoryFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("history.json");

        try {
            if (file.exists() && !file.isDirectory()) {
                List<convertHistory> loadedHistory = Arrays.asList(objectMapper.readValue(file, convertHistory[].class));
                history.addAll(loadedHistory);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enregistre l'historique dans un JSON
     * @param history
     */
    public static void saveHistoryToJson(List<convertHistory> history) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("history.json"), history);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
