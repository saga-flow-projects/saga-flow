package com.demosoft.investiogation.neuronlan.xml;

import com.demosoft.investiogation.neuronlan.xml.generated.network.Network;
import com.demosoft.investiogation.neuronlan.xml.generated.network.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Andrii_Korkoshko on 01.12.2015.
 */
public class JAXB {

    private String configFile;

    public JAXB(String configFile) {
        this.configFile = configFile;
    }

    public Network getStructure() {
        try {
            Reader reader = new InputStreamReader(this.getClass().getClassLoader()
                    .getResourceAsStream(configFile));
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Network network = ((JAXBElement<Network>) jaxbUnmarshaller.unmarshal(reader)).getValue();
            return network;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
