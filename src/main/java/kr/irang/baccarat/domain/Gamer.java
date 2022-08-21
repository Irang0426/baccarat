package kr.irang.baccarat.domain;

public class Gamer {
    private int money ;
    private int selectTeam;

    public Gamer(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getSelectTeam() {
        return selectTeam;
    }

    public void setSelectTeam(int selectTeam) {
        this.selectTeam = selectTeam;
    }
}
