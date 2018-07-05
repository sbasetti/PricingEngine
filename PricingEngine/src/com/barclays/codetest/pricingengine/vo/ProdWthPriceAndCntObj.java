package com.barclays.codetest.pricingengine.vo;

import java.util.Map;

public final class ProdWthPriceAndCntObj
{
	private String								prodNm;
	private Map<Double, Integer>	priceCntHMap;

	public Map<Double, Integer> getPriceCntHMap()
	{
		return priceCntHMap;
	}

	double price;

	ProdWthPriceAndCntObj(String prodNm, Map<Double, Integer>	priceCntHMap)
	{
		this.prodNm = prodNm;
		this.priceCntHMap = priceCntHMap;
	}

	public String getProductCode()
	{
		return prodNm;
	}

	public double getPrice()
	{
		return price;
	}


}