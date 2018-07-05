package com.barclays.codetest.pricingengine.vo;

public final class SupplyDemandCodeObj
{
	public String getSupply()
	{
		return supply;
	}

	public String getDemand()
	{
		return demand;
	}

	String	supply;
	String	demand;

	public SupplyDemandCodeObj(String supply, String demand)
	{
		this.supply = supply;
		this.demand = demand;
	}

	public String getSupDmndCd()
	{
		return this.supply +" "+ this.demand;
	}
}