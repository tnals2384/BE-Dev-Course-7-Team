package w3.day03.ws;

import java.util.Scanner;

public class ClothesTest {
    public static void main(String[] args) {
        ClothesManager clothesManager = ClothesManagerImpl.getInstance();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("메뉴를 선택하세요:");
            System.out.println("1. 옷 추가");
            System.out.println("2. 재고 추가");
            System.out.println("3. 의류 전체 목록 조회");
            System.out.println("4. 이름으로 의류 검색");
            System.out.println("5. 카테고리로 의류 검색");
            System.out.println("6. 의류 판매");
            System.out.println("7. 의류 삭제");
            System.out.println("8. 의류 업데이트");
            System.out.println("9. 종료");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("옷 ID:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("옷 이름:");
                    String name = sc.nextLine();
                    System.out.println("옷 가격:");
                    int price = sc.nextInt();
                    sc.nextLine();
                    System.out.println("옷 카테고리:");
                    String category = sc.nextLine();
                    clothesManager.addClothes(new Clothes(id, name, price, category));
                    System.out.println("옷이 추가되었습니다.");
                    break;
                case 2:
                    System.out.println("재고 ID:");
                    int stockId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("재고 사이즈:");
                    String size = sc.nextLine();
                    System.out.println("재고 수량:");
                    int stock = sc.nextInt();
                    clothesManager.addStock(new Stock(stockId, size, stock));
                    System.out.println("재고가 추가되었습니다.");
                    break;
                case 3:
                    System.out.println("**********************의류 전체 목록**********************");
                    Clothes[] clothesList = clothesManager.getList();
                    for (Clothes clothes : clothesList) {
                        System.out.println(clothes.getId() + "\t" + clothes.getName() + "\t" + clothes.getPrice() + "\t" + clothes.getCategory());
                    }
                    break;
                case 4:
                    System.out.println("검색할 옷 이름:");
                    sc.nextLine();
                    String searchName = sc.nextLine();
                    System.out.println("**********************이름으로 검색: '" + searchName + "'**********************");
                    Clothes[] nameSearchResults = clothesManager.searchClothesByName(searchName);
                    for (Clothes clothes : nameSearchResults) {
                        System.out.println(clothes.getId() + "\t" + clothes.getName() + "\t" + clothes.getPrice() + "\t" + clothes.getCategory());
                    }
                    break;
                case 5:
                    System.out.println("검색할 카테고리:");
                    sc.nextLine();
                    String searchCategory = sc.nextLine();
                    System.out.println("**********************카테고리로 검색: '" + searchCategory + "'**********************");
                    Clothes[] categorySearchResults = clothesManager.searchClothesByCategory(searchCategory);
                    for (Clothes clothes : categorySearchResults) {
                        System.out.println(clothes.getId() + "\t" + clothes.getName() + "\t" + clothes.getPrice() + "\t" + clothes.getCategory());
                    }
                    break;
                case 6:
                    System.out.println("판매할 옷 ID:");
                    int sellId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("판매할 옷 사이즈:");
                    String sellSize = sc.nextLine();
                    System.out.println("판매할 수량:");
                    int sellQuantity = sc.nextInt();
                    Stock sellStock = new Stock(sellId, sellSize, sellQuantity);
                    clothesManager.sell(sellStock);
                    System.out.println("의류 판매가 완료되었습니다.");
                    break;
                case 7:
                    System.out.println("삭제할 옷 ID:");
                    int deleteId = sc.nextInt();
                    clothesManager.deleteClothes(deleteId);
                    System.out.println("의류가 삭제되었습니다.");
                    break;
                case 8:
                    System.out.println("업데이트할 옷 ID:");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("새 옷 이름:");
                    String updateName = sc.nextLine();
                    System.out.println("새 옷 가격:");
                    int updatePrice = sc.nextInt();
                    sc.nextLine();
                    System.out.println("새 옷 카테고리:");
                    String updateCategory = sc.nextLine();
                    Clothes updatedClothes = new Clothes(updateId, updateName, updatePrice, updateCategory);
                    clothesManager.updateClothes(updatedClothes);
                    System.out.println("의류가 업데이트되었습니다.");
                    break;
                case 9:
                    System.out.println("종료합니다.");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
}
