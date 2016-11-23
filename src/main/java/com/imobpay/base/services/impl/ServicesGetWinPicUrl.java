package com.imobpay.base.services.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.console.Console_ErrCode;
import com.imobpay.base.console.OuterConsoleColumn;
import com.imobpay.base.dao.TbvActivityLotteryDao;
import com.imobpay.base.dao.TbvCardDao;
import com.imobpay.base.entity.TbvActivityLottery;
import com.imobpay.base.entity.TbvCard;
import com.imobpay.base.exception.QTException;
import com.imobpay.base.iface.BusinessInterface;
import com.imobpay.base.util.DateUtil;
import com.imobpay.base.util.EmptyChecker;

/**
 * 
 * ClassName: ServicesGetWinPicUrl <br/>
 * Function: 用户中奖转盘 <br/>
 * date: 2016年8月27日 下午1:08:06 <br/>
 * 
 * @author madman
 * @version
 * @since JDK 1.6 PayTACard 1.0
 */
@Service
public class ServicesGetWinPicUrl implements BusinessInterface {
    /**
     * TA卡
     */
    @Resource
    TbvCardDao<TbvCard>                       tbvCardDao;
    /**
     * 中奖抽取表
     */
    @Resource
    TbvActivityLotteryDao<TbvActivityLottery> tbvActiveLotteryDao;

    @Override
    public String execute(String json) throws QTException {
        JSONObject reqJson = JSONObject.parseObject(json);
        EmptyChecker.checkEmpty(reqJson, OuterConsoleColumn.OPENID, OuterConsoleColumn.PUB_ACCOUNT);
        String openId = reqJson.getString(OuterConsoleColumn.OPENID);
        // 2016年10月13日15:44:56 由madman添加
        String pubAccount = reqJson.getString(OuterConsoleColumn.PUB_ACCOUNT);

        JSONObject respJson = new JSONObject();
        // 判断用户是否已经抽取
        TbvActivityLottery tbvCode = new TbvActivityLottery();
        tbvCode.setStatus("1");
        tbvCode.setOpenid(openId);
        // 2016年10月13日15:44:56 由madman添加
        tbvCode.setPubAccount(pubAccount);
        TbvActivityLottery selCode = tbvActiveLotteryDao.selectById(tbvCode);
        // 获取当前时间的后五分钟
        String time = DateUtil.addDate(new Date(), 5, Calendar.MINUTE, DateUtil.YYYYMMDDHHMMSS);
        if (EmptyChecker.isEmpty(selCode)) {
            // 随机抽取一张TA卡
            TbvActivityLottery t = new TbvActivityLottery();
            t.setPubAccount(pubAccount);
            TbvActivityLottery selectActiveLottery = tbvActiveLotteryDao.selectActiveLottery(t);
            if (EmptyChecker.isEmpty(selectActiveLottery)) {
                throw new QTException(Console_ErrCode.NOTE_THISENDCODE, Console_ErrCode.NOTE_THISENDDESC);
            }
            // 在此处判断次TA卡是否已经通过其他渠道注册
            TbvCard tcard = new TbvCard();
            tcard.setTaaccount(selectActiveLottery.getId());
            TbvCard taCard = tbvCardDao.selectById(tcard);
            if (EmptyChecker.isEmpty(taCard)) {
                throw new QTException(Console_ErrCode.NOTE_THISENDCODE, Console_ErrCode.NOTE_THISENDDESC);
            }
            if ("1".equals(taCard.getBindflag())) {
                // 如果有记录则更新相关数据 更新当前TA卡的锁时间
                tbvCode.setStatus("2");
                tbvCode.setChecktime(time);
                tbvCode.setId(selectActiveLottery.getId());
                tbvActiveLotteryDao.updateCodeLottery(tbvCode);
                throw new QTException(Console_ErrCode.NOTE_THISENDCODE, Console_ErrCode.NOTE_THISENDDESC);
            }
            // 如果有记录则更新相关数据 更新当前TA卡的锁时间
            tbvCode.setStatus("1");
            tbvCode.setChecktime(time);
            tbvCode.setId(selectActiveLottery.getId());
            tbvActiveLotteryDao.updateCodeLottery(tbvCode);
            respJson.put("url", selectActiveLottery.getCardurl());

        } else {
            // 在此处判断次TA卡是否已经通过其他渠道注册
            TbvCard tcard = new TbvCard();
            tcard.setTaaccount(selCode.getId());
            TbvCard taCard = tbvCardDao.selectById(tcard);
            if (EmptyChecker.isEmpty(taCard)) {
                throw new QTException(Console_ErrCode.NOTE_THISENDCODE, Console_ErrCode.NOTE_THISENDDESC);
            }
            if ("1".equals(taCard.getBindflag())) {
                // 如果有记录则更新相关数据 更新当前TA卡的锁时间
                tbvCode.setStatus("2");
                tbvCode.setChecktime(time);
                tbvCode.setId(selCode.getId());
                tbvActiveLotteryDao.updateCodeLottery(tbvCode);
                throw new QTException(Console_ErrCode.NOTE_THISENDCODE, Console_ErrCode.NOTE_THISENDDESC);
            }
            respJson.put("url", selCode.getCardurl());
        }
        return respJson.toString();
    }
}
