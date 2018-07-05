
package com.barclays.codetest.pricingengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import com.barclays.codetest.pricingengine.vo.SortByRule;
import com.barclays.codetest.pricingengine.vo.ProdWthCompetrNmPriceObj;
import com.barclays.codetest.pricingengine.vo.ProdWthSupplyDemCodeObj;
import com.barclays.codetest.pricingengine.vo.SupplyDemandCodeObj;

public final class PricingEngine
{
	protected static final Map<String, Double>	SUPLYDEMANDFCTR							= new HashMap<String, Double>()
																																{
																																	{
																																		put(HH, 1.0);
																																		put(HL, 0.95);
																																		put(LH, 1.05);
																																		put(LL, 1.1);
																																	}
																																};
	protected List<String>											inputProdSuplyDemStrLst			= new ArrayList<>();
	protected List<String>											inputProdAndPrceStrLst			= new ArrayList<>();
	protected List<ProdWthSupplyDemCodeObj>			prodWthSupplyDemCodeObjLst	= new ArrayList<>();
	protected List<ProdWthCompetrNmPriceObj>		prodWthCompetrNmPriceObjLst	= new ArrayList<>();
	protected Map<String, SupplyDemandCodeObj>	supplyDemandsByProduct;
	protected Map<String, SupplyDemandCodeObj>	prodWthSupAndDemHMap				= new HashMap<>();
	protected Map<String, Map<Double, Long>>		prodWthPriceCntMapOfHMap		= new HashMap<>();

	protected static final String								HH													= "H H";
	protected static final String								HL													= "H L";
	protected static final String								LH													= "L H";
	protected static final String								LL													= "L L";

	public PricingEngine()
	{
		// TODO Auto-generated constructor stub
	}

	void processEngineRules()
	{
		Map<String, Double> prodCdWthAvgPrcHMap = prodWthCompetrNmPriceObjLst.stream().collect(Collectors.groupingBy(
				ProdWthCompetrNmPriceObj::getProductCode, Collectors.averagingDouble(ProdWthCompetrNmPriceObj::getPrice)));

		/*	 Rule 1: Prices less than 50% of average price are treated as promotion and not considered
			 Rule 2: Prices more than 500% of average price are treated as data errors and not considered.
		*/
		prodWthCompetrNmPriceObjLst = prodWthCompetrNmPriceObjLst.stream()
				.filter(obj -> prodCdWthAvgPrcHMap.get(obj.getProductCode()) * 0.5 <= obj.getPrice()
						&& obj.getPrice() <= prodCdWthAvgPrcHMap.get(obj.getProductCode()) * 5.0)
				.collect(Collectors.toList());

		// sort before applying further rules
		prodWthCompetrNmPriceObjLst.sort(Comparator.comparing(ProdWthCompetrNmPriceObj::getProductCode));

		/*Rule 3:
		 The retail company uses a Pricing engine which recommends most frequently
		 occurring price.	 If multiple prices occur frequently, the least amongst them is chosen.
		Rule 3:
			Applying PriceEngine rule 3:
				If Supply is High and Demand is High, Product is sold at same price as chosen price.
				If Supply is Low and Demand is Low, Product is sold at 10 % more than chosen price.
				If Supply is Low and Demand is High, Product is sold at 5 % more than chosen price.
				If Supply is High and Demand is Low, Product is sold at 5 % less than chosen price.
			*/
		prodWthPriceCntMapOfHMap = prodWthCompetrNmPriceObjLst.stream()
				.collect(Collectors.groupingBy(ProdWthCompetrNmPriceObj::getProductCode,
						Collectors.groupingBy(ProdWthCompetrNmPriceObj::getPrice, Collectors.counting())));

		for (Entry<String, Map<Double, Long>> entry : prodWthPriceCntMapOfHMap.entrySet())
		{

			List<Entry<Double, Long>> entryLst = entry.getValue().entrySet().stream().collect(Collectors.toList());
			Collections.sort(entryLst, new SortByRule());

			Optional<ProdWthSupplyDemCodeObj> pr = prodWthSupplyDemCodeObjLst.stream()
					.filter(p -> p.getProductCode().equals(entry.getKey())).findFirst();

			String Prod = entry.getKey();

			Double fnlPrice = (entryLst.get(0).getKey())
					* SUPLYDEMANDFCTR.get(pr.get().getSupplyDemandCodeObj().getSupDmndCd());

			System.out.println(Prod + "  " + fnlPrice);
		}
	}

}
