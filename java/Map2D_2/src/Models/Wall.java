package Models;

public class Wall {

    private String _id;

    private String _name;
    private String _x1;
    private String _y1;
    private String _x2;
    private String _y2;

    public void Wall(String name,String x1,String y1,String x2,String y2 ) {
        this._name = name;
        this._x1 = x1;
        this._y1 = y1;
        this._x2 = x2;
        this._y2 = y2;
    }

    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }


    public String getName() {
        return _name;
    }
    public void setName(String name) {
        this._name = name;
    }

    public String getX1() {
        return _x1;
    }
    public void setX1(String x1) {
        this._x1 = x1;
    }

    public String getY1() {
        return _y1;
    }
    public void setY1(String y1) {
        this._y1 = y1;
    }

    public String getX2() {
        return _x2;
    }
    public void setX2(String x2) {
        this._x2 = x2;
    }

    public String getY2() {
        return _y2;
    }
    public void setY2(String y2) {
        this._y2 = y2;
    }
}
