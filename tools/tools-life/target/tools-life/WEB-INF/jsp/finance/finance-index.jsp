<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>银行卡管理</title>
    <life:script isQueyPage="yes"/>
    <script>
        $(document).ready(function () {
            /* loadBind();
             initDataTable();
             initColumn(); */
        });
    </script>
</head>

<body>
<div class="page-wrapper">

    <div class="breadCrumb">
        <a href="javascript:parent.location.reload();"> <i class="icon icon-home icon-orange"></i> 首页</a>
        &nbsp;&gt;&nbsp; <span>财务管理</span>
        &nbsp;&gt;&nbsp; <span style="font-weight: 600;">银行卡管理</span>
    </div>

    <div class="alert alert-info pusht" style="width: 80%;margin-left: 50px;background-color:${financeView.netAssets>10000?'#00FF7F':(financeView.netAssets<0?'#FFFF00':'D3D3D3')}">
        <table class="form">
            <tbody>
            <tr>
                <td colspan="6" style="font-size: 17px;">净资产：${financeView.netAssets}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(净资产=总资产-总负债)</td>
            </tr>
            <tr style="font-size: 15px;">
                <td align="right" valign="middle" width="15%">
                    总资产：
                </td>
                <td align="left" valign="middle" width="15%">
                    ${financeView.totalAssets}
                </td>
                <td align="right" valign="middle" width="15%">
                    总债务：
                </td>
                <td align="left" valign="middle" width="15%">
                    ${financeView.creditCardDebt}
                </td>
                <td align="right" valign="middle" width="15%">
                    &nbsp;&nbsp;
                </td>
                <td align="left" valign="middle" width="25%">
                    &nbsp;&nbsp;
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="alert alert-info pusht" style="width: 80%;margin-left: 50px;">
        <table class="form">
            <tbody>
            <tr>
                <td colspan="6" style="font-size: 17px;">总资产：${financeView.totalAssets}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(总资产=储蓄余额+其他余额)</td>
            </tr>
            <tr style="font-size: 15px;">
                <td align="right" valign="middle" width="15%">
                    储蓄余额：
                </td>
                <td align="left" valign="middle" width="15%">
                    ${financeView.bankAssets}
                </td>
                <td align="right" valign="middle" width="15%">
                    其他余额：
                </td>
                <td align="left" valign="middle" width="15%">
                    ${financeView.otherAssets}
                </td>
                <td align="right" valign="middle" width="15%">
                    &nbsp;&nbsp;
                </td>
                <td align="left" valign="middle" width="25%">
                    &nbsp;&nbsp;
                </td>
            </tr>
            </tbody>
        </table>
    </div>


    <div class="alert" style="width: 80%;margin-left: 50px;">
        <table class="form">
            <tbody>
            <tr>
                <td colspan="6" style="font-size: 17px;">总债务：${financeView.creditCardDebt}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(债务主要是信用卡欠款，暂时没有其他欠款)</td>
            </tr>
            <tr style="font-size: 15px;">
                <td align="right" valign="middle" width="15%">
                    信用卡欠款：
                </td>
                <td align="left" valign="middle" width="15%">
                    ${financeView.creditCardDebt}
                </td>
                <td align="right" valign="middle" width="15%">
                    信用卡可用额度：
                </td>
                <td align="left" valign="middle" width="15%">
                    ${financeView.avlCreditCardLimit}
                </td>
                <td align="right" valign="middle" width="15%">
                    信用卡总额度：
                </td>
                <td align="left" valign="middle" width="25%">
                    ${financeView.creditCardLimit}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>