package com.demosoft.investiogation.neuronlan.rule.csv;

import com.demosoft.investiogation.neuronlan.entity.PlayerStateRule;
import com.demosoft.investiogation.neuronlan.rule.StateProvider;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 01.12.2015.
 */
public class CsvStateProvider implements StateProvider {
    private String file;

    public CsvStateProvider(String file) {
        this.file = file;
    }

    @Override
    public List<PlayerStateRule> getRules() {
        List<PlayerStateRule> result = new ArrayList<>();
        try {
            Reader in = new InputStreamReader(this.getClass().getClassLoader()
                    .getResourceAsStream(file));
            BufferedReader bufferedReader = new BufferedReader(in);
            String[] headers =  bufferedReader.readLine().split(",");

            CSVFormat format = CSVFormat.DEFAULT;
            format = format.withHeader(headers);
            format = format.withCommentMarker('#');
            Iterable<CSVRecord> records = format.parse(bufferedReader);
            for (CSVRecord record : records) {
                PlayerStateRule newRule = new PlayerStateRule();
                newRule.setAction(PlayerStateRule.Action.getByCode(Integer.parseInt(record.get("action"))));
                newRule.setHealth(Double.parseDouble(record.get("health")));
                newRule.setGun(Boolean.parseBoolean(record.get("gun")));
                newRule.setEnemies(Integer.parseInt(record.get("enemies")));
                newRule.setArmor(Double.parseDouble(record.get("armor")));
                System.out.print(record.get("action") + " ");
                System.out.print(record.get("health") + " ");
                System.out.print(record.get("gun") + " ");
                System.out.print(record.get("enemies") + " ");
                System.out.println(record.get("armor") + " ");
                result.add(newRule);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public static void main(String[] args) {
        StateProvider stateProvider = new CsvStateProvider("startStatus.csv");
        stateProvider.getRules();
    }
}
