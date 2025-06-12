package ke.co.minet.fusion.service;

import java.sql.*;

public class CallsReport {

    static String sqlString;


    public static void main(String[] args) {

        outBoundCalls();

    }

    static int startHour = 0;

    // start at 00:00:00 hrs
    // end at 23:00:00 hrs
    public static void offeredMedicalcallsPerHour(){

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
                        sqlString = "select count(*) from asteriskcdrdb.cdr where dst = '603' and calldate between '2025-05-" + date + " 23:00:00' and '2025-05-0" + nextDay + " 00:00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    } else {
                        sqlString = "select count(*) from asteriskcdrdb.cdr where dst = '603' and calldate between '2025-05-" + date + " 23:00:00' and '2025-05-" + nextDay + " 00:00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    }
                } else {
                    if(hour <= 10){
                        sqlString = "select count(*) from asteriskcdrdb.cdr where dst = '603' and calldate between '2025-05-" + date + " 0" + i + ":00:00' and '2025-05-" + date + " 0" + hour +":00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    } else {
                        sqlString = "select count(*) from asteriskcdrdb.cdr where dst = '603' and calldate between '2025-05-" + date + " "+i + ":00:00' and '2025-05-" + date + " " + hour +":00:00';";
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


    public static void answeredMedicalCallsPerHour(){
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
                        sqlString = "select count(*) from asteriskcdrdb.cdr where dst = '603' and disposition = 'ANSWERED' and calldate between '2025-05-" + date + " 23:00:00' and '2025-05-0" + nextDay + " 00:00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    } else {
                        sqlString = "select count(*) from asteriskcdrdb.cdr where dst = '603' and disposition = 'ANSWERED' and calldate between '2025-05-" + date + " 23:00:00' and '2025-05-" + nextDay + " 00:00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    }
                } else {
                    if(hour <= 10){
                        sqlString = "select count(*) from asteriskcdrdb.cdr where dst = '603' and disposition = 'ANSWERED' and calldate between '2025-05-" + date + " 0" + i + ":00:00' and '2025-05-" + date + " 0" + hour +":00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    } else {
                        sqlString = "select count(*) from asteriskcdrdb.cdr where dst = '603' and disposition = 'ANSWERED' and calldate between '2025-05-" + date + " "+i + ":00:00' and '2025-05-" + date + " " + hour +":00:00';";
                        int count = dbConnection(sqlString);
                        System.out.println(count);
                    }
                }
            }
            System.out.println("------------------------------------------------------");
        }
    }

    public static void outBoundCalls(){
        for (int day = 1; day <= 31; day++) {
            System.out.println("day" + day);
            if(day < 10){
                sqlString = "select count(*) from  asteriskcdrdb.cdr where src in \n" +
                        "('4014','4015','4018','4019','4019','4020','4022','4022','5007','5008','5021','5032','5164','5164','6003','6004','6022', '254719044544') \n" +
                        "and date(calldate) = '2025-04-0" + day + "'";
                int count = dbConnection(sqlString);
                System.out.println(count);
            } else {
                sqlString = "select count(*) from  asteriskcdrdb.cdr where src in \n" +
                        "('4014','4015','4018','4019','4019','4020','4022','4022','5007','5008','5021','5032','5164','5164','6003','6004','6022', '254719044544') \n" +
                        "and date(calldate) = '2025-04-" + day + "'";
                int count = dbConnection(sqlString);
                System.out.println(count);
            }
        }

    }



}
