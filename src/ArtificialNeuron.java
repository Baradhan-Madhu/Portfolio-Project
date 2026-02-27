import java.util.ArrayList;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * ArtificialNeuron represented as a dynamic list of weights.
 *
 * @author Baradhan Madhavan
 */
public class ArtificialNeuron {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of this neuron.
     */
    private ArrayList<Double> weights;

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public ArtificialNeuron() {
        this.weights = new ArrayList<>();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    public final void addWeight(double w) {
        this.weights.add(w);
    }

    public final void setWeight(int index, double w) {
        this.weights.set(index, w);
    }

    public final double getWeight(int index) {
        return this.weights.get(index);
    }

    /*
     * Secondary methods ------------------------------------------------------
     */

    public final int weightCount() {
        return this.weights.size();
    }

    public final void clearWeights() {
        this.weights.clear();
    }

    public final void scaleWeights(double factor) {
        for (int i = 0; i < this.weights.size(); i++) {
            this.weights.set(i, this.weights.get(i) * factor);
        }
    }

    /*
     * Main method ------------------------------------------------------------
     */

    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        ArtificialNeuron neuron = new ArtificialNeuron();

        // Add weights
        neuron.addWeight(0.5);
        neuron.addWeight(-1.0);
        neuron.addWeight(2.5);

        out.println("Initial Weights:");
        for (int i = 0; i < neuron.weightCount(); i++) {
            out.println("Weight " + i + ": " + neuron.getWeight(i));
        }

         // Example "client usage": scaling weights to simulate learning
        neuron.scaleWeights(2.0);

        out.println("\nAfter Scaling by 2:");
        for (int i = 0; i < neuron.weightCount(); i++) {
            out.println("Weight " + i + ": " + neuron.getWeight(i));
        }

        // Update a weight to simulate retraining
        neuron.setWeight(1, 10.0);

        out.println("\nAfter Updating Index 1:");
        for (int i = 0; i < neuron.weightCount(); i++) {
            out.println("Weight " + i + ": " + neuron.getWeight(i));
        }

        // Clear weights to simulate reset
        neuron.clearWeights();

        in.close();
        out.close();
    }
}
