import processing.core.PApplet;

public class Fruit {
    public float x;
    public float y;
private int color;
private String fruitname;
private int size;
private PApplet canvas;
private float gravity;
private float yVelocity;
private float xVelocity;
private boolean AliveFruit = true;



public Fruit(String specificfruitname, int ypos, PApplet c ){
    x = c.random(0,800);
   if(x<400){
        xVelocity = c.random(0,5);
    }else{
        xVelocity = c.random(-5,0);

    }
    y=550;
    canvas = c;
    fruitname = specificfruitname;
    gravity = .3f;
    if(fruitname.equals("apple")){
        appleSetup();
    }
    yVelocity = -15;
    
    

   
    }
    
public void display(){
   launchBall();
  

    if (fruitname.equals("apple")){
        appleSetup();
    }else{
        kiwiVisiual();
    }

    canvas.circle(x, y, size);

   
       
        color = canvas.color(255, 0, 0);

    
}

public void appleSetup(){
    color = canvas.color(168, 5, 5);
    canvas.fill(color);
    size = 75;

}

public void kiwiVisiual(){
    canvas.fill(color);
    color = canvas.color(5, 84, 17);
    canvas.circle(x, y, size);
    size = 50;


}



public boolean checkTouch(int mouseX, int mouseY) {
    float distanceFromCenter = canvas.dist(x, y, mouseX, mouseY);
    if (distanceFromCenter < size / 2) {
        System.out.println("dead");
        gravity = 1.3f;
        return false;
    } else {
        System.out.println("alive");
        return true;
    }
}

private void launchBall(){
    yVelocity += gravity;
    y += yVelocity;
    x+= xVelocity;
}

}
