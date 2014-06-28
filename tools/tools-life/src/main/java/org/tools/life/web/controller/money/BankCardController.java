package org.tools.life.web.controller.money;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tools.life.domain.base.BaseListBO;
import org.tools.life.domain.money.BankCard;
import org.tools.life.domain.money.CardListVo;
import org.tools.life.service.common.SafeService;
import org.tools.life.service.money.BankCardService;
import org.tools.life.web.base.BaseController;
import org.tools.life.web.base.LifeExcepiton;
import org.tools.life.web.form.base.PageableForm;
import org.tools.life.web.form.money.CardListForm;

@Controller
@RequestMapping("/cardManage")
public class BankCardController extends BaseController {
	@Autowired
	private BankCardService bankCardService;
	@Autowired
	private SafeService safeService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "/bankcard/bankCard-index";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCardList(CardListForm form,
			PageableForm pageableForm, HttpServletRequest request) {
		try {
			CardListVo cardListVo = new CardListVo();
			BeanUtils.copyProperties(cardListVo, form);
			pageableForm.copyPropertiesToVO(cardListVo);
			BaseListBO baseListBO = bankCardService.getCardList(cardListVo);
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

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("act", "add");
		return "/bankcard/bankCard-addOrEdit-dialog";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(BankCard bankCard, HttpServletRequest request) {
		try {
			bankCardService.addCard(bankCard);
			return getSuccessResult();
		} catch (LifeExcepiton e) {
			logger.error(e.getErrorMsg(), e);
			return getFailResult(e.getErrorMsg());
		} catch (Exception e) {
			logger.error(UNKNOWNEXCEPTION, e);
			return getFailResult(UNKNOWNEXCEPTION);
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editPage(@PathVariable Integer id, Model model) {
		try {
			model.addAttribute("act", "edit");
			BankCard bankCard = bankCardService.getCardById(id);
			model.addAttribute("cardInfo", bankCard);
			return "/bankcard/bankCard-addOrEdit-dialog";
		} catch (LifeExcepiton e) {
			logger.error(e.getErrorMsg(), e);
			model.addAttribute(ERRORKEY, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(UNKNOWNEXCEPTION, e);
			model.addAttribute(ERRORKEY, UNKNOWNEXCEPTION);
		}
		return ERROR;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(@PathVariable Integer id,
			BankCard bankCard, HttpServletRequest request) {
		try {
			int i = bankCardService.updateCard(bankCard);
			if (i == 1) {
				return getSuccessResult();
			}
			return getFailResult("数据更新失败");
		} catch (LifeExcepiton e) {
			logger.error(e.getErrorMsg(), e);
			return getFailResult(e.getErrorMsg());
		} catch (Exception e) {
			logger.error(UNKNOWNEXCEPTION, e);
			return getFailResult(UNKNOWNEXCEPTION);
		}
	}

	@RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> remove(@PathVariable Integer id,
			BankCard bankCard, HttpServletRequest request) {
		try {
			int i = bankCardService.deleteCard(id);
			if (i == 1) {
				return getSuccessResult();
			}
			return getFailResult("数据更新失败");
		} catch (LifeExcepiton e) {
			logger.error(e.getErrorMsg(), e);
			return getFailResult(e.getErrorMsg());
		} catch (Exception e) {
			logger.error(UNKNOWNEXCEPTION, e);
			return getFailResult(UNKNOWNEXCEPTION);
		}
	}

}
