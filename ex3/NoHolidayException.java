package ex3;

public class NoHolidayException extends Exception{
    int day;

    public NoHolidayException(int day){
        this.day=day;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.err.println(this.day+"は休日じゃないです！");
    }
}
