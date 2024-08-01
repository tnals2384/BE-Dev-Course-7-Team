package day0731.shopAY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClothesTest {
    public static void main(String[] args) throws IOException {
        ClothesManagerImpl clothesManager = new ClothesManagerImpl();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //메뉴 선택
        while(true) {
            System.out.println("1. 옷 추가");
            System.out.println("2. 재고 추가");
            System.out.println("3. 의류 전체 목록 조회");
            System.out.println("4. 이름으로 의류 검색");
            System.out.println("5. 카테고리로 의류 검색");
            System.out.println("6. 가격으로 의류 검색");
            System.out.println("7. 의류 판매");
            System.out.println("8. 의류 삭제");
            System.out.println("9. 의류 업데이트");
            System.out.println("10. 종료");
            System.out.println("메뉴를 선택하세요:");
            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    System.out.println("옷 ID : ");
                    int id = Integer.parseInt(br.readLine());
                    System.out.println("옷 이름 : ");
                    String name = br.readLine();
                    System.out.println("옷 가격 : ");
                    int price = Integer.parseInt(br.readLine());
                    System.out.println("옷 카테고리 : ");
                    String category = br.readLine();
                    clothesManager.addClothes(new Clothes(id, name, price, category));
                    System.out.println("옷이 추가되었습니다.");
                    break;
                case 2:
                    System.out.println("옷 ID : ");
                    int stockId = Integer.parseInt(br.readLine());
                    System.out.println("옷 사이즈 : ");
                    String size = br.readLine();
                    System.out.println("재고 수량 : ");
                    int stock = Integer.parseInt(br.readLine());
                    clothesManager.addStock(new Stock(stockId, size, stock));
                    System.out.println("재고가 추가되었습니다.");
                    break;
                case 3:
                    System.out.println("**********************의류 전체 목록**********************");
                    clothesManager.getList();
                    Clothes[] clothesList = clothesManager.getList();
                    for (Clothes clothes : clothesList) {
                        System.out.println(clothes);
                    }
                    break;
                case 4:
                    System.out.println("검색할 옷 이름 : ");// consume newline
                    String searchName = br.readLine();
                    System.out.println("**********************이름으로 검색: '" + searchName + "'**********************");
                    Clothes[] nameSearchResults = clothesManager.searchClothesByName(searchName);
                    for (Clothes clothes : nameSearchResults) {
                        System.out.println(clothes);
                    }
                    break;
                case 5:
                    System.out.println("검색할 옷 카테고리 : ");
                    String searchCategory = br.readLine();
                    System.out.println("**********************카테고리로 검색: '" + searchCategory + "'**********************");
                    Clothes[] categorySearchResults = clothesManager.searchClothesByCategory(searchCategory);
                    for (Clothes clothes : categorySearchResults) {
                        System.out.println(clothes);
                    }
                    break;
                case 6:
                    System.out.println("원하는 가격 : ");
                    int searchPrice = Integer.parseInt(br.readLine());
                    System.out.println("**********************가격으로 검색: '" + searchPrice + "'**********************");
                    Clothes[] priceSearchResults = clothesManager.searchClothesByPrice(searchPrice);
                    for (Clothes clothes : priceSearchResults) {
                        System.out.println(clothes);
                    }
                    break;
                case 7:
                    System.out.println("판매할 옷 ID : ");
                    int sellId = Integer.parseInt(br.readLine());
                    System.out.println("판매할 옷 사이즈 : ");
                    String sellSize = br.readLine();
                    System.out.println("판매할 수량 : ");
                    int sellQuantity = Integer.parseInt(br.readLine());
                    clothesManager.sell(new Stock(sellId, sellSize, sellQuantity));
                    System.out.println("의류 판매가 완료되었습니다.");
                    break;
                case 8:
                    System.out.println("삭제할 옷 ID:");
                    int deleteId = Integer.parseInt(br.readLine());
                    clothesManager.deleteClothes(deleteId);
                    System.out.println("의류가 삭제되었습니다.");
                    break;
                case 9:
                    System.out.println("업데이트할 옷 ID : ");
                    int updateId = Integer.parseInt(br.readLine());
                    System.out.println("새 옷 이름 : ");
                    String updateName = br.readLine();
                    System.out.println("새 옷 가격 : ");
                    int updatePrice = Integer.parseInt(br.readLine());// consume newline
                    System.out.println("새 옷 카테고리 : ");
                    String updateCategory = br.readLine();
                    clothesManager.updateClothes(new Clothes(updateId, updateName, updatePrice, updateCategory));
                    System.out.println("의류가 업데이트되었습니다.");
                    break;
                case 10:
                    System.out.println("종료합니다.");
                    br.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }

    }
}