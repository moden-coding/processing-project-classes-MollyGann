import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Fruit> fruits;
    private int livesleft = 3;
    PVector[] circlesswordparts = { new PVector(-1200, 100), new PVector(-1200, 100), new PVector(-1200, 100) };

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        fruits = new ArrayList<>();

        Fruit applefruits = new Fruit("apple", 50, this);
        // Fruit kiwifruits = new Fruit("kiwi", 250, 290, this);
        // fruits.add(kiwifruits);
        fruits.add(applefruits);
        background(59, 35, 6);

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(59, 35, 6);

        for (Fruit f : fruits) {
            f.display();
        }
        if (circlesswordparts[0] != null) {
            for (int i = 0; i < circlesswordparts.length; i++) {
                fill(i * 100);
                PVector p = circlesswordparts[i];

                circle(p.x, p.y, 30 - 5 * i);

            }

        }
        if (frameCount % 2 == 0) {
            update();

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
            if (!f.checkTouch(mouseX, mouseY)){
                livesleft--;
        }
        }
    }

    

}
