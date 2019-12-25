package dbexample;

import java.sql.*;

/* Maven dependency
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.9</version>
</dependency>
 */

public class Main {

    public static void main(String[] args) {
        new Main().run1();
    }

    private void run() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/example", "postgres", "123")) {
            PreparedStatement ps = connection.prepareStatement("update example.example.customer set sum = ? where sum < ?");
            ps.setInt(2, 1000);
            ps.setInt(1, 500);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void run3() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/example", "postgres", "123")) {
            PreparedStatement ps = connection.prepareStatement("delete from example.example.customer where sum = ?");
            ps.setInt(1, 0);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void run2() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/example", "postgres", "123")) {
            PreparedStatement ps = connection.prepareStatement("insert into example.customer (name, sum, card_number) values (?,?,?)");
            ps.setString(1, "Alexeev");
            ps.setInt(2, 1000);
            ps.setInt(3, 1256);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void run1() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/example", "postgres", "123");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from example.customer");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int sum = rs.getInt("sum");
                int cardNumber = rs.getInt("card_number");
                System.out.println(id + " : " + name + " : " + sum + " : " + cardNumber);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
