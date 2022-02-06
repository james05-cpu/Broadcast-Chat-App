            package CHAT;
            import java.sql.*;
            public class makeTable {
                Connection con = null;
                PreparedStatement preparedStatement = null;
                String conString = "jdbc:ucanaccess://C:\\Users\\ADMIN\\Documents\\TEST.accdb";
            
                String sql =
                        "CREATE TABLE MESSAGES("
                        +"NAME VARCHAR(45) NOT NULL,"
                        +"TEXT VARCHAR(45) NOT NULL,"+
                        "PRIMARY KEY(NAME)"+
                        ");";
                String sq="DROP TABLE USERS";
                        public void baseManage(){
                            try {
                                con = DriverManager.getConnection(conString);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                Statement statement = con.createStatement();
            statement.executeUpdate(sql);
            con.commit();
            con.close();
            
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
            }
