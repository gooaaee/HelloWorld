package com.itheima.domain;

import java.util.Date;

/*
pidvarchar(32) NOT NULL
pnamevarchar(50) NULL
market_pricedouble NULL
shop_pricedouble NULL
pimagevarchar(200) NULL
pdatedate NULL
is_hotint(11) NULL
pdescvarchar(255) NULL
pflagint(11) NULL
cidvarchar(32) NULL
 */
public class Product {
	private String pid;//商品编号
	private String pname;//商品名
	private double market_price;//商品市场价格
	private double shop_price;//商品商店价格
	private String pimage;//商品图片路径
	private Date pdate;//商品上架日期
	private int is_hot;//商品是否热门
	private String pdesc;//商品描述
	private int pflag;//商品是否下架,0:上架,1:下架
	private String cid;//商品所属分类
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}
	public double getShop_price() {
		return shop_price;
	}
	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public int getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getPflag() {
		return pflag;
	}
	public void setPflag(int pflag) {
		this.pflag = pflag;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", market_price=" + market_price + ", shop_price="
				+ shop_price + ", pimage=" + pimage + ", pdate=" + pdate + ", is_hot=" + is_hot + ", pdesc=" + pdesc
				+ ", pflag=" + pflag + ", cid=" + cid + "]";
	}
	
}
