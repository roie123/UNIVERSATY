import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Rent {
    private final int DISCOUNT_NUMBER_OF_DAYS = 7;
    private final double DISCOUNT_VALUE = 0.10;
    private String _name;
    private Car car;
    private Date _pickDate;
    private Date _returnDate;


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

    public Rent(Rent other) {

        this.car = other.car;
        this._name = other._name;
        this._pickDate = other._pickDate;
        this._returnDate = other._returnDate;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getPickDate() {
        return _pickDate;
    }

    public void setPickDate(Date _pickDate) {
        if (_pickDate.before(this._returnDate) && _pickDate.difference(_returnDate) > 1) {
            this._pickDate = _pickDate;

        }
    }

    public Date getReturnDate() {
        return _returnDate;
    }

    public void setReturnDate(Date _returnDate) {
        if (_returnDate.after(this._pickDate) && _returnDate.difference(this._pickDate) > 1) {
            this._returnDate = _returnDate;

        }
    }

    public boolean isDatesValid(Date returnDate, Date pickDate) {
        return pickDate.before(returnDate) && returnDate.difference(pickDate) >= 1;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return Objects.equals(_name, rent._name) && Objects.equals(car, rent.car) && Objects.equals(_pickDate, rent._pickDate) && Objects.equals(_returnDate, rent._returnDate);
    }

    public int howManyDays() {
        return this._pickDate.difference(this._returnDate);
    }

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


    public Date getEarlier(Date a, Date b) {
        if (a.before(b)) {
            return a;
        } else return b;
    }

    public Date getLater(Date a, Date b) {
        if (a.after(b)) {
            return a;
        } else return b;
    }

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
