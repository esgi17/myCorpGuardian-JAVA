import pa.plugins.Plugins;

public class MapPlugin implements Plugins {

    public static void main(String[] args){}

    public String getName() {
        return "Map2DPlugin";
    }

    public void plug() {
        System.out.println("Plugged");
    }

    public void unplug() {
        System.out.println("Unplugged");
    }

    public void print(String str) {
        System.out.println("LOG : " + str);
    }
}
