package day0731.shopAY;

/**
 * 옷리스트를 관리하기 위한 클래스를 위한 명세역할의 인터페이스
 */

public interface IClothesManager {

    // 옷 추가
    void addClothes(Clothes clothes);

    //재고 추가
    void addStock(Stock stock);

    //전체 옷 목록 조회
    Clothes[] getList();

    //이름으로 옷 검색
    Clothes[] searchClothesByName(String name);

    //카테고리로 옷 검색
    Clothes[] searchClothesByCategory(String category);

    //가격으로 옷 검색
    Clothes[] searchClothesByPrice(int price);

    //옷 삭제
    void deleteClothes(int id);

    //옷 판매
    void sell(Stock stock);

    //재고 조회
    Stock getStock(int id, String size);

    //옷 정보 수정
    void updateClothes(Clothes clothes);

}