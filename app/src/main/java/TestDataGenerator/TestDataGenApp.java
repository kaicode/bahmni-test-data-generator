package TestDataGenerator;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TestDataGenApp {

    public static void main(String args[])
    {
        /*Takes inputs from runTime*/
        //Map<String, Integer> userInput = validateInput();
        /* Takes input from Arguments */
        Map<String, Integer> userInput = validateArgsInput();
        try {
            createCSVs(userInput.get("profileCount"), userInput.get("contactCount"));
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (CsvException e) {
            System.out.println(e.getLocalizedMessage());
        }
        Interactions.login("superman","Admin123");
    }

    protected static Map<String, Integer> validateInput()
    {
        int patientProfileCount = 0;
//        int contactCount = 0;
        Map<String, Integer> totalCount = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of patient profiles needed: ");
        patientProfileCount = scanner.nextInt();

        System.out.println("Do you need contact csv to be created? (y/n):");
        String sCreateContact = scanner.next();

        if(sCreateContact.equalsIgnoreCase("y")) {
            System.out.println("Enter the number of profiles need to have contacts from above. " +
                    "Enter a number between 0 and "+patientProfileCount);
            int contactCount = scanner.nextInt();

            while(contactCount > patientProfileCount)
                {
                    System.out.println("Enter the number of lesser than of patient profile number entered above.");
                    contactCount = scanner.nextInt();
                }

            totalCount.put("contactCount", contactCount);
        }

        totalCount.put("profileCount", patientProfileCount);


        return totalCount;
    }

    protected static Map<String, Integer> validateArgsInput()
    {
       // int patientProfileCount = 0;
//        int contactCount = 0;
        Map<String, Integer> totalCount = new HashMap<>();

       int patientProfileCount = Integer.parseInt(validProperty("patientProfileCount"));
        System.out.println("Entered number of patient profiles : "+patientProfileCount);


        String sCreateContact = validProperty("sCreateContact");
        System.out.println("Do you need contact csv to be created? (y/n) : "+sCreateContact);

        if(sCreateContact.equalsIgnoreCase("y")) {

            int contactCount = Integer.parseInt(validProperty("contactCount"));
            System.out.println("Entered number of profiles need to have contacts from above : "+contactCount);

            if(contactCount > patientProfileCount)
            {
                throw new RuntimeException("contact count cannot be greater than patient count");
            }

            totalCount.put("contactCount", contactCount);
        }

        totalCount.put("profileCount", patientProfileCount);


        return totalCount;
    }

    protected static void createCSVs(int patientProfileCount, int contactProfileCount) throws IOException, ParseException,
            CsvException {
        PatientProfile patientProfile = new PatientProfile();
        ContactProfile contactProfile = new ContactProfile();

        if(patientProfileCount!=0) {
            patientProfile.writePatientProfileInCSV(patientProfileCount);
        }

        if(contactProfileCount!=0)
        {
            contactProfile.writeContactProfileInCSV(contactProfileCount);
        }

    }
    protected static String validProperty(String str)
    {
        String prop=System.getProperty(str);
        String result = (prop == null) ? Constant.getProperty(str) : prop;
        return result;
    }
}
