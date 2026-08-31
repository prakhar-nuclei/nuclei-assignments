package assignment1;

import assignment1.entity.Item;
import assignment1.entity.ItemResult;
import assignment1.exception.ParseException;
import assignment1.exception.ValidationException;
import assignment1.parser.InputParser;
import assignment1.service.TaxCalculatorService;
import assignment1.validation.InputValidation;

import java.util.Scanner;

public class Assignment1Application {

    public static void main(String[] args) {

        InputParser parser = new InputParser();
        InputValidation validation = new InputValidation();
        TaxCalculatorService taxCalculatorService =
                new TaxCalculatorService();

        try (Scanner scanner = new Scanner(System.in)) {

            boolean addAnotherItem;

            do {
                try {
                    System.out.println(
                            "Enter item details (-name <name> -price <price> " +
                                    "-quantity <quantity> -type <type>):"
                    );

                    String input = scanner.nextLine();

                    if (input.isBlank()) {
                        throw new ParseException("Input cannot be blank.");
                    }

                    String[] inputArguments = input.trim().split("\\s+");

                    Item item = parser.parse(inputArguments);

                    validation.validate(item);

                    ItemResult result = taxCalculatorService.calculate(item);

                    System.out.println("\nItem Name: " +
                            result.getItem().getName());

                    System.out.println("Item Price: " +
                            result.getItem().getTotalPrice());

                    System.out.println("Sales Tax: " +
                            result.getTax());

                    System.out.println("Final Price: " +
                            result.getFinalPrice());

                } catch (ParseException exception) {
                    System.out.println(
                            "Error: " + exception.getMessage()
                    );
                } catch (ValidationException exception) {

                    System.out.println(
                            "Error: " + exception.getMessage()
                    );

                } catch (Exception exception) {

                    System.out.println(
                            "An unexpected error occurred."
                    );
                }

                while (true) {
                    System.out.print(
                            "\nDo you want to enter details of any other item (y/n): "
                    );

                    String choice = scanner.nextLine().trim();

                    if (choice.equalsIgnoreCase("y")) {
                        addAnotherItem = true;
                        break;
                    }

                    if (choice.equalsIgnoreCase("n")) {
                        addAnotherItem = false;
                        break;
                    }

                    System.out.println("Invalid input. Please enter only y or n.");
                }

            } while (addAnotherItem);

        }
    }
}