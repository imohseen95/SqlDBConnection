package Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectSqlDB {
    public static Connection connect;
    public static PreparedStatement ps;

    public static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream ism = new FileInputStream("src/secret.properties");
        properties.load(ism);
        ism.close();
        return properties;

    }

    public static Connection connectDB() throws IOException, ClassNotFoundException, SQLException {
        Properties prop = loadProperties();
        String driver = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String userName = prop.getProperty("MYSQLJDBC.username");
        String password = prop.getProperty("MYSQLJDBC.password");
        Class.forName(driver);
        connect = DriverManager.getConnection(url, userName, password);
        return connect;
    }


    public static List<student> readStudentProfile(){
        List<student> list = new ArrayList<student>();
        student student = null;
        try{
            Connection connection = connectDB();
            String query = "Select * from student";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String stID = resultSet.getString("stID");
                String stName = resultSet.getString("stName");
                String stDOB = resultSet.getString("stDOB");
                student = new student(stID,stName,stDOB);
                list.add(student);
            }

        } catch (Exception ex){
        }
        return list;
    }

    public static void insertProfileIntoSqlTable(student student, String tableName,
                                                 String columnName1, String columnName2, String columnName3){
        try{
            connect = connectDB();
            ps = connect.prepareStatement("Insert Into "+tableName+"("+columnName1+","+columnName2+","+columnName3+
                    ") Values(?,?,?)");
            ps.setString(1,student.getStID());
            ps.setString(2,student.getStName());
            ps.setString(3,student.getStDOB());
            ps.execute();

        }catch (IOException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        insertProfileIntoSqlTable(new student("7","Danial","09-14-1999"),"student",
               "stID","stName","stDOB");

        List<student> list = readStudentProfile();
            for(student st:list){
                System.out.println(st.getStID()+ " "+ st.getStName()+ " "+ st.getStDOB());
            }
        }
    }


