package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.request.DonationRequest;
import com.cavaleiros.ginkano.core.domain.response.DonationAllResponse;
import com.cavaleiros.ginkano.core.domain.response.DonationResponse;
import com.cavaleiros.ginkano.exception.InvalidTokenException;

public interface DonationUsecase {

    DonationResponse createDonation(DonationRequest donation, String auth) throws InvalidTokenException;
    DonationResponse updateDonation(DonationRequest donation, String auth) throws InvalidTokenException;
    DonationResponse deleteDonation(DonationRequest donation, String auth);
    DonationResponse selectDonation(String code, String auth) throws InvalidTokenException;
    DonationAllResponse allDonations(String auth, String token);
}
