package zhuanzhuan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class TbSpPage {
	public static final String GOODS_STATUS_INVALID = "下架";
	public static final String ES_INDEX = "tbspage_jsdriven_store";
	private String tbGoodsId;
	private String tbGoodsTitle;// 产品标题:
	private String tbGoodsSellDesc;// 产品卖点描述
	private String taoBaoprice;//淘宝价格
	private String taoBaopOldrice;//淘宝价格
	private String quanPrice;//优惠券券的价格
	
	private String zhuanQQUrl;//转换qq的连接
	
	public String getZhuanQQUrl() {
		return zhuanQQUrl;
	}

	public void setZhuanQQUrl(String zhuanQQUrl) {
		this.zhuanQQUrl = zhuanQQUrl;
	}

	private String stoceName;// 商店名称
	private String distriInifo;// 配送
	private String skuInfo; // sku组合信息
	private String serviceIcon;// 承诺
	private String payInfo;// 支付方式
	private String tbGoodsImg;// 多图以;隔开 //List<TbSpPageImg> tbGoodsImg; // 产品主图
	
	private List<File> tbGoodsImgFiles = new ArrayList<File>();// 多图以;隔开 //List<TbSpPageImg> tbGoodsImg; // 产品主图
	
	private String tbGoodsDetailInfo; // 详细信息
	private String tbGoodsDetailImg;// 多图以;隔开 //List<TbSpPageImg>
									// tbGoodsDetailImg; // 详情图

	private String tbGoodsImgLocal;// 产品本地主图

	private String tbGoodsDetailImgLocal;// 详情图
	
	private String status="1";//商品状态 1:正常 ;2:下架
	
	public List<File> getTbGoodsImgFiles() {
		return tbGoodsImgFiles;
	}

	public void setTbGoodsImgFiles(List<File> tbGoodsImgFiles) {
		this.tbGoodsImgFiles = tbGoodsImgFiles;
	}

	private String pageUrl;//页面地址

	private String beginTime;
	private String endTime;
	
	
	private String shopId;
	private String shopName;
	private String sellerId;
	private String sellerNick;
	private String sibUrl;
	
	
	private String executeId;

	public String getTbGoodsId() {
		return tbGoodsId;
	}

	public void setTbGoodsId(String tbGoodsId) {
		this.tbGoodsId = tbGoodsId;
	}

	public String getTbGoodsTitle() {
		return tbGoodsTitle;
	}

	public void setTbGoodsTitle(String tbGoodsTitle) {
		this.tbGoodsTitle = tbGoodsTitle;
	}

	public String getTbGoodsSellDesc() {
		return tbGoodsSellDesc;
	}

	public void setTbGoodsSellDesc(String tbGoodsSellDesc) {
		this.tbGoodsSellDesc = tbGoodsSellDesc;
	}

	public String getExecuteId() {
		return executeId;
	}

	public void setExecuteId(String executeId) {
		this.executeId = executeId;
	}

	public String getStoceName() {
		return stoceName;
	}

	public void setStoceName(String stoceName) {
		this.stoceName = stoceName;
	}

	public String getDistriInifo() {
		return distriInifo;
	}

	public void setDistriInifo(String distriInifo) {
		this.distriInifo = distriInifo;
	}

	public String getSkuInfo() {
		return skuInfo;
	}

	public void setSkuInfo(String skuInfo) {
		this.skuInfo = skuInfo;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getTbGoodsDetailInfo() {
		return tbGoodsDetailInfo;
	}

	public void setTbGoodsDetailInfo(String tbGoodsDetailInfo) {
		this.tbGoodsDetailInfo = tbGoodsDetailInfo;
	}

	public String getServiceIcon() {
		return serviceIcon;
	}

	public void setServiceIcon(String serviceIcon) {
		this.serviceIcon = serviceIcon;
	}

	public String getTbGoodsImg() {
		return tbGoodsImg;
	}

	public void setTbGoodsImg(String tbGoodsImg) {
		this.tbGoodsImg = tbGoodsImg;
	}

	public String getTbGoodsDetailImg() {
		return tbGoodsDetailImg;
	}

	public void setTbGoodsDetailImg(String tbGoodsDetailImg) {
		this.tbGoodsDetailImg = tbGoodsDetailImg;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTbGoodsImgLocal() {
		return tbGoodsImgLocal;
	}

	public void setTbGoodsImgLocal(String tbGoodsImgLocal) {
		this.tbGoodsImgLocal = tbGoodsImgLocal;
	}

	public String getTbGoodsDetailImgLocal() {
		return tbGoodsDetailImgLocal;
	}

	public void setTbGoodsDetailImgLocal(String tbGoodsDetailImgLocal) {
		this.tbGoodsDetailImgLocal = tbGoodsDetailImgLocal;
	}
	

	public String getTaoBaoprice() {
		return taoBaoprice;
	}

	public void setTaoBaoprice(String taoBaoprice) {
		this.taoBaoprice = taoBaoprice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getSibUrl() {
		return sibUrl;
	}

	public void setSibUrl(String sibUrl) {
		this.sibUrl = sibUrl;
	}

	public String getTaoBaopOldrice() {
		return taoBaopOldrice;
	}

	public void setTaoBaopOldrice(String taoBaopOldrice) {
		this.taoBaopOldrice = taoBaopOldrice;
	}

	@Override
	public String toString() {
		return "TbSpPage [tbGoodsId=" + tbGoodsId + ", tbGoodsTitle="
				+ tbGoodsTitle + ", tbGoodsSellDesc=" + tbGoodsSellDesc
				+ ", taoBaoprice=" + taoBaoprice + ", stoceName=" + stoceName
				+ ", distriInifo=" + distriInifo + ", skuInfo=" + skuInfo
				+ ", serviceIcon=" + serviceIcon + ", payInfo=" + payInfo
				+ ", tbGoodsImg=" + tbGoodsImg + ", tbGoodsDetailInfo="
				+ tbGoodsDetailInfo + ", tbGoodsDetailImg=" + tbGoodsDetailImg
				+ ", tbGoodsImgLocal=" + tbGoodsImgLocal
				+ ", tbGoodsDetailImgLocal=" + tbGoodsDetailImgLocal
				+ ", status=" + status + ", pageUrl=" + pageUrl
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", shopId=" + shopId + ", shopName=" + shopName
				+ ", sellerId=" + sellerId + ", sellerNick=" + sellerNick
				+ ", sibUrl=" + sibUrl + "]";
	}

	public String getQuanPrice() {
		return quanPrice;
	}

	public void setQuanPrice(String quanPrice) {
		this.quanPrice = quanPrice;
	}
	
}
