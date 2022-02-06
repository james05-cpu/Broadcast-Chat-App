            package CHAT;
            import java.sql.*;
            public class DataRepo {
            
                    Connection con = null;
                    PreparedStatement preparedStatement = null;
            
                    String conString = "jdbc:ucanaccess://C:\\Users\\ADMIN\\Documents\\TEST.accdb";
            
                    String sql = "INSERT INTO USERS values(?,?)";
                public void collectData( String name){
            
                    try {
                        //GET COONECTION
                       con = DriverManager.getConnection(conString);
                        Statement statement=con.createStatement();
                        // PREPARED STMT
                        preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, "Client");
                        preparedStatement.setString(2, name);
                        preparedStatement.execute();
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
