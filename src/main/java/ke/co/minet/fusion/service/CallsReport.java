package ke.co.minet.fusion.service;

import java.sql.*;

public class CallsReport {

    static String sqlString;


    public static void main(String[] args) {
        callsPerHour();
    }

    static int startHour = 0;

    // start at 00:00:00 hrs
    // end at 23:00:00 hrs
    public static void callsPerHour(){

        for (int day = 1; day <= 31; day++) {
            System.out.println("-------------------------------------------------------");
            System.out.println(" day: " + day);

            String date = (day < 10) ? "0" + day : day + "";

            for(int i = startHour; i < 24; i++){
                int hour = i + 1;
                if(hour == 24){
                    hour = 0;
                    int nextDay = Integer.parseInt(date) + 1;
                    if(nextDay < 10){
                        sqlString = "select count(*) from asteriskcdrdb.cdr where calldate between '2025-03-" + date + " 23:00:00' and '2025-03-0" + hour + " 00:00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    } else {
                        sqlString = "select count(*) from asteriskcdrdb.cdr where calldate between '2025-03-" + date + " 23:00:00' and '2025-03-" + nextDay + " 00:00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    }
                } else {
                    if(hour <= 10){
                        sqlString = "select count(*) from asteriskcdrdb.cdr where calldate between '2025-03-" + date + " 0" + i + ":00:00' and '2025-03-" + date + " 0" + hour +":00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    } else {
                        sqlString = "select count(*) from asteriskcdrdb.cdr where calldate between '2025-03-" + date + " "+i + ":00:00' and '2025-03-" + date + " " + hour +":00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    }
                }
            }
            System.out.println("------------------------------------------------------");
        }

    }

    //static String sql = "select count(*) from asteriskcdrdb.cdr;";
    public static int dbConnection(String sql){
        try (Connection con = DriverManager.getConnection("jdbc:mysql://10.39.60.25:3306/asteriskcdrdb", "Minet_API", "Password1");
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            rs.next();
            //System.out.println(rs.getString(1));
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
