public class Main {
    public static void main(String[] args) {
    int a = 0000001;
        System.out.println(a);
Car car = new Car(9878686,'A',"Brand",true);
Car car1 = new Car(9878686,'B',"Brand",false);
        System.out.println(car.better(car1));
        System.out.println(car.worse(car1));
        System.out.println(car);

        Date date = new Date(1,12,2005);
        Date date1 = new Date(10,12,2005);

        Date date2 = new Date(1,12,2005);
        Date date3 = new Date(5,12,2005);
        Car car2 = new Car(9878636,'C',"Brand2",true);
        System.out.println(date1.difference(date));
        System.out.println(date);


        System.out.println("**********************");
        Rent rent = new Rent("FUCKER" ,car,date,date1);
        System.out.println(rent);
        System.out.println(rent.getPrice());
        System.out.println(rent.upgrade(car1));
        Rent rent1 = new Rent("FUCKER" ,car,date2,date3 );
        System.out.println(rent.overlap(rent));
    }

}