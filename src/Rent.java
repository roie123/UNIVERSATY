import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * MAMAN 12 - Using a class to represent Rent in a car company
 *
 * @author Roie Ivri
 * @version 2022
 */
public class Rent {
    private final int DISCOUNT_NUMBER_OF_DAYS = 7;
    private final double DISCOUNT_VALUE = 0.10;
    private String _name;
    private Car car;
    private Date _pickDate;
    private Date _returnDate;
//constructors:
    /*
     * creates a new Rent object
     * @param _name the name of the customer
     * @param _pickDate the date of the pick up 
     * @param _returnDate the date of the return
     * @return the new rental object
     */

    public Rent(String _name, Car car, Date _pickDate, Date _returnDate) {
        if (isDatesValid(_returnDate, _pickDate)) {
            this._name = _name;
            this.car = car;
            this._pickDate = _pickDate;
            this._returnDate = _returnDate;
        } else {
            this._name = _name;
            this.car = car;
            this._pickDate = _pickDate;
            this._returnDate = _pickDate.tomorrow();
        }
    }
//constructors:
    /*
     * creates a new Rent object from another Rent object 
     * @param other the other Rent object to create
     @return new Rent object 
     */
    public Rent(Rent other) {

        this.car = other.car;
        this._name = other._name;
        this._pickDate = other._pickDate;
        this._returnDate = other._returnDate;
    }
 /*
     * gets the name of the customer
     * returns the name of the customer
      
    
     */
    public String getName() {
        return _name;
    }
 /*
     * sets the name of the customer
     * @param name the name of the customer
     * returns the  name of the customer  
    
     */
    public void setName(String _name) {
        this._name = _name;
    }
 /*
     * gets the car object
     * @return the car object
    
     */
    public Car getCar() {
        return car;
    }
 /*
     * sets the car object 
     * @param the car to set 
     * @return void 
    
     */
    public void setCar(Car car) {
        this.car = car;
    }
 /*
     * gets the date of pickup
     * @return the pickDate 
    
     */
    public Date getPickDate() {
        return _pickDate;
    }
 /*
     * sets the pickDate of the  Rent
     * @param Date the pickup date 
     * @return void 
    
     */
    public void setPickDate(Date _pickDate) {
        if (_pickDate.before(this._returnDate) && _pickDate.difference(_returnDate) > 1) {
            this._pickDate = _pickDate;

        }
    }
 /*
     * get the returnDate of the Rent 
     * @return  the returnDate value
    
     */
    public Date getReturnDate() {
        return _returnDate;
    }
 /*
     * sets the returnDate of the rental
     * @param Date the return
     * @return void  
    
     */
    public void setReturnDate(Date _returnDate) {
        if (_returnDate.after(this._pickDate) && _returnDate.difference(this._pickDate) > 1) {
            this._returnDate = _returnDate;

        }
    }
 /*
     * checking the validity of date
     * @param returnDate the given returnDate
     * @param pickDate the given    pickDate
     * @return boolean if the date is valid 
    
     */
    public boolean isDatesValid(Date returnDate, Date pickDate) {
        return pickDate.before(returnDate) && returnDate.difference(pickDate) >= 1;
    }

