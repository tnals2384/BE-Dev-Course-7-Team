package ws;

import java.sql.SQLException;

public interface ClothesRepository {
    int addClothes(ClothesDTO clothes) throws SQLException;
    int addStock(StockDTO stock) throws SQLException;
    ClothesDTO[] getList() throws SQLException;
    ClothesDTO[] searchClothesByName(String name) throws SQLException;
    ClothesDTO[] searchClothesByCategory(String category) throws SQLException;
    ClothesDTO[] searchClothesByPrice(int price) throws SQLException;
    int deleteClothes(int id) throws SQLException;
    int sell(StockDTO stock) throws SQLException;
    StockDTO getStock(int id, String size) throws SQLException;
    StockDTO[] getStocks() throws SQLException;
    int updateClothes(ClothesDTO clothes) throws SQLException;
}
