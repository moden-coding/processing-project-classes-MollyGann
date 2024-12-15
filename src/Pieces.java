import processing.core.PApplet;

public class Pieces {
    private PApplet canvas;
    private int x;
    private int y;
    private int size = 5;
    private float gravity;
    private float yVelocity;
    private float xVelocity;
    private int color;

    public Pieces(int xball, int yball,  PApplet c) {
        x = xball;
        y = yball;
        canvas = c;
        movePieces();
        yVelocity = 4;

    }

    public void displayAll(){
        displayPiece1();
        displayPiece2();
        displayPiece3();
        movePieces();
    }

    public void displayPiece1() {
        // System.out.println("pieces one" + yVelocity);
        color = canvas.color(255);
        canvas.circle(x, y, size);

        canvas.circle(x, y, size); 
        canvas.circle(x, y, size);

        gravity = .3f;
        yVelocity = canvas.random(9, 20);
        xVelocity = 3;

    }

    public void displayPiece2() {
        // System.out.println("pieces two"+yVelocity);
        color = canvas.color(255);
        canvas.circle(x + 10, y +10, size);
        gravity = .3f;
        yVelocity = canvas.random(9, 20);
             xVelocity = 2;
    }

    public void displayPiece3(){
        // System.out.println("pieces three" + yVelocity);
        color = canvas.color(255);
        canvas.circle(x -10, y + 10, size);
        gravity = .2f;
        yVelocity = 3;
        xVelocity = -4;
    }

    public void movePieces() {
        yVelocity += gravity;
        y += yVelocity;
        x += xVelocity;

    }
}
