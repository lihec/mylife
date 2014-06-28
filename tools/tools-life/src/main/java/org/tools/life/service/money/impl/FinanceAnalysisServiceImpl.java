package org.tools.life.service.money.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tools.life.dao.money.PaymentTypeMapper;
import org.tools.life.dao.money.TransDetailsMapper;
import org.tools.life.domain.base.BaseListBO;
import org.tools.life.domain.base.Page;
import org.tools.life.domain.money.PaymentType;
import org.tools.life.domain.money.TransDetailsBo;
import org.tools.life.domain.money.TransRecordListVo;
import org.tools.life.service.money.FinanceAnalysisService;

import java.util.List;

@Service
public class FinanceAnalysisServiceImpl implements FinanceAnalysisService {
    @Autowired
    private PaymentTypeMapper paymentTypeMapper;
    @Autowired
    private TransDetailsMapper transDetailsMapper;

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
