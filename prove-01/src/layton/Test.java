package layton;

import java.util.Scanner;

/**
 * Test class to test User and NSALoginController classes.
 * Created by 22362 on 1/4/2017.
 */
public class Test {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please set your password: ");
        String password = s.next();
        boolean meetsRequirements = false;

        User newUser = new User(password);

        System.out.println("Password: " + newUser.getPassword());
        System.out.println("Hash:     " + newUser.getHashedPassword());
        System.out.println("Salt:     " + newUser.getSalt());

        while (!meetsRequirements){
            try {
                NSALoginController.hashUserPassword(newUser);
                meetsRequirements = true;
            } catch (WeakPasswordException e) {
                System.out.println(e.getMessage() + '\n');
                System.out.println("Please set your password: ");
                password = s.next();
                newUser.setPassword(password);
            }
        }

        System.out.println("Password: " + newUser.getPassword());
        System.out.println("Hash:     " + newUser.getHashedPassword());
        System.out.println("Salt:     " + newUser.getSalt());

        // Verify passwords match
        System.out.println("Re-enter your password: ");
        String checkPass = s.next();
        newUser.setPassword(checkPass);

        try {
            if (NSALoginController.verifyPassword(newUser))
                System.out.println("Passwords match!");
            else
                System.out.println("Passwords do not match!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}