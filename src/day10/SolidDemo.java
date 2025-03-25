package day10;

interface Bird{
    void eat();
}

interface Flyable{
    void fly();
}

abstract class BaseBird implements Bird{
    protected String name;

    public BaseBird(String name){
        this.name = name;
    }

    @Override
    public void eat(){
        System.out.println(name + " is eating.");
    }
}

// Birds that can fly
class FlyingBird extends BaseBird implements Flyable {
    public FlyingBird(String name) {
        super(name);
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying.");
    }
}

// Birds that cannot fly
class NonFlyingBird extends BaseBird {
    public NonFlyingBird(String name) {
        super(name);
    }
}

class Crow extends FlyingBird {
    public Crow() {
        super("Crow");
    }
}

class Parrot extends FlyingBird {
    public Parrot() {
        super("Parrot");
    }
}

class Penguin extends NonFlyingBird {
    public Penguin() {
        super("Penguin");
    }
}

public class SolidDemo {
    public static void main(String[] args) {
        Bird crow = new Crow();
        Bird parrot = new Parrot();
        Bird penguin = new Penguin();

        crow.eat();
        parrot.eat();
        penguin.eat();

        // Typecasting to access fly method only for flying birds
        if (crow instanceof Flyable) {
            ((Flyable) crow).fly();
        }
        if (parrot instanceof Flyable) {
            ((Flyable) parrot).fly();
        }
        if (penguin instanceof Flyable) {
            ((Flyable) penguin).fly(); // Won't execute as Penguin can't fly
        }
    }
}
