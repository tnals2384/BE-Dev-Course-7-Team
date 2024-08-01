package ws;

import ws.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClothesRepositoryMysql implements ClothesRepository {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public int addClothes(ClothesDTO clothes) throws SQLException {
        int result = 0;
        try {
            String SQL = "INSERT INTO clothes(NAME, PRICE, CATEGORY) VALUES(?, ?, ?)";
            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            ps.setString(1, clothes.getName());
            ps.setInt(2, clothes.getPrice());
            ps.setString(3, clothes.getCategory());
            result = ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

    @Override
    public int addStock(StockDTO stock) throws SQLException {
        int result = 0;
        try {
            String SQL = "INSERT INTO stocks(ID, SIZE, STOCK) VALUES(?, ?, ?)";
            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, stock.getId());
            ps.setString(2, stock.getSize());
            ps.setInt(3, stock.getStock());
            result = ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

    @Override
    public ClothesDTO[] getList() throws SQLException {
        List<ClothesDTO> list = new ArrayList<>();
        int result = 0;
        try {
            String SQL = "SELECT * FROM CLOTHES";
            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()) {
                list.add(makeclothesDTO(rs));
            }
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return list.toArray(new ClothesDTO[list.size()]);
    }

    @Override
    public ClothesDTO[] searchClothesByName(String name) throws SQLException {
        List<ClothesDTO> list = new ArrayList<>();
        int result = 0;
        try {
            String SQL = "SELECT * FROM CLOTHES WHERE NAME LIKE ?";
            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while(rs.next()) {
                list.add(makeclothesDTO(rs));
            }
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return list.toArray(new ClothesDTO[list.size()]);
    }

    @Override
    public ClothesDTO[] searchClothesByCategory(String category) throws SQLException {
        List<ClothesDTO> list = new ArrayList<>();
        int result = 0;
        try {
            String SQL = "SELECT * FROM CLOTHES WHERE CATEGORY LIKE ?";
            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + category + "%");
            rs = ps.executeQuery();
            while(rs.next()) {
                list.add(makeclothesDTO(rs));
            }
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return list.toArray(new ClothesDTO[list.size()]);
    }

    @Override
    public ClothesDTO[] searchClothesByPrice(int price) throws SQLException {
        return new ClothesDTO[0];
    }

    @Override
    public int deleteClothes(int id) throws SQLException {
        int result = 0;
        try {
            String SQL = "DELETE FROM CLOTHES WHERE ID = ?";
            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

    @Override
    public int sell(StockDTO stock) throws SQLException {
        int result = 0;
        StockDTO num = getStock(stock.getId(), stock.getSize());
        if (num != null || stock.getStock() > num.getStock()) {
            result = 0;
        }
        try {
            String SQL = "Update STOCKS SET STOCK = ? WHERE ID = ? AND SIZE = ?";
            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, stock.getStock() - num.getStock());
            ps.setInt(1, stock.getId());
            ps.setString(1, stock.getSize());
            result = ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

    @Override
    public StockDTO getStock(int id, String size) throws SQLException {
        StockDTO stock = null;
        int result = 0;
        try {
            String SQL = "SELECT * FROM stocks WHERE id = ? AND SIZE = ?";
            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.setString(2, size);
            rs = ps.executeQuery();
            if(rs.next()) {
                stock = makestockDTO(rs);
            }
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return stock;
    }

    @Override
    public StockDTO[] getStocks() throws SQLException {
        List<StockDTO> list = new ArrayList<>();
        int result = 0;
        try {
            String SQL = "SELECT * FROM stocks";
            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if(rs.next()) {
                list.add(makestockDTO(rs));
            }
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return list.toArray(new StockDTO[list.size()]);
    }

    @Override
    public int updateClothes(ClothesDTO clothes) throws SQLException {
        int result = 0;
        try {
            String SQL = "Update CLOTHES SET NAME = ?, PRICE = ?, CATEGORY = ? WHERE ID = ?";

            conn = DBUtil.getConnection();//static 메서드라 바로 사용 가능
            ps = conn.prepareStatement(SQL);
            ps.setString(1, clothes.getName());
            ps.setInt(1, clothes.getPrice());
            ps.setString(1, clothes.getCategory());
            result = ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println("insert error");
            throw e;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }
    private ClothesDTO makeclothesDTO(ResultSet rs) throws SQLException {
        ClothesDTO clothesDTO = new ClothesDTO();
        clothesDTO.setId(rs.getInt("ID"));
        clothesDTO.setName(rs.getString("NAME"));
        clothesDTO.setPrice(rs.getInt("PRICE"));
        clothesDTO.setCategory(rs.getString("CATEGORY"));
        return clothesDTO;
    }

    private StockDTO makestockDTO(ResultSet rs) throws SQLException {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(rs.getInt("ID"));
        stockDTO.setSize(rs.getString("SIZE"));
        stockDTO.setStock(rs.getInt("STOCK"));
        return stockDTO;
    }
}
