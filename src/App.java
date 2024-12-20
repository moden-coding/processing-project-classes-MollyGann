import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Fruit> fruits;
    ArrayList<Piece> pieces;
    int livesleft = 3;
    int scoreofhitfruit = 0;
    boolean touchTimer;
    int starttime;
    int mousehitX;
    int mousehitY;
    int gamescreen = 0;
    int highscore;

    PVector[] circlesswordparts = { new PVector(-1200, 100), new PVector(-1200, 100), new PVector(-1200, 100) };

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        readHighScore();
        fruits = new ArrayList<>();
        pieces = new ArrayList<>();
        background(59, 35, 6);

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        // System.out.println(gamescreen);
        background(59, 35, 6);
        if (gamescreen == 0) {
            startScreen();
        } else if (gamescreen == 1) {
            displayFruit();

            displaySword();
            textAlign(LEFT, CENTER);

            // fill(255);
            // textSize(40);
            // text("Lives: " + livesleft, 550, 30);

            drawXs();
            fill(255);
            textSize(40);
            text("Score: " + scoreofhitfruit, 20, 33);

            plusOne();
            showPieces();

        } else if (gamescreen == 2) {
            endScreen();
        }

    }

    public void update() {
        mouseTouch();
        PVector mouse = new PVector(mouseX, mouseY);
        circlesswordparts[2] = circlesswordparts[1];
        circlesswordparts[1] = circlesswordparts[0];
        circlesswordparts[0] = mouse;

    }

    public void mouseTouch() {
        for (int i = 0; i < fruits.size(); i++) {
            Fruit f = fruits.get(i);
            if (f.checkTouch(mouseX, mouseY)) {
                fruits.remove(f);
                scoreofhitfruit++;
                touchTimer = true;
                starttime = frameCount;

                mousehitX = mouseX;
                mousehitY = mouseY;

                createPieces(f.getXVelocity(), f.piecesColor());

                // System.out.println(f.piecesColor());
            }
        }
    }

    public void displayFruit() {

        hittingBottom();

        createFruit();
    }

    public void displaySword() {
        noStroke();
        if (circlesswordparts[0] != null) {
            for (int i = 0; i < circlesswordparts.length; i++) {
                fill(i * 100);
                PVector p = circlesswordparts[i];

                circle(p.x, p.y, 15 + (5 * i));

            }

        }
        if (frameCount % 2 == 0) {
            update();

        }
    }

    public void hittingBottom() {
        for (int i = 0; i < fruits.size(); i++) {
            Fruit f = fruits.get(i);

            f.display();
            if (f.hitBottom()) {
                // System.out.println("off screen");
                fruits.remove(f);
                i--;
                livesleft--;
                if (livesleft <= 0) {
                    gamescreen = 2;
                    saveHighScore();
                }

            }
        }
        // System.out.println(fruits.size());
    }

    public void createFruit() {
        if (frameCount % 100 == 0) {
            if (random(1) < .5) { // change so quantity is based on height
                for (int i = 0; i < 3; i++) { // add mango
                    float odds = random(3);
                    if (1 > odds && odds > 0) {
                        fruits.add(new Fruit("kiwi", this, "easy"));

                    } else if (2 > odds && odds > 1) {
                        fruits.add(new Fruit("apple", this, "easy"));

                    } else {
                        fruits.add(new Fruit("mango", this, "easy"));
                    }

                }
            } else {
                for (int i = 0; i < 1; i++) {
                    float odds = random(3);
                    if (1 > odds && odds > 0) {
                        fruits.add(new Fruit("kiwi", this, "hard"));

                    } else if (2 > odds && odds > 1) {
                        fruits.add(new Fruit("apple", this, "hard"));

                    } else {
                        fruits.add(new Fruit("mango", this, "hard"));
                    }

                }
            }

        }
    }

    public void plusOne() {
        if (touchTimer) {
            if (frameCount <= starttime + 25) {
                fill(255);
                textSize(32);
                text("+ 1 POINT!", mousehitX - 50, mousehitY - 25);
            }
        } else {
            touchTimer = false;
        }
    }

    public void drawXs() { // i fixed the repetition!!!!
        stroke(255);
        // if (livesleft <= 2) { // first X
        // stroke(168, 5, 5);
        // } else {
        // stroke(255);
        // }
        // strokeWeight(10);
        // line(670, 15, 690, 40);
        // line(690, 15, 670, 40);

        // if (livesleft <= 1) { // 2nd X
        // stroke(168, 5, 5);
        // } else {
        // stroke(255);
        // }
        // line(710, 15, 730, 40);
        // line(730, 15, 710, 40);

        // if (livesleft <= 0) {// 3rd X
        // stroke(168, 5, 5);
        // } else {
        // stroke(255);
        // }
        // line(750, 15, 770, 40);
        // line(770, 15, 750, 40);

        strokeWeight(10);
        for (int i = 2; i >= 0; i--) {
            if (livesleft <= i) {
                stroke(168, 5, 5);
            } else {
                stroke(255);
            }
            line(750 - 40 * i, 15, 770 - 40 * i, 40);
            line(770 - 40 * i, 15, 750 - 40 * i, 40);

        }

        stroke(255);
    }

    public void createPieces(float xVel, float piecesColor) {

        // System.out.println("pieces is happening");
        for (int i = 0; i < 5; i++) {
            pieces.add(new Piece(mousehitX, mousehitY, xVel, piecesColor, this));
            System.out.println(pieces.size());

        }
    }

    public void showPieces() {
        for (int i = 0; i < pieces.size(); i++) {
            Piece p = pieces.get(i);

            // for(Piece p: pieces){

            p.display();
            p.move();

            // }

            if (p.piecehitsbottom()) {
                pieces.remove(p);
            }

            // System.out.println(p);
        }
    }

    public void saveHighScore() {
        try (PrintWriter writer = new PrintWriter("highscore.txt")) {
            writer.println(highscore); // Writes the integer to the file
            writer.close(); // Closes the writer and saves the file

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void readHighScore() {
        try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {

            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                // we print the line that we read
                highscore = Integer.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void startScreen() {

        background(59, 35, 6);
        textAlign(CENTER, CENTER);
        fill(255);
        // title of the game in the middle
        textSize(30);
        text("Welcome to Fruit Ninja!!", width / 2, 135);
        text("You get points for slicing fruit", width / 2, 165);
        text("Good luck!", width / 2, 200);
        text("(You dont need to drag, just touch it with your mouse)", width / 2,
                235);

        // How to Start
        text("Press space to start", width / 2, 280);
    }

    public void keyPressed() {
        if (key == ' ' && gamescreen == 0 || gamescreen == 2) {
            reset();
        }
    }

    public void endScreen() {
        // if (gamescreen == 2) {

        if (highscore < scoreofhitfruit) {
            highscore = scoreofhitfruit;
            saveHighScore();

        }
        textAlign(LEFT, TOP);
        fill(255);
        textSize(60);
        text("Score: " + scoreofhitfruit, 20, 40);
        text("High Score: " + highscore, 20, 110);
        
        if(scoreofhitfruit == highscore){
            textSize(15);
            text("You got a new highscore!!!!", 10, 10);
        }
        textSize(40);
        text("Press space to play again", 20, 190);
        // fill(255,0,0);
        // textSize(100);
        // text("YOU DIED!", 20,220 );

        // }
    }

    public void reset() {
        gamescreen = 1;
        livesleft = 3;
        scoreofhitfruit = 0;
        fruits.clear(); // i googled this function
        pieces.clear();
        textAlign(LEFT, BASELINE);
    }

}

// public voud
