package w3.day03.ws;

import java.sql.*;
import java.util.*;

public class ClothesManagerImpl implements ClothesManager {
    private ClothesManagerImpl() {}
    private static ClothesManagerImpl instance = new ClothesManagerImpl();
    public static ClothesManagerImpl getInstance() {
        return instance;
    }

    private static String SQL = null;
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "3787");
    }

    // 4.1 insert/update/delete : int
    // 4.2 select : ResultSet

    // 옷 추가
    @Override
    public void addClothes(Clothes clothes) {
        SQL = "INSERT INTO clothes (id, name, price, category) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, clothes.getId());
            pstmt.setString(2, clothes.getName());
            pstmt.setInt(3, clothes.getPrice());
            pstmt.setString(4, clothes.getCategory());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 재고 추가
    @Override
    public void addStock(Stock stock) {
        SQL = "INSERT INTO stock (id, size, stock) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE stock = stock + VALUES(stock)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, stock.getId());
            pstmt.setString(2, stock.getSize());
            pstmt.setInt(3, stock.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 전체 옷 목록 조회
    @Override
    public Clothes[] getList() {
        List<Clothes> list = new ArrayList<>();
        SQL = "SELECT * FROM clothes";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String category = rs.getString("category");
                list.add(new Clothes(id, name, price, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Clothes[0]);
    }

    // 이름으로 검색
    @Override
    public Clothes[] searchClothesByName(String name) {
        List<Clothes> list = new ArrayList<>();
        SQL = "SELECT * FROM clothes WHERE name LIKE ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String cname = rs.getString("name");
                    int price = rs.getInt("price");
                    String category = rs.getString("category");
                    list.add(new Clothes(id, cname, price, category));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Clothes[0]);
    }

    // 카테고리로 검색
    @Override
    public Clothes[] searchClothesByCategory(String category) {
        List<Clothes> list = new ArrayList<>();
        SQL = "SELECT * FROM clothes WHERE category = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, category);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    String ccategory = rs.getString("category");
                    list.add(new Clothes(id, name, price, ccategory));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Clothes[0]);
    }

    // 가격으로 검색
    @Override
    public Clothes[] searchClothesByPrice(int price) {
        List<Clothes> list = new ArrayList<>();
        SQL = "SELECT * FROM clothes WHERE price = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, price);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int cprice = rs.getInt("price");
                    String category = rs.getString("category");
                    list.add(new Clothes(id, name, cprice, category));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Clothes[0]);
    }

    // 옷 삭제
    @Override
    public void deleteClothes(int id) {
        SQL = "DELETE FROM clothes WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 옷 판매 (재고 마이너스)
    @Override
    public void sell(Stock stock) {
        SQL = "UPDATE stock SET stock = stock - ? WHERE id = ? and size = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, stock.getStock());
            pstmt.setInt(2, stock.getId());
            pstmt.setInt(3, Integer.parseInt(stock.getSize()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 옷 정보 수정
    @Override
    public void updateClothes(Clothes clothes) {
        SQL = "UPDATE clothes SET name = ?, price = ?, category = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, clothes.getName());
            pstmt.setInt(2, clothes.getPrice());
            pstmt.setString(3, clothes.getCategory());
            pstmt.setInt(4, clothes.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 재고 가져오기
    @Override
    public Stock getStock(int id, String size) {
        SQL = "SELECT * FROM stock WHERE id = ? AND size = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, size);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int stockQuantity = rs.getInt("stock");
                    return new Stock(id, size, stockQuantity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}