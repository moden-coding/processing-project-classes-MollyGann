import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Fruit> fruits;
    private int livesleft = 3;
    private int scoreofhitfruit = 0;
    private boolean touchTimer;
    private int starttime;
    private int mousehitX;
    private int mousehitY;
    PVector[] circlesswordparts = { new PVector(-1200, 100), new PVector(-1200, 100), new PVector(-1200, 100) };

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        fruits = new ArrayList<>();

        background(59, 35, 6);

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(59, 35, 6);

        displayFruit();

        displaySword();

        fill(255);
        textSize(40);
        text("Lives: " + livesleft, 550, 30);
        fill(255);
        textSize(40);
        text("Score: " + scoreofhitfruit, 550, 60);

        if (touchTimer) {

            System.out.println("Startime: " + starttime);

            if (frameCount <= starttime + 25) {
                fill(255);
                textSize(32);
                text("+ 1 POINT!", mousehitX - 50, mousehitY - 25);
            } else {
                touchTimer = false;
            }
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

            }
        }
    }

    public void displayFruit() {

        hittingBottom();

        createFruit();
    }

    public void displaySword() {
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

    // public void keyPressed() {
    //     if (key == ' ') {
    //         fruits.add(new Fruit("apple", this));
    //     }
    // }

    public void hittingBottom() {
        for (int i = 0; i < fruits.size(); i++) {
            Fruit f = fruits.get(i);
            f.display();
            if (f.hitBottom()) {
                System.out.println("off screen");
                livesleft--;
                fruits.remove(f);
                i--;
            }
        }
        // System.out.println(fruits.size());
    }

    public void createFruit() {
        if (frameCount % 100 == 0) {
            if (random(1) < .5) { // change so quantity is based on height
                for (int i = 0; i < 3; i++) {
                    if (random(1) < .5) {
                        fruits.add(new Fruit("kiwi", this, "easy"));

                    } else {
                        fruits.add(new Fruit("apple", this, "easy"));

                    }

                }
            } else {
                for (int i = 0; i < 1; i++) {
                    if (random(1) < .5) {
                        fruits.add(new Fruit("kiwi", this, "hard"));

                    } else {
                        fruits.add(new Fruit("apple", this, "hard"));

                    }

                }
            }

        }
    }

}
