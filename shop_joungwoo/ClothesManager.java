package shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothesManager implements IClothesManager {
    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USER = "root";
    private static final String PASSWORD = "0922";

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Override
    public void addClothes(Clothes clothes) {
        String sql = "INSERT INTO clothes(id, name, price, category) VALUES(?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clothes.getId());
            pstmt.setString(2, clothes.getName());
            pstmt.setInt(3, clothes.getPrice());
            pstmt.setString(4, clothes.getCategory());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addStock(Stock stock) {
        String sql = "INSERT INTO stocks(id, size, stock) VALUES(?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stock.getId());
            pstmt.setString(2, stock.getSize());
            pstmt.setInt(3, stock.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Clothes[] getList() {
        String sql = "SELECT * FROM clothes";
        List<Clothes> list = new ArrayList<>();
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Clothes clothes = new Clothes(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"));
                list.add(clothes);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.toArray(Clothes[]::new);
    }

    @Override
    public Clothes[] searchClothesByName(String name) {
        String sql = "SELECT * FROM clothes WHERE name LIKE ?";
        List<Clothes> list = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Clothes clothes = new Clothes(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"));
                list.add(clothes);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.toArray(Clothes[]::new);
    }

    @Override
    public Clothes[] searchClothesByCategory(String category) {
        String sql = "SELECT * FROM clothes WHERE category LIKE ?";
        List<Clothes> list = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + category + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Clothes clothes = new Clothes(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"));
                list.add(clothes);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.toArray(Clothes[]::new);
    }

    @Override
    public Clothes[] searchClothesByPrice(int price) {
        String sql = "SELECT * FROM clothes WHERE price = ?";
        List<Clothes> list = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, price);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Clothes clothes = new Clothes(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"));
                list.add(clothes);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.toArray(Clothes[]::new);
    }

    @Override
    public void deleteClothes(int id) {
        String sql = "DELETE FROM clothes WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void sell(Stock stock) {
        String sql = "UPDATE stocks SET stock = stock - ? WHERE id = ? AND size = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stock.getStock());
            pstmt.setInt(2, stock.getId());
            pstmt.setString(3, stock.getSize());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Stock getStock(int id, String size) {
        String sql = "SELECT * FROM stocks WHERE id = ? AND size = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, size);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Stock(rs.getInt("id"), rs.getString("size"), rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateClothes(Clothes clothes) {
        String sql = "UPDATE clothes SET name = ?, price = ?, category = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, clothes.getName());
            pstmt.setInt(2, clothes.getPrice());
            pstmt.setString(3, clothes.getCategory());
            pstmt.setInt(4, clothes.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
