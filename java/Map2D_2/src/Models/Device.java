package Models;

public class Device {
    private String _id;

    private String _name;
    private String _x;
    private String _y;

    public void Device(String name, String x, String y) {
        this._name = name;
        this._x = x;
        this._y = y;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() { return _name; }
    public void setName(String name) {
        this._name = name;
    }

    public String getX() {
        return _x;
    }
    public void setX(String x) {
        this._x = x;
    }

    public String getY() {
        return _y;
    }
    public void setY(String y) {
        this._y = y;
    }

}
