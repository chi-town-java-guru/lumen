/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author gurunath.sugavanam
 */
public class Hierarchy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = "null,0,grandpa|0,1,son|0,2,daugther|1,3,grandkid|1,4,grandkid|2,5,grandkid|5,6,greatgrandkid";

        children(input.split("\\|"));
    }

    private static void children(String[] splitInput) {
        Map<String, List<String>> nodeToChildMap = nodeToChild(splitInput);
        Map<String, String> nodeToSelftMap = nodeToSelf(splitInput);

        for (String s : nodeToSelftMap.keySet()) {
            System.out.println("Children of " + nodeToSelftMap.get(s)+" (node "+s+"): ");
            if (nodeToChildMap.get(s) != null) {
                nodeToChildMap.get(s).forEach(st -> System.out.println(nodeToSelftMap.get(st)));
            }else{
                System.out.println("none");
            }
        }

    }

    private static Map<String, List<String>> nodeToChild(String[] splitInput) {
        Map<String, List<String>> nodeToChildMap = new TreeMap<>();
        for (String s : splitInput) {
            String[] person = s.split(",");
            if (null != person[0]) {
                if (nodeToChildMap.containsKey(person[0])) {
                    List<String> t = nodeToChildMap.get(person[0]);
                    t.add(person[1]);
                    nodeToChildMap.put(person[0], t);
                } else {
                    List<String> t = new ArrayList<>();
                    t.add(person[1]);
                    nodeToChildMap.put(person[0], t);
                }
            }
        }
        return nodeToChildMap;
    }

    private static Map<String, String> nodeToSelf(String[] splitInput) {
        Map<String, String> nodeToSelfMap = new HashMap<>();
        for (String s : splitInput) {
            String[] person = s.split(",");
            nodeToSelfMap.put(person[1], person[2]);
        }
        return nodeToSelfMap;
    }

}
