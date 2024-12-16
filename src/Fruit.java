import processing.core.PApplet;

public class Fruit { // list of problems: balls getting out before leaving bottom, the game too hard,
                     // broken pieces
    public float x;
    public float y;
    private int color;
    private String fruitname;
    private int size;
    private PApplet canvas;
    private float gravity;
    private float yVelocity;
    private float xVelocity;

    public Fruit(String specificfruitname, PApplet c, String difficulty) {
        x = c.random(0, 800);
        if (x < 400) {
            xVelocity = c.random(0, 5);
        } else {
            xVelocity = c.random(-5, 0);

        }
        y = 550;
        canvas = c;
        fruitname = specificfruitname;
        gravity = .2f;
        if (fruitname.equals("apple")) {
            appleSetup();
        }

        if (difficulty.equals("easy")) {
            yVelocity = canvas.random(-15, -8);

        } else {
            yVelocity = canvas.random(-10, -6);
        }

    }

    public float getXVelocity(){
        return xVelocity;
    }

    public float piecesColor(){
        return color;
    }

    public void display() {

     

            if (fruitname.equals("apple")) {
                appleSetup();
            } else {
                kiwiVisiual();
            }

            launchBall();
            color = canvas.color(255, 0, 0);

    }





    public void appleSetup() {
        canvas.noStroke();
        color = canvas.color(168, 5, 5);
        canvas.fill(color);
        canvas.circle(x, y, size);

        size = 75;

    }

    public void kiwiVisiual() {
        canvas.noStroke();
        canvas.fill(5, 84, 17);
        canvas.circle(x, y, size);
        size = 50;

    }

    public boolean checkTouch(int mouseX, int mouseY) {
        float distanceFromCenter = canvas.dist(x, y, mouseX, mouseY);
        if (distanceFromCenter < size / 2) {
            System.out.println("dead");

            // yVelocity =6;
            return true;
        } else {

        }
        return false;

    }

    public void launchBall() {
        yVelocity += gravity;
        y += yVelocity;
        x += xVelocity;


    }

    public boolean hitBottom() {
        // System.out.println("checking bottom");
        if (y + size / 2 > canvas.height) {
            // AliveFruit = false;
            System.out.println("hit bottom");
            return true;
        } else {
            return false;
        }

    }
}
