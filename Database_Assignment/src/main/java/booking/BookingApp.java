package booking;

import java.sql.*;

public class BookingApp {


    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String url ="jdbc:postgresql://localhost:5432/booking_db";
            String user="root";
            String password=args[0];

            Connection db=DriverManager.getConnection(url,user,password);

            Statement statement=db.createStatement();
            ResultSet priceResult= statement.executeQuery(
                    "SELECT a.type, rf.value, rf.season " +
                         "FROM accommodation a " +
                         "JOIN accommodation_room_fair_relation arfr ON a.id = arfr.accommodation_id " +
                         "JOIN room_fair rf ON arfr.room_fair_id = rf.id");
            while(priceResult.next()){
                String type=priceResult.getString(1);
                int value=priceResult.getInt(2);
                String season=priceResult.getString(3);
                System.out.printf("Type: %s, Price: %.2f, Season: %s%n", type, value, season);
            }

            db.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}



