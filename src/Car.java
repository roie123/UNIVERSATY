import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * MAMAN 12 - Using a class to represent a Car
 *
 * @author Roie Ivri
 * @version 2022
 */
public class Car {

    private final int MAX_ID_DIGITS = 7;
    private final int MIN_ID_VALUE = 1000000;
    private final int DEFAULT_ID_VALUE = 9999999;
    private final char DEFAULT_TYPE_VALUE = 'A';


    private int _id = DEFAULT_ID_VALUE;
    private char _type = DEFAULT_TYPE_VALUE;
    private String _brand;
    private boolean _isManual;


    public Car(int _id, char _type, String _brand, boolean _isManual) {
        if (idValidation(_id)) {
            this._id = _id;

        }
        if (isTypeValid(_type)) {
            this._type = _type;
        }
        this._brand = _brand;
        this._isManual = _isManual;
    }

    public Car(Car car) {
        this._id = car.getId();
        this._type = car.getType();
        this._isManual = car._isManual;
        this._brand = car.getBrand();
    }

    public int getId() {

        return _id;
    }

    public void setId(int _id) {
        if (idValidation(_id)) {
            this._id = _id;
        }
    }

    public char getType() {
        return _type;
    }

    public void setType(char _type) {
        this._type = _type;
    }

    public String getBrand() {
        return _brand;
    }

    public void setBrand(String _brand) {
        this._brand = _brand;
    }

    public boolean isManual() {
        return _isManual;
    }

    public void setIsManual(boolean _isManual) {
        this._isManual = _isManual;
    }


    /*
    This method will check if the input id is valid and return true if it does
     */
    private boolean idValidation(int id) {
        return id >= MIN_ID_VALUE && id <= DEFAULT_ID_VALUE;
    }

    /*
        This method will check if the input type is valid and return true if it does
         */
    private boolean isTypeValid(char type) {
        switch (type) {
            case ('A'), ('B'), ('C'), ('D') -> {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return _id == car._id && _type == car._type && _isManual == car._isManual && Objects.equals(_brand, car._brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, _type, _brand, _isManual);
    }

    @Override
    public String toString() {
        if (_isManual) {
            return
                    "id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + "manual";
        } else return
                "id:" + _id + " type:" + _type + " brand:'" + _brand + " gear:" + "auto";

    }

    public boolean better(Car other) {
        if (this.equals(other)){
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('B', 2);
        map.put('C', 3);
        map.put('D', 4);
        int tempHolder = map.get(this._type) - map.get(other.getType());
        if (tempHolder == 0) {
            if (!this._isManual){
                return true;
            }else if (!other._isManual){
                return false;
            }
        }  return tempHolder>0;
    }

    public boolean worse(Car other){
        return !better(other);
    }
}
