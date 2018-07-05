package com.barclays.codetest.pricingengine.vo;

import java.util.Comparator;
import java.util.Map;

public class SortByRule implements Comparator<Map.Entry<Double, Long>>
{
	// recommend most frequently occurring price.
	// If multiple prices occur frequently,
	// the least amongst them is chosen
	public int compare(Map.Entry<Double, Long> prodPrcCnt_a, Map.Entry<Double, Long> prodPrcCnt_b)
	{

		if (prodPrcCnt_b.getValue().intValue() == prodPrcCnt_a.getValue().intValue())
			return (prodPrcCnt_a.getKey().intValue() - prodPrcCnt_b.getKey().intValue());
		else
			return (prodPrcCnt_b.getValue().intValue() - prodPrcCnt_a.getValue().intValue());
	}

}