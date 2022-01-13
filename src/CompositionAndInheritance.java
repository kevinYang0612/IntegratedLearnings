
public class CompositionAndInheritance {

    public static void main(String[] args)
    {
        Honda hondaJazz = new Honda();
        hondaJazz.setColor("black");
        hondaJazz.setMaxSpeed(160);
        hondaJazz.carDetails();
        hondaJazz.HondaStart();

    }
}
// aggregation: is independent between two object
// Address is independent with a Person

// Composition: honda has an engine(composition), honda is a car(inheritance)
// car engine is part of car, without car, engine without meaning

// Association: describe composition and aggregation.
class Car
{
    private String color;
    private int maxSpeed;
    public void carDetails()
    {
        System.out.println("Car color = " + color + "Max speed = " + maxSpeed);
    }

    public void setColor(String color)
    {
        this.color = color;
    }
    public void setMaxSpeed(int maxSpeed)
    {
        this.maxSpeed = maxSpeed;
    }
}
class carEngine
{
    public void starEngine()
    {
        System.out.println("The car engine has started");
    }
    public void stopEngine()
    {
        System.out.println("The car engine has stopped");
    }
}
class Honda extends Car  // honda is a subclass, car is super class
{
    public void HondaStart()
    {
        carEngine hondaEngine = new carEngine(); // composition
        hondaEngine.starEngine();
    }
}