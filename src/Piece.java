import processing.core.PApplet;

public class Piece {
    private PApplet canvas;
    private int x;
    private int y;
    private int size = 15;
    private float gravity;
    private float yVelocity;
    private double xVelocity;
    // private float xVelocity2;
    // private float xVelocity3;
    private float fruitcolor;

    public Piece(int xball, int yball, float xVel, float piecesColor, PApplet c) {
        x = xball;
        y = yball;
        canvas = c;
        yVelocity = 2;
xVelocity = xVel +canvas.random(-.5f,.5f);
fruitcolor = piecesColor;

    }


    public void display() {
        canvas.noStroke();
canvas.fill(fruitcolor);
        canvas.circle(x, y, size);

        gravity = .1f;

    }

    // public void displayPiece2() {
    //     // System.out.println("pieces two"+yVelocity);
    //     color = canvas.color(255);
    //     canvas.circle(x + 10, y +10, size);
    //     gravity = .3f;
    //     yVelocity = canvas.random(9, 20);
    // }

    // public void displayPiece3(){
    //     // System.out.println("pieces three" + yVelocity);
    //     color = canvas.color(255);
    //     canvas.circle(x -10, y + 10, size);
    //     gravity = .2f;
    //     yVelocity = 3;
    // }

    public void move() {
        yVelocity += gravity;
        y += yVelocity;
        x += xVelocity;

      
    } 
    public boolean piecehitsbottom(){
        if (y + size / 2 > canvas.height) {
            // AliveFruit = false;
            return true;
        } else {
            return false;
        }
    }
    
}
