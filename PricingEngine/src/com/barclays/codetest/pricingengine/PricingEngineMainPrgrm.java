
package com.barclays.codetest.pricingengine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.barclays.codetest.pricingengine.vo.ProdWthCompetrNmPriceObj;
import com.barclays.codetest.pricingengine.vo.ProdWthSupplyDemCodeObj;
import com.barclays.codetest.pricingengine.vo.SupplyDemandCodeObj;

public class PricingEngineMainPrgrm
{

	public PricingEngineMainPrgrm()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] parms)
	{
		System.out.println("Please enter input data in the pattern given in sample, on the console/commandPrompt");

		try
		{
			PricingEngine priceEngine = new PricingEngine();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			Integer prodCnt = Integer.parseInt(br.readLine());
			for (int i = 0; i < prodCnt; i++)
			{
				priceEngine.inputProdSuplyDemStrLst.add(br.readLine());
			}

			int competitorCnt = Integer.parseInt(br.readLine());

			for (int i = 0; i < competitorCnt; i++)
			{
				priceEngine.inputProdAndPrceStrLst.add(br.readLine());
			}
			br.close();

			// Builds ProdWthSupplyDemCodeObjLst and ProdWthCompetrNmPriceObjLst
			for (String inputProdSuplyDemStr : priceEngine.inputProdSuplyDemStrLst)
			{
				priceEngine.prodWthSupplyDemCodeObjLst.add((new ProdWthSupplyDemCodeObj(inputProdSuplyDemStr.split(" ")[0],
						new SupplyDemandCodeObj(inputProdSuplyDemStr.split(" ")[1], inputProdSuplyDemStr.split(" ")[2]))));
			}

			for (String inputProdAndPrceStr : priceEngine.inputProdAndPrceStrLst)
			{

				priceEngine.prodWthCompetrNmPriceObjLst.add(new ProdWthCompetrNmPriceObj(inputProdAndPrceStr.split(" ")[0],
						inputProdAndPrceStr.split(" ")[1], inputProdAndPrceStr.split(" ")[2]));
			}
			System.out.println("");
			priceEngine.processEngineRules();
		} catch (Exception e)
		{
			System.out.println(
					"Input Data is not in the pattern/format given in problem-sample. Sample-input is given inside readme.txt\n");
			e.printStackTrace();
		}
	}
}
