package com.projects.digitalpostmasterrest.common;


import com.projects.digitalpostmasterrest.constant.PackagePriceConstants;
import org.springframework.stereotype.Service;

@Service
public class CommonMethods {

    public Float priceCalculator(Float weight){
        if (weight <= 0.5) {
            return getRandomValueInRange(PackagePriceConstants.DOMESTIC_UP_TO_0_5KG_MIN, PackagePriceConstants.DOMESTIC_UP_TO_0_5KG_MAX);
        } else if (weight <= 1) {
            return getRandomValueInRange(PackagePriceConstants.DOMESTIC_0_5KG_TO_1KG_MIN, PackagePriceConstants.DOMESTIC_0_5KG_TO_1KG_MAX);
        } else if (weight <= 2) {
            return getRandomValueInRange(PackagePriceConstants.DOMESTIC_1KG_TO_2KG_MIN, PackagePriceConstants.DOMESTIC_1KG_TO_2KG_MAX);
        } else if (weight <= 5) {
            return getRandomValueInRange(PackagePriceConstants.DOMESTIC_2KG_TO_5KG_MIN, PackagePriceConstants.DOMESTIC_2KG_TO_5KG_MAX);
        } else if (weight <= 10) {
            return getRandomValueInRange(PackagePriceConstants.DOMESTIC_5KG_TO_10KG_MIN, PackagePriceConstants.DOMESTIC_5KG_TO_10KG_MAX);
        } else if (weight <= 20) {
            return getRandomValueInRange(PackagePriceConstants.DOMESTIC_10KG_TO_20KG_MIN, PackagePriceConstants.DOMESTIC_10KG_TO_20KG_MAX);
        }
        return 0.0F;
    }

    private static Float getRandomValueInRange(Float min, Float max) {
        return (float) (min + Math.random() * (max - min));
    }

}