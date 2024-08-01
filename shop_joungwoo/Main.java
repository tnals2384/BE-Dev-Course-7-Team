package shop;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClothesManager clothingManager = new ClothesManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. 옷 추가");
            System.out.println("2. 재고 추가");
            System.out.println("3. 의류 전체 목록 조회");
            System.out.println("4. 이름으로 의류 검색");
            System.out.println("5. 카테고리로 의류 검색");
            System.out.println("6. 의류 판매");
            System.out.println("7. 의류 삭제");
            System.out.println("8. 의류 업데이트");
            System.out.println("9. 종료");
            System.out.println("메뉴를 선택하세요:");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("옷 ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("옷 이름:");
                    String name = scanner.nextLine();
                    System.out.println("옷 가격:");
                    int price = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("옷 카테고리:");
                    String category = scanner.nextLine();
                    clothingManager.addClothes(new Clothes(id, name, price, category));
                    System.out.println(name + "옷이 추가되었습니다.");
                    break;
                case 2:
                    System.out.println("재고 ID:");
                    int stockId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("재고 사이즈:");
                    String size = scanner.nextLine();
                    System.out.println("재고 수량:");
                    int stock = scanner.nextInt();
                    clothingManager.addStock(new Stock(stockId, size, stock));
                    System.out.println("재고가 추가되었습니다.");
                    break;
                case 3:
                    System.out.println("**********************의류 전체 목록**********************");
                    Clothes[] clothesList = clothingManager.getList();
                    for (Clothes clothes : clothesList) {
                        System.out.println(clothes.getId() + "\t" + clothes.getName() + "\t" + clothes.getPrice() + "\t" + clothes.getCategory());
                    }
                    System.out.println("********************************************************");
                    break;
                case 4:
                    System.out.println("검색할 옷 이름:");
                    scanner.nextLine(); // consume newline
                    String searchName = scanner.nextLine();
                    System.out.println("**********************이름으로 검색: '" + searchName + "'**********************");
                    Clothes[] nameSearchResults = clothingManager.searchClothesByName(searchName);
                    for (Clothes clothes : nameSearchResults) {
                        System.out.println(clothes.getId() + "\t" + clothes.getName() + "\t" + clothes.getPrice() + "\t" + clothes.getCategory());
                    }
                    System.out.println("********************************************************");
                    break;
                case 5:
                    System.out.println("검색할 카테고리:");
                    scanner.nextLine(); // consume newline
                    String searchCategory = scanner.nextLine();
                    System.out.println("**********************카테고리로 검색: '" + searchCategory + "'**********************");
                    Clothes[] categorySearchResults = clothingManager.searchClothesByCategory(searchCategory);
                    for (Clothes clothes : categorySearchResults) {
                        System.out.println(clothes.getId() + "\t" + clothes.getName() + "\t" + clothes.getPrice() + "\t" + clothes.getCategory());
                    }
                    System.out.println("********************************************************");
                    break;
                case 6:
                    System.out.println("판매할 옷 ID:");
                    int sellId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("판매할 옷 사이즈:");
                    String sellSize = scanner.nextLine();
                    System.out.println("판매할 수량:");
                    int sellQuantity = scanner.nextInt();
                    Stock stockToSell = clothingManager.getStock(sellId, sellSize);
                    if (stockToSell != null && stockToSell.getStock() >= sellQuantity) {
                        stockToSell.setStock(sellQuantity);
                        clothingManager.sell(stockToSell);
                        System.out.println("의류 판매가 완료되었습니다.");
                    } else {
                        System.out.println("재고가 부족하거나 해당 재고를 찾을 수 없습니다.");
                    }
                    break;
                case 7:
                    System.out.println("삭제할 옷 ID:");
                    int deleteId = scanner.nextInt();
                    clothingManager.deleteClothes(deleteId);
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
                    Clothes updatedClothes = new Clothes(updateId, updateName, updatePrice, updateCategory);
                    clothingManager.updateClothes(updatedClothes);
                    System.out.println("의류가 업데이트되었습니다.");
                    break;
                case 9:
                    System.out.println("종료합니다.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
}