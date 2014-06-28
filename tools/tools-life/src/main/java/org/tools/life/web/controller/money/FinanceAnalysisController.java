package org.tools.life.web.controller.money;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tools.life.domain.base.BaseListBO;
import org.tools.life.domain.money.FinanceView;
import org.tools.life.domain.money.PaymentType;
import org.tools.life.domain.money.TransRecordListVo;
import org.tools.life.service.money.BankCardService;
import org.tools.life.service.money.FinanceAnalysisService;
import org.tools.life.web.base.BaseController;
import org.tools.life.web.base.LifeExcepiton;
import org.tools.life.web.form.base.PageableForm;
import org.tools.life.web.form.money.TransRecordListForm;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/finance")
public class FinanceAnalysisController extends BaseController {
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private FinanceAnalysisService financeAnalysisService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        try {
            FinanceView financeView = bankCardService.getFinanceView();
            model.addAttribute("financeView", financeView);
            return "/finance/finance-index";
        } catch (LifeExcepiton e) {
            logger.error(e.getErrorMsg(), e);
            model.addAttribute(ERRORKEY, e.getErrorMsg());
        } catch (Exception e) {
            logger.error(UNKNOWNEXCEPTION, e);
            model.addAttribute(ERRORKEY, UNKNOWNEXCEPTION);
        }
        return ERROR;
    }

    @RequestMapping(value = "/pamentType", method = RequestMethod.GET)
    public String paymentType() {
        return "/finance/paymentType";
    }

    @RequestMapping(value = "/pamentType", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPamentType(String ptype, HttpServletRequest request) {
        try {
            List<PaymentType> payTypeList = financeAnalysisService
                    .getPayTypeData(ptype);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("payTypeList", payTypeList);
            return getSuccessResult(dataMap);
        } catch (LifeExcepiton e) {
            logger.error(e.getErrorMsg(), e);
            return getFailResult(e.getErrorMsg());
        } catch (Exception e) {
            logger.error(UNKNOWNEXCEPTION, e);
            return getFailResult(UNKNOWNEXCEPTION);
        }
    }

    @RequestMapping(value = "/pamentType/{pid}/edit", method = RequestMethod.GET)
    public String editPamentTypePage(@PathVariable int pid, Model model) {
        try {
            model.addAttribute("act", "edit");
            PaymentType paymentType = financeAnalysisService
                    .getPayTypeById(pid);
            model.addAttribute("paymentTypeInfo", paymentType);
            return "/finance/paymentType-addOrEdit-dialog";
        } catch (LifeExcepiton e) {
            logger.error(e.getErrorMsg(), e);
            model.addAttribute(ERRORKEY, e.getErrorMsg());
        } catch (Exception e) {
            logger.error(UNKNOWNEXCEPTION, e);
            model.addAttribute(ERRORKEY, UNKNOWNEXCEPTION);
        }
        return ERROR;
    }

    @RequestMapping(value = "/pamentType/{pid}/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editPamentType(@PathVariable int pid,
            PaymentType paymentType, HttpServletRequest request) {
        try {
            paymentType.setPid(pid);
            int i = financeAnalysisService.updatePayType(paymentType);
            if (i == 1) {
                return getSuccessResult();
            }
            return getFailResult(UPDATE_FAIL);
        } catch (LifeExcepiton e) {
            logger.error(e.getErrorMsg(), e);
            return getFailResult(e.getErrorMsg());
        } catch (Exception e) {
            logger.error(UNKNOWNEXCEPTION, e);
            return getFailResult(UNKNOWNEXCEPTION);
        }
    }

    @RequestMapping(value = "/pamentType/{pid}/remove", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removePamentType(@PathVariable int pid,
            HttpServletRequest request) {
        try {
            int i = financeAnalysisService.deletePayType(pid);
            if (i == 1) {
                return getSuccessResult();
            }
            return getFailResult(UPDATE_FAIL);
        } catch (LifeExcepiton e) {
            logger.error(e.getErrorMsg(), e);
            return getFailResult(e.getErrorMsg());
        } catch (Exception e) {
            logger.error(UNKNOWNEXCEPTION, e);
            return getFailResult(UNKNOWNEXCEPTION);
        }
    }

    @RequestMapping(value = "/pamentType/add", method = RequestMethod.GET)
    public String addPaymentTypePage(Model model) {
        model.addAttribute("act", "add");
        return "/finance/paymentType-addOrEdit-dialog";
    }

    @RequestMapping(value = "/pamentType/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPamentType(PaymentType paymentType,
            HttpServletRequest request) {
        try {
            financeAnalysisService.insertPayType(paymentType);
            return getSuccessResult();
        } catch (LifeExcepiton e) {
            logger.error(e.getErrorMsg(), e);
            return getFailResult(e.getErrorMsg());
        } catch (Exception e) {
            logger.error(UNKNOWNEXCEPTION, e);
            return getFailResult(UNKNOWNEXCEPTION);
        }
    }

    @RequestMapping(value = "/transRecord", method = RequestMethod.GET)
    public String transRecordPage(Model model) {
        return "/finance/transRecord-index";
    }

    @RequestMapping(value = "/transRecord/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTransRecordList(TransRecordListForm form,
            PageableForm pageableForm, HttpServletRequest request) {
        try {
            TransRecordListVo transRecordListVo = new TransRecordListVo();
            BeanUtils.copyProperties(transRecordListVo, form);
            pageableForm.copyPropertiesToVO(transRecordListVo);
            BaseListBO baseListBO = financeAnalysisService
                    .getTransRecordList(transRecordListVo);

            PaymentType paymentType = financeAnalysisService.getPayTypeById(1);

            return dataTableJson(baseListBO.getTotalcount(),
                    baseListBO.getDatalist());
        } catch (LifeExcepiton e) {
            logger.error(e.getErrorMsg(), e);
            return getFailResult(e.getErrorMsg());
        } catch (Exception e) {
            logger.error(UNKNOWNEXCEPTION, e);
            return getFailResult(UNKNOWNEXCEPTION);
        }
    }

    @RequestMapping(value = "/transRecord/add", method = RequestMethod.GET)
    public String addTransRecordPage(Model model) {
        return "/finance/transRecord-add-dialog";
    }

    @RequestMapping(value = "/pamentTypeChildren", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getChildPamentType(String ptype, HttpServletRequest request) {
        try {
            List<PaymentType> payTypeList = financeAnalysisService
                    .getPayTypeWithChild(ptype);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("payTypeList", payTypeList);
            return getSuccessResult(dataMap);
        } catch (LifeExcepiton e) {
            logger.error(e.getErrorMsg(), e);
            return getFailResult(e.getErrorMsg());
        } catch (Exception e) {
            logger.error(UNKNOWNEXCEPTION, e);
            return getFailResult(UNKNOWNEXCEPTION);
        }
    }

    // TODO 资金流水记录 收入 支出 转账

    // TODO 3收支流水
    // 收支流水查询 总收入 总支出 净流入 （净流入=总输入-总支出）
    // 收入分析 饼状 柱状图
    // 支出分析 饼状 柱状图

    // 月度分析 月初净资产 月末净资产 总收入 总支出

    // @RequestMapping(value = "/list", method = RequestMethod.POST)
    // @ResponseBody
    // public Map<String, Object> getCardList(CardListForm form,
    // PageableForm pageableForm, HttpServletRequest request) {
    // try {
    // CardListVo cardListVo = new CardListVo();
    // BeanUtils.copyProperties(cardListVo, form);
    // pageableForm.copyPropertiesToVO(cardListVo);
    // BaseListBO baseListBO = bankCardService.getCardList(cardListVo);
    // return dataTableJson(baseListBO.getTotalcount(),
    // baseListBO.getDatalist());
    // } catch (LifeExcepiton e) {
    // logger.error(e.getErrorMsg(), e);
    // return getFailResult(e.getErrorMsg());
    // } catch (Exception e) {
    // logger.error(UNKNOWNEXCEPTION, e);
    // return getFailResult(UNKNOWNEXCEPTION);
    // }
    // }

}
