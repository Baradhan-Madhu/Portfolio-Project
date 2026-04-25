/**
 * Demonstrates using {@code ArtificialNeuron} as a single perceptron trained to
 * learn the binary AND function. Given two binary inputs, the perceptron learns
 * to predict 1 when both inputs are 1 and 0 otherwise.
 *
 * <p>
 * This use case exercises {@code dotProduct} (for forward predictions),
 * {@code getWeight}/{@code setWeight} (for the learning rule), and
 * {@code weightCount} (to iterate over weights during updates).
 */
public final class PerceptronDemo {

    /**
     * Learning rate for weight updates.
     */
    private static final double LEARNING_RATE = 0.1;

    /**
     * Number of training epochs.
     */
    private static final int EPOCHS = 20;

    /**
     * Threshold for the step activation function.
     */
    private static final double THRESHOLD = 0.0;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private PerceptronDemo() {
    }

    /**
     * Step activation function.
     *
     * @param x
     *            the value to threshold
     * @return 1 if {@code x > THRESHOLD}, 0 otherwise
     */
    private static int step(double x) {
        int result = 0;
        if (x > THRESHOLD) {
            result = 1;
        }
        return result;
    }

    /**
     * Predicts the perceptron's binary output for a given input vector.
     *
     * @param neuron
     *            the perceptron
     * @param inputs
     *            the input vector
     * @return 0 or 1, the perceptron's prediction
     */
    private static int predict(ArtificialNeuron neuron, double[] inputs) {
        return step(neuron.dotProduct(inputs));
    }

    /**
     * Updates the perceptron's weights using the perceptron learning rule:
     * {@code w_i := w_i + learningRate * (target - prediction) * input_i}.
     *
     * @param neuron
     *            the perceptron to update
     * @param inputs
     *            the input vector for this training example
     * @param target
     *            the desired output (0 or 1)
     */
    private static void train(ArtificialNeuron neuron, double[] inputs,
            int target) {
        int prediction = predict(neuron, inputs);
        int error = target - prediction;
        for (int i = 0; i < neuron.weightCount(); i++) {
            double newWeight = neuron.getWeight(i)
                    + LEARNING_RATE * error * inputs[i];
            neuron.setWeight(i, newWeight);
        }
    }

    /**
     * Main method: trains a perceptron on the AND gate and prints results.
     *
     * @param args
     *            command-line arguments (ignored)
     */
    public static void main(String[] args) {
        ArtificialNeuron neuron = new ArtificialNeuron1L();
        // Two input weights plus a bias weight (the third input is always 1)
        neuron.addWeight(0.0);
        neuron.addWeight(0.0);
        neuron.addWeight(0.0);

        // AND gate training data: { input1, input2, biasInput }
        double[][] trainingInputs = { { 0, 0, 1 }, { 0, 1, 1 }, { 1, 0, 1 },
                { 1, 1, 1 } };
        int[] trainingTargets = { 0, 0, 0, 1 };

        System.out.println("Training perceptron on AND gate...");
        System.out.println("Initial weights: " + neuron);

        for (int epoch = 0; epoch < EPOCHS; epoch++) {
            for (int i = 0; i < trainingInputs.length; i++) {
                train(neuron, trainingInputs[i], trainingTargets[i]);
            }
        }

        System.out.println("Trained weights: " + neuron);
        System.out.println();
        System.out.println("Predictions:");
        for (int i = 0; i < trainingInputs.length; i++) {
            int pred = predict(neuron, trainingInputs[i]);
            System.out.printf("  AND(%d, %d) = %d (expected %d)%n",
                    (int) trainingInputs[i][0], (int) trainingInputs[i][1],
                    pred, trainingTargets[i]);
        }
    }

}