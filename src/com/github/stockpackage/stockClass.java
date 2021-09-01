package com.github.stockpackage;

import java.util.*;

public class stockClass {
    public static Scanner scanner = new Scanner(System.in);
    public static int money = 0;
    public static HashMap<String, Integer> stock = new HashMap<>();
    public static HashMap<String, Integer> buyStock = new HashMap<>();
    public static Random random = new Random();

    public static void main(String[] args) {
        while (true) {
            System.out.println("돈을 얼마로 설정하시겠습니까?");
            String moneyString = scanner.nextLine();
            if (moneyString.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                try {
                    money = Integer.parseInt(moneyString);
                    while (true) {
                        System.out.println("당신의 돈은 " + money + "원 있습니다.");
                        System.out.println("여기서 무엇을 하시겠습니까?");
                        System.out.println("1. 주식 만들기");
                        System.out.println("2. 주식 삭제하기");
                        System.out.println("3. 주식 사기");
                        System.out.println("4. 주식 팔기");
                        System.out.println("5. 주식 가격 변동");
                        System.out.println("6. 주식 확인");
                        System.out.println("7. 종료");
                        String stockString = scanner.nextLine();
                        try {
                            final int stockNum = Integer.parseInt(stockString);
                            if (stockNum == 1) {
                                createStock();
                            } else if (stockNum == 2) {
                                removeStock();
                            } else if (stockNum == 3) {
                                buyStock();
                            } else if (stockNum == 4) {
                                sellStock();
                            } else if (stockNum == 5) {
                                changePriceStock();
                            } else if (stockNum == 6) {
                                getStock();
                            } else if (stockNum == 7) {
                                System.out.println("프로그램을 종료합니다.");
                                break;
                            } else {
                                System.out.println("1부터 4까지의 번호만 입력해주세요");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("숫자를 입력해주세요");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("1000000000보다 작게 또는 숫자를 입력해주셔야 합니다.");
                }
            }
        }
    }

    private static void createStock() {
        while (true) {
            System.out.println("만드실 주식과 가격을 적어주세요(종료는 '종료')");
            String stockName = scanner.nextLine();
            if (stockName.equals("종료")) {
                System.out.println("주식 생성을 종료합니다.");
                break;
            } else {
                String stockPriceString = scanner.nextLine();
                try {
                    final int price = Integer.parseInt(stockPriceString);
                    stock.put(stockName, price);
                    System.out.println("주식 만들기에 성공하셨습니다!");
                    System.out.println("이름: " + stockName + "\n" + "가격: " + price);
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해주세요");
                }
            }
        }
    }

    private static void removeStock() {
        while (true) {
            System.out.println("삭제할 주식과 가격을 적어주세요(종료는 '종료')");
            String stockName = scanner.nextLine();
            if (stockName.equals("종료")) {
                System.out.println("주식 삭제를 종료합니다.");
                break;
            } else {
                try {
                    String stockPriceString = scanner.nextLine();
                    int get = stock.get(stockName);
                    try {
                        final int price = Integer.parseInt(stockPriceString);
                        if (get == price) {
                            stock.remove(stockName, price);
                            System.out.println("주식 삭제에 성공하셨습니다.");
                        } else {
                            System.out.println("주식 삭제에 실패하였습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요");
                    }
                } catch (NullPointerException e) {
                    System.out.println("주식을 찾지 못했습니다.");
                }
            }
        }
    }

    private static void buyStock() {
        while (true) {
            System.out.println("구매할 주식과 가격을 적어주세요(종료는 '종료')");
            String stockName = scanner.nextLine();
            if (stockName.equals("종료")) {
                System.out.println("주식 구매를 종료합니다.");
                break;
            } else {
                try {
                    String stockPriceString = scanner.nextLine();
                    int get = stock.get(stockName);
                    try {
                        final int price = Integer.parseInt(stockPriceString);
                        if (get == price) {
                            while (true) {
                                System.out.println("몇개를 사실건가요?");
                                try {
                                    int num = scanner.nextInt();
                                    final int minus = money - (price * num);
                                    if (minus < 0) {
                                        System.out.println("돈이 부족합니다.");
                                    } else {
                                        System.out.println("주식 구매에 성공하셨습니다!");
                                        buyStock.put(stockName, num);
                                        money = minus;
                                        break;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("숫자를 입력해주세요");
                                }
                            }
                        } else {
                            System.out.println("주식을 찾지 못했습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요");
                    }
                } catch (NullPointerException e) {
                    System.out.println("주식을 찾지 못했습니다.");
                }
            }
        }
    }

    private static void sellStock() {
        while (true) {
            System.out.println("팔 주식과 전체가격을 입력해주세요");
            String stockName = scanner.nextLine();
            if (stockName.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                try {
                    String stockPriceString = scanner.nextLine();
                    int get = stock.get(stockName);
                    try {
                        final int price = Integer.parseInt(stockPriceString);
                        if (get == price) {
                            while (true) {
                                System.out.println("몇개를 파실건가요?");
                                try {
                                    String numString = scanner.nextLine();
                                    try {
                                        int get2 = buyStock.get(stockName);
                                        final int num = Integer.parseInt(numString);
                                        final int plus = money + (price * num);
                                        if (get2 - num == 0) {
                                            System.out.println("주식 전체를 판매합니다.");
                                            buyStock.remove(stockName, num);
                                            money = plus;
                                            break;
                                        } else if (get2 - num < 0) {
                                            System.out.println("주식을 팔지 못했습니다.");
                                        } else {
                                            System.out.println("주식을 " + num + "개 팔았습니다.");
                                            int minus = get2 - num;
                                            buyStock.replace(stockName, get2, minus);
                                            money = plus;
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("숫자를 입력해주세요");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("숫자를 입력해주세요");
                                }
                            }
                        } else {
                            System.out.println("주식을 찾지 못했습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요");
                    }
                } catch (NullPointerException e) {
                    System.out.println("주식을 찾지 못했습니다.");
                }
            }
        }
    }

    private static void changePriceStock() {
        while (true) {
            System.out.println("가격변동 시킬 주식과 가격을 입력해주세요(종료는 '종료')");
            String stockName = scanner.nextLine();
            if (stockName.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                try {
                    String stockPriceString = scanner.nextLine();
                    int get = stock.get(stockName);
                    try {
                        final int price = Integer.parseInt(stockPriceString);
                        if (get == price) {
                            System.out.println("주식 가격을 변동시킵니다.");
                            while (true) {
                                int changePrice = random.nextInt();
                                if (changePrice < 0|| changePrice > 100000) {
                                    continue;
                                } else {
                                    stock.replace(stockName, get, changePrice);
                                    System.out.println("변동된 주식 가격은 " + changePrice + "원 입니다.");
                                    break;
                                }
                            }
                        } else {
                            System.out.println("주식을 찾지 못했습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요");
                    }
                } catch (NullPointerException e) {
                    System.out.println("주식을 찾지 못했습니다.");
                }
            }
        }
    }

    private static void getStock() {
        if (stock.isEmpty() || buyStock.isEmpty()) {
            System.out.println("생성한 주식과 구매한 주식중 비어있습니다.");
        } else {
            System.out.println("지금 생성한 주식(주식이름, 개당 가격)");
            System.out.println(stock);
            System.out.println("지금 구매한 주식(주식이름, 갯수)");
            System.out.println(buyStock);
        }
    }
}
