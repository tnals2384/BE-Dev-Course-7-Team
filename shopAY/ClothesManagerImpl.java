package day0731.shopAY;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothesManagerImpl implements IClothesManager {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/day0731.test01.shop";
    private static final String USER = "root";
    private static final String PASSWORD = "0210";

    private static Connection conn = null;
    private static String SQL = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void doFinal() {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static IClothesManager instance = new ClothesManagerImpl();

    ClothesManagerImpl() {

    }

    public static IClothesManager getInstance() {
        return instance;
    }

    @Override
    public void addClothes(Clothes clothes) {
        SQL = "insert into clothes(id, name, price, category) values(?,?,?,?)";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, clothes.getId());
            pstmt.setString(2, clothes.getName());
            pstmt.setInt(3, clothes.getPrice());
            pstmt.setString(4, clothes.getCategory());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            doFinal();
        }
    }

    @Override
    public void addStock(Stock stock) {
        SQL = "insert into stocks(id, size, stock) values(?,?,?)";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, stock.getId());
            pstmt.setString(2, stock.getSize());
            pstmt.setInt(3, stock.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            doFinal();
        }
    }

    @Override
    public Clothes[] getList() {
        List<Clothes> clothesList = new ArrayList<>();
        SQL = "select id, name, price, category from clothes";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String category = rs.getString("category");

                Clothes clothes = new Clothes(id, name, price, category);
                clothesList.add(clothes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            doFinal();
        }
        return clothesList.toArray(new Clothes[0]);
    }

    @Override
    public Clothes[] searchClothesByName(String name) {
        List<Clothes> searchList = new ArrayList<>();
        SQL = "select id, name, price, category from clothes where name like '%" + name + "%'";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String resultName = rs.getString("name");
                int price = rs.getInt("price");
                String category = rs.getString("category");

                Clothes clothes = new Clothes(id, resultName, price, category);
                searchList.add(clothes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            doFinal();
        }
        return searchList.toArray(new Clothes[0]);
    }

    @Override
    public Clothes[] searchClothesByCategory(String category) {
        List<Clothes> searchList = new ArrayList<>();
        SQL = "select id, name, price, category from clothes where category like '%" + category + "%'";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String resultCategory = rs.getString("category");

                Clothes clothes = new Clothes(id, name, price, resultCategory);
                searchList.add(clothes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            doFinal();
        }
        return searchList.toArray(new Clothes[0]);
    }

    @Override
    public Clothes[] searchClothesByPrice(int price) {
        List<Clothes> searchList = new ArrayList<>();
        SQL = "select id, name, price, category from clothes where price <= " + price;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int resultPrice = rs.getInt("price");
                String category = rs.getString("category");

                Clothes clothes = new Clothes(id, name, resultPrice, category);
                searchList.add(clothes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            doFinal();
        }
        return searchList.toArray(new Clothes[0]);
    }

    @Override
    public void sell(Stock stock) {
        SQL = "update stocks set stock = ? where id = ? and size = ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, stock.getStock());
            pstmt.setInt(2, stock.getId());
            pstmt.setString(3, stock.getSize());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            doFinal();
        }
    }

    @Override
    public void deleteClothes(int id) {
        SQL = "delete from clothes where id =" + id;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            doFinal();
        }
    }


    @Override
    public Stock getStock(int id, String size) {
        return null;
    }

    @Override
    public void updateClothes(Clothes clothes) {
        SQL = "update clothes set name = ?, price = ?, category = ? where id = ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, clothes.getName());
            pstmt.setInt(2, clothes.getPrice());
            pstmt.setString(3, clothes.getCategory());
            pstmt.setInt(4, clothes.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            doFinal();
        }
    }

}
