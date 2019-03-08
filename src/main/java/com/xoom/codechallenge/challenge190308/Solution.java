package com.xoom.codechallenge.challenge190308;

import java.util.Scanner;

public class Solution {

    enum MovementType {
        PLUS_ONE,
        MINUS_ONE,
        LEAP,
        INITIAL
    }

    private static boolean canWinAtPosition(int leap, int[] game, int position, MovementType movementType) {
        // If we exceeeded or arrived at the array n position, then we won!
        if (position >= game.length - 1) {
            return true;
        }
        game[position] = 1;
        return
                // Leap movement allowed on anytime
                (
                        leap > 1 && (
                                // Check whether we can move to the next position
                                position + leap < game.length && game[position + leap] == 0
                                // Or the next leap position would give us a win
                                || position + leap >= game.length
                        ) &&
                        canWinAtPosition(leap, game, position + leap, MovementType.LEAP)
                ) ||
                // Movement to the right is only allowed if we don't come from a movement to the left
                (
                        movementType != MovementType.MINUS_ONE &&
                        // Check if position on the right is 0, we are allowed to move to that position
                        position + 1 < game.length && game[position + 1] == 0 &&
                        canWinAtPosition(leap, game, position + 1, MovementType.PLUS_ONE)
                ) ||
                // Movement to the left is only allowed if we don't come from a movement on the right
                (
                        movementType != MovementType.PLUS_ONE &&
                        // Check if position on the left is 0, we are allowed to move to that position
                        position - 1 >= 0 && game[position - 1] == 0 &&
                        canWinAtPosition(leap, game, position - 1, MovementType.MINUS_ONE)
                );
    }

    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        return canWinAtPosition(leap, game, 0, MovementType.INITIAL);
    }

    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println((canWin(leap, game)) ? "YES" : "NO");
        }
        scan.close();
    }
}
