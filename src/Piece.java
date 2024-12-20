import processing.core.PApplet;

public class Piece {
    private PApplet canvas;
    private int x;
    private int y;
    private int size; 
    private float gravity;
    private float yVelocity;
    private double xVelocity;
    // private float xVelocity2;
    // private float xVelocity3;
    private int fruitcolor;

    public Piece(int xball, int yball, float xVel, float piecesColor, PApplet c) {
        x = xball;
        y = yball;
        canvas = c;
        yVelocity = 2;
xVelocity = xVel +canvas.random(-.9f,.3f);
fruitcolor = (int)piecesColor;
size = (int)canvas.random(15,30);
    }


    public void display() {
        canvas.noStroke();
canvas.fill(fruitcolor);
        canvas.circle(x, y, size);

        gravity = .2f;

    }

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

    public String toString(){
        return "x: " + x+ " y: "+ y + " xvelocity: "+ xVelocity+" yvelocity: " + yVelocity;
    }
    
}
