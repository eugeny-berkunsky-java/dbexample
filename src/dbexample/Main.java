package dbexample;

import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.Scanner;

/* Maven dependency
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.2.9</version>
</dependency>
 */

public class Main {

    public static void main(String[] args) {
        new Main().run3();
    }

    private void run() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/example", "postgres", "123")) {
            PreparedStatement ps = connection.prepareStatement("insert into example.example.vendor (title, country_id) values (?,?)");
            Scanner in = new Scanner(System.in);
            String name = in.nextLine();
            ps.setString(1, name);
            ps.setInt(2, 5);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void run5() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/example", "postgres", "123")) {
            PreparedStatement ps = connection.prepareStatement("insert into example.example.country (name) values (?)");
            Scanner in = new Scanner(System.in);
            String name = in.nextLine();
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void run4() {
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
            PreparedStatement ps = connection.prepareStatement("delete from example.example.country where id = ?");
            ps.setInt(1, 5);
            ps.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("Ошибка внешнего ключа");
            System.out.println(e.getMessage());
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
