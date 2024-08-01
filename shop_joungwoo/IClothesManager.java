package shop;

public interface IClothesManager {

    void addClothes(Clothes clothes);

    void addStock(Stock stock);

    Clothes[] getList();

    Clothes[] searchClothesByName(String name);

    Clothes[] searchClothesByCategory(String category);

    Clothes[] searchClothesByPrice(int price);

    void deleteClothes(int id);

    void sell(Stock stock);

    Stock getStock(int id, String size);

    void updateClothes(Clothes clothes);
}