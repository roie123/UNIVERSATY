
/**
 * MAMAN 12 - Using a class to represent a given date in the Gregorian Calendar
 *
 * @author Roie Ivri
 * @version 2022
 */
public class Date {
    private final int MIN_DAY_VALUE = 1;
    private final int MAX_DAY_VALUE = 31;
    private final int MAX_YEAR_VALUE = 9999;
    private final int MIN_YEAR_VALUE = 1;
    private final int MAX_MONTH_VALUE = 12;
    private final int MIN_MONTH_VALUE = 1;

    private int _day = 1;
    private int _month = 1;
    private int _year = 2000;


    //constructors:
    /*
     * creates a new Date object
     * @param _day the day in the month(1-31)
     * @param _month the month in the year
     * @param _year the year (in 4 digits)
     */
    public Date(int _day, int _month, int _year) {
        if (checkIfDayIsValid(_day, _month, _year) && checkIfMonthIsValid(_month) && checkIfYearIsValid(_year)) {
            this._day = _day;
            this._month = _month;
            this._year = _year;

        }

    }

    /*
    Copy Constructor
     */
    public Date(Date other) {
        this._day = other.getDay();
        this._month = other.getMonth();
        this._year = other.getYear();

    }

    /**
     * gets the Day
     */

    public int getDay() {
        return _day;
    }

    /**
     * Sets the Day
     */

    public void setDay(int dayToSet) {
        if (checkIfDayIsValid(dayToSet, this._month, this._year)) {
            this._day = dayToSet;
        }
    }

    /**
     * gets the month
     */

    public int getMonth() {
        return _month;
    }

    /**
     * sets the moth
     */

    public void setMonth(int monthToSet) {
        if (checkIfMonthIsValid(monthToSet)) {
            this._month = monthToSet;

        }
    }

    /**
     * gets the year
     */

    public int getYear() {
        return _year;
    }

    /**
     * sets the year while checking for leap year
     */

    public void setYear(int yearToSet) {
        if (this._day > 28 && this._month == 2) {
            if (checkIfLeapYear(yearToSet)) {
                this._year = yearToSet;
            } else {
                return;

            }
        }
        if (checkIfYearIsValid(yearToSet)) {
            this._year = yearToSet;

        }
    }

    /*
    this method checks if the day is valid using the whole date values
     */
    public boolean checkIfDayIsValid(int day, int monthOfYear, int year) {
        boolean isALeapYear = checkIfLeapYear(year);

        if (day < MIN_DAY_VALUE) {
            return false;
        }
        if (monthOfYear == 1 || monthOfYear == 3 || monthOfYear == 5 || monthOfYear == 7 || monthOfYear == 8 || monthOfYear == 10 || monthOfYear == 12) {
            return day <= 31;
        } else if (monthOfYear == 4 || monthOfYear == 6 || monthOfYear == 9 || monthOfYear == 11) {
            return day <= 30;
        } else if (monthOfYear == 2) {
            if (isALeapYear) {
                return day <= 29;
            } else return day <= 28;

        }
        return false;
    }

    //this method checks for a leap year
    public boolean checkIfLeapYear(int year) {
        boolean leap = false;


        if (year % 4 == 0) {


            if (year % 100 == 0) {


                if (year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            } else
                leap = true;
        } else
            leap = false;

        return leap;
    }
/*
This method checks for the validity of the month , using day value and the year
 */
    public boolean checkIfMonthIsValid(int month) {
        if (month <= MAX_MONTH_VALUE && month >= MIN_MONTH_VALUE) {
            if (this._day <= 28) {
                return true;
            }
            switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> {
                    if (_day <= MAX_DAY_VALUE) {
                        return true;
                    } else return false;

                }
                case 4, 6, 9, 11 -> {
                    if (_day < 31) {
                        return true;
                    } else return false;
                }
                case 2 -> {
                    if (checkIfLeapYear(this._year)) {
                        if (this._day < 30) {
                            return true;
                        }
                    } else if (!checkIfLeapYear(this._year)) {
                        if (this._day < 29) {
                            return true;
                        } else return false;

                    }
                }
            }
        }
        return false;
    }
    /** checking for the validity of the year
     * @param _year the given year to check
     * @return  true if the year is valid
     * */

