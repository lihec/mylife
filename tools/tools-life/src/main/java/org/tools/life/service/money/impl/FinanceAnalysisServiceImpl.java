package org.tools.life.service.money.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.life.dao.money.BankCardMapper;
import org.tools.life.dao.money.PaymentTypeMapper;
import org.tools.life.dao.money.TransDetailsMapper;
import org.tools.life.domain.base.BaseListBO;
import org.tools.life.domain.base.Page;
import org.tools.life.domain.money.*;
import org.tools.life.service.money.FinanceAnalysisService;
import org.tools.life.web.base.LifeExcepiton;
import org.tools.util.mail.java.DateUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@Service
public class FinanceAnalysisServiceImpl implements FinanceAnalysisService {
    @Autowired
    private PaymentTypeMapper paymentTypeMapper;
    @Autowired
    private TransDetailsMapper transDetailsMapper;
    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public List<PaymentType> getPayTypeData(String ptype) {
        return paymentTypeMapper.getPayTypeData(ptype);
    }

    @Override
    public void insertPayType(PaymentType paymentType) {
        paymentTypeMapper.insertPayType(paymentType);
    }

    @Override
    public int updatePayType(PaymentType paymentType) {
        return paymentTypeMapper.updatePayType(paymentType);
    }

    @Override
    public int deletePayType(int pid) {
        return paymentTypeMapper.deletePayType(pid);
    }

    @Override
    public PaymentType getPayTypeById(int pid) {
        return paymentTypeMapper.getPayTypeById(pid);
    }

    @Override
    public BaseListBO getTransRecordList(TransRecordListVo transRecordListVo) {
        Page page = Page.getInstance(transRecordListVo);
        List<TransDetailsBo> list = transDetailsMapper.getTransList(page);
        return BaseListBO.getInstance(page, list);
    }

    @Override
    public List<PaymentType> getPayTypeWithChild(String ptype) {
        List<PaymentType> childPayType = paymentTypeMapper.getChildPayType("0", ptype);
        if (childPayType != null) {
            for (PaymentType paymentType : childPayType) {
                getChildrens(paymentType);
            }
        }
        return childPayType;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(TransDetailsVo form) {
        TransDetails insertTrans = new TransDetails();
        insertTrans.setToptype(form.getPtype());
        insertTrans.setMoney(form.getMoney());
        insertTrans.setFromCard(form.getFromCard());
        try {
            insertTrans.setAddtime(DateUtil.getDate(form.getAddtime(), DateUtil.DATE_FORMAT));
        } catch (ParseException e) {
            throw new LifeExcepiton("获取添加时间失败", e);
        }
        insertTrans.setMark(form.getMark());

        int ptype = form.getPtype();
        BankCard fromCard = bankCardMapper.getCardById(Integer.parseInt(form.getFromCard()));
        BankCard toCard = null;
        switch (ptype) {
        case 0:
            //收入
            insertTrans.setType(form.getType());
            insertTrans.setTypeName(form.getTypeName());
            fromCard.setBalance(fromCard.getBalance().add(form.getMoney()));
            break;
        case 1:
            //支出
            insertTrans.setType(form.getType());
            insertTrans.setTypeName(form.getTypeName());
            fromCard.setBalance(fromCard.getBalance().subtract(form.getMoney()));
            break;
        case 2:
            //转账
            insertTrans.setToCard(form.getToCard());
            fromCard.setBalance(fromCard.getBalance().subtract(form.getMoney()));
            toCard = bankCardMapper.getCardById(Integer.parseInt(form.getToCard()));
            toCard.setBalance(toCard.getBalance().add(form.getMoney()));
            break;
        case 3:
            //提现
            insertTrans.setToCard(form.getToCard());
            // 提现万分之38的手续费
            BigDecimal withdrawFee = form.getMoney().multiply(new BigDecimal("0.0038"));
            insertTrans.setWithdrawFee(withdrawFee);
            if (fromCard.getType() != 1) {
                throw new LifeExcepiton("暂时不支持非信用卡提现");
            }
            // 信用卡欠款增加
            fromCard.setBalance(fromCard.getBalance().add(form.getMoney()));
            // 重新计算可以额度
            fromCard.setAvailableCredit(new BigDecimal(fromCard.getFixCredit()).subtract(fromCard.getBalance()));

            toCard = bankCardMapper.getCardById(Integer.parseInt(form.getToCard()));
            // 转入方要扣除手续费
            toCard.setBalance(toCard.getBalance().add(form.getMoney()).subtract(withdrawFee));
            break;
        }
        bankCardMapper.updateCard(fromCard);
        if (toCard != null) {
            bankCardMapper.updateCard(toCard);
        }
        return transDetailsMapper.insert(insertTrans);
    }

    @Override
    public int deleteTransDetail(String tid) {
        return transDetailsMapper.deleteByPrimaryKey(tid);
    }

    /**
     * 递归查询所有子分类
     * @param paymentType
     */
    private void getChildrens(PaymentType paymentType) {
        List<PaymentType> paymentTypes = paymentTypeMapper
                .getChildPayType(paymentType.getPid().toString(), paymentType.getPtype().toString());
        if (paymentTypes != null) {
            paymentType.setChildPayments(paymentTypes);
            for (PaymentType child : paymentTypes) {
                getChildrens(child);
            }
        }
    }

}
