package com.barclays.codetest.pricingengine.vo;

public final class ProdWthCompetrNmPriceObj
{
	private String	prodtNm;
	private String	competitor;
	private double	price;

	public ProdWthCompetrNmPriceObj(String prodNm, String competitor, String price)
	{
		this.prodtNm = prodNm;
		this.competitor = competitor;
		this.price = Double.parseDouble(price);
	}

	public String getProductCode()
	{
		return prodtNm;
	}

	public String getCompetitor()
	{
		return competitor;
	}

	public double getPrice()
	{
		return price;
	}

	public String toString()
	{
		return this.prodtNm + " " + this.competitor + " " + this.price;
	}
}