 /*
     * checking the other object is equal 
     * @param other the other object
     * @return boolean if the other object is equal
    
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return Objects.equals(_name, rent._name) && Objects.equals(car, rent.car) && Objects.equals(_pickDate, rent._pickDate) && Objects.equals(_returnDate, rent._returnDate);
    }
 /*
     *checking how meny days
     * @return int the number of days passed
    
     */
    public int howManyDays() {
        return this._pickDate.difference(this._returnDate);
    }
 /*
     * gets the price of the rental
     * @return the price of the rental 
    
     */
    public int getPrice() {
        Map<Character, Integer> priceMap = new HashMap<>();
        priceMap.put('A', 100);
        priceMap.put('B', 150);
        priceMap.put('C', 180);
        priceMap.put('D', 240);
        int numberOfDays = this._pickDate.difference(this._returnDate);

        int totalPriceBeforeDiscount = numberOfDays * priceMap.get(this.car.getType());
        double discountForAWeek = priceMap.get(car.getType()) * DISCOUNT_NUMBER_OF_DAYS * DISCOUNT_VALUE;

        int numberOfFullWeeks = _pickDate.difference(_returnDate) / 7;


        return (int) totalPriceBeforeDiscount - (int) (discountForAWeek * numberOfFullWeeks);
    }
 /*
     * gets the price for another Car
     * @param Car the car to get the price for
     * @return the price  
    
     */
    public int getPriceForACar(Car car) {
        Map<Character, Integer> priceMap = new HashMap<>();
        priceMap.put('A', 100);
        priceMap.put('B', 150);
        priceMap.put('C', 180);
        priceMap.put('D', 240);
        int numberOfDays = this._pickDate.difference(this._returnDate);

        int totalPriceBeforeDiscount = numberOfDays * priceMap.get(car.getType());
        double discountForAWeek = priceMap.get(car.getType()) * DISCOUNT_NUMBER_OF_DAYS * DISCOUNT_VALUE;
        int numberOfFullWeeks = _pickDate.difference(_returnDate) / 7;

        System.out.println("UPGRADE ::" + (totalPriceBeforeDiscount - (int) (discountForAWeek * numberOfFullWeeks)));
        return (int) totalPriceBeforeDiscount - (int) (discountForAWeek * numberOfFullWeeks);
    }
 /*
     * upgrade the Rent to another Car 
     * @param newCar the car to upgrade to 
     * @return the difference in price
    
     */
    public int upgrade(Car newCar) {
        if (newCar.better(this.car)){
            System.out.println(this.car);
            System.out.println("NEW ::"+ newCar);
            int tempPrice= getPrice();
            setCar(new Car(newCar));
            return getPrice()-tempPrice;
        }
        else return 0;
    }
 /*
        * checking if there is an overlap in the rental dates 
     * @param Rent the other rent
     * @return new Rent with the overlaping dates 
    
     */
    public Rent overlap(Rent other) {
        if (!this._name.equals(other._name) || !this.car.equals(other.car)) {
            return null;
        }
        Rent rent = new Rent(this);
        rent.setCar(new Car(this.car));
        rent.setPickDate(new Date(getEarlier(this._pickDate, other._pickDate)));
        rent.setReturnDate(new Date(getLater(this._returnDate, other._returnDate)));
        System.out.println(this);
        if (this._pickDate.after(other._returnDate) ) {

            return null;
        }
        if (_pickDate.after(other._pickDate) && _pickDate.before(other._returnDate)) {
            return rent;
        }
        if (_returnDate.after(other._pickDate) && _pickDate.before(other._returnDate)) {
            return rent;
        }
        if (_pickDate.equals(other._pickDate) && _returnDate.equals(other._returnDate)) {
            return rent;
        }
        if (_pickDate.equals(other._returnDate) || _returnDate.equals(other._pickDate)) {
            return rent;
        }

        return null;
    }

 /*
     * gets the earlier date 
     * @param Date a
     * @param Date b
     * @return  the earlier date
    
     */
    public Date getEarlier(Date a, Date b) {
        if (a.before(b)) {
            return a;
        } else return b;
    }
 /*
     * gets the later date 
     * @param Date a
     * @param Date b
     * @return the later Date
    
     */
    public Date getLater(Date a, Date b) {
        if (a.after(b)) {
            return a;
        } else return b;
    }
 /*
     *  gets the String value of the Rent
     * @return String value of the rental
    
     */
    @Override
    public String toString() {
        return "Name:" + _name  + " "+
                "From:" + _pickDate +" "+
                "To:" + _returnDate +" "+
                "Type:" + car.getType() +" "+
                "Days:" + _pickDate.difference(_returnDate) +" "+
                "Price:"+ getPrice();
    }
}
