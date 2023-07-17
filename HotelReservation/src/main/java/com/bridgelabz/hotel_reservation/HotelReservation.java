package com.bridgelabz.hotel_reservation;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Hotel{
    String name;
    private int rating;
    private List<Integer> regularRates;

    public Hotel(String name, int rating, List<Integer>regularRates){
        this.name=name;
        this.rating=rating;
        this.regularRates=regularRates;
    }
    public String getName(){
        return name;
    }
    public int getRating(){
        return rating;
    }

    public int getRateForTheDay(DayOfWeek dayOfWeek){
        int index = dayOfWeek.getValue()-1;
        return regularRates.get(index);
    }
}

 class HotelReservationData {
    private List<Hotel> hotels;
    public HotelReservationData(){
        hotels = new ArrayList<>();
    }
    public void addHotel(String name, int rating, List<Integer> regularRates){
        Hotel hotel = new Hotel(name,rating,regularRates);
        hotels.add(hotel);
    }
    public String findCheapestHotel(String customerType, String... dates){
        int minCost = Integer.MAX_VALUE;
        int maxRating = Integer.MIN_VALUE;
        String cheapestHotel = "";

        for(Hotel hotel : hotels){
            String hotelName = hotel.getName();

            int totalCost=0;
            for(String date: dates){
                DayOfWeek dayOfWeek =parseDate(date);
                int rate = (customerType.equalsIgnoreCase("rewards"))?hotel.getRateForTheDay(dayOfWeek):hotel.getRateForTheDay(dayOfWeek);
                totalCost +=rate;
            }
            if(totalCost<minCost||(totalCost== minCost && hotel.getRating()>maxRating)){
                minCost=totalCost;
                maxRating=hotel.getRating();
                cheapestHotel=hotelName;

            }
        }

        return cheapestHotel;
    }
    private DayOfWeek parseDate(String date){
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dMMMyyyy(E)");
        LocalDate localDate=LocalDate.parse(date, formatter);
        return localDate.getDayOfWeek();
    }
}
public class HotelReservation{
    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Reservation Program!");
        HotelReservationData reservationData = new HotelReservationData();

        //Adding hotel 1
        List<Integer> lakewoodRates = new ArrayList<>();
        lakewoodRates.add(110);//weekday
        lakewoodRates.add(110);
        lakewoodRates.add(110);

        lakewoodRates.add(90);//weekends
        lakewoodRates.add(90);
        reservationData.addHotel("Lakewood",3,lakewoodRates);

        //Adding hotel 2
        List<Integer> bridgewoodRates = new ArrayList<>();
        bridgewoodRates.add(160);//weekdays
        bridgewoodRates.add(160);
        bridgewoodRates.add(160);
        bridgewoodRates.add(160);
        bridgewoodRates.add(60);//weekends
        bridgewoodRates.add(60);
        reservationData.addHotel("Bridgewood",4,bridgewoodRates);

        //Adding hotel 3
        List<Integer> ridgewoodRates = new ArrayList<>();
        ridgewoodRates.add(220);//weekdays
        ridgewoodRates.add(220);
        ridgewoodRates.add(220);

        ridgewoodRates.add(150);//weekends
        ridgewoodRates.add(150);
        reservationData.addHotel("Ridgewood",5,ridgewoodRates);


        //Test
        String customerType1 = "Regular";
        String[]dates1={"16Mar2020(mon)","17Mar2020(tues)","16Mar2020(wed)"};
        String cheapestHotel1 = reservationData.findCheapestHotel(customerType1,dates1);
        System.out.println("Cheapest hotel for "+customerType1+": "+cheapestHotel1);

    }
}
