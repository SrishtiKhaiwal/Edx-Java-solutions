import java.util.*;

public class FractionCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Welcome the user
        System.out.println("Welcome to Fraction Calculator!");
        System.out.println("It will add, subtract, multiply and divide fraction until you type Q to quit.");
        System.out.println("Please enter your fraction in the form a/b, where a and b are integers.");
        System.out.println("-----------------------------------------------------------------------------");

        String operator = "";
        while (!operator.equalsIgnoreCase("Q")) { // if user do not input the "Q" letter
            System.out.print("Please, enter an operation (+, -, /, *, =) or Q to quit: ");
            operator = getOperation(input.nextLine());
            if (!operator.equalsIgnoreCase("Q")) {
                String firstPrint;
                String secondPrint;
                int firstFractionNumerator = 0;
                int firstFractionDenominator = 1;
                int secondFractionNumerator = 1;
                int secondFractionDenominator = 1;


                boolean validated = false;
                while (!validated) {
                    System.out.print("Please, enter a Fraction (a/b) or integer (a): ");
                    String firstFraction = input.nextLine();
                    validated = validFraction(firstFraction);
                    if (validated) {
                        int findSlash = firstFraction.indexOf("/");
                        if (findSlash >= 0) {
                            firstFractionNumerator = Integer.parseInt(firstFraction.substring(0, findSlash));
                            firstFractionDenominator = Integer.parseInt(firstFraction.substring(findSlash + 1, firstFraction.length()));
                        } else {
                            firstFractionNumerator = Integer.parseInt(firstFraction.substring(0, firstFraction.length()));
                            firstFractionDenominator = 1;
                        }
                    }
                }
                Fraction fractionOne = new Fraction(firstFractionNumerator, firstFractionDenominator);
                firstPrint = fractionOne.toString();

                validated = false;
                while (!validated || secondFractionNumerator == 0) {
                    System.out.print("Ok, now enter another Fraction (a/b) or integer (a): ");
                    String secondFraction = input.nextLine();
                    validated = validFraction(secondFraction);
                    if (validated) {
                        int findSlash = secondFraction.indexOf("/");
                        if (findSlash >= 0) {
                            secondFractionNumerator = Integer.parseInt(secondFraction.substring(0, findSlash));
                            secondFractionDenominator = Integer.parseInt(secondFraction.substring(findSlash + 1, secondFraction.length()));
                        } else {
                            secondFractionNumerator = Integer.parseInt(secondFraction.substring(0, secondFraction.length()));
                            secondFractionDenominator = 1;
                        }
                    }
                }
                Fraction fractionTwo = new Fraction(secondFractionNumerator, secondFractionDenominator);
                secondPrint = fractionTwo.toString();

                //Data treatment
                Fraction result = new Fraction();
                String equalsResult = "";
                switch (operator) {
                    case "/":
                        result = fractionOne.divide(fractionTwo);
                        break;
                    case "*":
                        result = fractionOne.multiply(fractionTwo);
                        break;
                    case "+":
                        result = fractionOne.add(fractionTwo);
                        break;
                    case "-":
                        result = fractionOne.subtract(fractionTwo);
                        break;
                    case "=":
                        boolean temp = fractionOne.equals(fractionTwo);
                        if (temp) {
                            equalsResult = "Equals";
                        } else {
                            equalsResult = "Not equals";
                        }
                        break;
                }

                //Shows result
                if (equalsResult.equals("Equals") || equalsResult.equals("Not equals")){
                        System.out.println("(" + firstPrint + ") " + operator + " (" + secondPrint + ") = " + equalsResult);
                        System.out.println("-------------------------------------------------------------------------------");
                }
                else if (result.getNumerator() == 0 || result.getDenominator() == 1) {
                    System.out.println("(" + firstPrint + ") " + operator + " (" + secondPrint + ") = " + result.getNumerator());
                    System.out.println("-------------------------------------------------------------------------------");
                } else {
                    System.out.println("(" + firstPrint + ") " + operator + " (" + secondPrint + ") = " + result.toString());
                    System.out.println("-------------------------------------------------------------------------------");
                }

            } else {
                // if user inputs the "Q" letter
                System.out.println("Thank you for using Fraction Calculator!"); //Exit message
            }
        }
    }

    public static String getOperation(String operator) {
        Scanner input = new Scanner(System.in);
        while (!operator.equalsIgnoreCase("Q") && !operator.equals("+") && !operator.equals("-") && !operator.equals("/") && !operator.equals("*") && !operator.equals("=")) {
            System.out.print("Please, enter an operation (+, -, /, *, =) or Q to quit: ");
            operator = input.nextLine();
        }
        return operator;
    }

    public static boolean validFraction(String testFraction) {
        if (testFraction.charAt(0) == '-') {
            int findSlash = testFraction.indexOf('/');
            if (findSlash >= 0) {
                return isANum(testFraction.substring(1, findSlash)) && isANum(testFraction.substring(findSlash + 1, testFraction.length())) && !testFraction.substring(findSlash + 1, testFraction.length()).equals("0") ;
            } else { // conditions to integers
                return isANum(testFraction.substring(1, testFraction.length()));
            }

        } else { // fraction or integers with no "-"
            int findSlash = testFraction.indexOf('/');
            if (findSlash >= 0) {
                return isANum(testFraction.substring(0, findSlash)) && isANum(testFraction.substring(findSlash + 1, testFraction.length())) && !testFraction.substring(findSlash + 1, testFraction.length()).equals("0");
            } else { // conditions to integers
                return isANum(testFraction.substring(0, testFraction.length()));
            }
        }
    }

    private static boolean isANum(String subString) {
        int cont = 0;
        if (subString.length() == 0) { // If the substring is empty
            return false;
        } else {
            for (int i = 0; i < subString.length(); i++) {
                if ((int)subString.charAt(i) < 58 && (int)subString.charAt(i) > 47) {
                    cont++;
                }
            }
            //shows if all the chars are numbers
            return cont == subString.length();
        }
    }
}

