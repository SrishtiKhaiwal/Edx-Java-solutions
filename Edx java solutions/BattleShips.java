import java.util.*;

public class BattleShips {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean war = false;
        int quantUser = 5;
        int quantComp = 5;

        int deployPause = 3500;
        int attackPause = 2500;
        int compTurnPause = 5000;

        // Step 1 - Create the ocean map
        System.out.println("*****************************************");
        System.out.println("***   Welcome to Battle Ships game!   ***");
        System.out.println("*****************************************");
        String[][] map = new String[14][14];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = "   ";
                if (i > 1 && i < map.length - 2) {
                    if (j == 0 || j == map[i].length - 1) {
                        map[i][j] = " " + (i - 2) + " ";
                    } else if (j == 1 || j == map[i].length - 2){
                        map[i][j] = " | ";
                    }
                } else if (j > 1 && j < map[i].length - 2) {
                    if (i == 0 || i == map.length - 1) {
                        map[i][j] = " " + (j - 2) + " ";
                    } else if (i == 1 || i == map.length - 2) {
                        map[i][j] = "---";
                    }
                }
            }
        }
        printMap(map, quantUser, quantComp, war);

        // Step 2 - Deploy player's ships
        System.out.println("        Right now, the sea is empty.");
        System.out.println("At first you need to place your ships on the map.");
        System.out.println("    You have 5 ships to deploy. Let's do it!");
        System.out.println();
        int[][] userShip = new int[5][2];
        for (int i =0; i < userShip.length; i++) {
            boolean repeated = true;
            while (repeated == true) {
                repeated = false;
                for (int j = 0; j < userShip[i].length; j++) {
                    userShip[i][j] = -1;
                    while (userShip[i][j] > 9 || userShip[i][j] < 0) {
                        if (j == 0) {
                            System.out.print("Enter the X coordinate of your ship n. " + (i + 1) + " ");
                            userShip[i][j] = input.nextInt();
                        } else {
                            System.out.print("Enter the Y coordinate of your ship n. " + (i + 1) + " ");
                            userShip[i][j] = input.nextInt();
                        }
                        if (userShip[i][j] > 9 || userShip[i][j] < 0) {
                            System.out.println("   |-------------------------------|");
                            System.out.println("   | Please! Enter a VALID number! |");
                            System.out.println("   |-------------------------------|");
                        }
                        if (j == userShip[i].length - 1) {
                            for (int cont = i -1; cont > -1; cont--) {
                                if (userShip[cont][j] == userShip[i][j] && userShip[cont][j - 1] == userShip[i][j - 1]) {
                                    System.out.println("|------------------------------------------------------------|");
                                    System.out.println("| You already deployed a ship in this position!!! Try again! |");
                                    System.out.println("|------------------------------------------------------------|");
                                    repeated = true;
                                }
                            }
                        }
                    }
                }
            }
            // transfers the coordinates to the map
            System.out.println();
            map[(userShip[i][1]) + 2][(userShip[i][0]) + 2] = "1";
            printMap(map, quantUser, quantComp, war);
        } // End of step 2

        // Step 3 - Deploy computer's ships
        System.out.println("                  Ok! Great!");
        System.out.println("Now computer will deploy its own ships on the map!");
        System.out.println("                 Please, wait...");
        pause(deployPause);
        System.out.println();
        int[][] compShip = new int[5][2];
        for (int i =0; i < compShip.length; i++) {
            boolean repeated = true;
            while (repeated == true) {
                repeated = false;
                for (int j = 0; j < compShip[i].length; j++) {
                    int temp = (int)(Math.random()*10);
                    compShip[i][j] = temp;
                    char coord = '0';
                    if (j == 0) {
                        coord = 'X';
                    } else {
                        coord = 'Y';
                    }
                    System.out.println("               " + coord + " coordinate is " + temp);

                    if (j == compShip[i].length - 1) { // verify if the coordinates is already a pair
                        // compares if the computer already placed a ship in that location
                        for (int cont = i -1; cont > -1; cont--) {
                            if (compShip[cont][j] == compShip[i][j] && compShip[cont][j - 1] == compShip[i][j - 1]) { // computers ship already deployed here
                                System.out.println("          not possible, trying again...");
                                repeated = true;
                            }
                        }
                        // compares if the user already placed a ship in that location
                        for (int a = 0; a < userShip.length; a++) {
                            if (userShip[a][j] == compShip[i][j] && userShip[a][j-1] == compShip[i][j-1]) { // users ship already deployed here
                                System.out.println("          not possible, trying again...");
                                repeated = true;
                            }
                        }
                    }
                }
            }
            // transfers the coordinates to the map
            map[(compShip[i][1]) + 2][(compShip[i][0]) + 2] = "2";
            System.out.println("               SHIP n." + (i + 1) + " DEPLOYED");
            System.out.println();
            pause(deployPause);
        }// End of step 3

        // Step 4 - Battle
        System.out.println();
        System.out.println("|--------------------------------------------|");
        System.out.println("|       **** LET THE WAR BEGINS! ****        |");
        System.out.println("|--------------------------------------------|");
        System.out.println();
        System.out.println("       Your ships @  |  Computer ships $");
        System.out.println(" This is the actual map with all ships deployed!");
        printMap(map, quantUser, quantComp, war);
        war = true;
        int turn = 0;
        while (quantUser > 0 && quantComp > 0) { // verify if the game is over
        // users turn
            if (turn == 0) { // users turn
                System.out.println("It's your turn: ");
                int[] shot = new int[2];
                boolean verify = false;
                while (verify == false) {
                    int a = 0;
                    verify = true;
                    for (a = 0; a < shot.length; a++) {
                        if (a == 0) {
                            int shotX = -1;
                            while (shotX < 0 || shotX > 9){
                                System.out.print("Input the X coordinate for your shot: ");
                                shotX = input.nextInt();
                                shot[a] = shotX;
                                if (shotX < 0 || shotX > 9) {
                                    System.out.println();
                                    System.out.println("Please, enter a VALID X coordinate!");
                                }
                            }
                        } else { // a == 1 , coordinate completed
                            int shotY = -1;
                            while (shotY < 0 || shotY > 9) {
                                System.out.print("Input the Y coordinate for your shot: ");
                                shotY = input.nextInt();
                                shot[a] = shotY;
                                if (shotY < 0 || shotY > 9) {
                                    System.out.println();
                                    System.out.println("Please, enter a VALID Y coordinate!");
                                }
                            }
                            if (map[(shot[a]) + 2][(shot[a - 1]) + 2] == "1") {
                                System.out.println();
                                System.out.println("Oh God! You sank your own ship!");
                                map[(shot[a]) + 2][(shot[a - 1]) + 2] = "3";
                                quantUser--;
                                pause(attackPause);
                                printMap(map, quantUser, quantComp, war);
                            } else if (map[(shot[a]) + 2][(shot[a - 1]) + 2] == "2") {
                                System.out.println();
                                System.out.println("Great! You sank one of your oponent's ship!");
                                map[(shot[a]) + 2][(shot[a - 1]) + 2] = "3";
                                quantComp--;
                                pause(attackPause);
                                printMap(map, quantUser, quantComp, war);
                            } else if (map[(shot[a]) + 2][(shot[a - 1]) + 2] == "3") {
                                System.out.println();
                                System.out.println("This point has already been attacked! Try again");
                                verify = false;
                            } else {
                                System.out.println();
                                System.out.println("You miss! Nothing here...");
                                map[(shot[a]) + 2][(shot[a - 1]) + 2] = "3";
                                pause(attackPause);
                                printMap(map, quantUser, quantComp, war);
                            }
                        }
                    }
                }
                turn = 1;
            } else if (turn == 1) {
                System.out.println("It's computer turn!");
                pause(compTurnPause);
                int[] shot = new int[2];
                boolean verify = false;
                while (verify == false) {
                    int a = 0;
                    verify = true;
                    for (a = 0; a < shot.length; a++) {
                        if (a == 0) {
                            shot[a] = (int)(Math.random()*10);
                        } else { // a == 1 , coordinate completed
                            shot[a] = (int)(Math.random()*10);
                            if (map[(shot[a]) + 2][(shot[a - 1]) + 2] == "1") {
                                System.out.println();
                                System.out.println("Oh Gosh! The computer sank one of your ships!");
                                map[(shot[a]) + 2][(shot[a - 1]) + 2] = "3";
                                quantUser--;
                                pause(attackPause);
                                printMap(map, quantUser, quantComp, war);
                            } else if (map[(shot[a]) + 2][(shot[a - 1]) + 2] == "2") {
                                System.out.println();
                                System.out.println("Wait! The computer sank its own ship! Great for you!");
                                map[(shot[a]) + 2][(shot[a - 1]) + 2] = "3";
                                quantComp--;
                                pause(attackPause);
                                printMap(map, quantUser, quantComp, war);
                            } else if (map[(shot[a]) + 2][(shot[a - 1]) + 2] == "3") {
                                verify = false;
                            } else {
                                System.out.println();
                                System.out.println("Computer missed...");
                                map[(shot[a]) + 2][(shot[a - 1]) + 2] = "3";
                                pause(attackPause);
                                printMap(map, quantUser, quantComp, war);
                            }
                        }
                    }
                }
                turn = 0;
            }
        }
        if (quantComp == 0) {
            System.out.println("Yeah! You WIN the battle!!! Congratulations!!!");
        } else if (quantUser == 0) {
            System.out.println("Oh No! You LOSE the battle! Play again...");
        }
        System.out.println("The game is over!!!");
    }

    public static void printMap(String[][] map, int quantUser, int quantComp, boolean war) {
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == "1") {
                    System.out.print(" @ ");
                } else if (map[i][j] == "2") {
                    System.out.print(" $ ");
                } else if (map[i][j] == "3") {
                    System.out.print(" + ");
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
        if (war == true) {
            System.out.println();
            System.out.println("Your ships@: " + quantUser + "  |  Computer ships$: " + quantComp);
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }

    public static void pause(int pause) {
        // Pause - to show the information
        try {
            Thread.sleep(pause);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