    public boolean checkIfYearIsValid(int _year) {

        return _year >= MIN_YEAR_VALUE && _year <= MAX_YEAR_VALUE;
    }
    /** checking if the two objects has the same values
     * @param o the given Date object to check
     * @return  true if the Dates are the same
     * */

    public boolean equals(Date o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return _day == date._day && _month == date._month && _year == date._year;
    }

    /** checking if this date is before the other Date object
     * @param other the given Date object to check
     * @return  true if this date is before the other date
     * */
    public boolean before(Date other) {
        if (this.equals(other)) {
            return false;
        }
        if (this._year < other.getYear()) {
            return true;
        } else if (this._year > other._year) {
            return false;
        }

        if (this._month < other._month) {
            return true;
        } else if (this._month > other._month) {
            return false;
        }

        return this._day < other._day;


    }
    /** checking if this date is after the other Date object
     * @param other the given Date object to check
     * @return  true if this date is after  the other date
     * */
    public boolean after(Date other) {
        if (this.difference(other) == 0) {
            return false;
        } else
            return !before(other);
    }

    /** calculates the diffrence in number of Days
     * @param other the given Date object
     * @return  int , the number of days between the two Dates
     * */
    public int difference(Date other) {
        int temp = calculateDate(this._day, this._month, this._year) - calculateDate(other._day, other._month, other._year);
        return Math.abs(temp);

    }
    /**  calculates the  number of Days
     * @param day the given Day
     * @param month the given Month
     * @param year the given Year
     * @return  int , the number of days passed until this given  date
     * */
    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    /**  format the day for the string representation
     * @return  the string value of the Day as per the specification
     * */
    private String getStringValueOfDay() {
        String dayInString = String.valueOf(_day);
        if (_day < 10) {
            dayInString = "0" + dayInString;
        }
        return dayInString;
    }
    /**  format the month for the string representation
     * @return  the string value of the Month as per the specification
     * */
    private String getStringValueOfMonth() {
        String monthInString = String.valueOf(_month);
        if (_month < 10) {
            monthInString = "0" + monthInString;
        }
        return monthInString;
    }
    /**  the string value of the Object
     * @return  the string value of the date as per the specification
     * */
    @Override
    public String toString() {
        return getStringValueOfDay() + "/" + getStringValueOfMonth() + "/" + _year;
    }

    /**  This method returns a new date of the next day of the year
     * @return  new Date , the values are +1 DAY
     * */
    public Date tomorrow() {
        Date date = new Date(this);
        boolean isLeapYear = checkIfLeapYear(this._year);
        if (date.getDay() < 28) {
            date.setDay(_day + 1);
            return date;
        }
        switch (_month) {
            case 1, 3, 7, 8, 10, 12: {
                if (_day == 31) {
                    date._day = 1;
                    if (_month == 12) {

                        date.setMonth(1);
                        System.out.println(date.getMonth());
                        date.setYear(date.getYear() + 1);
                        date.setDay(1);

                        return date;
                    }
                    date.setMonth(date.getMonth() + 1);
                } else date.setDay(date.getDay() + 1);
            }
            case 4, 6, 9, 11: {
                if (_day == 30) {
                    date._day = 1;
                    if (_month == 12) {
                        date.setMonth(1);
                        date.setYear(date.getYear() + 1);
                        return date;
                    }
                    date.setMonth(date.getMonth() + 1);
                    return date;
                } else date.setDay(date.getDay() + 1);
            }
            case 2: {
                if (isLeapYear && date.getDay() < 29) {
                    date.setDay(date.getDay() + 1);
                    return date;
                }
                if (isLeapYear && date.getDay() == 29) {
                    date.setDay(1);
                    date.setMonth(date.getMonth() + 1);
                    return date;
                }
                if (date.getDay() < 28) {
                    date.setDay(date.getDay() + 1);
                    return date;
                }
                if (date.getDay() == 28 && !isLeapYear) {
                    date.setDay(1);
                    date.setMonth(date.getMonth() + 1);
                    return date;
                }

            }
        }
        return null;
    }
}
