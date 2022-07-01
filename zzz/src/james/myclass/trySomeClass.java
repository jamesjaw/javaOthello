// package james.myclass;


// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.util.Properties;

// import org.json.JSONStringer;
// import org.json.JSONWriter;

// public class trySomeClass {

//     public static void main(String[] args) {
//         Properties prop = new Properties();
//         prop.put("user", "root");prop.put("password", "");

//         String sql = "SELECT * FROM foods ORDER BY id LIMIT 4";
//         try(Connection conn = DriverManager.getConnection(
//                 "jdbc:mysql://localhost:3306/iii", prop)) {
//             PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

//             JSONStringer js = new JSONStringer();
//             JSONWriter jw = js.array();

//             ResultSet rs = ps.executeQuery();

//             rs.last();

//             int z = rs.getRow();
//             System.out.println(z);


//         }catch (Exception e) {
//             System.err.println(e.toString());
//         }
//     }

// }
