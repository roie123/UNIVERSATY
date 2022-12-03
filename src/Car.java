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

//constructors:
    /*
     * creates a new Car object 
     * @param _id the id of car 
     * @param _char the type of car
     * @param _brand the brand of car
     * @param _isManual whether the car is manual
     */
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
//constructors:
    /*
     * creates a new Car object 
     * @param car the   car to create  
    
     */
    public Car(Car car) {
        this._id = car.getId();
        this._type = car.getType();
        this._isManual = car._isManual;
        this._brand = car.getBrand();
    }
/**
 * returns the id of the car
 * @return
 */
    public int getId() {

        return _id;
    }
/**
 * setting the  id  of  car
 * @return void 
 */
    public void setId(int _id) {
        if (idValidation(_id)) {
            this._id = _id;
        }
    }
/**
 * returns the type of car
 * @return type
 */
    public char getType() {
        return _type;
    }
/**
 * sets the type of car 
 * @return void 
 */
    public void setType(char _type) {
        this._type = _type;
    }
/**
 * returns the brand of brand 
 * @return String brand 
 */
    public String getBrand() {
        return _brand;
    }
/**
 * set the brand of the car 
 * @return
 */
    public void setBrand(String _brand) {
        this._brand = _brand;
    }
/**
 * gets isManual 
 * @return boolean if the car isManual
 */
    public boolean isManual() {
        return _isManual;
    }
/**
 * sets the manual attribute for the car
 * @return void 
 */
    public void setIsManual(boolean _isManual) {
        this._isManual = _isManual;
    }


    /**
    This method will check if the input id is valid and return true if it does
    @param id the id of the car 
    @return if the id is valid and return true if it does
     */
    private boolean idValidation(int id) {
        return id >= MIN_ID_VALUE && id <= DEFAULT_ID_VALUE;
    }

    /**
        This method will check if the input type is valid and return true if it does
        @param char the input type  character 
        @return boolean if the input type is valid and return true if it
         */
    private boolean isTypeValid(char type) {
        switch (type) {
            case ('A'), ('B'), ('C'), ('D') -> {
                return true;
            }
        }
        return false;
    }
     /**
        This method will check if the input type is valid and return true if it does
        @param char the input type  character 
        @return boolean if the input type is valid and return true if it
         */
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return _id == car._id && _type == car._type && _isManual == car._isManual && Objects.equals(_brand, car._brand);
    }

   
/**
        This method will represent the string value of the Car object 
        @return String  value of the Car object
         */
    public String toString() {
        if (_isManual) {
            return
                    "id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + "manual";
        } else return
                "id:" + _id + " type:" + _type + " brand:'" + _brand + " gear:" + "auto";

    }
/**
        This method will check if the input car is better than the other car object
        @param Car the car to check 
        @return boolean if the original car is better than the other car    
         */
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
