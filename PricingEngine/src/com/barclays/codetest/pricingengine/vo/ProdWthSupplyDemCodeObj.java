package com.barclays.codetest.pricingengine.vo;

 public final class ProdWthSupplyDemCodeObj
	{
		private String				prodNm;
		private SupplyDemandCodeObj	supDmndObj;

		public ProdWthSupplyDemCodeObj(String prodNm, SupplyDemandCodeObj sDObj)
		{
			this.prodNm = prodNm;
			this.supDmndObj = sDObj;
		}


		public String getProductCode()
		{
			return prodNm;
		}


		public SupplyDemandCodeObj getSupplyDemandCodeObj()
		{
			return supDmndObj;
		}


	}