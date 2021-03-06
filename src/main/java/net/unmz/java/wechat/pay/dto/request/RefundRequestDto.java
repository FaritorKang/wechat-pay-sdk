package net.unmz.java.wechat.pay.dto.request;

import net.unmz.java.util.data.ValidateLength;
import net.unmz.java.wechat.pay.dto.BaseRequestDto;

/**
 * Project Name: 微信支付SDK
 * 功能描述：微信支付申请退款请求实体
 *
 * @author faritor@unmz.net
 * @version 1.0
 * @date 2018-3-31 23:26
 * @since JDK 1.8
 */
public class RefundRequestDto extends BaseRequestDto {

    private static final long serialVersionUID = -1006502932011059109L;
    @ValidateLength(value = 32)
    private String transaction_id;//String(32)		微信生成的订单号，在支付通知中有返回
    @ValidateLength(value = 32)
    private String out_trade_no;//String(32)		商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
    @ValidateLength(value = 64, nullable = false)
    private String out_refund_no;//是	String(64)	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
    @ValidateLength(nullable = false)
    private String total_fee;//是	Int	        	订单总金额，单位为分，只能为整数，详见支付金额
    @ValidateLength(nullable = false)
    private String refund_fee;//是	Int	        	退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
    @ValidateLength(value = 8)
    private String refund_fee_type;//否	String(8)	退款货币类型，需与支付一致，或者不填。符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    @ValidateLength(value = 80)
    private String refund_desc;//否	String(80)		若商户传入，会在下发给用户的退款消息中体现退款原因
    @ValidateLength(value = 30)
    private String refund_account;//否	String(30)  仅针对老资金流商户使用 REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
    @ValidateLength(value = 256)
    private String notify_url;//否	String(256)	    异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getRefund_fee_type() {
        return refund_fee_type;
    }

    public void setRefund_fee_type(String refund_fee_type) {
        this.refund_fee_type = refund_fee_type;
    }

    public String getRefund_desc() {
        return refund_desc;
    }

    public void setRefund_desc(String refund_desc) {
        this.refund_desc = refund_desc;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
}
