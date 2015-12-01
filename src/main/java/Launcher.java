import com.demosoft.investiogation.neuronlan.entity.Input;
import com.demosoft.investiogation.neuronlan.KohonenNetwork;
import com.demosoft.investiogation.neuronlan.entity.Neuron;
import com.demosoft.investiogation.neuronlan.entity.PlayerStateRule;
import com.demosoft.investiogation.neuronlan.rule.StateProvider;
import com.demosoft.investiogation.neuronlan.rule.csv.CsvStateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 30.11.2015.
 */
public class Launcher {

    public static void main(String[] args) {
        Input[] inputs = new Input[4];
        Neuron[] neurons = new Neuron[5];
        initNeurons(neurons);
        initInputs(inputs, neurons);

        KohonenNetwork network = new KohonenNetwork(inputs, neurons);
        StateProvider stateProvider = new CsvStateProvider("startStatus.csv");
        stateProvider.getRules();
        network.bulkStudy(stateProvider.getRules());

        List<PlayerStateRule> playerTestStates = new ArrayList<>();
        playerTestStates.add(new PlayerStateRule(100, false, 1, 0.0));
        playerTestStates.add(new PlayerStateRule(90, true, 3, 0.0));
        playerTestStates.add(new PlayerStateRule(30, false, 8, 0.0));
        playerTestStates.add(new PlayerStateRule(100, true, 8, 0.0));
        playerTestStates.add(new PlayerStateRule(100, false, 0, 0.0));

        playerTestStates.add(new PlayerStateRule(40, false, 1, 1.0));
        playerTestStates.add(new PlayerStateRule(50, false, 3, 1.0));

        playerTestStates.add(new PlayerStateRule(50, false, 0, 1.0));
        processResults(playerTestStates, network);

    }

    public static void processResults(List<PlayerStateRule> playerTestStates, KohonenNetwork network) {
        for (PlayerStateRule playerState : playerTestStates) {

            int index = network.newHandle(playerState);

            switch (network.neurons[index].action) {
                case 0:
                    System.out.println("ATTACK");
                    break;
                case 1:
                    System.out.println("ESCAPE");
                    break;
                case 2:
                    System.out.println("HIDE");
                    break;
                case 3:
                    System.out.println("NONE");
                    break;
                case 4:
                    System.out.println("DEFENCE");
                    break;
                default:
                    System.out.println("unknown " + network.neurons[index].action);
            }
        }
    }

    public static void createLinks(Input inputs, Neuron[] neurons) {
        for (Neuron neuron : neurons) {
            neuron.createLink(inputs);
        }
    }

    public static void initNeurons(Neuron[] neurons) {
        for (int i = 0; i < neurons.length; i++) {
            Neuron neuron = new Neuron();
            neuron.setPower(0);
            neuron.action = i;
            neurons[i] = neuron;
        }
    }

    public static void initInputs(Input[] inputs, Neuron[] neurons) {
        for (int i = 0; i < inputs.length; i++) {
            Input input = new Input();
            createLinks(input, neurons);
            inputs[i] = input;
        }
    }
}
