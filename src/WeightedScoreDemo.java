/**
 * Demonstrates using {@code ArtificialNeuron} as a weighted scoring engine for
 * course grade calculation. Each weight represents the importance of a grade
 * category (homework, midterm, final), and the dot product with a student's
 * category scores produces the weighted final grade.
 *
 * <p>
 * This use case is qualitatively different from {@code PerceptronDemo}: there
 * is no training, the weights carry direct semantic meaning (percentages), and
 * the neuron is used as a static scoring engine rather than a learning model.
 */
public final class WeightedScoreDemo {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private WeightedScoreDemo() {
    }

    /**
     * Prints each student's weighted grade given a weight vector.
     *
     * @param weights
     *            the weight neuron
     * @param names
     *            student names
     * @param scores
     *            score matrix where scores[i] are scores for names[i]
     */
    private static void printGrades(ArtificialNeuron weights, String[] names,
            double[][] scores) {
        for (int i = 0; i < names.length; i++) {
            double grade = weights.dotProduct(scores[i]);
            System.out.printf("  %s: %.2f%n", names[i], grade);
        }
    }

    /**
     * Main method: builds a weighted-grade scoring neuron and applies it to
     * three students under two different weighting schemes.
     *
     * @param args
     *            command-line arguments (ignored)
     */
    public static void main(String[] args) {
        // Course weights: 30% homework, 30% midterm, 40% final
        ArtificialNeuron weights = new ArtificialNeuron1L();
        weights.addWeight(0.30);
        weights.addWeight(0.30);
        weights.addWeight(0.40);

        System.out.println("Course weights: " + weights);
        System.out.printf("Weights sum to: %.2f%n%n", weights.sumWeights());

        String[] names = { "Alice", "Bob", "Carol" };
        // Scores arranged as { homework, midterm, final }
        double[][] scores = { { 95.0, 88.0, 92.0 }, { 72.0, 80.0, 75.0 },
                { 100.0, 95.0, 98.0 } };

        System.out.println("Computed grades (original weighting):");
        printGrades(weights, names, scores);

        // Re-weight: bump the final exam, shrink the others
        System.out.println();
        System.out.println("Re-weighting (heavier emphasis on final)...");
        weights.setWeight(0, 0.25);
        weights.setWeight(1, 0.25);
        weights.setWeight(2, 0.50);
        System.out.println("New weights: " + weights);

        System.out.println("Computed grades (new weighting):");
        printGrades(weights, names, scores);

        // Demonstrate scaleWeights: convert percentages to a 0-100 scale
        System.out.println();
        System.out.println("Scaling weights to a 0-100 scale...");
        weights.scaleWeights(100.0);
        System.out.println("Scaled weights: " + weights);
    }

}