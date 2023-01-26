package com.tngus2sh.ssafy.hw7.step2;

public interface IUserManager {
    void add(User user);

    User[] getList();

    User[] getUsers();

    VipUser[] getVipUsers();

    User[] searchByName(String name);

    double getAgeAvg();
}
