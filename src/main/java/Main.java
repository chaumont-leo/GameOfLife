import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Main");

    }

    public void settings() {
        size(800, 600);
    }
    public void draw() {
        text("Hello world!", width / 2, height / 2);
    }
    public void setup() {
        textSize(32);
        textAlign(CENTER);
        background(255,155,255);
    }

}
