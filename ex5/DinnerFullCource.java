package ex5;

public class DinnerFullCource {
    private Dish[] list = new Dish[5];// [0]-[4]の計5個
    private String [] Dish_Name_List = {"ライス","ステーキ","親子丼","味噌汁","あじの塩焼き"};
    private int [] Dish_Value_List ={100,600,500,300,600};

    public static void main(String[] args) {

        DinnerFullCource fullcourse = new DinnerFullCource();
        fullcourse.eatAll();
    }

    public void eatAll(){
        for (int i = 0; i < this.list.length; i++) {
            this.list[i] = new Dish();
            this.list[i].setName(this.Dish_Name_List[i]);
            this.list[i].setValune(this.Dish_Value_List[i]);
            System.out.println(this.list[i].getName()+":"+this.list[i].getValune()+"円");
        }
    }
}
