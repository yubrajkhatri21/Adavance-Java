
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
    private Connection conn;
    public DBConnect() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/vs","root", "");
                String sql = "create table if not exists userdata"
                    +"(id int primary key auto_increment,"
                    +"name varchar(30),"
                    +"email varchar(30),"
                    +"height decimal(10,2))";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                System.out.println("Table created sucessfully");
    }
    public void createUser(UserValues user, DBCallback callback) throws SQLException {
        String sql = "insert into userdata (name,email,height) values(?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setFloat(3, user.getHeight());
        int check = stmt.executeUpdate();

        if(check != -1) {
            callback.dbSucess();
        } else {
            callback.dbError("Unable to insert data, please check the query");
        }
    }
    public ResultSet retrieveUsers() throws SQLException{
        String sql = "select * from userdata";
        Statement stmt= conn.createStatement();
        return stmt.executeQuery(sql);
    
    }
    public ResultSet retrieveMetaData() throws Exception{
        String sql= "select * from userdata";
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return stmt.executeQuery(sql);
    }
}
