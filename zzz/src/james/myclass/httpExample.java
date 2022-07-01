// package james.myclass;

// import org.json.JSONArray;
// import org.json.JSONObject;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.net.URL;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

// public class httpExample {
//     public static PreparedStatement pst;
//     public static void main(String[] args) {
//         try {
//             Class.forName("com.mysql.cj.jdbc.Driver");
//         } catch (ClassNotFoundException e) {
//             System.out.println(e.toString());
//         }

//         try {
//             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
//                     "iii?user=root&password=");
//             pst = con.prepareStatement("INSERT INTO foods(name,tel,address) VALUES(?,?,?)");
//             //con.close();
//         } catch (SQLException e) {
//             System.out.println(e.toString());
//         }

//         try {
//             URL url = new URL("https://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx");
//             HttpURLConnection conn = (HttpURLConnection)url.openConnection();

//             BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//             StringBuffer sb = new StringBuffer();

//             String s;
//             while((s = bf.readLine()) != null) {
//                 sb.append(s);
//             }
//             bf.close();
//             conn.disconnect();
//             parseJson(sb.toString());

//         } catch (Exception e) {
//             System.out.println(e.toString());
//         }
//     }

//     private static void parseJson(String Json) {
//         try {
//             JSONArray root = new JSONArray(Json);
//             System.out.println(root.length());
//             for (int i=0;i<root.length();i++) {
//                 JSONObject row = root.getJSONObject(i);
//                 String s1 = row.getString("Name");
//                 String s2 = row.getString("Address");
//                 String s3 = row.getString("Tel");
//                 useSQL(s1, s2, s3);
//             }
//         }
//         catch (Exception e) {
//             System.out.println(e.toString());
//         }

//     }
//     private static void useSQL(String s1, String s2, String s3) {
//         try {
//             pst.setString(1,s1);
//             pst.setString(2,s2);
//             pst.setString(3,s3);
//             pst.executeUpdate();
//         } catch (SQLException e) {
//             System.out.println(e.toString());
//         }


//     }

// }
