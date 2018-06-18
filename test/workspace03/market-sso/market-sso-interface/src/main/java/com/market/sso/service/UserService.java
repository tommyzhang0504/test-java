package com.market.sso.service;

import com.market.pojo.MarketResult;
import com.market.pojo.TbUser;

public interface UserService {
    MarketResult checkData(String param, int type);
    MarketResult register (TbUser tbUser);
    MarketResult login(String username, String password);
    MarketResult getUserByToken(String token);
    MarketResult logoutByDeletingToken(String token);
}
