package com.demosoft.investiogation.neuronlan.xml;

import com.demosoft.investiogation.neuronlan.PlayerBrain;
import com.demosoft.investiogation.neuronlan.xml.generated.network.Network;
import com.demosoft.investiogation.neuronlan.xml.generated.network.Neuron;
import com.demosoft.investiogation.neuronlan.xml.generated.network.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 01.12.2015.
 */
public class JAXB {
    public static void main(String[] args) {
        new JAXB().getStructure();

    }

    public Network getStructure() {
        try {

            Reader reader = new InputStreamReader(this.getClass().getClassLoader()
                    .getResourceAsStream("simpleNetwork.xml"));
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Network network = ((JAXBElement<Network>) jaxbUnmarshaller.unmarshal(reader)).getValue();
            System.out.println(network);
            return network;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PlayerBrain compileStructure(Network network) {
        List<Neuron> neurons = network.getNeuron();
        return null;
    }
}
