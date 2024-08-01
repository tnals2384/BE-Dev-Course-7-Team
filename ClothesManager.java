package w3.day03.ws;

/**
 * ClothesManager 인터페이스는 의류 관리 기능 정의합니다.
 */
public interface ClothesManager {

    /**
     * 새로운 의류를 추가합니다.
     *
     * @param clothes 추가할 의류 객체
     */
    void addClothes(Clothes clothes);

    /**
     * 의류 재고를 추가합니다.
     *
     * @param stock 추가할 재고 객체
     */
    void addStock(Stock stock);

    /**
     * 모든 의류 목록을 반환합니다.
     *
     * @return 등록된 모든 의류 목록 배열
     */
    Clothes[] getList();

    /**
     * 이름으로 의류를 검색합니다.
     *
     * @param name 검색할 의류의 이름
     * @return 이름에 해당하는 의류 목록 배열
     */
    Clothes[] searchClothesByName(String name);

    /**
     * 카테고리로 의류를 검색합니다.
     *
     * @param category 검색할 의류의 카테고리
     * @return 카테고리에 해당하는 의류 목록 배열
     */
    Clothes[] searchClothesByCategory(String category);

    /**
     * 가격으로 의류를 검색합니다.
     *
     * @param price 검색할 의류의 가격
     * @return 가격에 해당하는 의류 목록 배열
     */
    Clothes[] searchClothesByPrice(int price);

    /**
     * ID로 의류를 삭제합니다.
     *
     * @param id 삭제할 의류의 ID
     */
    void deleteClothes(int id);

    /**
     * 의류를 판매합니다.
     *
     * @param stock 판매할 재고 객체
     */
    void sell(Stock stock);

    /**
     * ID와 사이즈로 재고를 조회합니다.
     *
     * @param id 조회할 재고의 ID
     * @param size 조회할 재고의 사이즈
     * @return 해당 ID와 사이즈에 해당하는 재고 객체
     */
    Stock getStock(int id, String size);

    /**
     * 의류 정보를 업데이트합니다.
     *
     * @param clothes 업데이트할 의류 객체
     */
    void updateClothes(Clothes clothes);
}