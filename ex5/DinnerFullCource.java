package ex5;

public class DinnerFullCource {
    private Dish[] list = new Dish[5];// [0]-[4]の計5個

    public DinnerFullCource() {
        String[] Dish_Name_List = { "ライス", "ステーキ", "親子丼", "味噌汁", "あじの塩焼き" };
        int[] Dish_Value_List = { 100, 600, 500, 300, 600 };
        for (int i = 0; i < Dish_Name_List.length; i++) {
            this.list[i] = new Dish();
            this.list[i].setName(Dish_Name_List[i]);
            this.list[i].setValune(Dish_Value_List[i]);
        }
    }

    public static void main(String[] args) {
        DinnerFullCource fullcourse = new DinnerFullCource();
        fullcourse.eatAll();
    }

    public void eatAll() {
        for (Dish dish: this.list) {
            System.out.println(dish.getName() + ":" + dish.getValune() + "円");
        }
    }
}
