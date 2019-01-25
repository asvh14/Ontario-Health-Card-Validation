package validation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation
{
    public static void main(String[] args)
    {
        Validation validHC = new Validation();
        String tempHC;
        int[] healthCardNum = new int[10];
        int total = 0;
        String twoDigNum;
        String stringTotal;
        int checkDigit;
        int tempCheck;
        
        try
        {
            Scanner testScan = new Scanner(System.in);
            Scanner chooseTestScan = new Scanner(System.in);
            String test;
            String chooseTest;
            System.out.println("Would you like to run test cases? type 'yes' or anything else to enter your own number");
            test = testScan.nextLine();
            
            //Unit Testing
            if(test.equalsIgnoreCase("yes") == true)
            {
                do
                {
                    System.out.println("\nWhich test case would you like to run? (1-5)");
                    chooseTest = chooseTestScan.nextLine();

                    if(chooseTest.equalsIgnoreCase("1") == true)
                    {
                        /*
                        Test 1: Normal Case, Valid Health Card Number
                        Expected output: 'Valid Card Number'
                        */
                        System.out.println("Test 1: Normal Case, Valid Health Card Number\n" + "Expected output: 'Valid Card Number'");
                        System.out.println("Validating '9876543217'");
                        validHC.validate("9876543217");
                    }
                    else if(chooseTest.equalsIgnoreCase("2") == true)
                    {
                        /*
                        Test 2: Normal Case, Valid 10 Digit Number but Invalid Health Card Number
                        Expected output: 'Invalid Card Number'
                        */
                        System.out.println("Test 2: Normal Case, Valid 10 Digit Number, but Invalid Health Card Number\n" + "Expected output: 'Invalid Card Number'");
                        System.out.println("Validating '7485647382'");
                        validHC.validate("7485647382");
                    }
                    else if(chooseTest.equalsIgnoreCase("3") == true)
                    {
                        /*
                        Test 3: Invalid Input, number that greater than 10 digits 
                        */
                        System.out.println("Test 3: Invalid Input, number that is greater than 10 digits\n" + "Expected ouptut: 'Number is not 10 digits, therefore not valid'");
                        System.out.println("Validating '12345678910'");
                        validHC.validate("12345678910");
                    }
                    else if(chooseTest.equalsIgnoreCase("4") == true)
                    {
                        /*
                        Test 4: Invalid Input, number that less than 10 digits
                        Expected ouptut: 'Invalid Input', due to StringIndexOutOfBoundsException
                        */
                        System.out.println("Test 4: Invalid Input, number that is less than 10 digits\n" + "Expected ouptut: 'Invalid Input', this is due to a StringIndexOutOfBoundsException");
                        System.out.println("Validating '12345'");
                        validHC.validate("12345");
                    }
                    else if(chooseTest.equalsIgnoreCase("5") == true)
                    {
                        /*
                        Test 5: Invalid Input, letters instead of numbers
                        Expected output: 'Invalid Input', due to NumberFormatException
                        */
                        System.out.println("Test 5: Invalid Input, letters instead of numbers\n" + "Expected output: 'Invalid Input', this is due to a NumberFormatException");
                        System.out.println("Validating 'jskhdjaskh'");
                        validHC.validate("jskhdjaskh");
                    }
                        
                }while(chooseTest.equalsIgnoreCase("1") == true || chooseTest.equalsIgnoreCase("2") == true || chooseTest.equalsIgnoreCase("3") == true || chooseTest.equalsIgnoreCase("4") == true || chooseTest.equalsIgnoreCase("5") == true);
            }
            else
            {
                System.out.println("Enter Health Card Number");
                Scanner scanHC = new Scanner(System.in);
                tempHC = scanHC.nextLine();
                validHC.validate(tempHC);
            }
        }
        catch(StringIndexOutOfBoundsException | InputMismatchException |NumberFormatException e)
        {
            System.out.println("Invalid Input");
        }
    }
    
    //This is my method to validate the number the user enters,
    public void validate(String numHC)
    {
        String tempHC;
        boolean valid = true;
        int[] healthCardNum = new int[10];
        int total = 0;
        String twoDigNum;
        String stringTotal;
        int checkDigit;
        int tempCheck;
        long tempNum = Long.parseLong(numHC);
        
        //stores the check digit
        tempCheck = numHC.charAt(healthCardNum.length-1) - '0';
        //String.v
        //checks if the number entered by user is 10 digits
        if(String.valueOf(tempNum).length() == 10)
        {
            for(int i = 0; i < healthCardNum.length-1; i++)
            {
                healthCardNum[i] = numHC.charAt(i) - '0';
                //doubles the odd numbered digits of the card number and if the doubled digit is greater than 10, it will add the 2 digits of the doubled digit together
                if(i%2 == 0)
                {
                    healthCardNum[i] = healthCardNum[i]*2;
                    if(healthCardNum[i] >= 10)
                    {
                        twoDigNum = Integer.toString(healthCardNum[i]);
                        healthCardNum[i] = Character.getNumericValue(twoDigNum.charAt(0))+Character.getNumericValue(twoDigNum.charAt(1));
                    }
                }
                total += healthCardNum[i];
            }
            stringTotal = Integer.toString(total);

            /*
            checks if the check digit(10th digit of card number) is 0, becuase 
            for a card number to be valid according to Ontario's MOD 10 Check Digit, 
            10 minus the 2nd digit of the other 9 digits' sum must equal 
            the check digit, so I treat a 0 as a 10 to follow the standard
            */
            if(tempCheck == 0)
                checkDigit = 10;
            else
                checkDigit = tempCheck;

            //checks if 10 minus the 2nd digit of the other 9 digits' sum equals the check digit
            if(checkDigit == (10-(Character.getNumericValue(stringTotal.charAt(1)))))
                System.out.println("Valid Card Number");

            else
                System.out.println("Invalid Card Number");
        }

        else
        {
            System.out.println("Number is not 10 digits, therefore not valid");
        }
    }
}
