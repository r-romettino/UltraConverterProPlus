package outils;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class maths {
	// Méthode pour calculer une expression mathématique donnée sous forme de chaîne
	public static double evaluateMathExpression(String expression) {
        try {
            // Créer une nouvelle expression avec la bibliothèque exp4j
            Expression e = new ExpressionBuilder(expression)
                    .build();
            
            // Évaluer l'expression
            return e.evaluate();
        } catch (Exception ex) {
            // Gérer les erreurs d'évaluation
            System.out.println("Erreur d'évaluation de l'expression : " + ex.getMessage());
            return Double.NaN; // Retourne NaN si l'expression est invalide
        }
    }
}
