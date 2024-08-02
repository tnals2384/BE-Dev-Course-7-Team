package day0731.clothes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothesService implements IClothesManager{
    private Connection con;

    public ClothesService() {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "369369a@");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void addClothes(Clothes clothes) {
        String sql = "insert into clothes (name, price, category) values(?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, clothes.getName());
            pstmt.setInt(2, clothes.getPrice());
            pstmt.setString(3, clothes.getCategory());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addStock(Stock stock) {
        String sql = "insert into stocks (size, stock) values(?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, stock.getSize());
            pstmt.setInt(2, stock.getStock());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Clothes[] getList() {
        String sql = "select * from clothes";
        List<Clothes> arr = new ArrayList<>();
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String category = rs.getString("category");

                Clothes clothes = new Clothes(id, name, price, category);
                arr.add(clothes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arr.toArray(new Clothes[0]);
    }

    @Override
    public Clothes[] searchClothesByName(String name) {
        String sql = "select * from clothes where name like ?";
        List<Clothes> arr = new ArrayList<>();

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameResult = rs.getString("name");
                int price = rs.getInt("price");
                String category = rs.getString("category");

                Clothes clothes = new Clothes(id, nameResult, price, category);
                arr.add(clothes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arr.toArray(new Clothes[0]);
    }

    @Override
    public Clothes[] searchClothesByCategory(String category) {
        String sql = "select * from clothes where name category ?";
        List<Clothes> arr = new ArrayList<>();

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + category + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String categoryResult = rs.getString("category");

                Clothes clothes = new Clothes(id, name, price, categoryResult);
                arr.add(clothes);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arr.toArray(new Clothes[0]);
    }

    @Override
    public Clothes[] searchClothesByPrice(int price) {
        String sql = "select * from clothes where name price ?";
        List<Clothes> arr = new ArrayList<>();

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + price + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int priceResult = rs.getInt("price");
                String category = rs.getString("category");

                Clothes clothes = new Clothes(id, name, priceResult, category);
                arr.add(clothes);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arr.toArray(new Clothes[0]);
    }


    @Override
    public void deleteClothes(int id) {
        String sql = "delete from clothes where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sell(Stock stock) { // 여기 다시 하던가
        String sql = "update stock set stock = stock - ? where id =? and size = ?"; // update - set 사용
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, stock.getStock());
            pstmt.setInt(2, stock.getId());
            pstmt.setString(3, stock.getSize());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Stock getStock(int id, String size) {
        String sql = "select * from where id = ? and size = ?";
        Stock stock = null;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, size);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int stockNum = rs.getInt("stock");
                    stock = new Stock(id, size, stockNum);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }

    @Override
    public void updateClothes(Clothes clothes) {
        String sql = "update clothes set name = ?, price = ?, category = ?, where id = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, clothes.getName());
            pstmt.setInt(2, clothes.getPrice());
            pstmt.setString(3, clothes.getCategory());
            pstmt.setInt(4, clothes.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
