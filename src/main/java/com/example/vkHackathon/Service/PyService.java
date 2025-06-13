package com.example.vkHackathon.Service;

import com.example.vkHackathon.dto.PyServiceResponseDto;
import com.example.vkHackathon.dto.ResponseDto;
import com.example.vkHackathon.util.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PyService {

    private final Utils utils;

    public PyService(Utils utils) {
        this.utils = utils;
    }

    public ResponseDto analyzeResponse(PyServiceResponseDto dto, List<String> sanctionedNames) {
        if (dto == null) return null;

        List<String> reasons = new ArrayList<>();

        // Проверка на РФ, РБ, Украина
        if (dto.getForeignPartnerCountry() != null) {
            String country = dto.getForeignPartnerCountry().trim();

            if (country.equalsIgnoreCase("рк") || country.equalsIgnoreCase("rk")) {
                reasons.add("Валютные операции между резидентами РК запрещены.");
            } else {
                String partnerName = dto.getForeignPartnerName().toLowerCase();
                boolean isSanctionedCountry = country.equalsIgnoreCase("Россия") ||
                        country.equalsIgnoreCase("РФ") ||
                        country.equalsIgnoreCase("Беларусь") ||
                        country.equalsIgnoreCase("РБ") ||
                        country.equalsIgnoreCase("Украина");

                if (isSanctionedCountry && checkSanctionList(sanctionedNames, partnerName)) {
                    dto.setRelatedToRF_RB_UA(true);
                    reasons.add("Санкционный Банк");
                }
            }
        }

        // проверка валюты договора
        if (dto.getContractCurrency() != null) {
            String currencyCode = dto.getContractCurrency().trim().toUpperCase();

            if(currencyCode == null) {
                reasons.add("Нет валюты договора");
            }
            else {
                if (!utils.isValidCurrencyCode(currencyCode)) {
                    reasons.add("Неверный код валюты: " + currencyCode);
                }
            }
        }

        ResponseDto response = new ResponseDto();
        response.setIsOk(reasons.isEmpty());
        response.setReasonList(reasons);

        return response;
    }

    private boolean checkSanctionList(List<String> sanctionedNames, String partnerName) {
        for (String name : sanctionedNames) {
            if (partnerName.contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
