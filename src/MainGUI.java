import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import outils.Convertisseur;
import outils.CsvFileHelper;
import outils.convertHistory;
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
    private static JPanel importPanel;
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
    
    // Déclarations des path
    private static String filePath = "";
    private static String folderPath = "";

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
        JButton button2 = new JButton("importer un fichier");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        initialPanel.add(buttonPanel);

        // Panneau de conversion (sera affichÃ© aprÃ¨s clic sur le bouton 1)
        conversionPanel = new JPanel();
        conversionPanel.setLayout(new FlowLayout());
        setupConversionPanel();
        
        // Panneau d'importation (sera affiché après clic sur le bouton 2)
        importPanel = new JPanel();
        importPanel.setLayout(new FlowLayout());
        setupImportationPanel();

        // Ajouter les panneaux au CardLayout
        mainPanel.add(initialPanel, "initial");
        mainPanel.add(conversionPanel, "conversion");
        mainPanel.add(importPanel,"import");
        frame.add(mainPanel);

        // Action pour le bouton 1
        button1.addActionListener(e -> cardLayout.show(mainPanel, "conversion"));
        
        // Action pour le bouton 2
        button2.addActionListener(e -> cardLayout.show(mainPanel, "import"));
        
        // Afficher la fenÃªtre
        frame.setVisible(true);
    }
    
    private static void setupImportationPanel() {
        importPanel.setLayout(new BorderLayout()); // Changer le layout pour BorderLayout

        // Barre supérieure avec le bouton Retour
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Barre supérieure
        JButton backButton = new JButton("←");
        topPanel.add(backButton);
        importPanel.add(topPanel, BorderLayout.NORTH); // Ajouter en haut du panneau

        // Action du bouton Retour
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "initial"));

        // Le panneau principal qui contiendra tout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Organiser verticalement

        // Centrer le panneau sur la fenêtre
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        importPanel.add(contentPanel, BorderLayout.CENTER);
        
        // --- Groupe 1 : Fichier de départ à convertir ---
        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS)); // Organiser verticalement

        JLabel fileLabel = new JLabel("Fichier de départ à convertir:");
        JTextField filePathField = new JTextField(30); // Champ de texte pour afficher le chemin du fichier
        JButton browseFileButton = new JButton("Parcourir fichier CSV...");
        
        filePanel.add(fileLabel);
        filePanel.add(filePathField);
        filePanel.add(browseFileButton);

        // Ajouter Groupe 1 au panneau principal
        contentPanel.add(filePanel);
        contentPanel.add(Box.createVerticalStrut(50)); // Espacement entre les groupes

        // --- Groupe 2 : Dossier où le fichier converti sera téléchargé ---
        JPanel folderPanel = new JPanel();
        folderPanel.setLayout(new BoxLayout(folderPanel, BoxLayout.Y_AXIS)); // Organiser verticalement

        JLabel folderLabel = new JLabel("Dossier où le fichier converti est téléchargé:");
        JTextField folderPathField = new JTextField(30); // Champ de texte pour afficher le chemin du dossier
        JButton browseFolderButton = new JButton("Parcourir dossier...");
        
        folderPanel.add(folderLabel);
        folderPanel.add(folderPathField);
        folderPanel.add(browseFolderButton);

        // Ajouter Groupe 2 au panneau principal
        contentPanel.add(folderPanel);
        contentPanel.add(Box.createVerticalStrut(50)); // Espacement entre les groupes

        // --- Groupe 3 : Bouton Convertir ---
        JPanel convertPanel = new JPanel();
        JButton convertButton = new JButton("Convertir");
        
        convertPanel.add(convertButton);

        // Ajouter Groupe 3 au panneau principal
        contentPanel.add(convertPanel);

        // Action du bouton Parcourir pour ouvrir le sélecteur de fichiers CSV
        browseFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Sélectionner un fichier CSV");

            // Créer un filtre pour n'afficher que les fichiers CSV
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers CSV", "csv");
            fileChooser.setFileFilter(filter);

            // Optionnel : spécifie un répertoire de départ, si nécessaire
            fileChooser.setCurrentDirectory(new File("C:\\Users\\melih"));

            int result = fileChooser.showOpenDialog(importPanel); // Affiche la fenêtre de sélection de fichier
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                
                // Vérifier si l'élément sélectionné est bien un fichier CSV
                if (selectedFile.isFile() && selectedFile.getName().endsWith(".csv")) {
                    filePath = selectedFile.getAbsolutePath(); // Récupère le chemin absolu du fichier
                    filePathField.setText(filePath); // Affiche le chemin dans le champ de texte
                } else {
                    // Si ce n'est pas un fichier CSV, afficher un message d'erreur
                    JOptionPane.showMessageDialog(importPanel, 
                            "Erreur : Vous devez sélectionner un fichier CSV.",
                            "Erreur de sélection", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action du bouton Parcourir pour ouvrir le sélecteur de répertoires (dossier)
        browseFolderButton.addActionListener(e -> {
            JFileChooser folderChooser = new JFileChooser();
            folderChooser.setDialogTitle("Sélectionner un dossier");
            
            // Définit le mode de sélection à "répertoire seulement"
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            // Optionnel : spécifie un répertoire de départ, si nécessaire
            folderChooser.setCurrentDirectory(new File("C:\\Users\\melih"));

            int result = folderChooser.showOpenDialog(importPanel); // Affiche la fenêtre de sélection de dossier
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = folderChooser.getSelectedFile();
                
                // Récupérer le chemin absolu du dossier
                folderPath = selectedFolder.getAbsolutePath(); 
                folderPathField.setText(folderPath); // Affiche le chemin dans le champ de texte
            }
        });
        
        // Action sur bouton conversion
        convertButton.addActionListener(e -> {
        	CsvFileHelper.IOCSV(filePath, folderPath + "/new_fichier.csv");
        });
    }



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

    private static boolean isValidNumber(String value) {
        return value.matches("[-+]?\\d*\\.?\\d+");
    }

    private static void showResult(float result, String fromUnit, String toUnit, float value) {
        JFrame resultFrame = new JFrame("Résultat de la Conversion");
        resultFrame.setSize(300, 150);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String resultText = String.format("Résultat : %.2f %s = %.2f %s", value, fromUnit, result, toUnit);
        JLabel resultLabel = new JLabel(resultText, SwingConstants.CENTER);
        resultFrame.add(resultLabel);

        resultFrame.setVisible(true);
    }

    private static void addHistory(float result, String fromUnit, String toUnit, float value, String selectedType) {
        lastConvert = new convertHistory(result, fromUnit, toUnit, value, selectedType);
        history.add(lastConvert);
        listModel.add(0, lastConvert.toString());
    }

    private static void loadFromJson() {
        loadHistoryFromJson();
        for (int i = history.size() - 1; i >= 0; i--) {
            listModel.addElement(history.get(i).toString());
        }
    }

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

    public static void saveHistoryToJson(List<convertHistory> history) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("history.json"), history);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
