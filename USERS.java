package CHAT;
            import java.sql.*;
            public class USERS {
                Connection con = null;
                PreparedStatement preparedStatement = null;
            
                String conString = "jdbc:ucanaccess://C:\\Users\\ADMIN\\Documents\\TEST.accdb";
            
                String sql = "SELECT*from USERS";
            
                public void fetchData() {
            
                    try {
                        //GET COONECT
                        con = DriverManager.getConnection(conString);
                        Statement statement = con.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                        System.out.println("members online");
                        while (resultSet.next()) {
                            String name = resultSet.getString(2);
                            System.out.println(name);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
