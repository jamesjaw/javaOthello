package james.myclass;

import java.sql.*;

public class javaToMysql {
    public static void main(String[] args) {







        //1.load drive
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.create connection
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
                    "iii?user=root&password=");

            //3.sql statement

            //4.execute sql statement


            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
