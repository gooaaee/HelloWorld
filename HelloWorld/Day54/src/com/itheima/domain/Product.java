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
	private String pid;//��Ʒ���
	private String pname;//��Ʒ����
	private double market_price;//�г��۸�
	private double shop_price;//�̵�۸�
	private String pimage;//��ƷͼƬ·��
	private Date pdate;//��Ʒ�ϼ�����
	private int is_hot;//��Ʒ�Ƿ����� 1:����,0:������
	private String pdesc;//��Ʒ������Ϣ
	private int pflag;//��Ʒ�Ƿ��¼�,1:���¼�,0:δ�¼�
	private String cid;//��Ʒ��������
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
