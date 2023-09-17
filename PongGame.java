import java.util.Random;
import java.util.Scanner;

public class PongGame {
    public static void main(String[] args) {
        int width = 20;
        int height = 10;
        char[][] board = new char[height][width];

        int ballX = width / 2;
        int ballY = height / 2;
        int ballDirectionX = 1;
        int ballDirectionY = 1;

        int paddleY = height / 2;
        int paddleSize = 3;
        int playerScore = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Clear the board
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[i][j] = ' ';
                }
            }

            // Draw the paddle
            for (int i = paddleY - paddleSize / 2; i <= paddleY + paddleSize / 2; i++) {
                board[i][0] = '|';
            }

            // Draw the ball
            board[ballY][ballX] = 'O';

            // Print the board
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }

            // Move the ball
            ballX += ballDirectionX;
            ballY += ballDirectionY;

            // Check for collisions
            if (ballY == 0 || ballY == height - 1) {
                ballDirectionY *= -1;
            }

            if (ballX == 1) {
                // Check for paddle collision
                if (ballY >= paddleY - paddleSize / 2 && ballY <= paddleY + paddleSize / 2) {
                    ballDirectionX *= -1;
                    playerScore++;
                } else {
                    break; // Game over
                }
            }

            // Move the paddle
            if (System.currentTimeMillis() % 2 == 0) {
                if (paddleY > 0) {
                    paddleY--;
                }
            } else {
                if (paddleY < height - 1) {
                    paddleY++;
                }
            }

            // Sleep for a while to control game speed
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Clear the console
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        System.out.println("Game Over! Your score: " + playerScore);
        scanner.close();
    }
}
