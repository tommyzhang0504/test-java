package com.market.sso.service.impl;

import com.market.dao.TbUserDao;
import com.market.jedis.JedisClient;
import com.market.pojo.MarketResult;
import com.market.pojo.TbUser;
import com.market.pojo.TbUserQuery;
import com.market.sso.service.UserService;
import com.market.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserDao tbUserDao;
    @Autowired
    private JedisClient jedisClient;
    @Value("${USER_SESSION}")
    private String USER_SESSION;
    @Value("${TOKEN_EXPIRE_TIME}")
    private int TOKEN_EXPIRE_TIME;

    @Override
    public MarketResult checkData(String param, int type) {
        TbUserQuery example = new TbUserQuery();
        TbUserQuery.Criteria criteria = example.createCriteria();
        //根据type值进行判断，1是用户名，2是phone，3是email
        if (type == 1) {
            criteria.andUsernameEqualTo(param);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(param);
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        } else {
            return MarketResult.build(400, "数据查询有误，请核对后再查询");
        }

        List<TbUser> list = tbUserDao.selectByExample(example);
        if (list != null && list.size() > 0) {
            return MarketResult.ok(false);
        }

        return MarketResult.ok(true);
    }

    @Override
    public MarketResult register(TbUser tbUser) {
        //检验用户名不能为空
        if (StringUtils.isBlank(tbUser.getUsername())) {
            return MarketResult.build(400, "用户名不能为空");
        }
        //校验用户名是否已经存在
        MarketResult result = checkData(tbUser.getUsername(), 1);
        if (result.getData() == false) {
            return MarketResult.build(400, "注册失败，请校验数据后再提交");
        }

        //校验密码是否为空
        if (StringUtils.isBlank(tbUser.getPassword())) {
            return MarketResult.build(400, "密码不能为空");
        }

        //校验手机是否重复
        if (StringUtils.isNotBlank(tbUser.getPhone())) {
            result = checkData(tbUser.getPhone(), 2);
            if (result.getData() == false) {
                return MarketResult.build(400, "注册失败，手机号码重复");
            }
        }

        //校验email是否重复
        if (StringUtils.isNotBlank(tbUser.getEmail())) {
            result = checkData(tbUser.getEmail(), 3);
            if (result.getData() == false) {
                return MarketResult.build(400, "注册失败，Email重复");
            }
        }

        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
        tbUser.setPassword(password);

        tbUserDao.insert(tbUser);
        return MarketResult.ok();
    }

    @Override
    public MarketResult login(String username, String password) {
        //判断用户名与密码是否正确
        TbUserQuery example = new TbUserQuery();
        TbUserQuery.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = tbUserDao.selectByExample(example);
        if (list == null && list.size() == 0) {
            return MarketResult.build(400, "用户名或密码不存在");
        }
        TbUser tbuser = list.get(0);
        String digest = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!digest.equals(tbuser.getPassword())) {
            return MarketResult.build(400, "用户名或密码不存在");
        }
        //如果正确，使用UUID生成TOKEN
        String token = UUID.randomUUID().toString();
        //把TOKEN保存到REDIS中，并设置过期时间，注意这里不要保存密码
        tbuser.setPassword(null);
        jedisClient.set(USER_SESSION + ":" + token, JsonUtils.objectToJson(tbuser));
        jedisClient.expire(USER_SESSION + ":" + token, TOKEN_EXPIRE_TIME);

        //返回结果，并把TOKEN返回到表现层

        return MarketResult.ok(token);

    }

    @Override
    public MarketResult getUserByToken(String token) {
        String json = jedisClient.get(USER_SESSION + ":" + token);
        if (StringUtils.isBlank(json)) {
            return MarketResult.build(400, "用户登录已经过期");
        }

        //重置json过期时间
        jedisClient.expire(USER_SESSION + ":" + token, TOKEN_EXPIRE_TIME);
        TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
        return MarketResult.ok(tbUser);
    }

    @Override//安全退出，将session过期时间设置为0，不用判断token是否存在 ，不存在时，进行删除返回同样的结果
    public MarketResult logoutByDeletingToken(String token) {
        jedisClient.expire(USER_SESSION + ":" + token, 0);
        return MarketResult.ok();
    }

}
