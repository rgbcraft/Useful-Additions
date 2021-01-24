package com.rgbcraft.usefuladditions.utils;

import java.util.Map;


public interface ICardInfoProvider {

    String getMachineName();

    Map<String, String> getRows(Map<String, String> rows);

}
