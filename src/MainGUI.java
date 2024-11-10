import javax.swing.*;

import outils.Convertisseur;

import java.awt.*;
import java.awt.event.*;

public class MainGUI {

    // Déclarations des éléments graphiques
    private static JComboBox<String> typeComboBox;
    private static JComboBox<String> fromUnitComboBox;
    private static JComboBox<String> toUnitComboBox;
    private static JTextField valueTextField;
    private static JButton convertButton;

    // Unités pour chaque type
    private static String[] uniteTemps = {"Secondes", "Minutes", "Heures", "Jours", "Semaines"};
    private static String[] uniteTemperatures = {"Celsius", "Delisle", "Fahrenheit", "Kelvin", "Newton", "Rankine", "Reaumur"};
    private static String[] uniteDistances = {"Miles", "Metre", "Pouce", "Mile Nautique", "Yard", "Kilometre", "Centimetre", "Millimetre", "Micrometre", "Nanometre", "Pied"};
    private static String[] listeUnite;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Conversion d'Unités");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Liste déroulante pour choisir le type d'unité (distance, temps, température)
        String[] types = {"distances", "temps", "temperatures"};
        typeComboBox = new JComboBox<>(types);
        typeComboBox.setSelectedIndex(0); // Valeur par défaut
        frame.add(typeComboBox);

        // Liste déroulante pour l'unité de départ
        fromUnitComboBox = new JComboBox<>();
        frame.add(fromUnitComboBox);

        // Liste déroulante pour l'unité d'arrivée
        toUnitComboBox = new JComboBox<>();
        frame.add(toUnitComboBox);

        // Champ de texte pour la valeur à convertir
        valueTextField = new JTextField(10);
        frame.add(valueTextField);

        // Bouton de conversion
        convertButton = new JButton("Convertir");
        frame.add(convertButton);

        // Action quand on change le type d'unité
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnitCombos();
            }
        });

        // Initialisation des unités
        updateUnitCombos();
        
        // Action quand on clique sur le bouton de conversion
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    // Met à jour les listes déroulantes des unités selon le type choisi
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

    // Affiche une nouvelle fenêtre avec le résultat de la conversion
    private static void showResult(float result, String fromUnit, String toUnit, float value) {
        JFrame resultFrame = new JFrame("Résultat de la Conversion");
        resultFrame.setSize(300, 150);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String resultText = String.format("Résultat : %.2f %s = %.2f %s", value, fromUnit, result, toUnit);
        JLabel resultLabel = new JLabel(resultText, SwingConstants.CENTER);
        resultFrame.add(resultLabel);

        resultFrame.setVisible(true);
    }
    
    // Effectue la conversion en fonction des unités choisies
    private static void convert() {
    	String fromUnit = (String) fromUnitComboBox.getSelectedItem();
        String toUnit = (String) toUnitComboBox.getSelectedItem();
        String valueText = valueTextField.getText();
        String selectedType = (String) typeComboBox.getSelectedItem();
        
        // Vérifie si la valeur saisie est un float valide
        if (!isValidNumber(valueText)) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer une valeur numérique valide.");
        } else {
        	// Convertit en float maintenant que c'est validé
            float value = Float.parseFloat(valueText);
            float result;
            
            if (fromUnit.equals(toUnit)) {
            	result = value;
            } else {
            	result = Convertisseur.convertString(selectedType + "." + fromUnit, selectedType + "." + toUnit, value, listeUnite);
            }

            showResult(result, fromUnit, toUnit, value);
        }

    }
    
    private static boolean isValidNumber(String value) {
        return value.matches("[-+]?\\d*\\.?\\d+");
    }



}
