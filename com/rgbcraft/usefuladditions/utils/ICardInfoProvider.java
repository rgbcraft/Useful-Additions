package com.rgbcraft.usefuladditions.utils;

import java.util.Map;

public interface ICardInfoProvider {

	public String getName();
	
	public Map<String, String> getRows(Map<String, String> rows);

}
