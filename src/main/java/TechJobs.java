import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;                           //Added
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    public static Scanner side;
    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);  //0Search or 1List

            if (actionChoice.equals("list")) {  //1

                String columnChoice = getUserSelection("List", columnChoices);  // 0All, 1Pos, 2Emp, 3Loc, 4Skill

                if (columnChoice.equals("all")) {  //0All
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is 0Search

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
//                    System.out.println("Search all fields not yet implemented.");
                    printJobs(JobData.findByValue(searchTerm));                  //Added

                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

//        System.out.println("printJobs is not implemented yet");
        if (someJobs.size() > 0 ) {

            int countJob = 1;
            for (HashMap<String, String> job : someJobs) {                      //Added

                System.out.println("\n***** Start Job# " + countJob);           //Added
                for (Map.Entry<String, String> sj : job.entrySet()) {            //Added

                    System.out.println(sj.getKey() + " : " + sj.getValue());    //Added
                }
                System.out.println("***** End Job # " + countJob + "\n");       //Added
                countJob++;
            }                                                                   //Added
        } else {
            System.out.println("Search term was not found in the data.");
        }
    }
}