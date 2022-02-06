package CHAT;
            import java.sql.*;
            public class RemoveUser {
                public void pullOut(String name) {
                    Connection con = null;
                    PreparedStatement preparedStatement = null;
                    String conString = "jdbc:ucanaccess://C:\\Users\\ADMIN\\Documents\\TEST.accdb";
            
                    String sql = "Delete from USERS where NAME=?";
            
                        try {
                            //GET COONECTION
                            con = DriverManager.getConnection(conString);
                            // PREPARED STMT
                            preparedStatement = con.prepareStatement(sql);
                            preparedStatement.setString(1, name);
                            preparedStatement.executeUpdate();
                            con.commit();
                            con.close();
                        } catch (Exception e) {
                        }
                    }
                }
