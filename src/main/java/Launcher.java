import com.demosoft.investiogation.neuronlan.Input;
import com.demosoft.investiogation.neuronlan.KohonenNetwork;
import com.demosoft.investiogation.neuronlan.Neuron;
import com.demosoft.investiogation.neuronlan.PlayerState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Andrii_Korkoshko on 30.11.2015.
 */
public class Launcher {


    public static final int ATTACK = 0;
    public static final int ESCAPE = 1;
    public static final int HIDE = 2;
    public static final int NONE = 3;

    public static void main(String[] args) {
        Input[] inputs = new Input[3];
        Neuron[] neurons = new Neuron[4];
        Random random = new Random();
        for (int i = 0; i < neurons.length; i++) {
            Neuron neuron = new Neuron();
            neuron.setPower(0);
            neuron.action = i;
            neurons[i] = neuron;
        }
        for (int i = 0; i < inputs.length; i++) {
            Input input = new Input();
            createLinks(input, neurons);
            inputs[i] = input;
        }
        linkInputWithNeurin(inputs, neurons);

        KohonenNetwork network = new KohonenNetwork(inputs, neurons);
        List<PlayerState> playerAtackStates = new ArrayList<>();
        playerAtackStates.add(new PlayerState(50, true, 1));
        playerAtackStates.add(new PlayerState(90, true, 2));
        playerAtackStates.add(new PlayerState(80, false, 1));

        List<PlayerState> playerHideStates = new ArrayList<>();
        playerHideStates.add(new PlayerState(30, true, 1));
        playerHideStates.add(new PlayerState(60, true, 2));
        playerHideStates.add(new PlayerState(40, false, 1));

        List<PlayerState> playerEscapeStates = new ArrayList<>();
        playerEscapeStates.add(new PlayerState(90, true, 4));
        playerEscapeStates.add(new PlayerState(60, true, 4));
        playerEscapeStates.add(new PlayerState(10, false, 1));

        List<PlayerState> playerNoneStates = new ArrayList<>();
        playerNoneStates.add(new PlayerState(60, true, 0));
        playerNoneStates.add(new PlayerState(100, false, 0));

        network.bulkNewStudy(playerAtackStates, ATTACK);
        network.bulkNewStudy(playerHideStates, HIDE);
        network.bulkNewStudy(playerEscapeStates, ESCAPE);
        network.bulkNewStudy(playerNoneStates, NONE);

        List<PlayerState> playerTestStates = new ArrayList<>();
        playerTestStates.add(new PlayerState(100, false, 1));
        playerTestStates.add(new PlayerState(50, true, 1));
        playerTestStates.add(new PlayerState(90, true, 2));
        playerTestStates.add(new PlayerState(30, false, 8));
        playerTestStates.add(new PlayerState(100, true, 8));
        playerTestStates.add(new PlayerState(100, false, 0));

        for (PlayerState playerState : playerTestStates) {

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

    public static void linkInputWithNeurin(Input[] inputs, Neuron[] neurons) {

    }


}
