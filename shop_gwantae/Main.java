package ws;

import day12.ws.Stock;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        ClothesRepository clothesManager = new ClothesRepositoryMysql();
        Scanner scanner = new Scanner(System.in);

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
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // consume newline
                    System.out.println("옷 이름:");
                    String name = scanner.nextLine();
                    System.out.println("옷 가격:");
                    int price = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("옷 카테고리:");
                    String category = scanner.nextLine();
                    clothesManager.addClothes(new ClothesDTO(name, price, category));
                    System.out.println("옷이 추가되었습니다.");
                    break;
                case 2:
                    System.out.println("재고 ID:");
                    int stockId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("재고 사이즈:");
                    String size = scanner.nextLine();
                    System.out.println("재고 수량:");
                    int stock = scanner.nextInt();
                    clothesManager.addStock(new StockDTO(stockId, size, stock));
                    System.out.println("재고가 추가되었습니다.");
                    break;
                case 3:
                    System.out.println("**********************의류 전체 목록**********************");
                    ClothesDTO[] clothesList = clothesManager.getList();
                    for (ClothesDTO clothes : clothesList) {
                        System.out.println(clothes.getId() + "\t" + clothes.getName() + "\t" + clothes.getPrice() + "\t" + clothes.getCategory());
                    }
                    break;
                case 4:
                    System.out.println("검색할 옷 이름:");
                    scanner.nextLine(); // consume newline
                    String searchName = scanner.nextLine();
                    System.out.println("**********************이름으로 검색: '" + searchName + "'**********************");
                    ClothesDTO[] nameSearchResults = clothesManager.searchClothesByName(searchName);
                    for (ClothesDTO clothes : nameSearchResults) {
                        System.out.println(clothes.getId() + "\t" + clothes.getName() + "\t" + clothes.getPrice() + "\t" + clothes.getCategory());
                    }
                    break;
                case 5:
                    System.out.println("검색할 카테고리:");
                    scanner.nextLine(); // consume newline
                    String searchCategory = scanner.nextLine();
                    System.out.println("**********************카테고리로 검색: '" + searchCategory + "'**********************");
                    ClothesDTO[] categorySearchResults = clothesManager.searchClothesByCategory(searchCategory);
                    for (ClothesDTO clothes : categorySearchResults) {
                        System.out.println(clothes.getId() + "\t" + clothes.getName() + "\t" + clothes.getPrice() + "\t" + clothes.getCategory());
                    }
                    break;
                case 6:
                    System.out.println("판매할 옷 ID:");
                    int sellId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("판매할 옷 사이즈:");
                    String sellSize = scanner.nextLine();
                    System.out.println("판매할 수량:");
                    int sellQuantity = scanner.nextInt();
                    clothesManager.sell(new StockDTO(sellId,sellSize,sellQuantity));
                    System.out.println("의류 판매가 완료되었습니다.");

                    break;
                case 7:
                    System.out.println("삭제할 옷 ID:");
                    int deleteId = scanner.nextInt();
                    clothesManager.deleteClothes(deleteId);
                    System.out.println("의류가 삭제되었습니다.");
                    break;
                case 8:
                    System.out.println("업데이트할 옷 ID:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("새 옷 이름:");
                    String updateName = scanner.nextLine();
                    System.out.println("새 옷 가격:");
                    int updatePrice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("새 옷 카테고리:");
                    String updateCategory = scanner.nextLine();
                    ClothesDTO updatedClothes = new ClothesDTO(updateId, updateName, updatePrice, updateCategory);
                    clothesManager.updateClothes(updatedClothes);
                    System.out.println("의류가 업데이트되었습니다.");
                    break;
                case 9:
                    System.out.println("종료합니다.");
                    scanner.close();
                    System.exit(0);
                    break;
                case 10:
                    System.out.println("**********************모든 재고 출력**********************");
                    StockDTO[] stocks = clothesManager.getStocks();
                    for(StockDTO s : stocks){
                        System.out.println(s.getId() + "\t" + s.getSize() + "\t" + s.getStock());
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
}
